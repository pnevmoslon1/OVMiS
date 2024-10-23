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

        System.out.println(a + "\n" + b);
        sum(a, b);
    }

    private String addZero(int count, String str) {
        boolean isNegative = str.startsWith("1");
        if (isNegative) {
            str = str.substring(1);
        }
        String signBit = isNegative ? "1" : "0";
        for (int i = 0; i < count; i++) {
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

        int remainder = 0;
        String res = "";

        System.out.println("\n" + a + "\n" + b);
        return "";
    }
}
