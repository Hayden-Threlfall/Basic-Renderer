import java.awt.*;

public class Matrix4 {

    double[][] values;

    Matrix4(double[][] v)
    {
        this.values = v;
    }

    public Point3D multiplyPoint(Point3D in)
    {
        double[] result_matrix = new double[]{
                in.x * this.values[0][0] + in.y * this.values[1][0] + in.z * this.values[2][0] + this.values[3][0],
                in.x * this.values[0][1] + in.y * this.values[1][1] + in.z * this.values[2][1] + this.values[3][1],
                in.x * this.values[0][2] + in.y * this.values[1][2] + in.z * this.values[2][2] + this.values[3][2],
                in.x * this.values[0][3] + in.y * this.values[1][3] + in.z * this.values[2][3] + this.values[3][3]
        };

        if (result_matrix[3] != 0)
        {
            result_matrix[0] /= result_matrix[3];
            result_matrix[1] /= result_matrix[3];
            result_matrix[2] /= result_matrix[3];
        }

        return new Point3D(
                result_matrix[0],
                result_matrix[1],
                result_matrix[2]
        );
    }


}
