import java.util.Scanner;

public class Converter {
    int baseIn = 10;
    int baseOut = 16;
    String str = new String();
    int[] arr;


    private boolean check(Character c) {

        if (Character.isAlphabetic(c) && (int) c - (int) 'A' < baseIn || c >= 'A' && c <= 'F') {
            return true;
        }
        if (Character.isDigit(c) && (int) c - (int) '0' <= baseOut || (int) c - (int) '0' < 2) {
            return true;
        }
        return false;
    }


    public void insert() {
        Scanner sc = new Scanner(System.in);
        baseIn = sc.nextInt();
        baseOut = sc.nextInt();
        str = sc.next();

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) == '-' && i == 0) continue;
            if (!check(str.charAt(i)) || baseOut < 2 || baseIn < 2 || baseOut > 15 || baseIn > 15) {
                System.out.println("неправильный ввод");
                return;
            }
        }
        arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (Character.isAlphabetic(str.charAt(i))) {
                arr[i] = (int) str.charAt(i) - 'A';
            }
        }
    }


    int convertTo10(String n) {
        Integer res = 0;
        int a;
        for (int i = 1; i < n.length(); i++) {

            if (Character.isDigit(n.charAt(i))) res += (n.charAt(i) - '0') * (int) Math.pow(baseIn, n.length() - 1 - i);
            else res += ((n.charAt(i) - 'A') + 10) * (int) Math.pow(baseIn, n.length() - 1 - i);

        }
        if (n.charAt(0) == '-') res *= -1;
        else if (Character.isDigit(n.charAt(0))) res += (n.charAt(0) - '0') * (int) Math.pow(baseIn, n.length() - 1);
        else if (Character.isAlphabetic(n.charAt(0)))
            res += ((n.charAt(0) - 'A') + 10) * (int) Math.pow(baseIn, n.length() - 1);
        return res;
    }

    public String convertToOut(int n) {
        String res = "";
        String m = "";
        if (n < 0) {
            n *= -1;
            m = "-";
        }
        while (n >= baseOut) {
            if (n % baseOut < 10) {
                res = res + n % baseOut;
            } else {
                res = res + (char)(n % baseOut - 10 + 'A');
            }
            n /= baseOut;
        }

        if (n < 10) res = res + n + m;
        else res = res + (char)(n - 10 + 'A') + m;
        res = new StringBuilder(res).reverse().toString();

        return res;
    }

}
