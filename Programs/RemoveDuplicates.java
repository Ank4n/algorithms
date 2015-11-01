import java.util.Arrays;

public class RemoveDuplicates {

    public static int[] removeDuplicates(int[] input) {

        int i = 0;
        int j = 1;

        while (j < input.length) {

            if (input[i] == input[j])
                j++;

            else
                input[++i] = input[j++];

        }

        return Arrays.copyOf(input, i + 1);
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[] noDup = { 1, 3, 4, 6, 7, 9, 10 };
        noDup = removeDuplicates(noDup);
        printArray(noDup);

        int[] dup = { 1, 1, 3, 3, 3, 4, 4, 4, 6, 6, 7, 7, 9, 10, 10, 10, 10};
        dup = removeDuplicates(dup);
        printArray(dup);
    }

}
