package Cau4;

import java.lang.Math;

public class Rational {
    private int numerator;
    private int denominator;

    public Rational(int numerator, int denominator) {
        if (denominator == 0) {
            denominator = 1;
        }
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
        if (this.denominator < 0) {
            this.numerator = -this.numerator;
            this.denominator = -this.denominator;
        }
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public Rational reduce() {
        return this; // Already reduced in constructor
    }

    public Rational reciprocal() {
        return new Rational(denominator, numerator);
    }

    public Rational add(Rational other) {
        int newNum = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDen = this.denominator * other.denominator;
        return new Rational(newNum, newDen);
    }

    public Rational subtract(Rational other) {
        int newNum = this.numerator * other.denominator - other.numerator * this.denominator;
        int newDen = this.denominator * other.denominator;
        return new Rational(newNum, newDen);
    }

    public Rational multiply(Rational other) {
        int newNum = this.numerator * other.numerator;
        int newDen = this.denominator * other.denominator;
        return new Rational(newNum, newDen);
    }

    public Rational divide(Rational other) {
        return this.multiply(other.reciprocal());
    }

    public boolean equals(Rational other) {
        double tolerance = 0.0001;
        double thisValue = (double) this.numerator / this.denominator;
        double otherValue = (double) other.numerator / other.denominator;
        return Math.abs(thisValue - otherValue) < tolerance;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
