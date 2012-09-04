package cs3744.homework4.controllers;

import cs3744.model.Timestamp;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import cs3744.gui.events.interfaces.IActionEvent;
import cs3744.gui.interfaces.IButton;
import cs3744.gui.interfaces.ITextField;
import cs3744.homework4.HomeworkFour;
import cs3744.homework4.controllers.interfaces.IGameScreenController;
import cs3744.homework4.views.GameScreen;
import cs3744.model.events.interfaces.ICollectionChangeEvent;
import cs3744.model.exceptions.CollectionChangeVetoException;
import cs3744.model.exceptions.DataChangeVetoException;
import cs3744.model.interfaces.IGame;
import cs3744.model.interfaces.IGameLobby;
import cs3744.model.interfaces.IGamePiece;
import cs3744.model.interfaces.IMessage;
import cs3744.model.interfaces.IMessageChannel;
import cs3744.model.interfaces.IPlayer;

/**
 * The <CODE>GameScreenController</CODE> provides a reference implementation of
 * the <CODE>IGameScreenController</CODE> interface.
 *
 * @author Peter J. Radics
 * @version 1.0
 */
public class GameScreenController
    implements IGameScreenController
{

    private final GameScreen                gameScreen;

    private int                             numberOfGamePieces;

    private final HomeworkFour              owner;
    private final IPlayer                   player;

    private final IGame                     game;
    private final List<GamePieceController> gamePieceControllers;


    /**
     * Returns the game screen (the view) associated with this controller.
     *
     * @return the view.
     */
    protected GameScreen getGameScreen()
    {

        return this.gameScreen;
    }


    /**
     * Constructs a new <CODE>GameScreenController</CODE>.
     *
     * @param mainThread
     *            the owning thread of this controller.
     * @param game
     *            the game.
     * @param player
     *            the player.
     */
    public GameScreenController(
        HomeworkFour mainThread,
        IGame game,
        IPlayer player)
    {

        this.gamePieceControllers = new ArrayList<GamePieceController>();
        this.owner = mainThread;
        this.gameScreen = new GameScreen(this.owner.getConfiguration());

        this.gameScreen.getMessageTextField().addActionListener(this);
        this.gameScreen.getSendButton().addActionListener(this);
        this.gameScreen.getAddGamePieceButton().addActionListener(this);

        this.game = game;
        this.game.addCollectionChangeListener(this);
        this.game.getMessageChannel().addCollectionChangeListener(this);

        IPlayer somePlayer;
        Iterator<IPlayer> iterator = game.getPlayers().iterator();

        while (iterator.hasNext())
        {

            somePlayer = iterator.next();
            StringBuilder textToAdd = new StringBuilder();
            textToAdd.append(new Timestamp());
            textToAdd.append(": >>");
            textToAdd.append(somePlayer);
            textToAdd.append(" has joined the Game.<<");
            gameScreen.getTextArea().appendText(textToAdd.toString());
            gameScreen.addPlayer(somePlayer.toString());
        }

        this.player = player;
        numberOfGamePieces = 0;

    }


    /*
     * (non-Javadoc)
     * @see
     * cs3744.homework4.controllers.solution.IGameScreenController#displayView()
     */
    @Override
    public void displayView()
    {

        this.gameScreen.setVisible(true);
    }


    /*
     * (non-Javadoc)
     * @see
     * cs3744.homework4.controllers.solution.IGameScreenController#hideView()
     */
    @Override
    public void hideView()
    {

        this.gameScreen.setVisible(false);
    }


    /*
     * (non-Javadoc)
     * @see
     * cs3744.homework4.controllers.solution.IGameScreenController#disposeOfView
     * ()
     */
    @Override
    public void disposeOfView()
    {

        this.gameScreen.dispose();
    }


    @Override
    public void elementAdded(ICollectionChangeEvent event)
    {

        Object source = event.getSource();
        String collectionName = event.getCollectionName();
        Object causeOfChange = event.getCauseOfChange();

        if (source instanceof IGame)
        {
            if ("gamePieces".equals(collectionName))
            {
                if (causeOfChange instanceof IGamePiece)
                {
                    IGamePiece gamePiece = (IGamePiece)causeOfChange;

                    GamePieceController someController =
                        new GamePieceController(
                            gamePiece,
                            this.gameScreen,
                            this.gameScreen.getIsometricView()
                                .getGameBoardType(),
                            this.owner.getConfiguration());
                    this.gameScreen.getIsometricView().getGameBoard()
                        .addGamePiece(someController.getView());

                    GamePieceController topController =
                        new GamePieceController(
                            gamePiece,
                            this.gameScreen,
                            this.gameScreen.getTopView().getGameBoardType(),
                            this.owner.getConfiguration());
                    this.gameScreen.getTopView().getGameBoard()
                        .addGamePiece(topController.getView());

                    this.gamePieceControllers.add(someController);
                    this.gamePieceControllers.add(topController);
                }
            }
        }

        else if ("players".equals(collectionName))
        {
            if (causeOfChange instanceof IPlayer)
            {

                IGamePiece gamePiece = (IGamePiece)causeOfChange;
                GamePieceController someController =
                    new GamePieceController(
                        gamePiece,
                        gameScreen,
                        gameScreen.getIsometricView().getGameBoardType(),
                        owner.getConfiguration());
                gameScreen.getIsometricView().getGameBoard()
                    .addGamePiece(someController.getView());
                GamePieceController topController =
                    new GamePieceController(
                        gamePiece,
                        gameScreen,
                        gameScreen.getTopView().getGameBoardType(),
                        owner.getConfiguration());
                gameScreen.getTopView().getGameBoard()
                    .addGamePiece(topController.getView());
                gamePieceControllers.add(someController);
                gamePieceControllers.add(topController);
            }

        }
        else if (source instanceof IMessageChannel)
        {
            if (collectionName.equals("messages"))
            {
                if (causeOfChange instanceof IMessage)
                {

                    IMessage message = (IMessage)causeOfChange;
                    gameScreen.getTextArea().appendText(message.toString());
                }
            }

        }

    }


    @Override
    public void elementRemoved(ICollectionChangeEvent event)
    {

        Object source = event.getSource();
        String collectionName = event.getCollectionName();
        Object causeOfChange = event.getCauseOfChange();

        if (source instanceof IGameLobby)
        {
            if (collectionName.equals("players"))
            {
                if (causeOfChange instanceof IPlayer)
                {

                    IPlayer somePlayer = (IPlayer)causeOfChange;
                    gameScreen.removePlayer(somePlayer.toString());
                    StringBuilder textToAdd = new StringBuilder();
                    textToAdd.append(new Timestamp());
                    textToAdd.append(":>>");
                    textToAdd.append(somePlayer);
                    textToAdd.append(" has left the Game.<<");
                    gameScreen.getTextArea().appendText(textToAdd.toString());
                }
            }
        }
    }


    @Override
    public void collectionCleared(ICollectionChangeEvent event)
    {

        // Nothing to do
    }


    @Override
    public void actionPerformed(IActionEvent actionEvent)
    {

        if (actionEvent.getSource() instanceof ITextField)
        {

            String text = gameScreen.getMessageTextField().getText();
            if (!text.equals("") && !text.equals("\n"))
                sendMessage(text);
        }
        else if (actionEvent.getSource() instanceof IButton)
        {

            IButton sourceButton = (IButton)actionEvent.getSource();

            if (sourceButton.equals(this.gameScreen.getSendButton()))
            {

                String text = gameScreen.getMessageTextField().getText();
                if (!text.equals("") && !text.equals("\n"))
                    sendMessage(text);

            }
            else if (sourceButton.equals(this.gameScreen
                .getAddGamePieceButton()))
            {

                createGamePiece();
            }

        }

    }



    private void sendMessage(String text)
    {

        IMessage message = this.owner.createMessage(this.player, text);

        this.gameScreen.getMessageTextField().setText("");

        try
        {
            this.game.getMessageChannel().addMessage(message);
        }
        catch (CollectionChangeVetoException e)
        {
            System.out.println("Message got vetoed in controller: "
                + e.toString());
        }
    }



    private void createGamePiece()
    {

        IGamePiece gamePiece =
            this.owner.createGamePiece(this.player, this.game);

        try
        {
            int gamePieces = 0;

            gamePieces = this.numberOfGamePieces;

            gamePiece.setPosition(gamePieces);
            this.game.addGamePiece(gamePiece);

            this.numberOfGamePieces++;

        }
        catch (DataChangeVetoException e)
        {
            System.out.println(e);
        }
        catch (CollectionChangeVetoException e)
        {
            System.out.println(e);
        }
    }

}
