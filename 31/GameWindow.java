import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JFrame implements ActionListener {

    /* Fields */

    private JButton knockButton; // button for when the player wants to knock
    private JButton stockPileButton; // button for when the player wants to draw card from stock pile
    private JButton discardPileButton; // button for when the player wants to draw card from discard pile
    private JButton[] playerCardButtons; // the buttons that are visible when the player chooses which card to discard

    private JLabel[] computerCardLabels; // the labels that display the cards of the computer
    private JLabel[] playerCardLabels; // the labels that display the cards of the player
    private JLabel statusMessage; // tells what computer does and when it is the player's turn

    private Player computer; // the object representing the computer
    private Player player; // the object representing the player

    private ImageIcon backOfCardImage; // the image for the back of a card

    private Color colorOfBoard; // the color the board

    private boolean isDrawingFromStock; // remembers which pile to draw a card from

    /* Constructor */

    public GameWindow() {

        super("31");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.backOfCardImage = new ImageIcon("back_of_card.png");
        this.colorOfBoard = Color.decode("#35654d");
        this.isDrawingFromStock = false; // starting value, means nothing yet
        this.player = new Player(false);
        this.computer = new Player(true);
        
        this.generatePlayerComponents();
        this.generateOtherComponents();
        this.addAllComponentsToView();
    }

    /* Internal */

    // Method creates all things that the player will use in a game of 31
    private void generatePlayerComponents() {

        ImageIcon[] imagesOfCardsOnHand = this.getOnHandCardImages(this.player);
        this.playerCardLabels = new JLabel[3];
        this.generatImageLabels(this.playerCardLabels, imagesOfCardsOnHand);
        this.makeButtons();
    }

    // Method makes and returns an array of the images of the cards on a player's/ computer's hand
    private ImageIcon[] getOnHandCardImages(Player p) {

        ImageIcon[] imagesOfCardsOnHand = new ImageIcon[3];
        for (int i = 0; i < imagesOfCardsOnHand.length; i++) {

            imagesOfCardsOnHand[i] = p.getImageIconOf(i);
        }

        return imagesOfCardsOnHand;
    }

    // Method sets a list of labels to be a list of only labels with images
    private void generatImageLabels(JLabel[] cardLabels, ImageIcon[] cardImages) {

        for (int i = 0; i < cardLabels.length; i++) {

            cardLabels[i] = new JLabel(cardImages[i]);
        }
    }

    // Method makes all the buttons
    private void makeButtons() {

        // create and set up the buttons that are visible most of the time
		this.knockButton = new JButton("knock");
		this.knockButton.addActionListener(this);

		this.stockPileButton = new JButton(this.backOfCardImage);
		this.stockPileButton.setBackground(this.colorOfBoard);
		this.stockPileButton.addActionListener(this);

		this.discardPileButton = new JButton();
		this.discardPileButton.addActionListener(this);
		this.discardPileButton.setBackground(this.colorOfBoard);
		this.discardPileButton.setVisible(false); // discard pile should not be visible when empty

        // create the buttons that are visible when player chooses which card to discard
        ImageIcon[] imagesOfCardsOnHand = this.getOnHandCardImages(this.player);
        this.playerCardButtons = new JButton[3];
        for (int i = 0; i < this.playerCardButtons.length; i++) {

            this.playerCardButtons[i] = new JButton(imagesOfCardsOnHand[i]);
            this.playerCardButtons[i].addActionListener(this);
            this.playerCardButtons[i].setBackground(this.colorOfBoard);
            this.playerCardButtons[i].setVisible(false); // card buttons should only be visible when discarding a card
        }
    }

    // Method makes all the components that the player can't interact with
    private void generateOtherComponents() {

        // create the status message
        this.statusMessage = new JLabel("Your sum is: " + this.player.getSumOfCards());

        // create the image labels for the cards of the computer, because its cards should be hidden from the player
        // the cards will only be displayed as the from the back, till someone knocks
        this.computerCardLabels = new JLabel[3];
        ImageIcon[] backOfCardImageList = {this.backOfCardImage, this.backOfCardImage, this.backOfCardImage};
        this.generatImageLabels(this.computerCardLabels, backOfCardImageList);
    }

    // Method adds all components to the container/ view
    private void addAllComponentsToView() {

		Container contentArea = getContentPane();
		contentArea.setBackground(this.colorOfBoard);

		// add some of the buttons to view
		contentArea.add("West", this.discardPileButton);
		contentArea.add("East", this.stockPileButton);
		contentArea.add("South", this.knockButton);

		// make section for status message, and add it to view
		JPanel messageSection = new JPanel();
		messageSection.add(this.statusMessage);
		contentArea.add("North", messageSection);

        // add all card labels to view, and set the view
        this.addCardLabels(contentArea);
        setContentPane(contentArea);
    }

	// Method is used to add the card labels to the window.
	private void addCardLabels(Container container) {

		// the entire section for the card labels to be put in
		JPanel cardSection = new JPanel(new BorderLayout());
		cardSection.setBackground(this.colorOfBoard);

		// space for the computer's cards (labels)
		JPanel computerSubsection = new JPanel();
		computerSubsection.setBackground(this.colorOfBoard);

		// add the card labels to window
		for (int i = 0; i < this.computerCardLabels.length; i++) {

			computerSubsection.add(this.computerCardLabels[i]);
		}

		// space for the player's cards (labels)
		JPanel playerSubsection = new JPanel();
		playerSubsection.setBackground(this.colorOfBoard);

		// add the card labels to window
		for (int i = 0; i < this.playerCardLabels.length; i++) {

			playerSubsection.add(this.playerCardLabels[i]);
			playerSubsection.add(this.playerCardButtons[i]);
		}

		// add subsections for computer and for player to entire section
		// add  entire section to window
		cardSection.add(computerSubsection, BorderLayout.NORTH);
		cardSection.add(playerSubsection, BorderLayout.SOUTH);
		container.add("Center", cardSection);
	}

    // Method makes the pile buttons invisible and "turns the card labels into card buttons"
	private void hideLabelsAndShowButtons() {

		this.stockPileButton.setVisible(false);
		this.discardPileButton.setVisible(false);

		for (int i = 0; i < this.playerCardLabels.length; i++) {

			this.playerCardLabels[i].setVisible(false);
			this.playerCardButtons[i].setVisible(true);
		}
	}

    // Method is used to exchange a card with either stock pile or discard pile
    private void discardCard(int indexOfDiscardedCard) {

    	ImageIcon imageOfDiscardedCard = player.getImageIconOf(indexOfDiscardedCard);
        if (this.isDrawingFromStock) {

            this.player.drawCardFromStock(indexOfDiscardedCard);
        }
        else {

            this.player.drawCardFromDiscard(indexOfDiscardedCard);
        }

        this.alterView(imageOfDiscardedCard);
    }

    private void alterView(ImageIcon icon) {

        this.generatePlayerComponents();
        this.generateOtherComponents();
    	this.discardPileButton.setVisible(true);
        this.discardPileButton = new JButton(icon);
        this.discardPileButton.addActionListener(this);
        this.discardPileButton.setBackground(this.colorOfBoard);
        getContentPane().removeAll();
        this.addAllComponentsToView();
    }

    private void initiateComputerMove() {

        if (!(this.computer.getSumOfCards() > 26 && this.computer.getSumOfCards() < 32)) {

            ImageIcon imageOfDiscardedCard = this.computer.makeMove();
            this.alterView(imageOfDiscardedCard);
        }
        else {

        }
    }

    /* User Interface */

	// method is used to simulate whatever happens when player has
	// clicked a button.
	@Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == this.stockPileButton) {

            this.isDrawingFromStock = true;
            this.hideLabelsAndShowButtons();
        }
        else if (event.getSource() == this.discardPileButton) {

            this.isDrawingFromStock = false;
            this.hideLabelsAndShowButtons();
        }
        else {

            for (int indexOfDiscardedCard = 0; indexOfDiscardedCard < this.playerCardButtons.length; indexOfDiscardedCard++) {

                if (event.getSource() == this.playerCardButtons[indexOfDiscardedCard]) {

                    this.discardCard(indexOfDiscardedCard);
                    break;
                }
            }
           
            // this.initiateComputerMove();
        }
    }
}
