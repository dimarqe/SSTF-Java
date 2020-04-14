import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		try {
			Scanner input = new Scanner(System.in);

			int traversalCount = 0, numCylinder, startingCylinder;

			System.out.println("Enter the number of cylinders on the disk");
			numCylinder = input.nextInt();
			System.out.println("Enter the initial cylinder");
			startingCylinder = input.nextInt();

			System.out.printf("Lower cylinder: 0\nUpper cylinder: %d\nInitial Cylinder: %d\n\n", numCylinder - 1,
					startingCylinder);

			// loads in requests to list array
			ArrayList<Integer> requests = loadFile(numCylinder);

			System.out.printf("Cylinder Requests:\n");
			System.out.println(requests.toString() + "\n");

			SSTF sstf = new SSTF(requests);

			int[] processed = sstf.getSSTF(startingCylinder);

			traversalCount += Math.abs(startingCylinder - processed[0]);
			for (int i = 0; i < processed.length - 1; i++) {
				traversalCount += Math.abs(processed[i] - processed[i + 1]);
			}
			System.out.println("\n" + "SSTF traversal count = " + traversalCount);

			input.close();
		} catch (IOException e) {
			System.out.println("Error encountered while handling file");
			// e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Unexpected error encountered during runtime");
			// e.printStackTrace();
		}
	}

	public static ArrayList<Integer> loadFile(int limit) throws IOException {
		ArrayList<Integer> cylinderRequests = new ArrayList<Integer>();
		String fileName = "cylinderRequests.txt";
		File file = new File(fileName);

		while (true) {
			// check if file exists before reading from it
			if (file.exists()) {
				Scanner fileReader = new Scanner(new FileReader(file));

				while (fileReader.hasNext()) {
					int request = fileReader.nextInt();
					if (request < limit)
						cylinderRequests.add(request);
				}
				fileReader.close();

				break;
			}
			// creates request file if its not found
			else {
				createFile();
			}
		}

		return cylinderRequests;
	}

	// Populates file with 25 random cylinder requests in 0-500 range
	public static void createFile() throws IOException {
		FileWriter fileWriter = new FileWriter("cylinderRequests.txt");
		Random rand = new Random();

		for (int i = 0; i < 25; i++) {
			fileWriter.write(rand.nextInt(501) + "\n");
		}

		fileWriter.close();
	}
}
