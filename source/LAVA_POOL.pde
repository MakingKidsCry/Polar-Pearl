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
  void drawLava(){
    image(lava, x, y, xSize, ySize);
  }
}
