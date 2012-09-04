/**
 * 
 */
package cs3744.homework4.views;


import cs3744.client.HomeworkConfiguration;
import cs3744.gui.common.Dimension;
import cs3744.gui.common.Font;
import cs3744.gui.interfaces.IButton;
import cs3744.gui.interfaces.IComponent;
import cs3744.gui.interfaces.IGameBoardComponent;
import cs3744.gui.interfaces.ILabel;
import cs3744.gui.interfaces.IPanel;
import cs3744.gui.interfaces.ITextArea;
import cs3744.gui.interfaces.ITextField;
import cs3744.gui.solution.Frame;
import cs3744.gui.solution.GameBoardComponent;
import cs3744.gui.interfaces.IGameBoardComponent.GameBoardType;
import cs3744.gui.solution.ListPanel;
import cs3744.gui.solution.Panel;
import cs3744.gui.solution.TextArea;


/**
 * The <CODE>GameScreen</CODE> class provides a view for the IGame model.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class GameScreen
        extends Frame {


    private static final long serialVersionUID = 1L;

    private final HomeworkConfiguration configuration;

    private final GameBoardComponent isometricView;
    private final GameBoardComponent topView;
    private final ITextArea textArea;
    private final ITextField messageTextField;
    private final IButton sendButton;
    private final IButton addGamePieceButton;
    private final ListPanel playerListPanel;


    /**
     * Returns the isometric view.
     * 
     * @return the isometric view.
     */
    public IGameBoardComponent getIsometricView() {

        return this.isometricView;
    }


    /**
     * Returns the top view.
     * 
     * @return the top view.
     */
    public IGameBoardComponent getTopView() {

        return this.topView;
    }


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
     * Returns the send button.
     * 
     * @return the send button.
     */
    public IButton getSendButton() {

        return this.sendButton;
    }


    /**
     * Returns the Add GamePiece button.
     * 
     * @return the add GamePiece button.
     */
    public IButton getAddGamePieceButton() {

        return this.addGamePieceButton;
    }


    /**
     * Constructs a new <CODE>GameScreen</CODE>.
     * 
     * @param configuration
     *            the configuration to use.
     */
    public GameScreen(HomeworkConfiguration configuration) {

        super("Homework Four - Game");

        this.configuration = configuration;

        int width = 1024;
        int height = 1024;

        if (this.configuration.useStudentGridLayout()) {
            this.setLayout(new cs3744.gui.GridLayout(2, 2));
        }
        else {

            this.setLayout(new cs3744.gui.solution.GridLayout(2, 2));
        }
        this.setSize(new Dimension(width, height));
        this.setName("gameScreen");


        // Isometric view
        if (this.configuration.useStudentFourPlayerBoard3D()) {
            this.isometricView = new GameBoardComponent(
                    GameBoardType.STUDENT_3D, this.configuration);
        }
        else {

            this.isometricView = new GameBoardComponent(
                    GameBoardType.REFERENCE_3D, this.configuration);
        }
        this.isometricView.setUpIsometricView();
        this.isometricView.setName("isometricView");
        this.add(this.isometricView);

        // Chat area
        this.textArea = new TextArea();
        this.textArea.setFont(new Font("SansSerif", java.awt.Font.PLAIN, 12));
        this.textArea.setName("textArea");
        this.add(this.textArea);

        // Top view
        if (this.configuration.useStudentFourPlayerBoard()) {
            this.topView = new GameBoardComponent(GameBoardType.STUDENT_2D,
                    this.configuration);
        }
        else {

            this.topView = new GameBoardComponent(GameBoardType.REFERENCE_2D,
                    this.configuration);
        }
        this.topView.setUp2DView();
        this.topView.setName("topView");
        this.add(this.topView);

        // Player/Game panel
        IPanel playerChatPanel = null;
        if (this.configuration.useStudentGridLayout()) {
            playerChatPanel = new Panel(new cs3744.gui.GridLayout(2, 1),
                    "playerChatPanel");
        }
        else {

            playerChatPanel = new Panel(
                    new cs3744.gui.solution.GridLayout(2, 1), "playerChatPanel");
        }

        this.add(playerChatPanel);

        // Player List
        this.playerListPanel = new ListPanel();
        this.playerListPanel.setName("playerListPanel");
        playerChatPanel.add(this.playerListPanel);

        // Players label
        if (this.configuration.useStudentLabel()) {
            this.playerListPanel.add(new cs3744.gui.Label("Players"));
        }
        else {

            this.playerListPanel.add(new cs3744.gui.solution.Label("Players"));
        }

        // Chat Panel
        IPanel chatPanel = null;
        if (this.configuration.useStudentGridLayout()) {
            chatPanel = new Panel(new cs3744.gui.GridLayout(3, 1));
        }
        else {
            chatPanel = new Panel(new cs3744.gui.solution.GridLayout(3, 1));
        }

        chatPanel.setName("chatPanel");
        playerChatPanel.add(chatPanel);

        // Chat entry field
        if (this.configuration.useStudentTextField()) {
            this.messageTextField = new cs3744.gui.TextField();
        }
        else {
            this.messageTextField = new cs3744.gui.solution.TextField();
        }
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


        // Send button
        if (this.configuration.useStudentButton()) {
            this.addGamePieceButton = new cs3744.gui.Button("Add Game Piece");
        }
        else {

            this.addGamePieceButton = new cs3744.gui.solution.Button(
                    "Add Game Piece");
        }
        this.addGamePieceButton.setName("addGamePieceButton");
        chatPanel.add(this.addGamePieceButton);


    }


    /**
     * Displays the tester.
     */
    public void setVisible() {

        this.setVisible(true);
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
