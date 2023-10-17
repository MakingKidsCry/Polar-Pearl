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
  void drawYeti() {
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
  void update() {
    tossTime -= 0.017;
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
