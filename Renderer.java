import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;



public class Renderer {

    public static void main(String[] args)
    {
        final int HEIGHT = 600;
        final int WIDTH = 600;

        Point2D mouse = new Point2D(WIDTH / 2, HEIGHT / 2);

        Cube c = new Cube(200, Color.WHITE);

        JFrame frame = new JFrame();
        Container pane = frame.getContentPane();

        JPanel renderingPanel = new JPanel()
        {
            public void paintComponent(Graphics g)
            {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // Center at origin (middle of screen)
                g2.translate(getWidth() / 2, getHeight() / 2);

                // Calculate Transform
                double heading = Math.toRadians(mouse.x);
                Matrix3 headingTransform = new Matrix3(new double[]{
                   Math.cos(heading), 0, -Math.sin(heading),
                   0, 1, 0,
                   Math.sin(heading), 0, Math.cos(heading)
                });

                double pitch = Math.toRadians(mouse.y);
                Matrix3 pitchTransform = new Matrix3(new double[]{
                   1, 0, 0,
                   0, Math.cos(pitch), Math.sin(pitch),
                   0, -Math.sin(pitch), Math.cos(pitch)
                });

                Matrix3 transform = headingTransform.multiply(pitchTransform);

                c.draw(g2, transform);
            }
        };

        // add Mouse Listener for rotating
        renderingPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                double yi = 180.0 / renderingPanel.getHeight();
                double xi = 180.0 / renderingPanel.getWidth();

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




