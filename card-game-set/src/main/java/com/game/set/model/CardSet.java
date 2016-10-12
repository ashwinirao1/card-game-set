package com.game.set.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


/**
 * The Class CardSet.
 */
public class CardSet {

    /** The set of cards. */
    private Collection<Card> setOfCards = new ArrayList<Card>();

    /**
     * Instantiates a new card set.
     *
     * @param card1 the card1
     * @param card2 the card2
     * @param card3 the card3
     */
    public CardSet(Card card1,Card card2,Card card3){
        Collections.addAll(setOfCards, card1,card2,card3);
    }

    /**
     * Gets the sets the of cards.
     *
     * @return the sets the of cards
     */
    public Collection<Card> getSetOfCards() {
        return setOfCards;
    }


    /**
     * Sets the sets the of cards.
     *
     * @param setOfCards the new sets the of cards
     */
    public void setSetOfCards(Collection<Card> setOfCards) {
        this.setOfCards = setOfCards;
    }



    @Override
    public String toString() {
        return "CardSet [setOfCards=" + setOfCards + "]";
    }

}
