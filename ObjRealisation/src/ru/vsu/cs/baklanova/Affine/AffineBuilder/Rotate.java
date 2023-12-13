package ru.vsu.cs.baklanova.Affine.AffineBuilder;


import ru.vsu.cs.baklanova.Affine.AffineMatrix;
import ru.vsu.cs.baklanova.Affine.AxisEnum;
import ru.vsu.cs.baklanova.math.vector.Vector3D;

public class Rotate {
    AffineBuilder builder;
    public Rotate(AffineBuilder builder) {
        this.builder = builder;
    }

    public Rotate byAxisInRadians(AxisEnum axisEnum, double angle) throws Exception {
        builder.addRotate(AffineMatrix.rotateMatrix(axisEnum.getaNum(), angle));

        return this;
    }

    public Rotate byAxisInDegrees(AxisEnum axisEnum, double angle) throws Exception {
        return byAxisInRadians(axisEnum, Math.toRadians(angle));
    }

    private void rotate(AxisEnum[] enums, Vector3D angles) throws Exception {
        if (angles == null) {
            throw new Exception("Angles vector is null");
        }
        for (AxisEnum e : enums) {
            switch (e) {
                case X -> byX(angles.get(0));
                case Y -> byY(angles.get(1));
                case Z -> byZ(angles.get(2));
            }
        }
    }

    public Rotate byX(double angle) throws Exception {
        byAxisInDegrees(AxisEnum.X, angle);
        return this;
    }

    public Rotate byY(double angle) throws Exception {
        byAxisInDegrees(AxisEnum.Y, angle);
        return this;
    }

    public Rotate byZ(double angle) throws Exception {
        byAxisInDegrees(AxisEnum.Z, angle);
        return this;
    }

    public Rotate XYZ(Vector3D angle) throws Exception {
        AxisEnum[] enums = new AxisEnum[] {AxisEnum.X, AxisEnum.Y, AxisEnum.Z};
        rotate(enums, angle);
        return this;
    }

    public Rotate XZY(Vector3D angle) throws Exception {
        AxisEnum[] enums = new AxisEnum[] {AxisEnum.X, AxisEnum.Z, AxisEnum.Y};
        rotate(enums, angle);
        return this;
    }

    public Rotate YXZ(Vector3D angle) throws Exception {
        AxisEnum[] enums = new AxisEnum[] {AxisEnum.Y, AxisEnum.X, AxisEnum.Z};
        rotate(enums, angle);
        return this;
    }
    public Rotate YZX(Vector3D angle) throws Exception {
        AxisEnum[] enums = new AxisEnum[] {AxisEnum.Y, AxisEnum.Z, AxisEnum.X};
        rotate(enums, angle);
        return this;
    }

    public Rotate ZXY(Vector3D angle) throws Exception {
        AxisEnum[] enums = new AxisEnum[] {AxisEnum.Z, AxisEnum.X, AxisEnum.Y};
        rotate(enums, angle);
        return this;
    }

    public Rotate ZYX(Vector3D angle) throws Exception {
        AxisEnum[] enums = new AxisEnum[] {AxisEnum.Z, AxisEnum.Y, AxisEnum.X};
        rotate(enums, angle);
        return this;
    }

    public AffineBuilder close() {
        return builder;
    }

}
