var d3line2 = d3.svg.line()
    .x(function (d) {
        return xScale(d.x);
    })
    .y(function (d) {
        return yScale(d.y);
    })
    .interpolate("cardinal");

function drawTrackBox(fill) {
    svg.append("rect")
        .attr("class", "track")
        .attr("x", xScale(-.5))
        .attr("y", yScale(-.5))
        .attr("stroke", "darkslategrey")
        .attr("width", xScale(xMax + 1))
        .attr("height", yScale(yMax + 1))
        .attr("stroke-width", strokeWidth)
        .attr("fill", fill)
        .attr("rx", rounded)
        .attr("ry", rounded)
}

function drawTrackGrid() {
    svg.selectAll("line.vertical")
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


    svg.selectAll("line.horizontal")
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
    svg.selectAll("rect.obstacle")
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

function drawCar(px, py, r, color) {
    svg.append("circle")
        .attr("class", "car")
        .attr("cx", xScale(px))
        .attr("cy", yScale(py))
        .attr("r", xScale(r))
        .attr("stroke", "black")
        .attr("fill", color)
}

function drawTrajectory(path, color) {
    svg.append("svg:path")
        .attr("d", d3line2(path))
        .style("stroke-width", 2)
        .style("stroke", color)
        .style("fill", "none");
}