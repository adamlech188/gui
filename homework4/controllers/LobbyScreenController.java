package cs3744.homework4.controllers;

import cs3744.model.Timestamp;
import java.util.Iterator;
import cs3744.gui.events.interfaces.IActionEvent;
import cs3744.gui.interfaces.IButton;
import cs3744.gui.interfaces.ITextField;
import cs3744.homework4.HomeworkFour;
import cs3744.homework4.controllers.interfaces.ILobbyScreenController;
import cs3744.homework4.views.LobbyScreen;
import cs3744.model.events.interfaces.ICollectionChangeEvent;
import cs3744.model.exceptions.CollectionChangeVetoException;
import cs3744.model.interfaces.IGame;
import cs3744.model.interfaces.IGameLobby;
import cs3744.model.interfaces.IMessage;
import cs3744.model.interfaces.IMessageChannel;
import cs3744.model.interfaces.IPlayer;

/**
 * Your description here
 *
 * @author Your name here
 * @version 1.0
 */
public class LobbyScreenController
    implements ILobbyScreenController
{

    private final LobbyScreen  lobbyScreen;

    private final IGameLobby   lobby;
    private final IPlayer      player;
    private final HomeworkFour owner;
    private IGame              game;
    private boolean            toStart;
    private boolean            toContinue;


    /**
     * Returns the lobby screen (the view).
     *
     * @return the view.
     */
    protected LobbyScreen getLobbyScreen()
    {

        return this.lobbyScreen;
    }


    /**
     * Constructs a new <CODE>LobbyScreenController</CODE>.
     *
     * @param mainThread
     *            the owner of this controller.
     * @param lobby
     *            the lobby associated with the view.
     * @param player
     *            the player of this game.
     */
    public LobbyScreenController(
        HomeworkFour mainThread,
        IGameLobby lobby,
        IPlayer player)
    {

        this.owner = mainThread;
        this.lobbyScreen = new LobbyScreen(this.owner.getConfiguration());

        this.lobbyScreen.getMessageTextField().addActionListener(this);
        this.lobbyScreen.getSendButton().addActionListener(this);
        this.lobbyScreen.getStartGameButton().addActionListener(this);

        this.lobby = lobby;
        this.lobby.addCollectionChangeListener(this);
        this.lobby.getMessageChannel().addCollectionChangeListener(this);

        IPlayer somePlayer;
        Iterator<IPlayer> iterator = lobby.getPlayers().iterator();
        while (iterator.hasNext())
        {

            somePlayer = iterator.next();
            StringBuilder textToAdd = new StringBuilder();
            textToAdd.append(new Timestamp());
            textToAdd.append(": >>");
            textToAdd.append(somePlayer);
            textToAdd.append(" has joined the Lobby.<<");
            lobbyScreen.getTextArea().appendText(textToAdd.toString());
            lobbyScreen.addPlayer(somePlayer.toString());
        }

        this.player = player;
        toStart = false;
        toContinue = false;
    }


    /*
     * (non-Javadoc)
     * @see
     * cs3744.homework4.controllers.solution.ILobbyScreenController#displayView
     * ()
     */
    @Override
    public void displayView()
    {

        this.lobbyScreen.setVisible(true);
    }


    /*
     * (non-Javadoc)
     * @see
     * cs3744.homework4.controllers.solution.ILobbyScreenController#hideView()
     */
    @Override
    public void hideView()
    {

        this.lobbyScreen.setVisible(false);
    }


    /*
     * (non-Javadoc)
     * @see
     * cs3744.homework4.controllers.solution.ILobbyScreenController#disposeOfView
     * ()
     */
    @Override
    public void disposeOfView()
    {

        this.lobbyScreen.dispose();
    }


    /**
     * Notifies the owner to start a new game.
     */
    protected void notifyOwner()
    {

        this.owner.getProgressLock().lock();
        this.owner.setGame(this.game);
        this.owner.setPlayer(this.player);
        this.owner.getProgressCondition().signalAll();
        this.owner.getProgressLock().unlock();
    }


    @Override
    public void elementAdded(ICollectionChangeEvent event)
    {
        Object source = event.getSource();
        String collectionName = event.getCollectionName();
        Object causeOfChange = event.getCauseOfChange();

        if (source instanceof IGameLobby)
        {
            if (collectionName.equals("players"))
            {

                IPlayer somePlayer = (IPlayer)causeOfChange;
                lobbyScreen.addPlayer(somePlayer.toString());

                StringBuilder textToAdd = new StringBuilder();
                textToAdd.append(new Timestamp()).append(": >>")
                    .append(somePlayer).append(" has joined the Lobby.<<");
                textToAdd.append(": >>");
                textToAdd.append(somePlayer);
                textToAdd.append(" has joined the Lobby.<<");
                lobbyScreen.getTextArea().appendText(textToAdd.toString());
            }
            if ("games".equals(collectionName))
            {

                IGame someGame = (IGame)causeOfChange;
                lobbyScreen.addGame(someGame.toString());
                lobbyScreen.getInstructionLabel().setText(
                    "Excellent. Now press \"Join Game\" to continue.");
                toContinue = true;
            }
        }
        else if (source instanceof IMessageChannel)
        {
            if ("messages".equals(collectionName))
            {

                IMessage message = (IMessage)causeOfChange;
                lobbyScreen.getTextArea().appendText(message.toString());
                if (!toContinue)
                {
                    lobbyScreen.getInstructionLabel().setText(
                        "Now type \"expletive\" or press \"Create Game\" "
                            + "to continue.");
                    toStart = true;
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
                IPlayer aplayer = (IPlayer)causeOfChange;
                lobbyScreen.removePlayer(aplayer.toString());
                StringBuilder textToAdd = new StringBuilder();
                textToAdd.append(new Timestamp());
                textToAdd.append(": >>");
                textToAdd.append(aplayer);
                textToAdd.append(" has left the Lobby.<<");
                lobbyScreen.getTextArea().appendText(textToAdd.toString());
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

            String text = lobbyScreen.getMessageTextField().getText();
            if (!text.equals("") && !text.equals("\n"))
                sendMessage(text);
        }
        else if (actionEvent.getSource() instanceof IButton)
        {

            IButton sourceButton = (IButton)actionEvent.getSource();

            if (sourceButton.equals(this.lobbyScreen.getSendButton()))
            {

                String text = lobbyScreen.getMessageTextField().getText();
                if (!text.equals("") && !text.equals("\n"))
                    sendMessage(text);
            }
            else if (sourceButton.equals(this.lobbyScreen.getStartGameButton()))
            {

                if (toContinue)
                    notifyOwner();
                else if (toStart)
                {
                    System.out.println("Starting game.");
                    createGame();
                }
            }
        }

    }


    private void sendMessage(String text)
    {

        IMessage message = this.owner.createMessage(this.player, text);

        this.lobbyScreen.getMessageTextField().setText("");

        try
        {
            this.lobby.getMessageChannel().addMessage(message);
        }
        catch (CollectionChangeVetoException e)
        {
            System.out.println("Message got vetoed in controller: "
                + e.toString());
        }
    }


    private void createGame()
    {

        this.game = this.owner.createGame(this.player);

        try
        {
            this.lobby.addGame(this.game);
            this.lobbyScreen.getStartGameButton().setLabelText("Join Game");
        }
        catch (CollectionChangeVetoException e)
        {
            System.out.println("Game creation got vetoed in controller: "
                + e.toString());
        }
    }
}
