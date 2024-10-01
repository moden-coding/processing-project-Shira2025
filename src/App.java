import processing.core.*;

public class App extends PApplet {
    int checkCount = 0;
    boolean clicked = false;
    int boxX = 100;
    int boxY = 675;
    int boxWidth = 100;
    int boxHeight = 100;
    float x = 325, Y = 300, Width = 150, Height = 150; // place here box
    float  Width2 = boxWidth, Height2 = boxHeight; // moving block

    boolean isVisible = true; // A flag to control visibility
    int visibleDuration = 15000;  // 15 seconds (15000 milliseconds)
    int startTime;             // Variable to store the time when the shape became visible

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        background(99, 119, 122); // blue grey
        fill(0);
        textSize(50);
        text("order", 350, 50);
        fill(255, 69, 69);
        rect(500, 25, 50, 50);
        startTime = millis();
    }

    public void settings() {
        size(800, 800); // Set the size of the window (top corner 0,0, bottom 800,800)

    }

    public void draw() {
//         int currentTime = millis();
//          // Check if 15 seconds have passed since the shape became visible
//   if (currentTime - startTime > visibleDuration) {
//     isVisible = false;   // Set visibility to false after 15 seconds
//   }
//   // Only draw the rectangle if the isVisible flag is true
//   if (isVisible) {
    fill(255, 182, 193);      // Pale pink color
    rect(500, 15, 50, 50);
        background(99, 119, 122); // blue grey
        fill(0);
        textSize(50);
        text("Order:", 350, 50);
        fill(255, 69, 69); // order box
        rect(500, 15, 50, 50); // order box red
        fill(0); // text black
        text("You have:",50, 650);
        text("Place here:",75,375);
        rect( 325,300,150,150);  // box for place here
        fill (255, 69, 69); // red
        rect(boxX, boxY, boxWidth, boxHeight);
        if (clicked) {
            boxX = mouseX - boxWidth/2;
            boxY = mouseY - boxHeight/2; 
        }
         else if (isOverlapping( x,  Y,  Width,  Height,  boxX,  boxY,  Width2, Height2) && checkCount == 0) {
            fill(0); // Change fill color to black if overlapping
            textSize(32);
            text("Rectangles are overlapping!", 80, 80);
            } else {
            fill(0);
            textSize(32);
            text("Rectangles are NOT overlapping.", 80, 80);
        }
        // else if(isOverlapping(x2, y2, width2, height2, box2X, box2Y, box2Width, box2Height) && checkCount == 1)
    }


    public void mouseDragged() {

    }

    public void mouseReleased() {

    }

    public void mousePressed() {
        System.out.println("you clicked!");
        if (mouseX > boxX && mouseY > boxY && mouseX < boxX + boxWidth && mouseY < boxHeight + boxY) { // mouse has to be in the box
            System.out.println("box was clicked");
            clicked = !clicked;
            System.out.println(clicked);
        }
    }

    // Function to check if two rectangles are overlapping  
    boolean isOverlapping(float targetX, float targetY, float targetWidth, float targetHeight, float movingBoxX, float movingBoxY, float movingBoxWidth, float movingBoxHeight) {
        // System.out.println("checking overlap!");
        System.out.println("first check" + (movingBoxX < targetX + targetWidth));
        System.out.println("second check" + (movingBoxX + movingBoxWidth > targetX));
        System.out.println("boxX " + movingBoxX);
        System.out.println("width2 " + movingBoxWidth);
        System.out.println("x " + targetX);
        if(movingBoxX < targetX + targetWidth && movingBoxX + movingBoxWidth > targetX){// Check horizontal overlap

            System.out.println("x overlapping!");// Check vertical overlap
            if(boxY< movingBoxY + movingBoxHeight && movingBoxY + movingBoxHeight > targetY){
                System.out.println("x and y overlapping");
                return true;
            }
        }

        return false; 
     }

  
}

