import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private WeightedQuickUnionUF grid;
	private boolean[][] sites;
	private int N;
	private boolean[] status;

	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {

		if (N <= 0)
			throw new IllegalArgumentException("N cannot be less than zero");

		this.N = N;

		grid = new WeightedQuickUnionUF(N * N);
		sites = new boolean[N][N];
		status = new boolean[N * N];

		for (int v = 2; v <= N; ++v)
			grid.union(0, convertIndex(1, v));

	}

	private void validateIndex(int i, int j) {

		if (i > N || i < 1 || j > N || j < 1)
			throw new IndexOutOfBoundsException();
	}

	private int convertIndex(int x, int y) {

		return (N * (x - 1) + (y - 1));
	}

	public void open(int i, int j) {

		this.validateIndex(i, j);

		boolean connectedToBottom;
		int p = convertIndex(i, j);

		if (!this.sites[i - 1][j - 1]) {

			this.sites[i - 1][j - 1] = true;
			connectedToBottom = this.connectOpenSites(i, j);

			if (connectedToBottom || i == N)
				status[this.grid.find(p)] = true;

		}
	}

	private boolean connectOpenSites(int i, int j) {

		int p = convertIndex(i, j);
		boolean connectedToBottom = false;

		if ((i + 1) <= N) {

			if (this.sites[i][j - 1]) {

				if (((i + 1) == N))
					connectedToBottom = true;

				if (!connectedToBottom)
					connectedToBottom = status[grid.find(p + N)];

				this.grid.union(p, p + N);

			}
		}

		if ((i - 1) > 0)

			if (this.sites[i - 2][j - 1]) {

				if (!connectedToBottom)
					connectedToBottom = status[grid.find(p - N)];

				this.grid.union(p, p - N);

			}

		if ((j + 1) <= N)

			if (this.sites[i - 1][j]) {

				if (!connectedToBottom)
					connectedToBottom = status[grid.find(p + 1)];

				this.grid.union(p, p + 1);

			}

		if ((j - 1) > 0)

			if (this.sites[i - 1][j - 2]) {

				if (!connectedToBottom)
					connectedToBottom = status[grid.find(p - 1)];

				this.grid.union(p, p - 1);

			}

		return connectedToBottom;

	}

	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {

		this.validateIndex(i, j);

		return this.sites[i - 1][j - 1];
	}

	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {

		this.validateIndex(i, j);

		if (sites[i - 1][j - 1])
			return this.grid.connected(convertIndex(i, j), 0);

		return false;
	}

	// does the system percolate?
	public boolean percolates() {
		return status[grid.find(0)];
	}

	// test client
    public static void main(String[] args) {

	}

}
