package cs3744.homework2;


import javax.media.opengl.GLProfile;

import cs3744.client.GameClientException;
import cs3744.client.HomeworkTwoGameClient;
import cs3744.client.interfaces.IGameClient;
import cs3744.client.interfaces.IGameGUI;
import cs3744.model.Timestamp;
import cs3744.model.events.solution.CollectionChangeEchoListener;
import cs3744.model.events.solution.DataChangeEchoListener;
import cs3744.model.exceptions.CollectionChangeVetoException;
import cs3744.model.exceptions.DataChangeVetoException;
import cs3744.model.interfaces.IGame;
import cs3744.model.interfaces.IGamePiece;
import cs3744.model.interfaces.IGameLobby;
import cs3744.model.interfaces.IMessage;
import cs3744.model.interfaces.IMessageChannel;
import cs3744.model.interfaces.IPlayer;
import cs3744.model.solution2.Game;
import cs3744.model.solution2.GamePiece;
import cs3744.model.solution2.Message;
import cs3744.model.solution2.Player;


/**
 * This is the main class of homework two. It simulates the use of a game
 * client.
 * 
 * Your task is to replace all the solution instances in the import with your
 * own implementation. To that end, you have to implement all the classes that
 * are currently in error.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
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
 * 
 */
@SuppressWarnings("deprecation")
public class HomeworkTwo {


    static {
        // setting this true causes window events not to get sent on Linux if
        // you run from inside Eclipse
        GLProfile.initSingleton(false);
    }


    /**
     * The main thread of homework one.
     * 
     * @param args
     *            Arguments (unused).
     */
    public static void main(String[] args) {


        // Initializing the client
        IGameClient client = new HomeworkTwoGameClient();
        Thread clientThread = new Thread(client);
        clientThread.start();

        CollectionChangeEchoListener collectionEcho = new CollectionChangeEchoListener(
                "Collection Echo");
        DataChangeEchoListener dataEcho = new DataChangeEchoListener(
                "Data Echo");

        // Creating the shell of a player (the server will set the stats)
        IPlayer player = new Player("Your name here");

        // We want to see when things are changing
        player.addDataChangeListener(dataEcho);

        // We will get the lobby and its message channel from the server
        IGameLobby lobby = null;
        IMessageChannel lobbyMessageChannel = null;


        // Connection attempt (that will succeed)
        try {
            client.connectToServer("localhost", 12345);
        }
        catch (GameClientException e) {
            System.out.println(e);
        }

        // Connection attempt (that will fail)
        try {
            client.connectToServer("localhost", 12345);
        }
        catch (GameClientException e) {
            System.out.println(e);
        }


        // Attempting to get the lobby
        try {
            lobby = client.getGameLobby();
        }
        catch (GameClientException e) {
            System.out.println(e);
        }


        if (lobby != null) {
            // We want to see what's happening in the lobby
            lobby.addCollectionChangeListener(collectionEcho);

            // We add our player to the lobby (successful)
            try {
                lobby.addPlayer(player);
            }
            catch (CollectionChangeVetoException e) {
                System.out.println(e);
            }

            // Adding our player a second time should be vetoed!
            try {
                lobby.addPlayer(player);
            }
            catch (CollectionChangeVetoException e) {
                System.out.println(e);
            }


            // We remove the player (because we can)
            try {
                lobby.removePlayer(player);
            }
            catch (CollectionChangeVetoException e) {
                System.out.println(e);
            }

            // Removing our player a second time should be vetoed!
            try {
                lobby.removePlayer(player);
            }
            catch (CollectionChangeVetoException e) {
                System.out.println(e);
            }

            // We retrieve the message channel
            lobbyMessageChannel = lobby.getMessageChannel();
        }

        if (lobbyMessageChannel != null) {

            // We want to see changes in the message channel
            lobbyMessageChannel.addCollectionChangeListener(collectionEcho);

            // Sending a message without being in the lobby should be vetoed
            IMessage message = new Message(player, "Hello", new Timestamp());
            try {
                lobbyMessageChannel.addMessage(message);
            }
            catch (CollectionChangeVetoException e) {
                System.out.println(e);
            }

            // Re-adding the player should be successful
            try {
                lobby.addPlayer(player);
            }
            catch (CollectionChangeVetoException e) {
                System.out.println(e);
            }

            // Re-sending the earlier message
            try {
                lobbyMessageChannel.addMessage(message);
            }
            catch (CollectionChangeVetoException e) {
                System.out.println(e);
            }

            // Accidentally re-sending again. Should be vetoed!
            try {
                lobbyMessageChannel.addMessage(message);
            }
            catch (CollectionChangeVetoException e) {
                System.out.println(e);
            }

            // Attempting to cheat by giving us a flawless win/loss ratio
            try {
                player.setGamesLost(0);
            }
            catch (DataChangeVetoException e) {
                System.out.println(e);
            }

            // Attempting to cheat by giving us a higher win/loss ratio
            try {
                player.setGamesWon(500);
            }
            catch (DataChangeVetoException e) {
                System.out.println(e);
            }

            // new message
            message = new Message(player, "expletive", new Timestamp());

            // Trying to curse in the lobby should be vetoed!
            try {
                lobbyMessageChannel.addMessage(message);
            }
            catch (CollectionChangeVetoException e) {
                System.out.println(e);
            }

            // another new message
            message = new Message(player, "OK. I'll set it up!",
                    new Timestamp());

            // Sending should be successful
            try {
                lobbyMessageChannel.addMessage(message);
            }
            catch (CollectionChangeVetoException e) {
                System.out.println(e);
            }
        }


        // Creating a new game
        IGame game = new Game(client.createGameID(), player);
        game.addCollectionChangeListener(collectionEcho);
        game.getMessageChannel().addCollectionChangeListener(collectionEcho);

        // Successfully adding the game.
        try {
            lobby.addGame(game);
        }
        catch (CollectionChangeVetoException e) {
            System.out.println(e);
        }

        // Second time's no good.
        try {
            lobby.addGame(game);
        }
        catch (CollectionChangeVetoException e) {
            System.out.println(e);
        }


        IMessage message = new Message(player, "expletive", new Timestamp());
        // Trying to curse in the game should not be vetoed!
        try {
            game.getMessageChannel().addMessage(message);
        }
        catch (CollectionChangeVetoException e) {
            System.out.println(e);
        }


        // Creating a gui.
        IGameGUI gui = client.createGUI(game);
        gui.display();

        // Creating game pieces
        // Only allowed to add four per player!
        for (int i = 0; i < 5; i++) {
            IGamePiece gamePiece = new GamePiece(player, game);
            gamePiece.addDataChangeListener(dataEcho);

            try {
                gamePiece.setPosition(i);
                game.addGamePiece(gamePiece);
            }
            catch (DataChangeVetoException e) {
                System.out.println(e);
            }
            catch (CollectionChangeVetoException e) {
                System.out.println(e);
            }
            try {
                // Sleeping for animation purposes.
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Waiting around for other players.
        try {
            Thread.sleep(8000);
        }
        catch (InterruptedException e) {

            System.out.println(e);
        }

        // Disconnecting should be successful
        try {
            client.disconnectFromServer();
        }
        catch (GameClientException e) {
            System.out.println(e);
        }

        // ...but not the second time
        try {
            client.disconnectFromServer();
        }
        catch (GameClientException e) {
            System.out.println(e);
        }


        // Terminating client thread (if it's not already dead)
        if (clientThread.isAlive()) {
            clientThread.interrupt();
        }

    }
}
