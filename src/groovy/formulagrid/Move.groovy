package formulagrid

class Move {
    Point from
    Point to

    int getDeltaX() {
        return Math.abs(to.x - from.x)
    }

    int getDeltaY() {
        return Math.abs(to.y - from.y)
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Move move = (Move) o

        if (from != move.from) return false
        if (to != move.to) return false

        return true
    }

    int hashCode() {
        int result
        result = (from != null ? from.hashCode() : 0)
        result = 31 * result + (to != null ? to.hashCode() : 0)
        return result
    }
}
