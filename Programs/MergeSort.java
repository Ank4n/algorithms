
public class MergeSort {

    public static void sort(int[] a) {
        int[] aux = new int[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(int[] a, int[] aux, int lo, int hi) {

        if (hi <= lo)
            return;

        int mid = lo + (hi - lo) / 2;

        // sort left-half
        sort(a, aux, lo, mid);

        // sort right-half
        sort(a, aux, mid + 1, hi);

        // merge
        merge(a, aux, lo, mid, hi);

    }

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {

        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        for (int k = lo; k <= hi; k++) {

            // all items on left side are exhausted
            if (i > mid)
                a[k] = aux[j++];

            // all items on right side are exhausted
            else if (j > hi)
                a[k] = aux[i++];

            else if (aux[i] > aux[j])
                a[k] = aux[j++];

            else
                a[k] = aux[i++];
        }
    }

    public static void main(String[] args) {

        int[] input = { 4, 8, 7, 9, 0, 3 };
        sort(input);
        for (int i = 0; i < input.length; i++)
            System.out.print(input[i] + " ");
    }
}
