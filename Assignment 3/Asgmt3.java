
import java.util.Scanner;

class Asgmt3 {
	public static void main(String[] args) {
		System.out.println("Enter file name: "); // Ask for user input

		Scanner scan = new Scanner(System.in);
		String inputFileName = scan.nextLine(); // Get input

		int[][] matrix = AdjacencyMatrixParser.parseFile(inputFileName); // inputFileName should be provided by the user (refer to the asgmt specs)
		
		String ret = DijkstrasAlgorithm.dijkstra(matrix, 0); // Run algorithm and get return string

		AdjacencyMatrixParser.writeFile(inputFileName, ret); // Write to file
		scan.close();
	}

}