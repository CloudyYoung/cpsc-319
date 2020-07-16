import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * CPSC 319 Spring 2020 Assignment 1
 */
class Anagrams {
    /**
     * Main method as the entrace
     * @reference String into ArrayList:
     *            https://stackoverflow.com/questions/7347856/how-to-convert-a-string-into-an-arraylist
     */
    public static void main(final String[] args) {

        // The input and output file names
        String[] inputs = { "example_1--8_words.txt", "example_2--13_words.txt", "example_3--19_words.txt",
                "example_4--267_words.txt" };
        String[] outputs = { "example_1_out.txt", "example_2_out.txt", "example_3_out.txt", "example_4_out.txt" };

        int index = 0;
        for (String input : inputs) {
            ArrayList<String> list = readListFromFile(input); // Read list from file; this is Step #1 in Assignment PDF, unsorted 1-D array read from file
            Map<String, ArrayList<String>> map = anagrams(list); // Run anagrams
            writeStringToFile(concatenateMap(map), outputs[index]); // Concat to string and write to file; this is Step #3 generating the required output text
            index++;
        }
    }

    /**
     * @param list the list to be solved
     * @return the solved map
     */
    public static Map<String, ArrayList<String>> anagrams(ArrayList<String> list) {
        ArrayList<String> sortedList = insertionSort(list); // This is Step #2, sorted 1-D array

        Map<String, ArrayList<String>> map = new LinkedHashMap<String, ArrayList<String>>();

        for (String each : sortedList) {
            ArrayList<String> stringList = new ArrayList<String>(Arrays.asList(each.split(""))); // Split each letter and store into an array list
            String sortedLetter = String.join("", insertionSort(stringList)); // Sort the letters

            // According to sorted 'word', add to its own family in map, just like the approach given in the assignment PDF
            // Eg. "ate" and "eat" will both be sorted into "aet", and they are both stored into the map as "aet": ["ate", "eat"], where "aet" is the family 'word' and key in map, ["ate", "eat"] are the members and values in map

            if (!map.containsKey(sortedLetter)) { // If the family 'word' does not exist
                map.put(sortedLetter, new ArrayList<String>()); // Add one
            }

            map.get(sortedLetter).add(each); // Add the word itself to its family
        }

        return map;
    }

    /**
     * @param fileName the name of the file
     * @return the file content in string
     * @reference Read Files: https://www.w3schools.com/java/java_files_read.asp
     */
    public static ArrayList<String> readListFromFile(String fileName) {
        final ArrayList<String> list = new ArrayList<String>();

        try {
            final File file = new File(fileName);
            final Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                final String data = scanner.nextLine();
                list.add(data);
            }
            scanner.close();
        } catch (final IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return list;
    }

    /**
     * @param string the content to be written as string
     * @param fileName the name of the file
     * @reference Write Files: https://www.w3schools.com/java/java_files_create.asp
     */
    public static void writeStringToFile(String string, String filename) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(string);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * @param list the unsorted list
     * @return the sorted list
     * @reference: The introducion of insertion sort from powerpoint "5 - Searching
     *             & SORTING - (3) Insertion"
     */
    public static ArrayList<String> insertionSort(ArrayList<String> list) {

        for (int t = 0; t < list.size(); t++) {
            String current = list.get(t);
            int sortedIndex = t - 1;

            // Move elements
            while (sortedIndex >= 0 && current.compareToIgnoreCase(list.get(sortedIndex)) < 0) {
                list.set(sortedIndex + 1, list.get(sortedIndex));
                sortedIndex--;
            }

            // Insert
            list.set(sortedIndex + 1, current);
        }

        return list;
    }

    /**
     * @param map the solved map
     * @return the concatenated string
     */
    public static String concatenateMap(Map<String, ArrayList<String>> map){
        String result = "";
        for (ArrayList<String> each : map.values()) {
            result += String.join(" ", each) + "\r\n";
        }
        return result;
    }

}