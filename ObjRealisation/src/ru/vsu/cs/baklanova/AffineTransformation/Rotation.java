package ru.vsu.cs.baklanova.AffineTransformation;

import ru.vsu.cs.baklanova.Math.matrix.Matrix3x3;
import ru.vsu.cs.baklanova.Math.vector.Vector3D;
import ru.vsu.cs.baklanova.model.Model;

public class Rotation {
    public static void rotateModelFewTimesDegrees(Model model, char[] axis, double[] anglesInDegrees) throws Exception {
        if (model == null) {
            throw new NullPointerException("Model is null");
        }
        if (axis.length != anglesInDegrees.length) {
            throw new RotateExceptions("axis number not equal to angles");
        }
        for (int i = 0; i < axis.length; i++) {
            anglesInDegrees[i] = Math.toRadians(anglesInDegrees[i]);
        }
        rotateModelFewTimesRadians(model, axis, anglesInDegrees);
    }
    public static void rotateModelFewTimesRadians(Model model, char[] axis, double[] anglesInRadians) throws Exception {
        if (model == null) {
            throw new NullPointerException("Model is null");
        }
        if (axis.length != anglesInRadians.length) {
            throw new RotateExceptions("axis number not equal to angles");
        }
        for (int i = 0; i < axis.length; i++) {
            rotateModelRadians(model, axis[i], anglesInRadians[i]);
        }
    }
    public static void rotateModelDegrees(Model model, char axis, double angleInDegrees) throws RotateExceptions {
        rotateModelRadians(model, axis, Math.toRadians(angleInDegrees));
    }
    public static void rotateModelRadians(Model model, char axis, double angleInRadians) throws RotateExceptions {
        if (model == null) {
            throw new NullPointerException("Model is null");
        }
        Matrix3x3 scaleMatrix = rotationMatrixInRadians(axis, angleInRadians);
        model.vertices.replaceAll(scaleMatrix::multiply);
        model.normals.replaceAll(scaleMatrix::multiply);

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
