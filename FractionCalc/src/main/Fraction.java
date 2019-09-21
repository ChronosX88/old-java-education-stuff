package main;

public class Fraction {

    private int numerator;
    private int denominator;

    Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() { return numerator; }

    public int getDenominator() {
        return denominator;
    }

    public void add(Fraction fr) {
        Fraction result = new Fraction(this.numerator * fr.denominator + fr.numerator * this.denominator, this.denominator * fr.denominator);
        this.numerator = result.numerator;
        this.denominator = result.denominator;
    }

    public void subtract(Fraction fr) {
        Fraction result = new Fraction(this.numerator * fr.denominator - fr.numerator * this.denominator, this.denominator * fr.denominator);
        this.numerator = result.numerator;
        this.denominator = result.denominator;
    }

    public void multiply(Fraction fr) {
        Fraction result = new Fraction((this.numerator * fr.denominator) * (fr.numerator * this.denominator), this.denominator * fr.denominator);
        this.numerator = result.numerator;
        this.denominator = result.denominator;
    }

    public void divide(Fraction fr) {
        Fraction result = new Fraction((this.numerator * fr.numerator) * (fr.denominator * this.denominator), (this.denominator * fr.numerator));
        this.numerator = result.numerator;
        this.denominator = result.denominator;
    }

    public void reduce() {
        int min = this.numerator > this.denominator ? this.denominator : this.numerator;
        int gcd = 0; // GCD - Greatest Common Divisor
        for (int i = 1; i < min; i++) {
            if(this.numerator % i == 0 && this.denominator % i == 0){
                if(i > gcd)
                    gcd = i;
            }
        }

        this.numerator /= gcd;
        this.denominator /= gcd;
    }

    public String toString() {
        String str = new String(this.numerator + "/" + this.denominator);
        return str;
    }
}
