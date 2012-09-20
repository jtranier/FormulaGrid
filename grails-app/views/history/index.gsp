<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="formulagrid"/>
    <title>Historique</title>
    <r:require modules="bootstrapDocs,historyRace07,historyRace08"/>
</head>

<body>

<header class="jumbotron subhead" id="overview">
    <h1>Historique</h1>

    <p class="lead">L'avancement du projet version par version</p>

    <div class="subnav">
        <ul class="nav nav-pills">
            <li><a href="#version-0.8">Version 0.8</a></li>
            <li><a href="#version-0.7">Version 0.7</a></li>
            <li><a href="#version-0.6">Version 0.6</a></li>
            <li><a href="#version-0.5">Version 0.5</a></li>
            <li><a href="#version-0.4">Version 0.4</a></li>
            <li><a href="#version-0.3">Version 0.3</a></li>
            <li><a href="#version-0.2">Version 0.2</a></li>
            <li><a href="#version-0.1">Version 0.1</a></li>
        </ul>
    </div>
</header>

<section id="version-0.8">
    <div class="row">
        <div class="span10 offset1">
            <h1>Version 0.8</h1>

            <div class="row">
                <div class="span4 offset1">
                    <div id="svg08"></div>
                </div>

                <div class="span4">
                    <p class="lead">Animations & fluidité</p>

                    <p>Les communications entre le client et le serveur s'effectuent de manière asynchrones (AJAX) ce
                    qui permet de ne pas recharger l'intégralité de la page à chaque déplacement.</p>

                    <p>Le déplacements des autos est maintenant animé.</p>

                </div>

                <script type="text/javascript">
                    function createRace08Scenario() {
                        var config = {
                            width:20,
                            height:10
                        };
                        var allObstacleAsList = [];

                        for (var j = 0; j < config.height; j++) {
                            for (var i = 0; i < config.width; i++) {
                                if (i === 0 || j === 0 || i === config.width - 1 || j === config.height - 1) {
                                    allObstacleAsList.push(1);
                                }
                                else {
                                    allObstacleAsList.push(0);
                                }
                            }
                        }

                        var car = {
                            num:1,
                            trajectory:[],
                            position:{x:4, y:8},
                            speed:{x:0, y:0}
                        };

                        var race = {
                            track:{
                                width:config.width,
                                height:config.height,
                                allObstacleAsList:allObstacleAsList
                            },
                            currentCarNum:1,
                            allCar:[car]
                        };

                        var race08 = new Race08(race, "svg08");

                        function move1() {
                            car.trajectory = [
                                {x:4, y:8},
                                {x:4, y:7}
                            ];
                            car.position = {x:4, y:7};
                            car.speed = {x:0, y:-1};
                            race08.moveCar(car, 1);
                        }

                        function move2() {
                            car.trajectory = [
                                {x:4, y:8},
                                {x:4, y:7},
                                {x:5, y:6}
                            ];
                            car.position = {x:5, y:6};
                            car.speed = {x:1, y:-1};
                            race08.moveCar(car, 1);
                        }

                        function move3() {
                            car.trajectory = [
                                {x:4, y:8},
                                {x:4, y:7},
                                {x:5, y:6},
                                {x:7, y:5}
                            ];
                            car.position = {x:7, y:5};
                            car.speed = {x:2, y:-1};
                            race08.moveCar(car, 1);
                        }

                        function move4() {
                            car.trajectory = [
                                {x:4, y:8},
                                {x:4, y:7},
                                {x:5, y:6},
                                {x:7, y:5},
                                {x:10, y:5}
                            ];
                            car.position = {x:10, y:5};
                            car.speed = {x:3, y:0};
                            race08.moveCar(car, 1);
                        }

                        function reset() {
                            car = {
                                num:1,
                                trajectory:[],
                                position:{x:4, y:8},
                                speed:{x:0, y:0}
                            };

                            race08.reset(car);
                        }

                        function scenarioLoop() {
                            window.setTimeout(move1, 1000);
                            window.setTimeout(move2, 2000);
                            window.setTimeout(move3, 3000);
                            window.setTimeout(move4, 4000);
                            window.setTimeout(reset, 5000);
                        }

                        scenarioLoop();
                        window.setInterval(scenarioLoop, 6000);
                    }

                    createRace08Scenario();
                </script>
            </div>
        </div><!-- /.span -->

    </div><!-- /.row -->
</section>
<hr/>


<section id="version-0.7">
    <div class="row">
        <div class="span10 offset1">
            <h1>Version 0.7</h1>

            <div class="row">
                <div class="span4">
                    <p class="lead">Déplacements interactifs</p>

                    <ul>
                        <li>Les destinations possibles sont représentées par des cercles transparents.</li>
                        <li>Le survol par la souris d'une destination permet d'afficher la trajectoire correspondante.</li>
                        <li>Le clic sur une destination possible permet d'effectuer le déplacement.</li>
                    </ul>

                    <p>
                        Le code <i>Javascript</i> a été packagé sous forme d'un module réutilisable, ce qui permet
                    l'illustration ci-contre.
                    </p>
                </div>

                <div class="span4 offset1">
                    <div id="svg07"></div>
                </div>
                <script type="text/javascript">
                    function createRace07Scenario() {
                        var config07 = {
                            width:20,
                            height:10
                        };
                        var allObstacleAsList = [];

                        for (var j = 0; j < config07.height; j++) {
                            for (var i = 0; i < config07.width; i++) {
                                if (i === 0 || j === 0 || i === config07.width - 1 || j === config07.height - 1) {
                                    allObstacleAsList.push(1);
                                }
                                else {
                                    allObstacleAsList.push(0);
                                }
                            }
                        }

                        var race = {
                            track:{
                                width:config07.width,
                                height:config07.height,
                                allObstacleAsList:allObstacleAsList
                            },
                            currentCarNum:1,
                            allCar:[
                                {
                                    num:1,
                                    trajectory:[
                                        {x:7, y:8},
                                        {x:8, y:7},
                                        {x:9, y:5}
                                    ],
                                    position:{x:9, y:5},
                                    speed:{x:1, y:-2}
                                }
                            ]
                        };

                        createRaceApplication(race, "svg07");
                    }

                    createRace07Scenario();
                </script>
            </div>
        </div><!-- /.span -->
    </div><!-- /.row -->
</section>
<hr/>


<section id="version-0.6">
    <div class="row">
        <div class="span10 offset1">
            <h1>Version 0.6</h1>

            <div class="row">
                <div class="span6">
                    <g:img dir="images/FormulaGrid/history" file="formulagrid-0.6.JPG"/>
                </div>

                <div class="span4">
                    <p class="lead">Contenu rédactionnel</p>

                    <ul>
                        <li>Page d'accueil du site</li>
                        <li>Description des règles du jeu</li>
                        <li>Création de la présente page "historique"</li>
                    </ul>

                </div>
            </div>
        </div><!-- /.span -->

    </div><!-- /.row -->
</section>
<hr/>

<section id="version-0.5">
    <div class="row">
        <div class="span10 offset1">
            <h1>Version 0.5</h1>

            <div class="row">
                <div class="span4">
                    <p class="lead">Représentation graphique</p>

                    <p>Le dernier chipset 3D ne sera sans doute pas nécessaire ... Mais FormulaGrid dispose enfin d'une interface graphique.</p>

                    <p>Le circuit, les autos, et les trajectoires sont dessinés en SVG avec le framework <i>d3.js</i>.
                    </p>
                </div>

                <div class="span6">
                    <g:img dir="images/FormulaGrid/history" file="formulagrid-0.5.JPG"/>
                </div>
            </div>
        </div><!-- /.span -->
    </div><!-- /.row -->
</section>
<hr/>

<section id="version-0.4">
    <div class="row">
        <div class="span10 offset1">
            <h1>Version 0.4</h1>

            <div class="row">
                <div class="span6">
                    <g:img dir="images/FormulaGrid/history" file="formulagrid-0.4.JPG"/>
                </div>

                <div class="span4">
                    <p class="lead">Trajectoires</p>

                    <p>Représentation des trajectoires des autos en mode ASCII.</p>

                    <p></p>

                    <p>Rien de très sexy, mais les frissons de la course automobile deviennent accessibles à n'importe quel <i>geek</i> ;-)
                    </p>
                </div>
            </div>
        </div><!-- /.span -->

    </div><!-- /.row -->
</section>
<hr/>

<section id="version-0.3">
    <div class="row">
        <div class="span10 offset1">
            <h1>Version 0.3</h1>

            <div class="row">
                <div class="span4">
                    <p class="lead">Relooking de l'interface</p>

                    <p>C'est le passage du noir et blanc à la couleur ...</p>

                    <p></p>

                    <p>Le squelette du site web est construit avec l'aide de <i>twitter-bootstrap</i>.</p>

                    <p></p>

                    <p>La visualisation de la course pique un peu moins les yeux (il faut quand même le dire vite :P).</p>

                </div>

                <div class="span6">
                    <g:img dir="images/FormulaGrid/history" file="formulagrid-0.3a.JPG"/>
                    <g:img dir="images/FormulaGrid/history" file="formulagrid-0.3b.JPG"/>
                </div>
            </div>

        </div><!-- /.span -->
    </div><!-- /.row -->
</section>
<hr/>

<section id="version-0.2">
    <div class="row">
        <div class="span10 offset1">
            <h1>Version 0.2</h1>

            <div class="row">
                <div class="span6">
                    <g:img dir="images/FormulaGrid/history" file="formulagrid-0.2.JPG"/>
                </div>

                <div class="span4">
                    <p class="lead">Déplacements sur un circuit</p>

                    <p>Les circuits sont chargés à partir de simples fichiers ASCII.</p>

                    <p>On peut y décrire les positions de départ, la ligne d'arrivée, et le tracé du circuit.</p>

                    <p></p>

                    <p>La gestion des tours de jeu d'une course a été mise en place.
                    En théorie, on peut jouer de 1 à 6 joueurs. Dans la pratique, l'interface ne permet qu'une course à 2 joueurs pour l'instant.</p>


                    <p></p>

                    <p>La gestion des déplacements est complète, avec prise en compte des collisions et des sorties de routes.</p>

                    <p></p>

                    <p>Des tests unitaires sont rédigés systématiquement avec <i>Spock</i>.</p>
                </div>
            </div>

        </div><!-- /.span -->
    </div><!-- /.row -->
</section>
<hr/>

<section id="version-0.1">
    <div class="row">
        <div class="span10 offset1">
            <h1>Version 0.1</h1>

            <div class="row">
                <div class="span4">
                    <p class="lead">Démarrage du projet</p>

                    <p>Utilisation de <i>GitHub</i> pour héberger les sources, et de <i>CloudFoundry</i> pour déployer l'application.
                    </p>

                    <p></p>

                    <p>A ce stade, le jeu se résume à :</p>
                    <ul>
                        <li>une représentation minimaliste d'une auto (une position et une vitesse) ;</li>
                        <li>un pavé numérique permettant de gérer l'accélération, passer au tour suivant, et découvrir la nouvelle position de l'auto.</li>
                    </ul>
                </div>

                <div class="span4 offset2">
                    <g:img dir="images/FormulaGrid/history" file="formulagrid-0.1.JPG"/>
                </div>
            </div>

        </div><!-- /.span -->
    </div><!-- /.row -->
</section>

</body>
</html>