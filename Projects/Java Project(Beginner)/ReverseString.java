import java.util.*;

public class ReverseString {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String word = scan.nextLine();
        System.out.println(ReversingString(word));
    }

    static String ReversingString(String a) {
        String res = "";
        char[] word = a.toCharArray();
        for (int i = 0; i < a.length(); i++) {
            res = word[i] + res;
        }
        return res;

    }
}
