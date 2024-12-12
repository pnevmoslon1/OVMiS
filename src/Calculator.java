public class Calculator {

    public static String calculate(int baseNumberSystem, String number1, String number2, char operation) {
        // Преобразуем числа в двоичную систему с помощью вашего класса Converter
        String binary1 = new Converter(baseNumberSystem, 2, number1).convertFromTo().split(" ")[0];
        String binary2 = new Converter(baseNumberSystem, 2, number2).convertFromTo().split(" ")[0];

        String result;
        switch (operation) {
            case '-':
                result = minus(binary1, binary2);
                break;
            case '+':
                result = plus(binary1, binary2);
                break;
            case '*':
                result = multiply(binary1, binary2);
                break;
            case '/':
                result = divideBinary(binary1, binary2).first;
                break;
            default:
                throw new IllegalArgumentException("Введена неверная операция");
        }

        try {
            // Преобразуем результат обратно в исходную систему счисления
            String resultInBase = new Converter(2, baseNumberSystem, result).convertFromTo();
            String signMagnitude = Converter.convertToSignMagnitude(result);

            String strResult = "Двоичная СС: " +
                    result + " " + Converter.convertToSignMagnitude(result) +
                    "\nИсходная СС (" + baseNumberSystem + "): " +
                    resultInBase;
            return strResult;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static String plus(String binaryNumber1, String binaryNumber2) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = binaryNumber1.length() - 1, j = binaryNumber2.length() - 1;

        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry +
                    (i >= 0 ? binaryNumber1.charAt(i--) - '0' : 0) +
                    (j >= 0 ? binaryNumber2.charAt(j--) - '0' : 0);
            result.append(sum % 2);
            carry = sum / 2;
        }

        return result.reverse().toString();
    }

    public static String minus(String binaryNumber1, String binaryNumber2) {
        StringBuilder result = new StringBuilder();
        int borrow = 0;
        int i = binaryNumber1.length() - 1, j = binaryNumber2.length() - 1;

        while (i >= 0 || j >= 0) {
            int sub = (i >= 0 ? binaryNumber1.charAt(i--) - '0' : 0) -
                    (j >= 0 ? binaryNumber2.charAt(j--) - '0' : 0) - borrow;
            if (sub < 0) {
                sub += 2;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result.append(sub);
        }

        while (result.length() > 1 && result.charAt(result.length() - 1) == '0') {
            result.deleteCharAt(result.length() - 1);
        }

        return result.length() == 0 ? "0" : result.reverse().toString();
    }

    public static String multiply(String binaryNumber1, String binaryNumber2) {
        String result = "0";

        for (int i = binaryNumber2.length() - 1; i >= 0; --i) {
            if (binaryNumber2.charAt(i) == '1') {
                StringBuilder temp = new StringBuilder(binaryNumber1);
                for (int j = 0; j < binaryNumber2.length() - i - 1; j++) {
                    temp.append('0');
                }
                result = plus(result, temp.toString());
            }
        }

        return result;
    }

    public static Pair<String, String> divideBinary(String dividend, String divisor) {
        if (divisor.equals("0")) {
            throw new IllegalArgumentException("Деление на ноль невозможно");
        }

        StringBuilder quotient = new StringBuilder();
        StringBuilder remainder = new StringBuilder(dividend);

        while (remainder.length() >= divisor.length()) {
            if (isGreaterOrEqual(remainder.toString(), divisor)) {
                quotient.append('1');
                remainder = new StringBuilder(subtractBinary(remainder.toString(), divisor));
            } else {
                quotient.append('0');
            }
        }

        while (quotient.length() > 1 && quotient.charAt(0) == '0') {
            quotient.deleteCharAt(0);
        }

        return new Pair<>(quotient.toString(), remainder.toString());
    }

    public static String padToLength(String binary, int length) {
        StringBuilder padded = new StringBuilder(binary);
        while (padded.length() < length) {
            padded.insert(0, '0');
        }
        return padded.toString();
    }

    public static String invertBits(String binary) {
        StringBuilder result = new StringBuilder();
        for (char bit : binary.toCharArray()) {
            result.append(bit == '0' ? '1' : '0');
        }
        return result.toString();
    }

    public static String toTwosComplement(String binary) {
        String inverted = invertBits(binary);
        int carry = 1;
        StringBuilder result = new StringBuilder(inverted);

        for (int i = inverted.length() - 1; i >= 0; --i) {
            int bit = (inverted.charAt(i) - '0') + carry;
            result.setCharAt(i, (char) ((bit % 2) + '0'));
            carry = bit / 2;
        }

        return result.toString();
    }

    public static boolean isGreaterOrEqual(String a, String b) {
        String aPadded = padToLength(a, b.length());
        String bPadded = padToLength(b, a.length());
        return aPadded.compareTo(bPadded) >= 0;
    }

    public static String subtractBinary(String a, String b) {
        int maxLength = Math.max(a.length(), b.length());

        String aPadded = padToLength(a, maxLength);
        String bPadded = padToLength(b, maxLength);

        String bTwosComplement = toTwosComplement(bPadded);

        String sum = plus(aPadded, bTwosComplement);

        if (sum.length() > maxLength) {
            sum = sum.substring(1);
        }

        return sum;
    }

    public static void main(String[] args) {
        String result = calculate(10, "10", "5", '-');
        System.out.println(result);
    }
}

class Pair<T, U> {
    public T first;
    public U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
}