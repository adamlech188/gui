package cs3744.homework3;


import cs3744.graphics.common.PositionalLightSource;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.swing.JFrame;

import cs3744.client.HomeworkConfiguration;
import cs3744.client.interfaces.IGameGUI;
import cs3744.controller.IGameController;
import cs3744.graphics.common.solution.Color;
import cs3744.graphics.common.solution.ProjectionMode;
import cs3744.graphics.common.solution.Scene;
import cs3744.graphics.common.solution.ShadingModel;
import cs3744.graphics.common.solution.Vector;
import cs3744.graphics.interfaces.ICamera;
import cs3744.graphics.interfaces.IColor;
import cs3744.graphics.interfaces.IPositionalLightSource;
import cs3744.graphics.primitives.solution.Point;


/**
 * The GUI for Homework Three.
 *
 * @author Peter J. Radics
 * @version 1.0
 *
 */
public class HomeworkThreeGUI
        implements IGameGUI {

    private final HomeworkConfiguration configuration;
    private final Scene scene;
    @SuppressWarnings("unused")
    private IGameController controller;

    private JFrame jframe;


    /**
     * Returns the JFrame.
     *
     * @return the JFrame.
     */
    protected JFrame getJFrame() {

        return this.jframe;
    }


    /**
     * @param jframe
     *            the jframe to set
     */
    public void setJFrame(JFrame jframe) {

        this.jframe = jframe;
    }


    @Override
    public Scene getScene() {

        return this.scene;
    }


    /**
     * Constructs a new GUI for Homework Three.
     *
     * @param configuration
     */
    public HomeworkThreeGUI(HomeworkConfiguration configuration) {


        this(configuration, new Scene());
    }


    /**
     * Constructs a new GUI for Homework Three and sets its scene to the
     * provided parameter.
     *
     * @param configuration
     *
     * @param scene
     *            the scene to set.
     */
    public HomeworkThreeGUI(HomeworkConfiguration configuration, Scene scene) {

        this.configuration = configuration;
        this.scene = scene;

        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities(glprofile);
        GLJPanel gljpanel = new GLJPanel(glcapabilities);

        // The dimensions of the frustum
        float left = 0;
        float right = 560;
        float bottom = 0;
        float top = 560;
        float near = 1;
        float far = 1000;

        ICamera camera = null;
        if (this.configuration.useStudentCamera()) {
            camera = new cs3744.graphics.common.Camera(new Point(0, 0, 560),
                    new Point(0, 0, 0), Vector.J_UNIT_VECTOR, left, right,
                    bottom, top, near, far);
        }
        else {

            camera = new cs3744.graphics.common.solution.Camera(new Point(0, 0,
                    560), new Point(0, 0, 0), Vector.J_UNIT_VECTOR, left,
                    right, bottom, top, near, far);
        }
        camera.setProjectionMode(ProjectionMode.ORTHOGRAPHIC_3D);

        this.getScene().setCamera(camera);
        this.getScene().getSceneOptions().enableShading();
        this.getScene().getSceneOptions().setShadingModel(ShadingModel.SMOOTH);



        IColor dimLight = new Color(0.8f, 0.8f, 0.8f);
        IPositionalLightSource light1 = null;
        if (this.configuration.useStudentLights()) {

            light1 = new PositionalLightSource(
                    GLLightingFunc.GL_LIGHT0);
        }
        else {
            light1 = new cs3744.graphics.common.solution.PositionalLightSource(
                    GLLightingFunc.GL_LIGHT0);
        }


        light1.setAmbientComponent(dimLight);
        light1.setDiffuseComponent(dimLight);
        light1.setSpecularComponent(dimLight);
        light1.setPosition(new Point(560f / 2f, 560f / 2f, 400));
        this.getScene().addPrimitive(light1);

        gljpanel.addGLEventListener(this.getScene());

        this.setJFrame(new JFrame("Homework Three"));
        this.getJFrame().addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent windowevent) {

                getJFrame().dispose();
                System.exit(0);
            }
        });

        this.getJFrame().getContentPane().add(gljpanel, BorderLayout.CENTER);
        this.getJFrame().setSize(560, 560);
    }


    @Override
    public void display() {

        this.getJFrame().setVisible(true);
    }


    @Override
    public void repaint() {

        this.jframe.repaint();
    }


    @Override
    public void setController(IGameController controller) {

        this.controller = controller;

    }
}
