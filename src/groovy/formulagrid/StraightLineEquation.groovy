package formulagrid

class StraightLineEquation {
    // for equation of the form x = N
    private Integer xN

    // For equation of the form y = a * x + b
    private Float a
    private Float b

    public StraightLineEquation(Point p1, Point p2) {
        if(p1 == p2) {
            throw new IllegalArgumentException("Two distinct points are required.")
        }

        if (p1.x == p2.x) {
            xN = p1.x
        }
        else {
            a = (p1.y - p2.y).toFloat() / (p1.x - p2.x).toFloat()
            b = p1.y - a * p1.x
        }
    }

    Point getPointFromX(int x) {
        if(xN != null) {
            throw new IllegalStateException("Cannot invoke getPointFromX : Equation is of the form 'x = N.")
        }

        new Point(x, Math.round(a*x+b).toInteger())
    }

    Point getPointFromY(int y) {
        if(xN != null) {
            return new Point(xN, y)
        }

        new Point(Math.round((y-b)/a).toInteger(), y)
    }
}
