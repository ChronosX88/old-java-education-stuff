package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int rangeBegin = 0;
        int rangeEnd = 100;
        
        int tempRand = 0;
        //int lastRand = 0;
        int countOfAttempt = 1;
        String userAnswer;


        System.out.println("OneFourThree program guesses the number that the user made.");
        System.out.println("Make a number from 0 to " + rangeEnd + ".");
        System.out.println("Enter +/-/= if the number that the computer displays is more/less than your number, or it is correct.");
        System.out.println("For exit, write \"quit\".");
        System.out.printf("\n\n");
        System.out.print("Make a number, then press Enter...");
        scanner.nextLine();

        System.out.printf("\n\n");


        while (true) {
            tempRand = (int) (int)Math.round((rangeBegin + rangeEnd) / 2);

            System.out.println(tempRand);
            System.out.print("Правильно? +/-/=: ");
            userAnswer = scanner.next();
            if( userAnswer.equals("+") )  {
                rangeBegin = tempRand;
                countOfAttempt++;
            } else if( userAnswer.equals("-") ) {
                rangeEnd = tempRand;
                countOfAttempt++;
            } else if( userAnswer.equals("=") ) {
                System.out.println("Вы выиграли!");
                System.out.println("Компьютер угадал ваше число за " + countOfAttempt + " попыток (-ки).");
                break;
            } else if( userAnswer.equals("quit") )  {
                System.out.println("Выходим...");
                break;
            }
        }

    }


}
