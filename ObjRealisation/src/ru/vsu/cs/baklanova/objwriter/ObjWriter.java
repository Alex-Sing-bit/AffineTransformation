package ru.vsu.cs.baklanova.objwriter;

import ru.vsu.cs.baklanova.math.vector.Vector3D;
import ru.vsu.cs.baklanova.model.Model;
import ru.vsu.cs.baklanova.model.Polygon;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ObjWriter {
    private static final String OBJ_VERTEX_TOKEN = "v";
    private static final String OBJ_TEXTURE_TOKEN = "vt";
    private static final String OBJ_NORMAL_TOKEN = "vn";
    private static final String OBJ_FACE_TOKEN = "f";
    private static final String OBJ_GROUP_TOKEN = "g";

    public static void write(Model model, String fileName) throws IOException {
        File file = getObjFileByPath(fileName);
        //если файла с таким именем нет, то создаем новый файл с именем newFile.obj в этом проекте
        //если же закинули директорию то создаем новый файл с именем newFile.obj в этой директории
        PrintStream fileWriter = new PrintStream(file);
        fileWriter.println(getContent(model));

    }
    public static String getContent(Model model){
        String modelContent = "";
        ArrayList<Vector3D> vertices = model.vertices;
        ArrayList<Vector3D> normals = model.normals;
        ArrayList<Polygon> polygons = model.polygons;
        ArrayList<Vector3D> textureVertices = model.textureVertices;

        checkVerticesExistence(vertices);

        modelContent+=getStringVerticesContent(vertices)+"\n";
        StringBuilder normalsInfo = getStringNormalsContent(normals);
        if (!normalsInfo.toString().equals("")){
            modelContent+=normalsInfo+"\n";
        }
        StringBuilder textureVerticesInfo = getStringTextureVerticesContent(textureVertices);
        if (!textureVerticesInfo.toString().equals("")){
            modelContent+=textureVerticesInfo+"\n";
        }
        modelContent+=getStringPolygonsContent(polygons)+"\n";
        return modelContent;
    }

    protected static void checkVerticesExistence(ArrayList<Vector3D> vertices) {
        if (vertices == null) {
            throw new NullPointerException("List of vertices is null");
        }
        if (vertices.isEmpty()) {
            throw new IllegalArgumentException("No vertices found");
        }
    }

    protected static StringBuilder getStringVerticesContent(ArrayList<Vector3D> vertices) {
        StringBuilder str = new StringBuilder();
        for (Vector3D v : vertices) {
            str.append(OBJ_VERTEX_TOKEN + " " + v.coordstoStringSplitBySpace() + "\n");
        }
        str.append("# " + vertices.size() + " vertices\n");
        return str;

    }

    protected static StringBuilder getStringTextureVerticesContent(ArrayList<Vector3D> textureVertices) {
        StringBuilder str = new StringBuilder();
        if (textureVertices == null || textureVertices.isEmpty()) {
            return str;
        }
        for (Vector3D vt : textureVertices) {
            str.append(OBJ_TEXTURE_TOKEN + " " + vt.coordstoStringSplitBySpace() +  "\n");
        }
        str.append("# " + textureVertices.size() + " texture coords\n");
        return str;
    }

    protected static StringBuilder getStringNormalsContent(ArrayList<Vector3D> normals) {
        StringBuilder str = new StringBuilder();
        if (normals == null || normals.isEmpty()) {
            return str;
        }
        for (Vector3D vn : normals) {
            str.append(OBJ_NORMAL_TOKEN + " " + vn.coordstoStringSplitBySpace() + "\n");
        }
        str.append("# " + normals.size() + " vertex normals\n");
        return str;
    }

    protected static StringBuilder getStringPolygonsContent(ArrayList<Polygon> polygons) {
        StringBuilder str = new StringBuilder();
        if (polygons == null || polygons.isEmpty()) {
            return str;
        }
        String currGroupName = "";
        for (Polygon polygon : polygons) {
            if (!polygon.getGroupName().equals("") && !polygon.getGroupName().equals(currGroupName)) {
                currGroupName = polygon.getGroupName();
                str.append(OBJ_GROUP_TOKEN + " " + currGroupName + "\n");
            }
            str.append(OBJ_FACE_TOKEN + " " + polygonInfoInObjForm(polygon) + "\n");
        }
        str.append("# " + polygons.size() + " polygons\n");
        return str;
    }

    protected static StringBuilder polygonInfoInObjForm(Polygon polygon) {
        return polygonInfoString(polygon);
    }

    private static StringBuilder polygonInfoString(Polygon polygon) {
        if (polygon == null) {
            throw new NullPointerException("Polygon is null");
        }
        if (polygon.getVertexIndices() == null || polygon.getVertexIndices().isEmpty()) {
            throw new IllegalArgumentException("No vertices found in polygon");
        }
        ArrayList<Integer> vertexIndices = polygon.getVertexIndices();
        ArrayList<Integer> textureVertexIndices = polygon.getTextureVertexIndices();
        ArrayList<Integer> normalIndices = polygon.getNormalIndices();

        ArrayList<StringBuilder> oneVertexInPolygonInfoString = new ArrayList<>();

        StringBuilder str = new StringBuilder();
        for (Integer v : vertexIndices) {
            oneVertexInPolygonInfoString.add(new StringBuilder((v + 1) + ""));
        }
        boolean isTextured = false;
        String slash = "/";
        if (!textureVertexIndices.isEmpty()) {
            isTextured = true;
            for (int i = 0; i < oneVertexInPolygonInfoString.size(); i++) {
                oneVertexInPolygonInfoString.get(i).append(slash + (textureVertexIndices.get(i) + 1));
            }
        }
        if (!normalIndices.isEmpty()) {
            slash = (isTextured) ? "/" : "//";
            for (int i = 0; i < oneVertexInPolygonInfoString.size(); i++) {
                oneVertexInPolygonInfoString.get(i).append(slash + (normalIndices.get(i) + 1));
            }
        }
        for (StringBuilder info : oneVertexInPolygonInfoString) {
            str.append(info + " ");
        }
        return str;
    }

    protected static File getObjFileByPath(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        File file;
        if (Files.isDirectory(path)) {
            file = new File(filePath + "newFile.obj");
            file.createNewFile();
            return file;
        }
        if (!Files.exists(path)) {
            filePath = (filePath.equals("")) ? "newFile.obj" : getFileName(filePath);
            file = new File(filePath);
            file.createNewFile();
            return file;
        }
        file = new File(path.toUri());
        return file;
    }

    private static String getFileName(String filePath) {
        if (filePath.indexOf('.') < 0) {
            return filePath + ".obj";
        }
        return filePath.substring(0, filePath.indexOf('.') + 1) + "obj";

    }
}
