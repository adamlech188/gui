package cs3744.graphics.models;


import cs3744.graphics.common.Color;
import cs3744.graphics.common.Material;
import cs3744.graphics.interfaces.IMaterial;


/**
 * The <CODE>MaterialLookupTable</CODE> provides the materials of coordinates in
 * a grid-based game board.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class MaterialLookupTable {

    /**
     * Material of the playing board.
     */
    public static final IMaterial BOARD_MATERIAL = new Material(
            ColorLookupTable.BOARD_COLOR, ColorLookupTable.BOARD_COLOR,
            Color.BLACK, 0f);

    /**
     * Material of the playing board border.
     */
    public static final IMaterial BORDER_MATERIAL = new Material(Color.BROWN,
            Color.BROWN, Color.BLACK, 0f);
    /**
     * Material of the red player.
     */
    // public static final IMaterial RED_PLAYER = new
    // Material(ColorLookupTable.RED_PLAYER,
    // ColorLookupTable.RED_PLAYER, Color.WHITE, 32f, null);
    public static final IMaterial RED_PLAYER = Material.BRONZE;
    /**
     * Material of the yellow player.
     */
    // public static final IMaterial YELLOW_PLAYER = new
    // Material(ColorLookupTable.YELLOW_PLAYER,
    // ColorLookupTable.YELLOW_PLAYER, Color.WHITE, 32f, null);
    public static final IMaterial YELLOW_PLAYER = Material.GOLD;
    /**
     * Material of the blue player.
     */
    // public static final IMaterial BLUE_PLAYER = new Material(
    // ColorLookupTable.BLUE_PLAYER, ColorLookupTable.BLUE_PLAYER,
    // new Color(0.5f, 0.5f, 0.5f), 32f, null);
    public static final IMaterial BLUE_PLAYER = Material.BRASS;
    /**
     * Material of the black player.
     */
    public static final IMaterial BLACK_PLAYER = Material.BLACK_PLASTIC;
    /**
     * Material of the purple player.
     */
    public static final IMaterial PURPLE_PLAYER = new Material(
            ColorLookupTable.PURPLE_PLAYER, ColorLookupTable.BLUE_PLAYER,
            new Color(0.5f, 0.5f, 0.5f), 32f);
    /**
     * Material of the green player.
     */
    // public static final IMaterial GREEN_PLAYER = new Material(
    // ColorLookupTable.GREEN_PLAYER, ColorLookupTable.GREEN_PLAYER,
    // new Color(0.5f, 0.5f, 0.5f), 32f, null);
    public static final IMaterial GREEN_PLAYER = Material.POLISHED_SILVER;

    /**
     * Material of a white field.
     */
    public static final IMaterial WHITE_FIELD = new Material(
            ColorLookupTable.WHITE_FIELD, ColorLookupTable.WHITE_FIELD,
            Color.BLACK, 0f);


    /**
     * Serves as a look-up table for the colors of a four-player board,
     * providing the color of each coordinate, if a circle is to be drawn, or
     * null, if there is no circle at the coordinates provided.
     * 
     * @param x
     *            the x coordinate.
     * @param y
     *            the y coordinate.
     * @return the color of the circle in the grid coordinate, or null.
     */
    public static IMaterial fourPlayerBoardGridMaterial(int x, int y) {

        if (x >= 0 && y >= 0 && x <= 12 && y <= 12) {
            switch (x + 15 * y) {
                case 0:
                case 1:
                    return YELLOW_PLAYER;
                case 11:
                case 12:
                    return GREEN_PLAYER;

                case 15:
                case 16:
                case 20:
                    return YELLOW_PLAYER;
                case 21:
                case 22:
                    return WHITE_FIELD;
                case 26:
                case 27:
                    return GREEN_PLAYER;
                case 35:
                    return WHITE_FIELD;
                case 36:
                    return YELLOW_PLAYER;
                case 37:
                case 50:
                    return WHITE_FIELD;
                case 51:
                    return YELLOW_PLAYER;
                case 52:
                case 65:
                    return WHITE_FIELD;
                case 66:
                    return YELLOW_PLAYER;
                case 67:
                    return WHITE_FIELD;
                case 76:
                case 77:
                case 78:
                case 79:
                case 80:
                    return WHITE_FIELD;
                case 81:
                    return YELLOW_PLAYER;
                case 82:
                case 83:
                case 84:
                case 85:
                    return WHITE_FIELD;
                case 86:
                    return GREEN_PLAYER;
                case 91:
                    return WHITE_FIELD;
                case 92:
                case 93:
                case 94:
                case 95:
                    return RED_PLAYER;
                case 97:
                case 98:
                case 99:
                case 100:
                    return GREEN_PLAYER;
                case 101:
                    return WHITE_FIELD;
                case 106:
                    return RED_PLAYER;
                case 107:
                case 108:
                case 109:
                case 110:
                    return WHITE_FIELD;
                case 111:
                    return BLUE_PLAYER;
                case 112:
                case 113:
                case 114:
                case 115:
                case 116:
                    return WHITE_FIELD;
                case 125:
                    return WHITE_FIELD;
                case 126:
                    return BLUE_PLAYER;
                case 127:
                    return WHITE_FIELD;
                case 140:
                    return WHITE_FIELD;
                case 141:
                    return BLUE_PLAYER;
                case 142:
                    return WHITE_FIELD;
                case 155:
                    return WHITE_FIELD;
                case 156:
                    return BLUE_PLAYER;
                case 157:
                    return WHITE_FIELD;
                case 165:
                case 166:
                    return RED_PLAYER;
                case 170:
                case 171:
                    return WHITE_FIELD;
                case 172:
                case 176:
                case 177:
                    return BLUE_PLAYER;
                case 180:
                case 181:
                    return RED_PLAYER;
                case 191:
                case 192:
                    return BLUE_PLAYER;


                default:
                    break;

            }
        }

        return null;
    }

}
