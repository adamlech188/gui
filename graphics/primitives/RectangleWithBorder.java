/**
 *
 */
package cs3744.graphics.primitives;


import javax.media.opengl.GL2;

import cs3744.graphics.interfaces.IPrimitive;
import cs3744.graphics.interfaces.IRectangleWithBorder;


/**
 * Your description here.
 *
 * @author Your name here.
 * @version 1.0
 *
 */
public class RectangleWithBorder
        extends Primitive
        implements IRectangleWithBorder {


    @Override
    public void paint(GL2 gl2) {

        // TODO Auto-generated method stub

    }


    @Override
    public float getBorderWidth() {

        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public IPrimitive copy() {

        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public IPrimitive copy(IPrimitive parent) {

        // TODO Auto-generated method stub
        return null;
    }


}
