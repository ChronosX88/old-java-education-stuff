package main;

// Driver for managing the Fraction class

public class Main {

    public static void main(String[] args) {
        Fraction fraction = new Fraction(2,4);
        Fraction fraction1 = new Fraction(4, 6);

        fraction.add(fraction1);
        fraction.subtract(fraction1);
        fraction.multiply(fraction1);
        fraction.divide(fraction1);
        fraction.reduce();

        System.out.println(fraction.toString());
    }
}
