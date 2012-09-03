<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="formulagrid"/>
    <title>Historique</title>
    <r:require modules="bootstrapDocs"/>
</head>

<body>

<header class="jumbotron subhead" id="overview">
    <h1>Historique</h1>

    <p class="lead">L'avancement du projet version par version</p>

    <div class="subnav">
        <ul class="nav nav-pills">
            <li><a href="#version-0.6">Version 0.6</a></li>
            <li><a href="#version-0.5">Version 0.5</a></li>
            <li><a href="#version-0.4">Version 0.4</a></li>
            <li><a href="#version-0.3">Version 0.3</a></li>
            <li><a href="#version-0.2">Version 0.2</a></li>
            <li><a href="#version-0.1">Version 0.1</a></li>
        </ul>
    </div>
</header>

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