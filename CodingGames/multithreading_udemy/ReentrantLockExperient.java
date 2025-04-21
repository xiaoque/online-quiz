package CodingGames.multithreading_udemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * @description ReentrantLock Part 2 - User Interface Application example 
 * @author xiaoque
 * @date 2025.04.20
 */
public class ReentrantLockExperient {
    private static int MAX_PRICE = 999;
    private static Random random = new Random();

    public static void main(String[] args) {
        manipulateDatabase(new InventoryDatabaseReadWirteLock());
        manipulateDatabase(new InventoryDatabaseReentrantLock());

    }

    private static void manipulateDatabase(InventoryDatabase db) {
        for (int i = 0; i < 10000; i++) {
            db.addItem(random.nextInt(MAX_PRICE));
        }

        Thread write = new Thread(() -> {
            while (true) {
                db.addItem(random.nextInt(MAX_PRICE));
                db.removeItem(random.nextInt(MAX_PRICE));
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                }
            }
        });

        write.start();
        List<Thread> reads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            reads.add(new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    int high = random.nextInt(MAX_PRICE);
                    int low = high > 0 ? random.nextInt(high) : 0;
                    db.getNumberOfItems(low, high);
                }
            }));
        }

        long startTime = System.currentTimeMillis();
        reads.forEach(Thread::start);
        reads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        System.out.printf("Database %s process ends for %s ms%n", db.getClass().getSimpleName(),
                System.currentTimeMillis() - startTime);
    }

    private static abstract class InventoryDatabase {
        public Map<Integer, Integer> priceMap = new TreeMap<>();

        public abstract int getNumberOfItems(int lowPrice, int highPrice);

        public abstract void addItem(int price);

        public abstract void removeItem(int price);
    }

    private static class InventoryDatabaseReentrantLock extends InventoryDatabase {
        private Lock lock = new ReentrantLock();

        @Override
        public int getNumberOfItems(int lowPrice, int highPrice) {
            lock.lock();
            try {
                return priceMap.entrySet().stream()
                        .filter(entry -> entry.getKey() >= lowPrice && entry.getKey() <= highPrice)
                        .mapToInt(Map.Entry::getValue)
                        .sum();

            } finally {
                lock.unlock();
            }
        }

        @Override
        public void addItem(int price) {
            lock.lock();
            try {
                priceMap.merge(price, 1, Integer::sum);
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void removeItem(int price) {
            lock.lock();
            try {
                priceMap.computeIfPresent(price, (k, v) -> v > 1 ? v - 1 : null);
            } finally {
                lock.unlock();
            }
        }

    }

    private static class InventoryDatabaseReadWirteLock extends InventoryDatabase {
        private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        private Lock readLock = rwLock.readLock();
        private Lock writeLock = rwLock.writeLock();

        public int getNumberOfItems(int lowPrice, int highPrice) {
            readLock.lock();
            try {
                return priceMap.entrySet().stream()
                        .filter(entry -> entry.getKey() >= lowPrice && entry.getKey() <= highPrice)
                        .mapToInt(Map.Entry::getValue)
                        .sum();

            } finally {
                readLock.unlock();
            }
        }

        public void addItem(int price) {
            writeLock.lock();
            try {
                priceMap.merge(price, 1, Integer::sum);
            } finally {
                writeLock.unlock();
            }
        }

        public void removeItem(int price) {
            writeLock.lock();
            try {
                priceMap.computeIfPresent(price, (k, v) -> v > 1 ? v - 1 : null);
            } finally {
                writeLock.unlock();
            }
        }
    }

}
