package solitaire.solitaire.working_stack;

import solitaire.solitaire.card.Card;

public interface CardStack {
    void add(Card card, int index);
    boolean canAdd(Card card, int index);
    boolean isEmpty(int index);
}
