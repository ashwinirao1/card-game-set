package com.game.set.model;

import com.game.set.enums.Color;
import com.game.set.enums.Number;
import com.game.set.enums.Shading;
import com.game.set.enums.Shape;

import java.util.Arrays;

/**
 * The Class Card.
 */
public class Card implements Comparable<Card>{


    /** The card array. */
    private final int cardArray[]= new int[4];

    /**
     * Instantiates a new card.
     */
    public Card() {

    }

    /**
     * Instantiates a new card.
     *
     * @param cardArray the card array
     */
    public Card(int cardArray[]) {
        System.arraycopy( cardArray, 0, this.cardArray, 0, this.cardArray.length );
    }

    /**
     * Instantiates a new card.
     *
     * @param color the color
     * @param shape the shape
     * @param shading the shading
     * @param number the number
     */
    public Card(Color color, Shape shape, Shading shading, Number number) {
        this.cardArray[0]=number.ordinal();
        this.cardArray[1]=color.ordinal();
        this.cardArray[2]=shape.ordinal();
        this.cardArray[3]=shading.ordinal();
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Card card) {
        if(this.equals(card)){
            return 0;
        }
        for(int i =0;i<this.cardArray.length;i++){
            if(this.cardArray[i]>card.getCardArray()[i]){
                return 1;
            }
            else if(this.cardArray[i]==card.getCardArray()[i]){
                continue;
            }
            else{
                return -1;
            }
        }
        return 0;

    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(cardArray);
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Card other = (Card) obj;
        if (!Arrays.equals(cardArray, other.cardArray))
            return false;
        return true;
    }

    /**
     * Gets the card array.
     *
     * @return the card array
     */
    public int[] getCardArray() {
        return cardArray;
    }


    @Override
    public String toString() {
        return "Card [number="+Number.values()[cardArray[0]]+", color=" + Color.values()[cardArray[1]]+", shape=" + Shape.values()[cardArray[2]]+", shading=" + Shading.values()[cardArray[3]]+ "]";
    }

}
