package utils;

import java.util.Scanner;

/**
 * This class provides methods for the robust handling of I/O using Scanner.
 * It creates a new Scanner object for each read from the user, thereby
 * eliminating the Scanner bug (where the buffers don't flush correctly after an int read).
 *
 * The methods also parse the numeric data entered to ensure it is correct. If it isn't correct,
 * the user is prompted to enter it again.
 *
 * @author Siobhan Drohan, Mairead Meagher
 * @version 1.0
 *
 */

public class ScannerInput {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readNextInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid integer. Try again.");
            scanner.nextLine();
            System.out.print(prompt);
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // clear buffer
        return value;
    }

    public static char readNextChar(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();

        while (input.isEmpty()) {
            System.out.println("Invalid character. Try again.");
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        }

        return input.charAt(0);
    }

    public static double readNextDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid number. Try again.");
            scanner.nextLine();
            System.out.print(prompt);
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // clear buffer
        return value;
    }

    public static String readNextString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static boolean readNextBoolean(String prompt) {
        System.out.print(prompt);
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("t") || input.equals("yes") || input.equals("y")) {
                return true;
            }
            if (input.equals("false") || input.equals("f") || input.equals("no") || input.equals("n")) {
                return false;
            }
            System.out.println("Invalid boolean. Enter true/false.");
            System.out.print(prompt);
        }
    }
}
