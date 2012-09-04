/**
 *
 */
package cs3744.gui;

import cs3744.gui.events.interfaces.IKeyEvent.Key;
import cs3744.gui.events.interfaces.IKeyEvent.KeyEventModifier;
import cs3744.gui.events.interfaces.IFocusEvent.FocusModifier;
import cs3744.gui.events.interfaces.IFocusEvent;
import cs3744.gui.events.solution.FocusEvent;
import cs3744.gui.events.interfaces.IMouseEvent;
import cs3744.gui.events.solution.ActionEvent;
import cs3744.gui.events.interfaces.IKeyEvent;
import cs3744.gui.events.interfaces.IGUIEvent;
import cs3744.gui.common.interfaces.IGraphics;
import cs3744.graphics.common.Color;
import cs3744.gui.events.solution.ActionEventSource;
import cs3744.gui.events.interfaces.IActionEventSource;
import cs3744.common.Property;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import cs3744.gui.events.interfaces.IActionEvent;
import cs3744.gui.events.interfaces.IActionListener;
import cs3744.gui.interfaces.ITextField;

/**
 * Class that provides all the necessary information for the text field.
 *
 * @author Adam Lech (PID: adaml8)
 * @version 1.0
 */
public class TextField
    extends cs3744.gui.solution.Component
    implements ITextField
{

    private static final long        serialVersionUID  = 1L;
    private final Lock               stringLock        = new ReentrantLock();
    private StringBuffer             text;
    private Property<Boolean>        hasFocus;
    private final IActionEventSource actionEventSource =
                                                           new ActionEventSource();


    // ----------------------------------------------------------
    /**
     * Creates empty text field.
     */
    public TextField()
    {
        setBackgroundColor(Color.WHITE);
        setForegroundColor(Color.BLACK);
        hasFocus = new Property<Boolean>("hasFocus", Boolean.valueOf(false));
        text = new StringBuffer("");
    }


    @Override
    public void addActionListener(IActionListener listener)
    {

        actionEventSource.addActionListener(listener);

    }


    @Override
    public void removeActionListener(IActionListener listener)
    {

        actionEventSource.removeActionListener(listener);

    }


    @Override
    public void clearActionListeners()
    {
        actionEventSource.clearActionListeners();

    }


    @Override
    public void fireActionEvent(IActionEvent event)
    {

        actionEventSource.fireActionEvent(event);

    }


    @Override
    public void setFocus(Boolean hasFocus)
    {

        this.hasFocus.setPropertyValue(hasFocus);
        this.invalidate();

    }


    @Override
    public boolean hasFocus()
    {

        return this.hasFocus.getPropertyValue();
    }


    @Override
    public String getText()
    {

        String returnValue;
        try
        {
            stringLock.lock();
            returnValue = new String(text);
        }
        finally
        {
            stringLock.unlock();
        }

        return returnValue;
    }


    @Override
    public void setText(String text)
    {

        try
        {
            stringLock.lock();
            this.text = new StringBuffer(text);
        }
        finally
        {
            stringLock.unlock();
        }

    }


    public void paintBody(IGraphics graphics)
    {
        super.paintBody(graphics);
        if (getBorderWidth() > 0)
            graphics.drawBorder(
                getSize(),
                getBorderWidth(),
                getForegroundColor());
        graphics.setUpTextRendering();
        graphics.setFontColor(getForegroundColor());
        if (hasFocus())
            graphics.renderText(
                (new StringBuilder(String.valueOf(getText()))).append("|")
                    .toString(),
                getAbsoluteLocation(),
                getSize(),
                getBorderWidth() * 2,
                getBorderWidth() * 2);
        else
            graphics.renderText(
                getText(),
                getAbsoluteLocation(),
                getSize(),
                getBorderWidth() * 2,
                getBorderWidth() * 2);
        graphics.cleanUpTextRendering();
    }


    public void processGUIEvent(IGUIEvent event)
    {
        super.processGUIEvent(event);
        if (event instanceof IKeyEvent)
        {
            IKeyEvent keyEvent = (IKeyEvent)event;
            if (hasFocus())
                if (keyEvent.getModifier() == KeyEventModifier.PRESSED
                    && keyEvent.getKey() == Key.TEXT)
                {
                    keyEvent.consume();
                    addChar(keyEvent.getCharacter());
                }
                else if (keyEvent.getModifier() == KeyEventModifier.RELEASED
                    && keyEvent.getKey() == Key.DELETE)
                {
                    keyEvent.consume();
                    removeLastChar();
                }
                else if (keyEvent.getModifier() == KeyEventModifier.RELEASED
                    && keyEvent.getKey() == Key.RETURN)
                {
                    keyEvent.consume();
                    IActionEvent actionEvent = new ActionEvent(this, getText());
                    fireActionEvent(actionEvent);
                }
        }
        else if (event instanceof IMouseEvent)
        {
            IMouseEvent mouseEvent = (IMouseEvent)event;
            switch (mouseEvent.getModifier())
            {
                case MOUSE_CLICKED:
                    mouseEvent.consume();
                    if (!hasFocus())
                    {
                        setFocus(true);
                        fireGUIEvent(new FocusEvent(
                            this,
                            FocusModifier.FOCUS_GAINED));
                    }
                    break;
            }
        }
        else if (event instanceof IFocusEvent)
        {
            IFocusEvent focusEvent = (IFocusEvent)event;
            switch (focusEvent.getModifier())
            {
                case FOCUS_GAINED:
                default:
                    break;

                case FOCUS_LOST:
                    if (hasFocus() && equals(focusEvent.getOppositeComponent()))
                    {
                        focusEvent.consume();
                        setFocus(Boolean.valueOf(false));
                    }
                    break;
            }
        }
    }


    private void addChar(char character)
    {

        try
        {
            stringLock.lock();
            text.append(character);

        }
        finally
        {
            stringLock.unlock();
        }

        invalidate();
    }


    private void removeLastChar()
    {
        boolean invalidate = false;
        try
        {
            stringLock.lock();
            if (text.length() > 0)
            {
                text.deleteCharAt(text.length() - 1);
                invalidate = true;
            }
        }
        finally
        {

            stringLock.unlock();
        }

        if (invalidate)
        {
            invalidate();
        }

    }

}
