package formulagrid

class TrackPoint {
    boolean obstacle = false
    boolean finishLine = false
    Car car = null
    boolean startPosition = false
    int startPositionNum

    boolean isFree() {
        return !obstacle && !car
    }

    char getAsciiRepresentation() {
        if(obstacle) {
            return '■'
        }

        if(car) {
            return car.num as String
        }

        if(finishLine) {
            return'='
        }

        if(startPosition) {
            return '^'
        }

        return '·'
    }

    static TrackPoint parseFromCharacter(char c) {
        switch(c) { // TODO constants
            case '*' as char: return new TrackPoint(obstacle: true)
            case '.' as char: return new TrackPoint(obstacle: false)
            case '=' as char: return new TrackPoint(obstacle: false, finishLine: true)
            case '1' as char:
            case '2' as char:
            case '3' as char:
            case '4' as char:
            case '5' as char:
            case '6' as char:
                return new TrackPoint(
                        obstacle: false,
                        startPosition: true,
                        startPositionNum: Integer.parseInt(c as String)
                )

            default: throw new IllegalArgumentException("'$c' is not a valid character.")
        }
    }
}
