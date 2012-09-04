package cs3744.graphics.snippets;


import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import cs3744.graphics.common.solution.*;
import cs3744.graphics.interfaces.*;
import cs3744.graphics.models.solution.ColorLookupTable;
import cs3744.graphics.models.solution.FourPlayerBoard3D;
import cs3744.graphics.models.solution.GamePiece3D;
import cs3744.graphics.models.solution.MaterialLookupTable;
// import cs3744.graphics.models.solution.FourPlayerBoard;
import cs3744.graphics.primitives.solution.*;


/**
 * A simple class for drawing some points.
 * 
 * @author Peter J. Radics.
 * @version 1.0
 * 
 */
@SuppressWarnings("deprecation")
public class Tester
        implements GLEventListener {

    private static int step = 0;
    // static Cylinder cylinder = new Cylinder();

    static {
        // setting this true causes window events not to get sent on Linux if
        // you run from inside Eclipse
        GLProfile.initSingleton(false);
    }


    @Override
    public void init(GLAutoDrawable glautodrawable) {

        GL2 gl2 = glautodrawable.getGL().getGL2();

        //
        // Uncomment this to play around with alpha blending
        //
        // gl2.glEnable(GL.GL_BLEND);
        // gl2.glDepthMask(false);
        // gl2.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

        //
        // Uncomment this to test back face culling
        //
        gl2.glFrontFace(GL.GL_CCW);
        gl2.glCullFace(GL.GL_BACK);
        gl2.glEnable(GL.GL_CULL_FACE);

        //
        // Uncomment this to test depth buffer
        //
        gl2.glEnable(GL.GL_DEPTH_TEST);
        gl2.glDepthFunc(GL.GL_LEQUAL);
        gl2.glDepthMask(true);
        gl2.glClear(GL.GL_DEPTH_BUFFER_BIT);


        //
        // Uncomment this to test lighting and materials
        //
        Primitive.lightingEnabled = true;

        //
        // Uncomment this to test lighting and materials
        //
        gl2.glEnable(GLLightingFunc.GL_LIGHTING);
        gl2.glLightModeli(GL2.GL_LIGHT_MODEL_LOCAL_VIEWER, GL.GL_TRUE);
        gl2.glLightModeli(GL2ES1.GL_LIGHT_MODEL_TWO_SIDE, GL.GL_FALSE);
        // Use this for smooth shading
        gl2.glShadeModel(GLLightingFunc.GL_SMOOTH);
        // Use this for flat shading
        // gl2.glShadeModel(GLLightingFunc.GL_FLAT);

        IColor dimLight = new Color(0.8f, 0.8f, 0.8f);
        IColor noLight = new Color(0.0f, 0.0f, 0.0f);
        PositionalLightSource light1 = new PositionalLightSource(
                GLLightingFunc.GL_LIGHT0);

        light1.setAmbientComponent(noLight);
        light1.setDiffuseComponent(dimLight);
        light1.setSpecularComponent(dimLight);
        light1.setPosition(new Point(100, 100, 0));
        light1.paint(gl2);


    }


    @Override
    public void dispose(GLAutoDrawable glautodrawable) {

        // Nothing to do.
    }


    @Override
    public void reshape(GLAutoDrawable glautodrawable, int x, int y, int width,
            int height) {

        GL2 gl2 = glautodrawable.getGL().getGL2();

        GLU glu = new GLU();


        gl2.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl2.glLoadIdentity();


        // The dimensions of the frustum
        float left = 0;
        float right = 560;
        float bottom = 0;
        float top = 560;
        float near = 1;
        float far = 1000;


        // Use this for 2D orthographic projection
        // glu.gluOrtho2D(left, right, bottom, top);

        // Use this to do 3D orthographic projection
        gl2.glOrtho(left, right, bottom, top, near, far);

        // Use this to do 3D perspective projection
        // gl2.glFrustumf(left, right, bottom, top, near, far);


        float projectionMatrix[] = new float[16];
        gl2.glGetFloatv(GLMatrixFunc.GL_PROJECTION_MATRIX, projectionMatrix, 0);

        String returnValue = "\n|";

        for (int v = 0; v < 16; v++) {
            if (v > 0 && v % 4 == 0) {
                returnValue += "\t\t|\n|";
            }
            returnValue += "\t\t" + String.format("%.3f", projectionMatrix[v]);
        }

        returnValue += "\t\t|\n";

        System.out.println("ProjectionMatrix: \n" + returnValue);

        // makes the modelview matrix the state machine's current matrix.
        gl2.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        // Loads the unit matrix into the state machine's current matrix.
        gl2.glLoadIdentity();


        // Use this command to play around with the camera
        // glu.gluLookAt (eyeX, eyeY, eyeZ, lookAtX, lookAtY, lookAtZ, upX, upY,
        // upZ);
        float xl = 0;
        float yl = 0;

        glu.gluLookAt(xl, yl, 560, xl, yl, 0, 0, 1, 0);
        // Use this command to play around with viewports.
        gl2.glViewport(0, 0, width, height);


        float modelviewMatrix[] = new float[16];
        gl2.glGetFloatv(GLMatrixFunc.GL_MODELVIEW_MATRIX, modelviewMatrix, 0);

        returnValue = "\n|";

        for (int v = 0; v < 16; v++) {
            if (v > 0 && v % 4 == 0) {
                returnValue += "\t\t|\n|";
            }
            returnValue += "\t\t" + String.format("%.3f", modelviewMatrix[v]);
        }

        returnValue += "\t\t|\n";

        System.out.println("ModelViewMatrix: \n" + returnValue);

        Camera camera2 = new Camera(new Point(xl, yl, 2), new Point(xl, yl, 0),
                Vector.J_UNIT_VECTOR, left, right, bottom, top, near, far);
        camera2.setProjectionMode(ProjectionMode.ORTHOGRAPHIC_3D);

        System.out.println("Camera Projection COS:\n"
                + camera2.getProjectionTransformation().toString());
        System.out.println("Camera View COS:\n"
                + camera2.getViewingTransformation().toString());
    }


    @Override
    public void display(GLAutoDrawable glautodrawable) {

        GL2 gl2 = glautodrawable.getGL().getGL2();


        // Put whatever you want to display here!

        gl2.glClearColor(1, 1, 1, 1);

        // Clears the screen by zeroing out the frame buffer's color information
        gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);


        Scaling scaling = new Scaling(50, 50, 50);

        float angle = Operations.degreeToRadian(1);

        Rotation rotation = new Rotation(new Vector(1f, 1f, 1f), angle * step);

        Translation translation = new Translation(250, 250, 10);

        Translation translation2 = new Translation(350, 350, 10);
        step++;

        Cylinder cylinder = new Cylinder();

        cylinder.setColor(Color.ORANGE);
        cylinder.setMaterial(Material.BRONZE);
        cylinder.setTranslation(translation);

        cylinder.setScaling(scaling);
        cylinder.setRotation(rotation);

        cylinder.paint(gl2);


        Cube cube = new Cube();
        cube.setColor(Color.YELLOW);
        cube.setMaterial(Material.GOLD);

        cube.setTranslation(translation2);
        cube.setScaling(scaling);
        cube.setRotation(rotation);
        // cube.setSolid(false);

        cube.paint(gl2);

        FourPlayerBoard3D board = new FourPlayerBoard3D();
        board.setRotation(rotation);

        GamePiece3D gamePiece = new GamePiece3D();
        gamePiece.setColor(ColorLookupTable.YELLOW_PLAYER);
        gamePiece.setMaterial(MaterialLookupTable.YELLOW_PLAYER);
        gamePiece.setXPosition(1);
        gamePiece.setYPosition(1);

        board.addGamePiece(gamePiece);
        // board.setSolid(false);

        // board.paint(gl2);
    }


    /**
     * Main method
     * 
     * @param args
     *            Arguments (unused)
     */
    public static void main(String[] args) {


        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities(glprofile);
        glcapabilities.setHardwareAccelerated(true);
        GLJPanel gljpanel = new GLJPanel(glcapabilities);


        Tester pointDrawer = new Tester();

        gljpanel.addGLEventListener(pointDrawer);

        final JFrame jframe = new JFrame("GameScreen");
        jframe.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent windowevent) {

                jframe.dispose();
                System.exit(0);
            }
        });

        jframe.getContentPane().add(gljpanel, BorderLayout.CENTER);
        jframe.setSize(560, 560);
        jframe.setVisible(true);
    }
}
