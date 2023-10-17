import com.lowagie.text.html.simpleparser.Img; //<>//
Player player;
Yeti Bobthebuilder;
Penguin peppaThePig;
PImage playerImage, bgImage, winImage;
Ladder ladders[];
ArrayList <snowball> snowballs;
ArrayList <lavaPool> pools;
PImage gameOverImage;
boolean gameOver, youWin;
float Randomdeatherestart = random(2);
float time = 0;
float xWindowSize = 1;
float yWindowSize = 1;
int windowWidth = 640;
int windowHeight = 530;
floor floors[];

int currentLevel = 1;








void setup() {
//fullScreen();
  size(640, 530);
  //surface.setResizable(true);
  frameRate(60);
  //windowResized();
  playerImage = loadImage("Pics/idle0.png");
  bgImage = loadImage("Pics/level"+currentLevel+"-background.jpg");
  //bgImage.resize((int)(640 * xWindowSize), (int)(530 * yWindowSize));
  gameOverImage = loadImage("Pics/gameOver.png");
  winImage = loadImage("Pics/youWin.png");
  winImage.resize(640, 530);
  gameOverImage.resize(640, 530);
  gameOver  = false;
  player = new Player(300, 428, playerImage);
  Bobthebuilder = new Yeti();
  youWin = false;
  ladders = new Ladder [6];

  ladders [0] = new Ladder(450, 400);
  ladders [1] = new Ladder(225, 321);
  ladders [2] = new Ladder(405, 245);
  ladders [3] = new Ladder(200, 170);
  ladders [4] = new Ladder(450, 91);
  ladders [5] = new Ladder(290, 39);
  floors = new floor [7];
  floors [0] = new floor(-0.0000001, 137, 465, 0, 570);
  floors [1] = new floor(0.078, 137, 377, 0, 507);
  floors [2] = new floor(-0.078, 137, 327, 133, 570);
  floors [3] = new floor(0.078, 137, 226, 0, 507);
  floors [4] = new floor(-0.078, 137, 176, 133, 570);
  floors [5] = new floor(0, 137, 92, 0, 507);
  floors [6] = new floor(0, 137, 42, 200, 312);
  snowballs  = new ArrayList <snowball>();
  pools = new ArrayList <lavaPool>();
  if (currentLevel == 3) {
    pools.add(new lavaPool(250, 231.4, 37, 15, 3));
    pools.add(new lavaPool(350, 390, 37, 15, 1));
    pools.add(new lavaPool(290, 155, 60, 15, 4));
  }
  //snowballs.add(new snowball(100, 72));
  //snowballs.add(new snowball(150, 72));
  //snowballs.add(new snowball(200, 72));
  peppaThePig = new Penguin(215, 0);
}
void draw() {
  if (youWin == true) {
    image (winImage, 0, 0);
    resetButton();
    return;
  }
  if (gameOver == true) {
    image(gameOverImage, 0, 0);
    resetButton();
    return;
  }

  update();
  background(25, 25, 50);
  image(bgImage, 0, 0);
  //image(player.image, player.x, player.y);
  player.drawPlayer();
  if (currentLevel == 3) {
    for (int i = 0; i < pools.size(); i ++) {
      pools.get(i).drawLava();
    }
  }
  for ( int i = 0; i < ladders.length; i ++) {
    ladders[i].display();
  }
  Bobthebuilder.drawYeti();
  peppaThePig.drawPenguin();
  for ( int i = 0; i < snowballs.size(); i ++) {
    snowballs.get(i).display();
  }
  textSize(24);
  text("time: " + nf(time, 5, 3), 400, 20);
}
void update() {
  time += 0.0166;
  player.move(ladders);
  Bobthebuilder.update();
  if (Bobthebuilder.createSnowball == true) {
    snowballs.add(new snowball(200, 72));
    Bobthebuilder.createSnowball = false;
  }
  for ( int i = 0; i < snowballs.size(); i ++) {
    snowballs.get(i).move();
  }
  gameOver = player.touching(snowballs);
  if (player.floorNumber == 6 && player.x - peppaThePig.x < 10) { //&& player.x - peppaThePig.x < 10){
    youWin = true;
  }
  
}

void keyPressed() {
  switch(currentLevel) {
  case 1:
    if (key == 'd' || key == 'D') {
      player.rightSpeed = 1;
    }
    if (key == 'a' || key == 'A') {
      player.leftSpeed = -1;
    }
    player.jumpSpeed = -1;
    if (keyCode == 32 && player.ySpeed == 0 && player.onLadder == false) {
      player.ySpeed = player.jumpSpeed;
      player.floatTime = 0;
      player.inAir = true;
    }
    break;
  case 4:
    if (key == 'd' || key == 'D') {
      if (player.inAir == true) {
        player.rightSpeed = 1;
      } else {
        player.rightSpeed = 3;
      }
    }
    if (key == 'a' || key == 'A') {
      if (player.inAir == true) {
        player.leftSpeed = -1;
      } else {
        player.leftSpeed = -3;
      }
    }
    player.jumpSpeed = -0.5;
    if (keyCode == 32 && player.ySpeed == 0 && player.onLadder == false) {
      player.ySpeed = -0.5;
      player.floatTime = 0;
      player.inAir = true;
    }

    break;
  case 2:
    if (key == 'd' || key == 'D') {
      player.rightSpeed = 1;
      player.leftSpeed = 0;
    }
    if (key == 'a' || key == 'A') {
      player.leftSpeed = -1;
      player.rightSpeed = 0;
    }
    player.jumpSpeed = -1;
    if (keyCode == 32 && player.ySpeed == 0 && player.onLadder == false) {
      player.ySpeed = -1;
      player.floatTime = 0;
      player.inAir = true;
    }
    break;
  case 3:
    if (key == 'd' || key == 'D') {
      player.rightSpeed = 3;
    }
    if (key == 'a' || key == 'A') {
      player.leftSpeed = -3;
    }
    player.jumpSpeed = -1;
    if (keyCode == 32 && player.ySpeed == 0 && player.onLadder == false) {
      player.ySpeed = -1;
      player.floatTime = 0;
      player.inAir = true;
    }
    break;
  }

  //if (player.inAir == false && player.onLadder == true) {
  if (key == 'w' || key == 'W') {
    player.ySpeed = -3;
  }
  if (key == 's' || key == 'S') {
    player.ySpeed = 3;
  }
  //}
}


void keyReleased () { 
  if (key == 'd' && currentLevel != 2) {
    player.rightSpeed = 0;
  }
  if (key == 'a' && currentLevel != 2) {
    player.leftSpeed = 0;
  }
  //if (player.inAir == false && player.onLadder == true) {
  if (key == 'w') {
    player.ySpeed = 0;
  }
  if (key == 's') {
    player.ySpeed = 0;
  }
}
void resetButton() {
  if ((gameOver == true || youWin == true) && mouseX >= 200 && mouseX <= 450 && mouseY >= 350 && mouseY <= 450) {
    fill(255, 0, 0);
    strokeWeight(10);
    stroke(#00FFDF);
  } else {
    fill(#B600FF);
    strokeWeight(1);
    stroke(255);
  }


  rect(200, 350, 250, 100);
  textSize(27);
  fill(0, 0, 50);
  if (youWin == false) {
    text("Do I know you \nbecause you suck", 205, 390);
  } else {
    text(" your still not \n good", 205, 390);
  }
}
void mousePressed() {
  if ((gameOver == true || youWin == true) && mouseX >= 200 && mouseX <= 450 && mouseY >= 350 && mouseY <= 450) {
    gameOver = false;

    if (youWin == true) {
      youWin = false;
      currentLevel ++;
      if (currentLevel > 4) {
        currentLevel = 1;
      }
    }

    setup();
  }
}
//}
