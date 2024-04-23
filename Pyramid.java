import java.awt.*;

public class Pyramid extends Object3D {

    Point3D[] points;
    Color color;

    Pyramid(Point3D p1, Point3D p2, Point3D p3, Point3D p4, Color color)
    {
        this.points = new Point3D[]{p1, p2, p3, p4};
        this.color = color;
    }

    public void draw(Graphics2D g2) {

    }
}
