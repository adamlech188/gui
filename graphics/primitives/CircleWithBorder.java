/**
 *
 */
package cs3744.graphics.primitives;

import cs3744.graphics.common.Scaling;
import cs3744.graphics.interfaces.IPrimitive;
import javax.media.opengl.GL2;
import cs3744.graphics.interfaces.ICircleWithBorder;

/**
 * This class provides methods to draw circle with border.
 *
 * @author Adam Lech (PID: adaml8)
 * @version 1.0
 */
public class CircleWithBorder
    extends Primitive
    implements ICircleWithBorder
{
    private final float borderWidth; // pixel width of the border


    // ----------------------------------------------------------
    /**
     * Creates a unit circle with border with 0.2px width.
     */
    public CircleWithBorder()
    {
        this(0.2f, null);
    }


    // ----------------------------------------------------------
    /**
     * Create a copy of a circle with border.
     *
     * @param primitiveToCopy
     *            - circle to be copied
     * @param parent
     *            - parent
     */
    public CircleWithBorder(CircleWithBorder primitiveToCopy, IPrimitive parent)
    {
        this(primitiveToCopy.getBorderWidth(), parent);

    }


    // ----------------------------------------------------------
    /**
     * Create a circle with the given border width.
     *
     * @param borderWidth
     *            - border width
     */
    public CircleWithBorder(float borderWidth)
    {
        this(borderWidth, null);

    }


    // ----------------------------------------------------------
    /**
     * Creates a circle with border of the given width and the parent.
     *
     * @param borderWidth
     *            - border width
     * @param parent
     *            - parent of the circle with border
     */
    public CircleWithBorder(float borderWidth, IPrimitive parent)
    {
        super(parent);
        this.borderWidth = borderWidth;
    }


    // ----------------------------------------------------------
    /**
     * Create a circle with 0.2px border with the given parent.
     *
     * @param parent
     *            - parent of the circle with border
     */
    public CircleWithBorder(IPrimitive parent)
    {
        this(0.2f, parent);

    }


    /**
     * Paints the primitives.
     *
     * @param gl2
     *            - openGL state machine
     */
    @Override
    public void paint(GL2 gl2)
    {

        // Checks whether the figure is set to be solid.
        if (isSolid())
        {
            Circle circle1 = new Circle(this);
            circle1.setSolid(true);
            Circle circle2 = new Circle(this);
            circle2
                .setScaling(new Scaling(1 - borderWidth, 1 - borderWidth, 0));
            circle2.setSolid(true);
            circle2.setColor(this.getColor());
            circle1.paint(gl2);
            circle2.paint(gl2);
        }
        else
        {
            Circle circle1 = new Circle(this);
            circle1.setSolid(false);
            Circle circle2 = new Circle(this);
            circle2
                .setScaling(new Scaling(1 - borderWidth, 1 - borderWidth, 0));
            circle2.setSolid(false);
            circle2.setColor(this.getColor());
            circle1.paint(gl2);
            circle2.paint(gl2);
        }

    }


    /**
     * Gets the border width.
     */
    @Override
    public float getBorderWidth()
    {

        return borderWidth;
    }


    @Override
    public IPrimitive copy()
    {

        return copy(null);
    }


    @Override
    public IPrimitive copy(IPrimitive parent)
    {

        return new CircleWithBorder(this,parent);
    }

}
