package solitaire.solitaire;

import solitaire.solitaire.card.Card;
import solitaire.solitaire.card.Deck;
import solitaire.solitaire.working_stack.SuitStack;
import solitaire.solitaire.working_stack.WorkingStack;

import java.util.Stack;

public class GameModel {
    private static final GameModel instance = new GameModel();
    private final Deck deck = new Deck();
    private Stack<Card> waste;
    private WorkingStack workingStack;
    private SuitStack suitStack;

    public GameModel() {
        this.waste = new Stack<Card>();
    }

    public static GameModel getInstance(){
        return instance;
    }

      public void reset(){
        deck.reset();
        workingStack = new WorkingStack(deck);
        suitStack = new SuitStack();
        waste = new Stack<Card>();
    }

    public boolean discard() {
        if (this.deck.isEmpty()) return false;
        waste.add(this.deck.draw());
        return true;
    }

    public Card peekWaste() {
        if (waste.isEmpty()){
            return null;
        }
        return waste.peek();
    }

    public boolean wasteEmpty(){
        return waste.isEmpty();
    }
    public void removeWaste(){
        waste.pop();
    }

    public Card[] getStack(int stack){
        Card[] cards = new Card[workingStack.getWorkingStack(stack).size()];
        for (int i = 0; i < workingStack.getWorkingStack(stack).size(); i++) {
            cards[i] = (Card) workingStack.getWorkingStack(stack).get(i);
        }
        return cards;
    }

    public Card takeCardFromWorkingStack(int index){
        if (workingStack.isEmpty(index)) return null;
       return (Card) workingStack.getWorkingStack(index).peek();
    }
    public void addCard(Card card, int index){
            workingStack.add(card, index);
    }

    public boolean canAdd(Card top, int index){
        return workingStack.canAdd(top, index);
    }

    public boolean emptyWorkingStack(int index){
        return workingStack.isEmpty(index);
    }

    public void removeCard(int index){
        workingStack.removeCard(index);
    }

    public boolean canAddSuitCard(Card top){
        int index = top.suit().ordinal();
        return suitStack.canAdd(top, index);
    }

    public void addSuitCard(Card top){
        int index = top.suit().ordinal();
            suitStack.add(top, index);
    }

    public void shuffleCards(){
        workingStack.shuffle();
    }

}
