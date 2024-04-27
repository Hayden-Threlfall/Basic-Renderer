import java.awt.*;
import java.awt.geom.Path2D;

public class Pyramid extends Object3D {

    Vertex[] points;
    Color color;

    Pyramid(Vertex p1, Vertex p2, Vertex p3, Vertex p4, Color color)
    {
        this.points = new Vertex[]{p1, p2, p3, p4};
        this.color = color;
    }

    public void draw(Graphics2D g2, Matrix3 transform)
    {
        short i = 0;
        Path2D path = new Path2D.Double();
        g2.setColor(this.color);

        Vertex[] translate_points = new Vertex[4];

        // Translate Points to camera
        for (Vertex point : this.points)
        {
            translate_points[i] = transform.transform(point);

            i++;
        }


        // Draw all Lines
        for (Vertex point : translate_points)
        {
            path.moveTo(point.x, point.y);
            for (Vertex point2 : translate_points)
            {
                if (point == point2)
                    continue;

                path.lineTo(point2.x, point2.y);

            }
        }

        path.closePath();
        g2.draw(path);

    }
}
