import java.util.Arrays;

public class MinimumCoins {

    public static void minCoins(int[] availableCoins, int total) {

        int minCoins = 0;
        Arrays.sort(availableCoins);
        int deficit = total;

        for (int i = availableCoins.length - 1; i >= 0; i--) {

            minCoins += deficit / availableCoins[i];
            deficit %= availableCoins[i];

            if (deficit == 0)
                break;

        }

        if (deficit != 0)
            minCoins = 0;

        System.out.println(minCoins != 0 ? minCoins : "Solution not possible");
    }

    public static void main(String[] args) {

        int[] coins1 = { 1, 3, 5 };
        minCoins(coins1, 11); // 5*2 + 1*1
        minCoins(coins1, 74); // 5*14 + 3*1 + 1*1
        
        int[] coins2 = { 3, 5 };
        minCoins(coins2, 11); // Solution not possible
        minCoins(coins2, 15); // 5*3
        
        

    }

}
