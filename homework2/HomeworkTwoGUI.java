package cs3744.homework2;


import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.JFrame;

import cs3744.client.interfaces.IGameGUI;
import cs3744.controller.IGameController;
import cs3744.graphics.common.solution.Camera;
import cs3744.graphics.common.solution.ProjectionMode;
import cs3744.graphics.common.solution.Scene;
import cs3744.graphics.common.solution.Vector;
import cs3744.graphics.primitives.solution.Point;


/**
 * The GUI for Homework Two.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class HomeworkTwoGUI
        implements IGameGUI {

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
     * Constructs a new GUI for Homework Two.
     */
    public HomeworkTwoGUI() {

        this(new Scene());
    }


    /**
     * Constructs a new GUI for Homework Two and sets its scene to the provided
     * parameter.
     * 
     * @param scene
     *            the scene to set.
     */
    public HomeworkTwoGUI(Scene scene) {

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
        float far = 10;

        Camera camera = new Camera(new Point(0, 0, 2), new Point(0, 0, 0),
                Vector.J_UNIT_VECTOR, left, right, bottom, top, near, far);
        camera.setProjectionMode(ProjectionMode.ORTHOGRAPHIC_3D);

        this.getScene().setCamera(camera);


        gljpanel.addGLEventListener(this.getScene());

        this.setJFrame(new JFrame("Homework Two"));
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
