package ru.vsu.cs.baklanova.AffineTransformationTest;

import  ru.vsu.cs.baklanova.AffineTransformation.Scale;
import  ru.vsu.cs.baklanova.Math.vector.Vector2D;
import  ru.vsu.cs.baklanova.Math.vector.Vector3D;
import  ru.vsu.cs.baklanova.model.Model;
import  ru.vsu.cs.baklanova.model.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ScaleTest {
    @Test
    public void testScaleModel1() {
        ArrayList<Vector3D> vertex = new ArrayList<Vector3D>();
        vertex.add(new Vector3D(-2.0, 0.0, 0.1));

        Model model = new Model(vertex, new ArrayList<Vector3D>(), new ArrayList<Vector3D>(), new ArrayList<Polygon>());
        Model result = Scale.scaleModel(model, new Vector3D(0.0, 0.0, 0.0));

        Assertions.assertEquals(0.0, result.vertices.get(0).get(0), 10e-5);
        Assertions.assertEquals(0.0, result.vertices.get(0).get(1), 10e-5);
        Assertions.assertEquals(0.0, result.vertices.get(0).get(2), 10e-5);
    }


    @Test
    public void testScaleModel2() {
        ArrayList<Vector3D> vertex = new ArrayList<Vector3D>();
        vertex.add(new Vector3D(2.0, 0.0, -0.1));

        Model model = new Model(vertex, new ArrayList<Vector3D>(), new ArrayList<Vector3D>(), new ArrayList<Polygon>());
        Model result = Scale.scaleModel(model, new Vector3D(1.0, 1.0, 1.0));

        Assertions.assertEquals(2.0, result.vertices.get(0).get(0), 10e-5);
        Assertions.assertEquals(1.0, result.vertices.get(0).get(1), 10e-5);
        Assertions.assertEquals(-0.1, result.vertices.get(0).get(2), 10e-5);
    }

    @Test
    public void testScaleModel3() {
        ArrayList<Vector3D> vertex = new ArrayList<Vector3D>();
        vertex.add(new Vector3D(2.0, 1.0, -0.1));
        vertex.add(new Vector3D(1.0, 8.0, -0.126));
        vertex.add(new Vector3D(4.0, 11.0, 0.0));
        Model model = new Model(vertex, new ArrayList<Vector3D>(), new ArrayList<Vector3D>(), new ArrayList<Polygon>());
        Model result = Scale.scaleModel(model, new Vector3D(3.0, -1.0, 4.0));

        Assertions.assertEquals(6.0, result.vertices.get(0).get(0), 10e-5);
        Assertions.assertEquals(-1.0, result.vertices.get(0).get(1), 10e-5);
        Assertions.assertEquals(-0.4, result.vertices.get(0).get(2), 10e-5);

        Assertions.assertEquals(3.0, result.vertices.get(1).get(0), 10e-5);
        Assertions.assertEquals(-8.0, result.vertices.get(1).get(1), 10e-5);
        Assertions.assertEquals(-0.504, result.vertices.get(1).get(2), 10e-5);

        Assertions.assertEquals(12.0, result.vertices.get(1).get(0), 10e-5);
        Assertions.assertEquals(-11.0, result.vertices.get(1).get(1), 10e-5);
        Assertions.assertEquals(0.0, result.vertices.get(1).get(2), 10e-5);
    }
}