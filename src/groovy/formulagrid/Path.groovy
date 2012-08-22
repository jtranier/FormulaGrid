package formulagrid

class Path {
    Point from
    Point to
    List<Point> allIntermediaryPoint // List of points between from and to
    Boolean crash

    Character getAsciiRepresentation() {
        if(from.x == to.x) {
            return '|'
        }
        if(from.y == to.y) {
            return '-'
        }
        if((from.x - to.x) * (from.y - to.y) > 0) {
            return '\\'
        }
        else {
            return '/'
        }
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Path path = (Path) o

        if (allIntermediaryPoint != path.allIntermediaryPoint) return false
        if (crash != path.crash) return false
        if (from != path.from) return false
        if (to != path.to) return false

        return true
    }

    int hashCode() {
        int result
        result = (from != null ? from.hashCode() : 0)
        result = 31 * result + (to != null ? to.hashCode() : 0)
        result = 31 * result + (allIntermediaryPoint != null ? allIntermediaryPoint.hashCode() : 0)
        result = 31 * result + (crash != null ? crash.hashCode() : 0)
        return result
    }
}
