package ru.vsu.cs.baklanova.model;
import ru.vsu.cs.baklanova.math.vector.Vector2D;
import ru.vsu.cs.baklanova.math.vector.Vector3D;

import java.util.*;

public class Model {

    public ArrayList<Vector3D> vertices = new ArrayList<>();
    public ArrayList<Vector3D> textureVertices = new ArrayList<>();
    public ArrayList<Vector3D> normals = new ArrayList<>();
    public ArrayList<Polygon> polygons = new ArrayList<>();


    public Model(ArrayList<Vector3D> vertices, ArrayList<Vector3D> textureVertices, ArrayList<Vector3D> normals, ArrayList<Polygon> polygons) {
        this.vertices = vertices;
        this.textureVertices = textureVertices;
        this.normals = normals;
        this.polygons = polygons;
    }

    public Model (Model model) {
        ArrayList<Vector3D> checkList = model.vertices;
        if (checkList != null) {
            this.vertices.addAll(model.vertices);
        }
        ArrayList<Vector3D> checkList2 = model.textureVertices;
        if (checkList2 != null) {
            this.textureVertices.addAll(model.textureVertices);
        }

        checkList = model.normals;
        if (checkList != null) {
            this.normals.addAll(model.normals);
        }

        ArrayList<Polygon> checkListP = model.polygons;
        if (checkListP != null) {
            this.polygons.addAll(model.polygons);
        }
    }

    public Model() {}
}
