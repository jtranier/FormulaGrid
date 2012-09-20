var Race08 = function (raceModel, containerId) {
    var config = {
        palette:[ "red", "blue"],
        car:{
            size:0.5
        },
        trajectory:{
            pointSize:0.25
        },
        possibleMove:{
            normalSize:0.25,
            selectedSize:0.3,
            mouseZone:{
                size:0.9
            },
            opacity:0.5
        },
        svg:{
            padding:10,
            strokeWidth:5,
            rounded:10
        }
    };

    // TODO how to describe the model ?
    var model = {
        race:raceModel
    };

    var svg = undefined;
    var xScale = undefined;
    var yScale = undefined;
    var xMax = undefined;
    var yMax = undefined;
    var lineFn = undefined;

    constructor();

    function constructor() {
        initSvg(containerId);
        drawTrackBox("white");
        drawTrackGrid();
        drawAllObstacle(model.race.track.allObstacleAsList);
        drawTrackBox("none");

        model.race.allCar.forEach(function (car) {
            drawTrajectory(car);
            drawAllTrajectoryPoint(car);
            drawCar(car);
        });

        drawAllPossibleMove(getCurrentCar());
    }

    function getCurrentCar() {
        return model.race.allCar[model.race.currentCarNum - 1];
    }

    function initSvg(containerId) {
        xMax = model.race.track.width;
        yMax = model.race.track.height;
        var width = d3.select("#" + containerId).property("clientWidth");
        var height = Math.round(width * (yMax / xMax));

        xScale = d3.scale.linear()
            .domain([0, xMax])
            .range(["0", (width - 2 * config.svg.padding)]);

        yScale = d3.scale.linear()
            .domain([0, yMax])
            .range(["0", (height - 2 * config.svg.padding)]);

        lineFn = d3.svg.line()
            .x(function (d) {
                return xScale(d.x);
            })
            .y(function (d) {
                return yScale(d.y);
            })
            .interpolate("cardinal");

        svg = d3.select("#" + containerId).
            append("svg")
            .attr("width", width)
            .attr("height", height)
            .append("g")
            .attr("transform", "translate(" + config.svg.padding + "," + config.svg.padding + ")");
    }

    function drawTrackBox(fill) {
        svg.append("rect")
            .attr("class", "track")
            .attr("x", xScale(-.5))
            .attr("y", yScale(-.5))
            .attr("stroke", "darkslategrey")
            .attr("width", xScale(xMax + 1))
            .attr("height", yScale(yMax + 1))
            .attr("stroke-width", config.svg.strokeWidth)
            .attr("fill", fill)
            .attr("rx", config.svg.rounded)
            .attr("ry", config.svg.rounded)
    }

    function drawTrackGrid() {
        svg.selectAll("#" + containerId + " line.vertical")
            .data(d3.range(xMax - 1))
            .enter()
            .append("line")
            .attr("class", "track-line vertical")
            .attr("x1", function (d) {
                return xScale(d + 1);
            })
            .attr("y1", yScale(0))
            .attr("x2", function (d) {
                return xScale(d + 1);
            })
            .attr("y2", yScale(yMax))
            .attr("stoke-width", "1px")
            .attr("stroke", "darkgrey");


        svg.selectAll("#" + containerId + " line.horizontal")
            .data(d3.range(yMax - 1))
            .enter()
            .append("line")
            .attr("class", "track-line horizontal")
            .attr("x1", xScale(0))
            .attr("y1", function (d) {
                return yScale(d + 1);
            })
            .attr("x2", xScale(xMax))
            .attr("y2", function (d) {
                return yScale(d + 1);
            })
            .attr("stoke-width", "1px")
            .attr("stroke", "darkgrey");
    }

    function drawAllObstacle(allObstacle) {
        svg.selectAll("#" + containerId + " rect.obstacle")
            .data(allObstacle)
            .enter()
            .append("rect")
            .attr("x", function (d, i) {
                return xScale((i % xMax));
            })
            .attr("y", function (d, i) {
                return yScale(Math.floor(i / xMax));
            })
            .attr("fill", function (d) {
                if (d == 1) {
                    return "saddlebrown";
                } else {
                    return "none";
                }
            })
            .attr("opacity", .5)
            .attr("width", xScale(1))
            .attr("height", yScale(1));
    }

    function drawCar(car) {
        var carRepresentation = drawCircle(
            car.position.x,
            car.position.y,
            config.car.size,
            getPaletteColor(car.num)
        );

        carRepresentation.attr('id', getCarId(car));
    }

    function moveCarRepresentation(car) {
        var carRepresentation = d3.select("#" + containerId + ' #' + getCarId(car));
        carRepresentation
            .transition()
            .duration(500)
            .attr('cx', xScale(car.position.x))
            .attr('cy', yScale(car.position.y));
    }

    function getCarId(car) {
        return 'car-' + car.num;
    }

    function drawAllTrajectoryPoint(car) {
        car.trajectory.forEach(function (point) {
            drawTrajectoryPoint(car, point);
        });
    }

    function drawTrajectoryPoint(car, point) {
        drawCircle(point.x, point.y, config.trajectory.pointSize, getPaletteColor(car.num));
    }

    function drawCircle(px, py, r, color) {
        return svg.append("circle")
            .attr("class", "car")
            .attr("cx", xScale(px))
            .attr("cy", yScale(py))
            .attr("r", xScale(r))
            .attr("stroke", "black")
            .attr("fill", color)
    }

    function getTrajectoryId(carNum) {
        return "trajectory-" + carNum;
    }

    function getPaletteColor(carNum) {
        return config.palette[carNum - 1];
    }

    function drawTrajectory(car) {
        svg.append("svg:path")
            .attr("id", getTrajectoryId(car.num))
            .attr("d", lineFn(car.trajectory))
            .style("stroke-width", 2)
            .style("stroke", getPaletteColor(car.num))
            .style("fill", "none");
    }

    function updateTrajectory(car) {
        d3.select("#" + containerId + ' #' + getTrajectoryId(car.num))
            .attr("d", lineFn(car.trajectory));
    }

    function getPossibleMoveId(accelerationCode) {
        return "possible-move-" + accelerationCode;
    }

    function drawAllPossibleMove(car) {
        return; // do nothing for the demo
    }

    function drawPossibleMove(car, px, py, accelerationCode) {

        var possibleMoveRepresentation = svg.append("circle")
            .attr("id", getPossibleMoveId(accelerationCode))
            .attr("class", "possible-move")
            .attr("cx", xScale(px))
            .attr("cy", yScale(py))
            .attr("r", xScale(config.trajectory.pointSize))
            .attr("stroke", "black")
            .attr("fill", getPaletteColor(car.num))
            .attr("opacity", config.possibleMove.opacity);

        svg.append("rect")
            .attr("class", "possible-move-mousezone")
            .attr("x", xScale(px - (config.possibleMove.mouseZone.size / 2)))
            .attr("y", yScale(py - (config.possibleMove.mouseZone.size / 2)))
            .attr("height", yScale(config.possibleMove.mouseZone.size))
            .attr("width", xScale(config.possibleMove.mouseZone.size))
            .attr("stroke", "transparent")
            .attr("fill", "transparent")
            .on("click", function () {
                play(accelerationCode);
            })
            .on("mouseover", function () {
                selectPossibleMove(car, possibleMoveRepresentation);
            })
            .on("mouseout", function () {
                unselectPossibleMove(car, possibleMoveRepresentation);
            });
    }

    function play(accelerationCode) {
        removeAllPossibleMove();

        jQuery.post(
            urlJsonMakeAMove,
            {accelerationCode:accelerationCode},
            function (makeAMoveModel) {
                moveCar(makeAMoveModel.movingCar, makeAMoveModel.nextCarNum);
            }
        )
    }

    function selectPossibleMove(car, possibleMoveRepresentation) {
        possibleMoveRepresentation
            .attr("opacity", 1)
            .attr("r", xScale(config.possibleMove.selectedSize));

        car.trajectory.push({x:px, y:py});
        d3.select("#" + containerId + ' #' + getTrajectoryId(car.num))
            .attr("d", lineFn(car.trajectory));
    }

    function unselectPossibleMove(car, possibleMoveRepresentation) {
        possibleMoveRepresentation
            .attr("opacity", config.possibleMove.opacity)
            .attr("r", xScale(config.possibleMove.normalSize));

        car.trajectory.pop();
        d3.select("#" + containerId + ' #' + getTrajectoryId(car.num))
            .attr("d", lineFn(car.trajectory));
    }

    function removeAllPossibleMove() {
        d3.selectAll("#" + containerId + " .possible-move-mousezone").on("mouseover", null);
        d3.selectAll("#" + containerId + " .possible-move-mousezone").on("mouseout", null);
        d3.selectAll("#" + containerId + " .possible-move").remove();
        d3.selectAll("#" + containerId + " .possible-move-mousezone").remove();
    }

    this.moveCar = function (movingCar, nextCarNum) {
        model.race.allCar[movingCar.num - 1] = movingCar;
        model.race.currentCarNum = nextCarNum;

        drawTrajectoryPoint(
            movingCar,
            movingCar.trajectory[movingCar.trajectory.length - 2]
        );

        moveCarRepresentation(movingCar);
        updateTrajectory(movingCar);
        drawAllPossibleMove(getCurrentCar());
    };

    this.reset = function(car) {
        d3.selectAll("#" + containerId + ' #'+getTrajectoryId(car.num)).remove();
        d3.selectAll("#" + containerId + ' circle').remove();
        drawCar(car);
        drawTrajectory(car);
    };
};

