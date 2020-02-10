[![Build Status](https://travis-ci.org/caltuntas/trading-card-game.svg?branch=master)](https://travis-ci.org/caltuntas/trading-card-game)
[![Code Coverage](https://codecov.io/github/caltuntas/trading-card-game/coverage.svg)](https://codecov.io/gh/caltuntas/trading-card-game)

# Trading Card Game
This is a simple trading card game

### Prerequisites

In order to run trading card game, As a prerequisite, JRE8 must be installed on your computer

## Installation

You can download [latest release](https://github.com/caltuntas/trading-card-game/releases/latest) and run it by

`java -jar trading-card-game-1.0.0.jar` 

### Building From Source Code
First, make sure you have following software installed on your computer

* [Maven](https://maven.apache.org/) - Dependency Management
* [JDK8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java Development Kit

`git clone https://github.com/caltuntas/trading-card-game.git`

After you can compile the source code by

`mvn clean install`

You will see the final jar file in the target folder

## Game Play

You will see active and opponent player on the screen, also their cards on hand as well as their current deck after game started. Game will ask you to type a command to do your move.  

### Commands

 * S - skip your turn, do nothing and opponent player becomes active.  Example "S" skip
 * P[cardNumber] - Play with card specified with card number. Example "P0" means play with card 0
 
Game will ask you to type a valid command if you type an invalid command