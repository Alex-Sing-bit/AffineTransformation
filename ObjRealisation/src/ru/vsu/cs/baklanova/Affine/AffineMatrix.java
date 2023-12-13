package ru.vsu.cs.baklanova.Affine;


import ru.vsu.cs.baklanova.math.matrix.Matrix4x4;
import ru.vsu.cs.baklanova.math.vector.Vector3D;

public class AffineMatrix {
    public static Matrix4x4 scaleMatrix(Vector3D vector) {
        if (vector == null) {
            throw new NullPointerException("Vector is null");
        }
        final  int SIZE = 3;
        double[][] matrix = new double[SIZE + 1][SIZE + 1];

        for (int i = 0; i < SIZE; i++) {
            matrix[i][i] = vector.get(i);
        }
        matrix[SIZE][SIZE] = 1;


        return new Matrix4x4(matrix);
    }

    public static Matrix4x4 translateMatrix(Vector3D vector) {
        if (vector == null) {
            throw new NullPointerException("Vector is null");
        }
        final int SIZE = 4;
        double[][] arr = new double[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            arr[i][i] = 1; //1 для корректности, 0 для билдера
        }
        arr[0][SIZE - 1] = vector.get(0);
        arr[1][SIZE - 1] = vector.get(1);
        arr[2][SIZE - 1] = vector.get(2);

        return new Matrix4x4(arr);
    }

    public static Matrix4x4 rotateMatrix(int axis, double fi) throws Exception {
        final int SIZE = 3;
        if (axis > SIZE) {
            throw new Exception("Wrong axis");
        }
        final double EXP = 10e-15;
        double[][] arr = new double[SIZE + 1][SIZE + 1];
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

        arr[SIZE][SIZE] = 1;

        return new Matrix4x4(arr);
    }
}
