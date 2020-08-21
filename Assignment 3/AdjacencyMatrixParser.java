import java.util.ArrayList;

public class AdjacencyMatrixParser {
    
    public static int[][] parseFile(String fileName){

        ArrayList<String> lines = FileIO.readFile(fileName + ".txt");

        int[][] list = new int[lines.size()][];
        int index = 0;

        for(String line : lines){
            line = line.trim().replaceAll(" +", " ");

            if (line.startsWith("#")){ // Comments
                continue;
            }

            String[] numbers = line.split(" ");
            int[] nums = new int[numbers.length];
            int index2 = 0;
            for (String number : numbers){
                number = number.trim();

                if(number.isEmpty()){
                    continue;
                }
                
                nums[index2] = Integer.valueOf(number);
                index2 ++;
            }

            list[index] = nums;
            index ++;
        }

        return list;
    }

    public static void writeFile(String fileName, String content){
        FileIO.writeFile(fileName + "_dijkstra_output.txt", content);
    }
}