/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackJack;

/**
 *
 * @author Mark
 */
public class PlayingCard {

    private final CardSuit suit;
    private final CardValue value;
    
    public PlayingCard(CardSuit suit, CardValue value){
        this.suit = suit;
        this.value = value;
    }
    
    
    @Override
    public String toString(){
    return this.value.toString() + " of " + this.suit.toString();
}
    public CardValue getValue(){
        return this.value;
    }
    
}