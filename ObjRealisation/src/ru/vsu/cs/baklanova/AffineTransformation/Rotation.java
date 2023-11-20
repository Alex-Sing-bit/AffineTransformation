package ru.vsu.cs.baklanova.AffineTransformation;

import ru.vsu.cs.baklanova.Math.matrix.Matrix3x3;
import ru.vsu.cs.baklanova.Math.vector.Vector3D;
import ru.vsu.cs.baklanova.model.Model;

import java.util.ArrayList;

public class Rotation {
    public static Model rotateModelFewTimesDegrees(Model model, char[] axis, double[] anglesInDegrees) throws Exception {
        if (model == null) {
            throw new NullPointerException("Model is null");
        }
        if (axis.length != anglesInDegrees.length) {
            throw new RotateExceptions("axis number not equal to angles");
        }
        for (int i = 0; i < axis.length; i++) {
            anglesInDegrees[i] = Math.toRadians(anglesInDegrees[i]);
        }

        return rotateModelFewTimesRadians(model, axis, anglesInDegrees);
    }
    public static Model rotateModelFewTimesRadians(Model model1, char[] axis, double[] anglesInRadians) throws Exception {
        if (model1 == null) {
            throw new NullPointerException("Model is null");
        }
        if (axis.length != anglesInRadians.length) {
            throw new RotateExceptions("axis number not equal to angles");
        }

        Model model = new Model(new ArrayList<>(model1.vertices), new ArrayList<>(model1.textureVertices),
                new ArrayList<>(model1.normals), new ArrayList<>(model1.polygons));
        for (int i = 0; i < axis.length; i++) {
            model = rotateModelRadians(model, axis[i], anglesInRadians[i]);
        }

        return model;
    }
    public static Model rotateModelDegrees(Model model, char axis, double angleInDegrees) throws RotateExceptions {
        return rotateModelRadians(model, axis, Math.toRadians(angleInDegrees));
    }
    public static Model rotateModelRadians(Model model1, char axis, double angleInRadians) throws RotateExceptions {
        if (model1 == null) {
            throw new NullPointerException("Model is null");
        }

        Model model = new Model(new ArrayList<>(model1.vertices), new ArrayList<>(model1.textureVertices),
                new ArrayList<>(model1.normals), new ArrayList<>(model1.polygons));
        Matrix3x3 rotationMatrix = rotationMatrixInRadians(axis, angleInRadians);
        model.vertices.replaceAll(v -> rotateDot(v, rotationMatrix));
        model.normals.replaceAll(v -> rotateDot(v, rotationMatrix));

        return model;
    }

    private static Vector3D rotateDot(Vector3D dot, Matrix3x3 rotationMatrix) {
        if (dot == null) {
            throw new NullPointerException("Dot vector is null");
        }

        return rotationMatrix.multiply(dot);
    }

    public static Matrix3x3 rotationMatrixInDegrees(char axis, double fi) throws RotateExceptions {
        return rotationMatrixInRadians(axis, Math.toRadians(fi));
    }
    public static Matrix3x3 rotationMatrixInRadians(char axis, double fi) throws RotateExceptions {
        switch (axis) {
            case 'x', 'X' -> {
                return rotationMatrix(0, fi);
            }
            case 'y', 'Y' -> {
                return rotationMatrix(1, fi);
            }
            case 'z', 'Z' -> {
                return rotationMatrix(2, fi);
            }
            default -> throw new RotateExceptions("invalid axis specified");
        }
    }

    private static Matrix3x3 rotationMatrix(int axis, double fi) {
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
