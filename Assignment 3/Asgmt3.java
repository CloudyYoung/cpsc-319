
import java.util.Scanner;

class Asgmt3 {
	public static void main(String[] args) {
		System.out.println("Enter file name: ");

		Scanner scan = new Scanner(System.in);
		String inputFileName = scan.nextLine();

		int[][] matrix = AdjacencyMatrixParser.parseFile(inputFileName); // inputFileName should be provided by the user (refer to the asgmt specs)
		
		String ret = DijkstrasAlgorithm.dijkstra(matrix, 0);

		AdjacencyMatrixParser.writeFile(inputFileName, ret);
		scan.close();
	}

}