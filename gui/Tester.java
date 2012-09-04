/**
 *
 */
package cs3744.gui;



import javax.media.opengl.fixedfunc.GLLightingFunc;

import cs3744.graphics.common.Operations;
import cs3744.graphics.common.Translation;
import cs3744.graphics.common.solution.Camera;
import cs3744.graphics.common.solution.Color;
import cs3744.graphics.common.solution.PositionalLightSource;
import cs3744.graphics.common.solution.ProjectionMode;
import cs3744.graphics.common.solution.Rotation;
import cs3744.graphics.common.solution.ShadingModel;
import cs3744.graphics.common.solution.Vector;
import cs3744.graphics.interfaces.IColor;
import cs3744.graphics.models.interfaces.IGame;
import cs3744.graphics.models.solution.FourPlayerBoard3D;
import cs3744.gui.common.Dimension;
import cs3744.gui.common.Point;
import cs3744.gui.events.interfaces.IActionEvent;
import cs3744.gui.events.solution.ActionAdapter;
import cs3744.gui.interfaces.IButton;
import cs3744.gui.interfaces.IComponent;
import cs3744.gui.interfaces.IFrame;
import cs3744.gui.interfaces.ILabel;
import cs3744.gui.interfaces.IPanel;
import cs3744.gui.interfaces.ITextArea;
import cs3744.gui.interfaces.ITextField;
import cs3744.gui.solution.Button;
import cs3744.gui.solution.Component3D;
import cs3744.gui.solution.Frame;
import cs3744.gui.solution.GridLayout;
import cs3744.gui.solution.Label;
import cs3744.gui.solution.Panel;
import cs3744.gui.solution.TextArea;
import cs3744.gui.solution.TextField;


/**
 * A class for testing GUI components.
 *
 * @author Peter J. Radics
 * @version 1.0
 *
 */
public class Tester {

    private ILabel label;
    private IFrame testFrame;

    private IPanel panel;

    private ITextField textField;
    private IButton button;
    private ITextArea textArea;


    /**
     * @return the label
     */
    public ILabel getLabel() {

        return this.label;
    }


    /**
     * @param label
     *            the label to set
     */
    public void setLabel(ILabel label) {

        this.label = label;
    }


    /**
     * @return the textArea
     */
    public ITextArea getTextArea() {

        return this.textArea;
    }


    /**
     * @param textArea
     *            the textArea to set
     */
    public void setTextArea(TextArea textArea) {

        this.textArea = textArea;
    }


    /**
     * @return the textField
     */
    public ITextField getTextField() {

        return this.textField;
    }


    /**
     * @param textField
     *            the textField to set
     */
    public void setTextField(ITextField textField) {

        this.textField = textField;
    }


    /**
     * @return the button
     */
    public IButton getButton() {

        return this.button;
    }


    /**
     * @param button
     *            the button to set
     */
    public void setButton(IButton button) {

        this.button = button;
    }


    /**
     * Constructs a new <CODE>GameScreen</CODE>.
     */
    public Tester() {

        this.testFrame = new Frame("GameScreen");

        int width = 800;
        int height = 600;

        this.testFrame.setSize(new Dimension(width, height));
        this.testFrame.setName("My Frame");

        GridLayout frameLayout = new GridLayout(2, 2);

        this.testFrame.setLayout(frameLayout);


        this.textArea = new TextArea();
        this.textArea.setName("MyTextArea");
        GridLayout panelLayout = new GridLayout(3, 1, 50, 50);
        this.panel = new Panel(panelLayout);


        this.label = new Label("Hello\nWorld!");
        this.label.setLocation(new Point(width / 2 - 132 / 2,
                height / 2 - 16 / 2));

        this.label.setSize(new Dimension(132, 16));
        this.label.setName("MyLabel");

        this.label.setBorderWidth(1);
        this.panel.add(this.label);

        this.textField = new TextField();
        this.textField.setSize(new Dimension(132, 16));
        this.textField.setName("MyTextfield");
        this.textField.setBorderWidth(1);

        this.panel.add(this.textField);

        this.textField.addActionListener(new ActionAdapter() {

            @Override
            public void actionPerformed(IActionEvent event) {

                if (event.getSource() instanceof ITextField) {

                    ITextField aTextField = (ITextField) event.getSource();

                    Tester.this.getTextArea().appendText(aTextField.getText());
                    aTextField.setText("");
                }

            }
        });

        this.button = new Button("Send");
        this.button.setName("myButton");
        this.button.setSize(new Dimension(160, 16));
        this.button.addActionListener(new ActionAdapter() {

            @Override
            public void actionPerformed(IActionEvent event) {

                if (event.getSource() instanceof IButton) {

                    if (Tester.this.getTextField().getText() != "") {

                        Tester.this.getTextArea().appendText(
                                Tester.this.getTextField().getText());
                    }

                    Tester.this.getTextField().setText("");
                }

            }
        });
        this.panel.add(this.button);


        this.testFrame.add(this.createIsometric3DViewComponent());

        this.testFrame.add(this.textArea);
        this.testFrame.add(this.create2DViewComponent());
        this.testFrame.add(this.panel);
    }


    /**
     * Displays the tester.
     */
    public void setVisible() {

        this.testFrame.setVisible(true);
    }


    /**
     * The main method.
     *
     * @param arguments
     *            unused
     */
    public static void main(String[] arguments) {

        Tester tester = new Tester();

        tester.setVisible();

    }


    /**
     * Creates a more-or-less isometric view using a 3D game board.
     *
     * @return the isometric view in a 3d component.
     */
    public IComponent createIsometric3DViewComponent() {

        Component3D component3D = new Component3D();
        component3D.setName("My3DIsoComponent");
        // The dimensions of the frustum
        float left = 0;
        float right = 900;
        float bottom = 0;
        float top = 560;
        float near = 100;
        float far = 900;

        float cameraX = 0;
        float cameraY = 0;
        float cameraZ = 560;


        Camera camera = new Camera(new cs3744.graphics.primitives.Point(
                cameraX, cameraY, cameraZ),
                new cs3744.graphics.primitives.Point(cameraX, cameraY, 0),
                Vector.J_UNIT_VECTOR, left, right, bottom, top, near, far);
        camera.setProjectionMode(ProjectionMode.ORTHOGRAPHIC_3D);
        IGame board = new FourPlayerBoard3D();

        // board.setTranslation(new Translation (250, 250, 100));
        component3D.getScene().addPrimitive(board);
        component3D.getScene().getSceneOptions().enableDepthBuffer();
        component3D.getScene().getSceneOptions().enableShading();
        component3D.getScene().getSceneOptions()
                .setShadingModel(ShadingModel.SMOOTH);
        IColor dimLight = new Color(0.8f, 0.8f, 0.8f);
        PositionalLightSource light1 = new PositionalLightSource(
                GLLightingFunc.GL_LIGHT0);

        light1.setAmbientComponent(dimLight);
        light1.setDiffuseComponent(dimLight);
        light1.setSpecularComponent(dimLight);
        light1.setPosition(new cs3744.graphics.primitives.Point(560f / 2f,
                560f / 2f, 100));
        component3D.getScene().addPrimitive(light1);

        board.setRotation(new Rotation(new Vector(1f, 1f, 1f), Operations
                .degreeToRadian(-70)));
        board.setTranslation(new Translation(800f/2f, 560f/2f, 0f));

        component3D.getScene().setCamera(camera);

        return component3D;
    }


    /**
     * Creates a orthographic 3d top view using a 3D game board.
     *
     * @return the orthographic view in a 3d component.
     */
    public IComponent create2DViewComponent() {

        Component3D component3D = new Component3D();
        component3D.setName("My2DViewComponent");
        // The dimensions of the frustum
        float left = 0;
        float right = 560;
        float bottom = 0;
        float top = 560;
        float near = 100;
        float far = 900;

        float cameraX = 0;
        float cameraY = 0;
        float cameraZ = 560;


        Camera camera = new Camera(new cs3744.graphics.primitives.Point(
                cameraX, cameraY, cameraZ),
                new cs3744.graphics.primitives.Point(cameraX, cameraY, 0),
                Vector.J_UNIT_VECTOR, left, right, bottom, top, near, far);
        camera.setProjectionMode(ProjectionMode.ORTHOGRAPHIC_3D);
        IGame board = new FourPlayerBoard3D();

        board.setTranslation(new Translation(560f/2f, 560f/2f, 0f));
        component3D.getScene().addPrimitive(board);
        component3D.getScene().getSceneOptions().enableDepthBuffer();
        component3D.getScene().getSceneOptions().enableShading();
        component3D.getScene().getSceneOptions()
                .setShadingModel(ShadingModel.SMOOTH);
        IColor dimLight = new Color(0.8f, 0.8f, 0.8f);
        PositionalLightSource light1 = new PositionalLightSource(
                GLLightingFunc.GL_LIGHT0);

        light1.setAmbientComponent(dimLight);
        light1.setDiffuseComponent(dimLight);
        light1.setSpecularComponent(dimLight);
        light1.setPosition(new cs3744.graphics.primitives.Point(560f / 2f,
                560f / 2f, 100));
        component3D.getScene().addPrimitive(light1);


        component3D.getScene().setCamera(camera);

        return component3D;
    }
}
