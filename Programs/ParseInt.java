
public class ParseInt {

    public static int parseInt(String number) {

        int val = 0;
        char[] num = number.toCharArray();
        int zero = (int) '0'; //48
        
        for (int i = 0; i < num.length; i++)
            val = val * 10 + (int) num[i] - zero;

        return val;
    }

    public static void main(String[] args) {

        System.out.println(parseInt("2356"));
        System.out.println((int) '0');
       
    }

}
