package ru.vsu.cs.baklanova.AffineTransformationTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.baklanova.AffineTransformation.Translation;
import ru.vsu.cs.baklanova.math.vector.Vector3D;
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
        result = Translation.translateModelForFewAxis(result, new Vector3D(0, 0, 0));

        Vector3D resultVector = result.vertices.get(0);
        Vector3D expected = new Vector3D(-2.0, 0.0, 0.1);
        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(expected.get(i), resultVector.get(i), DELTA);
        }
    }

    @Test
    public void testTranslateModel2() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        Model result = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        result = Translation.translateModelForFewAxis(result, new Vector3D(-5, 0, 4.44));

        Vector3D resultVector = result.vertices.get(0);
        Vector3D expected = new Vector3D(-7.0, 1.0, 4.54);
        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(expected.get(i), resultVector.get(i), DELTA);
        }
    }


    @Test
    public void testTranslateModelWithNullVector() {
        try {
            Translation.translateModelForFewAxis(new Model(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()),null);
        } catch (NullPointerException ex) {
            String expectedError = "Vector for multiplication is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }
    @Test
    public void testTranslateNullModel1() {
        try {
            Translation.translateModelForFewAxis(null, new Vector3D(2, 0, -9));
        } catch (Exception ex) {
            String expectedError = "Model is null";
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
            Translation.translateModelForFewAxis(model, new Vector3D(1, 0, 1));
        } catch (NullPointerException ex) {
            String expectedError = "Dot vector is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }



    /*@Test
    public void testTranslationMatrix() throws Exception {
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
    }*/
}
