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
  void display() {
    image(image, x, y);
  }
}
