package com.app.rps.shape;

public final class ShapeSupplier {
    public static final String TYPE_RANDOM_SHAPE = "rps.shape.random";
    public static final String TYPE_SHAPE_1_WINS = "rps.shape.1wins";
    public static final String TYPE_SHAPE_2_WINS = "rps.shape.2wins";

    private Shape mPreviousShape;

    public ShapesPair generatePair(String pairType) {
        Shape[] containers;
        switch (pairType) {
            case TYPE_RANDOM_SHAPE:
                containers = randomPair();
                break;
            case TYPE_SHAPE_1_WINS:
                containers = shape1wins();
                break;
            case TYPE_SHAPE_2_WINS:
                containers = shape2wins();
                break;
            default:
                containers = new Shape[]{Shape.NONE, Shape.NONE};
                break;
        }
        int score1 = containers[0].worse() == containers[1] ? 1 : 0;
        int score2 = containers[0] == containers[1].worse() ? 1 : 0;
        return new ShapesPair(containers[0], containers[1], score1, score2);
    }

    /**
     * Generates a random pair of resource containers.
     * @return Two shapes packed in a {@link Shape} array.
     */
    private Shape[] randomPair() {
        Shape container1 = Shape.randomShapeExcept(mPreviousShape);
        mPreviousShape = container1;
        Shape container2 = Shape.randomShapeExcept(container1);
        return new Shape[]{container1, container2};
    }

    /**
     * Returns a unequalPair of two shapes.
     * The first one is the winning shape and the second is the losing shape.
     * @return Two shapes packed in a {@link Shape} array.
     */
    private Shape[] shape1wins() {
        Shape winningShape = Shape.randomShapeExcept(mPreviousShape);
        mPreviousShape = winningShape;
        Shape losingShape = winningShape.worse();
        return new Shape[]{winningShape, losingShape};
    }

    /**
     * Returns a unequalPair of two shapes.
     * The second one is the winning shape and the first is the losing shape.
     * @return Two shapes packed in a {@link Shape} array.
     */
    private Shape[] shape2wins() {
        Shape winningShape = Shape.randomShapeExcept(mPreviousShape);
        mPreviousShape = winningShape;
        Shape losingShape = winningShape.worse();
        return new Shape[]{losingShape, winningShape};
    }
}
