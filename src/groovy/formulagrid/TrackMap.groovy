package formulagrid

class TrackMap {
    TrackPoint[][] bitmap

    TrackMap() {}

    TrackMap(TrackPoint[][] bitmap) {
        this.bitmap = bitmap
    }

    // TODO overload [] operator
    TrackPoint getTrackPoint(Point point) {
        checkArgumentIsNotNull('point', point)
        return bitmap[point.x][point.y]
    }

    void moveCarTo(Car car, Point position) {
        if(car.position) {
            getTrackPoint(car.position).car = null
        }

        getTrackPoint(position).car = car
        car.setPosition(position)
    }

    // TODO Mettre cette méthode en commun
    private void checkArgumentIsNotNull(String argName, def arg) {
        if (arg == null) {
            throw new IllegalArgumentException("Argument '$argName' must be not null.")
        }
    }
}
