import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Renderer {

    static final int HEIGHT = 1080;
    static final int WIDTH = 1920;
    static final int FOV = 90;
    static final boolean SHOW_AXIS_LINES = false;
    static final Color BACKGROUND_COLOR = Color.BLACK;
    static final boolean ROTATE_MODE = false;
    static final int TARGET_FPS = 60;
    // Overrides Target FPS
    static final boolean FPS_TEST = false;
    static final boolean DRAW_FACES = false;


    static final boolean RENDER_GIF = true;
    static final String RENDER_FILE = "render.gif";
    // Time between frames in GIF, 100 units to 1 second
    // e.x. DELAY = 5; means a frame every 1000/100*5 = 50 ms
    static final int DELAY = 5;
    //Size (squared) that the GIF should be cropped to
    //  Center of square is at center of screen
    static final int GIF_SIZE = 250;
    //the number of frames to capture
    static final int FRAMES_TO_CAPTURE = 500;

    public static void main(String[] args)
    {


        final double DRAG_SPEED = 50;
        final double ASPECT_RATIO = (double) HEIGHT / WIDTH;
        final double FOV_RAD = 1.0 / (Math.tan(Math.toRadians(FOV / 2.0)));
        final double Z_NEAR = 0.1;
        final double Z_FAR = 1000;
        final double Q = Z_FAR/(Z_FAR - Z_NEAR);

        Camera camera = new Camera();
        ArrayList<Object3D> world_objects = new ArrayList<Object3D>();
        Vertex Camera = new Vertex(0, 0, 0);
        Vertex light_direction = new Vertex(0, 0, -1);

        // X, Y, Z Axis Lines
        if (SHOW_AXIS_LINES)
        {
            Line3D z_line = new Line3D(new Vertex[]{
                    new Vertex(0, 0, -3),
                    new Vertex(0, 0, 3)
            },
                    Color.BLUE);

            world_objects.add(z_line);

            Line3D x_line = new Line3D(new Vertex[]{
                    new Vertex(-3, 0, 0),
                    new Vertex(3, 0, 0)
            },
                    Color.RED);

            world_objects.add(x_line);

            Line3D y_line = new Line3D(new Vertex[]{
                    new Vertex(0, -3, 0),
                    new Vertex(0, 3, 0)
            },
                    Color.GREEN);

            world_objects.add(y_line);
        }

        // OTHER OBJECTS
//        Cube c = new Cube(3, Color.WHITE, new Vertex(0, 0, 0), true);
//        Cube c = new Cube();
//        c.loadFromOBJ("objs/cube.obj");
//
//        c.setFaceColor('S', Color.RED);
//        c.setFaceColor('E', Color.CYAN);
//        c.setFaceColor('N', Color.GREEN);
//        c.setFaceColor('W', Color.ORANGE);
//        c.setFaceColor('B', Color.YELLOW);
//        c.setFaceColor('T', Color.BLUE);
        Object3D c = new Object3D();

        c.loadFromOBJ("objs/tetrahedron.obj");
        c.randomRGB();


        world_objects.add(c);


        // Do the Computer Graphics
        JFrame frame = new JFrame();
        Container pane = frame.getContentPane();

        JPanel renderingPanel = new JPanel()
        {
            public void paintComponent(Graphics g)
            {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(BACKGROUND_COLOR);
                g2.fillRect(0, 0, getWidth(), getHeight());


                // Calculate POV transform
                Matrix4 map_projection = new Matrix4(new double[][]{
                        {ASPECT_RATIO * FOV_RAD, 0, 0, 0},
                        {0, FOV_RAD, 0, 0},
                        {0, 0, Q, 1},
                        {0, 0, -Z_NEAR * Q, 0}
                });

                // Calculate rotation Transforms
                double pitch = Math.toRadians(camera.userY);
                Matrix4 z_r_transform = new Matrix4(new double[][]{
                        {Math.cos(pitch), Math.sin(pitch), 0, 0},
                        {-Math.sin(pitch), Math.cos(pitch), 0 , 0},
                        {0, 0, 1, 0},
                        {0, 0, 0, 1}
                });

                double roll = Math.toRadians(camera.userX);
                Matrix4 x_r_transform = new Matrix4(new double[][]{
                        {1, 0, 0, 0},
                        {0, Math.cos(roll), Math.sin(roll), 0},
                        {0, -Math.sin(roll), Math.cos(roll), 0},
                        {0, 0, 0, 1}
                });

                // Render Stored 3D Objects
                for (Object3D object : world_objects)
                {
                    object.draw(g2, x_r_transform, z_r_transform, map_projection, Camera, light_direction);
                }
            }
        };

        // add Mouse Listener for rotating
        renderingPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!ROTATE_MODE) {
                    double yi = DRAG_SPEED / renderingPanel.getHeight();
                    double xi = DRAG_SPEED / renderingPanel.getWidth();

                    camera.userX += (e.getX() * xi);
                    camera.userY += (e.getY() * yi);

                    renderingPanel.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        });

        renderingPanel.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}
        });

        renderingPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_W:
                        camera.moveFoward();
                        break;
                    case KeyEvent.VK_A:
                        camera.moveLeft();
                        break;
                    case KeyEvent.VK_S:
                        camera.moveBack();
                        break;
                    case KeyEvent.VK_D:
                        camera.moveRight();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

            @Override
            public void keyTyped(KeyEvent e) {}
        });



        pane.add(renderingPanel, BorderLayout.CENTER);

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Rotate mode needs 60~ fps
        if (ROTATE_MODE) {
            long sleep_time = (long)Math.floor(1000.0 / TARGET_FPS);
            if (FPS_TEST)
                sleep_time = 0;

            int frames = 0;
            long startTime = System.currentTimeMillis();
            long endTime = 0;

            
            while (true) {
                renderingPanel.repaint();
                frames++;

                endTime = System.currentTimeMillis();


                double deltaTime = (double) (endTime - startTime);
                if (deltaTime >= 1000)
                {
                    double FPS = frames / (deltaTime/1000);
                    System.out.println("FPS: " + FPS);

                    frames = 0;
                    startTime = System.currentTimeMillis();
                }

                camera.userX = (System.currentTimeMillis() / 100.0) * 4;
                camera.userY = (System.currentTimeMillis() / 100.00) * 4;

                try {
                    Thread.sleep(sleep_time);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        } else if (RENDER_GIF) {
            long sleep_time = 1000/100*DELAY;

            long startTime = System.currentTimeMillis();
            long endTime = 0;

            int frames_captured = 0;

            //BufferedImage[] captured_frames = new BufferedImage[frames_to_capture];
            Giffer giffer;
            try {
                giffer = new Giffer(RENDER_FILE, DELAY, true);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            
            while (true) {
                camera.userX = (System.currentTimeMillis() / 100.0) * 4;
                camera.userY = (System.currentTimeMillis() / 100.00) * 4;
                
                long frameRenderStart = System.currentTimeMillis();

                renderingPanel.repaint();

                endTime = System.currentTimeMillis();

                BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = bi.createGraphics();
                renderingPanel.print(g);
                g.dispose();

                int center_x = WIDTH/2;
                int center_y = HEIGHT/2;
                int left_x = center_x - GIF_SIZE/2;
                int left_y = center_y - GIF_SIZE/2;
                BufferedImage cropped = bi.getSubimage(left_x, left_y, GIF_SIZE, GIF_SIZE);
                //captured_frames[frames_captured] = cropped;
                try {
                    giffer.addImage(cropped);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                frames_captured++;              

                if (frames_captured == FRAMES_TO_CAPTURE) {
                    System.out.println("---------------------------------Started drawing gif!");
                    try {
                        //Giffer.generateFromBI(captured_frames, "render.gif", 2, true);
                        giffer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("---------------------------------Finished drawing gif!");
                    System.exit(1);
                }

                double deltaTime = (double) (endTime - startTime);
                if (deltaTime >= 1000)
                {
                    System.out.println("Captured frames: " + Integer.toString(frames_captured));

                    startTime = System.currentTimeMillis();
                }

                try {
                    long currentTime = System.currentTimeMillis();
                    Thread.sleep(Math.max(0, sleep_time - (currentTime - frameRenderStart)));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
    }
}




