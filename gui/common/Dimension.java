package cs3744.gui.common;


import cs3744.gui.common.interfaces.IDimension;


/**
 * The <CODE>Dimension</CODE> class provides the dimensions (width and height)
 * of a widget.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class Dimension
        implements IDimension {


    private final int width;
    private final int height;


    @Override
    public int getWidth() {

        return this.width;
    }


    @Override
    public int getHeight() {

        return this.height;
    }


    /**
     * Constructs a new <CODE>Dimension</CODE> with the given width and height.
     * 
     * @param width
     *            the width.
     * @param height
     *            the height.
     */
    public Dimension(int width, int height) {

        this.width = clamp(width);
        this.height = clamp(height);
    }


    /**
     * Copy Constructor.
     * 
     * @param dimension
     *            the dimension to copy.
     */
    public Dimension(IDimension dimension) {

        this.width = clamp(dimension.getWidth());
        this.height = clamp(dimension.getHeight());
    }


    private static int clamp(int value) {

        if (value < 0) {
            return 0;
        }
        else {
            return value;
        }
    }


    /**
     * Checks whether two <CODE>IDimension</CODE> objects have equal values.
     * 
     * @param object
     *            the reference object with which to compare.
     * @return <CODE>true</CODE> if this object is the same as the
     *         <CODE>object</CODE> argument; <CODE>false</CODE> otherwise.
     */
    @Override
    public boolean equals(Object object) {

        if (object instanceof IDimension) {
            IDimension dimension = (IDimension) object;

            if (dimension.getWidth() == this.width
                    && dimension.getHeight() == this.height) {
                return true;
            }
        }
        return false;
    }


    /**
     * Returns the hash code for this <CODE>Dimension</CODE> object.
     * 
     * @return a hash code.
     */
    @Override
    public int hashCode() {

        return this.width + (this.height << 16);
    }


    /**
     * Returns a string representation of this dimension and its width and
     * height. This method is intended to be used only for debugging purposes.
     * The content and format of the returned string may vary between
     * implementations. The returned string may be empty but may not be null.
     * 
     * @return a string representation of this point.
     */
    @Override
    public String toString() {

        return "Dimension(" + this.width + ", " + this.height + ")";
    }

}
