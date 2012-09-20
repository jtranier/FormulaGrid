package formulagrid

class Race {
    Track track

    List<Player> allPlayer = []
    boolean started = false

    int numTurn
    int currentPlayerNum

    TrajectoryRepresenter trajectoryRepresenter = new TrajectoryRepresenter()

    Race(Track track) {
        this.track = track
    }

    int getNbPlayer() {
        return allPlayer.size()
    }

    Player getPlayer(int num) {
        if (num > allPlayer.size()) {
            return null
        }

        return allPlayer[num - 1]
    }

    Player getCurrentPlayer() {
        return allPlayer[currentPlayerNum - 1]
    }

    void addPlayer(String name) {
        Car car = new Car(num: allPlayer.size() + 1)
        Player player = new Player(name: name, car: car)
        allPlayer << player
        track.addCar(car)
    }

    void start() {
        if (allPlayer.isEmpty()) {
            throw new IllegalStateException("There is no player.")
        }

        started = true
        currentPlayerNum = 1
        numTurn = 1
    }

    void play(Point deltaSpeed) {
        checkIsStarted()
        "bl"
        Player player = allPlayer[currentPlayerNum - 1]
        player.car.accelerate(deltaSpeed)
        track.playCar(player.car)

        currentPlayerNum = getNextPlayerNum()
        numTurn++
    }

    private int getNextPlayerNum() {
        1 + (currentPlayerNum % allPlayer.size())
    }

    void checkIsStarted() {
        if (!started) {
            throw new IllegalStateException("Race is not started.")
        }
    }

    Character[][] getAsciiRepresentation() {
        Character[][] representation = track.map.asciiRepresentation

        allPlayer.each {Player player ->
            trajectoryRepresenter.addTrajectoryToMap(player, representation)
        }

        return representation
    }

    static final Closure MARSHALLER = { Race race ->
        Map result = [
                track: [
                        width: race.track.width,
                        height: race.track.height,
                        allObstacleAsList: race.track.map.allObstacleAsList
                ],
                currentCarNum: race.currentPlayerNum,
                allCar: race.allPlayer.collect { Player player ->
                    player.car
                }
        ]

        return result
    }
}
