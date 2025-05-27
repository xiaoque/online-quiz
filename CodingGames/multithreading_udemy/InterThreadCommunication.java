package CodingGames.multithreading_udemy;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * @description  Inter-Thread Communication: Objects as Condition Variables - wait(), notify() and notifyAll()
 * @author xiaoque
 * @date 2025.04.21
 */
public class InterThreadCommunication {
    public static int N = 50;
    public static String INPUT = "array.txt";
    public static String OUTPUT = "result.txt";

    public static void main(String[] args) throws IOException {
        ThreadSafeQueue queue = new ThreadSafeQueue();
        File input = new File(INPUT);
        File output = new File(OUTPUT);
        // create producer and consumer that sharing the same queue
        Producer producer = new Producer(new FileReader(input), queue);
        Consumer consumer = new Consumer(new FileWriter(output), queue);

        producer.start();
        consumer.start();
    }

    private static class Consumer extends Thread {
        private FileWriter fileWriter;
        private ThreadSafeQueue queue;
        long startTime = System.currentTimeMillis();

        public Consumer(FileWriter file, ThreadSafeQueue queue) {
            this.fileWriter = file;
            this.queue = queue;
        }

        public void run() {
            while (true) {
                List<Integer>[] arr = queue.remove();
                if (arr == null) {
                    System.out.println("All inputs are used");
                    System.out.printf("Consumer ends for %s ms%n", System.currentTimeMillis() - startTime);
                    break;
                }
                String result = multiplyList(arr[0], arr[1]);
                try {
                    writeTofile(fileWriter, result);
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {

            }

        }

        private void writeTofile(FileWriter fileWriter, String result) throws IOException {
            fileWriter.write(result);
            fileWriter.write("\n");
        }

        private String multiplyList(List<Integer> list1, List<Integer> list2) {
            return IntStream.range(0, N)
                    .map(i -> list1.get(i) * list2.get(i))
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(","));
        }
    }

    private static class Producer extends Thread {
        private Scanner scanner;
        private ThreadSafeQueue queue;

        public Producer(FileReader file, ThreadSafeQueue queue) {
            this.queue = queue;
            this.scanner = new Scanner(file);
        }

        @SuppressWarnings("unchecked")
        public void run() {
            while (true) {
                List<Integer> list1 = readArray();
                List<Integer> list2 = readArray();
                if (list1 == null || list2 == null) {
                    queue.terminate();
                    System.out.println("No more input");
                    return;
                }
                queue.add(new List[] { list1, list2 });
            }
        }

        public List<Integer> readArray() {
            if (!scanner.hasNext())
                return null;

            return Arrays.stream(scanner.nextLine().split(","))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
        }
    }

    private static class ThreadSafeQueue {
        private Queue<List<Integer>[]> queue = new ArrayDeque<>();
        // limit size of queue to avoid outOfMemory
        private final int QUEUE_SIZE = 5;
        private boolean isEmpty = true;
        private boolean isTerminated = false;

        public synchronized void add(List<Integer>[] list) {
            // producer waits for consumer remove item from queue
            while (queue.size() == QUEUE_SIZE) {
                try {
                    wait();
                } catch (Exception e) {

                }
            }
            queue.offer(list);
            isEmpty = false;
            notify();
        }

        public synchronized List<Integer>[] remove() {
            List<Integer>[] pair = null;
            // Consumer waits for producer add item
            while (isEmpty && !isTerminated) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (queue.isEmpty() && isTerminated)
                return null;

            if (queue.size() == 1)
                isEmpty = true;

            System.out.printf("Current queue size : %s %n", queue.size());

            pair = queue.poll();
            // Consumer notifies producer to produce item
            if (queue.size() == QUEUE_SIZE - 1)
                notifyAll();
            return pair;
        }

        public synchronized void terminate() {
            isTerminated = true;
            notifyAll();
        }

    }
}
