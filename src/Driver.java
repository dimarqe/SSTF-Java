import java.util.Random;
import java.util.Scanner;

public class Driver {
	static int lengthOfReference = 0, numOfFrames = 0, referenceString[];
	static Scanner input = new Scanner(System.in);

	public static void main(String args[]) {
		try {
			OptimalPageReplacement opt = new OptimalPageReplacement();
			LeastRecentlyUsed lru = new LeastRecentlyUsed();

			String algorithm = menu();
			System.out.println("You have selected the "+algorithm);

			setLengthOfReference();
			setReferenceString();

			System.out.println("\n*************************************************************************");
			System.out.println("PROCESS 1");
			System.out.println("\nREFERENCE STRING 1:");

			for (int i = 0; i < lengthOfReference; i++)
				System.out.printf("%d, ", referenceString[i]);

			System.out.println("\n---------------------------------------------------\n");

			numOfFrames = 5;

			float optFault = calculateFaults(opt);
			float lruFault = calculateFaults(lru);
			System.out.println("*************************************************************************");

			// Second reference string

			opt = new OptimalPageReplacement();
			lru = new LeastRecentlyUsed();

			setLengthOfReference();
			setReferenceString();

			System.out.println("PROCESS 2");
			System.out.println("\nREFERENCE STRING 2:");

			for (int i = 0; i < lengthOfReference; i++)
				System.out.printf("%d, ", referenceString[i]);

			System.out.println("\n---------------------------------------------------\n");

			numOfFrames = 3;

			optFault += calculateFaults(opt);
			lruFault += calculateFaults(lru);
			System.out.println("*************************************************************************");

			System.out.println("\n\nAverage Page Fault Rate\n" + "________________________\n");
			System.out.printf("Optimal Page Replacement Algorithm:\t%f\n", optFault/2);
			System.out.printf("Least Recently Used Algorithm:\t\t%f\n\n", lruFault/2);
			
			//Better performing algorithm results 
			System.out.println("**********************");
			System.out.println("**BETTER PERFORMANCE**");
			System.out.println("**********************");
			if(optFault > lruFault)
				System.out.println("Least Recently Used Algorithm");
			
			else if(optFault < lruFault)
				System.out.println("Optimal Page Replacement Algorithm");
			
			else
				System.out.println("Both are equal");
		
		} catch (Exception e) {
			System.out.println("\n!!!Unexpected exception encountered during runtime!!!");
			e.printStackTrace();
		}
	}

	public static float calculateFaults(ReplacementAlgorithm algorithm) {
		algorithm.setFrames(numOfFrames);
		algorithm.setReferenceLength(lengthOfReference);
		algorithm.setReferenceString(referenceString);

		float faultRate = algorithm.calculatePageFaults();
		System.out.println("\n");

		return faultRate;
	}

	public static String menu() {
		System.out.println("Select an algorithm by entering its corresponding number\n" + "1.Optimal Page Replacement\n"
				+ "2.Least Recently Used\n" + "3.Exit");

		int choice = input.nextInt();

		switch (choice) {
		case 1:
			return "Optimal Page Replacement Algorithm";

		case 2:
			return "Least Recently Used Algorithm";

		default:
			System.out.println("\nGoodbye (-_-)");
			System.exit(0);
			return "";
		}
	}

	public static void setLengthOfReference() {
		Random random = new Random();

		lengthOfReference = random.nextInt(15) + 1;
	}

	public static void setReferenceString() {
		referenceString = new int[lengthOfReference];

		Random random = new Random();

		for (int i = 0; i < lengthOfReference; i++) {
			referenceString[i] = random.nextInt(100);
		}
	}
}
