package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Double> priceWithDiscount = decryptData(new int[]{80, 40, 60, 55, 78, 89}, 91, 1, 4);

        // showing prices with discount
        for (double price :
                priceWithDiscount) {
            // float was used to show the price because double is very long ( double: 2.09090804830494729 vs float: 2.09
            float polishedPrice = (float) price;
            System.out.println(polishedPrice);
        }
    }

    public static ArrayList<Double> decryptData(int[] price, int discount,
                                                int offset, int readLength) {

        // array of price indexes which were generated from actual prices
        ArrayList<Integer> rowOfGeneratedPriceIndexes = new ArrayList<>();

        //array of generated items ( positions)
        ArrayList<Integer> generatedItems = new ArrayList<>();
        int generatedNumber;

        // Generate numbers and add them to array of generated numbers
        for (int i = 0; i < readLength; i++) {
            generatedNumber = (int) (Math.random() * (price.length - offset + 1) + offset);
            generatedItems.add(generatedNumber);
        }

        // implementing array of generated indexes of price
        for (int generatedItemIndex : generatedItems) {
            for (int currentPrice : price) {
                if (price[generatedItemIndex - 1] == currentPrice) {
                    rowOfGeneratedPriceIndexes.add(currentPrice);
                }
            }
        }

        // returning array of prices with discount
        return getDiscount(discount, rowOfGeneratedPriceIndexes);
    }

    // Method to get discount price after subtracting percentage
    private static ArrayList<Double> getDiscount(int percent, ArrayList<Integer> rowPrice) {
        ArrayList<Double> priceWithDiscount = new ArrayList<>();
        for (double element :
                rowPrice) {
            double onePercent = (element / 100);
            double minus = onePercent * percent;
            double left = element - minus;
            priceWithDiscount.add(left);
        }
        return priceWithDiscount;
    }
}