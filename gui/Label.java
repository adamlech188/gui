package cs3744.gui;

import cs3744.graphics.common.solution.Color;
import cs3744.gui.common.interfaces.IGraphics;
import cs3744.gui.interfaces.ILabel;
import cs3744.gui.interfaces.TextAlignment;

/**
 * Class that provides all the necessary methods and data for label.
 *
 * @author Adam Lech
 * @version 1.0
 */
public class Label
    extends Component
    implements ILabel
{

    private static final long serialVersionUID = 1L;

    private String            text;
    private TextAlignment     textAlignment;
    private int               border;


    // ----------------------------------------------------------
    /**
     * Creates label without text.
     */
    public Label()
    {
        this("");

    }


    // ----------------------------------------------------------
    /**
     * Creates label with the provided text.
     *
     * @param text
     */
    public Label(String text)
    {
        this(text, TextAlignment.CENTER);
    }


    /**
     * Creates label with the provided text ant the given text alignment.
     *
     * @param text
     * @param textAlignment
     */
    public Label(String text, TextAlignment textAlignment)
    {
        this.textAlignment = TextAlignment.LEFT;
        this.text = null;
        this.text = text;
        this.textAlignment = textAlignment;
        setForegroundColor(Color.BLACK);

    }


    @Override
    public TextAlignment getTextAlignment()
    {
        TextAlignment returnValue = this.textAlignment;

        return returnValue;
    }


    @Override
    public void setTextAlignment(TextAlignment textAlignment)
    {

        switch (textAlignment)
        {
            case LEFT:
                this.textAlignment = TextAlignment.LEFT;
                break;
            case RIGHT:
                this.textAlignment = TextAlignment.RIGHT;
                break;
            case CENTER:
                this.textAlignment = TextAlignment.CENTER;
                break;
            default:
                this.textAlignment = TextAlignment.LEFT;
                break;
        }

    }


    @Override
    public void paintBody(IGraphics graphics)
    {
        super.paintBody(graphics);

        graphics.setUpTextRendering();
        graphics.setFontColor(this.getForegroundColor());
        graphics.renderText(
            this.getText(),
            this.getAbsoluteLocation(),
            this.getSize(),
            border,
            border);
        graphics.cleanUpTextRendering();

    }


    @Override
    public String getText()
    {

        return this.text;
    }


    @Override
    public void setText(String text)
    {

        this.text = text;
        invalidate();

    }

}
