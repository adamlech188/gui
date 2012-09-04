/**
 *
 */
package cs3744.gui;

import cs3744.common.Property;
import cs3744.graphics.common.Color;
import cs3744.graphics.interfaces.IColor;
import cs3744.gui.common.Point;
import cs3744.gui.common.interfaces.IGraphics;
import cs3744.gui.common.interfaces.IPoint;
import cs3744.gui.events.interfaces.*;
import cs3744.gui.events.interfaces.IFocusEvent.FocusModifier;
import cs3744.gui.events.interfaces.IKeyEvent.Key;
import cs3744.gui.events.interfaces.IKeyEvent.KeyEventModifier;
import cs3744.gui.events.solution.ActionEvent;
import cs3744.gui.events.solution.ActionEventSource;
import cs3744.gui.events.solution.FocusEvent;
import cs3744.gui.interfaces.IButton;

/**
 * Class that provides all necessary information to create button.
 *
 * @author Adam Lech(PID: adaml8)
 * @version 1.0
 */
public class Button
    extends cs3744.gui.solution.Component
    implements IButton
{

    private static final long        serialVersionUID = 1L;
    private final Property<String>   labelText;
    private final Property<Boolean>  hasFocus;
    private final Property<Boolean>  isPressed;
    private final IActionEventSource actionEventSource;


    // ----------------------------------------------------------
    /**
     *Creates a button with empty text.
     */
    public Button()
    {
        this("");
    }

    /**
     * Creates Button with the provided text.
     * @param labelText
     *
     */
    public Button(String labelText)
    {
        setBackgroundColor(Color.LIGHT_GRAY);
        setForegroundColor(Color.BLACK);
        setBorderWidth(5);
        actionEventSource = new ActionEventSource();
        hasFocus = new Property<Boolean>("hasFocus", Boolean.valueOf(false));
        isPressed = new Property<Boolean>("isPressed", Boolean.valueOf(false));
        this.labelText = new Property<String>("labelText", labelText);
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
    }


    @Override
    public boolean hasFocus()
    {

        return this.hasFocus.getPropertyValue();
    }


    @Override
    public String getLabelText()
    {

        return this.labelText.getPropertyValue();
    }


    @Override
    public void setLabelText(String labelText)
    {

        this.labelText.setPropertyValue(labelText);

    }


    @Override
    public boolean isPressed()
    {

        return this.isPressed.getPropertyValue();

    }


    @Override
    public void setPressed(boolean pressed)
    {

        this.isPressed.setPropertyValue(pressed);

    }


    public void processGUIEvent(IGUIEvent event)
    {

        super.processGUIEvent(event);


        if (event instanceof IKeyEvent)
        {

            IKeyEvent mainEvent = (IKeyEvent)event;
            if (hasFocus()
                && mainEvent.getModifier() == KeyEventModifier.RELEASED
                && mainEvent.getKey() == Key.RETURN)
            {
                IActionEvent actionEvent =
                    new ActionEvent(this, "buttonPressed");
                fireActionEvent(actionEvent);
                mainEvent.consume();
            }
        }
         if (event instanceof IMouseEvent)
        {
            IMouseEvent mouseEvent = (IMouseEvent)event;
            switch (mouseEvent.getModifier())
            {
                case MOUSE_CLICKED:
                    if (!hasFocus())
                    {
                        this.setFocus(true);
                        fireGUIEvent(new FocusEvent(
                            this,
                            FocusModifier.FOCUS_GAINED));

                    }
                    IActionEvent actionEvent =
                        new ActionEvent(this, "buttonPressed");
                    fireActionEvent(actionEvent);
                    break;
                case MOUSE_PRESSED:
                    mouseEvent.consume();
                    this.setPressed(true);
                    invalidate();
                    break;
                case MOUSE_RELEASED:
                    mouseEvent.consume();
                    setPressed(false);
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
                        setFocus(false);

                    }
                    break;
            }
        }
    }


    public void paintBody(IGraphics graphics)
    {
        super.paintBody(graphics);
        if (isPressed())
        {
            this.drawBox(graphics, 0.3f, 0.3f, 0.3f, 0.3f);
        }
        else if (hasFocus())
        {
            this.drawBox(graphics, 0.5f, 0.5f, 0.5f, 0.4f);
        }
        else
            drawBox(graphics, 0.6f, 0.6f, 0.3f, 0.3f);
        graphics.setUpTextRendering();
        graphics.setFontColor(this.getForegroundColor());
        graphics.renderText(
            this.getLabelText(),
            this.getAbsoluteLocation(),
            this.getSize(),
            this.getBorderWidth() * 2,
            this.getBorderWidth() * 2);
        graphics.cleanUpTextRendering();
    }


    private void drawBox(
        IGraphics graphics,
        float topColorFactor,
        float leftColorFactor,
        float rightColorFactor,
        float bottomColorFactor)
    {
        IPoint bottomLeftCorner =
            new Point(this.getBorderWidth(), this.getBorderWidth());
        IPoint bottomRightCorner =
            new Point(
                this.getSize().getWidth() - this.getBorderWidth(),
                this.getBorderWidth());
        IPoint topLeftCorner = new Point(0, 0);
        IPoint topRightCorner = new Point(this.getSize().getWidth(), 0);
        IColor color =
            new Color(this.getBackgroundColor().getRedComponent()
                * bottomColorFactor, this.getBackgroundColor()
                .getGreenComponent() * bottomColorFactor, this
                .getBackgroundColor().getBlueComponent() * bottomColorFactor);
        graphics.drawQuadrilateral(
            this.getSize(),
            bottomLeftCorner,
            bottomRightCorner,
            topRightCorner,
            topLeftCorner,
            color);
        bottomLeftCorner = new Point(0, 0);
        bottomRightCorner =
            new Point(this.getBorderWidth(), this.getBorderWidth());
        topRightCorner =
            new Point(this.getBorderWidth(), this.getSize().getHeight()
                - this.getBorderWidth());
        topLeftCorner = new Point(0, getSize().getHeight());
        color =
            new Color(this.getBackgroundColor().getRedComponent()
                * leftColorFactor, this.getBackgroundColor()
                .getGreenComponent() * leftColorFactor, this
                .getBackgroundColor().getBlueComponent() * leftColorFactor);
        graphics.drawQuadrilateral(
            getSize(),
            bottomLeftCorner,
            bottomRightCorner,
            topRightCorner,
            topLeftCorner,
            color);
        bottomLeftCorner =
            new Point(
                this.getSize().getWidth() - this.getBorderWidth(),
                this.getBorderWidth());
        bottomRightCorner = new Point(this.getSize().getWidth(), 0);
        topRightCorner =
            new Point(this.getSize().getWidth(), this.getSize().getHeight());
        topLeftCorner =
            new Point(this.getSize().getWidth() - this.getBorderWidth(), this
                .getSize().getHeight() - this.getBorderWidth());
        color =
            new Color(this.getBackgroundColor().getRedComponent()
                * rightColorFactor, this.getBackgroundColor()
                .getGreenComponent() * rightColorFactor, this
                .getBackgroundColor().getBlueComponent() * rightColorFactor);
        graphics.drawQuadrilateral(
            this.getSize(),
            bottomLeftCorner,
            bottomRightCorner,
            topRightCorner,
            topLeftCorner,
            color);
        bottomLeftCorner =
            new Point(this.getBorderWidth(), this.getSize().getHeight()
                - this.getBorderWidth());
        bottomRightCorner =
            new Point(
                this.getSize().getWidth() - this.getBorderWidth(),
                getSize().getHeight() - this.getBorderWidth());
        topRightCorner =
            new Point(this.getSize().getWidth(), this.getSize().getHeight());
        topLeftCorner = new Point(0, this.getSize().getHeight());
        color =
            new Color(this.getBackgroundColor().getRedComponent()
                * topColorFactor, this.getBackgroundColor().getGreenComponent()
                * topColorFactor, this.getBackgroundColor().getBlueComponent()
                * topColorFactor);
        graphics.drawQuadrilateral(
            this.getSize(),
            bottomLeftCorner,
            bottomRightCorner,
            topRightCorner,
            topLeftCorner,
            color);

    }
}
