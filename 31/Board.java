/* Class is used to simulate two a piles of cards, a stock pile and a discard pile.
 * The formatting of the cards are explained below.
 *
 * A card is a string of digits 'sdd' where the first digit, 's', is the suit of the card.
 *
 * 0 : clubs
 * 1 : diamonds
 * 2 : hearts
 * 3 : spades
 *
 * The last two digits, 'dd' denote the card. Where the cards with numbers are the numbers on the cards.
 * The other "special" cards have been given other values.
 *
 * 1 : ace
 * 11 : jester
 * 12 : queen
 * 13 : king
 *
 * This class is used with other classes to simulate a variation of the game "31".
 * 
 * CLARIFICATION: 
 * 
 * (1) declaring all fields (= variables declared outside methods) 
 *     as private prevents them to be changed be changed by anything outside of this class. 
 * (2) this.[variable name] is used to refer to a field of a class.
 *     You are able to refer to a field with only its name, but I like to use
 *     this.[variable name].
 * (3) declaring methods as privates prevents them from being called outside this class
 * (4) this.[method name] is in the same as doing this.[variable name], I prefer
 *     to wrtie this.[method name] instead of [method name]. 
 *
 * ----
 * Author: Shamiur Rahman Ramim
 */

import java.util.Random;
import javax.swing.ImageIcon;
import java.util.Arrays;

public class Board {

    /* Fields */

    private ImageIcon[] cardImages; // icons of all the playing-cards (1)
    private String[] stockPile; // the stock pile of cards
    private String[] discardPile; // the discard pile of cards
    private int numberOfDiscardedCards; // number of cards that have actually been discarded

    /* Constructor */

    public Board() {

        this.getAllCardImages(); // (2)
        this.generateStockPile();
        this.shufflePile(this.stockPile);

        this.discardPile = new String[52];
        this.numberOfDiscardedCards = 0;
    }

    /* Internal */

    // Method fills this.cardImages with icons of all the playing-cards in order (3)
    private void getAllCardImages() {

        this.cardImages = new ImageIcon[52];
        this.addClubs(0);
        this.addDiamonds(13);
        this.addHearts(25);
        this.addSpades(38);
    }

    // Method adds all cards that are clubs to this.cardImages
	private void addClubs(int startIndex) {

		this.cardImages[startIndex++] = new ImageIcon("ace_of_clubs.png");
		this.cardImages[startIndex++] = new ImageIcon("2_of_clubs.png");
		this.cardImages[startIndex++] = new ImageIcon("3_of_clubs.png");
		this.cardImages[startIndex++] = new ImageIcon("4_of_clubs.png");
		this.cardImages[startIndex++] = new ImageIcon("5_of_clubs.png");
		this.cardImages[startIndex++] = new ImageIcon("6_of_clubs.png");
		this.cardImages[startIndex++] = new ImageIcon("7_of_clubs.png");
		this.cardImages[startIndex++] = new ImageIcon("8_of_clubs.png");
		this.cardImages[startIndex++] = new ImageIcon("9_of_clubs.png");
		this.cardImages[startIndex++] = new ImageIcon("10_of_clubs.png");
		this.cardImages[startIndex++] = new ImageIcon("jack_of_clubs.png");
		this.cardImages[startIndex++] = new ImageIcon("queen_of_clubs.png");
		this.cardImages[startIndex++] = new ImageIcon("king_of_clubs.png");
	}

    // Method adds all diamonds that are clubs to this.cardImages
	private void addDiamonds(int startIndex) {

		this.cardImages[startIndex++] = new ImageIcon("ace_of_diamonds.png");
		this.cardImages[startIndex++] = new ImageIcon("2_of_diamonds.png");
		this.cardImages[startIndex++] = new ImageIcon("3_of_diamonds.png");
		this.cardImages[startIndex++] = new ImageIcon("4_of_diamonds.png");
		this.cardImages[startIndex++] = new ImageIcon("5_of_diamonds.png");
		this.cardImages[startIndex++] = new ImageIcon("6_of_diamonds.png");
		this.cardImages[startIndex++] = new ImageIcon("7_of_diamonds.png");
		this.cardImages[startIndex++] = new ImageIcon("8_of_diamonds.png");
		this.cardImages[startIndex++] = new ImageIcon("9_of_diamonds.png");
		this.cardImages[startIndex++] = new ImageIcon("10_of_diamonds.png");
		this.cardImages[startIndex++] = new ImageIcon("jack_of_diamonds.png");
		this.cardImages[startIndex++] = new ImageIcon("queen_of_diamonds.png");
		this.cardImages[startIndex++] = new ImageIcon("king_of_diamonds.png");
	}

    // Method adds all cards that are hearts to this.cardImages
	private void addHearts(int startIndex) {

		this.cardImages[startIndex++] = new ImageIcon("ace_of_hearts.png");
		this.cardImages[startIndex++] = new ImageIcon("2_of_hearts.png");
		this.cardImages[startIndex++] = new ImageIcon("3_of_hearts.png");
		this.cardImages[startIndex++] = new ImageIcon("4_of_hearts.png");
		this.cardImages[startIndex++] = new ImageIcon("5_of_hearts.png");
		this.cardImages[startIndex++] = new ImageIcon("6_of_hearts.png");
		this.cardImages[startIndex++] = new ImageIcon("7_of_hearts.png");
		this.cardImages[startIndex++] = new ImageIcon("8_of_hearts.png");
		this.cardImages[startIndex++] = new ImageIcon("9_of_hearts.png");
		this.cardImages[startIndex++] = new ImageIcon("10_of_hearts.png");
		this.cardImages[startIndex++] = new ImageIcon("jack_of_hearts.png");
		this.cardImages[startIndex++] = new ImageIcon("queen_of_hearts.png");
		this.cardImages[startIndex++] = new ImageIcon("king_of_hearts.png");
	}

    // Method adds all cards that are spades to this.cardImages
	private void addSpades(int startIndex) {

		this.cardImages[startIndex++] = new ImageIcon("ace_of_spades.png");
		this.cardImages[startIndex++] = new ImageIcon("2_of_spades.png");
		this.cardImages[startIndex++] = new ImageIcon("3_of_spades.png");
		this.cardImages[startIndex++] = new ImageIcon("4_of_spades.png");
		this.cardImages[startIndex++] = new ImageIcon("5_of_spades.png");
		this.cardImages[startIndex++] = new ImageIcon("6_of_spades.png");
		this.cardImages[startIndex++] = new ImageIcon("7_of_spades.png");
		this.cardImages[startIndex++] = new ImageIcon("8_of_spades.png");
		this.cardImages[startIndex++] = new ImageIcon("9_of_spades.png");
		this.cardImages[startIndex++] = new ImageIcon("10_of_spades.png");
		this.cardImages[startIndex++] = new ImageIcon("jack_of_spades.png");
		this.cardImages[startIndex++] = new ImageIcon("queen_of_spades.png");
		this.cardImages[startIndex++] = new ImageIcon("king_of_spades.png");
	}

    // Method creates the string representations of all 52 cards
    private void generateStockPile() {

        this.stockPile = new String[52];
        int selectedSuit = 0; // the integer that tells the suit of a card

        // loop goes through and fills the empty stock pile with string representations of cards
        for (int cardPlacement = 0; cardPlacement < 52; cardPlacement++) {

            int cardCount = cardPlacement % 13; // tells the count of a card (value if you were to look through a sorted deck)
            if (cardCount == 0 && cardPlacement != 0) { // changes suit when count is 13, which is when you've looked through a whole suit

                selectedSuit++;
            }

            String card = ""; // string representation of card
            if (cardCount < 10) { // formats the string correctly so that retrieving the count will be problem-free

                card = selectedSuit + "0";
            }

            card += (cardCount + 1); // 1 is added so that the cardCount is within the integer range of [1, 13]
            this.stockPile[cardPlacement] = card;
        }
    }

    // shuffles a card pile (stolen: https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array)
    private void shufflePile(String[] pile) {

        int indexOfCard = 0;
        String temporaryCardHolder = null;
        Random random = new Random();

        for (int i = pile.length - 1; i > 1; i--) {

            indexOfCard = random.nextInt(i + 1);
            temporaryCardHolder = pile[indexOfCard];
            pile[indexOfCard] = pile[i];
            pile[i] = temporaryCardHolder;
        }
    }

    // Method is used to get the index of a card, index is used to get the card's icon
    private int getIndexOf(String card) {

		int suit = Integer.parseInt(card.substring(0, 1)); // gets the suit of the card
		int cardCount = Integer.parseInt(card.substring(1)); // gets the card count of the card
		int index = suit * 13 + (cardCount - 1); // is count - 1 because the index has to be less that 52.

		return index;
    }

    /* User Interface */

    // Method is used when user wants to draw a new card from stock pile
    public String drawFromStock(String card) {

        this.discardPile[this.numberOfDiscardedCards++] = card; // adds the discarded card to the discard pile
        String newCard = this.stockPile[this.stockPile.length - 1]; // gets the card from the top of the stock pile

        // makes new stock pile without the top card
        String[] newStockPile = Arrays.copyOf(this.stockPile, this.stockPile.length - 1);
        this.stockPile = newStockPile;

        return newCard;
    }

    // Method is used when user wants to draw a new card from discard pile
    public String drawFromDiscard(String card) {

        String newCard = this.discardPile[this.numberOfDiscardedCards]; // get the top card of discard pile
        this.discardPile[this.numberOfDiscardedCards] = card; // over write top card with argument
        return newCard;
    }

    // Method is used to get the icon of a card
    public ImageIcon getIconOf(String card) {

        int indexOfCard = this.getIndexOf(card); // (4)
        return this.cardImages[indexOfCard];
    }

    // Method is used to get the value of a card
    public int getValueOf(String card) {

		int value = Integer.parseInt(card.substring(1));

		if (value > 10) {

			value = 11;
		}

		return value;
    }

    // Method is used for when the game starts and the player are given cards without having to discard cards
    public String[] drawStartingCards() {

        String[] startingCards = new String[3];
        for (int i = 0; i < startingCards.length; i++) {

            startingCards[i] = this.stockPile[this.stockPile.length - (i + 1)];
        }

        // makes new stock pile without the top 3 cards
        String[] newStockPile = Arrays.copyOf(this.stockPile, this.stockPile.length - 3);
        this.stockPile = newStockPile;

        return startingCards;
    }

    // Method is used to check if discard pile is empty
    public boolean isDiscardPileEmpty() {

        return (this.numberOfDiscardedCards == 0);
    }

    // Method is used to check if stock pile is empty
    public boolean isStockPileEmpty() {

        return (this.stockPile.length == 0);
    }

    // Method is used by the computer for choosing which pile to draw from card
    public String peekTopOfDiscard() {

        if (!this.isDiscardPileEmpty()) {

            return this.discardPile[this.numberOfDiscardedCards];
        }
        else {

            return null;
        }
    }
}