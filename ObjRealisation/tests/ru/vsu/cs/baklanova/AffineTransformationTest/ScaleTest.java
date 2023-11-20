package ru.vsu.cs.baklanova.AffineTransformationTest;

import  ru.vsu.cs.baklanova.AffineTransformation.Scale;
import ru.vsu.cs.baklanova.Math.matrix.Matrix3x3;
import  ru.vsu.cs.baklanova.Math.vector.Vector2D;
import  ru.vsu.cs.baklanova.Math.vector.Vector3D;
import  ru.vsu.cs.baklanova.model.Model;
import  ru.vsu.cs.baklanova.model.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ScaleTest {
    @Test
    public void testScaleModel1() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model result = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        result = Scale.scaleModel(result, new Vector3D(0.0, 0.0, 0.0));

        Assertions.assertEquals(0.0, result.vertices.get(0).get(0), 10e-5);
        Assertions.assertEquals(0.0, result.vertices.get(0).get(1), 10e-5);
        Assertions.assertEquals(0.0, result.vertices.get(0).get(2), 10e-5);
    }


    @Test
    public void testScaleModel2() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(2.0, 0.0, -0.1));

        Model result = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        result = Scale.scaleModel(result, new Vector3D(1.0, 1.0, 1.0));

        Assertions.assertEquals(2.0, result.vertices.get(0).get(0), 10e-5);
        Assertions.assertEquals(0.0, result.vertices.get(0).get(1), 10e-5);
        Assertions.assertEquals(-0.1, result.vertices.get(0).get(2), 10e-5);
    }

    @Test
    public void testScaleModel3() {
        ArrayList<Vector3D> vertex = new ArrayList<Vector3D>();
        vertex.add(new Vector3D(2.0, 1.0, -0.1));
        vertex.add(new Vector3D(1.0, 8.0, -0.126));
        vertex.add(new Vector3D(4.0, 11.0, 0.0));

        Model result = new Model(vertex, new ArrayList<Vector3D>(), new ArrayList<Vector3D>(), new ArrayList<Polygon>());
        result = Scale.scaleModel(result, new Vector3D(3.0, -1.0, 4.0));

        Assertions.assertEquals(6.0, result.vertices.get(0).get(0), 10e-5);
        Assertions.assertEquals(-1.0, result.vertices.get(0).get(1), 10e-5);
        Assertions.assertEquals(-0.4, result.vertices.get(0).get(2), 10e-5);

        Assertions.assertEquals(3.0, result.vertices.get(1).get(0), 10e-5);
        Assertions.assertEquals(-8.0, result.vertices.get(1).get(1), 10e-5);
        Assertions.assertEquals(-0.504, result.vertices.get(1).get(2), 10e-5);

        Assertions.assertEquals(12.0, result.vertices.get(2).get(0), 10e-5);
        Assertions.assertEquals(-11.0, result.vertices.get(2).get(1), 10e-5);
        Assertions.assertEquals(0.0, result.vertices.get(2).get(2), 10e-5);
    }
    @Test
    public void testScaleModel4() {
        ArrayList<Vector3D> normals = new ArrayList<Vector3D>();
        normals.add(new Vector3D(2.0, 1.0, -0.1));
        normals.add(new Vector3D(1.0, 8.0, -0.126));
        normals.add(new Vector3D(4.0, 11.0, 0.0));

        Model result = new Model(new ArrayList<Vector3D>(), new ArrayList<Vector3D>(), normals, new ArrayList<Polygon>());
        result = Scale.scaleModel(result, new Vector3D(3.0, -1.0, 4.0));

        Assertions.assertEquals(6.0, result.normals.get(0).get(0), 10e-5);
        Assertions.assertEquals(-1.0, result.normals.get(0).get(1), 10e-5);
        Assertions.assertEquals(-0.4, result.normals.get(0).get(2), 10e-5);

        Assertions.assertEquals(3.0, result.normals.get(1).get(0), 10e-5);
        Assertions.assertEquals(-8.0, result.normals.get(1).get(1), 10e-5);
        Assertions.assertEquals(-0.504, result.normals.get(1).get(2), 10e-5);

        Assertions.assertEquals(12.0, result.normals.get(2).get(0), 10e-5);
        Assertions.assertEquals(-11.0, result.normals.get(2).get(1), 10e-5);
        Assertions.assertEquals(0.0, result.normals.get(2).get(2), 10e-5);
    }

    @Test
    public void testScaleModel5() {
        ArrayList<Vector3D> normal = new ArrayList<Vector3D>();
        normal.add(new Vector3D(-2.0, 0.0, 0.1));

        Model result = new Model(new ArrayList<Vector3D>(), new ArrayList<Vector3D>(), normal, new ArrayList<Polygon>());
        result = Scale.scaleModel(result, new Vector3D(0.0, 0.0, 0.0));

        Assertions.assertEquals(0.0, result.normals.get(0).get(0), 10e-5);
        Assertions.assertEquals(0.0, result.normals.get(0).get(1), 10e-5);
        Assertions.assertEquals(0.0, result.normals.get(0).get(2), 10e-5);
    }


    @Test
    public void testScaleModel6() {
        ArrayList<Vector3D> normal = new ArrayList<Vector3D>();
        normal.add(new Vector3D(2.0, 0.0, -0.1));

        Model result = new Model(new ArrayList<Vector3D>(), new ArrayList<Vector3D>(), normal, new ArrayList<Polygon>());
        result = Scale.scaleModel(result, new Vector3D(1.0, 1.0, 1.0));

        Assertions.assertEquals(2.0, result.normals.get(0).get(0), 10e-5);
        Assertions.assertEquals(0.0, result.normals.get(0).get(1), 10e-5);
        Assertions.assertEquals(-0.1, result.normals.get(0).get(2), 10e-5);
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
                Assertions.assertEquals(expectedResult[i][j], result[i][j], 10e-15);
            }
        }
    }
}