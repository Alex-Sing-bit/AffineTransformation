package ru.vsu.cs.baklanova.AffineTransformation;

import ru.vsu.cs.baklanova.Math.matrix.Matrix3x3;
import ru.vsu.cs.baklanova.Math.vector.Vector2D;
import ru.vsu.cs.baklanova.Math.vector.Vector3D;
import ru.vsu.cs.baklanova.model.Model;
import ru.vsu.cs.baklanova.model.Polygon;

import java.util.ArrayList;

public class Scale {
    public static Model scaleModel(Model model, Vector3D vector) {
        for (int i = 0; i < model.vertices.size(); i++) {
            Vector3D dot = model.vertices.get(i);
            model.vertices.set(i, scaleDot(dot, vector));
        }

        return model;
    }

    public static Vector3D scaleDot(Vector3D dot, Vector3D vector3D){
        Matrix3x3 scaleMatrix = scaleMatrix(vector3D);
        dot = scaleMatrix.multiply(dot);

        return dot;
    }

    private static Matrix3x3 scaleMatrix(Vector3D vector) {
        final  int SIZE = 3;
        double matrix[][] = new double[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++)
        matrix[i][i] = vector.get(i);


        return new Matrix3x3(matrix);
    }
}
