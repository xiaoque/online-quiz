package practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * @source A game to test multithread, each thread represents a horse, guess the winner
 * @author xiaoque
 * @date 2025.03.22
 */

abstract class Race {
    public static final int END = 50;
    private static int count = 1;
    private final int id = count++;
    private int position;
    public static final AtomicInteger winner = new AtomicInteger(0);
    public static final AtomicBoolean raceOver = new AtomicBoolean(false);

    public int getPosition() {
        return this.position;
    }

    public void addToPosition(int move) {
        this.position += move;
    }

    public int getId() {
        return this.id;
    }

    public boolean isToEnd() {
        return this.position >= END;
    }

    abstract boolean getRaceOver();

}

// Thread let all finish and record time
class HorseWithTime extends Race implements Runnable {
    public static Random ram = new Random();
    private long finishTime = -1;

    public long getFinishTime() {
        return this.finishTime;
    }

    public void run() {
        while (!isToEnd()) {
            addToPosition(ram.nextInt(5));

            if (isToEnd()) {
                if (finishTime == -1) {
                    finishTime = System.currentTimeMillis();
                    if (raceOver.compareAndSet(false, true)) {
                        winner.compareAndSet(0, getId());
                    }
                }

            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

    }

    @Override
    boolean getRaceOver() {
        return raceOver.get();
    }
}

public class MultiThread {
    public static void main(String[] args) {

        gameStart(5, false);

    }

    private static void gameStart(int size, boolean isAllFinish) {
        List<Race> horses = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            HorseWithTime horse = new HorseWithTime();
            horses.add(horse);
            threads.add(new Thread(horse));
        }

        threads.forEach(Thread::start);

        while (!HorseWithTime.raceOver.get()) {
            try {
                printStatus(horses);
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // interrupt other threads
        if (!isAllFinish) {
            threads.forEach(Thread::interrupt);
        }

        threads.forEach(th -> {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("\nwinner is : " + Race.winner.get());
        // print result
        if (isAllFinish)
            printFinishTimes(horses);
        else {
            printFinishPosition(horses);
        }
    }

    private static void printFinishTimes(List<Race> horses) {
        horses.stream().map(x -> (HorseWithTime) x)
                .sorted(Comparator.comparing(HorseWithTime::getFinishTime))
                .forEach(x -> System.out
                        .println(String.format("Horse %s using time: %s", x.getId(), x.getFinishTime())));
    }

    private static void printFinishPosition(List<Race> horses) {
        horses.stream().sorted(Comparator.comparing(Race::getPosition).reversed())
                .forEach(x -> System.out
                        .println(String.format("Horse %s position at : %s", x.getId(), x.getPosition())));

    }

    private static void printStatus(List<Race> horses) {
        System.out.println("══════════════════════════════════");
        horses.forEach(x -> System.out.println(String.format("Horse %s position at : %s", x.getId(), x.getPosition())));
    }
}
