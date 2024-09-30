import processing.core.*;

public class App extends PApplet {
    boolean clicked = false;
    int boxX = 100;
    int boxY = 675;
    int boxWidth = 100;
    int boxHeight = 100;
    float x = 325, Y = 300, Width = 150, Height = 150;
    float x2 = boxX, Y2 = boxY, Width2 = boxWidth, Height2 = boxHeight;

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
    }

    public void settings() {
        size(800, 800); // Set the size of the window (top corner 0,0, bottom 800,800)

    }

    public void draw() {
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

    }

    public void mouseDragged() {

    }

    public void mouseReleased() {

    }

    boolean isOverlapping(float x, float Y, float Width, float Height, float x2, float Y2, float Width2, float Height2) {
        return (x < x2 + Width2 && x + Width > x2 &&  // Check horizontal overlap
                Y< Y2 + Height2 && Y + Height2 > Y2);   // Check vertical overlap
    }

    // if (isOverlappingt)) {
    //     fill(0); // Change fill color to black if overlapping
    //     textSize(32);
    //     text("Rectangles are overlapping!", 50, 50);
    // } else {
    //     fill(0);
    //     textSize(32);
    //     text("Rectangles are NOT overlapping.", 50, 50);
    // }





    public void mousePressed() {
        System.out.println("you clicked!");
        if (mouseX > boxX && mouseY > boxY && mouseX < boxX + boxWidth && mouseY < boxHeight + boxY) { // mouse has to be in the box
            System.out.println("box was clicked");
            clicked = !clicked;
            System.out.println(clicked);
        }

    }
}

