package CodingGames.multithreading_udemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * @description Threads Creation - Part 2. Thread Inheritance
 * @author xiaoque
 * @date 2025.04.19
 */
public class ThreadCreation2 {
    public static final int MAX_PASS = 9999;

    private static record Vault(int password) {
        public boolean guessPassword(int pass) {
            try {
                Thread.sleep(5);
            } catch (Exception e) {
            }
            return this.password == pass;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        Vault vault = new Vault(random.nextInt(MAX_PASS));
        List<Thread> thread = new ArrayList<>();
        thread.add(new PoliceThread());
        thread.add(new AscendingHackerThead(vault));
        thread.add(new DescendingHackerThread(vault));

        thread.stream().forEach(x -> x.start());
    }

    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        public void start() {
            System.out.printf("Thread start: %s %n", this.getName());
            super.start();
        }
    }

    private static class AscendingHackerThead extends HackerThread {
        public AscendingHackerThead(Vault vault) {
            super(vault);
        }

        public void run() {
            for (int i = 0; i < MAX_PASS; i++) {
                if (vault.guessPassword(i)) {
                    System.out.printf("%s found out the password %s %n", this.getName(), i);
                    System.exit(0);
                }

            }
        }
    }

    private static class DescendingHackerThread extends HackerThread {

        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        public void run() {
            for (int i = MAX_PASS; i >= 0; i--) {
                if (vault.guessPassword(i)) {
                    System.out.printf("%s found out the password %s %n", this.getName(), i);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread {
        public PoliceThread() {
            this.setName(this.getClass().getSimpleName());
        }

        public void start() {
            System.out.printf("Thread start: %s %n", this.getName());
            super.start();
        }

        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
            }
            System.out.println("Game over");
            System.exit(0);
        }
    }
}
