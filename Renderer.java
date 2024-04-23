import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;


public class Renderer {

    public static void main(String[] args)
    {

        Triangle t = new Triangle(new Point3D(100, 100, 100),
                new Point3D(-100, -100, 100),
                new Point3D(-100, 100, -100),
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

                // Render Here

                // Center at origin (middle of screen)
                g2.translate(getWidth() / 2, getHeight() / 2);

                g2.setColor(Color.WHITE);

                Path2D path = new Path2D.Double();

                path.moveTo(t.p1.x, t.p1.y);
                path.lineTo(t.p2.x, t.p2.y);
                path.lineTo(t.p3.x, t.p3.y);
                path.closePath();
                g2.draw(path);
            }
        };

        pane.add(renderingPanel, BorderLayout.CENTER);

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}




