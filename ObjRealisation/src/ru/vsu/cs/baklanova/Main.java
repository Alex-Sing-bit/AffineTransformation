package ru.vsu.cs.baklanova;

import ru.vsu.cs.baklanova.Affine.AffineBuilder;
import ru.vsu.cs.baklanova.Affine.AxisEnum;
import ru.vsu.cs.baklanova.math.vector.Vector3D;
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


        //Model model1 = Rotation.rotateModelFewTimesDegrees(model, new char[]{'x', 'y', 'z'}, new double[] {90, -90, 90});

        //Y направлен вниз?

        //model = Rotation.rotateModelDegrees(model, 'y', 90);
        //model = Scale.scaleModel(model, new Vector3D(1, 2, 1));

        //model1 = Translation.translateModelForFewAxis(model1, new Vector3D(10, 20, 30));
        //model = Scale.scaleModel(model, new Vector3D(2, 1, 1));

        //new AffineBuilder().scale(new Vector3D(1, 1, 1)).translate(new Vector3D(5, 5, 5))
         //       .rotateInRadians(AxisEnum.Y, 3).applyModel(model);

        AffineBuilder a = new AffineBuilder();
        a.rotateInDegrees(AxisEnum.Y, 180).scale(new Vector3D(1, 2, 1)).returnChangedModel(model);
        a.rotateInDegrees(AxisEnum.X, 120);
        Model model1 = a.returnChangedModel(model);
        ObjWriter.write(model1,"result");
        //ObjWriter.write(model1,"result1");


        /*Affine a = new Affine();
        a.add(new Translate(1, 2, 3));
        a.add(new Rotate(Axis.X, 10));
        a.add(new Rotate(Axis.Y, 20));
        a.apply(model);

        new AffineBuilder().translate(5, 5, 5).rotate(Axis.X, 7).scale().byX(4).byY(5).end().moveByX(5).build().apply(model)*/


    }
}