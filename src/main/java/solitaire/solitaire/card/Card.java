package solitaire.solitaire.card;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public record Card(Rank rank, Suit suit) {
    private static final Map<String, Image> cardsImages = new HashMap<>();
    private static final Card[][] cards = new Card[Suit.values().length][Rank.values().length];
    private static final String[] rankCodes = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private static final String[] suitCodes = {"_Clover", "_Diamonds", "_Spades", "_Hearts"};

    public static String getCode(Card card) {
        return rankCodes[card.rank().ordinal()] + suitCodes[card.suit().ordinal()] + ".png";
    }

    public static Card getCard(Rank rank, Suit suit) {
        if (cards[suit.ordinal()][rank.ordinal()] == null) {
            cards[suit.ordinal()][rank.ordinal()] = new Card(rank, suit);
        }
        return cards[suit.ordinal()][rank.ordinal()];
    }

    public static Image getImage(Card card) {
        return getImage(getCode(card));
    }

    private static Image getImage(String code) {
        Image image = cardsImages.get(code);
        if (image == null) {
            image = new Image(Objects.requireNonNull(Card.class.getClassLoader().getResourceAsStream(code)));
            cardsImages.put(code, image);
        }
        return image;
    }

    @Override
    public String toString() {
        return rank +
                " of " + suit;
    }
}
