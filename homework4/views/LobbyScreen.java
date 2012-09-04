/**
 * 
 */
package cs3744.homework4.views;


import cs3744.client.HomeworkConfiguration;
import cs3744.gui.common.Dimension;
import cs3744.gui.common.Font;
import cs3744.gui.interfaces.IButton;
import cs3744.gui.interfaces.IComponent;
import cs3744.gui.interfaces.ILabel;
import cs3744.gui.interfaces.IPanel;
import cs3744.gui.interfaces.ITextArea;
import cs3744.gui.interfaces.ITextField;
import cs3744.gui.solution.Frame;
import cs3744.gui.solution.ListPanel;
import cs3744.gui.solution.Panel;
import cs3744.gui.solution.Spacer;
import cs3744.gui.solution.TextArea;


/**
 * The <CODE>LobbyScreen</CODE> class provides a view for
 * <CODE>IGameLobby</CODE>s.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class LobbyScreen
        extends Frame {

    private static final long serialVersionUID = 1L;

    private final HomeworkConfiguration configuration;
    private final ITextArea textArea;
    private final ITextField messageTextField;
    private final IButton startGameButton;
    private final IButton sendButton;
    private final ILabel instructionLabel;
    private final ListPanel playerListPanel;
    private final ListPanel gameListPanel;


    /**
     * Returns the text area.
     * 
     * @return the text area.
     */
    public ITextArea getTextArea() {

        return this.textArea;
    }


    /**
     * Returns the message text field.
     * 
     * @return the message text field.
     */
    public ITextField getMessageTextField() {

        return this.messageTextField;
    }


    /**
     * Returns the start game button.
     * 
     * @return the start game button
     */
    public IButton getStartGameButton() {

        return this.startGameButton;
    }


    /**
     * Returns the send button.
     * 
     * @return the send button.
     */
    public IButton getSendButton() {

        return this.sendButton;
    }


    /**
     * Returns the instruction labe.
     * 
     * @return the instruction label.
     */
    public ILabel getInstructionLabel() {

        return this.instructionLabel;
    }


    /**
     * Constructs a new <CODE>LobbyScreen</CODE>.
     * 
     * @param configuration
     *            the configuration to use.
     * 
     */
    public LobbyScreen(HomeworkConfiguration configuration) {

        super("Homework Four - Lobby");

        this.configuration = configuration;

        int width = 1024;
        int height = 768;

        this.setSize(new Dimension(width, height));


        if (this.configuration.useStudentGridLayout()) {

            this.setLayout(new cs3744.gui.GridLayout(2, 1));
        }
        else {

            this.setLayout(new cs3744.gui.solution.GridLayout(2, 1));
        }

        // Top Panel
        IPanel topPanel = null;
        if (this.configuration.useStudentGridLayout()) {

            topPanel = new Panel(new cs3744.gui.GridLayout(1, 2));
        }
        else {

            topPanel = new Panel(new cs3744.gui.solution.GridLayout(1, 2));
        }
        topPanel.setName("topPanel");
        this.add(topPanel);

        // Player/Game panel
        IPanel playerGamePanel = null;
        if (this.configuration.useStudentGridLayout()) {
            playerGamePanel = new Panel(new cs3744.gui.GridLayout(2, 1));
        }
        else {

            playerGamePanel = new Panel(
                    new cs3744.gui.solution.GridLayout(2, 1));
        }
        playerGamePanel.setName("playerGamePanel");
        topPanel.add(playerGamePanel);

        // Player List
        this.playerListPanel = new ListPanel();
        this.playerListPanel.setName("playerListPanel");
        playerGamePanel.add(this.playerListPanel);

        // Players label
        if (this.configuration.useStudentLabel()) {
            this.playerListPanel.add(new cs3744.gui.Label("Players"));
        }
        else {

            this.playerListPanel.add(new cs3744.gui.solution.Label("Players"));
        }
        // Player List
        this.gameListPanel = new ListPanel();
        this.gameListPanel.setName("gameListPanel");
        playerGamePanel.add(this.gameListPanel);

        // Players label
        if (this.configuration.useStudentLabel()) {
            this.gameListPanel.add(new cs3744.gui.Label("Games"));
        }
        else {

            this.gameListPanel.add(new cs3744.gui.solution.Label("Games"));
        }


        // Chat area
        this.textArea = new TextArea();
        this.textArea.setFont(new Font("SansSerif", java.awt.Font.PLAIN, 12));

        topPanel.add(this.textArea);

        // Bottom Panel
        IPanel bottomPanel = null;
        if (this.configuration.useStudentGridLayout()) {
            bottomPanel = new Panel(new cs3744.gui.GridLayout(3, 1));
        }
        else {

            bottomPanel = new Panel(new cs3744.gui.solution.GridLayout(3, 1));
        }
        this.add(bottomPanel);

        if (this.configuration.useStudentLabel()) {
            this.instructionLabel = new cs3744.gui.Label(
                    "Type anything to start!");
        }
        else {

            this.instructionLabel = new cs3744.gui.solution.Label(
                    "Type anything to start!");
        }
        this.instructionLabel.setFont(new Font("SansSerif",
                java.awt.Font.PLAIN, 12));
        bottomPanel.add(this.instructionLabel);
        bottomPanel.add(new Spacer("spacer3"));


        // Chat Panel
        IPanel chatPanel = null;
        if (this.configuration.useStudentGridLayout()) {
            chatPanel = new Panel(new cs3744.gui.GridLayout(1, 3));
        }
        else {

            chatPanel = new Panel(new cs3744.gui.solution.GridLayout(1, 3));
        }
        bottomPanel.add(chatPanel);


        // Start Button
        if (this.configuration.useStudentButton()) {
            this.startGameButton = new cs3744.gui.Button("Create Game");
        }
        else {

            this.startGameButton = new cs3744.gui.solution.Button("Create Game");
        }
        this.startGameButton.setName("startGameButton");
        chatPanel.add(this.startGameButton);


        // Chat entry field
        this.messageTextField = new cs3744.gui.solution.TextField();
        this.messageTextField.setBorderWidth(1);
        this.messageTextField.setName("messageTextField");

        chatPanel.add(this.messageTextField);


        // Send button
        if (this.configuration.useStudentButton()) {
            this.sendButton = new cs3744.gui.Button("Send");
        }
        else {

            this.sendButton = new cs3744.gui.solution.Button("Send");
        }
        this.sendButton.setName("sendButton");
        chatPanel.add(this.sendButton);
    }


    /**
     * Adds a game with the provided name to the list of games.
     * 
     * @param name
     *            the name of the game.
     */
    public void addGame(String name) {

        ILabel label = null;
        if (this.configuration.useStudentLabel()) {
            label = new cs3744.gui.Label(name);
        }
        else {

            label = new cs3744.gui.solution.Label(name);
        }

        label.setFont(new Font("SansSerif", java.awt.Font.BOLD, 16));
        this.gameListPanel.add(label);
    }


    /**
     * Adds a player with the provided name to the list of players.
     * 
     * @param name
     *            the name of the player.
     */
    public void addPlayer(String name) {

        ILabel label = null;

        if (this.configuration.useStudentLabel()) {
            label = new cs3744.gui.Label(name);
        }
        else {

            label = new cs3744.gui.solution.Label(name);
        }

        label.setFont(new Font("SansSerif", java.awt.Font.BOLD, 16));
        this.playerListPanel.add(label);
    }


    /**
     * Removes a player with the provided name to the list of players.
     * 
     * @param name
     *            the name of the player.
     */
    public void removePlayer(String name) {

        ILabel playerLabel = null;
        for (IComponent component : this.playerListPanel.getComponents()) {
            if (component instanceof ILabel) {
                playerLabel = (ILabel) component;
                if (playerLabel.getText().equals(name)) {
                    break;
                }
            }
        }
        this.playerListPanel.remove(playerLabel);
    }
}
