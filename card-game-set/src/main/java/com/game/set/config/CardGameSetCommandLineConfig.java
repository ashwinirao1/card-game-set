package com.game.set.config;

import static com.game.set.util.CardGameUtil.CARD_SEQUENCE;

import com.game.set.model.Card;
import com.game.set.util.CardGameUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * The Class CardGameSetCommandLineConfig is the entry point for a player to use card game set
 * via the command line interface.The player can input the card details with simple inputs
 * from the options 0,1 or 2 for each of its attributes color, number, shape and shade.
 *
 */
@Component
public class CardGameSetCommandLineConfig implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Please enter any one of the options below to proceed (1,2 or 3): ");
            System.out.println("[1] - Check if three cards are a set ");
            System.out.println("[2] - Check if there is a set present in a board of cards ");
            System.out.println("[3] - Play a sample set game !! ");

            switch (scanner.nextInt()) {
            case 1:
                checkIfThreeCardsAreASet(scanner);
                break;
            case 2:
                checkSetFromABoardOfCards(scanner);
                break;
            case 3:
                playASetGame();
                break;

            default:
                System.out.println("Please provide a valid input to proceed (1,2 or 3)");
                break;
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Please provide valid input type to proceed :"+e.getMessage());
        }
        catch (Exception e) {

            System.out.println("Exception occured while proceeding with card game set : " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }

    }

    /**
     * Play a sample set game.
     */
    private void playASetGame() {
        CardGameUtil.playSetCardGame();
    }

    /**
     * Check set from a board of cards.
     *
     * @param scanner the scanner
     */
    private void checkSetFromABoardOfCards(Scanner scanner) {
        int cards = 1;
        List<Card> listOfBoardCards = new ArrayList<>();
        try{
            System.out.println("################### Enter number of cards on the boards to check a set from (at least three cards required) : ###################");
            int numberOfCards = scanner.nextInt();
            while (cards <= numberOfCards) {
                System.out.println("################### Enter card " + cards + " details : ###################");
                System.out.println("Enter card color [0 (for red), 1(for green), 2 (for purple)]: ");
                int color = scanner.nextInt();
                System.out.println("Enter card number [0 (for one), 1(for two), 2 (for three)]: ");
                int number = scanner.nextInt();
                System.out.println("Enter card shape [0 (for diamond), 1(for squiggle), 2 (for oval)]: ");
                int shape = scanner.nextInt();
                System.out.println("Enter card shade [0 (for solid), 1(for empty), 2 (for striped)]: ");
                int shade = scanner.nextInt();
                listOfBoardCards.add(new Card(new int[] { number, color, shape, shade }));
                cards++;
            }
        }
        catch(Exception e){
            throw new InputMismatchException("Invalid input provided :Error excuting option 2");
        }
        System.out.println("################### Entered input cards : ###################");
        CardGameUtil.printUtility(CARD_SEQUENCE, CardGameUtil.returnPrintableArray(listOfBoardCards));
        System.out.println("################### Result ###################: " + (CardGameUtil.checkSetFromBoardCards(listOfBoardCards) ? "SET PRESENT" : "NO SET PRESENT"));

    }

    /**
     * Check if three cards are a set.
     *
     * @param scanner the scanner
     */
    private void checkIfThreeCardsAreASet(Scanner scanner) {
        int cards = 1;
        Card cardArray[] = new Card[3];
        try{
            while (cards < 4) {
                System.out.println("################### Enter card " + cards + " details : ###################");
                System.out.println("Enter card color [0 (for red), 1(for green), 2 (for purple)]: ");
                int color = scanner.nextInt();
                System.out.println("Enter card number [0 (for one), 1(for two), 2 (for three)]: ");
                int number = scanner.nextInt();
                System.out.println("Enter card shape [0 (for diamond), 1(for squiggle), 2 (for oval)]: ");
                int shape = scanner.nextInt();
                System.out.println("Enter card shade [0 (for solid), 1(for empty), 2 (for striped)]: ");
                int shade = scanner.nextInt();
                cardArray[cards - 1] = new Card(new int[] { number, color, shape, shade });
                cards++;
            }
        }
        catch(Exception e){
            throw new InputMismatchException("Invalid input provided :Error excuting option 1");
        }
        System.out.println("################### Entered input cards : ###################");
        CardGameUtil.printUtility(CARD_SEQUENCE, CardGameUtil.returnPrintableArray(Arrays.asList(cardArray)));
        System.out.println("################### Result ###################: " + (CardGameUtil.checkIfThreeCardsAreSet(cardArray[0], cardArray[1], cardArray[2]) ? "A SET" : "NOT A SET"));

    }

}
