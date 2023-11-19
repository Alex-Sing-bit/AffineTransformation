package ru.vsu.cs.baklanova.AffineTransformationTest;
import ru.vsu.cs.baklanova.AffineTransformation.RotateExceptions;
import ru.vsu.cs.baklanova.AffineTransformation.Rotation;
import ru.vsu.cs.baklanova.Math.matrix.Matrix3x3;
import  ru.vsu.cs.baklanova.Math.vector.Vector3D;
import  ru.vsu.cs.baklanova.model.Model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RotationTest {
    public static double EXP = 10e-15;
    @Test
    public void TestRotateModel1() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<Vector3D>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model result = new Model(vertex, new ArrayList<>(), vertex, new ArrayList<>());
        Rotation.rotateModelDegrees(result, 'z', 0);
        ArrayList<Vector3D> vertices = result.vertices;
        ArrayList<Vector3D> normals = result.normals;
        ArrayList<Vector3D> expected = new ArrayList<>();
        expected.add(new Vector3D(-2.0, 0.0, 0.1));

        for (int j = 0; j < expected.size(); j++) {
            Vector3D vector = expected.get(j);
            final int SIZE = 3;
            for (int i = 0; i < SIZE; i++) {
                double expectedNum = vector.get(i);
                Assertions.assertEquals(expectedNum, vertices.get(j).get(i), EXP);
                Assertions.assertEquals(expectedNum, normals.get(j).get(i), EXP);
            }
        }
    }

    @Test
    public void TestRotateModel2() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<Vector3D>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model result = new Model(vertex, new ArrayList<>(), vertex, new ArrayList<>());
        Rotation.rotateModelDegrees(result, 'x', 360);
        ArrayList<Vector3D> vertices = result.vertices;
        ArrayList<Vector3D> normals = result.normals;
        ArrayList<Vector3D> expected = new ArrayList<>();
        expected.add(new Vector3D(-2.0, 0.0, 0.1));

        for (int j = 0; j < expected.size(); j++) {
            Vector3D vector = expected.get(j);
            final int SIZE = 3;
            for (int i = 0; i < SIZE; i++) {
                double expectedNum = vector.get(i);
                Assertions.assertEquals(expectedNum, vertices.get(j).get(i), EXP);
                Assertions.assertEquals(expectedNum, normals.get(j).get(i), EXP);
            }
        }
    }

    @Test
    public void TestRotateModel4() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<Vector3D>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        Model result = new Model(vertex, new ArrayList<>(), vertex, new ArrayList<>());
        Rotation.rotateModelDegrees(result, 'y', 124);
        Rotation.rotateModelDegrees(result, 'y', -124);
        ArrayList<Vector3D> vertices = result.vertices;
        ArrayList<Vector3D> normals = result.normals;
        ArrayList<Vector3D> expected = new ArrayList<>();
        expected.add(new Vector3D(-2.0, 1.0, 0.1));

        for (int j = 0; j < expected.size(); j++) {
            Vector3D vector = expected.get(j);
            final int SIZE = 3;
            for (int i = 0; i < SIZE; i++) {
                double expectedNum = vector.get(i);
                Assertions.assertEquals(expectedNum, vertices.get(j).get(i), EXP);
                Assertions.assertEquals(expectedNum, normals.get(j).get(i), EXP);
            }
        }
    }

    @Test
    public void TestRotateModel5() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));
        ArrayList<Vector3D> normal = new ArrayList<>();
        normal.add(new Vector3D(-2.0, 1.0, 0.1));

        Model result = new Model(vertex, new ArrayList<>(), normal, new ArrayList<>());
        Rotation.rotateModelDegrees(result, 'x', 180);
        ArrayList<Vector3D> vertices = result.vertices;
        ArrayList<Vector3D> normals = result.normals;
        ArrayList<Vector3D> expected = new ArrayList<>();
        expected.add(new Vector3D(-2.0, -1.0, -0.1));

        for (int j = 0; j < expected.size(); j++) {
            Vector3D vector = expected.get(j);
            final int SIZE = 3;
            for (int i = 0; i < SIZE; i++) {
                double expectedNum = vector.get(i);
                Assertions.assertEquals(expectedNum, vertices.get(j).get(i), EXP);
                Assertions.assertEquals(expectedNum, normals.get(j).get(i), EXP);
            }
        }
    }

    @Test
    public void TestRotateModel6() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));
        ArrayList<Vector3D> normal = new ArrayList<>();
        normal.add(new Vector3D(-2.0, 1.0, 0.1));

        Model result = new Model(vertex, new ArrayList<>(), normal, new ArrayList<>());
        char[] axis = new char[]{'X', 'y', 'Z'};
        double[] angles = new double[]{450, 90, 90};

        Rotation.rotateModelFewTimesDegrees(result, axis, angles);

        ArrayList<Vector3D> vertices = result.vertices;
        ArrayList<Vector3D> normals = result.normals;
        ArrayList<Vector3D> expected = new ArrayList<>();
        expected.add(new Vector3D(0.1, 1.0, 2.0));

        for (int j = 0; j < expected.size(); j++) {
            Vector3D vector = expected.get(j);
            final int SIZE = 3;
            for (int i = 0; i < SIZE; i++) {
                double expectedNum = vector.get(i);
                Assertions.assertEquals(expectedNum, vertices.get(j).get(i), EXP);
                Assertions.assertEquals(expectedNum, normals.get(j).get(i), EXP);
            }
        }
    }

    @Test
    public void TestRotateModelFewTimesInDifferentWays() throws Exception {
        ArrayList<Vector3D> vertex1 = new ArrayList<>();
        vertex1.add(new Vector3D(-2.0, 1.0, 0.1));
        ArrayList<Vector3D> vertex2 = new ArrayList<>();
        vertex2.add(new Vector3D(-2.0, 1.0, 0.1));

        Model result1 = new Model(vertex1, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Model result2 = new Model(vertex2, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        char[] axis = new char[]{'X', 'y', 'Z'};
        double[] angles = new double[]{70, 50, -200};

        Rotation.rotateModelDegrees(result1, 'x', 70);
        Rotation.rotateModelDegrees(result1, 'y', 50);
        Rotation.rotateModelDegrees(result1, 'z', -200);
        Rotation.rotateModelFewTimesDegrees(result2, axis, angles);

        ArrayList<Vector3D> vertices1 = result1.vertices;
        ArrayList<Vector3D> vertices2 = result2.vertices;

        for (int j = 0; j < vertices1.size(); j++) {
            Vector3D vector1 = vertices1.get(j);
            Vector3D vector2 = vertices2.get(j);
            final int SIZE = 3;
            for (int i = 0; i < SIZE; i++) {
                double expectedNum1 = vector1.get(i);
                double expectedNum2 = vector2.get(i);
                Assertions.assertTrue(Math.abs(expectedNum1 - expectedNum2) < EXP);
            }
        }
    }

    @Test
    public void TestRotateModelFewTimesInDegreesAndRadians() throws Exception {
        ArrayList<Vector3D> vertex1 = new ArrayList<>();
        vertex1.add(new Vector3D(-2.0, 1.0, 0.1));
        ArrayList<Vector3D> vertex2 = new ArrayList<>();
        vertex2.add(new Vector3D(-2.0, 1.0, 0.1));

        Model result1 = new Model(vertex1, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Model result2 = new Model(vertex2, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        char[] axis = new char[]{'x', 'Y', 'Z'};
        double[] angles1 = new double[]{90, 45, -30};
        double[] angles2 = new double[]{Math.PI / 2, Math.PI / 4, - Math.PI / 6};

        Rotation.rotateModelFewTimesDegrees(result1, axis, angles1);
        Rotation.rotateModelFewTimesRadians(result2, axis, angles2);

        ArrayList<Vector3D> vertices1 = result1.vertices;
        ArrayList<Vector3D> vertices2 = result2.vertices;

        for (int j = 0; j < vertices1.size(); j++) {
            Vector3D vector1 = vertices1.get(j);
            Vector3D vector2 = vertices2.get(j);
            final int SIZE = 3;
            for (int i = 0; i < SIZE; i++) {
                double expectedNum1 = vector1.get(i);
                double expectedNum2 = vector2.get(i);
                Assertions.assertTrue(Math.abs(expectedNum1 - expectedNum2) < EXP);
            }
        }
    }

    @Test
    public void testRotateNullModel1() {
        try {
            Rotation.rotateModelFewTimesDegrees(null, new char[]{'x'}, new double[]{90});
        } catch (Exception ex) {
            String expectedError = "Model is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testRotateNullModel2() {
        try {
            Rotation.rotateModelFewTimesRadians(null, new char[]{'x'}, new double[]{3});
        } catch (Exception ex) {
            String expectedError = "Model is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testRotateNullModel3() {
        try {
            Rotation.rotateModelDegrees(null, 'x', 90);
        } catch (Exception ex) {
            String expectedError = "Model is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testRotateNullModel4() {
        try {
            Rotation.rotateModelRadians(null, 'x', 14);
        } catch (Exception ex) {
            String expectedError = "Model is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testRotateModelWithWrongAxis1() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        try {
            Rotation.rotateModelDegrees(model, 's', 90);
        } catch (Exception ex) {
            String expectedError = "Rotation exception: invalid axis specified";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testRotateModelWithWrongAxis2() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        try {
            Rotation.rotateModelFewTimesRadians(model, new char[]{'5'}, new double[]{3});
        } catch (Exception ex) {
            String expectedError = "Rotation exception: invalid axis specified";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testRotateModelWithNullVertex() throws RotateExceptions {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(null);
        vertex.add(new Vector3D(0, 0, 0));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        try {
            Rotation.rotateModelRadians(model, 'x', 3);
        } catch (Exception ex) {
            throw ex;
            //String expectedError = "Dot vector is null";
            //Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }
    @Test
    public void testRotateMatrixFewTimesWithTooMuchAngles() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        try {
            Rotation.rotateModelFewTimesRadians(model, new char[1], new double[3]);
        } catch (Exception ex) {
            String expectedError = "Rotation exception: axis number not equal to angles";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testRotateMatrixFewTimesWithTooMuchAxis() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        try {
            Rotation.rotateModelFewTimesDegrees(model, new char[3], new double[2]);
        } catch (Exception ex) {
            String expectedError = "Rotation exception: axis number not equal to angles";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void testRotateMatrix() throws RotateExceptions {
        Matrix3x3 matrix = Rotation.rotationMatrixInDegrees('z', 90);

        double[][] result = matrix.getMatrix();
        double[][] expectedResult = new double[][]{{0, 1, 0}, {-1, 0, 0}, {0, 0, 1}};

        int expectedLength = expectedResult.length;
        int expectedDepth = expectedResult[0].length;

        Assertions.assertEquals(result.length, expectedLength);
        Assertions.assertEquals(result[0].length, expectedDepth);

        for (int i = 0; i < expectedLength; i++) {
            for (int j = 0; j < expectedDepth; j++) {
                Assertions.assertEquals(expectedResult[i][j], result[i][j], EXP);
            }
        }
    }

    @Test
    public void testRotateMatrixCompare() throws RotateExceptions {
        Matrix3x3 matrix = Rotation.rotationMatrixInDegrees('z', 90);
        Matrix3x3 matrix1 = Rotation.rotationMatrixInRadians('z', Math.PI / 2);

        double[][] result = matrix.getMatrix();
        double[][] expectedResult = matrix1.getMatrix();

        int expectedLength = expectedResult.length;
        int expectedDepth = expectedResult[0].length;

        Assertions.assertEquals(result.length, expectedLength);
        Assertions.assertEquals(result[0].length, expectedDepth);

        for (int i = 0; i < expectedLength; i++) {
            for (int j = 0; j < expectedDepth; j++) {
                Assertions.assertEquals(expectedResult[i][j], result[i][j], EXP);
            }
        }
    }

    //Метод RotateDot
}
