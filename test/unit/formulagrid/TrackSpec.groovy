package formulagrid

import spock.lang.Specification

class TrackSpec extends Specification {
    Track track = new Track()

    def "addCar - with car argument null"() {
        when:
        track.addCar(null)

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "Argument 'car' must be not null."
    }

    def "addCar - without start grid"() {
        setup:
        track.startGrid = null
        Car car = Mock(Car)

        when:
        track.addCar(car)

        then:
        def e = thrown(IllegalStateException)
        e.message == "Impossible to add a car to this track because there is no start grid."
    }

    def "addCar - 2 valid + 1 with start grid full"() {
        setup:
        TrackStartGrid startGrid = Mock(TrackStartGrid)
        startGrid.nbPosition >> 2

        track.startGrid = startGrid
        track.map = createBitmapTest(2, 2)

        when:
        track.addCar(car1)

        then:
        1 * startGrid.getStartPositionPoint(1) >> new Point(0, 0)
        notThrown(Exception)
        track.map.bitmap[0][0].car == car1
        car1.position == new Point(0, 0)

        and:
        when:
        track.addCar(car2)

        then:
        1 * startGrid.getStartPositionPoint(2) >> new Point(1, 1)
        notThrown(Exception)
        track.map.bitmap[1][1].car == car2
        car2.position == new Point(1, 1)

        and:
        when:
        track.addCar(car3)

        then:
        def e = thrown(IllegalStateException)
        e.message == "Impossible to add a car to this track because the start grid is full."

        where:
        car1 = new Car()
        car2 = new Car()
        car3 = new Car()
    }

    def "playCar - valid, without crash"() {
        setup:
        PathFinder pathFinder = Mock(PathFinder)
        track.pathFinder = pathFinder

        MoveStrategy moveStrategy = Mock(DefaultMoveStrategy)
        track.moveStrategy = moveStrategy

        TrackMap map = Mock(TrackMap)
        track.map = map

        when:
        track.playCar(car)

        then:
        1 * pathFinder.findPath(car.intendedMove()) >> intendedPath
        1 * moveStrategy.calculatePathAccordingToMap(track, intendedPath) >> effectivePath
        1 * map.moveCarTo(car, effectivePath)

        if(crash) {
            car.speed == new Point(0, 0)
        }
        else {
            car.speed == speed
        }

        where:
        finalPosition = new Point(1, 1)
        intendedPath = new Path(to:  finalPosition)
        crash = [false, true]
        effectivePath = new Path(to: finalPosition, crash: crash)
        car = new Car(position: new Point(0, 0))
        speed = new Point(1, 1)
    }

    TrackMap createBitmapTest(int width, int height) {
        TrackPoint[][] bitmap = new TrackPoint[width][height]
        width.times { int x ->
            height.times { int y ->
                bitmap[x][y] = new TrackPoint()
            }
        }

        return new TrackMap(bitmap)
    }
}
