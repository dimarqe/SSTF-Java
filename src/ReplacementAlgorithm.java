
public class ReplacementAlgorithm {
	protected int frames, pointer, numFault, referenceLength, referenceString[];
	protected float faultRate;
	
	protected boolean isFull = false;

	public ReplacementAlgorithm() {
		super();
		this.frames = 0;
		this.pointer = 0;
		this.numFault = 0;
		this.faultRate = 0.0f;
		this.referenceLength = 0;
		this.referenceString = null;
		this.isFull = false;
	}

	public void setFrames(int frames) {
		this.frames = frames;
	}

	public void setReferenceLength(int lengthOfReference) {
		this.referenceLength = lengthOfReference;
	}

	public void setReferenceString(int referenceString[]) {
		this.referenceString = new int[referenceLength];
		for (int i = 0; i < referenceLength; i++) {
			this.referenceString[i] = referenceString[i];
		}
	}
	
	public float calculatePageFaults() {
		//method overridden in child classes
		return 0;
	}
}
