import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

	private double[] threshold;
	private double meanValue;
	private double stddevValue;
	private double confidenceLow;
	private double confidenceHigh;

	// perform T independent experiments on an N-by-N grid
	public PercolationStats(int N, int T) {

		if (N <= 0 || T <= 0)
			throw new IllegalArgumentException();

		this.threshold = new double[T];

		for (int i = 0; i < T; i++) {

			Percolation perc = new Percolation(N);
			int openSites = 0;

			while (!perc.percolates()) {

				int x = StdRandom.uniform(N) + 1;
				int y = StdRandom.uniform(N) + 1;

				if (!perc.isOpen(x, y)) {

					perc.open(x, y);
					openSites++;
				}
			}

			this.threshold[i] = ((double) openSites / (N * N));
		}

		this.meanValue = StdStats.mean(threshold);
		this.stddevValue = StdStats.stddev(threshold);
		this.confidenceLow = this.meanValue - ((1.96 * this.stddevValue) / Math.sqrt(threshold.length));
		this.confidenceHigh = this.meanValue + ((1.96 * this.stddevValue) / Math.sqrt(threshold.length));
	}

	// sample mean of percolation threshold
	public double mean() {
		return this.meanValue;
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return this.stddevValue;
	}

	// low endpoint of 95% confidence interval
	public double confidenceLo() { 
		return this.confidenceLow;
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() { 
		return this.confidenceHigh;
	}

	// test client
	public static void main(String[] args) { 

		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);

		Stopwatch watch = new Stopwatch();
		PercolationStats percStats = new PercolationStats(N, T);
		StdOut.println("Running Time = " + watch.elapsedTime() + " seconds");
		StdOut.println("mean = " + percStats.mean());
		StdOut.println("stddev = " + percStats.stddev());
		StdOut.println("95% confidence interval = " + percStats.confidenceLo() + ", " + percStats.confidenceHi());

	}

}