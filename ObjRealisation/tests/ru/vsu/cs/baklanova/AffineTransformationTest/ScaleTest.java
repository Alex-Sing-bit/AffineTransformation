package ru.vsu.cs.baklanova.AffineTransformationTest;

import ru.vsu.cs.baklanova.Affine.AffineBuilder.AffineBuilder;
import ru.vsu.cs.baklanova.Affine.AffineMatrix;
import ru.vsu.cs.baklanova.math.matrix.Matrix4x4;
import ru.vsu.cs.baklanova.math.vector.Vector3D;
import ru.vsu.cs.baklanova.model.Model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ScaleTest {
    public final int SIZE = 3;
    public final double DELTA = 10e-15;

    @Test
    public void affineBuilderScaleTest1() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Vector3D scale = new Vector3D(0, 0, 0);
        Model resultModel = new AffineBuilder().scale().byVector(scale).close().returnChangedModel(model);
        ArrayList<Vector3D> result = resultModel.vertices;

        Vector3D expected = new Vector3D(0, 0, 0);
        Assertions.assertEquals(result.size(), vertex.size());
        for (Vector3D dot : result) {
            for (int i = 0; i < SIZE; i++) {
                double resultNum = dot.get(i);
                Assertions.assertEquals(expected.get(i), resultNum, DELTA);
            }
        }
    }

    @Test
    public void affineBuilderScaleTest2() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Vector3D scale = new Vector3D(2, 0, -9.1);
        Model resultModel = new AffineBuilder().scale().byVector(scale).close().returnChangedModel(model);
        ArrayList<Vector3D> result = resultModel.vertices;

        Vector3D expected = new Vector3D(-4, 0, -0.91);
        Assertions.assertEquals(result.size(), vertex.size());
        for (Vector3D dot : result) {
            for (int i = 0; i < SIZE; i++) {
                double resultNum = dot.get(i);
                Assertions.assertEquals(expected.get(i), resultNum, DELTA);
            }
        }
    }

    @Test
    public void affineBuilderFewScaleTest() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Vector3D scale1 = new Vector3D(2, 1, -9.1);
        Vector3D scale2 = new Vector3D(0, 17, -10);
        Model resultModel = new AffineBuilder().scale().byVector(scale1).byVector(scale2)
                .byZ(2).byX(3).byY(0.5).close().returnChangedModel(model);
        ArrayList<Vector3D> result = resultModel.vertices;

        Vector3D expected = new Vector3D(0, 8.5, 18.2);
        Assertions.assertEquals(result.size(), vertex.size());
        for (Vector3D dot : result) {
            for (int i = 0; i < SIZE; i++) {
                double resultNum = dot.get(i);
                Assertions.assertEquals(expected.get(i), resultNum, DELTA);
            }
        }
    }

    @Test
    public void scaleWithoutChangingTest() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        AffineBuilder affineBuilder = new AffineBuilder();

        Vector3D scale = new Vector3D(1, 1, 1);
        Model resultModel = affineBuilder.scale().byVector(scale).close().returnChangedModel(model);
        ArrayList<Vector3D> result = resultModel.vertices;

        Assertions.assertEquals(result.size(), vertex.size());
        for (int j = 0; j < result.size(); j++) {
            for (int i = 0; i < SIZE; i++) {
                double resultNum = result.get(j).get(i);
                Assertions.assertEquals(vertex.get(j).get(i), resultNum, DELTA);
            }
        }
    }

    @Test
    public void scaleDifferentWaysTest() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        AffineBuilder builder = new AffineBuilder();

        Vector3D scale = new Vector3D(2, 0, -9.1);
        Model resultModel = builder.scale().byX(scale.get(0))
                .byY(scale.get(1)).byZ(scale.get(2)).close().returnChangedModel(model);
        ArrayList<Vector3D> result = resultModel.vertices;

        builder.clear().scale().byVector(scale).close().changeModel(model);
        ArrayList<Vector3D> expected = model.vertices;

        Assertions.assertEquals(result.size(), vertex.size());
        for (int j = 0; j < expected.size(); j++) {
            Vector3D expectedV = expected.get(j);
            Vector3D v = result.get(j);
            for (int i = 0; i < SIZE; i++) {
                Assertions.assertEquals(expectedV.get(i), v.get(i), DELTA);
            }
        }
    }

    @Test
    public void testScaleModelWithNullVector() {
        try {
            new AffineBuilder().scale().byVector(null).close()
                    .changeModel(new Model(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        } catch (NullPointerException ex) {
            String expectedError = "Vector is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testScaleMatrixWithNullVector() {
        try {
            AffineMatrix.scaleMatrix(null);
        } catch (NullPointerException ex) {
            String expectedError = "Vector is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testScaleMatrix() {
        Matrix4x4 matrix = AffineMatrix.scaleMatrix(new Vector3D(-0.67, 123, 0));

        double[][] result = matrix.getMatrix();
        double[][] expectedResult = new double[][]{{-0.67, 0, 0, 0}, {0, 123, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 1}};

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