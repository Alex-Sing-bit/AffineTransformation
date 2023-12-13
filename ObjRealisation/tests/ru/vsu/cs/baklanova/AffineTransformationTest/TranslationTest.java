package ru.vsu.cs.baklanova.AffineTransformationTest;

import ru.vsu.cs.baklanova.Affine.AffineBuilder.AffineBuilder;
import ru.vsu.cs.baklanova.Affine.AffineMatrix;
import ru.vsu.cs.baklanova.math.matrix.Matrix4x4;
import ru.vsu.cs.baklanova.math.vector.Vector3D;
import ru.vsu.cs.baklanova.model.Model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TranslationTest {
    public final int SIZE = 3;

    public final double DELTA = 10e-15;


    @Test
    public void affineBuilderTranslateTest1() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Vector3D translate = new Vector3D(2, -0.133, 0);
        Model resultModel = new AffineBuilder().translate().byVector(translate).close().returnChangedModel(model);
        ArrayList<Vector3D> result = resultModel.vertices;

        Vector3D expected = new Vector3D(0, -0.133, 0.1);
        Assertions.assertEquals(result.size(), vertex.size());
        for (Vector3D dot : result) {
            for (int i = 0; i < SIZE; i++) {
                double resultNum = dot.get(i);
                Assertions.assertEquals(expected.get(i), resultNum, DELTA);
            }
        }
    }

    @Test
    public void affineBuilderFewTranslateTest() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Vector3D translate1 = new Vector3D(2, -0.13, 0);
        Model resultModel = new AffineBuilder().translate().byX(4.1).byVector(translate1)
                .byZ(0).byY(0.3).close().returnChangedModel(model);
        ArrayList<Vector3D> result = resultModel.vertices;

        Vector3D expected = new Vector3D(4.1, 0.17, 0.1);
        Assertions.assertEquals(result.size(), vertex.size());
        for (Vector3D dot : result) {
            for (int i = 0; i < SIZE; i++) {
                double resultNum = dot.get(i);
                Assertions.assertEquals(expected.get(i), resultNum,  DELTA);
            }
        }
    }

    @Test
    public void translateWithoutChangingTest() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        AffineBuilder affineBuilder = new AffineBuilder();

        Vector3D translate = new Vector3D(0, 0, 0);
        Model resultModel = affineBuilder.translate().byVector(translate).close().returnChangedModel(model);
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
    public void translateDifferentWaysTest() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        AffineBuilder builder = new AffineBuilder();

        Vector3D translate = new Vector3D(2, 0, -9.1);
        Model resultModel = builder.translate().byX(translate.get(0))
                .byY(translate.get(1)).byZ(translate.get(2)).close().returnChangedModel(model);
        ArrayList<Vector3D> result = resultModel.vertices;

        builder.clear().translate().byVector(translate).close().changeModel(model);
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
    public void testTranslateModelWithNullVector() {
        try {
            new AffineBuilder().translate().byVector(null).close()
                    .changeModel(new Model(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        } catch (NullPointerException ex) {
            String expectedError = "Vector is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testTranslateMatrixWithNullVector() {
        try {
            AffineMatrix.translateMatrix(null);
        } catch (NullPointerException ex) {
            String expectedError = "Vector is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testScaleMatrix() {
        Matrix4x4 matrix = AffineMatrix.translateMatrix(new Vector3D(5.5, -10, 3));

        double[][] result = matrix.getMatrix();
        double[][] expectedResult = new double[][]{{1, 0, 0, 5.5}, {0, 1, 0, -10}, {0, 0, 1, 3}, {0, 0, 0, 1}};

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
