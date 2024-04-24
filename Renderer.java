import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


public class Renderer {

    public static void main(String[] args)
    {
        final int HEIGHT = 1000;
        final int WIDTH = 1000;
        final double DRAG_SPEED = 180;
        final boolean SHOW_AXIS_LINES = true;
        final Color BACKGROUND_COLOR = Color.BLACK;

        Point2D mouse = new Point2D(WIDTH / 2, HEIGHT / 2);
        ArrayList<Object3D> world_objects = new ArrayList<Object3D>();

        // X, Y, Z Axis Lines
        if (SHOW_AXIS_LINES)
        {
            Line3D z_line = new Line3D(new Point3D[]{
                    new Point3D(0, 0, HEIGHT * -2),
                    new Point3D(0, 0, HEIGHT * 2)
            },
                    Color.BLUE);

            world_objects.add(z_line);

            Line3D x_line = new Line3D(new Point3D[]{
                    new Point3D(WIDTH * -2, 0, 0),
                    new Point3D(WIDTH * 2, 0, 0)
            },
                    Color.RED);

            world_objects.add(x_line);

            Line3D y_line = new Line3D(new Point3D[]{
                    new Point3D(0, WIDTH * -2, 0),
                    new Point3D(0, WIDTH * 2, 0)
            },
                    Color.GREEN);

            world_objects.add(y_line);
        }

        // OTHER OBJECTS
        Cube c = new Cube(250, Color.WHITE, new Point3D(0, 0, 0));

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

                // Center at origin (middle of screen)
                g2.translate(getWidth() / 2, getHeight() / 2);

                // Calculate Transform
                double pitch = Math.toRadians(mouse.x);
                Matrix3 pitchTransform = new Matrix3(new double[]{
                   Math.cos(pitch), 0, -Math.sin(pitch),
                   0, 1, 0,
                   Math.sin(pitch), 0, Math.cos(pitch)
                });

                double roll = Math.toRadians(mouse.y);
                Matrix3 rollTransform = new Matrix3(new double[]{
                   1, 0, 0,
                   0, Math.cos(roll), Math.sin(roll),
                   0, -Math.sin(roll), Math.cos(roll)
                });

                Matrix3 transform = pitchTransform.multiply(rollTransform);

                // Render Stored 3D Objects
                for (Object3D object : world_objects)
                {
                    object.draw(g2, transform);
                }
            }
        };

        // add Mouse Listener for rotating
        renderingPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                double yi = DRAG_SPEED / renderingPanel.getHeight();
                double xi = DRAG_SPEED / renderingPanel.getWidth();

                mouse.x = e.getX() * xi;
                mouse.y = e.getY() * yi;

                renderingPanel.repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        pane.add(renderingPanel, BorderLayout.CENTER);

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}




