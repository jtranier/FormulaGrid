package formulagrid

class TrajectoryRepresenter {

    void addTrajectoryToMap(Player player, Character[][] representation) {

        player.car.trajectory.eachWithIndex { Path path, int i ->
            representation[path.from.x][path.from.y] = '*'
            path.allIntermediaryPoint.each { Point p ->
                representation[p.x][p.y] = path.asciiRepresentation
            }
        }

        representation[player.car.position.x][player.car.position.y] = "${player.car.num}"
    }
}
