package ru.vsu.cs.baklanova.AffineTransformationTest;

import  ru.vsu.cs.baklanova.AffineTransformation.Scale;
import ru.vsu.cs.baklanova.Math.matrix.Matrix3x3;
import  ru.vsu.cs.baklanova.Math.vector.Vector3D;
import  ru.vsu.cs.baklanova.model.Model;
import  ru.vsu.cs.baklanova.model.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ScaleTest {
    public final int SIZE = 3;
    public final double DELTA = 10e-15;
    @Test
    public void testScaleModel1() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model result = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        result = Scale.scaleModel(result, new Vector3D(0.0, 0.0, 0.0));

        Vector3D resultVector = result.vertices.get(0);
        Vector3D expected = new Vector3D(0.0, 0.0, 0.0);

        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(expected.get(i), resultVector.get(i), DELTA);
        }
    }


    @Test
    public void testScaleModel2() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(2.0, 0.0, -0.1));

        Model result = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        result = Scale.scaleModel(result, new Vector3D(1.0, 1.0, 1.0));

        Vector3D resultVector = result.vertices.get(0);
        Vector3D expected = new Vector3D(2.0, 0.0, -0.1);

        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(expected.get(i), resultVector.get(i), DELTA);
        }
    }

    @Test
    public void testScaleModel3() {
        ArrayList<Vector3D> vertex = new ArrayList<Vector3D>();
        vertex.add(new Vector3D(2.0, 1.0, -0.1));
        vertex.add(new Vector3D(1.0, 8.0, -0.126));
        vertex.add(new Vector3D(4.0, 11.0, 0.0));

        Model result = new Model(vertex, new ArrayList<Vector3D>(), new ArrayList<Vector3D>(), new ArrayList<Polygon>());
        result = Scale.scaleModel(result, new Vector3D(3.0, -1.0, 4.0));

        Vector3D resultVector0 = result.vertices.get(0);
        Vector3D expected0 = new Vector3D(6.0, -1.0, -0.4);
        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(expected0.get(i), resultVector0.get(i), DELTA);
        }

        Vector3D resultVector1 = result.vertices.get(1);
        Vector3D expected1 = new Vector3D(3.0, -8.0, -0.504);
        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(expected1.get(i), resultVector1.get(i), DELTA);
        }

        Vector3D resultVector2 = result.vertices.get(2);
        Vector3D expected2 = new Vector3D(12.0, -11.0, 0.0);
        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(expected2.get(i), resultVector2.get(i), DELTA);
        }
    }
    @Test
    public void testScaleModelNormals1() {
        ArrayList<Vector3D> normals = new ArrayList<>();
        normals.add(new Vector3D(-2.0, 0.0, 0.1));

        Model result = new Model(new ArrayList<>(), new ArrayList<>(), normals, new ArrayList<>());
        result = Scale.scaleModel(result, new Vector3D(0.0, 0.0, 0.0));

        Vector3D resultVector = result.normals.get(0);
        Vector3D expected = new Vector3D(0.0, 0.0, 0.0);

        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(expected.get(i), resultVector.get(i), DELTA);
        }
    }


    @Test
    public void testScaleModelNormal2() {
        ArrayList<Vector3D> normals = new ArrayList<>();
        normals.add(new Vector3D(2.0, 0.0, -0.1));

        Model result = new Model(new ArrayList<>(), new ArrayList<>(), normals, new ArrayList<>());
        result = Scale.scaleModel(result, new Vector3D(1.0, 1.0, 1.0));

        Vector3D resultVector = result.normals.get(0);
        Vector3D expected = new Vector3D(2.0, 0.0, -0.1);

        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(expected.get(i), resultVector.get(i), DELTA);
        }
    }

    @Test
    public void testScaleModelNormal3() {
        ArrayList<Vector3D> normals = new ArrayList<Vector3D>();
        normals.add(new Vector3D(2.0, 1.0, -0.1));
        normals.add(new Vector3D(1.0, 8.0, -0.126));
        normals.add(new Vector3D(4.0, 11.0, 0.0));

        Model result = new Model(new ArrayList<>(), new ArrayList<>(), normals, new ArrayList<>());
        result = Scale.scaleModel(result, new Vector3D(3.0, -1.0, 4.0));

        Vector3D resultVector0 = result.normals.get(0);
        Vector3D expected0 = new Vector3D(6.0, -1.0, -0.4);
        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(expected0.get(i), resultVector0.get(i), DELTA);
        }

        Vector3D resultVector1 = result.normals.get(1);
        Vector3D expected1 = new Vector3D(3.0, -8.0, -0.504);
        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(expected1.get(i), resultVector1.get(i), DELTA);
        }

        Vector3D resultVector2 = result.normals.get(2);
        Vector3D expected2 = new Vector3D(12.0, -11.0, 0.0);
        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(expected2.get(i), resultVector2.get(i), DELTA);
        }
    }


    @Test
    public void testScaleNullModel() {
        try {
            Scale.scaleModel(null, new Vector3D(1, 1, 1));
        } catch (NullPointerException ex) {
            String expectedError = "Model is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testScaleModelWithNullVector() {
        try {
            Scale.scaleModel(new Model(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()),null);
        } catch (NullPointerException ex) {
            String expectedError = "Vector for multiplication is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testScaleModelWithNullNormal() {
        ArrayList<Vector3D> normal = new ArrayList<>();
        normal.add(new Vector3D(1, 1, 1));
        normal.add(null);
        Model model = new Model(new ArrayList<>(), new ArrayList<>(), normal, new ArrayList<>());
        try {
            Scale.scaleModel(model, new Vector3D(1.0, 1.0, 1.0));
        } catch (NullPointerException ex) {
            String expectedError = "Dot vector is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testScaleModelWithNullVertex() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(null);
        vertex.add(new Vector3D(0, 0, 0));
        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        try {
            Scale.scaleModel(model, new Vector3D(1.0, 1.0, 1.0));
        } catch (NullPointerException ex) {
            String expectedError = "Dot vector is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testScaleMatrixWithNullVector() {
        try {
            Scale.scaleMatrix(null);
        } catch (NullPointerException ex) {
            String expectedError = "Vector is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testScaleMatrix() {
        Matrix3x3 matrix = Scale.scaleMatrix(new Vector3D(-0.67, 123, 0));

        double[][] result = matrix.getMatrix();
        double[][] expectedResult = new double[][]{{-0.67, 0, 0}, {0, 123, 0}, {0, 0, 0}};

        int expectedLength = expectedResult.length;
        int expectedDepth = expectedResult[0].length;
        Assertions.assertEquals(result.length, expectedLength);
        Assertions.assertEquals(result[0].length, expectedDepth);

        for (int i = 0; i < expectedLength; i++) {
            for (int j = 0; j < expectedDepth; j++) {
                Assertions.assertEquals(expectedResult[i][j], result[i][j], DELTA);
            }
        }
    }
}