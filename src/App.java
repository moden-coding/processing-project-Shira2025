import processing.core.*;

public class App extends PApplet {
    int checkCount = 0;
    boolean clicked = false;
    int boxX = 100;
    int boxY = 675;
    int boxWidth = 100;
    int boxHeight = 100;

    float orderboxX = 500, orderboxY = 15, orderboxWidth = 50, orderboxHeight = 50;
    float rectX = 325, rectY = 300, rectWidth = 150, rectHeight = 150; // Place here box
    float rectWidth2 = boxWidth, rectHeight2 = boxHeight; // Moving block

    boolean isVisible = true; // A flag to control visibility
    int visibleDuration = 5000; // 5 seconds (5000 milliseconds)
    int startTime; // Variable to store the time when the shape became visible

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        startTime = millis(); // Record the time when the shape becomes visible
        background(99, 119, 122); // Blue grey background
        fill(0);
        textSize(50);
        text("order", 350, 50);
        fill(255, 69, 69);
        rect(500, 25, 50, 50); // Red box
    }

    public void settings() {
        size(800, 800); // Set the size of the window
    }

    public void draw() {
        background(99, 119, 122); // Clear the screen every frame

        // Check if 5 seconds have passed since the shape became visible
        int currentTime = millis();
        if (currentTime - startTime > visibleDuration) {
            isVisible = false; // Set visibility to false after 5 seconds
        }

        // Draw text and other set up elements
        fill(0);
        textSize(50);
        text("Order:", 350, 50);

        fill(0); // Black text
        text("You have:", 50, 650);
        text("Place here:", 75, 375);

        // Draw the "place here" box
        fill(0); // Black
        rect(rectX, rectY, rectWidth, rectHeight);

        // Only draw the order box if isVisible is true
        if (isVisible) {
            fill(255, 69, 69); // Red color
            rect(orderboxX, orderboxY, orderboxWidth, orderboxHeight); // Order box
        }

        // Draw the moving box (boxX, boxY, boxWidth, boxHeight)
        fill(255, 69, 69); // moving box
        rect(boxX, boxY, boxWidth, boxHeight); // Draw moving box

        // Check if rectangles are overlapping
        if (isOverlapping(rectX, rectY, rectWidth, rectHeight, boxX, boxY, rectWidth2, rectHeight2)
                && checkCount == 0) {
            fill(0); // Change fill color to black if overlapping
            textSize(32);
            text("Rectangles are overlapping!", 80, 80);
        } else {
            fill(0);
            textSize(32);
            text("Rectangles are NOT overlapping.", 80, 80);
        }

        // Move the moving box if clicked
        if (clicked) {
            boxX = mouseX - boxWidth / 2;
            boxY = mouseY - boxHeight / 2;
        }

    }

    public void mousePressed() {
        System.out.println("you clicked!");
        if (mouseX > boxX && mouseY > boxY && mouseX < boxX + boxWidth && mouseY < boxY + boxHeight) {
            clicked = !clicked; // Toggle clicked state
        }
    }

    // Function to check if two rectangles are overlapping
    boolean isOverlapping(float targetX, float targetY, float targetWidth, float targetHeight, float movingBoxX,
            float movingBoxY, float movingBoxWidth, float movingBoxHeight) {
        if (movingBoxX < targetX + targetWidth && movingBoxX + movingBoxWidth > targetX) {
            if (movingBoxY < targetY + targetHeight && movingBoxY + movingBoxHeight > targetY) {
                return true;
            }
        }
        return false;
    }
}