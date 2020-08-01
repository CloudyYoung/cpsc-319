import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    /**
     * ANSI colors for sysout printing
     * 
     * @reference: https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String next = null;
        String file = "";
        BinaryTree binaryTree = null;
        int step = 0;

        while (true) {

            if (next != null && next.equals("!find") && !file.isEmpty()) { // If want to move on to next function
                step = 1;
                next = null; // Clear user input

            } else if (next != null && next.equals("!trav") && !file.isEmpty()) { // If want to move on to next function
                step = 2;
                next = null; // Clear user input

            } else if (next != null && next.equals("!end")) { // If want to end program
                break;

            } else if (step == 0 && next == null) { // Print tree structure and statistics
                System.out.println("");
                System.out.print("Enter the input file name: ");
                next = next(scan);

            } else if (step == 0 && next != null) { // Print tree structure and statistics
                ArrayList<String> list = readListFromFile(next + ".txt");
                file = next;
                if (!list.isEmpty()) {
                    binaryTree = new BinaryTree(list);
                    System.out.println(ANSI_YELLOW + binaryTree + ANSI_RESET);
                    System.out.println("Total number of words in " + ANSI_YELLOW + file + ANSI_RESET + " = "
                            + ANSI_YELLOW + binaryTree.getTotalNodes() // Prints total words
                            + ANSI_RESET);
                    System.out.println("Number of unique words in " + ANSI_YELLOW + file + ANSI_RESET + " = "
                            + ANSI_YELLOW + binaryTree.getUniqueNodes() // Prints unique words
                            + ANSI_RESET);
                    System.out.println("The word(s) which occur(s) most often and the number "
                            + "of times that it/they occur(s) = ");
                    for (Node each : binaryTree.getMostFrequentNodes()) {
                        System.out.println("  " + ANSI_YELLOW + each.getValue() // Print most frequent word
                                + ANSI_RESET + " = " + ANSI_YELLOW + each.getFrequency() // Print its frequency
                                + ANSI_RESET + " times");
                    }
                    step++; // Automatically go to next function
                }
                next = null; // Clear user input

            } else if (step == 1 && next == null) { // Find words
                System.out.println("");
                System.out.print("Enter the word you are looking for in " + ANSI_YELLOW + file + ANSI_RESET + " ? ");
                next = next(scan);

            } else if (step == 1 && next != null) { // Find words
                Node node = binaryTree.search(next);
                if (node != null) {
                    System.out.print(ANSI_GREEN_BACKGROUND + ANSI_BLACK + "FOUND" + ANSI_RESET + " ");
                    System.out.println("It appears " + ANSI_GREEN + node.getFrequency() + ANSI_RESET
                            + " times in the input text file");
                } else {
                    System.out.print(ANSI_RED_BACKGROUND + ANSI_BLACK + "FAIL" + ANSI_RESET + " ");
                    System.out.println(ANSI_RED + "Word not found!" + ANSI_RESET);
                }
                next = null;

            } else if (step == 2 && next == null) { // Traversal
                System.out.println("");
                System.out.print(
                        "Enter the BST traversal method " + ANSI_PURPLE + "(1 = IN-ORDER, 2 = PRE-ORDER, 3 = POST-ORDER)"
                                + ANSI_RESET + " for " + ANSI_GREEN + file + ANSI_RESET + " ? ");
                next = next(scan);

            } else if (step == 2 && next != null) { // Traversal

                ArrayList<Node> nodes;
                if (next.equals("1")) {
                    System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "IN-ORDER" + ANSI_RESET + " ");
                    nodes = binaryTree.inOrder();

                } else if (next.equals("2")) {
                    System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "PRE-ORDER" + ANSI_RESET + " ");
                    nodes = binaryTree.preOrder();

                } else if (next.equals("3")) {
                    System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "POST-ORDER" + ANSI_RESET + " ");
                    nodes = binaryTree.postOrder();
                } else {
                    System.out.print(ANSI_RED_BACKGROUND + ANSI_BLACK + "FAIL" + ANSI_RESET + " ");
                    System.out.println(ANSI_RED + "Input value is invalid" + ANSI_RESET);
                    next = null;
                    continue;
                }

                System.out.print("output: ");
                System.out.print("" + ANSI_YELLOW + "");

                for (Node node : nodes) {
                    System.out.print(node.getValue() + " ");
                }

                System.out.println(ANSI_RESET + "");
                next = null;

            } else {
                next = null;
                break;
            }
        }

        scan.close();

        System.out.println(ANSI_RESET + "");
        System.out.print(ANSI_CYAN_BACKGROUND + ANSI_BLACK + "ENDED" + ANSI_RESET + " ");
        System.out.println(ANSI_CYAN + "Program has ended. Thank you for using!" + ANSI_RESET);

    }

    private static String next(Scanner scan) {
        System.out.print(ANSI_PURPLE + "");
        String next = scan.next();
        System.out.print(ANSI_RESET);
        scan.nextLine();
        return next;
    }

    /**
     * @param fileName the name of the file
     * @return all words in the file in string
     * @reference Read Files: https://www.w3schools.com/java/java_files_read.asp
     */
    public static ArrayList<String> readListFromFile(String fileName) {
        final ArrayList<String> list = new ArrayList<String>();

        try {
            final File file = new File(fileName);
            final Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                Pattern reg = Pattern.compile("([A-Za-z']+)"); // Regular expresson to match words
                Matcher res = reg.matcher(line);
                while (res.find()) {
                    list.add(res.group().toLowerCase());
                }
            }
            scanner.close();
        } catch (final IOException e) {
            System.out.println("An error occurred when reading " + fileName);
        }

        return list;
    }

}