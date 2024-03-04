import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static String ANSI_RED = "\u001B[31m";
    public static String ANSI_WHITE = "\u001B[0m";
    public static String ANSI_GREEN = "\u001B[32m";

    public static void createTable() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert table name>>> ");
        String tableName = scanner.next();
        System.out.print("Insert input file path>>> ");
        String input = scanner.next();
        System.out.print("Insert output file path>>> ");
        String output = scanner.next();
        try {
            FrequencyDictionary dictionary = new FrequencyDictionary (
                    new File(input),
                    new File(output)
            );
            dictionary.generateTable(tableName);
            System.out.println(ANSI_GREEN + tableName + " generated successfully and saved in " + output + ANSI_WHITE);
        }
        catch (IOException e) {
            System.out.println(ANSI_RED + e.getMessage() + ANSI_WHITE);
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to frequency dictionary creator!");
        while (true) {
            System.out.println("1 - create new table");
            System.out.println("2 - exit from program");
            System.out.print(">>> ");
            Scanner scanner = new Scanner(System.in);
            int operation = scanner.nextInt();
            switch (operation) {
                case 1:
                    createTable();
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.println(ANSI_RED + "Unknown command" + ANSI_WHITE);
            }
        }
    }
}