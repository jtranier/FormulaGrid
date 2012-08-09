package formulagrid

import java.awt.Point

class TrackStartGrid {

    private Point[] allPosition = new Point[6]

    void addStartPosition(int num, int x, int y) {
        checkNum(num)

        if(allPosition[normalizeNum(num)]) {
            throw new IllegalStateException("Start position $num is already defined.")
        }

        checkFreeLocation(x, y)

        allPosition[normalizeNum(num)] = new Point(x:  x, y:  y)
    }

    private void checkFreeLocation(int x, int y) {
        allPosition.eachWithIndex { Point point, int num ->
            if (point == new Point(x: x, y: y)) {
                throw new IllegalStateException(
                        "Start position ${denormalizeNum(num)} occupies location ($x,$y)."
                )
            }
        }
    }

    Point getStartPositionPoint(int num) {
        checkNum(num)
        return allPosition[normalizeNum(num)]
    }

    void validate() {

        // Check first start position is well defined
        if(!allPosition[normalizeNum(1)]) {
            throw new IllegalStateException("Start position 1 is undefined.")
        }

        // Check that if start position N is defined, then N-1 is defined
        for(int i in 2..6) {
            if(allPosition[normalizeNum(i)] && !allPosition[normalizeNum(i-1)]) {
                throw new IllegalStateException(
                        "Start position $i is defined wheras start position ${i-1} is undefined."
                )
            }
        }
    }

    private int normalizeNum(int num) {
        return num-1
    }

    private int denormalizeNum(int num) {
        return num+1
    }

    private void checkNum(int num) {
        if (!(num in 1..6)) {
            throw new IllegalArgumentException("Argument 'num' must be between 1 and 6 ; given $num.")
        }
    }
}