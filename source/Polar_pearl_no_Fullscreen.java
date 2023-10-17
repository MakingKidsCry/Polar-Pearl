import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import com.lowagie.text.html.simpleparser.Img; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Polar_pearl_no_Fullscreen extends PApplet {

 //<>//
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








public void setup() {
//fullScreen();
  
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
  floors [0] = new floor(-0.0000001f, 137, 465, 0, 570);
  floors [1] = new floor(0.078f, 137, 377, 0, 507);
  floors [2] = new floor(-0.078f, 137, 327, 133, 570);
  floors [3] = new floor(0.078f, 137, 226, 0, 507);
  floors [4] = new floor(-0.078f, 137, 176, 133, 570);
  floors [5] = new floor(0, 137, 92, 0, 507);
  floors [6] = new floor(0, 137, 42, 200, 312);
  snowballs  = new ArrayList <snowball>();
  pools = new ArrayList <lavaPool>();
  if (currentLevel == 3) {
    pools.add(new lavaPool(250, 231.4f, 37, 15, 3));
    pools.add(new lavaPool(350, 390, 37, 15, 1));
    pools.add(new lavaPool(290, 155, 60, 15, 4));
  }
  //snowballs.add(new snowball(100, 72));
  //snowballs.add(new snowball(150, 72));
  //snowballs.add(new snowball(200, 72));
  peppaThePig = new Penguin(215, 0);
}
public void draw() {
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
public void update() {
  time += 0.0166f;
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

public void keyPressed() {
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
    player.jumpSpeed = -0.5f;
    if (keyCode == 32 && player.ySpeed == 0 && player.onLadder == false) {
      player.ySpeed = -0.5f;
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


public void keyReleased () { 
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
public void resetButton() {
  if ((gameOver == true || youWin == true) && mouseX >= 200 && mouseX <= 450 && mouseY >= 350 && mouseY <= 450) {
    fill(255, 0, 0);
    strokeWeight(10);
    stroke(0xff00FFDF);
  } else {
    fill(0xffB600FF);
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
public void mousePressed() {
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
class lavaPool{
  float x, y;
  PImage lava;
  float xSize, ySize;
  int floorNumber = 0;
  public lavaPool(float x, float y, float xSize, float ySize, int floorNumber){
    lava = loadImage("Pics/lavaPool.png");
    this.x = x;
    this.y = y;
    this.xSize = xSize;
    this.ySize = ySize;
    this.floorNumber = floorNumber;
  }
  public void drawLava(){
    image(lava, x, y, xSize, ySize);
  }
}
class Ladder {
  PImage image;
  float x;
  float y;
  float right;
  float bottom;
  int yoffset = 15;

  public Ladder(float x, float y) {
    image = loadImage("Pics/ladder.png");
    //image.resize(image.width), convertY(image.height));
    this.x = x;
    this.y = y;
    right = x + image.width;
    bottom = y + image.height;
  }
  public void display() {
    image(image, x, y);
  }
}
//<>//
class Player {
  animation walk, idle, jump, climb, currentAnim;
  float x, y;
  float xSpeed;
  float ySpeed;
  PImage image;
  int floatTime;
  float leftSpeed;
  float rightSpeed;
  boolean facingRight;
  boolean inAir = false;
  boolean onLadder = false;
  int floorNumber = 0;
  float jumpSpeed = -2;



  public Player(int x, int y, PImage image) {
    this.x = x;
    this.y = y;
    this.image = image;
    xSpeed = 0;
    ySpeed = 0;
    walk = new animation("walk", 5);
    idle = new animation("idle", 3);
    jump = new animation("jump", 2);
    climb = new animation("climb", 3);
  }


  public void move(Ladder[] ladders) {
    floorNumber = getFloorNum();
    if (inAir) {
      jump();
    } else {

      onLadder = checkLadders(ladders);
      if (player.onLadder == false) {

        moveAlongFloor();
      }
    }
    xSpeed = rightSpeed + leftSpeed;
    y += ySpeed;
    if (xSpeed >= 1  && x < 535) {
      x += xSpeed;
      facingRight = true;
    } else if (xSpeed <= -1 && x > 65) {
      x += xSpeed;
      facingRight = false;
    }
  }
  public void jump() {
    if (floatTime >= 30) {

      if (ySpeed < 0) {
        ySpeed = -jumpSpeed;
      } else 
      {
        ySpeed = 0;
        inAir = false;
      }
      floatTime = 1;
    } else {
      floatTime += 1;
    }
  }
  public void drawPlayer() {
    if (inAir && onLadder == false) {
      currentAnim = jump;
    } else if (onLadder == true && inAir == false) {
      currentAnim = climb;
    } else {
      if (xSpeed != 0) {
        currentAnim = walk;
      } else {
        currentAnim = idle;
      }
    }

    currentAnim.display(x, y, facingRight);
  }
  public boolean checkLadders(Ladder[] ladders) {
    for (Ladder lad : ladders) {
      int ladderOffset = 15;
      int playerLeft = (int)(x + 20);
      int playerRight = (int)(x -20 + image.width);
      if (ySpeed < 0) {
        if (playerLeft > lad.x && playerRight < (lad.x + lad.image.width)) {


          if (y < (lad.y + lad.image.height) && (y + image.height) > lad.y) {
            return true;
          }
        }
      } else if (ySpeed > 0) {
        if (playerLeft > lad.x && playerRight < (lad.x + lad.image.width)) {
          if (y + image.height < (lad.y + lad.image.height) && (y+image.height) > (lad.y  - ladderOffset) ) {
            return true;
          }
        }
      } else if (ySpeed == 0){
        if (playerLeft > lad.x && playerRight < (lad.x + lad.image.width)) {
          if (y + image.height < (lad.y + lad.image.height - ladderOffset) && (y + image.height) > (lad.y + ladderOffset)) {
            return true;
          }
        }
      }
    }
    return false;
  }
  public int getFloorNum() {
    if (y < 20) {
      return 6;
    } else if (y < 80) {
      return 5;
    } else if (y < 160) {
      return 4;
    } else if (y < 240) {
      return 3;
    } else if (y < 320) {
      return 2;
    } else if (y < 400) {
      return 1;
    } else {
      return 0;
    }
  }
  public void moveAlongFloor() {
    y = floors[floorNumber].slope * (x - floors[floorNumber].x) + floors[floorNumber].y - image.height;
    if (x > floors[floorNumber].rightEdge || x + image.width < floors[floorNumber].leftEdge) {
      inAir = true;
      floatTime = 1;
      ySpeed = 2;
    }
  }
  public boolean touching(ArrayList <snowball> snowballs) {
    float centerX = x + image.width / 2;
    float centerY = y + image.height / 2;
    for (int i = 0; i < snowballs.size(); i ++) {
      snowball ball = snowballs.get(i);
      float ballcenterX =  ball.x + ball.w / 2;
      float ballcenterY = ball.y + ball.w / 2;
      float distance = sqrt((centerX - ballcenterX) * (centerX - ballcenterX) + (centerY - ballcenterY) * (centerY - ballcenterY));
      if (distance < 20) {
        return true;
      }
    }
    for(int i = 0; i < pools.size(); i ++){
      lavaPool pool = pools.get (i);
      float poolRight = pool.x + pool.xSize / 2;
      float poolLeft = pool.y + pool.xSize / 2;
      if(floorNumber == pool.floorNumber){
        if(x < poolRight  - 5 &&  x + image.width > pool.x + 20&& inAir == false) {
        return true;
      }
      }
      
    }
    return false;
  }
}
class Yeti {
  float x, y;
  PImage pileOfSnowballs;
  animation dance;
  animation stand;
  animation toss;
  animation currentAnimation;
  boolean tossing = false;
  boolean danceing = false;
  boolean standing = true;
  float tossTime;
  boolean createSnowball = false;
  public Yeti() {
    tossTime = random(1);
    pileOfSnowballs = loadImage("Pics/ballpile.png");
    dance = new animation("BobDance", 4);
    stand = new animation("BobStand", 2);
    toss = new animation("BobToss", 3);
    dance.maxDuration = 13;
    stand.maxDuration = 30;
    toss.maxDuration = 10;
    x = 120;
    y= 30;
  }
  public void drawYeti() {
    if (standing == true) {
      currentAnimation = stand;
    } else if (danceing == true) {
      currentAnimation = dance;
    } else if (tossing == true) {
      currentAnimation = toss;
    }
    currentAnimation.display(x, y, false);
    image(pileOfSnowballs, x - 45, y);
  }
  public void update() {
    tossTime -= 0.017f;
    if (tossTime > 5) {
      tossing = false;
      standing = false;
      danceing = true;
    } else if (tossTime > 0) {
      tossing = false;
      standing = true;
      danceing = false;
    } else {
      tossing = true;
      standing = false;
      danceing = false;
    }
    if(toss.currentImage == toss.images.length - 1 && toss.timer == toss.maxDuration){
      createSnowball = true;
      danceing = false;
      standing = true;
      tossing = false;
      tossTime = random(1, 2);
      toss.currentImage = 0;
      toss.timer = 0;
    }
  }
}
class animation {
  PImage[]images;
  int maxDuration  = 10;
  int timer;
  int currentImage;
  boolean ball;

  public animation(String imageName, int n) {
    images = new PImage [n];
    for (int i = 0; i < n; i ++) {
      images[i] = loadImage("Pics/" + imageName + i + ".png");
    }ball = false;
  }
   public animation(String imageName, int n, boolean b) {
    images = new PImage [n];
    for (int i = 0; i < n; i ++) {
      images[i] = loadImage("Pics/" + imageName + i + ".png");
    }ball = b;
  }
  public void display(float x, float y, boolean facingRight){
    update();
    if(facingRight){
      if(ball == true){
        image(images [currentImage], x, y, 24, 20);
      }
      else{
        image(images[currentImage], x, y);
      }
      
    }
    else{
      if(ball == true){
        pushMatrix();
      scale(-1, 1);
      int w = 24;
      image(images[currentImage], -x - w, y, 24, 20);
      popMatrix();
      }else{
        pushMatrix();
      scale(-1, 1);
      int w = images[currentImage].width;
      image(images[currentImage], -x - w, y);
      popMatrix();
      }
      
    }
    
  }
  public void update(){
    //check if timer has reached the max duration of time
    if(timer >= maxDuration){
      timer= 0;
      currentImage ++;
    }
    if(currentImage >= images.length){
      currentImage = 0;
    }
    timer ++;
    //reset timer
    //increase current image by 1 to display next image in array
    // check if current image has reached the total # of images
    
    //reset current image back to 0 to repeat the animation
    // increase the timer by 1
    
  }
}
class floor {
  int x;
  int y;
  float slope;
  int leftEdge;
  int rightEdge;

  public floor(float slope, int x, int y, int leftEdge, int rightEdge) {
    this.slope = slope;
    this.x = x;
    this.y = y;
    this.leftEdge = leftEdge;
    this.rightEdge = rightEdge;
  }
}
class Penguin{
  float x, y;
  animation help, idle;
  public Penguin(float x, float y){
    this.x = x;
    this.y = y;
    help = new animation("help", 2);
    idle = new animation("penguin", 1);
    help.maxDuration = 50;
    
  }

public void drawPenguin(){
  idle.display(x, y, false);
  help.display(x + 30, y, true);
}

}
class snowball {
  animation roll;
  PImage image;
  float x, y;
  float xSpeed, ySpeed;
  boolean inAir, onLadder;
  int floorNumber = 0;
  int airtime;
  float w;



  public snowball(float x, float y) {
    this.x = x;
    this.y = y;
    xSpeed = 1;
    ySpeed = 0;
    inAir = false;
    switch(currentLevel){
      case 1:
      // use logs
            roll = new animation("log", 4, true);

      break;
      case 4:
      // use tumbleweed 
      roll = new animation("tumbleweed", 4, true);
      break;
      case 2:
      roll = new animation("ball", 4);
      break;
      case  3:
      //use fireball
            roll = new animation("fire", 4, true);

      break;
    }
    
    image = roll.images[0];
    this.w = 24;
  }
  public void display() {
    roll.display(x, y, xSpeed < 0);
  }
  public void move () {
    x += xSpeed;
    y += ySpeed;
    floorNumber = getFloorNum();
    if (inAir == true) {
      this.falling();
    } else {
      onLadder = checkLadders();
      if (onLadder == false) {
        if (floors[floorNumber].slope < 0) {
          xSpeed = -1;
        } else{
          xSpeed = 1;
        }
        moveAlongFloor();
      }else {
          xSpeed = 0 ;
          ySpeed = 2;
          inAir = true;
        }
    }
  }
  public int getFloorNum() {
    if (y < 20) {
      return 6;
    } else if (y < 80) {
      return 5;
    } else if (y < 160) {
      return 4;
    } else if (y < 240) {
      return 3;
    } else if (y < 320) {
      return 2;
    } else if (y < 400) {
      return 1;
    } else {
      return 0;
    }
  }
  public void falling() {
    if (airtime == 25) {
      if (ySpeed == -1) {
        ySpeed  = 1;
      } else {
        ySpeed = 0;
        inAir = false;
      }
      airtime = 1;
    } else {
      airtime += 1;
    }
  }
  public boolean checkLadders() {
    for (Ladder lad : ladders) {
      if  (random(100)>92) {
        if (x > lad.x && (x + 24) <lad.right) {
          if (y + 20 - lad.yoffset < lad.y && y + 20 + lad.yoffset > lad.y) {
            return true;
          }
        }
      }
    }
    return false;
  }
  public void moveAlongFloor() {
    y = floors[floorNumber].slope * (x - floors[floorNumber].x) + floors[floorNumber].y - 20;
    if (x > floors[floorNumber].rightEdge || x + 24 < floors[floorNumber].leftEdge) {
      inAir = true;
      airtime = 1;
      ySpeed = 2;
    }
  }
}
  public void settings() {  size(640, 530); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Polar_pearl_no_Fullscreen" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
