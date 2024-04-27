import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Renderer {

    static final int HEIGHT = 1080;
    static final int WIDTH = 1920;
    static final int FOV = 90;
    static final boolean SHOW_AXIS_LINES = false;
    static final Color BACKGROUND_COLOR = Color.BLACK;
    static final boolean ROTATE_MODE = true;
    static final int TARGET_FPS = 60;
    // Overrides Target FPS
    static final boolean FPS_TEST = false;
    static final boolean DRAW_FACES = false;

    public static void main(String[] args)
    {


        final double DRAG_SPEED = 50;
        final double ASPECT_RATIO = (double) HEIGHT / WIDTH;
        final double FOV_RAD = 1.0 / (Math.tan(Math.toRadians(FOV / 2.0)));
        final double Z_NEAR = 0.1;
        final double Z_FAR = 1000;
        final double Q = Z_FAR/(Z_FAR - Z_NEAR);

        Point mouse = new Point(0, 0);
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
                double pitch = Math.toRadians(mouse.y);
                Matrix4 z_r_transform = new Matrix4(new double[][]{
                        {Math.cos(pitch), Math.sin(pitch), 0, 0},
                        {-Math.sin(pitch), Math.cos(pitch), 0 , 0},
                        {0, 0, 1, 0},
                        {0, 0, 0, 1}
                });

                double roll = Math.toRadians(mouse.x);
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

                    mouse.x = e.getX() * xi;
                    mouse.y = e.getY() * yi;

                    renderingPanel.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
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

            long trueStartTime = System.currentTimeMillis();
            long captureInterval = 100;
            long captureDelayFromStart = 500;
            long lastCapture = trueStartTime + captureDelayFromStart - captureInterval;
            int frames_to_capture = 100;
            int frames_captured = 0;

            boolean drawn_gif = false;
            BufferedImage[] captured_frames = new BufferedImage[frames_to_capture];

            while (true) {
                renderingPanel.repaint();
                frames++;

                endTime = System.currentTimeMillis();

                if (frames_captured < frames_to_capture && endTime - lastCapture >= captureInterval) {
                    lastCapture = endTime;

                    BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = bi.createGraphics();
                    renderingPanel.print(g);
                    g.dispose();

                    int center_x = WIDTH/2;
                    int center_y = HEIGHT/2;
                    int box_size = 400;
                    int left_x = center_x - box_size/2;
                    int left_y = center_y - box_size/2;
                    captured_frames[frames_captured] = bi.getSubimage(left_x, left_y, box_size, box_size);

                    frames_captured++;              
                } else if (!drawn_gif && frames_captured == frames_to_capture) {
                    drawn_gif = true;
                    Runnable thread = () -> {
                        System.out.println("---------------------------------Started drawing gif!");
                        try {
                            Giffer.generateFromBI(captured_frames, "render.gif", 1, true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("---------------------------------Finished drawing gif!");
                    };
                    Thread run = new Thread(thread);
                    run.start();
                }

                double deltaTime = (double) (endTime - startTime);
                if (deltaTime >= 1000)
                {
                    double FPS = frames / (deltaTime/1000);
                    System.out.println("FPS: " + FPS);
                    if (!drawn_gif)
                        System.out.println("Captured frames: " + Integer.toString(frames_captured));

                    frames = 0;
                    startTime = System.currentTimeMillis();
                }

                mouse.x = (System.currentTimeMillis() / 100.0) * 4;
                mouse.y = (System.currentTimeMillis() / 100.00) * 4;

                try {
                    Thread.sleep(sleep_time);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
    }
}




