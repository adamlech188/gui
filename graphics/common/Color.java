/**
 *
 */
package cs3744.graphics.common;


import javax.media.opengl.GL2;

import cs3744.graphics.interfaces.IColor;


/**
 * Provides a reference implementation for the <CODE>IColor</CODE> interface.
 *
 * @author Peter J. Radics
 * @version 1.0
 *
 */
public class Color
        implements IColor {


    /**
     * The color cyan. In the default sRGB space - (0, 255, 255).
     */
    public final static IColor CYAN = new Color(0, 255, 255) {

        @Override
        public String toString() {

            return "Cyan";
        };
    };

    /**
     * The color dark gray. In the default sRGB space - (64, 64, 64).
     */
    public final static IColor DARK_GRAY = new Color(64, 64, 64) {

        @Override
        public String toString() {

            return "Dark Gray";
        };
    };

    /**
     * The color gray. In the default sRGB space - (128, 128, 128).
     */
    public final static IColor GRAY = new Color(128, 128, 128) {

        @Override
        public String toString() {

            return "Gray";
        };
    };


    /**
     * The color light gray. In the default sRGB space - (192, 192, 192).
     */
    public final static IColor LIGHT_GRAY = new Color(192, 192, 192) {

        @Override
        public String toString() {

            return "Light Gray";
        };
    };

    /**
     * The color magenta. In the default sRGB space - (255, 0, 255).
     */
    public final static IColor MAGENTA = new Color(255, 0, 255) {

        @Override
        public String toString() {

            return "Magenta";
        };
    };

    /**
     * The color orange. In the default sRGB space - (255, 200, 0).
     */
    public final static IColor ORANGE = new Color(255, 200, 0) {

        @Override
        public String toString() {

            return "Orange";
        };
    };

    /**
     * The color pink. In the default sRGB space - (255, 175, 175).
     */
    public final static IColor PINK = new Color(255, 175, 175) {

        @Override
        public String toString() {

            return "Pink";
        };
    };


    /**
     * The representation of the color black.
     */
    public static final IColor BLACK = new Color(0f, 0f, 0f, 1f) {

        @Override
        public String toString() {

            return "Black";
        };
    };

    /**
     * The representation of the color white.
     */
    public static final IColor WHITE = new Color(1f, 1f, 1f, 1f) {

        @Override
        public String toString() {

            return "White";
        };
    };

    /**
     * The representation of the color yellow.
     */
    public static final IColor YELLOW = new Color(1f, 1f, 0f, 1f) {

        @Override
        public String toString() {

            return "Yellow";
        }
    };
    /**
     * The representation of the color red.
     */
    public static final IColor RED = new Color(1f, 0f, 0f, 1f) {

        @Override
        public String toString() {

            return "Red";
        };
    };
    /**
     * The representation of the color green.
     */
    public static final IColor GREEN = new Color(0f, 1f, 0f, 1f) {

        @Override
        public String toString() {

            return "Green";
        };
    };
    /**
     * The representation of the color blue.
     */
    public static final IColor BLUE = new Color(0f, 0f, 1f, 1f) {

        @Override
        public String toString() {

            return "Blue";
        };
    };
    /**
     * A representation of the color brown.
     */
    public static final IColor BROWN = new Color(139f / 255f, 69f / 255f,
            19f / 255f, 1f) {

        @Override
        public String toString() {

            return "Brown";
        };
    };

    private final float redComponent;
    private final float greenComponent;
    private final float blueComponent;
    private final float alphaComponent;


    @Override
    public float getRedComponent() {

        return this.redComponent;
    }


    @Override
    public float getGreenComponent() {

        return this.greenComponent;
    }


    @Override
    public float getBlueComponent() {

        return this.blueComponent;
    }


    @Override
    public float getAlphaComponent() {

        return this.alphaComponent;
    }


    /**
     * Constructs a new <CODE>Color</CODE> class with the specified color
     * components and the alpha component set to 1.
     *
     * @param redComponent
     *            the red component.
     * @param greenComponent
     *            the green component.
     * @param blueComponent
     *            the blue component.
     */
    public Color(int redComponent, int greenComponent, int blueComponent) {

        this(redComponent, greenComponent, blueComponent, 1);
    }


    /**
     * Constructs a new <CODE>Color</CODE> class with the specified color and
     * alpha components.
     *
     * @param redComponent
     *            the red component.
     * @param greenComponent
     *            the green component.
     * @param blueComponent
     *            the blue component.
     * @param alphaComponent
     *            the alpha component.
     */
    public Color(int redComponent, int greenComponent, int blueComponent,
            int alphaComponent) {

        this(redComponent / 255f, greenComponent / 255f, blueComponent / 255f,
                alphaComponent / 255f);
    }


    /**
     * Constructs a new <CODE>Color</CODE> class with the specified color
     * components and the alpha component set to 1.
     *
     * @param redComponent
     *            the red component.
     * @param greenComponent
     *            the green component.
     * @param blueComponent
     *            the blue component.
     */
    public Color(float redComponent, float greenComponent, float blueComponent) {

        this(redComponent, greenComponent, blueComponent, 1f);
    }


    /**
     * Constructs a new <CODE>Color</CODE> class with the specified color and
     * alpha components.
     *
     * @param redComponent
     *            the red component.
     * @param greenComponent
     *            the green component.
     * @param blueComponent
     *            the blue component.
     * @param alphaComponent
     *            the alpha component.
     */
    public Color(float redComponent, float greenComponent, float blueComponent,
            float alphaComponent) {

        this.redComponent = redComponent;
        this.greenComponent = greenComponent;
        this.blueComponent = blueComponent;
        this.alphaComponent = alphaComponent;
    }


    @Override
    public void applyAttribute(GL2 gl2) {

        // System.out.println("Applying color " + this.toString());
        gl2.glColor4f(this.redComponent, this.greenComponent,
                this.blueComponent, this.alphaComponent);

    }


    @Override
    public float[] toArray() {

        float returnValue[] = new float[4];

        returnValue[0] = this.redComponent;
        returnValue[1] = this.greenComponent;
        returnValue[2] = this.blueComponent;
        returnValue[3] = this.alphaComponent;

        return returnValue;
    }


    @Override
    public String toString() {

        return "Color with red: " + this.redComponent + ", green: "
                + this.greenComponent + ", blue: " + this.blueComponent
                + ", and alpha: " + this.alphaComponent;
    }


    @Override
    public boolean equals(Object obj) {

        if (obj != null) {

            if (obj instanceof IColor) {
                IColor otherColor = (IColor) obj;

                return new Float(this.redComponent).equals(otherColor
                        .getRedComponent())
                        && new Float(this.greenComponent).equals(otherColor
                                .getGreenComponent())
                        && new Float(this.blueComponent).equals(otherColor
                                .getBlueComponent())
                        && new Float(this.alphaComponent).equals(otherColor
                                .getAlphaComponent());
            }
        }

        return false;
    }


    @Override
    public int hashCode() {

        return new Float(this.redComponent).hashCode()
                + new Float(this.greenComponent).hashCode()
                + new Float(this.blueComponent).hashCode()
                + new Float(this.alphaComponent).hashCode();
    }


}
