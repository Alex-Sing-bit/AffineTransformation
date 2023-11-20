package ru.vsu.cs.baklanova.AffineTransformationTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.baklanova.AffineTransformation.Rotation;
import ru.vsu.cs.baklanova.AffineTransformation.Scale;
import ru.vsu.cs.baklanova.AffineTransformation.Translation;
import ru.vsu.cs.baklanova.Math.matrix.Matrix3x3;
import ru.vsu.cs.baklanova.Math.matrix.Matrix4x4;
import ru.vsu.cs.baklanova.Math.vector.Vector3D;
import ru.vsu.cs.baklanova.model.Model;

import java.util.ArrayList;

public class TranslationTest {
    public final int SIZE = 3;

    public final double DELTA = 10e-15;

    @Test
    public void testTranslateModel1() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model result = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        result = Translation.translateModel(result, 'x', 10.1);

        Vector3D resultVector = result.vertices.get(0);
        Vector3D expected = new Vector3D(8.1, 0.0, 0.1);
        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(expected.get(i), resultVector.get(i), DELTA);
        }
    }

    @Test
    public void testTranslateModel2() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model result = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        result = Translation.translateModelForFewAxis(result, 0, 0, 0);

        Vector3D resultVector = result.vertices.get(0);
        Vector3D expected = new Vector3D(-2.0, 0.0, 0.1);
        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(expected.get(i), resultVector.get(i), DELTA);
        }
    }

    @Test
    public void testTranslateModel3() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        Model result = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        result = Translation.translateModelForFewAxis(result, -5, 0, 4.44);

        Vector3D resultVector = result.vertices.get(0);
        Vector3D expected = new Vector3D(-7.0, 1.0, 4.54);
        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(expected.get(i), resultVector.get(i), DELTA);
        }
    }

    @Test
    public void testTranslateModel4() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model result = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        result = Translation.translateModel(result, 'x', 10.1);
        result = Translation.translateModelForFewAxis(result, -5, -3.1, 0.0);

        Vector3D resultVector = result.vertices.get(0);
        Vector3D expected = new Vector3D(3.1, -3.1, 0.1);
        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(expected.get(i), resultVector.get(i), DELTA);
        }
    }

    @Test
    public void testTranslateModelInDifferentWays() throws Exception {
        ArrayList<Vector3D> vertex1 = new ArrayList<>();
        vertex1.add(new Vector3D(-2.0, 1.0, 0.1));
        ArrayList<Vector3D> vertex2 = new ArrayList<>();
        vertex2.add(new Vector3D(-2.0, 1.0, 0.1));

        Model result1 = new Model(vertex1, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Model result2 = new Model(vertex2, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        result1 = Translation.translateModel(result1, 'x', 4);
        result1 = Translation.translateModel(result1, 'y', 3);
        result2 = Translation.translateModelForFewAxis(result2, 4, 3, 0.0);

        Vector3D result1Vector = result1.vertices.get(0);
        Vector3D result2Vector = result2.vertices.get(0);
        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(result2Vector.get(i), result1Vector.get(i), DELTA);
        }
    }

    @Test
    public void testTranslateNullModel1() {
        try {
            Translation.translateModelForFewAxis(null, 2, 0, -9);
        } catch (Exception ex) {
            String expectedError = "Model is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testTranslateNullModel2() {
        try {
            Translation.translateModel(null, 'x', 10);
        } catch (Exception ex) {
            String expectedError = "Model is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testTranslateModelWithWrongAxis1() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        try {
            Translation.translateModel(model, 's', 90);
        } catch (Exception ex) {
            String expectedError = "Translation exception: invalid axis specified";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testTranslateModelWithNullNormal() {
        ArrayList<Vector3D> normal = new ArrayList<>();
        normal.add(new Vector3D(1, 1, 1));
        normal.add(null);
        Model model = new Model(new ArrayList<>(), new ArrayList<>(), normal, new ArrayList<>());
        try {
            Translation.translateModelForFewAxis(model, 1, 0, 1);
        } catch (NullPointerException ex) {
            String expectedError = "Dot vector is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testTranslateModelWithNullVertex() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(null);
        vertex.add(new Vector3D(0, 0, 0));
        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        try {
            Translation.translateModel(model, 'x', 1);
        } catch (NullPointerException ex) {
            String expectedError = "Dot vector is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testTranslationMatrix1() throws Exception {
        Matrix4x4 matrix = Translation.translateMatrix('x', 5.5);

        double[][] result = matrix.getMatrix();
        double[][] expectedResult = new double[][]{{1, 0, 0, 5.5}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};

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

    @Test
    public void testTranslationMatrix2() throws Exception {
        Matrix4x4 matrix = Translation.translateMatrixForFewAxis(5.5, -10, 3);

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
