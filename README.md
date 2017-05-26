[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a1808a7d5d4f4935a081224128dc1bd4)](https://www.codacy.com/app/madar94/FlappySpaceShip?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=madar94/FlappySpaceShip&amp;utm_campaign=Badge_Grade)


# Flappy SpaceShip
This application is an assignment for **Programming Technologies** and **Programming Environments** class at the [University of Debrecen, Faculty of Informatics](http://www.inf.unideb.hu/).

## Install
You need Java 1.8 at least and Maven 3.x+.

1. `mvn clean package` to build the standalone `.jar` application
2. `cd target` to navigate to the build directory
3. `java -jar FlappySpaceShip-1.0-jar-with-dependencies.jar` to start the game

or

1. `mvn exec:java` to run the game directly

## What is the FlappySpaceShip?
This is an implementation of Flappy Bird using the JavaFX platform.
Like the original, the user must use the space bar in order to flap the ship  to fly, while avoiding being hit by pipes.

## License
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)