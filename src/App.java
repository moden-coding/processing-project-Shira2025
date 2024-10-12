import processing.core.*;

public class App extends PApplet {
    int nextlevel = 0;
    int score = 0;

    boolean clickstart = false; // detect the start button
    boolean clicknext = false; // next button

    boolean clicked = false; // red moving block click
    boolean clicked2 = false; // blue moving box cick
    boolean clicked3 = false; // yellow moving block click

    boolean clickedtriRed = false;
    boolean clickedtriBlue = false;
    boolean clickedtriYellow = false;

    boolean redOverlapping = false;
    boolean blueOverlapping = false;

    int boxX = 100; // red moving block
    int boxY = 675;
    int boxWidth = 100;
    int boxHeight = 100;

    int boxXblue = 250; // blue moving box
    int boxYblue = 675;
    int boxWidthblue = 100;
    int boxHeightblue = 100;

    int boxXyellow = 400; // yellow moving box
    int boxYyellow = 675;
    int boxWidthyellow = 100;
    int boxHeightyellow = 100;

    // int triangleX1 = 100; // lvl 2 triangle red
    // int triangleY1 = 775;
    // int triangleX2 = 200;
    // int triangleY2 = 775;
    // int triangleX3 = 150;
    // int triangleY3 = 675;

    // Triangle vertices
    float triangleX1 = 100, triangleY1 = 775;
    float triangleX2 = 200, triangleY2 = 775;
    float triangleX3 = 150, triangleY3 = 675;

    int blueTriangleX1 = 250; // lvl 2 triangle blue
    int blueTriangleY1 = 775;
    int blueTriangleX2 = 350;
    int blueTriangleY2 = 775;
    int blueTriangleX3 = 300;
    int blueTriangleY3 = 675;

    int yellowTriangleX1 = 400; // lvl 2 triangle yellow
    int yellowTriangleY1 = 775;
    int yellowTriangleX2 = 500;
    int yellowtTriangleY2 = 775;
    int yellowTriangleX3 = 450;
    int yellowTriangleY3 = 675;

    int startX = 325; // start box
    int startY = 125;
    int startWidth = 200;
    int startHeight = 100;

    int nextX = 650; // next box
    int nextY = 710;
    int nextWidth = 100;
    int nextHeight = 50;

    float orderboxX = 400, orderboxY = 15, orderboxWidth = 50, orderboxHeight = 50;
    float orderboxX2 = 460, orderboxY2 = 15, orderboxWidth2 = 50, orderboxHeight2 = 50;

    float rectX = 325, rectY = 300, rectWidth = 150, rectHeight = 150; // Place here box
    float rectX2 = 480, rectY2 = 300, rectWidth2nd = 150, rectHeight2nd = 150; // Place here box 2
    float rectX3 = 635, rectY3 = 300, rectWidth3 = 150, rectHeight3 = 150; // Place here box 2

    float blueTriX1 = 325, blueTriY1 = 100, blueTriX2 = 425, blueTriY2 = 100, blueTriX3 = 375, blueTriY3 = 10;
    float redTriX1 = 450, redTriY1 = 100, redTriX2 = 550, redTriY2 = 100, redTriX3 = 500, redTriY3 = 10;
    float yellowTriX1 = 575, yellowTriY1 = 100, yellowTriX2 = 675, yellowTriY2 = 100, yellowTriX3 = 625, yellowTriY3 = 10;

    float rectWidth2 = boxWidth, rectHeight2 = boxHeight; // Moving block

    boolean isVisible = true; // A flag to control visibility
    boolean isVisble2 = true; // A second flag to control visibility
    int visibleDuration = 1000; // 1 second (1000 milliseconds)
    int startTime; // Variable to store the time when the shape became visible
    int startTime2;

    // Variables to store positions of the two rectangles
    float rect1X = 50, rect1Y = 100; // Initial position of Rectangle 1
    float rect2X = 700, rect2Y = 400; // Initial position of Rectangle 2

    // Variables to control the movement speed of the rectangles
    float speed1X = 2, speed1Y = 1; // Speed for Rectangle 1
    float speed2X = -2, speed2Y = -1; // Speed for Rectangle 2 (moving left and up)

    int gameState = 0;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
    }

    public void settings() {
        size(800, 800); // Set the size of the window
    }

    public void draw() {

        if(gameState == 0){
            homePage();
            // gameSetup();
            //do all of the start screen stuff
        }else if(gameState == 1){
            gamestart(); 
            //show level 1 stuff
        }else if(gameState == 2){
            gamestart();
            //show level 1 stuff and show next button
            //make next button clickable
        }else if(gameState == 3){
            TheNextlevel();
            clicknext = false;
        }
    }

    public void homePage() {
        background(108, 213, 217); // Clear the screen every frame, light blue grey background
        fill(209, 169, 199); // pink
        rect(325, 125, 200, 100); // start box
        fill(0);
        textSize(60);
        text("Start!", 350, 200);
        textSize(32);
        text("Click once to drag box, click again to drop box", 100, 400);
        // Draw Rectangle 1
        fill(255, 182, 193); // Pale pink color
        rect(rect1X, rect1Y, 100, 100); // Rectangle 1

        // Draw Rectangle 2
        fill(255, 255, 204); // Pale yellow color
        rect(rect2X, rect2Y, 100, 100); // Rectangle 2

        // Move Rectangle 1 by updating its position
        rect1X += speed1X; // Move horizontally
        rect1Y += speed1Y; // Move vertically

        // Move Rectangle 2 by updating its position
        rect2X += speed2X; // Move horizontally
        rect2Y += speed2Y; // Move vertically

        // Bounce Rectangle 1 off the edges of the window
        if (rect1X < 0 || rect1X + 100 > width) {
            speed1X *= -1; // Reverse horizontal direction
        }
        if (rect1Y < 0 || rect1Y + 50 > height) {
            speed1Y *= -1; // Reverse vertical direction
        }

        // Bounce Rectangle 2 off the edges of the window
        if (rect2X < 0 || rect2X + 100 > width) {
            speed2X *= -1; // Reverse horizontal direction
        }
        if (rect2Y < 0 || rect2Y + 50 > height) {
            speed2Y *= -1; // Reverse vertical direction
        }
        if (clickstart) {
            gameState = 1;
            // method called again and again
        }
    }

    public void mouseTap() {
        if (clickstart == false && mouseX > startX && mouseY > startY && mouseX < startX + startWidth
                && mouseY < startY + startHeight) {
            clickstart = true;
            startTime = millis();
        }
    }

    public void gameSetup() {
        // background(99, 119, 122);
        fill(0);
        textSize(32);
        text("Score: " + score, 675, 100);
        // Check if 1 second have passed since the shape became visible
        int currentTime = millis();
        if (currentTime - startTime > visibleDuration) {
            isVisible = false; // Set visibility to false after 1 second
        }

        // Draw text and other set up elements
        fill(0);
        textSize(50);
        text("Order:", 200, 50);

        fill(0);
        text("You have:", 50, 650);
        text("Place here:", 75, 375);
    }

    public void mousePressed() {
        System.out.println("you clicked!");
        if (mouseX > boxX && mouseY > boxY && mouseX < boxX + boxWidth && mouseY < boxY + boxHeight) {
            clicked = !clicked; // Toggle clicked state
        } else if (mouseX > boxXblue && mouseY > boxYblue && mouseX < boxXblue + boxWidth
                && mouseY < boxYblue + boxHeight) { // second moving box
            clicked2 = !clicked2;
        } else if (mouseX > boxXyellow && mouseY > boxYyellow && mouseX < boxXyellow + boxWidthyellow
                && mouseY < boxYyellow + boxHeightyellow) {
            clicked3 = !clicked3;
        } else if (mouseX > nextX && mouseY > nextY && mouseX < nextX + nextWidth && mouseY < nextY + nextHeight
                && gameState == 2) {
            clicknext = !clicknext;
            System.out.println("you clicked next");
          }               
         // else if (isMouseInTriangle(triangleX1, triangleY1, triangleX2, triangleY2, triangleX3, triangleY3)) {
        //                         clickedtriRed = !clickedtriRed;
        //                     } else if (isMouseInTriangle(blueTriangleX1, blueTriangleY1, blueTriangleX2, blueTriangleY2, blueTriangleX3,
        //                             blueTriangleY3)) {
        //                         clickedtriBlue = !clickedtriBlue;
        //                     } else if (isMouseInTriangle(yellowTriangleX1, yellowTriangleY1, yellowTriangleX2, yellowtTriangleY2,
        //                             yellowTriangleX3, yellowTriangleY3)) {
        //                         clickedtriYellow = !clickedtriYellow;
        //                     }

        mouseTap();
    }

    // Function to check if two rectangles are overlapping
    public boolean isOverlapping(float targetX, float targetY, float targetWidth, float targetHeight, float movingBoxX,
            float movingBoxY, float movingBoxWidth, float movingBoxHeight) {
        if (movingBoxX < targetX + targetWidth && movingBoxX + movingBoxWidth > targetX) {
            if (movingBoxY < targetY + targetHeight && movingBoxY + movingBoxHeight > targetY) {
                return true;
            }
        }
        return false;
    }

                                    // // Function to check if triangle is overlapping a square
                                    // public boolean TriIsOverlapping(float targetX, float targetY, float targetWidth, float targetHeight, 
                                    //                                 float x1, float y1, float x2, float y2, float x3, float y3) {
                                    //     // Check if any of the triangle's vertices are inside the square
                                    //     if (isPointInsideSquare(targetX, targetY, targetWidth, targetHeight, x1, y1) || 
                                    //         isPointInsideSquare(targetX, targetY, targetWidth, targetHeight, x2, y2) ||
                                    //         isPointInsideSquare(targetX, targetY, targetWidth, targetHeight, x3, y3)) {
                                    //         return true;   
                                    //     }
                                    //     return false;
                                    // }

                                    // // Helper function to check if a point is inside a square
                                    // public boolean isPointInsideSquare(float squareX, float squareY, float squareWidth, float squareHeight, float px, float py) {
                                    //     return (px >= squareX && px <= squareX + squareWidth &&
                                    //             py >= squareY && py <= squareY + squareHeight);
                                    // }

                                    //     float area(float x1, float y1, float x2, float y2, float x3, float y3) {
                                    //         // Casting the result to float to avoid the double type
                                    //         return abs((float) ((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0)); // abs is absulute value
                                    //     }

                                    //     public boolean isMouseInTriangle(float x1, float y1, float x2, float y2, float x3, float y3) {
                                    //         float A = area(x1, y1, x2, y2, x3, y3);
                                    //         float A1 = area(mouseX, mouseY, x2, y2, x3, y3);
                                    //         float A2 = area(x1, y1, mouseX, mouseY, x3, y3);
                                    //         float A3 = area(x1, y1, x2, y2, mouseX, mouseY);
                                    //         return (A == A1 + A2 + A3);
                                    //     }

    public void gamestart() {
        background(99, 119, 122);
        gameSetup();
        // Draw the "place here" box
        fill(0); // Black
        rect(rectX, rectY, rectWidth, rectHeight);

        fill(0); // second place here box
        rect(rectX2, rectY2, rectWidth2nd, rectHeight2nd);

        fill(0); // third place here box
        rect(rectX3, rectY3, rectWidth3, rectHeight3);

        // Only draw the order box if isVisible is true
        if (isVisible) {
            fill(255, 69, 69); // Red color
            rect(orderboxX, orderboxY, orderboxWidth, orderboxHeight); // Order box red
            fill(52, 161, 235); // blue color
            rect(orderboxX2, orderboxY2, orderboxWidth2, orderboxHeight2); // order box blue
        }

        fill(255, 69, 69); // moving box red
        rect(boxX, boxY, boxWidth, boxHeight); // Draw moving box

        fill(52, 161, 235); // moving box2 blue
        rect(boxXblue, boxYblue, boxWidthblue, boxHeightblue);

        fill(237, 185, 43); // Draw moving boxYellow
        rect(boxXyellow, boxYyellow, boxWidthyellow, boxHeightyellow);

        // Check if rectangles are overlapping
        if (isOverlapping(rectX, rectY, rectWidth, rectHeight, boxX, boxY, rectWidth2, rectHeight2)) {
            fill(0); // Change fill color to black if overlapping
            textSize(32);
            text("The red rectangle is in the correct place", 80, 90);
            redOverlapping = true;
        } else {
            fill(0);
            textSize(32);
            text("The red rectangle is not in the correct place.", 80, 90);
        }
        if (isOverlapping(boxXblue, boxYblue, boxWidthblue, boxHeightblue, rectX2, rectY2, rectWidth2nd,
                rectHeight2nd)) {
            fill(0); // Change fill color to black if overlapping
            textSize(32);
            text("The blue rectangle is in the correct place", 80, 120);
            blueOverlapping = true;
        } else {
            fill(0);
            textSize(32);
            text("The blue rectangle is not in the corrct place", 80, 120);
        }

        // Move the moving box if clicked
        if (clicked) {
            boxX = mouseX - boxWidth / 2;
            boxY = mouseY - boxHeight / 2;
        } else if (clicked2) { // second moving box
            boxXblue = mouseX - boxWidthblue / 2;
            boxYblue = mouseY - boxWidthblue / 2;
        } else if (clicked3) { // third moving box
            boxXyellow = mouseX - boxWidthyellow / 2;
            boxYyellow = mouseY - boxWidthyellow / 2;
        }

        levelDone();
        if (score == 1) {
            fill(209, 169, 199);
            rect(nextX, nextY, nextWidth, nextHeight);
            fill(0);
            text("Next", 675, 750);
            gameState = 2;
        }
        // mousePressed ();
        if (clicknext == true) { // not here
            System.out.println("clicknext");
            gameState = 3;
        }

    }

    public void levelDone() { // after complete lvl: next
        if (redOverlapping == true && blueOverlapping == true) {
            score = +1;
            nextlevel = +1;
            text("Score: " + score, 675, 100);
        }
    }

    public void TheNextlevel() {
        background(123, 73, 156);
        int currentTime2 = millis();
        if (currentTime2 - startTime2 > visibleDuration) {
            isVisble2 = false; // Set visibility to false after 1 second
        }
        gameSetup();

        if (clicknext == true && mouseX > nextX && mouseY > nextY && mouseX < nextX + nextWidth
                && mouseY < nextY + nextHeight) { // if next is clicked
            startTime2 = millis(); // new start time
            isVisble2 = true; // boxes will be visible
            System.out.println("hiding the tri"); 
        }

        // Draw the "place here" box
        fill(0);
        rect(rectX, rectY, rectWidth, rectHeight);

        fill(0); // second place here box
        rect(rectX2, rectY2, rectWidth2nd, rectHeight2nd);

        fill(0); // third place here box
        rect(rectX3, rectY3, rectWidth3, rectHeight3);

        if (isVisble2) { // new count
            fill(255, 69, 69); // red
            triangle(redTriX1, redTriY2, redTriX2, redTriY2, redTriX3, redTriY3); // orderbox red
            fill(52, 161, 235); // blue
            triangle(blueTriX1, blueTriY1, blueTriX2, blueTriY2, blueTriX3, blueTriY3); // orderbox blue
            fill(237, 185, 43); // yellow
            triangle(yellowTriX1, yellowTriY1, yellowTriX2, yellowTriY1, yellowTriX3, yellowTriY3); // orderbox yellow
        }
        fill(255, 69, 69); // red
        triangle(triangleX1, triangleY1, triangleX2, triangleY2, triangleX3, triangleY3);

        fill(52, 161, 235); // blue
        triangle(blueTriangleX1, blueTriangleY1, blueTriangleX2, blueTriangleY2, blueTriangleX3, blueTriangleY3);

        fill(237, 185, 43); // yellow
        triangle(yellowTriangleX1, yellowTriangleY1, yellowTriangleX2, yellowtTriangleY2, yellowTriangleX3,
                yellowTriangleY3);

                // if (isMouseInTriangle(triangleX1, triangleY1, triangleX2, triangleY2, triangleX3, triangleY3)) {
                //     fill(0); // Black color if inside
                //     System.out.println ("Mouse inside triangle!");
                // } else {
                //     fill(255, 0, 0); // Red if outside
                //     System.out.println ("Mouse outside triangle");
                // }
                
                if (clickedtriRed) {
                    triangleX1 = mouseX - 50;
                    triangleY1 = mouseY + 50;
                    triangleX2 = mouseX + 50;
                    triangleY2 = mouseY + 50;
                    triangleX3 = mouseX;
                    triangleY3 = mouseY - 50;
                } else if (clickedtriBlue) {
                    blueTriangleX1 = mouseX - 50;
                    blueTriangleY1 = mouseY + 50;
                    blueTriangleX2 = mouseX + 50;
                    blueTriangleY2 = mouseY + 50;
                    blueTriangleX3 = mouseX;
                    blueTriangleY3 = mouseY - 50;
                }
                else if (clickedtriYellow) {
                    yellowTriangleX1 = mouseX - 50;
                    yellowTriangleY1 = mouseY + 50;
                    yellowTriangleX2 = mouseX + 50;
                    yellowtTriangleY2 = mouseY + 50;
                    yellowTriangleX3 = mouseX;
                    yellowTriangleY3 = mouseY - 50;
                }
     }
}

