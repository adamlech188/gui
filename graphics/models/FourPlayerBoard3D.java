package cs3744.graphics.models;

import cs3744.graphics.common.Scaling;
import cs3744.graphics.common.Translation;
import cs3744.graphics.interfaces.IPrimitive;
import cs3744.graphics.models.interfaces.IGame;
import cs3744.graphics.models.interfaces.IGamePiece;
import cs3744.graphics.primitives.Cube;
import cs3744.graphics.primitives.Cylinder;
import cs3744.graphics.primitives.Primitive;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.media.opengl.GL2;

// -------------------------------------------------------------------------
/**
 * Class that stores all the necessary data to draw and organize four player
 * board.
 *
 * @author Adam Lech (PID: adaml8)
 * @version Aug 10, 2012
 */
public class FourPlayerBoard3D
    extends Primitive
    implements IGame
{
    private final float      border = 40.0f;
    private List<IGamePiece> gamepieces;
    private final Lock       gamepiecesLock;
    private final Lock       gamepieceLock;


    // ----------------------------------------------------------
    /**
     * Create a new instance of FourPlayerBoard3D class.
     */
    public FourPlayerBoard3D()
    {
        this(null);

    }


    // ----------------------------------------------------------
    /**
     * Copy constructor.
     *
     * @param primitiveToCopy
     * @param parent
     */
    public FourPlayerBoard3D(
        FourPlayerBoard3D primitiveToCopy,
        IPrimitive parent)
    {
        super(primitiveToCopy, parent);
        gamepieces = new ArrayList<IGamePiece>();
        for (IGamePiece gamePiece : this.getGamePieces())
        {
            gamepieces.add(gamePiece);
        }
        gamepiecesLock = new ReentrantLock();
        gamepieceLock = new ReentrantLock();

    }


    // ----------------------------------------------------------
    /**
     * Create a new instance with the provided parent.
     *
     * @param parent
     */
    public FourPlayerBoard3D(IPrimitive parent)
    {
        super(parent);
        this.setTranslation(new Translation(280f, 280f, 0f));
        gamepieces = new ArrayList<IGamePiece>();
        gamepiecesLock = new ReentrantLock();
        gamepieceLock = new ReentrantLock();

    }


    /**
     * Provides a copy of the current object.
     */
    @Override
    public IPrimitive copy()
    {

        return this.copy(null);
    }


    /**
     * Provides a copy of the current object and sets its parent.
     */
    @Override
    public IPrimitive copy(IPrimitive parent)
    {

        return new FourPlayerBoard3D(this, parent);
    }


    /**
     * Paints the primitive.
     */
    public void paint(GL2 gl2)
    {
        Cube boardBackground = new Cube(this);
        boardBackground.setMaterial(MaterialLookupTable.BORDER_MATERIAL);
        boardBackground.setColor(ColorLookupTable.BORDER_COLOR);
        boardBackground.setTranslation(new Translation(0f, 0f, -70f));
        boardBackground.setSolid(true);
        boardBackground.setScaling(new Scaling(560f, 560f, 70f));
        boardBackground.paint(gl2);
        Cube playingBoard = new Cube(this);
        playingBoard.setTranslation(new Translation(0f, 0f, -25f));
        playingBoard.setScaling(new Scaling(560f - 40f, 560 - 40f, 25f));
        playingBoard.setColor(ColorLookupTable.BOARD_COLOR);
        playingBoard.setMaterial(MaterialLookupTable.BOARD_MATERIAL);
        playingBoard.setSolid(true);
        playingBoard.paint(gl2);

        for (int x = 0; x < 13; x++)
        {
            for (int y = 0; y < 13; y++)
            {
                if (MaterialLookupTable.fourPlayerBoardGridMaterial(x, y) != null
                    || ColorLookupTable.fourPlayerBoardGridColor(x, y) != null)
                {
                    Cylinder playingField = new Cylinder(this);
                    playingField.setScaling(new Scaling(15f, 15f, 7.5f));
                    Translation translation =
                        new Translation(40f + border * x - 280f, 40f + border
                            * y - 280f, -7.5f);
                    playingField.setTranslation(translation);
                    playingField.setMaterial(MaterialLookupTable
                        .fourPlayerBoardGridMaterial(x, y));
                    playingField.setColor(ColorLookupTable
                        .fourPlayerBoardGridColor(x, y));
                    playingField.paint(gl2);

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
                        new Translation(40f + border * xCoordinate - 280f, 40f
                            + border * yCoordinate - 280f, 32.5f);
                    Scaling gamepiecescaling = new Scaling(7.5f, 7.5f, 40f);
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

    }


    /**
     * Returns an unmodifiable list of game pieces.
     */
    @Override
    public List<IGamePiece> getGamePieces()
    {
        List<IGamePiece> returnValue;
        try
        {
            gamepiecesLock.lock();
            returnValue = Collections.unmodifiableList(gamepieces);

        }
        finally
        {
            gamepiecesLock.unlock();
        }
        return returnValue;
    }


    /**
     * Adds a game piece to the list of game pieces.
     */
    @Override
    public void addGamePiece(IGamePiece gamePiece)
    {
        try
        {
            gamepiecesLock.lock();
            gamepieces.add(gamePiece);
            gamePiece.setParent(this);

        }
        finally
        {
            gamepiecesLock.unlock();
        }
    }


    /**
     * Removes a game piece to the list of game pieces.
     */
    @Override
    public void removeGamePiece(IGamePiece gamePiece)
    {
        try
        {
            gamepiecesLock.lock();
            gamepieces.remove(gamePiece);

        }
        finally
        {
            gamepiecesLock.unlock();
        }
    }


    /**
     * Clears the list of game pieces.
     */
    @Override
    public void clearGamePieces()
    {
        try
        {
            gamepiecesLock.lock();
            gamepieces.clear();

        }
        finally
        {
            gamepiecesLock.unlock();
        }
    }

}
