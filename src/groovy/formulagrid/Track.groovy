package formulagrid

class Track {

    int width
    int height
    TrackMap map
    TrackStartGrid startGrid

    private List<Car> allCar = []

    PathFinder pathFinder = new PathFinder()
    MoveStrategy moveStrategy = new DefaultMoveStrategy()

    void addCar(Car car) { // TODO: ajouter en paramètre la position sur la grille
        checkArgumentIsNotNull('car', car)
        checkStartGridIsDefined()
        checkStartGridIsNotFull()

        allCar << car
        Point position = startGrid.getStartPositionPoint(allCar.size())
        map.moveCarTo(car, position)
    }

    void playCar(Car car) {
        checkArgumentIsNotNull('car', car)
        List<Point> path = pathFinder.findPath(car.intendedMove())
        MoveResult moveResult = moveStrategy.calculateMoveResult(this, path)
        map.moveCarTo(car, moveResult.finalPosition)
        if(moveResult.crash) {
            applyCrashEffect(car)
        }
    }

    private void applyCrashEffect(Car car) {
        car.speed = new Point(x: 0, y: 0)
    }

    private void checkArgumentIsNotNull(String argName, def arg) {
        if (arg == null) {
            throw new IllegalArgumentException("Argument '$argName' must be not null.")
        }
    }

    private void checkStartGridIsDefined() {
        if (!startGrid) {
            throw new IllegalStateException("Impossible to add a car to this track because there is no start grid.")
        }
    }

    private void checkStartGridIsNotFull() {
        if (allCar.size() == startGrid.nbPosition) {
            throw new IllegalStateException("Impossible to add a car to this track because the start grid is full.")
        }
    }
}
