# boardgame

This is a simple board game.
At least two players are needed to play this game.
A dice will be thrown by each player.
A player will be eligible to earn a score after throwing a six.
The first six will not add any bonus.
Each dice will add an additional point after the first six. 
There is a winning score that should be earned to win this game.
One who can reach this score for the first time will win.

There are some rules that must be followed : 
* There should be at least two players and at max four players for this game.
* The winning score cannot be zero.

There are total four API are available for this game. They should be executed sequentially. They are given below : 
1. `POST` http://localhost:8080/api/v1/board-game/totalscore
2. `POST` http://localhost:8080/api/v1/board-game/player
3. `POST` http://localhost:8080/api/v1/board-game/start
4. `GET` http://localhost:8080/api/v1/board-game/get-score

### Technology used in this project
1. Spring boot
2. Java 11

### How to run this game
1. build the project using this command :<br>
`./mvnw clean install`
2. use this docker command to build a docker image : <br>
`docker build -t hishab/boardgame .`
3. After creating the image , use the following command to run the project<br>
`docker run -p 8080:8080 hishab/boardgame`

** The swagger documentation can be found
form this link :
>http://localhost:8080/swagger-ui/#/

