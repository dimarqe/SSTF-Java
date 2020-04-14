public class OptimalPageReplacement extends ReplacementAlgorithm {

	private int buffer[], fault[], mem_layout[][];
	private boolean hit[];
	
	public OptimalPageReplacement() {
		super();
		this.buffer = null;
		this.fault = null;
		this.mem_layout = null;
		this.hit = null;
	}


	@Override
	public float calculatePageFaults() {
		System.out.println("Optimal Page Replacement Algorithm\n");
		mem_layout = new int[referenceLength][frames];
		buffer = new int[frames];
		hit = new boolean[referenceLength];
		fault = new int[referenceLength];
		for (int j = 0; j < frames; j++) {
			buffer[j] = -1;
		}

		for (int i = 0; i < referenceLength; i++) {
			int search = -1;
			for (int j = 0; j < frames; j++) {
				if (buffer[j] == referenceString[i]) {
					search = j;
					hit[i] = true;
					fault[i] = numFault;
					break;
				}
			}

			if (search == -1) {
				if (isFull) {
					int index[] = new int[frames];
					boolean index_flag[] = new boolean[frames];
					for (int j = i + 1; j < referenceLength; j++) {
						for (int k = 0; k < frames; k++) {
							if ((referenceString[j] == buffer[k]) && (index_flag[k] == false)) {
								index[k] = j;
								index_flag[k] = true;
								break;
							}
						}
					}
					int max = index[0];
					pointer = 0;
					if (max == 0) {
						max = 200;
					}

					for (int j = 0; j < frames; j++) {
						if (index[j] == 0) {
							index[j] = 200;
						}

						if (index[j] > max) {
							max = index[j];
							pointer = j;
						}
					}
				}
				buffer[pointer] = referenceString[i];
				numFault++;
				fault[i] = numFault;
				if (!isFull) {
					pointer++;
					if (pointer == frames) {
						pointer = 0;
						isFull = true;
					}
				}
			}

			for (int j = 0; j < frames; j++) {
				mem_layout[i][j] = buffer[j];
			}
		}

		for (int i = 0; i < referenceLength; i++) {
			System.out.print(referenceString[i] + "\t| Memory is|\t");
			for (int j = 0; j < frames; j++) {
				if (mem_layout[i][j] == -1) {
					System.out.printf("%3s ", "*");
				} else {
					System.out.printf("%3d ", mem_layout[i][j]);
				}
			}
			if (hit[i]) {
				System.out.print("\tHit\n");
			} else {
				System.out.print("\tPage Fault\n");
			}
		}
		System.out.println("\n(Number of Page Faults: " + numFault + ")");
		
		faultRate = (float)numFault/(float)referenceLength;
		System.out.printf("(Page Fault Rate: %.2f", faultRate);
		
		return faultRate;
	}
}
