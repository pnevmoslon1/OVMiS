public class Converter {
    int baseIn = 2;
    int baseOut = 2;
    String str = new String();
    public String signMagnitude = new String();

    Converter(int in, int out, String n) {
        baseIn = in;
        baseOut = out;
        str = n;
        convertFromTo();
    }

    private boolean check(Character c) {

        if (Character.isAlphabetic(c) && (int) c - (int) 'A' + 10 < baseIn && c >= 'A') {
            return true;
        }
        if (Character.isDigit(c) && (int) c - (int) '0' <= baseIn || (int) c - (int) '0' < 2) {
            return true;
        }
        return false;
    }


    public String convertFromTo() {

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) == '-' && i == 0) continue;
            if (!check(str.charAt(i)) || baseOut < 2 || baseIn < 2 || baseOut > 16 || baseIn > 16) {
                return "неправильный ввод";
            }
        }

        return convertToOut(convertTo10(str));
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


    public static String convertToSignMagnitude(String binary) {
        boolean isNegative = binary.startsWith("-");
        if (isNegative) {
            binary = binary.substring(1);
        }


        String signBit = isNegative ? "1" : "0";

        int nextPowerOfTwo = 1;

        while(nextPowerOfTwo < binary.length() + 1){
            nextPowerOfTwo*=2;
        }

        while (binary.length() < nextPowerOfTwo - 1) {
            binary = "0" + binary;
        }
        binary = signBit + binary;

        return binary;
    }

    public String formatWithSpaces(String binary) {
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < binary.length(); i++) {
            if (i > 0 && i % 4 == 0) {
                formatted.append(" ");
            }
            formatted.append(binary.charAt(i));
        }
        return formatted.toString();
    }

    String convertToOut(int n) {
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
                res = res + (char) (n % baseOut - 10 + 'A');
            }
            n /= baseOut;
        }

        if (n < 10) res = res + n + m;
        else res = res + (char) (n - 10 + 'A') + m;
        res = new StringBuilder(res).reverse().toString();

        if (baseOut == 2) {
            signMagnitude = convertToSignMagnitude(res);
            res += " ПК " + formatWithSpaces(signMagnitude);
        }
        return res;
    }

}
