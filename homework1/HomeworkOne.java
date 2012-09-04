package cs3744.homework1;


import cs3744.client.GameClientException;
import cs3744.client.HomeworkOneGameClient;
import cs3744.client.interfaces.IGameClient;
import cs3744.model.Timestamp;
import cs3744.model.events.solution.CollectionChangeEchoListener;
import cs3744.model.events.solution.DataChangeEchoListener;
import cs3744.model.exceptions.CollectionChangeVetoException;
import cs3744.model.exceptions.DataChangeVetoException;
import cs3744.model.interfaces.ILobby;
import cs3744.model.interfaces.IMessage;
import cs3744.model.interfaces.IMessageChannel;
import cs3744.model.interfaces.IPlayer;
import cs3744.model.solution.Message;
import cs3744.model.solution.Player;


/**
 * This is the main class of homework one. It simulates the use of a chat
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
public class HomeworkOne {


    /**
     * The main thread of homework one.
     * 
     * @param args
     *            Arguments (unused).
     */
    public static void main(String[] args) {


        // Initializing the client
        IGameClient client = new HomeworkOneGameClient();
        Thread clientThread = new Thread(client);
        clientThread.start();


        // Creating the shell of a player (the server will set the stats)
        IPlayer player = new Player("Your name here");

        // We want to see when things are changing
        player.addDataChangeListener(new DataChangeEchoListener("Data Echo"));

        // We will get the lobby and its message channel from the server
        ILobby lobby = null;
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
            lobby = client.getLobby();
        }
        catch (GameClientException e) {
            System.out.println(e);
        }


        if (lobby != null) {
            // We want to see what's happening in the lobby
            lobby.addCollectionChangeListener(new CollectionChangeEchoListener(
                    "Collection Echo"));

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
            lobbyMessageChannel
                    .addCollectionChangeListener(new CollectionChangeEchoListener(
                            "Collection Echo"));

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
            message = new Message(player, "OK. See you later", new Timestamp());

            // Sending should be successful
            try {
                lobbyMessageChannel.addMessage(message);
            }
            catch (CollectionChangeVetoException e) {
                System.out.println(e);
            }
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
