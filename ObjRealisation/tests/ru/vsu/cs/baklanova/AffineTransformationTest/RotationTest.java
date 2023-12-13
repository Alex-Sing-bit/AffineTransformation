package ru.vsu.cs.baklanova.AffineTransformationTest;

import ru.vsu.cs.baklanova.Affine.AffineBuilder.AffineBuilder;
import ru.vsu.cs.baklanova.Affine.AffineMatrix;
import ru.vsu.cs.baklanova.math.vector.Vector3D;
import ru.vsu.cs.baklanova.model.Model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RotationTest {
    public final int SIZE = 3;

    public final double DELTA = 10e-15;

    @Test
    public void affineBuilderRotateTest1() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Model resultModel = new AffineBuilder().rotate().byX(1000).close().returnChangedModel(model);
        ArrayList<Vector3D> result = resultModel.vertices;

        double angle = Math.toRadians(1000);
        double x = -2.0;
        double y = Math.cos(angle) + 0.1 * Math.sin(angle);
        double z =  - Math.sin(angle) + 0.1 * Math.cos(angle);
        Vector3D expected = new Vector3D(x, y ,z);
        Assertions.assertEquals(result.size(), vertex.size());
        for (Vector3D dot : result) {
            for (int i = 0; i < SIZE; i++) {
                double resultNum = dot.get(i);
                Assertions.assertEquals(expected.get(i), resultNum, DELTA);
            }
        }
    }

    @Test
    public void affineBuilderRotateTest2() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Model resultModel = new AffineBuilder().rotate().byY(99).close().returnChangedModel(model);
        ArrayList<Vector3D> result = resultModel.vertices;

        double angle = Math.toRadians(99);
        double x = -2.0 * Math.cos(angle) + 0.1 * Math.sin(angle);
        double y = 1;
        double z = 2 * Math.sin(angle) + 0.1 * Math.cos(angle);
        Vector3D expected = new Vector3D(x, y ,z);
        Assertions.assertEquals(result.size(), vertex.size());
        for (Vector3D dot : result) {
            for (int i = 0; i < SIZE; i++) {
                double resultNum = dot.get(i);
                Assertions.assertEquals(expected.get(i), resultNum, DELTA);
            }
        }
    }

    @Test
    public void affineBuilderRotateTest3() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Model resultModel = new AffineBuilder().rotate().byZ(-36).close().returnChangedModel(model);
        ArrayList<Vector3D> result = resultModel.vertices;

        double angle = Math.toRadians(-36);
        double x = -2.0 * Math.cos(angle) + Math.sin(angle);
        double y = 2.0 * Math.sin(angle) + Math.cos(angle);
        double z = 0.1;
        Vector3D expected = new Vector3D(x, y ,z);
        Assertions.assertEquals(result.size(), vertex.size());
        for (Vector3D dot : result) {
            for (int i = 0; i < SIZE; i++) {
                double resultNum = dot.get(i);
                Assertions.assertEquals(expected.get(i), resultNum, DELTA);
            }
        }
    }
    @Test
    public void affineBuilderRotateTest4() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Vector3D rotate = new Vector3D(90, 90, 90);
        Model resultModel = new AffineBuilder().rotate().XYZ(rotate).close().returnChangedModel(model);
        ArrayList<Vector3D> result = resultModel.vertices;

        Vector3D expected = new Vector3D(0.1, 1, 2);
        Assertions.assertEquals(result.size(), vertex.size());
        for (Vector3D dot : result) {
            for (int i = 0; i < SIZE; i++) {
                double resultNum = dot.get(i);
                Assertions.assertEquals(expected.get(i), resultNum, DELTA);
            }
        }
    }

    @Test
    public void affineBuilderRotateTest5() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Vector3D rotate = new Vector3D(90, 90, 90);
        Model resultModel = new AffineBuilder().rotate().XZY(rotate).close().returnChangedModel(model);
        ArrayList<Vector3D> result = resultModel.vertices;

        Vector3D expected = new Vector3D(-1, 2, -0.1);
        Assertions.assertEquals(result.size(), vertex.size());
        for (Vector3D dot : result) {
            for (int i = 0; i < SIZE; i++) {
                double resultNum = dot.get(i);
                Assertions.assertEquals(expected.get(i), resultNum, DELTA);
            }
        }
    }

    @Test
    public void affineBuilderRotateTest6() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Vector3D rotate = new Vector3D(90, 90, 90);
        Model resultModel = new AffineBuilder().rotate().YXZ(rotate).close().returnChangedModel(model);
        ArrayList<Vector3D> result = resultModel.vertices;

        Vector3D expected = new Vector3D(2, -0.1, -1);
        Assertions.assertEquals(result.size(), vertex.size());
        for (Vector3D dot : result) {
            for (int i = 0; i < SIZE; i++) {
                double resultNum = dot.get(i);
                Assertions.assertEquals(expected.get(i), resultNum, DELTA);
            }
        }
    }

    @Test
    public void affineBuilderRotateTest7() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Vector3D rotate = new Vector3D(90, 90, 90);
        Model resultModel = new AffineBuilder().rotate().YZX(rotate).close().returnChangedModel(model);
        ArrayList<Vector3D> result = resultModel.vertices;

        Vector3D expected = new Vector3D(1, 2, 0.1);
        Assertions.assertEquals(result.size(), vertex.size());
        for (Vector3D dot : result) {
            for (int i = 0; i < SIZE; i++) {
                double resultNum = dot.get(i);
                Assertions.assertEquals(expected.get(i), resultNum, DELTA);
            }
        }
    }

    @Test
    public void affineBuilderRotateTest8() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Vector3D rotate = new Vector3D(90, 90, 90);
        Model resultModel = new AffineBuilder().rotate().ZXY(rotate).close().returnChangedModel(model);
        ArrayList<Vector3D> result = resultModel.vertices;

        Vector3D expected = new Vector3D(-2, 0.1, -1);
        Assertions.assertEquals(result.size(), vertex.size());
        for (Vector3D dot : result) {
            for (int i = 0; i < SIZE; i++) {
                double resultNum = dot.get(i);
                Assertions.assertEquals(expected.get(i), resultNum, DELTA);
            }
        }
    }

    @Test
    public void affineBuilderRotateTest9() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Vector3D rotate = new Vector3D(90, 90, 90);
        Model resultModel = new AffineBuilder().rotate().ZYX(rotate).close().returnChangedModel(model);
        ArrayList<Vector3D> result = resultModel.vertices;

        Vector3D expected = new Vector3D(0.1, -1, -2);
        Assertions.assertEquals(result.size(), vertex.size());
        for (Vector3D dot : result) {
            for (int i = 0; i < SIZE; i++) {
                double resultNum = dot.get(i);
                Assertions.assertEquals(expected.get(i), resultNum, DELTA);
            }
        }
    }

    @Test
    public void affineBuilderRotateTest10() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Vector3D rotate = new Vector3D(90, 0, 0);
        Model resultModel1 = new AffineBuilder().rotate().ZYX(rotate).close().returnChangedModel(model);
        ArrayList<Vector3D> result1 = resultModel1.vertices;
        Model resultModel2 = new AffineBuilder().rotate().YZX(rotate).close().returnChangedModel(model);
        ArrayList<Vector3D> result2 = resultModel2.vertices;

        Assertions.assertEquals(result1.size(), result2.size());
        for (int i = 0; i < result1.size(); i++)
            Assertions.assertTrue(result1.equals(result2));
    }

    @Test
    public void rotateWithoutChangingTest1() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        AffineBuilder affineBuilder = new AffineBuilder();

        Model resultModel = affineBuilder.rotate().XZY(new Vector3D(360, 360, 360)).close().returnChangedModel(model);
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
    public void rotateWithoutChangingTest2() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        AffineBuilder affineBuilder = new AffineBuilder();

        Model resultModel = affineBuilder.rotate().byX(100).byX(-100).close().returnChangedModel(model);
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
    public void rotateWithoutChangingTest3() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        AffineBuilder affineBuilder = new AffineBuilder();

        Model resultModel = affineBuilder.rotate().byX(120).byX(120).XZY(new Vector3D(120, 0, 0)).close().returnChangedModel(model);
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
    public void testRotateModelWithNullVector() {
        try {
            new AffineBuilder().rotate().XZY(null).close()
                    .changeModel(new Model(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        } catch (Exception ex) {
            String expectedError = "Angles vector is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testRotateMatrixWithNullVector() {
        try {
            AffineMatrix.rotateMatrix(4, 2);
        } catch (Exception ex) {
            String expectedError = "Wrong axis";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }
}
