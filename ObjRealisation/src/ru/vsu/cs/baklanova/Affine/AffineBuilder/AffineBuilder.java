package ru.vsu.cs.baklanova.Affine.AffineBuilder;

import ru.vsu.cs.baklanova.math.matrix.Matrix4x4;
import ru.vsu.cs.baklanova.math.vector.Vector3D;
import ru.vsu.cs.baklanova.math.vector.Vector4D;
import ru.vsu.cs.baklanova.model.Model;


public class AffineBuilder {
    private Matrix4x4 finalScale;
    private Matrix4x4 finalRotate;
    private Matrix4x4 finalTranslate;

    public AffineBuilder() {
        clear();
    }
    public AffineBuilder(AffineBuilder builder) {
        this.finalScale = new Matrix4x4(builder.finalScale.getMatrix());
        this.finalRotate = new Matrix4x4(builder.finalRotate.getMatrix());
        this.finalTranslate = new Matrix4x4(builder.finalTranslate.getMatrix());
    }

    public Scale scale() {
        return new Scale(this);
    }

    protected void addScale(Matrix4x4 newScale) {
        finalScale = newScale.multiply(finalScale);
    }

    public Rotate rotate() {
        return new Rotate(this);
    }
    protected void addRotate(Matrix4x4 newRotate) throws Exception {
        finalRotate = newRotate.multiply(finalRotate);
    }

    public Translate translate() {
        return new Translate(this);
    }

    protected void addTranslate(Matrix4x4 newTranslate) {
        finalTranslate = newTranslate.add(finalTranslate);
        for (int i = 0; i < finalTranslate.getMatrix().length; i++) {
            finalTranslate.getMatrix()[i][i] = 1;
        }
    }


    public AffineBuilder clear() {
        cleanScale();
        cleanRotate();
        cleanTranslate();

        return this;
    }

    public AffineBuilder cleanScale() {
        finalScale = new Matrix4x4(new double[][] {
                {1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}
        });

        return this;
    }

    public AffineBuilder cleanRotate() {
        finalRotate = new Matrix4x4(new double[][] {
                {1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}
        });

        return this;
    }

    public AffineBuilder cleanTranslate() {
        finalTranslate = new Matrix4x4(new double[][] {
                {1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}
        });

        return this;
    }
    public void changeModel(Model model) {
        if (model.vertices != null) {
            model.vertices.replaceAll(this::changeDot);
        }
        if (model.normals != null) {
            model.normals.replaceAll(this::changeDot);
        }
    }

    public Vector3D returnChangedVector3d(Vector3D vector) {
        return returnVector3D(vector);
    }

    private Vector3D changeDot(Vector3D dot) {
        if (dot == null) {
            throw new NullPointerException("Dot is null");
        }
        return returnVector3D(dot);
    }

    public Model returnChangedModel(Model model1) {
        if (model1 == null) {
            throw new NullPointerException("Model is null");
        }

        Model model = new Model(model1);

        changeModel(model);

        return model;
    }

    private Vector3D returnVector3D(Vector3D v) {
        Vector4D vector = new Vector4D(v);
        vector = returnModelMatrix().multiply(vector);

        return new Vector3D(vector.get(0), vector.get(1), vector.get(2));
    }

    public Matrix4x4 returnModelMatrix () {
        finalScale.multiply(finalRotate).multiply(finalTranslate);

        return finalScale.multiply(finalRotate).multiply(finalTranslate);
    }

    public Matrix4x4 getFinalRotate() {
        return finalRotate;
    }

    public Matrix4x4 getFinalScale() {
        return finalScale;
    }

    public Matrix4x4 getFinalTranslate() {
        return finalTranslate;
    }
}
