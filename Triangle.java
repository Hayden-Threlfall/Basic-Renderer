import java.awt.*;

public class Triangle {
    Point3D p1;
    Point3D p2;
    Point3D p3;

    Color color;

    Triangle(Point3D p1, Point3D p2, Point3D p3, Color color)
    {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.color = color;
    }
}
