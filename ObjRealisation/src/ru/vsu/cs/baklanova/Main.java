package ru.vsu.cs.baklanova;

import ru.vsu.cs.baklanova.AffineTransformation.Rotation;
import ru.vsu.cs.baklanova.AffineTransformation.Scale;
import ru.vsu.cs.baklanova.AffineTransformation.Translation;
import ru.vsu.cs.baklanova.Math.vector.Vector3D;
import ru.vsu.cs.baklanova.model.Model;
import ru.vsu.cs.baklanova.objreader.ObjReader;
import ru.vsu.cs.baklanova.objreader.PathReadException;
import ru.vsu.cs.baklanova.objwriter.ObjWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Main {

    public static void main(String[] args) throws Exception {
        String fileContent = null;
        try {
            Path fileName = Path.of("C:\\Repositories\\AffineTransformation\\3DModels\\SimpleModelsForReaderTests\\Teapot01.obj");
            fileContent = Files.readString(fileName);
        } catch (IOException e) {
            throw new PathReadException();
        }

        System.out.println("Loading model ...");
        Model model = ObjReader.read(fileContent);

        System.out.println("Vertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals: " + model.normals.size());
        System.out.println("Polygons: " + model.polygons.size());

        Scale.scaleModel(model, new Vector3D(1, 2, 1));

        Rotation.rotateModelDegrees(model, 'x', 90);
        //Rotation.rotateModelDegrees(model, 'y', 90);
        //Rotation.rotateModelDegrees(model, 'z', 90);
        //Translation.translateModelRadiansForFewAxis(model, 10, 20, 30);

        ObjWriter.write(model,"result");
    }

    public static final float eps = 1e-7f;

}