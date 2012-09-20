modules = {
    application {
        resource url:'js/application.js'
    }

    d3 {
        resource url:'js/d3.v2.js', disposition: 'head'
    }

    bootstrapDocs {
        resource url: 'css/bootstrap-docs.css'
        dependsOn "bootstrap"
    }

    race {
        resource url: 'js/FormulaGrid/race.js', disposition: 'head'
        dependsOn 'd3'
        dependsOn 'jquery'
    }

    historyRace08 {
        resource url: 'js/FormulaGrid/history/race-0.8.js', disposition: 'head'
        dependsOn 'd3'
    }

    historyRace07 {
        resource url: 'js/FormulaGrid/history/race-0.7.js', disposition: 'head'
        dependsOn 'd3'
    }
}