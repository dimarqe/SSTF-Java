import java.util.ArrayList;

public class SSTF {
	ArrayList<Integer> requestArr;
	int nearest;

	public SSTF(ArrayList<Integer> cylinderRequests) {
		super();
		this.requestArr = cylinderRequests;
		this.nearest = 0;
	}

	public int findClosest(int desiredNumber) {
		int bestDistanceFoundYet = 10000, d, closestRequest;

		for (int i = 0; i < requestArr.size(); i++) {
			d = Math.abs(desiredNumber - requestArr.get(i));
			if (d < bestDistanceFoundYet) {
				bestDistanceFoundYet = d;
				nearest = i;
			}
		}
		closestRequest = requestArr.get(nearest);
		requestArr.remove(nearest);

		return closestRequest;
	}

	public int[] getSSTF(int startingCylinder) {
		int length = requestArr.size();
		int[] sstf = new int[length];

		int desiredNumber = startingCylinder;

		for (int i = 0; i < length; i++) {
			sstf[i] = findClosest(desiredNumber);

			System.out.println("Servicing " + sstf[i]);
			desiredNumber = sstf[i];
		}
		return sstf;
	}
}