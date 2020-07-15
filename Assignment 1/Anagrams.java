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

        // The input and output file names
        String[] inputs = {"example_1--8_words.txt", "example_2--13_words.txt", "example_3--19_words.txt", "example_4--267_words.txt"};
        String[] outputs = {"example_1_out.txt", "example_2_out.txt", "example_3_out.txt", "example_4_out.txt"};

        int index = 0;
        for(String file : inputs){
            ArrayList<String> list = readListFromFile(file);
            Map<String, ArrayList<String>> map = anagrams(list);
    
            String result = "";
            for (ArrayList<String> each : map.values()) {
                result += String.join(" ", each) + "\r\n";
            }
            writeStringToFile(result, outputs[index]);

            index ++;
        }
    }

    public static Map<String, ArrayList<String>> anagrams(ArrayList<String> list) {
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

        return map;
    }

    /**
     * @reference Read Files: https://www.w3schools.com/java/java_files_read.asp
     */
    public static ArrayList<String> readListFromFile(String fileName) {
        final ArrayList<String> list = new ArrayList<String>();

        try {
            final File myObj = new File(fileName);
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
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(string);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * @reference: The introducion of insertion sort from powerpoint "5 - Searching & SORTING - (3) Insertion"
     * @param list
     * @return
     */
    public static ArrayList<String> insertionSort(ArrayList<String> list) {
        
        int sortedIndex = 0;
        for (int t = 0; t < list.size(); t++) {
            
            
            sortedIndex ++;
        }

        return list;
    }

}