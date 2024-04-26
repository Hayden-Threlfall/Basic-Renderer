import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

public class Triangle {

    Vertex[] points;
    Color color;

    Triangle(Vertex p1, Vertex p2, Vertex p3, Color color)
    {
        this.points = new Vertex[]{p1, p2, p3};
        this.color = color;
    }

    Triangle(Vertex[] p, Color c)
    {
        this.points = p;
        this.color = c;
    }

}
