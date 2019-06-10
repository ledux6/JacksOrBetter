import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.swing.border.EmptyBorder;

public class PokerGame {


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ArrayList<String> cards = getADeckOfCards();
        ArrayList<String> firstFive = getCards(cards, 5);

        for (String card : firstFive) {
            System.out.print(card+ " ");
        }
        System.out.println("\n1  2  3  4  5");
        System.out.println("Type in the numbers of cards you want to discard:"
                + "(example: 1,2,3)");
        Scanner myObj = new Scanner(System.in);
        String s = myObj.nextLine();
        ArrayList<Integer> indexes = getIndexes(s);
        ArrayList<String> newDraw = getCards(cards, indexes.size());
        ArrayList<String> firstDraw = removeIndexes(indexes, firstFive);
        firstDraw.addAll(newDraw);
        for (String card : firstDraw) {
            System.out.print(card+ " ");
        }
        String result = getResult(firstDraw);
        System.out.println("\n"+result);

    }

    public static ArrayList<String> getADeckOfCards(){
        ArrayList<String> cards = new ArrayList<>(
                Arrays.asList("2H","3H","4H","5H","6H","7H","8H","9H","10H","JH","QH","KH","AH",
                        "2D","3D","4D","5D","6D","7D","8D","9D","10D","JD","QD","KD","AD",
                        "2C","3C","4C","5C","6C","7C","8C","9C","10C","JC","QC","KC","AC",
                        "2S","3S","4S","5S","6S","7S","8S","9S","10S","JS","QS","KS","AS"));
        return cards;
    }

    public static ArrayList<String> getCards(ArrayList<String> cards, int count){
        ArrayList<String> selected = new ArrayList<>();
        Random r = new Random();
        int index = 0;
        for (int i = 0; i < count; i++) {
            index = r.nextInt(cards.size());
            //System.out.println(index);
            selected.add(cards.get(index));
            cards.remove(index);
        }
        return selected;
    }

    public static ArrayList<Integer> getIndexes(String s){
        ArrayList<Integer> indexes = new ArrayList<>();
        if (s.isEmpty()){
            return indexes;
        }
        String[] parts = s.split(",");
        for (String string : parts) {
            indexes.add(Integer.parseInt(string));
        }
        return indexes;
    }

    public static ArrayList<String> removeIndexes(ArrayList<Integer> indexes, ArrayList<String> cards){
        if (indexes.size() == 5){
            ArrayList<String> empty = new ArrayList<String>();
            return empty;
        }
        for (Integer index : indexes) {
            cards.remove(index-1);
        }
        return cards;
    }
    public static String getResult(ArrayList<String> cards){

        if (isFlush(cards)){
            if(isRoyal(cards)){
                return "Royal Flush";
            }
        }
        if(isFlush(cards)){
            if(isStraight(cards)){
                return "Straight Flush";
            }
        }
        if (isFlush(cards)){
            return "Flush";
        }
        if(isStraight(cards)){
            return "Straight";
        }
        if(countSimilar(cards) == 4){
            return "4 of a Kind";
        }
        if(countSimilar(cards) == 5){
            return "Full House";
        }
        if(countSimilar(cards) == 3){
            return "3 of a Kind";
        }
        if(countSimilar(cards) == 2){
            return "Two Pairs";
        }
        if(countSimilar(cards) == 1){
            return "Jacks or Better";
        }
        return "You didn't win";

    }
    public static boolean isFlush(ArrayList<String> cards){
        int count = 0;
        for (String card : cards) {
            if(card.charAt(card.length()-1) == 'D' ){
                count++;
            }
        }
        if (count == 5){
            return true;
        }
        count = 0;
        for (String card : cards) {
            if(card.charAt(card.length()-1) == 'S' ){
                count++;
            }
        }
        if (count == 5){
            return true;
        }
        count = 0;
        for (String card : cards) {
            if(card.charAt(card.length()-1) == 'H' ){
                count++;
            }
        }
        if (count == 5){
            return true;
        }
        count = 0;
        for (String card : cards) {
            if(card.charAt(card.length()-1) == 'C' ){
                count++;
            }
        }
        if (count == 5){
            return true;
        }
        return false;
    }

    public static boolean isRoyal(ArrayList<String> cards){
        boolean ten = false;
        for (String card : cards) {
            if (card.contains("10")){
                ten = true;
            }
        }
        boolean j = false;
        for (String card : cards) {
            if (card.contains("J")){
                j = true;
            }
        }
        boolean q = false;
        for (String card : cards) {
            if (card.contains("Q")){
                q = true;
            }
        }
        boolean k = false;
        for (String card : cards) {
            if (card.contains("K")){
                k = true;
            }
        }
        boolean a = false;
        for (String card : cards) {
            if (card.contains("A")){
                a = true;
            }
        }
        if(ten && j && q && k && a){
            return true;
        }
        return false;
    }

    public static boolean isStraight(ArrayList<String> cards){
        boolean one = false;
        boolean two = false;
        boolean three = false;
        boolean four = false;
        boolean five = false;
        for (String card : cards) {
            if (card.contains("2")) {
                one = true;
            }
            if (card.contains("3")) {
                two = true;
            }
            if (card.contains("4")) {
                three = true;
            }
            if (card.contains("5")) {
                four = true;
            }
            if (card.contains("6")) {
                five = true;
            }
        }
        if(one && two && three && four && five){
            return true;
        }
        one = false;
        two = false;
        three = false;
        four = false;
        five = false;
        for (String card : cards) {
            if (card.contains("3")) {
                one = true;
            }
            if (card.contains("4")) {
                two = true;
            }
            if (card.contains("5")) {
                three = true;
            }
            if (card.contains("6")) {
                four = true;
            }
            if (card.contains("7")) {
                five = true;
            }
        }
        if(one && two && three && four && five){
            return true;
        }
        one = false;
        two = false;
        three = false;
        four = false;
        five = false;
        for (String card : cards) {
            if (card.contains("4")) {
                one = true;
            }
            if (card.contains("5")) {
                two = true;
            }
            if (card.contains("6")) {
                three = true;
            }
            if (card.contains("7")) {
                four = true;
            }
            if (card.contains("8")) {
                five = true;
            }
        }
        if(one && two && three && four && five){
            return true;
        }
        one = false;
        two = false;
        three = false;
        four = false;
        five = false;
        for (String card : cards) {
            if (card.contains("5")) {
                one = true;
            }
            if (card.contains("6")) {
                two = true;
            }
            if (card.contains("7")) {
                three = true;
            }
            if (card.contains("8")) {
                four = true;
            }
            if (card.contains("9")) {
                five = true;
            }
        }
        if(one && two && three && four && five){
            return true;
        }
        one = false;
        two = false;
        three = false;
        four = false;
        five = false;
        for (String card : cards) {
            if (card.contains("6")) {
                one = true;
            }
            if (card.contains("7")) {
                two = true;
            }
            if (card.contains("8")) {
                three = true;
            }
            if (card.contains("9")) {
                four = true;
            }
            if (card.contains("10")) {
                five = true;
            }
        }
        if(one && two && three && four && five){
            return true;
        }
        one = false;
        two = false;
        three = false;
        four = false;
        five = false;
        for (String card : cards) {
            if (card.contains("7")) {
                one = true;
            }
            if (card.contains("8")) {
                two = true;
            }
            if (card.contains("9")) {
                three = true;
            }
            if (card.contains("10")) {
                four = true;
            }
            if (card.contains("J")) {
                five = true;
            }
        }
        if(one && two && three && four && five){
            return true;
        }
        one = false;
        two = false;
        three = false;
        four = false;
        five = false;
        for (String card : cards) {
            if (card.contains("8")) {
                one = true;
            }
            if (card.contains("9")) {
                two = true;
            }
            if (card.contains("10")) {
                three = true;
            }
            if (card.contains("J")) {
                four = true;
            }
            if (card.contains("Q")) {
                five = true;
            }
        }
        if(one && two && three && four && five){
            return true;
        }
        one = false;
        two = false;
        three = false;
        four = false;
        five = false;
        for (String card : cards) {
            if (card.contains("9")) {
                one = true;
            }
            if (card.contains("10")) {
                two = true;
            }
            if (card.contains("J")) {
                three = true;
            }
            if (card.contains("Q")) {
                four = true;
            }
            if (card.contains("K")) {
                five = true;
            }
        }
        if(one && two && three && four && five){
            return true;
        }
        one = false;
        two = false;
        three = false;
        four = false;
        five = false;
        for (String card : cards) {
            if (card.contains("10")) {
                one = true;
            }
            if (card.contains("J")) {
                two = true;
            }
            if (card.contains("Q")) {
                three = true;
            }
            if (card.contains("K")) {
                four = true;
            }
            if (card.contains("A")) {
                five = true;
            }
        }
        if(one && two && three && four && five){
            return true;
        }
        return false;
    }

    public static int countSimilar(ArrayList<String> cards){
        int[] counters = new int[13];
        for (int i : counters) {
            i = 0;
        }

        for (String card : cards) {
            if(card.contains("2")){
                counters[0]++;
            }
            if(card.contains("3")){
                counters[1]++;
            }
            if(card.contains("4")){
                counters[2]++;
            }
            if(card.contains("5")){
                counters[3]++;
            }
            if(card.contains("6")){
                counters[4]++;
            }
            if(card.contains("7")){
                counters[5]++;
            }
            if(card.contains("8")){
                counters[6]++;
            }
            if(card.contains("9")){
                counters[7]++;
            }
            if(card.contains("10")){
                counters[8]++;
            }
            if(card.contains("J")){
                counters[9]++;
            }
            if(card.contains("Q")){
                counters[10]++;
            }
            if(card.contains("K")){
                counters[11]++;
            }
            if(card.contains("A")){
                counters[12]++;
            }
        }
        for (int i : counters) {
            if(i == 4){
                return 4;
            }
        }
        boolean three = false;
        boolean two = false;
        for (int i : counters) {
            if( i == 3){
                three = true;
            }
            if( i == 2){
                two = true;
            }
        }
        if (three && two) {
            return 5;
        }
        for (int i : counters) {
            if( i == 3){
                return 3;
            }
        }
        int pairCount = 0;
        for (int i : counters) {
            if( i == 2){
                pairCount++;
            }
        }
        if(pairCount==2){
            return 2;
        }
        if(counters[9] == 2 || counters[10] == 2 || counters[11] == 2 || counters[12] == 2){
            return 1;
        }
        return 0;

    }
}
