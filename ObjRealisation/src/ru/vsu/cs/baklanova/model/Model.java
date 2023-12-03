package ru.vsu.cs.baklanova.model;
import ru.vsu.cs.baklanova.math.vector.Vector3D;

import java.util.*;

public class Model {

    public ArrayList<Vector3D> vertices;
    public ArrayList<Vector3D> textureVertices;
    public ArrayList<Vector3D> normals;
    public ArrayList<Polygon> polygons;


    public Model(ArrayList<Vector3D> vertices, ArrayList<Vector3D> textureVertices, ArrayList<Vector3D> normals, ArrayList<Polygon> polygons) {
        this.vertices = vertices;
        this.textureVertices = textureVertices;
        this.normals = normals;
        this.polygons = polygons;
    }


}
