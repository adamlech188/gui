/**
 *
 */
package cs3744.graphics.models;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import cs3744.graphics.common.Scaling;
import cs3744.graphics.common.Translation;
import cs3744.graphics.primitives.CircleWithBorder;
import cs3744.graphics.primitives.Rectangle;
import cs3744.graphics.primitives.Point;
import java.util.Collections;
import java.util.ArrayList;
import cs3744.graphics.interfaces.IPrimitive;
import java.util.List;
import javax.media.opengl.GL2;
import cs3744.graphics.models.interfaces.IGame;
import cs3744.graphics.models.interfaces.IGamePiece;
import cs3744.graphics.primitives.Primitive;

/**
 * This class contains all data and methods necessary to draw a board with four
 * players.
 *
 * @author Adam Lech(PID: adaml8)
 * @version 1.0
 */
public class FourPlayerBoard
    extends Primitive
    implements IGame
{
    private final float      size   = 560.0f;
    private final float      border = 40.0f;
    private List<IGamePiece> gamepieces;
    private final Lock       gamepieceLock;


    // ----------------------------------------------------------
    /**
     * Creates four player board.
     */
    public FourPlayerBoard()
    {
        this(null);
    }


    // ----------------------------------------------------------
    /**
     * Creates four player board with the parent.
     * @param parent
     */
    public FourPlayerBoard(IPrimitive parent)
    {
        super(parent);
        gamepieces = new ArrayList<IGamePiece>();
        gamepieceLock = new ReentrantLock();

    }


    // ----------------------------------------------------------
    /**
     * Creates a copy of the four player board.
     *
     * @param primitiveToCopy
     * @param parent
     */
    public FourPlayerBoard(FourPlayerBoard primitiveToCopy, IPrimitive parent)
    {
        super(parent);
        gamepieces = new ArrayList<IGamePiece>();
        for (IGamePiece gamePiece : this.getGamePieces())
        {
            gamepieces.add(gamePiece);
        }
        gamepieceLock = new ReentrantLock();

    }


    /**
     * Paints board using rectangle and circles with border.
     *
     * @param gl2
     *            - OpenGL state machine
     */
    public void paint(GL2 gl2)
    {

        Point point1 = new Point(0f, 0f, 0f);
        Point point2 = new Point(size, 0f, 0f);
        Point point3 = new Point(size, size, 0f);
        Point point4 = new Point(0f, size, 0f);
        // Draws rectangle that contains all the game pieces and play fields.
        Rectangle rectangle =
            new Rectangle(point1, point2, point3, point4, this);
        rectangle.setSolid(true);
        rectangle.setColor(ColorLookupTable.BOARD_COLOR);
        rectangle.paint(gl2);

        CircleWithBorder circlewithBorder = new CircleWithBorder();
        circlewithBorder.setSolid(true);
        Scaling scaling = new Scaling(15f, 15f, 0f);
        circlewithBorder.setScaling(scaling);
        for (int x = 0; x < 13; x++)
        {
            // Loops over board using 40px units
            for (int y = 0; y < 13; y++)
            {
                if (ColorLookupTable.fourPlayerBoardGridColor(x, y) != null)
                {
                    Translation translation =
                        new Translation(40f + border * x, 40f + border * y, 0f);
                    circlewithBorder.setTranslation(translation);
                    circlewithBorder.setColor(ColorLookupTable
                        .fourPlayerBoardGridColor(x, y));
                    circlewithBorder.paint(gl2);

                }

            }

        }

        try
        {
            gamepieceLock.lock();
            // Draws game pieces.
            for (IGamePiece gamepiece : gamepieces)
            {
                int xCoordinate = gamepiece.getXPosition();
                int yCoordinate = gamepiece.getYPosition();
                Translation translation =
                    new Translation(
                        40f + 40 * xCoordinate,
                        40f + 40 * yCoordinate,
                        0f);
                Scaling gamepiecescaling = new Scaling(7.5f, 7.5f, 0f);
                gamepiece.setTranslation(translation);
                gamepiece.setScaling(gamepiecescaling);
                gamepiece.paint(gl2);

            }
        }
        finally
        {
            gamepieceLock.unlock();
        }

    }


    /**
     * Obtains the list of game pieces.
     */
    @Override
    public List<IGamePiece> getGamePieces()
    {
        List<IGamePiece> returnList;
        try
        {
            gamepieceLock.lock();
            returnList = Collections.unmodifiableList(gamepieces);
        }
        finally
        {
            gamepieceLock.unlock();
        }
        return returnList;
    }


    /**
     * Adds game piece to the board.
     *
     * @param gamePiece
     */
    @Override
    public void addGamePiece(IGamePiece gamePiece)
    {
        try
        {
            gamepieceLock.lock();
            gamepieces.add(gamePiece);
        }
        finally
        {
            gamepieceLock.unlock();
        }

    }


    /**
     * Removes game piece from the board.
     */
    @Override
    public void removeGamePiece(IGamePiece gamePiece)
    {

        try
        {
            gamepieceLock.unlock();
            gamepieces.remove(gamePiece);
        }
        finally
        {
            gamepieceLock.unlock();
        }
    }


    /**
     * Empties board of all game pieces.
     */
    @Override
    public void clearGamePieces()
    {

        try
        {
            gamepieceLock.lock();
            gamepieces.clear();
        }
        finally
        {
            gamepieceLock.unlock();
        }

    }


    @Override
    public IPrimitive copy()
    {

        return copy(null);
    }


    @Override
    public IPrimitive copy(IPrimitive parent)
    {

        return new FourPlayerBoard(this,parent);
    }

}
