import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;


public class Renderer {

    public static void main(String[] args)
    {

        Point3D camera = new Point3D(0, 0, 0);
        double d = 50;

        Pyramid p = new Pyramid(new Point3D(100, 0, 200),
                new Point3D(0, -100, 200),
                new Point3D(0, 100, 200),
                new Point3D(0, 100, 250),
                Color.WHITE);

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

                p.draw(g2, camera, d);
            }
        };

        pane.add(renderingPanel, BorderLayout.CENTER);

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}




