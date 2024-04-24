import java.awt.*;
import java.awt.geom.Path2D;

public class Line3D extends Object3D
{
    Point3D[] points;
    int[][] lines;
    Color color;

    Line3D(Point3D[] p, Color c)
    {
        this.points = p;
        this.color = c;
        this.lines = new int[][]{{0, 1}};
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

        for (int[] line : this.lines)
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
