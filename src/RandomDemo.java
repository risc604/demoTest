
import java.util.Random;

public class RandomDemo {
    public RandomDemo() {
	// TODO Auto-generated constructor stub
	final double MIN = 32.0;
	final double MAX = 43.0;
	final int ARRAY_SZ = 1000;
	double[] dataObjects = new double[ARRAY_SZ];
	Random rand = new Random();

	// for (int i=0; i<dataObjects.length; i++)
	int i = 0;
	while (i < ARRAY_SZ) {
	    // dataObjects[i] = rand.nextGaussian();
	    double tmpX = rand.nextDouble() * 100;

	    if ((Double.compare(tmpX, MIN) > 0) && (Double.compare(tmpX, MAX) < 1)) {
		dataObjects[i++] = tmpX;
	    }
	}

	System.out.println("");
	for (int j = 0; j < dataObjects.length; j++) {
	    if ((j > 0) && (j % 10 == 0))
		System.out.println("");
	    System.out.printf("[%03d]:%4.2f   ", j, dataObjects[j]);
	}

	System.out.println("");
    }

}
