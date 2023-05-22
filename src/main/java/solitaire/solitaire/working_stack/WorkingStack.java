package solitaire.solitaire.working_stack;

import solitaire.solitaire.card.Card;
import solitaire.solitaire.card.Deck;

import java.util.Collections;
import java.util.Stack;

public class WorkingStack implements CardStack {

    private Stack[] workingStacks = new Stack[]{
            new Stack<>(), new Stack<>(), new Stack<>(), new Stack<>(),
            new Stack<>(), new Stack<>(), new Stack<>()
    };


    public Stack getWorkingStack(int index) {
        return workingStacks[index];
    }

    public Card getCard(int index){
       return (Card) workingStacks[index].peek();
    }

    public boolean isEmpty(int index){
        return workingStacks[index].isEmpty();
    }

    public void removeCard(int index){
        workingStacks[index].pop();
    }

    public WorkingStack(Deck deck) {
        int num = 1;
        for (Stack workingStack : workingStacks) {
            for (int j = 0; j < num; j++) {
                workingStack.add(deck.draw());
            }
            num++;
        }
    }

    public void add(Card card, int index){
        workingStacks[index].push(card);
    }

    public boolean canAdd(Card card, int index){
        if (workingStacks[index].isEmpty()){
            return true;
        } else {
            if ((card.suit().ordinal() + getCard(index).suit().ordinal()) % 2 != 0){
                return card.rank().ordinal() == getCard(index).rank().ordinal() - 1;
            }
        }
        return false;
    }

    public void shuffle(){
        for (Stack workingStack : workingStacks) {
            Collections.shuffle(workingStack);
        }
    }

}
