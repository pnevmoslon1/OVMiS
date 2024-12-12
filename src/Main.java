import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        String result = Calculator.calculate(10, "50", "45", '-');
//        System.out.println(result);


        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Исходная СС, числа, операция");
            System.out.println(Calculator.calculate(scanner.nextInt(), scanner.next(), scanner.next(), scanner.next().charAt(0)) + "\n\n");
        }
    }
}