
public class Calculator {
    String a = new String();
    String b = new String();
    int base;

    Calculator(int base, String f, String s) {
        this.base = base;
        Converter c = new Converter(base, 2, f);
        a = c.signMagnitude;
        c = new Converter(base, 2, s);
        b = c.signMagnitude;

        if (a.length() > b.length()) b = addZero(a.length() - b.length(), b);
        else a = addZero(b.length() - a.length(), a);

        sum(a, b);
    }

    private String addZero(int count, String str) {
        boolean isNegative = str.startsWith("1");
        if (isNegative) {
            str = str.substring(1);
        }
        String signBit = isNegative ? "1" : "0";
        for (int i = 1; i < count; i++) {
            str = "0" + str;
        }
        str = signBit + str;
        return str;
    }

    private String reverseCode(String str) {
        String res = "";
        str = str.substring(1);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') res = res + "0";
            else res = res + '1';
        }
        return "1" + res;


    }

    private String sum(String a, String b) {
        if (a.startsWith("1")) a = reverseCode(a);
        if (b.startsWith("1")) b = reverseCode(b);

        String digitA;

        int remainder = 0;
        int sa;
        String res = "";
        for (int i = a.length() - 1; i >= 0; i--) {
            sa = a.charAt(i) - '0' + b.charAt(i) - '0' + remainder;
            res = sa % 2 + res;
            remainder /= 2;
        }
        if (remainder > 0) res = remainder + res;
        System.out.println(a + "\n" + b + "\n" + res);
        return res;
    }

}
