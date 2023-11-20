package ru.vsu.cs.baklanova.AffineTransformation;

import ru.vsu.cs.baklanova.Math.matrix.Matrix3x3;
import ru.vsu.cs.baklanova.Math.vector.Vector3D;
import ru.vsu.cs.baklanova.model.Model;

import java.util.ArrayList;

public class Scale {
    public static Model scaleModel(Model model1, Vector3D vector) {
        if (model1 == null) {
            throw new NullPointerException("Model is null");
        }
        if (vector == null) {
            throw new NullPointerException("Vector for multiplication is null");
        }

        Model model = new Model(new ArrayList<>(model1.vertices), new ArrayList<>(model1.textureVertices),
                new ArrayList<>(model1.normals), new ArrayList<>(model1.polygons));

        Matrix3x3 scaleMatrix = scaleMatrix(vector);
        model.vertices.replaceAll(v -> scaleDot(v, scaleMatrix));
        model.normals.replaceAll(v -> scaleDot(v, scaleMatrix));

        return model;
    }

    //Передавать в scaleDot или проверять циклом в scaleModel
    private static Vector3D scaleDot(Vector3D dot, Matrix3x3 scaleMatrix){
        if (dot == null) {
            throw new NullPointerException("Dot vector is null");
        }

        return scaleMatrix.multiply(dot);
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
