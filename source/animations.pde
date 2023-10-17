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
  void display(float x, float y, boolean facingRight){
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
  void update(){
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
