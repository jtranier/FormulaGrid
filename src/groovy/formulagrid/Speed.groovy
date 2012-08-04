package formulagrid

class Speed {
    int x
    int y

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Speed speed = (Speed) o

        if (x != speed.x) return false
        if (y != speed.y) return false

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
        return "Speed{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
