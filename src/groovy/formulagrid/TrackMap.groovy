package formulagrid

class TrackMap {

    TrackPoint[][] bitmap

    TrackMap() {}

    int getHeight() {
        return bitmap[0].length
    }

    int getWidth() {
        return bitmap.length
    }

    TrackMap(TrackPoint[][] bitmap) {
        this.bitmap = bitmap
    }

    // TODO overload [] operator
    TrackPoint getTrackPoint(Point point) {
        checkArgumentIsNotNull('point', point)
        return bitmap[point.x][point.y]
    }

    void moveCarTo(Car car, Point position) {
        if (car.position) {
            getTrackPoint(car.position).car = null
        }

        getTrackPoint(position).car = car
        car.setPosition(position)
    }

    void moveCarTo(Car car, Path path) {
        if (car.position) {
            getTrackPoint(car.position).car = null
        }

        getTrackPoint(path.to).car = car
        car.followPath(path)
    }

    Character[][] getAsciiRepresentation() {
        Character[][] representation = new char[width][height]

        (0..width-1).each { int x ->
            (0..height-1).each { int y ->
                representation[x][y] = bitmap[x][y].asciiRepresentation
            }
        }

        return representation
    }

    // TODO Mettre cette méthode en commun
    private void checkArgumentIsNotNull(String argName, def arg) {
        if (arg == null) {
            throw new IllegalArgumentException("Argument '$argName' must be not null.")
        }
    }
}
