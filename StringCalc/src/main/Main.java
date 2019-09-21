package main;

import java.util.Scanner;
import java.util.Stack;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line;
        Stack<String> operators = new Stack<>();
        Stack<Float> numbers = new Stack<>();

        line = scanner.nextLine();

        char[] lineArray = new char[line.length()];

        lineArray = line.toCharArray();

        System.out.println(calcExpression(lineArray, operators, numbers));
    }

    static float calcExpression(char[] charArr, Stack<String> operators, Stack<Float> numbers) {
        for(int i = 0; i < charArr.length; i++) {

            String operatorTemp;
            float numberTemp;

            if (charArr == null)
                throw new IllegalArgumentException("Num не должен быть null");

            switch(charArr[i]) {
                case '(': {
                    break;
                }
                case '+': {
                    operators.push("+");
                    break;
                }
                case '-': {
                    operators.push("-");
                    break;
                }
                case '*': {
                    operators.push("*");
                    break;
                }
                case '/': {
                    operators.push("/");
                    break;
                }
                case ')': {
                    operatorTemp = operators.pop();
                    numberTemp = numbers.pop();

                    switch(operatorTemp) {
                        case "+": {
                            numberTemp = numbers.pop() + numberTemp;
                            break;
                        }
                        case "-": {
                            numberTemp = numbers.pop() - numberTemp;
                            break;
                        }
                        case "*": {
                            numberTemp = numbers.pop() * numberTemp;
                            break;
                        }
                        case "/": {
                            numberTemp = numbers.pop() / numberTemp;
                            break;
                        }
                    }

                    numbers.push(numberTemp);
                    break;
                }
                default: {
                    numbers.push(Float.parseFloat(String.valueOf(charArr[i])));
                }
            }
        }

        return numbers.pop();
    }
}