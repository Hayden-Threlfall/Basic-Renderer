import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Cube extends Object3D{

    Point3D[] points;
    int[][] lines;
    Color color;

    Cube(int size, Color c)
    {
        this.points = new Point3D[]{
                new Point3D(0, 0, 0),
                new Point3D(0, 0, size),
                new Point3D(0, size, 0),
                new Point3D(0, size, size),
                new Point3D(size, 0, 0),
                new Point3D(size, 0, size),
                new Point3D(size, size, 0),
                new Point3D(size, size, size)
        };

        this.lines = new int[][] {
                {0, 1},
                {0, 2},
                {3, 1},
                {3, 2},
                {4, 5},
                {4, 6},
                {7, 5},
                {7, 6},
                {0, 4},
                {1, 5},
                {2, 6},
                {3, 7}
        };

        this.color = c;
    }

    public void draw(Graphics2D g2, Matrix3 transform)
    {
        g2.setColor(this.color);

        Point3D[] translate_points = new Point3D[this.points.length];

        // Translate Points to camera
        for (int i = 0; i < this.points.length; i++)
        {
            translate_points[i] = transform.transform(this.points[i]);
        }

        // Draw all Lines
        Path2D path = new Path2D.Double();

        for (int[] line : lines)
        {
            Point3D p1 = translate_points[line[0]];
            Point3D p2 = translate_points[line[1]];

            path.moveTo(p1.x, p1.y);
            path.lineTo(p2.x, p2.y);

        }

        path.closePath();
        g2.draw(path);
    }
}
