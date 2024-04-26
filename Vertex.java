public class Vertex {
    double x;
    double y;
    double z;

    Vertex(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void print()
    {
        System.out.println("X: " + this.x + " Y: " + this.y + " Z: " + this.z);
    }
}
