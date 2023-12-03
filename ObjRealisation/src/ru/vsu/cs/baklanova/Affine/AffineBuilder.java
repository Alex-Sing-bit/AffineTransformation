package ru.vsu.cs.baklanova.Affine;

import ru.vsu.cs.baklanova.math.matrix.Matrix4x4;
import ru.vsu.cs.baklanova.math.vector.Vector3D;
import ru.vsu.cs.baklanova.math.vector.Vector4D;
import ru.vsu.cs.baklanova.model.Model;

import java.util.ArrayList;

public class AffineBuilder implements Builder {
    private Matrix4x4 finalScale;
    private Matrix4x4 finalRotate;
    private Matrix4x4 finalTranslate;

    @Override
    public AffineBuilder scale(Vector3D scaleVector) {
        if (finalScale == null) {
            finalScale = AffineMatrix.scaleMatrix(scaleVector);
            return this;
        }

        finalScale = AffineMatrix.scaleMatrix(scaleVector).multiply(finalScale);
        return this;
    }

    @Override
    public AffineBuilder scaleByX(double num) {
        return scale(new Vector3D(num, 0, 0));
    }

    @Override
    public AffineBuilder scaleByY(double num) {
        return scale(new Vector3D(0, num, 0));
    }

    @Override
    public AffineBuilder scaleByZ(double num) {
        return scale(new Vector3D(0, 0, num));
    }

    @Override
    public AffineBuilder rotateInRadians(AxisEnum axis, double angle) {
        if (finalRotate == null) {
            finalRotate = AffineMatrix.rotateMatrix(axis.getaNum(), angle);
            return this;
        }

        finalRotate = AffineMatrix.rotateMatrix(axis.getaNum(), angle).multiply(finalRotate);
        return this;
    }

    @Override
    public AffineBuilder rotateInDegrees(AxisEnum axis, double angle) {
        rotateInRadians(axis, Math.toRadians(angle));
        return this;
    }
    @Override
    public AffineBuilder translate(Vector3D translateVector) {
        if (finalTranslate == null) {
            finalTranslate = AffineMatrix.translateMatrix(translateVector);
            return this;
        }

        finalTranslate = AffineMatrix.translateMatrix(translateVector).add(finalTranslate);
        return this;
    }

    @Override
    public AffineBuilder moveByX(double num) {
        return translate(new Vector3D(num, 0, 0));
    }

    @Override
    public AffineBuilder moveByY(double num) {
        return translate(new Vector3D(0, num, 0));
    }

    @Override
    public AffineBuilder moveByZ(double num) {
        return translate(new Vector3D(0, 0, num));
    }

    public Model returnChangedModel(Model model1) {
        if (model1 == null) {
            throw new NullPointerException("Model is null");
        }

        Model model = new Model(new ArrayList<>(model1.vertices), new ArrayList<>(model1.textureVertices),
                new ArrayList<>(model1.normals), new ArrayList<>(model1.polygons));

        model.vertices.replaceAll(this::returnVector3D);
        model.normals.replaceAll(this::returnVector3D);

        return model;
    }

    private Vector3D returnVector3D(Vector3D v) {
        Vector4D vector = new Vector4D(v);
        vector = returnModelMatrix().multiply(vector);

        return new Vector3D(vector.get(0), vector.get(1), vector.get(2));
    }

    public Matrix4x4 returnModelMatrix () {
        Matrix4x4 finalMatrix = null;
        boolean full = false;
        if (finalScale != null) {
            finalMatrix = finalScale;
            full = true;
        }
        if (finalRotate != null) {
            if (!full) {
                finalMatrix = finalRotate;
                full = true;
            } else {
                finalMatrix = finalMatrix.multiply(finalRotate);
            }
        }
        if (finalTranslate != null) {
            if (!full) {
                finalMatrix = finalTranslate;
            } else {
                finalMatrix = finalMatrix.multiply(finalTranslate);
            }
        }

        return finalMatrix;
    }

}
