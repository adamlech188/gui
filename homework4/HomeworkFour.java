package cs3744.homework4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import cs3744.client.GameClientException;
import cs3744.client.HomeworkConfiguration;
import cs3744.client.HomeworkFourGameClient;
import cs3744.homework4.controllers.interfaces.IGameScreenController;
import cs3744.homework4.controllers.interfaces.ILobbyScreenController;
import cs3744.homework4.controllers.interfaces.ILoginScreenController;
import cs3744.model.Timestamp;
import cs3744.model.events.solution.CollectionChangeEchoListener;
import cs3744.model.events.solution.DataChangeEchoListener;
import cs3744.model.exceptions.CollectionChangeVetoException;
import cs3744.model.interfaces.IGame;
import cs3744.model.interfaces.IGameLobby;
import cs3744.model.interfaces.IGamePiece;
import cs3744.model.interfaces.IMessage;
import cs3744.model.interfaces.IMessageChannel;
import cs3744.model.interfaces.IPlayer;

/**
 * This is the main class of homework three. It simulates the use of a game
 * client. Your task is to replace all the solution instances in the import with
 * your own implementation. To that end, you have to implement all the classes
 * that are currently in error.
 *
 * @author Peter J. Radics
 * @version 1.0
 * @see cs3744.model.Lobby
 * @see cs3744.model.Message
 * @see cs3744.model.MessageChannel
 * @see cs3744.model.Player
 * @see cs3744.model.events.CollectionChangeEvent
 * @see cs3744.model.events.CollectionChangeSource
 * @see cs3744.model.events.DataChangeEvent
 * @see cs3744.model.events.DataChangeSource
 * @see cs3744.model.events.VetoableCollectionChangeSource
 * @see cs3744.model.events.VetoableDataChangeSource
 */
public class HomeworkFour
{

    private final HomeworkConfiguration        configuration;
    private final ILoginScreenController       loginScreenController;
    private ILobbyScreenController             lobbyScreenController;
    private IGameScreenController              gameScreenController;

    private final HomeworkFourGameClient       gameClient;
    private final Thread                       clientThread;
    private final CollectionChangeEchoListener collectionEcho;
    private final DataChangeEchoListener       dataEcho;

    private IGame                              game;
    private IPlayer                            player;

    private final Lock                         progressLock;
    private final Condition                    progressCondition;


    /**
     * @return the configuration
     */
    public HomeworkConfiguration getConfiguration()
    {

        return this.configuration;
    }


    /**
     * @return the loginScreenController
     */
    public ILoginScreenController getLoginScreenController()
    {

        return this.loginScreenController;
    }


    /**
     * @return the lobbyScreenController
     */
    public ILobbyScreenController getLobbyScreenController()
    {

        return this.lobbyScreenController;
    }


    /**
     * @return the clientThread
     */
    public Thread getClientThread()
    {

        return this.clientThread;
    }


    /**
     * @return the collectionEcho
     */
    public CollectionChangeEchoListener getCollectionEcho()
    {

        return this.collectionEcho;
    }


    /**
     * @return the dataEcho
     */
    public DataChangeEchoListener getDataEcho()
    {

        return this.dataEcho;
    }


    /**
     * @return returns the progress lock
     */
    public Lock getProgressLock()
    {

        return this.progressLock;
    }


    /**
     * @return returns the progress condition
     */
    public Condition getProgressCondition()
    {

        return this.progressCondition;
    }


    /**
     * @return the game
     */
    public IGame getGame()
    {

        return this.game;
    }


    /**
     * @param game
     *            the game to set
     */
    public void setGame(IGame game)
    {

        this.game = game;
    }


    /**
     * @return the player
     */
    public IPlayer getPlayer()
    {

        return this.player;
    }


    /**
     * @param player
     *            the player to set
     */
    public void setPlayer(IPlayer player)
    {

        this.player = player;
    }


    /**
     *
     */
    public HomeworkFour()
    {

        this.configuration = new HomeworkConfiguration();

        this.loginScreenController =
            new cs3744.homework4.controllers.LoginScreenController(this);
        // TODO: use your own login screen controller
        // this.loginScreenController = new
        // cs3744.homework4.controllers.LoginScreenController(
        // this);

        this.lobbyScreenController = null;

        this.collectionEcho =
            new CollectionChangeEchoListener("Collection Echo");
        this.dataEcho = new DataChangeEchoListener("Data Echo");

        // Initializing the client
        this.gameClient = new HomeworkFourGameClient(this.configuration);
        this.clientThread = new Thread(this.gameClient);

        this.progressLock = new ReentrantLock();
        this.progressCondition = this.progressLock.newCondition();
    }


    /**
     * The main thread of homework one.
     *
     * @param args
     *            Arguments (unused).
     */
    public static void main(String[] args)
    {

        HomeworkFour homework = new HomeworkFour();
        //
        // Uncomment the lines corresponding to the implementations you want to
        // use!
        //
        // // Homework One
        // homework.getConfiguration().setUseStudentMessage(true);
        // homework.getConfiguration().setUseStudentPlayer(true);
        //
        // // Homework Two
        // homework.getConfiguration().setUseStudentGameLobby(true);
        // homework.getConfiguration().setUseStudentGameModel(true);
        // homework.getConfiguration().setUseStudentGamePieceModel(true);
        // homework.getConfiguration().setUseStudentGamePiece2D(true);
        // homework.getConfiguration().setUseStudentFourPlayerBoard(true);
        //
        // // Homework Three
        // homework.getConfiguration().setUseStudentGamePiece3D(true);
        // homework.getConfiguration().setUseStudentFourPlayerBoard3D(true);
        // homework.getConfiguration().setUseStudentCamera(true);
        // homework.getConfiguration().setUseStudentMaterial(true);
        // homework.getConfiguration().setUseStudentLights(true);
        //
        // // Homework Four
        // homework.getConfiguration().setUseStudentLabel(true);
        // homework.getConfiguration().setUseStudentTextField(true);
        // homework.getConfiguration().setUseStudentButton(true);
        // homework.getConfiguration().setUseStudentGridLayout(true);
        // ;
        //
        // // Project

        homework.loginScreenController.displayView();

        homework.getClientThread().start();

        homework.getProgressLock().lock();
        try
        {
            homework.getProgressCondition().await();
        }
        catch (InterruptedException e)
        {

            System.exit(0);
        }

        homework.getProgressLock().unlock();
        homework.login();

        homework.getProgressLock().lock();
        try
        {
            homework.getProgressCondition().await();
        }
        catch (InterruptedException e)
        {

            System.exit(0);
        }

        homework.getProgressLock().unlock();

        homework.setupGame();

    }


    /**
     * Attempts to log in to the server.
     */
    public void login()
    {

        String playerName = this.loginScreenController.getPlayerName();
        if (!"".equals(playerName))
        {

            System.out.println("PlayerName: " + playerName);
            this.loginScreenController.hideView();
            this.loginScreenController.disposeOfView();

        }
        // // Creating the shell of a player (the server will set the stats)
        IPlayer aplayer =
            HomeworkFour.createPlayer(this.configuration, playerName);

        // We want to see when things are changing
        aplayer.addDataChangeListener(this.dataEcho);

        // We will get the lobby and its message channel from the server
        IGameLobby lobby = null;

        IMessageChannel lobbyMessageChannel = null;

        // Connection attempt (that will succeed)
        try
        {
            this.gameClient.connectToServer("localhost", 12345);
        }
        catch (GameClientException e)
        {
            System.out.println(e);
        }

        // Attempting to get the lobby
        try
        {
            lobby = this.gameClient.getGameLobby();
        }
        catch (GameClientException e)
        {
            System.out.println(e);
        }

        if (lobby != null)
        {
            // We want to see what's happening in the lobby
            lobby.addCollectionChangeListener(this.collectionEcho);

            // this.lobbyScreenController = new
// cs3744.homework4.controllers.solution.LobbyScreenController(
            // this, lobby, aplayer);
            // TODO: use your own lobby screen controller!
            this.lobbyScreenController =
                new cs3744.homework4.controllers.LobbyScreenController(
                    this,
                    lobby,
                    aplayer);

            this.getLobbyScreenController().displayView();

            // We add our player to the lobby (successful)
            try
            {
                lobby.addPlayer(aplayer);
            }
            catch (CollectionChangeVetoException e)
            {
                System.out.println(e);
            }

            // Adding our player a second time should be vetoed!
            try
            {
                lobby.addPlayer(aplayer);
            }
            catch (CollectionChangeVetoException e)
            {
                System.out.println(e);
            }

            // We remove the player (because we can)
            try
            {
                lobby.removePlayer(aplayer);
            }
            catch (CollectionChangeVetoException e)
            {
                System.out.println(e);
            }

            // Removing our player a second time should be vetoed!
            try
            {
                lobby.removePlayer(aplayer);
            }
            catch (CollectionChangeVetoException e)
            {
                System.out.println(e);
            }

            // We retrieve the message channel
            lobbyMessageChannel = lobby.getMessageChannel();
        }

        if (lobbyMessageChannel != null)
        {

            // We want to see changes in the message channel
            lobbyMessageChannel
                .addCollectionChangeListener(this.collectionEcho);

            // Re-adding the player should be successful
            try
            {
                lobby.addPlayer(aplayer);
            }
            catch (CollectionChangeVetoException e)
            {
                System.out.println(e);
            }

        }
    }


    /**
     * Attempts to create a game.
     */
    public void setupGame()
    {

        this.lobbyScreenController.hideView();
        this.lobbyScreenController.disposeOfView();

// this.gameScreenController = new
// cs3744.homework4.controllers.solution.GameScreenController(
// this, this.game, this.player);
        // TODO: use your own game screen controller!
        this.gameScreenController =
            new cs3744.homework4.controllers.GameScreenController(
                this,
                this.game,
                this.player);
        this.gameScreenController.displayView();

    }


    private static IPlayer createPlayer(
        HomeworkConfiguration configuration,
        String name)
    {

        if (configuration.useStudentPlayer())
        {

            return new cs3744.model.Player(name);
        }
        else
        {
            return new cs3744.model.solution2.Player(name);
        }
    }


    /**
     * Creates a player.
     *
     * @param aPlayer
     *            the player to create.
     * @return a new player.
     */
    public IGame createGame(IPlayer aPlayer)
    {

        if (this.configuration.useStudentGameModel())
        {

            return new cs3744.model.Game(
                this.gameClient.createGameID(),
                aPlayer);
        }
        else
        {
            return new cs3744.model.solution2.Game(
                this.gameClient.createGameID(),
                aPlayer);
        }
    }


    /**
     * Creates a new message.
     *
     * @param sender
     *            the sender.
     * @param message
     *            the message text.
     * @return the new message.
     */
    public IMessage createMessage(IPlayer sender, String message)
    {

        if (this.configuration.useStudentMessage())
        {

            return new cs3744.model.Message(sender, message, new Timestamp());
        }
        else
        {
            return new cs3744.model.solution2.Message(
                sender,
                message,
                new Timestamp());
        }
    }


    /**
     * Creates a new game piece
     *
     * @param owner
     *            the owner of the game piece.
     * @param theGame
     *            the game to add the gamepiece to.
     * @return the new game piece.
     */
    public IGamePiece createGamePiece(final IPlayer owner, final IGame theGame)
    {

        if (this.configuration.useStudentGamePieceModel())
        {
            return new cs3744.model.GamePiece(owner, theGame);
        }
        else
        {
            return new cs3744.model.solution2.GamePiece(owner, theGame);
        }

    }

}
