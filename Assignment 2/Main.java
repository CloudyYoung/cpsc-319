import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args){
        ArrayList<String> list = readListFromFile("text1.txt");
        BinaryTree binaryTree = new BinaryTree(list);
        System.out.println(binaryTree);
        System.out.println(binaryTree.getTotalNodes());
        System.out.println(binaryTree.getUniqueNodes());
        System.out.println(binaryTree.getMostFrequentNodes());
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
                while(res.find()){
                    list.add(res.group().toLowerCase());
                }
            }
            scanner.close();
        } catch (final IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return list;
    }

}