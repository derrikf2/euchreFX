package application.view;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import application.Game;
import application.GameServer;
import application.Suit;
import javafx.scene.control.ButtonBar.ButtonData;

/**
 * <h1>GamePlayController</h1>
 * <p>
 * The controller class for the GameView.fxml Some gameplay control logic is
 * found in this class, It works in congruence with the instantiated Game object
 * to controll the game play.
 *
 * @author Derrik Fleming
 * @author Josh T
 * @author Jarret Swales
 * @version 1.0
 * @since 2016 Fall
 */
public class GameViewController implements Initializable {

    /**
     * Used to specify index 0 in hand.
     */
    private static final int INDEX_0 = 0;

    /**
     * Used to specify index 1 in hand.
     */
    private static final int INDEX_1 = 1;

    /**
     * Used to specify index 2 in hand.
     */
    private static final int INDEX_2 = 2;

    /**
     * Used to specify index 3 in hand.
     */
    private static final int INDEX_3 = 3;

    /**
     * Used to specify index 4 in hand.
     */
    private static final int INDEX_4 = 4;

    /**
     * Used to specify CHOICE 0 in hand.
     */
    private static final int CHOICE_1 = 1;

    /**
     * Used to specify CHOICE 1 in hand.
     */
    private static final int CHOICE_2 = 2;

    /**
     * Used to specify CHOICE 2 in hand.
     */
    private static final int CHOICE_3 = 3;

    /**
     * Used to specify CHOICE 3 in hand.
     */
    private static final int CHOICE_4 = 4;

    /**
     * Used to specify CHOICE 4 in hand.
     */
    private static final int CHOICE_5 = 5;

    /**
     * Maximum hand size.
     */
    private static final int MAX_HAND_SIZE = 5;

    /**
     * Maximum played cards.
     */
    private static final int MAX_PLAYED_CARDS = 4;

    /**
     * This button deals the next hand once the current hand is over.
     */
    @FXML
    private Button dealButton;

    /**
     * To pass on trump call.
     */
    @FXML
    private Button nextButton;

    /**
     *     */
    @FXML
    private Button optionsButton;

    /**
     * This is where the overall game score is displayed.
     */
    @FXML
    private Label gameScoreLabel;

    /**
     * This is where the current round score is displayed.
     */
    @FXML
    private Label roundScoreLabel;

    /**
     * This is where the Game Status will be displayed.
     */
    @FXML
    private Label gameStatus;

    /**
     * This is where the players hand will be displayed.
     */
    @FXML
    private Rectangle card1, card2, card3, card4, card5;

    /**
     *      */
    private ArrayList<Rectangle> cardSlots = new ArrayList<Rectangle>();

    /**
     * This is where each player's played card for the hand will appear.
     */
    @FXML
    private Rectangle pPlayedCard, a1PlayedCard, a2PlayedCard, a3PlayedCard;

    /**
     *      */
    private ArrayList<Rectangle> playedCardSlots = new ArrayList<Rectangle>();

    /**
     * The remainder of the deck after dealing.
     */
    @FXML
    private Rectangle deck;

    /**
     * AI players' hands the image of a card back will be shown.
     */
    @FXML
    private Rectangle a1Hand, a2Hand, a3Hand;

    /**
     * This is where a string will be displayed representing cards left.
     */
    @FXML
    private Label a1CardsLeft, a2CardsLeft, a3CardsLeft;

    /**
     * The game object.
     */
    private Game game;

    /**
     * The image of the face up Card.
     */
    private Image upCard;

    /**
     * ImagePattern used for setting upCard to Rectangle deck.
     */
    private ImagePattern upCardPattern;

    /**
     * ImagePattern for the back of a card,
     */
    private ImagePattern cardBack;

    /**
     * The score of the game.
     */
    private int score;
    
    /**
     * The number player you are.
     */
    private int playerNum;
    
    /**
     * The socket used to connect to the server.
     */
    private Socket serverSocket;
    
    /**
     * The stream used to send data to the server.
     */
    private ObjectOutputStream outToServer;
    
    /**
     * The stream used to receive data from the server.
     */
    private ObjectInputStream inFromServer;


    @Override
    public final void initialize(final URL location,
            final ResourceBundle resources) {
        cardSlots.add(card1);
        cardSlots.add(card2);
        cardSlots.add(card3);
        cardSlots.add(card4);
        cardSlots.add(card5);
        playedCardSlots.add(pPlayedCard);
        playedCardSlots.add(a1PlayedCard);
        playedCardSlots.add(a2PlayedCard);
        playedCardSlots.add(a3PlayedCard);
        nextButton.setDisable(true);
    }
    
    /**
     * This method is called when the start game button is clicked, and starts a
     * new game.
     *
     * @param event An event from the user on the start button.
     */
    public final void startGame(final ActionEvent event) {
        dealButton.setDisable(true);
        cardBack = new ImagePattern(new Image("application/view/images/cardBack.jpg"));

        a1Hand.setFill(cardBack);
        a2Hand.setFill(cardBack);
        a3Hand.setFill(cardBack);
        
        serverSocket = null;

        game = new Game();

        upCardPattern = new ImagePattern(game.getUpCard().getFaceImage());
        deck.setFill(upCardPattern);
        
        playerNum = 0;
        
        refresh();
        buildTrumpr1Dialog();
    }
    
    /**
     * Starts a multiplayer game.
     */
    public final void startMultiplayerGame() {
    	dealButton.setDisable(true);
        cardBack = new ImagePattern(new Image("application/view/images/cardBack.jpg"));

        a1Hand.setFill(cardBack);
        a2Hand.setFill(cardBack);
        a3Hand.setFill(cardBack);

        game = new Game();

        upCardPattern = new ImagePattern(game.getUpCard().getFaceImage());
        deck.setFill(upCardPattern);

        refresh();
        buildTrumpr1Dialog();
    }
    
    /**
     * Method is called when create game button is pressed.
     * It creates a GameServer object and establishes a client connection.
     * 
     */
    public final void createGame() {
    	TextInputDialog diologIP = new TextInputDialog();
    	diologIP.setTitle("IP Address");
    	diologIP.setHeaderText("Game Server Launch");
    	diologIP.setContentText("Please enter your IP address:");
    	
    	Optional<String> enteredIP = diologIP.showAndWait();
    	if(enteredIP.isPresent()){
    		String IP = enteredIP.get();
    		try {
    			new GameServer();
    			serverSocket = new Socket(IP, 9878);
    	    	outToServer = new ObjectOutputStream(serverSocket.getOutputStream());
    	    	inFromServer = new ObjectInputStream(serverSocket.getInputStream());
    	    
    	    	// wait for other player to join game.
    	    	int connected = inFromServer.read();
    	    	
    	    	playerNum = 0;
    	    	startMultiplayerGame();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else {
    		twoPlayerDialog();
    		return;
    	}
    }
    
    /**
     * Method is called when join game button is pressed.
     * It establishes the other client connection to the server.
     * 
     */
    public final void joinGame() {
    	TextInputDialog dialogIP = new TextInputDialog();
    	dialogIP.setTitle("IP Address");
    	dialogIP.setHeaderText("Connect To Game Server");
    	dialogIP.setContentText("Please enter host player's IP address:");
    	
    	Optional<String> enteredIP = dialogIP.showAndWait();
    	if(enteredIP.isPresent()){
    		String IP = dialogIP.getEditor().toString();
    		try {
    			try {
    				serverSocket = new Socket(IP, 9878);
    			} catch(IOException e) {
    				
    			}
    			outToServer = new ObjectOutputStream(serverSocket.getOutputStream());
    	    	inFromServer = new ObjectInputStream(serverSocket.getInputStream());
    	    	/** Let other human know you've joined. */
    	    	outToServer.write(1);
    	    	playerNum = 2;
    	    	startMultiplayerGame();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	} else {
    		twoPlayerDialog();
    		return;
    	}	
    }

    /**
     * This method is called when the next round button is clicked. It starts
     * the next round.
     *
     * @param event An event from the user on the next round button.
     */
    public final void nextRound(final ActionEvent event) {
        nextButton.setDisable(true);
        game.clearTable();
        refresh();
        nextPlayerSingle();
    }

    /**
     * This method is called whenever a card in the user's hand is clicked on.
     * It plays the card that the user clicked on.
     *
     * @param event An event from the user on users Cards displayed.
     */
    public final void playThisCard(final MouseEvent event) {
        if (game.getTurn() == playerNum) {
            if (event.getSource() == card1) {
                game.playCard(game.getPlayerHand(playerNum), INDEX_0);
            } else if (event.getSource() == card2) {
                game.playCard(game.getPlayerHand(playerNum), INDEX_1);
            } else if (event.getSource() == card3) {
                game.playCard(game.getPlayerHand(playerNum), INDEX_2);
            } else if (event.getSource() == card4) {
                game.playCard(game.getPlayerHand(playerNum), INDEX_3);
            } else {
                game.playCard(game.getPlayerHand(playerNum), INDEX_4);
            }
            refresh();
            
            nextPlayerSingle();
        }
    }

    /**
     * This method is called after the user plays a card in a single player
     * game. It determines whether the round has ended and sets the appropriate
     * turn and button configuration.
     */
    public final void nextPlayerSingle() {
        if (game.roundComplete()) {
            if (game.getPlayerHand(0).get(0) == null) {
                nextButton.setDisable(true);
                dealButton.setDisable(false);
                game.scoreRound();
                score += game.getGameScore();
            } else {
                nextButton.setDisable(false);
                dealButton.setDisable(true);
            }
            game.updateScore();
            refresh();
        } else if (game.getTurn() > 0) {
            game.playCardAI(game.getTurn());
            refresh();
            nextPlayerSingle();
        }
    }

    /**
     * This method is called after the user plays a card in a multiplayer game.
     * It determines whether the round has ended and sets the appropriate turn
     * and button configuration.
     */
    public final void nextPlayerDouble() {
    	if (game.roundComplete()) {
            if (game.getPlayerHand(0).get(0) == null) {
                nextButton.setDisable(true);
                dealButton.setDisable(false);
                game.scoreRound();
                score += game.getGameScore();
            } else {
                nextButton.setDisable(false);
                dealButton.setDisable(true);
            }
            game.updateScore();
            
            try {
				outToServer.writeObject(game);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            refresh();
        } else if (game.getTurn() > 0) {
            game.playCardAI(game.getTurn());
            try {
				outToServer.writeObject(game);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            refresh();
            nextPlayerDouble();
        }
    }

    /**
     * This method is used to refresh the table top to reflect the Game object,
     * after game events occur.
     */
    public final void refresh() {
        // update score
    	gameScoreLabel.setText("Game Score: " + score);
        
    	// the face image of the card
        Image card;
        
        // displays user's hand in the card slots located at the bottom of window
        // when playernum == 0 user host's hand displayed
        // when playernum == 2 user client's hand displayed
        for (int i = 0; i < MAX_HAND_SIZE; i++) {
            if (game.getPlayerHand(playerNum).get(i) != null) {
                card = game.getPlayerHand(playerNum).get(i).getFaceImage();
                ImagePattern imagePattern = new ImagePattern(card);
                cardSlots.get(i).setFill(imagePattern);
            } else {
                cardSlots.get(i).setFill(null);
            }
        }
        
        // face images of cards in play
        Image playedCard;
        
        // update played cards on table
        for (int i = 0; i < MAX_PLAYED_CARDS; i++) {
            if (game.getPlayedCard(i) != null) {
                playedCard = game.getPlayedCard(i).getFaceImage();
                ImagePattern imagePattern = new ImagePattern(playedCard);
                // host
                if (playerNum == INDEX_0) {
                	playedCardSlots.get(i).setFill(imagePattern);
                }
                
                // client
                if (playerNum == INDEX_2) {
                	if (i == 0 || i == 1) {
	                	playedCardSlots.get(i + INDEX_2).setFill(imagePattern);
	                }
                	
	                if (i == 2 || i == 3) {
	                	playedCardSlots.get(i - INDEX_2).setFill(imagePattern);
	                }
                }
            } else {
            	// empty card slots in host's hand 
            	if (playerNum == INDEX_0) {
            		playedCardSlots.get(i).setFill(null);
            	}
            	// empty card slots in client's hand
            	if (playerNum == INDEX_2) {
            		if (i == 0 || i == 1) {
	                	playedCardSlots.get(i + INDEX_2).setFill(null);
	            	}
	                if (i == 2 || i == 3) {
	                	playedCardSlots.get(i - INDEX_2).setFill(null);
	                }
            	}
            }
        }
	    
        // cards in hand count from host perspective
        if(playerNum == INDEX_0) {
	        a1CardsLeft.setText("Cards Left: "
	                + game.getPlayerHand(INDEX_1).getSize());
	        a2CardsLeft.setText("Cards Left: "
	                + game.getPlayerHand(INDEX_2).getSize());
	        a3CardsLeft.setText("Cards Left: "
	                + game.getPlayerHand(INDEX_3).getSize());
        }
        
        // cards in hand count from client perspective
        if(playerNum == INDEX_2) {
	        a1CardsLeft.setText("Cards Left: "
	                + game.getPlayerHand(INDEX_3).getSize());
	        a2CardsLeft.setText("Cards Left: "
	                + game.getPlayerHand(INDEX_0).getSize());
	        a3CardsLeft.setText("Cards Left: "
	                + game.getPlayerHand(INDEX_1).getSize()); 
        }

        if (game.getTrump() != null) {
            deck.setFill(cardBack);
        } else {
            deck.setFill(upCardPattern);
        }
        roundScoreLabel.setText("Us: " + game.getScore1()
                + " Them: " + game.getScore2());
    }
    
    /**
     * This method is called when the user presses the options button. It will
     * launch a dialog to set the difficulty of the AI or attempt to start a
     * multiplayer game.
     *
     * @param event An event from the user on the options button.
     */
    public final void optionButton(final ActionEvent event) {
        Alert options = new Alert(AlertType.CONFIRMATION);
        Image icon = new Image("application/view/images/alertIcon.png");
        ImageView iconImage = new ImageView(icon);
        ButtonType difficultyButton = new ButtonType("Difficulty");
        ButtonType twoPlayerButton = new ButtonType("Two Player");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        
        options.setGraphic(iconImage);
        options.setTitle("Options");
        options.setHeaderText("Change AI difficulty or start Multiplayer");
        options.setContentText("Choose an option.");
        options.getButtonTypes().setAll(difficultyButton, twoPlayerButton, cancelButton);

        Optional<ButtonType> result = options.showAndWait();
        if (result.get() == difficultyButton) {
            // TODO
        }
        if (result.get() == twoPlayerButton) {
        	twoPlayerDialog();
        }
        if (!result.isPresent()) {
        	
        }
    }
    
    /**
     * 
     */
    public final void twoPlayerDialog () {
    	Alert twoPlayer = new Alert(AlertType.CONFIRMATION);
    	Image icon = new Image("application/view/images/alertIcon.png");
        ImageView iconImage = new ImageView(icon);
        ButtonType joinGame = new ButtonType("Join Game");
        ButtonType createGame = new ButtonType("Create Game");
        
        twoPlayer.setGraphic(iconImage);
        twoPlayer.setTitle("Join/Create");
        twoPlayer.setHeaderText("Launch Server or Connect");
        twoPlayer.setContentText("Join Game to connect to a server or"
        		+  " Create Game to launch game server and wait for "
        		+ "teammate.");
        twoPlayer.getButtonTypes().setAll(joinGame, createGame);
        
        Optional<ButtonType> result = twoPlayer.showAndWait();
        if(result.get() == joinGame) {
        	joinGame();
        }
        if(result.get() == createGame) {
        	createGame();
        }
        refresh();
    }

    /**
     *
     */
    public final void buildTrumpr1Dialog() {
        Alert r1 = new Alert(AlertType.CONFIRMATION);
        Image icon = new Image("application/view/images/alertIcon.png");
        ImageView iconImage = new ImageView(icon);
        ButtonType orderIt = new ButtonType("Order it up");
        ButtonType pass = new ButtonType("Pass");
        
        r1.setTitle("Select Trump: Round 1");
        r1.setHeaderText("Order it up or turn it down: "
                + game.getUpCard().toString());
        r1.setGraphic(iconImage);
        r1.getButtonTypes().setAll(orderIt, pass);

        Optional<ButtonType> result = r1.showAndWait();
        if (result.get() == orderIt) {
            buildCardSwapDialog();
        }
        if (result.get() == pass) {
            game.orderUpAI(game.getPlayerHand(game.getTurn()),
                    game.getUpCard());
            if (game.getTrump() == null) {
                game.orderUpAI(game.getPlayerHand(game.getTurn() + 1),
                        game.getUpCard());
                if (game.getTrump() == null) {
                    game.orderUpAI(game.getPlayerHand(game.getTurn() + 2),
                            game.getUpCard());
                }
            }
            if (game.getTrump() == null) {
                buildTrumpr2Dialog();
            }
        }
        gameStatus.setText("Trump is: " + game.getTrump());
        refresh();
    }
    
    /**
    *
    */
   public final void buildTrumpr2Dialog() {
       Alert r2 = new Alert(AlertType.CONFIRMATION);
       Image icon = new Image("application/view/images/alertIcon.png");
       ImageView iconImage = new ImageView(icon);
       ButtonType spades = new ButtonType("Spades");
       ButtonType clubs = new ButtonType("Clubs");
       ButtonType hearts = new ButtonType("Hearts");
       ButtonType diamonds = new ButtonType("Diamonds");
       ButtonType pass = new ButtonType("Pass");
       
       r2.setTitle("Select Trump: Round 2");
       r2.setHeaderText("All players have passed! Select trump suit or pass");
       r2.setGraphic(iconImage);
       r2.getButtonTypes().setAll(spades, clubs, hearts, diamonds, pass);

       Optional<ButtonType> result = r2.showAndWait();
       if (result.get() == pass) {
           game.selectTrumpAI(game.getPlayerHand(INDEX_1));
           if (game.getTrump() == null) {
               game.selectTrumpAI(game.getPlayerHand(INDEX_2));
               if (game.getTrump() == null) {
                   game.selectTrumpAI(game.getPlayerHand(INDEX_3));
               }
           }
       } else if (result.get() == spades) {
           game.setTrump(Suit.SPADES);
       } else if (result.get() == clubs) {
           game.setTrump(Suit.CLUBS);
       } else if (result.get() == hearts) {
           game.setTrump(Suit.HEARTS);
       } else if (result.get() == diamonds) {
           game.setTrump(Suit.DIAMONDS);
       }
       refresh();
   }

    /**
     *
     */
    public final void buildCardSwapDialog() {
        List<String> choices = new ArrayList<>();
        choices.add("Select a card to replace");
        choices.add(game.getPlayerHand(0).get(INDEX_0).toString());
        choices.add(game.getPlayerHand(0).get(INDEX_1).toString());
        choices.add(game.getPlayerHand(0).get(INDEX_2).toString());
        choices.add(game.getPlayerHand(0).get(INDEX_3).toString());
        choices.add(game.getPlayerHand(0).get(INDEX_4).toString());
        
        ChoiceDialog<String> r1s
                = new ChoiceDialog<>("Select a card to replace", choices);
        r1s.setTitle("Order Up Confirmation");
        r1s.setHeaderText("Replacing with: " + game.getUpCard().toString());
        Optional<String> result = r1s.showAndWait();
        if (result.isPresent()) {
            game.setTrump(game.getUpCard().getSuit());

            if (result.get().equals(choices.get(CHOICE_1))) {
                game.getPlayerHand(0).set(INDEX_0, game.getUpCard());
            } else if (result.get().equals(choices.get(CHOICE_2))) {
                game.getPlayerHand(0).set(INDEX_1, game.getUpCard());
            } else if (result.get().equals(choices.get(CHOICE_3))) {
                game.getPlayerHand(0).set(INDEX_2, game.getUpCard());
            } else if (result.get().equals(choices.get(CHOICE_4))) {
                game.getPlayerHand(0).set(INDEX_3, game.getUpCard());
            } else if (result.get().equals(choices.get(CHOICE_5))) {
                game.getPlayerHand(0).set(INDEX_4, game.getUpCard());
            }
            game.removeUpCard();
            upCardPattern = null;
        }
        refresh();
    }
}
