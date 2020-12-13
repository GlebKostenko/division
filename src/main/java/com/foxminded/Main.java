package com.foxminded;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanNumbers = new Scanner(System.in);
        Division division = new Division();
        int dividend = scanNumbers.nextInt();
        int divisor = scanNumbers.nextInt();
        System.out.println(division.makeDivision(dividend,divisor));
    }
}
