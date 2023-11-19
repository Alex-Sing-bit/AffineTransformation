package ru.vsu.cs.baklanova.AffineTransformation;

import ru.vsu.cs.baklanova.Math.matrix.Matrix3x3;
import ru.vsu.cs.baklanova.Math.matrix.Matrix4x4;
import ru.vsu.cs.baklanova.Math.vector.Vector3D;
import ru.vsu.cs.baklanova.Math.vector.Vector4D;
import ru.vsu.cs.baklanova.model.Model;

public class Translation {
    
    public static Model translateModelRadians(Model model, char axis, double shift) throws Exception {
        switch (axis) {
            case 'x', 'X' -> {
                model.vertices.replaceAll(v -> translateDot(v, 0, shift));
                model.normals.replaceAll(v -> translateDot(v, 0, shift));
            }
            case 'y', 'Y' -> {
                model.vertices.replaceAll(v -> translateDot(v, 1, shift));
                model.normals.replaceAll(v -> translateDot(v, 1, shift));
            }
            case 'z', 'Z' -> {
                model.vertices.replaceAll(v -> translateDot(v, 2, shift));
                model.normals.replaceAll(v -> translateDot(v, 2, shift));
            }
            default -> throw new Exception("Я ошибка в TranslateModel, потому что указана неверная ось");
        }

        return model;
    }

    public static Model translateModelRadiansForFewAxis(Model model, double shiftX, double shiftY, double shiftZ) throws Exception {
        model.vertices.replaceAll(v -> translateDotForFewAxis(v, shiftX, shiftY, shiftZ));
        model.normals.replaceAll(v -> translateDotForFewAxis(v, shiftX, shiftY, shiftZ));

        return model;
    }
    private static Vector3D translateDot(Vector3D dot, int axis, double shift) {
        Matrix4x4 scaleMatrix = translateMatrix(axis, shift);
        Vector4D v = scaleMatrix.multiply(new Vector4D(dot));

        return new Vector3D(v.get(0), v.get(1), v.get(2));
    }

    private static Vector3D translateDotForFewAxis(Vector3D dot, double shiftX, double shiftY, double shiftZ) {
        Matrix4x4 scaleMatrix = translateMatrixForFewAxis(shiftX, shiftY, shiftZ);
        Vector4D v = scaleMatrix.multiply(new Vector4D(dot));

        return new Vector3D(v.get(0), v.get(1), v.get(2));
    }
    public static Matrix4x4 translateMatrix(int axis, double shift) {
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
