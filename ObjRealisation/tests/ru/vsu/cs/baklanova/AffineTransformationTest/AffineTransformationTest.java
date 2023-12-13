package ru.vsu.cs.baklanova.AffineTransformationTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.baklanova.Affine.AffineBuilder.AffineBuilder;
import ru.vsu.cs.baklanova.math.vector.Vector3D;
import ru.vsu.cs.baklanova.model.Model;

import java.util.ArrayList;

public class AffineTransformationTest {
    public final int SIZE = 3;

    public final double DELTA = 10e-15;

    @Test
    public void vertexAndNormalsChangingTest() {
        ArrayList<Vector3D> vertex = new ArrayList<Vector3D>();
        vertex.add(new Vector3D(-2.0, 1.0, 0.1));

        ArrayList<Vector3D> normal = new ArrayList<Vector3D>();
        normal.add(new Vector3D(-4.0, 2.0, 0.2));

        Model model1 = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Model model2 = new Model(new ArrayList<>(), new ArrayList<>(), normal, new ArrayList<>());

        Model resultModel1 = new AffineBuilder().scale().byVector(new Vector3D(3, 2, 1)).close().returnChangedModel(model1);
        Model resultModel2 = new AffineBuilder().scale().byVector(new Vector3D(3, 2, 1)).close().returnChangedModel(model2);

        ArrayList<Vector3D> newVertexes = resultModel1.vertices;
        ArrayList<Vector3D> newNormals = resultModel2.normals;

        Assertions.assertEquals(newVertexes.size(), newNormals.size());
        for (int j = 0; j < newVertexes.size(); j++) {
            for (int i = 0; i < SIZE; i++) {
                double vertNum = newVertexes.get(j).get(i);
                double normNum = newNormals.get(j).get(i);
                double expected = vertex.get(j).get(i) / normal.get(j).get(i);
                Assertions.assertEquals(expected, vertNum/normNum, DELTA);
            }
        }
    }

    @Test
    public void nullModelChangingTest() {
        Model model = null;
        try {
            Model result = new AffineBuilder().returnChangedModel(model);
        } catch (NullPointerException ex) {
            String expectedError = "Model is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void emptyModelChangingTest() throws Exception {
        Model model = new Model(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Model result = new AffineBuilder().scale().byVector(new Vector3D(1, 0, -16.7)).close().rotate().byX(90).close()
                .translate().byVector(new Vector3D(0, -11, 0.4)).close().returnChangedModel(model);

        Assertions.assertEquals(result.vertices.size(), 0);
        Assertions.assertEquals(result.textureVertices.size(), 0);
        Assertions.assertEquals(result.normals.size(), 0);
        Assertions.assertEquals(result.polygons.size(), 0);
    }

    @Test
    public void emptyAffineBuilderReturnTest() {
        ArrayList<Vector3D> vertex = new ArrayList<Vector3D>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Model resultModel = new AffineBuilder().returnChangedModel(model);
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
    public void testAffineBuilderOnModelWithNullVertexes1() {
        Model model = new Model(null, null, null, null);
        new AffineBuilder().changeModel(model);
        Assertions.assertNull(model.vertices);
        Assertions.assertNull(model.normals);
    }

    @Test
    public void testAffineBuilderOnModelWithNullVertexes2() {
        Model model = new Model(null, null, null, null);
        model = new AffineBuilder().returnChangedModel(model);
        Assertions.assertEquals(model.vertices.size(), 0);
        Assertions.assertEquals(model.normals.size(), 0);
    }

    @Test
    public void testAffineBuilderOnModelWithNullVertex() {
        ArrayList<Vector3D> vertex = new ArrayList<>();
        vertex.add(null);
        vertex.add(new Vector3D(0, 0, 0));
        Model model = new Model(vertex, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        try {
            new AffineBuilder().changeModel(model);
        } catch (NullPointerException ex) {
            String expectedError = "Dot is null";
            Assertions.assertEquals(expectedError, ex.getMessage());
        }
    }

    @Test
    public void affinesWithoutChangingTest() throws Exception {
        ArrayList<Vector3D> vertex = new ArrayList<Vector3D>();
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

        Vector3D translate = new Vector3D(0, 0, 0);
        resultModel = affineBuilder.translate().byVector(translate).close().returnChangedModel(model);
        result = resultModel.vertices;

        Assertions.assertEquals(result.size(), vertex.size());
        for (int j = 0; j < result.size(); j++) {
            for (int i = 0; i < SIZE; i++) {
                double resultNum = result.get(j).get(i);
                Assertions.assertEquals(vertex.get(j).get(i), resultNum, DELTA);
            }
        }

        resultModel = affineBuilder.rotate().XYZ(new Vector3D(0, 360, -42.3)).byZ( 42.3).close().returnChangedModel(model);
        result = resultModel.vertices;

        Assertions.assertEquals(result.size(), vertex.size());
        for (int j = 0; j < result.size(); j++) {
            for (int i = 0; i < SIZE; i++) {
                double resultNum = result.get(j).get(i);
                Assertions.assertEquals(vertex.get(j).get(i), resultNum, DELTA);
            }
        }
    }
}
