package ru.vsu.cs.baklanova;

import com.cgvsu.AffineTransformations.Scale;
import ru.vsu.cs.baklanova.Math.vector.Vector3D;
import ru.vsu.cs.baklanova.model.Model;
import ru.vsu.cs.baklanova.objrealisation.IncorrectFileException;
import ru.vsu.cs.baklanova.objrealisation.ObjReader;
import ru.vsu.cs.baklanova.objrealisation.PathReadException;
import ru.vsu.cs.baklanova.objwriter.ObjWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/*public class Main {

    public static void main(String[] args) throws IOException {

        Path fileName = Path.of("../../3DModels/Faceform/WrapHead.obj");
        String fileContent = Files.readString(fileName);

        System.out.println("Loading model ...");
        Model model = ObjReader.read(fileContent);

        System.out.println("Vertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals: " + model.normals.size());
        System.out.println("Polygons: " + model.polygons.size());
    }
}*/

public class Main {

    public static void main(String[] args) throws PathReadException, IncorrectFileException, IOException {
        String fileContent = null;
        try {
            Path fileName = Path.of("C:\\Repositories\\Task3\\3DModels\\SimpleModelsForReaderTests\\Teapot.obj");
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

        Scale.scaleModel(model,new Vector3D(2, -0.5,1));

        ObjWriter.write(model,"result");
    }

    public static final float eps = 1e-7f;
}