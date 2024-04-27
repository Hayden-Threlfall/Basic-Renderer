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

    Vertex(String x, String y, String z)
    {
        this.x = Double.parseDouble(x);
        this.y = Double.parseDouble(y);
        this.z = Double.parseDouble(z);
    }

    public void print()
    {
        System.out.println("X: " + this.x + " Y: " + this.y + " Z: " + this.z);
    }

    public Vertex cross(Vertex other)
    {
        return new Vertex(
                this.y * other.z - this.z * other.y,
                this.z * other.x - this.x * other.z,
                this.x * other.y - this.y * other.x
        );
    }

    public double dot(Vertex other)
    {
        return (this.x * other.x) + (this.y * other.y) + (this.z * other.z);
    }

    public void normalise()
    {
        double length = Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
        this.x = this.x / length;
        this.y = this.y / length;
        this.z = this.z / length;
    }
}
