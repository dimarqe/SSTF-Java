import java.util.ArrayList;

public class LeastRecentlyUsed extends ReplacementAlgorithm{
	private int mem[];
	private ArrayList<Integer> stack = new ArrayList<Integer>();

	public LeastRecentlyUsed() {
		super();
		this.mem = null;
	}
	
	@Override
	public float calculatePageFaults() {
		System.out.println("Least Recently Used Algorithm\n");
		mem = new int[frames];
		for (int j = 0; j < frames; j++)
			mem[j] = -1;

		System.out.println();
		for (int i = 0; i < referenceLength; i++) {
			if (stack.contains(referenceString[i])) {
				stack.remove(stack.indexOf(referenceString[i]));
			}
			stack.add(referenceString[i]);
			int search = -1;
			for (int j = 0; j < frames; j++) {
				if (mem[j] == referenceString[i]) {

					search = j;

					System.out.print(referenceString[i] + "\t| Memory is|\t");
					for (int w = 0; w < frames; w++) {
						if (mem[w] == -1) {
							System.out.printf("%3c ", '*');
						} else {
							System.out.printf("%3d ", mem[w]);
						}
					}
					System.out.println("\tHit");
					break;
				}
			}
			if (search == -1) {
				if (isFull) {
					int min_loc = referenceLength;
					for (int j = 0; j < frames; j++) {
						if (stack.contains(mem[j])) {
							int temp = stack.indexOf(mem[j]);
							if (temp < min_loc) {

								min_loc = temp;
								pointer = j;
							}
						}
					}
				}
				mem[pointer] = referenceString[i];
				numFault++;
				System.out.print(referenceString[i] + "\t| Memory is|\t");
				for (int w = 0; w < frames; w++) {
					if (mem[w] == -1) {
						System.out.printf("%3c ", '*');
					} else {
						System.out.printf("%3d ", mem[w]);
					}
				}
				System.out.println("\tPage Fault");
				pointer++;
				if (pointer == frames) {
					pointer = 0;
					isFull = true;
				}
			}
		}
		
		System.out.println("\n(Number of Page Faults: " + numFault + ")");
		
		faultRate = (float)numFault/(float)referenceLength;
		System.out.printf("(Page Fault Rate: %.2f", faultRate);
		
		return faultRate;
	}
}