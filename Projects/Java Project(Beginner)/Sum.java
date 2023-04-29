import java.util.*;

public class Sum {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        Calculate c = new Adder();
        System.out.println(c.add(a, b));

    }
}

abstract class Calculate {
    abstract int add(int a, int b);
}

class Adder extends Calculate {
    int add(int a, int b) {
        return a + b;
    }
}