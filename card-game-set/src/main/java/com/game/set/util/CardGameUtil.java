package com.game.set.util;

import com.game.set.enums.Color;
import com.game.set.enums.Number;
import com.game.set.enums.Shading;
import com.game.set.enums.Shape;
import com.game.set.model.Card;
import com.game.set.model.CardSet;

import dnl.utils.text.table.TextTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * The Class CardGameUtil is a utility class for performing various tasks for a player such as
 * playing a sample set game, checking whether there is a set formed out of three cards ,if there is
 * a set in a list of board cards.It also encapsulates various print utility methods for displaying
 * a list of cards based on inputs by a player.
 */
public class CardGameUtil {

    //ORDER : number,color,shape,shade
    /** The Constant CARD_SEQUENCE. */
    public static final String [] CARD_SEQUENCE= new String[]{"Number","Color","Shape","Shade"};

    /**
     * Play set card game.
     */
    public static void playSetCardGame() {
        System.out.println("######################### SET GAME STARTED #########################");
        Collection<Card> listOfCards = populateDeckOfCards();
        int deckOfCardsCounter=52;boolean playDecider=true;
        List<Card> currentPlayBackLog = new ArrayList<Card>();
        while(deckOfCardsCounter >0){
            List<Card> currentPlay =getNextDealFromDeck(listOfCards,playDecider);
            listOfCards =burnCardsFromDeck(listOfCards,playDecider);
            System.out.println("######################### PLAY STARTED #########################");
            System.out.println("Cards Fetched : "+currentPlay.size());
            System.out.println("Play Cards : ");
            CardGameUtil.printUtility(CARD_SEQUENCE,CardGameUtil.returnPrintableArray(currentPlay));

            List<CardSet> setsInPlayRetreived = fetchSetsInPlay(playDecider?currentPlay:currentPlayBackLog);
            System.out.println("Sets in play retreived : ");
            setsInPlayRetreived.stream().forEach( s -> {
                CardGameUtil.printUtility(CARD_SEQUENCE,CardGameUtil.returnPrintableArray((List<Card>) s.getSetOfCards()));
            });
            if(setsInPlayRetreived.size() > 0){
                playDecider = true;//fetch next 12 cards from the deck
                currentPlayBackLog.clear();

            }
            else{
                playDecider = false;//fetch next 3 cards from the deck
                currentPlayBackLog.addAll(currentPlay);

            }
            deckOfCardsCounter=listOfCards.size();
            System.out.println("Current cards in Deck : "+deckOfCardsCounter);
            System.out.println("######################### PLAY ENDED #########################");
        }
        System.out.println("######################### SET GAME COMPLETED #########################");
        System.out.println("######################### THANKS FOR PLAYING !! #########################");

    }

    /**
     * Burn cards from deck.
     *
     * @param listOfCards the list of cards
     * @param decider the decider
     * @return the collection
     */
    public static Collection<Card> burnCardsFromDeck(Collection<Card> listOfCards, boolean decider) {
        return decider?listOfCards.stream().skip(12).collect(Collectors.toList()):listOfCards.stream().skip(3).collect(Collectors.toList());
    }

    /**
     * Fetch sets in play.
     *
     * @param currentPlay the current play
     * @return the list
     */
    public static List<CardSet> fetchSetsInPlay(List<Card> currentPlay) {

        final int N = currentPlay.size();
        List<CardSet> listOfCardSets = new ArrayList<CardSet>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                final Card card3 = getResultantThirdCard(currentPlay.get(i).getCardArray(), currentPlay.get(j).getCardArray());
                if(currentPlay.get(i).equals(currentPlay.get(j))){
                    final List<Card> newSubArrayList = currentPlay.stream().collect(Collectors.toList());
                    newSubArrayList.remove(currentPlay.get(i));newSubArrayList.remove(currentPlay.get(i));
                    if(Collections.binarySearch(newSubArrayList, card3) >=0){
                        listOfCardSets.add(new CardSet(currentPlay.get(i), currentPlay.get(j), card3));
                    }
                }
                else if(Collections.binarySearch(currentPlay, card3)>=0){
                    listOfCardSets.add(new CardSet(currentPlay.get(i), currentPlay.get(j), card3));
                }

            }

        }
        return listOfCardSets;
    }

    /**
     * Gets the next deal from deck.
     *
     * @param listOfCards the list of cards
     * @param decider the decider
     * @return the next deal from deck
     */
    public static List<Card> getNextDealFromDeck(Collection<Card> listOfCards, boolean decider) {
        return decider?listOfCards.stream().limit(12).collect(Collectors.toList()):listOfCards.stream().limit(3).collect(Collectors.toList());
    }

    /**
     * Populate deck of cards.
     *
     * @return the collection
     */
    public static Collection<Card> populateDeckOfCards() {
        List<Card> listOfCards=IntStream.range(0, 52).mapToObj(x -> new Card(new int[]{new Random().nextInt(3),new Random().nextInt(3),new Random().nextInt(3),new Random().nextInt(3)})).collect(Collectors.toList());
        return listOfCards;
    }

    /**
     * Check if three cards are set.
     *
     * @param card1 the card1
     * @param card2 the card2
     * @param card3 the card3
     * @return true, if successful
     */
    public static boolean checkIfThreeCardsAreSet(Card card1, Card card2, Card card3) {
        final int number = Stream.of(card1.getCardArray()[0], card2.getCardArray()[0], card3.getCardArray()[0]).collect(Collectors.toSet()).size();
        final int color = Stream.of(card1.getCardArray()[1], card2.getCardArray()[1], card3.getCardArray()[1]).collect(Collectors.toSet()).size();
        final int shape = Stream.of(card1.getCardArray()[2], card2.getCardArray()[2], card3.getCardArray()[2]).collect(Collectors.toSet()).size();
        final int shading = Stream.of(card1.getCardArray()[3], card2.getCardArray()[3], card3.getCardArray()[3]).collect(Collectors.toSet()).size();
        return ((color == 1 || color == 3) && (shape == 1 || shape == 3) && (shading == 1 || shading == 3) && (number == 1 || number == 3)) ? true : false;
    }


    /**
     * Check set from board cards.
     *
     * @param boardCards the board cards
     * @return true, if successful
     */
    public static boolean checkSetFromBoardCards(List<Card> boardCards) {

        final int N = boardCards.size();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                final Card card3 = getResultantThirdCard(boardCards.get(i).getCardArray(), boardCards.get(j).getCardArray());
                if(boardCards.get(i).equals(boardCards.get(j))){
                    final List<Card> newSubArrayList = boardCards.stream().collect(Collectors.toList());
                    newSubArrayList.remove(boardCards.get(i));newSubArrayList.remove(boardCards.get(i));
                    if(Collections.binarySearch(newSubArrayList, card3) >=0){
                        return true;
                    }
                }
                else if(Collections.binarySearch(boardCards, card3)>=0){
                    return true;
                }

            }

        }
        return false;
    }

    /**
     * Gets the resultant third card.
     *
     * @param arr1 the arr1
     * @param arr2 the arr2
     * @return the resultant third card
     */
    public static Card getResultantThirdCard(int[] arr1, int[] arr2) {
        int[] arr3 = new int[4];
        for(int i=0;i<arr1.length;i++){
            if(arr1[i]==arr2[i]){
                arr3[i]=arr1[i];
            }
            else if(arr1[i] > arr2[i]){
                arr3[i]=arr1[i]==2?(arr2[i]==1?0:1):2;
            }
            else{
                arr3[i]=arr2[i]==2?(arr1[i]==1?0:1):2;
            }
        }
        Card card3 = new Card(arr3);
        return card3;
    }

    /**
     * Prints the utility.
     *
     * @param columnNames the column names
     * @param data the data
     */
    public static void printUtility(String columnNames[], Object[][] data){
        TextTable tt = new TextTable(columnNames, data);
        tt.setAddRowNumbering(true);
        tt.printTable();
    }

    /**
     * Return printable array.
     *
     * @param cardsList the cards list
     * @return the object[][]
     */
    public static Object[][] returnPrintableArray(List<Card> cardsList){
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Object[][] data = new Object[cardsList.size()][4];
        cardsList.forEach(x -> {
            data[atomicInteger.get()][0] = Number.values()[x.getCardArray()[0]];
            data[atomicInteger.get()][1] = Color.values()[x.getCardArray()[1]];
            data[atomicInteger.get()][2] = Shape.values()[x.getCardArray()[2]];
            data[atomicInteger.get()][3] = Shading.values()[x.getCardArray()[3]];
            atomicInteger.getAndIncrement();
        });
        return data;
    }

}
