/**
 * 
 */
package cs3744.homework4.controllers;


import cs3744.client.HomeworkConfiguration;
import cs3744.controller.GamePieceModelViewTranslation;
import cs3744.graphics.interfaces.IColor;
import cs3744.graphics.interfaces.IMaterial;
import cs3744.graphics.models.ColorLookupTable;
import cs3744.gui.interfaces.IGameBoardComponent.GameBoardType;
import cs3744.homework4.controllers.interfaces.IGamePieceController;
import cs3744.homework4.views.GameScreen;
import cs3744.model.events.interfaces.IDataChangeEvent;
import cs3744.model.interfaces.IGamePiece;


/**
 * The <CODE>GamePieceController</Code> class links <CODE>IGamePiece</CODE>
 * model and view.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class GamePieceController
        implements IGamePieceController {


    private final GameScreen gui;
    private final IGamePiece model;
    private final cs3744.graphics.models.interfaces.IGamePiece view;
    private final GameBoardType type;
    private final HomeworkConfiguration configuration;


    /*
     * (non-Javadoc)
     * 
     * @see
     * cs3744.homework4.controllers.interfaces.IGamePieceController#getModel()
     */
    @Override
    public synchronized IGamePiece getModel() {

        return this.model;
    }


    /* (non-Javadoc)
     * @see cs3744.homework4.controllers.interfaces.IGamePieceController#getView()
     */
    @Override
    public synchronized cs3744.graphics.models.interfaces.IGamePiece getView() {

        return this.view;
    }


    /**
     * Constructs a <CODE>GamePieceController</CODE> that links the model with
     * the view.
     * 
     * @param model
     *            the model.
     * @param gui
     *            the gui.
     * @param type
     *            the type of the game piece to create
     * @param configuration
     *            the configuration to use.
     */
    public GamePieceController(IGamePiece model, GameScreen gui,
            GameBoardType type, HomeworkConfiguration configuration) {

        this.type = type;
        this.model = model;
        this.view = this.createGamePieceView();
        this.gui = gui;
        this.configuration = configuration;

        this.configureGamePiece();
        this.model.addDataChangeListener(this);

        this.gui.invalidate();
    }


    @Override
    public synchronized void dataChanged(IDataChangeEvent event) {

        if (event.getSource().equals(this.model)) {
            if ("position".equals(event.getDataName())
                    || "state".equals(event.getDataName())) {

                int xCoordinate = GamePieceModelViewTranslation
                        .getViewXCoordinate(this.model);
                int yCoordinate = GamePieceModelViewTranslation
                        .getViewYCoordinate(this.model);

                this.view.setXPosition(xCoordinate);
                this.view.setYPosition(yCoordinate);
            }
        }
        this.gui.invalidate();
    }


    private cs3744.graphics.models.interfaces.IGamePiece createGamePieceView() {

        cs3744.graphics.models.interfaces.IGamePiece theView = null;
        switch (this.type) {
            case REFERENCE_2D: {
                theView = new cs3744.graphics.models.solution.GamePiece();
            }
                break;
            case REFERENCE_3D: {
                theView = new cs3744.graphics.models.solution.GamePiece3D();
            }
                break;
            case STUDENT_2D:
                theView = new cs3744.graphics.models.GamePiece();
                break;
            case STUDENT_3D:
                theView = new cs3744.graphics.models.GamePiece3D();
                break;
            default:
                break;

        }

        return theView;
    }


    private void configureGamePiece() {

        IColor playerColor = null;
        IMaterial playerMaterial = null;
        switch (this.model.getGame().getPlayerRole(this.model.getOwner())) {
            case PLAYER_ONE:
                playerColor = ColorLookupTable.YELLOW_PLAYER;
                if (this.configuration.useStudentMaterial()) {

                    playerMaterial = cs3744.graphics.models.MaterialLookupTable.YELLOW_PLAYER;
                }
                else {

                    playerMaterial = cs3744.graphics.models.solution.MaterialLookupTable.YELLOW_PLAYER;
                }
                break;
            case PLAYER_TWO:
                playerColor = ColorLookupTable.RED_PLAYER;
                if (this.configuration.useStudentMaterial()) {

                    playerMaterial = cs3744.graphics.models.MaterialLookupTable.RED_PLAYER;
                }
                else {

                    playerMaterial = cs3744.graphics.models.solution.MaterialLookupTable.RED_PLAYER;
                }
                break;
            case PLAYER_THREE:
                playerColor = ColorLookupTable.BLUE_PLAYER;
                if (this.configuration.useStudentMaterial()) {

                    playerMaterial = cs3744.graphics.models.MaterialLookupTable.BLUE_PLAYER;
                }
                else {

                    playerMaterial = cs3744.graphics.models.solution.MaterialLookupTable.BLUE_PLAYER;
                }
                break;
            case PLAYER_FOUR:
                playerColor = ColorLookupTable.GREEN_PLAYER;
                if (this.configuration.useStudentMaterial()) {

                    playerMaterial = cs3744.graphics.models.MaterialLookupTable.GREEN_PLAYER;
                }
                else {

                    playerMaterial = cs3744.graphics.models.solution.MaterialLookupTable.GREEN_PLAYER;
                }
                break;
            case NONE:
            case OBSERVER:
                break;
            default:
                break;

        }


        this.view.setColor(playerColor);
        this.view.setMaterial(playerMaterial);
        this.view.setXPosition(GamePieceModelViewTranslation
                .getViewXCoordinate(this.model));
        this.view.setYPosition(GamePieceModelViewTranslation
                .getViewYCoordinate(this.model));
    }


}
