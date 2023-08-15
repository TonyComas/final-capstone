const canvas = document.getElementById("pongGame");
const context = canvas.getContext("2d");
canvas.width=760;
canvas.height=580;

let scoreOne = 0;
let scoreTwo = 0;

const BALL_BASE_SPEED = 3;
const BALL_BASE_Y_VELOCITY = 1;
const BALL_SPEED_CHANGE_PER_HIT = .25;
const BALL_Y_VELOCITY_CHANGE_PER_HIT = 1.3;
const PADDLE_SPEED = 4;
let GAME_OVER = true;

//key movement
window.addEventListener("keydown", keyDown);
window.addEventListener("keyup", keyUp)

function keyDown(e){
    const key = e.key;
    if(key == "w"){
        playerOne.movingUp = true;
    } else if(key == 's'){
        playerOne.movingDown = true;
    }

    if(key == "o"){
        playerTwo.movingUp = true;
    } else if(key == "l"){
        playerTwo.movingDown = true;
    }

    if(key == "Enter"){
        if(GAME_OVER == true){
            GAME_OVER = false;
            scoreTwo = 0;
            scoreOne = 0;
        }
        
    }
}

function keyUp(e){
    const key = e.key;
    if(key == "w"){
        playerOne.movingUp = false;
    } else if(key == 's'){
        playerOne.movingDown = false;
    }

    if(key == "o"){
        playerTwo.movingUp = false;
    } else if(key == "l"){
        playerTwo.movingDown = false;
    }
}


function movePlayers(){
    if(playerOne.movingUp && playerOne.y - playerOne.yVelocity > 0){
        playerOne.y -= playerOne.yVelocity
    }
    if(playerOne.movingDown && playerOne.y + playerOne.height + playerOne.yVelocity < canvas.height){
        playerOne.y += playerOne.yVelocity
    }
    if(playerTwo.movingUp && playerTwo.y - playerTwo.yVelocity > 0){
        playerTwo.y -= playerTwo.yVelocity
    }
    if(playerTwo.movingDown && playerTwo.y + playerTwo.height + playerTwo.yVelocity < canvas.height){
        playerTwo.y += playerTwo.yVelocity
    }
}

class Element {
    constructor(options){
        this.x = options.x,
        this.y = options.y,
        this.width = options.width,
        this.height = options.height,
        this.color = options.color,
        this.xVelocity = options.xVelocity,
        this.yVelocity = options.yVelocity,
        this.movingUp = options.movingUp,
        this.movingDown = options.movingDown
    }
}

const playerOne = new Element({
    x:10,
    y:200,
    width:15,
    height:80,
    color:"white",
    yVelocity: PADDLE_SPEED,
    movingUp: false,
    movingDown: false
});

const playerTwo = new Element({
    x:625,
    y:200,
    width:15,
    height:80,
    color:"white",
    yVelocity: PADDLE_SPEED,
    movingUp: false,
    movingDown: false
});

const ball = new Element({
    x: 650 / 2,
    y: 400 /2,
    width:15,
    height:15,
    color:"white",
    xVelocity: BALL_BASE_SPEED,
    yVelocity: BALL_BASE_Y_VELOCITY 
});

function startGameScreen(){
    context.font = "18px Arial";
    context.fillStyle = "white";
    context.fillText("Press Enter To Start!", canvas.width / 2 - 90, canvas.height/2 - 100)
    context.fillText("Player One W / S     :     Player Two O / L", canvas.width / 2 - 170, canvas.height/2 - 70)
}

function playerOneWon(){
    context.font = "18px Arial";
    context.fillStyle = "white";
    context.fillText("Player One Won!", canvas.width / 2 - 120, canvas.height/2 - 100)
    
}
function playerTwoWon(){
    context.font = "18px Arial";
    context.fillStyle = "white";
    context.fillText("Player Two Won!", canvas.width / 2 - 120, canvas.height/2 - 100)
    
}

function displayScoreOne(){
    context.font = "18px Arial";
    context.fillStyle = "white";
    context.fillText(scoreOne, canvas.width/2 - 80, 30);
}

function displayScoreTwo(){
    context.font = "18px Arial";
    context.fillStyle = "white";
    context.fillText(scoreTwo, canvas.width/2 + 75, 30);
}

function drawElement(element){
    context.fillStyle = element.color;
    context.fillRect(element.x, element.y, element.width, element.height);
}

function ballBounce(){
    if(ball.y + ball.yVelocity <= 0 || ball.y + ball.height + ball.yVelocity >= canvas.height){
        ball.yVelocity = ball.yVelocity * -1;
        ball.y += ball.yVelocity;
        ball.x += ball.xVelocity;
    } else {
        ball.y += ball.yVelocity;
        ball.x += ball.xVelocity;
    }
    ballWallCollision();
}

function ballWallCollision(){
    if(ball.y + ball.yVelocity <= playerOne.y + playerOne.height && 
        ball.y + ball.yVelocity >= playerOne.y && 
        ball.x + ball.xVelocity <= playerOne.x + playerOne.width &&
        ball.x + ball.xVelocity >= playerOne.x)
    {
        ball.xVelocity = ball.xVelocity * -1;
        ball.xVelocity += BALL_SPEED_CHANGE_PER_HIT;

        if(playerOne.movingUp || playerOne.movingDown){
            ball.yVelocity *= BALL_Y_VELOCITY_CHANGE_PER_HIT;
        }

    } 
    
    if(ball.y + ball.yVelocity <= playerTwo.y + playerTwo.height && 
        ball.y + ball.yVelocity >= playerTwo.y &&
        ball.x + ball.xVelocity + ball.width >= playerTwo.x &&
        ball.x + ball.xVelocity + ball.width <= playerTwo.x + playerTwo.width)
    {
        ball.xVelocity = ball.xVelocity * -1;
        ball.xVelocity -= BALL_SPEED_CHANGE_PER_HIT;

        if(playerTwo.movingUp || playerTwo.movingDown){
            ball.yVelocity *= BALL_Y_VELOCITY_CHANGE_PER_HIT;
        }
        
    }
        
    else if(ball.x + ball.xVelocity + ball.width < 0){
        scoreTwo += 1;
        if(scoreTwo == 5){
            playerTwoWon();
            GAME_OVER = true;
        }
        ball.xVelocity = BALL_BASE_SPEED;
        ball.x = canvas.width / 2 + ball.xVelocity;
        ball.yVelocity = BALL_BASE_Y_VELOCITY;
        ball.y = canvas.height /2 + ball.yVelocity;
    }
    else if(ball.x + ball.xVelocity > canvas.width) {
        scoreOne += 1;
        if(scoreOne == 5){
            playerOneWon();
            GAME_OVER = true;
        }
        ball.xVelocity = BALL_BASE_SPEED * -1;
        ball.x = canvas.width / 2 + ball.xVelocity;
        ball.yVelocity = BALL_BASE_Y_VELOCITY;
        ball.y = canvas.height / 2 + ball.yVelocity;
    }
    drawElements();
}

//draw elements
function drawElements(){
    context.clearRect(0, 0, canvas.width, canvas.height);
    drawElement(playerOne);
    drawElement(playerTwo);
    drawElement(ball);
    displayScoreOne();
    displayScoreTwo();
}
function loop(){
    if(GAME_OVER){
        startGameScreen();
    } else{
        movePlayers();
        ballBounce();    
    }
    window.requestAnimationFrame(loop);
}
loop();