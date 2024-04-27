import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

public class Triangle {

    Vertex[] points;
    Color color;
    Vertex normal;


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

    public Color getShade(double shade)
    {
        // it looks like this because sRGB sucks
        double redLinear = Math.pow(this.color.getRed(), 2.2) * shade;
        double greenLinear = Math.pow(this.color.getGreen(), 2.2) * shade;
        double blueLinear = Math.pow(this.color.getBlue(), 2.2) * shade;

        int red = (int) Math.pow(redLinear, 1 / 2.2);
        int green = (int) Math.pow(greenLinear, 1 / 2.2);
        int blue = (int) Math.pow(blueLinear, 1 / 2.2);

        return new Color(red, green, blue);
    }

}
