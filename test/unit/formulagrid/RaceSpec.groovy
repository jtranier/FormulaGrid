package formulagrid

import spock.lang.Specification

class RaceSpec extends Specification{

    def "constructor"() {
        setup:
        Track track = Mock(Track)

        when:
        Race race = new Race(track)

        then:
        race.track == track
        !race.started
        race.nbPlayer == 0
    }

    def "getPlayer - without player"() {
        setup:
        Track track = Mock(Track)
        Race race = new Race(track)

        expect:
        race.getPlayer(num) == null

        where:
        num << (1..6).collect { it }
    }

    def "addPlayer - valid according to track nb player limit"() {
        setup:
        Track track = Mock(Track)
        Race race = new Race(track)

        when:
        race.addPlayer(name1)

        then:
        race.getPlayer(1)?.name == name1
        1 * track.addCar({Car car -> car.num == 1})

        and:
        when:
        race.addPlayer(name2)

        then:
        race.getPlayer(1)?.name == name1
        race.getPlayer(2)?.name == name2
        1 * track.addCar({Car car -> car.num == 2})

        where:
        name1 = "name1"
        name2 = "name2"
    }

    def "start - without player"() {
        setup:
        Track track = Mock(Track)
        Race race = new Race(track)

        when:
        race.start()

        then:
        def e = thrown(IllegalStateException)
        e.message == "There is no player."
    }

    def "start - with player"() {
        setup:
        Track track = Mock(Track)
        track.addCar(_)
        Race race = new Race(track)
        race.addPlayer("name")

        when:
        race.start()

        then:
        race.started
        race.currentPlayerNum == 1
        race.numTurn == 1
    }

    def "play - race not started"(Point deltaSpeed) {
        setup:
        Track track = Mock(Track)
        Race race = new Race(track)

        when:
        race.play(deltaSpeed)

        then:
        def e = thrown(IllegalStateException)
        e.message == "Race is not started."

        where:
        deltaSpeed = new Point()
    }

    def "play - race started"() {
        setup:
        Track track = Mock(Track)
        Race race = new Race(track)
        race.addPlayer(name1)
        race.addPlayer(name2)
        race.start()

        // Hook cars
        Car car1 = Mock(Car)
        Car car2 = Mock(Car)
        race.getPlayer(1).car = car1
        race.getPlayer(2).car = car2

        when:
        race.play(deltaSpeed1)

        then:
        1 * car1.accelerate(deltaSpeed1)

        then:
        1 * track.playCar(car1)
        race.numTurn == 2
        race.currentPlayerNum == 2

        and:
        when:
        race.play(deltaSpeed2)

        then:
        1 * car2.accelerate(deltaSpeed2)

        then:
        1 * track.playCar(car2)
        race.numTurn == 3
        race.currentPlayerNum == 1

        and:
        when:
        race.play(deltaSpeed3)

        then:
        1 * car1.accelerate(deltaSpeed3)

        then:
        1 * track.playCar(car1)
        race.numTurn == 4
        race.currentPlayerNum == 2

        where:
        name1 = "name1"
        name2 = "name2"
        deltaSpeed1 = new Point(1, 1)
        deltaSpeed2 = new Point(-1, -1)
        deltaSpeed3 = new Point(0, 1)
    }

    def "getAsciiRepresentation"() {
        setup:
        TrackMap map = Mock(TrackMap)
        map.getAsciiRepresentation() >> mapRepresentation

        Track track = Mock(Track)
        track.getMap() >> map

        TrajectoryRepresenter trajectoryRepresenter = Mock(TrajectoryRepresenter)

        Race race = new Race(track)
        race.trajectoryRepresenter = trajectoryRepresenter
        race.addPlayer('player1')
        race.addPlayer('player2')

        when:
        race.asciiRepresentation

        then:
        1 * trajectoryRepresenter.addTrajectoryToMap(race.getPlayer(1), mapRepresentation)
        1 * trajectoryRepresenter.addTrajectoryToMap(race.getPlayer(2), mapRepresentation)

        where:
        mapRepresentation = new Character[3][3]
    }
}
