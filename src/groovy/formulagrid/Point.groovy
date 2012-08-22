package formulagrid

class Point {
    int x
    int y

    def Point() {
        x = 0
        y = 0
    }

    def Point(Integer x, Integer y) {
        this.x = x
        this.y = y
    }

    Point plus(Point p2) {
        return new Point(x+p2.x, y+p2.y)
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Point point = (Point) o

        if (x != point.x) return false
        if (y != point.y) return false

        return true
    }

    int hashCode() {
        int result
        result = x
        result = 31 * result + y
        return result
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
