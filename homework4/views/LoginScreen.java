package cs3744.homework4.views;


import cs3744.client.HomeworkConfiguration;
import cs3744.gui.common.Dimension;
import cs3744.gui.interfaces.IButton;
import cs3744.gui.interfaces.ILabel;
import cs3744.gui.interfaces.ITextField;

import cs3744.gui.solution.Frame;


/**
 * The <CODE>LoginScreen</CODE> provides a view for a login window.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class LoginScreen
        extends Frame {


    private static final long serialVersionUID = 1L;
    private final HomeworkConfiguration configuration;
    private final ITextField textField;
    private final IButton loginButton;


    /**
     * Returns the text field.
     * 
     * @return the text field.
     */
    public ITextField getTextField() {

        return this.textField;
    }


    /**
     * Returns the login button.
     * 
     * @return the login button.
     */
    public IButton getButton() {

        return this.loginButton;
    }


    /**
     * Constructs a new <CODE>LoginScreen</CODE>.
     * 
     * @param configuration
     *            the configuration to use.
     * 
     */
    public LoginScreen(HomeworkConfiguration configuration) {

        super("Homework Four - Login");

        this.configuration = configuration;

        int width = 400;
        int height = 400;

        this.setSize(new Dimension(width, height));


        if (this.configuration.useStudentGridLayout()) {

            this.setLayout(new cs3744.gui.GridLayout(3, 1));
        }
        else {

            this.setLayout(new cs3744.gui.solution.GridLayout(3, 1));
        }


        if (this.configuration.useStudentLabel()) {
            ILabel label = new cs3744.gui.Label("Please enter your name!");
            this.add(label);
        }
        else {

            ILabel label = new cs3744.gui.solution.Label(
                    "Please enter your name!");
            this.add(label);
        }

        if (this.configuration.useStudentTextField()) {

            this.textField = new cs3744.gui.TextField();
        }
        else {

            this.textField = new cs3744.gui.solution.TextField();
        }
        this.add(this.textField);

        if (this.configuration.useStudentButton()) {

            this.loginButton = new cs3744.gui.Button("Login");
        }
        else {

            this.loginButton = new cs3744.gui.solution.Button("Login");
        }
        this.add(this.loginButton);
    }
}
