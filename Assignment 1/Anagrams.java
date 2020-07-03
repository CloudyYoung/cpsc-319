import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class Anagrams {
    /**
     * @reference String into ArrayList:
     *            https://stackoverflow.com/questions/7347856/how-to-convert-a-string-into-an-arraylist
     */
    public static void main(final String[] args) {

        ArrayList<String> list = readListFromFile("example_4--267_words.txt");
        ArrayList<String> sortedList = insertionSort(list);
        Map<String, ArrayList<String>> map = new LinkedHashMap<String, ArrayList<String>>();

        for (String each : sortedList) {
            ArrayList<String> stringList = new ArrayList<String>(Arrays.asList(each.split("")));
            String sortedLetter = String.join("", insertionSort(stringList));

            if (!map.containsKey(sortedLetter)) {
                map.put(sortedLetter, new ArrayList<String>());
            }

            map.get(sortedLetter).add(each);
        }

        String result = "";
        for (ArrayList<String> each : map.values()) {
            result += String.join(" ", each) + "\r\n";
        }

        // System.out.println(list);
        // System.out.println(result);
        writeStringToFile(result, "example_4_out.txt");
    }

    /**
     * @reference Read Files: https://www.w3schools.com/java/java_files_read.asp
     */
    public static ArrayList<String> readListFromFile(String fileName) {
        final ArrayList<String> list = new ArrayList<String>();

        try {
            final File myObj = new File("words/" + fileName);
            final Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                final String data = myReader.nextLine();
                list.add(data);
            }
            myReader.close();
        } catch (final IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return list;
    }

    /**
     * @reference Write Files: https://www.w3schools.com/java/java_files_create.asp
     */
    public static void writeStringToFile(String string, String filename) {
        try {
            FileWriter myWriter = new FileWriter("words/" + filename);
            myWriter.write(string);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static ArrayList<String> insertionSort(ArrayList<String> list) {
        ArrayList<String> sortedList = new ArrayList<String>();

        for (int t = 0; t < list.size(); t++) {
            int sortedIndex = sortedList.size();
            int insertIndex = 0;

            while (sortedIndex > 0) {
                sortedIndex--;
                int compare = list.get(t).compareToIgnoreCase(sortedList.get(sortedIndex));

                if (compare > 0) {
                    insertIndex = sortedIndex + 1;
                    break;
                }
            }

            sortedList.add(insertIndex, list.get(t));
        }

        return sortedList;
    }

}