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
  void display() {
    roll.display(x, y, xSpeed < 0);
  }
  void move () {
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
  void falling() {
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
  boolean checkLadders() {
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
  void moveAlongFloor() {
    y = floors[floorNumber].slope * (x - floors[floorNumber].x) + floors[floorNumber].y - 20;
    if (x > floors[floorNumber].rightEdge || x + 24 < floors[floorNumber].leftEdge) {
      inAir = true;
      airtime = 1;
      ySpeed = 2;
    }
  }
}
