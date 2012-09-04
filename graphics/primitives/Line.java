package cs3744.graphics.primitives;


import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import cs3744.graphics.interfaces.IColor;
import cs3744.graphics.interfaces.ILine;
import cs3744.graphics.interfaces.IPoint;
import cs3744.graphics.interfaces.IPrimitive;
import cs3744.graphics.primitives.Point;


/**
 * A <CODE>Line</CODE> between two points.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class Line
        extends Primitive
        implements ILine {

    private final IPoint source;
    private final IPoint sink;


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.graphics.primitives.solution.ILine#getSource()
     */
    @Override
    public IPoint getSource() {

        return this.source;
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.graphics.primitives.solution.ILine#getSink()
     */
    @Override
    public IPoint getSink() {

        return this.sink;
    }


    @Override
    public void setColor(IColor color) {

        super.setColor(color);
        this.source.setColor(color);
        this.sink.setColor(color);
    }


    /**
     * Constructs a new <CODE>Line</CODE> with the provided source and sink.
     * 
     * @param source
     *            the source of this line.
     * @param sink
     *            the sink of this line.
     */
    public Line(IPoint source, IPoint sink) {

        this(source, sink, null);
    }


    /**
     * Constructs a new <CODE>Line</CODE> with the provided parent, source, and
     * sink.
     * 
     * @param source
     *            the source of this line.
     * @param sink
     *            the sink of this line.
     * 
     * @param parent
     *            the parent.
     */
    public Line(IPoint source, IPoint sink, IPrimitive parent) {

        super(parent);

        this.source = new Point(source, this);

        this.sink = new Point(sink, this);
    }


    /**
     * Copy Constructor.
     * 
     * @param lineToCopy
     *            the line to copy.
     * @param parent
     *            the new instance's parent.
     */
    public Line(ILine lineToCopy, IPrimitive parent) {

        super(lineToCopy, parent);

        this.source = new Point(lineToCopy.getSource(), this);
        this.sink = new Point(lineToCopy.getSink(), this);
    }


    @Override
    public ILine copy() {

        return this.copy(null);
    }


    @Override
    public ILine copy(IPrimitive parent) {

        return new Line(this, parent);
    }


    @Override
    public void paint(GL2 gl2) {

        gl2.glBegin(GL.GL_LINES);

        // Drawing the points.
        this.source.paint(gl2);
        this.sink.paint(gl2);

        // End of the glBegin() glEnd() block.
        gl2.glEnd();
    }


    @Override
    public String toString() {

        return "Line from: " + this.source + " to: " + this.sink;
    }
}
