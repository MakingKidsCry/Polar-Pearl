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

void drawPenguin(){
  idle.display(x, y, false);
  help.display(x + 30, y, true);
}

}
