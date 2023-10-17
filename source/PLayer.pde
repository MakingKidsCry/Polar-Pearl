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


  void move(Ladder[] ladders) {
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
  void jump() {
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
  void drawPlayer() {
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
  boolean checkLadders(Ladder[] ladders) {
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
  int getFloorNum() {
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
  void moveAlongFloor() {
    y = floors[floorNumber].slope * (x - floors[floorNumber].x) + floors[floorNumber].y - image.height;
    if (x > floors[floorNumber].rightEdge || x + image.width < floors[floorNumber].leftEdge) {
      inAir = true;
      floatTime = 1;
      ySpeed = 2;
    }
  }
  boolean touching(ArrayList <snowball> snowballs) {
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
