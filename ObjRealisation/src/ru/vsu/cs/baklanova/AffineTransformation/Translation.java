package ru.vsu.cs.baklanova.AffineTransformation;

import ru.vsu.cs.baklanova.Math.matrix.Matrix4x4;
import ru.vsu.cs.baklanova.Math.vector.Vector3D;
import ru.vsu.cs.baklanova.Math.vector.Vector4D;
import ru.vsu.cs.baklanova.model.Model;

import java.util.ArrayList;

public class Translation {
    
    public static Model translateModel(Model model1, char axis, double shift) throws Exception {
        if (model1 == null) {
            throw new NullPointerException("Model is null");
        }

        Model model = new Model(new ArrayList<>(model1.vertices), new ArrayList<>(model1.textureVertices),
                new ArrayList<>(model1.normals), new ArrayList<>(model1.polygons));

        Matrix4x4 translateMatrix = translateMatrix(axis, shift);

        model.vertices.replaceAll(v -> translateDot(v,  translateMatrix));
        model.normals.replaceAll(v -> translateDot(v, translateMatrix));

        return model;
    }

    public static Model translateModelForFewAxis(Model model1, double shiftX, double shiftY, double shiftZ) {
        if (model1 == null) {
            throw new NullPointerException("Model is null");
        }
        Model model = new Model(new ArrayList<>(model1.vertices), new ArrayList<>(model1.textureVertices),
                new ArrayList<>(model1.normals), new ArrayList<>(model1.polygons));

        Matrix4x4 translateMatrix = translateMatrixForFewAxis(shiftX, shiftY, shiftZ);

        model.vertices.replaceAll(v -> translateDotForFewAxis(v, translateMatrix));
        model.normals.replaceAll(v -> translateDotForFewAxis(v, translateMatrix));

        return model;
    }
    private static Vector3D translateDot(Vector3D dot, Matrix4x4 translateMatrix) {
        if (dot == null) {
            throw new NullPointerException("Dot vector is null");
        }
        Vector4D v = translateMatrix.multiply(new Vector4D(dot));

        return new Vector3D(v.get(0), v.get(1), v.get(2));
    }

    private static Vector3D translateDotForFewAxis(Vector3D dot, Matrix4x4 translateMatrix) {
        if (dot == null) {
            throw new NullPointerException("Dot vector is null");
        }

        Vector4D v = translateMatrix.multiply(new Vector4D(dot));

        return new Vector3D(v.get(0), v.get(1), v.get(2));
    }

    public static Matrix4x4 translateMatrix(char axis, double shift) throws Exception {
        switch (axis) {
            case 'x', 'X' -> {
                return translateMatrix(0, shift);
            }
            case 'y', 'Y' -> {
                return translateMatrix(1, shift);
            }
            case 'z', 'Z' -> {
                return translateMatrix(2, shift);
            }
            default -> throw new TranslateException("invalid axis specified");
        }
    }
    private static Matrix4x4 translateMatrix(int axis, double shift) {
        final int SIZE = 4;
        double[][] arr = new double[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            arr[i][i] = 1;
        }
        arr[axis][SIZE - 1] = shift;

        return new Matrix4x4(arr);
    }

    public static Matrix4x4 translateMatrixForFewAxis(double shiftX, double shiftY, double shiftZ) {
        final int SIZE = 4;
        double[][] arr = new double[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            arr[i][i] = 1;
        }
        arr[0][SIZE - 1] = shiftX;
        arr[1][SIZE - 1] = shiftY;
        arr[2][SIZE - 1] = shiftZ;

        return new Matrix4x4(arr);
    }
}
