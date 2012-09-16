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
        opacity: 0.5
    },
    svg: {
        padding: 10,
        strokeWidth: 5,
        rounded: 10
    }
};

var model = {
    race: undefined
};

var application = {
    svg: undefined,
    xScale: undefined,
    yScale: undefined,
    xMax: undefined,
    yMax: undefined
};


// TODO how to describe the model ?
function createRaceApplication(race, containerId) {
    model.race = race;

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

    var currentCar = model.race.allCar[model.race.currentCarNum - 1];
    [-1, 0, 1].forEach(function (i) {
        [-1, 0, 1].forEach(function (j) {
            drawPossibleMove(
                currentCar,
                currentCar.position.x + currentCar.speed.x + i,
                currentCar.position.y + currentCar.speed.y + j,
                (i + 2) + 3 * (1 - j) // Calcul (abscon) du code d'accélération
            );
        });
    });
}

function initSvg(containerId) {
    application.xMax = model.race.track.width;
    application.yMax = model.race.track.height;
    var width = d3.select("#"+containerId).property("clientWidth");
    var height = Math.round(width * (application.yMax / application.xMax));

    application.xScale = d3.scale.linear()
        .domain([0, application.xMax])
        .range(["0", (width - 2 * config.svg.padding)]);

    application.yScale = d3.scale.linear()
        .domain([0, application.yMax])
        .range(["0", (height - 2 * config.svg.padding)]);

    application.svg = d3.select("#svg").
        append("svg")
        .attr("width", width)
        .attr("height", height)
        .append("g")
        .attr("transform", "translate(" + config.svg.padding + "," + config.svg.padding + ")");
}

var d3line2 = d3.svg.line()
    .x(function (d) {
        return application.xScale(d.x);
    })
    .y(function (d) {
        return application.yScale(d.y);
    })
    .interpolate("cardinal");

function drawTrackBox(fill) {
    application.svg.append("rect")
        .attr("class", "track")
        .attr("x", application.xScale(-.5))
        .attr("y", application.yScale(-.5))
        .attr("stroke", "darkslategrey")
        .attr("width", application.xScale(application.xMax + 1))
        .attr("height", application.yScale(application.yMax + 1))
        .attr("stroke-width", config.svg.strokeWidth)
        .attr("fill", fill)
        .attr("rx", config.svg.rounded)
        .attr("ry", config.svg.rounded)
}

function drawTrackGrid() {
    application.svg.selectAll("line.vertical")
        .data(d3.range(application.xMax - 1))
        .enter()
        .append("line")
        .attr("class", "track-line vertical")
        .attr("x1", function (d) {
            return application.xScale(d + 1);
        })
        .attr("y1", application.yScale(0))
        .attr("x2", function (d) {
            return application.xScale(d + 1);
        })
        .attr("y2", application.yScale(application.yMax))
        .attr("stoke-width", "1px")
        .attr("stroke", "darkgrey");


    application.svg.selectAll("line.horizontal")
        .data(d3.range(application.yMax - 1))
        .enter()
        .append("line")
        .attr("class", "track-line horizontal")
        .attr("x1", application.xScale(0))
        .attr("y1", function (d) {
            return application.yScale(d + 1);
        })
        .attr("x2", application.xScale(application.xMax))
        .attr("y2", function (d) {
            return application.yScale(d + 1);
        })
        .attr("stoke-width", "1px")
        .attr("stroke", "darkgrey");
}

function drawAllObstacle(allObstacle) {
    application.svg.selectAll("rect.obstacle")
        .data(allObstacle)
        .enter()
        .append("rect")
        .attr("x", function (d, i) {
            return application.xScale((i % application.xMax));
        })
        .attr("y", function (d, i) {
            return application.yScale(Math.floor(i / application.xMax));
        })
        .attr("fill", function (d) {
            if (d == 1) {
                return "saddlebrown";
            } else {
                return "none";
            }
        })
        .attr("opacity", .5)
        .attr("width", application.xScale(1))
        .attr("height", application.yScale(1));
}

function drawCar(car) {
    drawCircle(
        car.position.x,
        car.position.y,
        config.car.size,
        getPaletteColor(car.num)
    );
}

function drawAllTrajectoryPoint(car) {
    car.trajectory.forEach(function(point) {
        drawCircle(point.x, point.y, config.trajectory.pointSize, getPaletteColor(car.num));
    });

}

function drawCircle(px, py, r, color) {
    application.svg.append("circle")
        .attr("class", "car")
        .attr("cx", application.xScale(px))
        .attr("cy", application.yScale(py))
        .attr("r", application.xScale(r))
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
    application.svg.append("svg:path")
        .attr("id", getTrajectoryId(car.num))
        .attr("d", d3line2(car.trajectory))
        .style("stroke-width", 2)
        .style("stroke", getPaletteColor(car.num))
        .style("fill", "none");
}

function getPossibleMoveId(accelerationCode) {
    return "possible-move-" + accelerationCode;
}

function drawPossibleMove(car, px, py, accelerationCode) {

    var possibleMove = application.svg.append("circle")
        .attr("id", getPossibleMoveId(accelerationCode))
        .attr("class", "possible-move")
        .attr("cx", application.xScale(px))
        .attr("cy", application.yScale(py))
        .attr("r", application.xScale(config.trajectory.pointSize))
        .attr("stroke", "black")
        .attr("fill", getPaletteColor(car.num))
        .attr("opacity", config.possibleMove.opacity);

    application.svg.append("rect")
        .attr("class", "possible-move-mousezone")
        .attr("x", application.xScale(px - (config.possibleMove.mouseZone.size / 2)))
        .attr("y", application.yScale(py - (config.possibleMove.mouseZone.size / 2)))
        .attr("height", application.yScale(config.possibleMove.mouseZone.size))
        .attr("width", application.xScale(config.possibleMove.mouseZone.size))
        .attr("stroke", "transparent")
        .attr("fill", "transparent")
        .on("mouseover", function () {
            possibleMove
                .attr("opacity", 1)
                .attr("r", application.xScale(config.possibleMove.selectedSize));

            car.trajectory.push({x:px, y:py});
            d3.select('#' + getTrajectoryId(car.num))
                .attr("d", d3line2(car.trajectory));
        })
        .on("mouseout", function () {
            possibleMove
                .attr("opacity", config.possibleMove.opacity)
                .attr("r", application.xScale(config.possibleMove.normalSize));

            car.trajectory.pop();
            d3.select('#' + getTrajectoryId(car.num))
                .attr("d", d3line2(car.trajectory));
        })
        .on("click", function () {
            document.forms["car-control"].action += "?accelerationCode=" + accelerationCode;
            document.forms["car-control"].submit();
        });
}