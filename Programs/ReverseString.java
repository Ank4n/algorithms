
public class ReverseString {

    public static String reverseString(String string){
        
        if (string.length() == 1)
            return string;
        
        return string.charAt(string.length()-1) + reverseString(string.substring(0, string.length()-1)); 
    }

    public static void main(String[] args) {
       
        System.out.println(reverseString("India"));
        System.out.println(reverseString("987654321"));
        
    }

}
