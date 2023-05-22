package solitaire.solitaire;


import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import solitaire.solitaire.card.Card;

import java.awt.event.KeyListener;
import java.util.Queue;
import java.util.Stack;


public class SolitaireController {

    public StackPane suitStack1;
    public StackPane suitStack2;
    public StackPane suitStack3;
    public StackPane suitStack4;
    public ImageView cardStack;
    public Button cardDeck;
    public StackPane cardStack1 = new StackPane();
    public StackPane cardStack2 = new StackPane();
    public StackPane cardStack3 = new StackPane();
    public StackPane cardStack4 = new StackPane();
    public StackPane cardStack5 = new StackPane();
    public StackPane cardStack6 = new StackPane();
    public StackPane cardStack7 = new StackPane();
    public Button start;
    public Label showPickedCards;
    private Card pickedCard;
    private int source;


    public SolitaireController() {
        GameModel.getInstance().reset();
    }

    public void mousePressed() {
        cardDeck.setStyle(" -fx-background-color: transparent; -fx-padding: 6,4,4,6; -fx-cursor: CLOSED_HAND ");
    }

    public void mouseReleased() {
        cardDeck.setStyle(" -fx-background-color: transparent; -fx-padding: 5,5,5,5");
        if (GameModel.getInstance().discard()) {
            cardStack.setVisible(true);
            Card topCard = GameModel.getInstance().peekWaste();
            cardStack.setImage(Card.getImage(topCard));
        } else {
            cardDeck.setVisible(false);
        }
    }

    private void buildLayout(StackPane pane, int index) {
        int offset = 0;
        Card[] stack = GameModel.getInstance().getStack(index);
        for (Card card : stack) {
            ImageView imageView = new ImageView(Card.getImage(card));
            imageView.setTranslateY(17 * offset);
            offset++;
            pane.setStyle("-fx-padding: 5, 5, 5, 5");
            pane.getChildren().add(imageView);
        }
    }

    public void shuffleCards(){
        StackPane[] cardStacks = {cardStack1, cardStack2, cardStack3, cardStack4, cardStack5, cardStack6, cardStack7};
        GameModel.getInstance().shuffleCards();
        for (int i = 0; i < cardStacks.length; i++) {
            cardStacks[i].getChildren().clear();
            buildLayout(cardStacks[i], i);
        }
    }

    public void buttonStart() {
        StackPane[] cardStacks = {cardStack1, cardStack2, cardStack3, cardStack4, cardStack5, cardStack6, cardStack7};
        for (int i = 0; i < cardStacks.length; i++) {
            cardStacks[i].setVisible(true);
            cardStacks[i].getChildren().clear();
            buildLayout(cardStacks[i], i);
        }
    }

    public void addCard(MouseEvent mouseEvent) {
        StackPane pane = (StackPane) mouseEvent.getSource();
        String id = pane.getId();
        StackPane[] cardStacks = {cardStack1, cardStack2, cardStack3, cardStack4, cardStack5, cardStack6, cardStack7};
        if (pickedCard != null) {
            if (addCardInStack(id, pane, cardStacks)) {
                if (source == -1) {
                    removeCardWaste();
                } else {
                    removeCardStack(cardStacks);
                }
                pickedCard = null;
            }

        }
    }

    private boolean addCardInStack(String id, StackPane pane, StackPane[] cardStacks) {
        for (int i = 0; i < cardStacks.length; i++) {
            if (id.equals(cardStacks[i].getId()) && GameModel.getInstance().canAdd(pickedCard, i)) {
                GameModel.getInstance().addCard(pickedCard, i);
                ImageView imageView = new ImageView(Card.getImage(pickedCard));
                if (pane.getChildren().size() != 0) {
                    imageView.setTranslateY(pane.getChildren().size() * 17);
                }
                pane.getChildren().add(imageView);
                pane.setOpacity(100.0);

                return true;
            }
        }
        return false;
    }


    private void removeCardWaste() {
        GameModel.getInstance().removeWaste();
        if (GameModel.getInstance().wasteEmpty()) {
            cardStack.setVisible(false);
        } else {
            cardStack.setImage(Card.getImage(GameModel.getInstance().peekWaste()));
        }
    }

    private void removeCardStack(StackPane[] cardStacks) {
        GameModel.getInstance().removeCard(source);
        if (GameModel.getInstance().emptyWorkingStack(source)) {
            cardStacks[source].getChildren().remove(0);
            cardStacks[source].setStyle("-fx-opacity: 0.0");
        } else {
            cardStacks[source].getChildren().remove(GameModel.getInstance().getStack(source).length);
        }
        source = -1;
    }


    public void setPickedCard(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            pickedCard = GameModel.getInstance().peekWaste();
            source = -1;
            showPickedCards.setText(pickedCard.toString());
        } else if (mouseEvent.getSource() instanceof StackPane pane) {
            String id = pane.getId();
            StackPane[] cardStacks = {cardStack1, cardStack2, cardStack3, cardStack4, cardStack5, cardStack6, cardStack7};
            for (int i = 0; i < cardStacks.length; i++) {
                if (id.equals(cardStacks[i].getId())) {
                        pickedCard = GameModel.getInstance().takeCardFromWorkingStack(i);
                        showPickedCards.setText(pickedCard.toString());
                        source = i;
                        break;
                }
            }
        }
    }

    

    public void addSuitCard(MouseEvent mouseEvent) {
        StackPane[] cardStacks = {cardStack1, cardStack2, cardStack3, cardStack4, cardStack5, cardStack6, cardStack7};
        StackPane[] suitStacks = {suitStack1, suitStack2, suitStack3, suitStack4};
        if (GameModel.getInstance().canAddSuitCard(pickedCard) && pickedCard != null) {
            GameModel.getInstance().addSuitCard(pickedCard);
            ImageView imageView = new ImageView(Card.getImage(pickedCard));
            suitStacks[pickedCard.suit().ordinal()].getChildren().add(imageView);
            if (source == -1) {
                removeCardWaste();
            } else {
                removeCardStack(cardStacks);
            }
            pickedCard = null;
        }
    }
}
