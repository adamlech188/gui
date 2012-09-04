package cs3744.homework4.controllers;

import cs3744.gui.interfaces.IButton;
import cs3744.gui.interfaces.ITextField;
import cs3744.gui.events.interfaces.IActionEvent;
import cs3744.homework4.HomeworkFour;
import cs3744.homework4.controllers.interfaces.ILoginScreenController;
import cs3744.homework4.views.LoginScreen;

/**
 * The <CODE>LoginScreenController</CODE> provides a reference implementation of
 * the <CODE>ILoginScreenController</CODE> interface.
 *
 * @author Peter J. Radics
 * @version 1.0
 */
public class LoginScreenController
    implements ILoginScreenController
{

    private LoginScreen  loginScreen;

    private String       playerName;

    private HomeworkFour owner;


    /**
     * Returns the login screen (the view).
     *
     * @return the view.
     */
    protected LoginScreen getLoginScreen()
    {

        return this.loginScreen;
    }


    /*
     * (non-Javadoc)
     * @see
     * cs3744.homework4.controllers.solution.ILoginScreenController#getPlayerName
     * ()
     */
    @Override
    public String getPlayerName()
    {

        return this.playerName;
    }


    /*
     * (non-Javadoc)
     * @see
     * cs3744.homework4.controllers.solution.ILoginScreenController#setPlayerName
     * (java.lang.String)
     */
    @Override
    public void setPlayerName(String playerName)
    {

        this.playerName = playerName;
    }


    /**
     * Constructs a new <CODE>LoginScreenController</CODE>, creating a new view
     * using the configuration specified in the owner.
     *
     * @param mainThread
     *            the owner of this instance.
     */
    public LoginScreenController(HomeworkFour mainThread)
    {

        // TODO consider thread-safety!
        this.owner = mainThread;
        this.loginScreen = new LoginScreen(this.owner.getConfiguration());
        this.playerName = "";

        this.loginScreen.getTextField().addActionListener(this);
        this.loginScreen.getButton().addActionListener(this);

    }


    /*
     * (non-Javadoc)
     * @see
     * cs3744.homework4.controllers.solution.ILoginScreenController#displayView
     * ()
     */
    @Override
    public void displayView()
    {

        this.loginScreen.setVisible(true);
    }


    /*
     * (non-Javadoc)
     * @see
     * cs3744.homework4.controllers.solution.ILoginScreenController#hideView()
     */
    @Override
    public void hideView()
    {

        this.loginScreen.setVisible(false);
    }


    /*
     * (non-Javadoc)
     * @see
     * cs3744.homework4.controllers.solution.ILoginScreenController#disposeOfView
     * ()
     */
    @Override
    public void disposeOfView()
    {

        this.loginScreen.dispose();
    }


    /**
     * Notifies the owner of the control of the login attempt.
     */
    protected void notifyOwner()
    {

        this.owner.getProgressLock().lock();
        this.owner.getProgressCondition().signalAll();
        this.owner.getProgressLock().unlock();
    }


    @Override
    public void actionPerformed(IActionEvent actionEvent)
    {

        if ((actionEvent.getSource() instanceof ITextField)
            || (actionEvent.getSource() instanceof IButton))
        {
            String text = getLoginScreen().getTextField().getText();
            if (!text.equals("") && !text.equals("\n"))
            {
                setPlayerName(text);
                getLoginScreen().getTextField().setText("Adam");
                notifyOwner();
            }
        }

    }
}
