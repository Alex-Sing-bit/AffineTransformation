package ru.vsu.cs.baklanova.AffineTransformation;

import ru.vsu.cs.baklanova.Math.matrix.Matrix3x3;
import ru.vsu.cs.baklanova.Math.vector.Vector3D;
import ru.vsu.cs.baklanova.model.Model;

public class Scale {
    public static Model scaleModel(Model model, Vector3D vector) {
        if (model == null) {
            throw new NullPointerException("Model is null");
        }
        if (vector == null) {
            throw new NullPointerException("Vector for multiplication is null");
        }
        model.vertices.replaceAll(v -> scaleDot(v, vector));
        model.normals.replaceAll(v -> scaleDot(v, vector));

        return model;
    }

    private static Vector3D scaleDot(Vector3D dot, Vector3D vector){
        if (dot == null) {
            throw new NullPointerException("Dot vector is null");
        }
        if (vector == null) {
            throw new NullPointerException("Vector for multiplication is null");
        }
        Matrix3x3 scaleMatrix = scaleMatrix(vector);
        dot = scaleMatrix.multiply(dot);

        return dot;
    }

    public static Matrix3x3 scaleMatrix(Vector3D vector) {
        if (vector == null) {
            throw new NullPointerException("Vector is null");
        }
        final  int SIZE = 3;
        double[][] matrix = new double[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            matrix[i][i] = vector.get(i);
        }


        return new Matrix3x3(matrix);
    }
}
