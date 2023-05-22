package solitaire.solitaire.working_stack;

import solitaire.solitaire.card.Card;
import solitaire.solitaire.card.Rank;

import java.util.Stack;


public class SuitStack implements CardStack {
    private Stack<Card>[] suitStacks = new Stack[]{
        new Stack<>(), new Stack<>(), new Stack<>(), new Stack<>() };

    public void add(Card card, int index){
        suitStacks[index].push(card);
    }

    public boolean isEmpty(int index){
        return suitStacks[index].isEmpty();
    }

    public boolean canAdd(Card card, int index){
        if (isEmpty(index)){
            return card.rank() == Rank.Ace;
        } else {
            return suitStacks[index].peek().rank().ordinal() + 1 == card.rank().ordinal();
        }
    }

}
