package CodingGames.multithreading_udemy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayGenerator {
    public static int MAX_NUM = 9999;
    public static Random random = new Random();

    private static void generateArrayFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("array.txt"))) {
            for (int i = 0; i < 10000; i++) {
                writer.write(arrayGenerator());
                writer.newLine();
            }
        } catch (Exception e) {

        }
    }

    private static String arrayGenerator() {
        return IntStream.range(0, 50)
                .map(x -> random.nextInt(MAX_NUM))
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
    }

    public static void main(String[] args) {
        generateArrayFile();
    }
}
