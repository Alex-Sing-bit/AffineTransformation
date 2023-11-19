package ru.vsu.cs.baklanova.AffineTransformation;

import ru.vsu.cs.baklanova.Math.matrix.Matrix3x3;
import ru.vsu.cs.baklanova.Math.vector.Vector3D;
import ru.vsu.cs.baklanova.model.Model;

public class Rotation {
    public static Model rotateModelDegrees(Model model, char axis, double cornerInDegrees) throws Exception {
        return rotateModelRadians(model, axis, Math.toRadians(cornerInDegrees));
    }
    public static Model rotateModelRadians(Model model, char axis, double cornerInRadians) throws Exception {
        switch (axis) {
            case 'x', 'X' -> {
                model.vertices.replaceAll(v -> rotateDot(v, 0, cornerInRadians));
                model.normals.replaceAll(v -> rotateDot(v, 0, cornerInRadians));
            }
            case 'y', 'Y' -> {
                model.vertices.replaceAll(v -> rotateDot(v, 1, cornerInRadians));
                model.normals.replaceAll(v -> rotateDot(v, 1, cornerInRadians));
            }
            case 'z', 'Z' -> {
                model.vertices.replaceAll(v -> rotateDot(v, 2, cornerInRadians));
                model.normals.replaceAll(v -> rotateDot(v, 2, cornerInRadians));
            }
            default -> throw new Exception("Я ошибка в RotateModel, потому что указана неверная ось");
        }

        return model;
    }

    private static Vector3D rotateDot(Vector3D dot, int axis, double fi) {
        Matrix3x3 scaleMatrix = rotationMatrix(axis, fi);
        dot = scaleMatrix.multiply(dot);

        return dot;
    }

    public static Matrix3x3 rotationMatrixWithCornerInDegrees(int axis, double fi) {
        return rotationMatrix(axis, Math.toRadians(fi));
    }
    public static Matrix3x3 rotationMatrix(int axis, double fi) {
        final int SIZE = 3;
        final double EXP = 10e-15;
        double[][] arr = new double[SIZE][SIZE];
        double sinFi = Math.sin(fi);
        if (Math.abs(sinFi) < EXP) {
            sinFi = 0;
        }
        double cosFi = Math.cos(fi);
        if (Math.abs(cosFi) < EXP) {
            cosFi = 0;
        }

        int k = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == axis && j == axis) {
                    arr[i][j] = 1;
                } else if (i == axis || j == axis) {
                    arr[i][j] = 0;
                } else {
                    switch (k) {
                        case 0, 3 -> arr[i][j] = cosFi;
                        case 1 -> arr[i][j] = sinFi;
                        case 2 -> arr[i][j] = -sinFi;
                    }
                    k++;
                }
            }
        }

        return new Matrix3x3(arr);
    }
}
