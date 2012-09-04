/**
 * 
 */
package cs3744.gui.events.interfaces;


import java.awt.event.KeyEvent;


/**
 * The <CODE>IKeyEvent</CODE> interface provides a contract for EventObjects
 * that provide key events.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IKeyEvent
        extends IInputEvent {

    /**
     * The <CODE>KeyEventModifier</CODE> enum enumerates the possible event
     * modifiers of a key event.
     * 
     * @author Peter J. Radics
     * @version 1.0
     * 
     */
    public enum KeyEventModifier {

        /**
         * The event produced a readable Unicode character.
         */
        TYPED,
        /**
         * A key has been pressed.
         */
        PRESSED,
        /**
         * A key has been released.
         */
        RELEASED;
    }

    /**
     * The <CODE>Key</CODE> enum enumerates some of the keys of a keyboard.
     * 
     * @author Peter J. Radics
     * @version 1.0
     * 
     */
    public enum Key {
        /**
         * The return key.
         */
        RETURN,
        /**
         * The delete key.
         */
        DELETE,
        /**
         * Input is text.
         */
        TEXT,
        /**
         * Other key.
         */
        OTHER,
        /**
         * Control key.
         */
        CONTROL,
        /**
         * Up (direction) key.
         */
        UP,
        /**
         * Down (direction) key.
         */
        DOWN,
        /**
         * Left (direction) key.
         */
        LEFT,
        /**
         * Right (direction) key.
         */
        RIGHT,
        /**
         * Escape key.
         */
        ESC,
        /**
         * Alt key.
         */
        ALT,
        /**
         * F1 key.
         */
        F1,
        /**
         * F2 key.
         */
        F2,
        /**
         * F3 key.
         */
        F3,
        /**
         * F4 key.
         */
        F4,
        /**
         * F5 key.
         */
        F5,
        /**
         * F6 key.
         */
        F6,
        /**
         * F7 key.
         */
        F7,
        /**
         * F8 key.
         */
        F8,
        /**
         * F9 key.
         */
        F9,
        /**
         * F10 key.
         */
        F10,
        /**
         * F11 key.
         */
        F11,
        /**
         * F12 key.
         */
        F12;

        /**
         * Returns the Key corresponding to the AWT key mask.
         * 
         * @param key
         *            the AWT key.
         * @return the IKeyEvent key.
         */
        public static Key awtToIKeyEvent(int key) {

            switch (key) {

                case KeyEvent.VK_ENTER:
                case KeyEvent.VK_ACCEPT:
                    return Key.RETURN;
                case KeyEvent.VK_ALT:
                case KeyEvent.VK_ALT_GRAPH:
                    return Key.ALT;
                case KeyEvent.VK_DELETE:
                case KeyEvent.VK_BACK_SPACE:
                    return Key.DELETE;
                case KeyEvent.VK_CONTROL:
                    return Key.CONTROL;
                case KeyEvent.VK_ESCAPE:
                    return Key.ESC;
                    
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_KP_DOWN:
                    return Key.DOWN;
                case KeyEvent.VK_KP_LEFT:
                case KeyEvent.VK_LEFT:
                    return Key.LEFT;
                case KeyEvent.VK_KP_RIGHT:
                case KeyEvent.VK_RIGHT:
                    return Key.RIGHT;
                case KeyEvent.VK_KP_UP:
                case KeyEvent.VK_UP:
                    return Key.UP;
                    
                case KeyEvent.VK_F1:
                    return Key.F1;
                case KeyEvent.VK_F2:
                    return Key.F2;
                case KeyEvent.VK_F3:
                    return Key.F3;
                case KeyEvent.VK_F4:
                    return Key.F4;
                case KeyEvent.VK_F5:
                    return Key.F5;
                case KeyEvent.VK_F6:
                    return Key.F6;
                case KeyEvent.VK_F7:
                    return Key.F7;
                case KeyEvent.VK_F8:
                    return Key.F8;
                case KeyEvent.VK_F9:
                    return Key.F9;
                case KeyEvent.VK_F10:
                    return Key.F10;
                case KeyEvent.VK_F11:
                    return Key.F11;
                case KeyEvent.VK_F12:
                    return Key.F12;
                case KeyEvent.VK_0:
                case KeyEvent.VK_1:
                case KeyEvent.VK_2:
                case KeyEvent.VK_3:
                case KeyEvent.VK_4:
                case KeyEvent.VK_5:
                case KeyEvent.VK_6:
                case KeyEvent.VK_7:
                case KeyEvent.VK_8:
                case KeyEvent.VK_9:
                case KeyEvent.VK_A:
                case KeyEvent.VK_ADD:
                case KeyEvent.VK_AMPERSAND:
                case KeyEvent.VK_ASTERISK:
                case KeyEvent.VK_AT:
                case KeyEvent.VK_B:
                case KeyEvent.VK_BACK_QUOTE:
                case KeyEvent.VK_BACK_SLASH:
                case KeyEvent.VK_BRACELEFT:
                case KeyEvent.VK_BRACERIGHT:
                case KeyEvent.VK_C:
                case KeyEvent.VK_CIRCUMFLEX:
                case KeyEvent.VK_CLOSE_BRACKET:
                case KeyEvent.VK_COLON:
                case KeyEvent.VK_COMMA:
                case KeyEvent.VK_D:
                case KeyEvent.VK_DEAD_ABOVEDOT:
                case KeyEvent.VK_DEAD_ABOVERING:
                case KeyEvent.VK_DEAD_ACUTE:
                case KeyEvent.VK_DEAD_BREVE:
                case KeyEvent.VK_DEAD_CARON:
                case KeyEvent.VK_DEAD_CEDILLA:
                case KeyEvent.VK_DEAD_CIRCUMFLEX:
                case KeyEvent.VK_DEAD_DOUBLEACUTE:
                case KeyEvent.VK_DEAD_GRAVE:
                case KeyEvent.VK_DEAD_OGONEK:
                case KeyEvent.VK_DEAD_TILDE:
                case KeyEvent.VK_DIVIDE:
                case KeyEvent.VK_DOLLAR:
                case KeyEvent.VK_E:
                case KeyEvent.VK_EQUALS:
                case KeyEvent.VK_EURO_SIGN:
                case KeyEvent.VK_EXCLAMATION_MARK:
                case KeyEvent.VK_F:
                case KeyEvent.VK_G:
                case KeyEvent.VK_GREATER:
                case KeyEvent.VK_H:
                case KeyEvent.VK_I:
                case KeyEvent.VK_INVERTED_EXCLAMATION_MARK:
                case KeyEvent.VK_J:
                case KeyEvent.VK_K:
                case KeyEvent.VK_L:
                case KeyEvent.VK_LEFT_PARENTHESIS:
                case KeyEvent.VK_LESS:
                case KeyEvent.VK_M:
                case KeyEvent.VK_MINUS:
                case KeyEvent.VK_MULTIPLY:
                case KeyEvent.VK_N:
                case KeyEvent.VK_NUMBER_SIGN:
                case KeyEvent.VK_NUMPAD0:
                case KeyEvent.VK_NUMPAD1:
                case KeyEvent.VK_NUMPAD2:
                case KeyEvent.VK_NUMPAD3:
                case KeyEvent.VK_NUMPAD4:
                case KeyEvent.VK_NUMPAD5:
                case KeyEvent.VK_NUMPAD6:
                case KeyEvent.VK_NUMPAD7:
                case KeyEvent.VK_NUMPAD8:
                case KeyEvent.VK_NUMPAD9:
                case KeyEvent.VK_O:
                case KeyEvent.VK_OPEN_BRACKET:
                case KeyEvent.VK_P:
                case KeyEvent.VK_PERIOD:
                case KeyEvent.VK_PLUS:
                case KeyEvent.VK_Q:
                case KeyEvent.VK_QUOTE:
                case KeyEvent.VK_QUOTEDBL:
                case KeyEvent.VK_R:
                case KeyEvent.VK_RIGHT_PARENTHESIS:
                case KeyEvent.VK_S:
                case KeyEvent.VK_SEMICOLON:
                case KeyEvent.VK_SEPARATOR:
                case KeyEvent.VK_SLASH:
                case KeyEvent.VK_SPACE:
                case KeyEvent.VK_STOP:
                case KeyEvent.VK_SUBTRACT:
                case KeyEvent.VK_T:
                case KeyEvent.VK_TAB:
                case KeyEvent.VK_U:
                case KeyEvent.VK_UNDERSCORE:
                case KeyEvent.VK_V:
                case KeyEvent.VK_W:
                case KeyEvent.VK_X:
                case KeyEvent.VK_Y:
                case KeyEvent.VK_Z:
                    return Key.TEXT;
                default:
                    return Key.OTHER;
            }
        }
    }


    /**
     * Returns whether the shift key was held down while the key event occurred.
     * 
     * @return <CODE>true</CODE>, if the shift key was held down;
     *         <CODE>false</CODE> otherwise.
     */
    public boolean isShiftHeldDown();


    /**
     * Returns the key that caused the event.
     * 
     * @return the key.
     */
    public Key getKey();


    /**
     * Returns the modifier of this event.
     * 
     * @return the modifier.
     */
    public KeyEventModifier getModifier();


    /**
     * Returns the readable character (if any) that this event produced.
     * 
     * @return the readable character.
     */
    public char getCharacter();
}
