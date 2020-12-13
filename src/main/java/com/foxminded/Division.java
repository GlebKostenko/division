package com.foxminded;

import java.util.Stack;

public class Division {
    private StringBuilder result = new StringBuilder();
    private StringBuilder reminder = new StringBuilder();

    public String makeDivision(int dividend, int divisor) {

        if (divisor == 0) {
            return "Divisor cannot be 0, division by zero";
        }

        if (dividend < divisor) {
            result = addNextDivisionStep((Integer.toString(dividend)).length() - 1,dividend,divisor,dividend,0);
            String last = String.format("%" + ((Integer.toString(dividend)).length()+1) + "s", dividend + "");
            result.append(last).append("\n");
            result = makeHeader(dividend,divisor);
            return result.toString();
        }
        int indexOfSurPlus = makeDivisionStable(dividend,divisor);
        if (reminder.length() == 0) {
            String last = String.format("%" + (indexOfSurPlus + 1) + "s", "0");
            result.append(last).append("\n");
        } else {
            String last = String.format("%" + (indexOfSurPlus + 1) + "s", reminder.toString());
            result.append(last).append("\n");
        }
        result = makeHeader(dividend,divisor);
        return result.toString();
    }

    public int makeDivisionStable(int dividend, int divisor){
        char[] digits = (Integer.toString(dividend)).toCharArray();
        Stack<String> digitsOfNum = new Stack<>();
        for (int i = digits.length - 1; i >= 0; --i) {
            digitsOfNum.add(String.valueOf(digits[i]));
        }
        int index = 0;
        while (!digitsOfNum.empty()) {
            reminder.append(digitsOfNum.pop());
            Integer curNum = Integer.parseInt(reminder.toString());
            if (curNum >= divisor) {
                Integer mod = curNum % divisor;
                int needToSubstract = curNum / divisor * divisor;
                if (mod != 0) {
                    reminder.replace(0, reminder.length(), mod.toString());
                } else {
                    reminder.replace(0, reminder.length(), "");
                }
                result = addNextDivisionStep(index,dividend,divisor,curNum,needToSubstract);
            }
            ++index;
        }
        return index;
    }

    public StringBuilder addNextDivisionStep(int index, int dividend, int divisor, Integer curNum, int needToSubstract) {
        if (result.length() == 0) {
            String lastReminder = String.format("%" + (index + 2) + "s", "_"
                    + Integer.toString(dividend)
                    + "│"
                    + Integer.toString(divisor));
            result.append(lastReminder).append("\n");
        } else {
            String lastReminder = String.format("%" + (index + 2) + "s", "_" + curNum.toString());
            result.append(lastReminder).append("\n");
        }

        String multiply = String.format("%" + (index + 2) + "d", needToSubstract);
        result.append(multiply).append("\n");
        String tab = String.format("%" + (index + 2) + "s", tabs(needToSubstract));
        result.append(tab).append("\n");
        return result;
    }

    public StringBuilder makeHeader(int dividend, int divisor){
        int indexOfFirstTransfer = 0;
        while (result.toString().charAt(indexOfFirstTransfer) != '\n') {
            ++indexOfFirstTransfer;
        }
        int indexOfSecondTransfer = indexOfFirstTransfer + 1;
        while (result.toString().charAt(indexOfSecondTransfer) != '\n') {
            ++indexOfSecondTransfer;
        }
        int indexOfThirdTransfer = indexOfSecondTransfer + 1;
        while (result.toString().charAt(indexOfThirdTransfer) != '\n') {
            ++indexOfThirdTransfer;
        }
        result.replace(indexOfSecondTransfer, indexOfSecondTransfer + 1,
                spaces(Integer.toString(dividend).length() - indexOfSecondTransfer + indexOfFirstTransfer + 2)
                        + "│"
                        + tabs(Math.max(divisor,dividend / divisor)) + "\n");
        while (result.toString().charAt(indexOfSecondTransfer) != '\n') {
            ++indexOfSecondTransfer;
        }
        indexOfThirdTransfer = indexOfSecondTransfer + 1;
        while (result.toString().charAt(indexOfThirdTransfer) != '\n') {
            ++indexOfThirdTransfer;
        }
        result.replace(indexOfThirdTransfer, indexOfThirdTransfer + 1,
                spaces(Integer.toString(dividend).length() - indexOfThirdTransfer + indexOfSecondTransfer + 2)
                        + "│"
                        + Integer.toString(dividend / divisor) + "\n");
        return result;
    }

    public String spaces(int numbersOfSpace){
        String result = "";
        for(int i = 0;i < numbersOfSpace ;++i) {
            result = result + " ";
        }
        return result;
    }

    public String tabs(int num){
        StringBuilder result = new StringBuilder();
        char[] digits = (Integer.toString(num)).toCharArray();
        for(int i = 0;i < digits.length; ++i){
            result.append("-");
        }
        return result.toString();
    }

}