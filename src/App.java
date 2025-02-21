import processing.core.*;

public class App extends PApplet {
    int nextlevel = 0;
    int score = 0;

    final int draggingRed = 1; // final means cant change value
    final int draggingBlue = 2;
    final int draggingYellow = 3;
    int draggingColor = 0; // not dragging anything

    boolean clickstart = false; // detect the start button
    boolean clicknext = false; // next button

    boolean clickedTriRed = false; // triangle clickes
    boolean clickedTriBlue = false;
    boolean clickedTriYellow = false;

    boolean redOverlapping = false; // if in target area
    boolean blueOverlapping = false; // if in target area

    boolean TriangleBlueOverlapping = false;
    boolean TriangleRedOverlapping = false;
    boolean TriangleYellowOverlapping = false;

    int boxXred = 100; // red moving block
    int boxYred = 675;
    int boxWidth = 100; // same for all 3 boxes
    int boxHeight = 100;

    int boxXblue = 250; // blue moving box
    int boxYblue = 675;

    int boxXyellow = 400; // yellow moving box
    int boxYyellow = 675;

    final int base = 100; // triangles base
    final int height = 100; // triangle height

    int redTriangleX1 = 100; // lvl 2 triangle red
    int redTriangleY1 = 775;

    int blueTriangleX1 = 250; // lvl 2 triangle blue
    int blueTriangleY1 = 775;

    int yellowTriangleX1 = 400; // lvl 2 triangle yellow
    int yellowTriangleY1 = 775;

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
    float rectX2 = 480, rectY2 = 300; // Place here box 2
    float rectX3 = 635, rectY3 = 300; // Place here box 3

    float blueTriX1 = 325, blueTriY1 = 100, blueTriX2 = 425, blueTriY2 = 100, blueTriX3 = 375, blueTriY3 = 10; // orders
    float redTriX1 = 450, redTriY1 = 100, redTriX2 = 550, redTriY2 = 100, redTriX3 = 500, redTriY3 = 10;
    float yellowTriX1 = 575, yellowTriY1 = 100, yellowTriX2 = 675, yellowTriY2 = 100, yellowTriX3 = 625,
            yellowTriY3 = 10;

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

    int triangleCorrect = 0;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
    }

    public void settings() {
        size(800, 800); // Set the size of the window
    }

    public void draw() { // home base instructions

        if (gameState == 0) {
            homePage();
            homePageInstructions();
            startTime();
            // do all of the start screen stuff
        } else if (gameState == 1) {
            boxLevel();
            // show level 1 stuff
        } else if (gameState == 2) { // complete level 1
            gameSetup();
            boxLevel();
            // show level 1 stuff and show next button
            // make next button clickable
            // there are two steps for a level setup, and setup do next
        } else if (gameState == 3) {
            TriangleLevel(); // next level set up
            clicknext = false;
        }

    }

    public void MyTriangle(float triX, float triY) { // making triangles easier to be made
        triangle(triX, triY, triX + base, triY, triX + base / 2, triY - height);
    }

    public void homePageInstructions() { // setup for homepage
        if (clickstart && gameState == 0) {
            fill(237, 40, 188); // diff pink
        } else
            fill(209, 169, 199); // pink
        rect(325, 125, 200, 100); // start box
        fill(0);
        textSize(60);
        text("Start!", 350, 200);
        textSize(32);
        text("Click once to drag box, click again to drop box", 100, 400);
    }

    public void homePage() { // home page decor
        background(108, 213, 217); // Clear the screen every frame, light blue grey background
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
        if (rect1Y < 0 || rect1Y + 100 > height) {
            speed1Y *= -1; // Reverse vertical direction
        }

        // Bounce Rectangle 2 off the edges of the window
        if (rect2X < 0 || rect2X + 100 > width) {
            speed2X *= -1; // Reverse horizontal direction
        }
        if (rect2Y < 0 || rect2Y + 100 > height) {
            speed2Y *= -1; // Reverse vertical direction
        }
    }

    public void startTime() { // staring time counter
        startTime = millis();
        System.out.println("click start");
    }

    public void gameSetup() { // Draw text and other set up elements
        background(99, 119, 122);
        fill(0);
        textSize(32);
        text("Score: " + score, 675, 100);
        // Check if 1 second have passed since the shape became visible
        int currentTime = millis();
        if (currentTime - startTime > visibleDuration) {
            isVisible = false; // Set visibility to false after 1 second
        }

        fill(0);
        textSize(50);
        text("Order:", 200, 50);

        fill(0);
        text("You have:", 50, 650);
        text("Place here:", 75, 375);

        fill(0); // Black
        rect(rectX, rectY, rectWidth, rectHeight);

        fill(0); // second place here box
        rect(rectX2, rectY2, rectWidth, rectHeight);

        fill(0); // third place here box
        rect(rectX3, rectY3, rectWidth, rectHeight);
    }

    public void mousePressed() { // this is the detection of all clicked things (next,moving blocks, and start)
        System.out.println("you clicked!");
        if (gameState == 0) {
            pressedInState0();
        } else if (gameState == 1) {
            pressedInState1();
        } else if (gameState == 2) {
            pressedInState2();
        } else if (gameState == 3) {
            pressedInState3();
        }
    }

    public void mouseReleased() { // this makes the actions of anything happen after the mouse is lifted
        if (gameState == 0 && clickstart) {
            gameState = 1;
            System.out.println("mouseReleased in state 0");
        } else if (gameState == 1 && redOverlapping && blueOverlapping) {
            score = +1;
            nextlevel += 1;
            text("Score: " + score, 675, 100);
        }
        if (isTriangleEntirellyInSquare(blueTriangleX1, blueTriangleY1, base, height, rectX, rectY, rectWidth,
                rectHeight)) {
            TriangleBlueOverlapping = true;
            System.out.println(" point is square red" + triangleCorrect);
        }
        if (isTriangleEntirellyInSquare(redTriangleX1, redTriangleY1, base, height, rectX2, rectY2, rectWidth,
                rectHeight)) {
            TriangleRedOverlapping = true;
            System.out.println(" point is square blue" + triangleCorrect);
        }
        if (isTriangleEntirellyInSquare(yellowTriangleX1, yellowTriangleY1, base, height, rectX3, rectY3, rectWidth,
                rectHeight)) {
            TriangleYellowOverlapping = true;
            System.out.println(" point is square yellow" + triangleCorrect);
        }
        if (TriangleBlueOverlapping && TriangleRedOverlapping && TriangleYellowOverlapping) {
            score += 1;
        }
    }

    public void pressedInState0() {
        if (clickstart == false && mouseX > startX && mouseY > startY && mouseX < startX + startWidth
                && mouseY < startY + startHeight) {
            clickstart = true;
        }
    }

    public void pressedInState1() { // first level box stuff
        if (draggingColor != 0) {
            draggingColor = 0;
            return;
        }
        if (mouseX > boxXred && mouseY > boxYred && mouseX < boxXred + boxWidth && mouseY < boxYred + boxHeight) {
            draggingColor = draggingRed; // Toggle clicked state
        } else if (mouseX > boxXblue && mouseY > boxYblue && mouseX < boxXblue + boxWidth
                && mouseY < boxYblue + boxHeight) { // second moving box first lvl
            draggingColor = draggingBlue;
        } else if (mouseX > boxXyellow && mouseY > boxYyellow && mouseX < boxXyellow + boxWidth
                && mouseY < boxYyellow + boxHeight) { // first lvl
            draggingColor = draggingYellow;
        }

    }

    public void pressedInState2() { // next button stuff
        if (mouseX > nextX && mouseY > nextY && mouseX < nextX + nextWidth && mouseY < nextY + nextHeight
                && gameState == 2) {// next level
            clicknext = !clicknext;
            System.out.println("you clicked next");
        }
    }

    public void pressedInState3() { // level two triangle stuff
        if (gameState == 3 && get(mouseX, mouseY) == color(255, 69, 69)) {
            System.out.println("red selected");
            clickedTriRed = !clickedTriRed;
        } else if (gameState == 3 && get(mouseX, mouseY) == color(52, 161, 235)) {
            clickedTriBlue = !clickedTriBlue;
        } else if (gameState == 3 && get(mouseX, mouseY) == color(237, 185, 43)) {
            clickedTriYellow = !clickedTriYellow;
        }
    }

    public boolean isOverlapping(float targetX, float targetY, float targetWidth, float targetHeight, float movingBoxX,
            float movingBoxY, float movingBoxWidth, float movingBoxHeight) { // Function to check if two rectangles are
                                                                             // overlapping
        if (movingBoxX < targetX + targetWidth && movingBoxX + movingBoxWidth > targetX) {
            if (movingBoxY < targetY + targetHeight && movingBoxY + movingBoxHeight > targetY) {
                return true;
            }
        }
        return false;
    }

    public void placeHereBox() { // Draw the "place here" box
        fill(0); // Black
        rect(rectX, rectY, rectWidth, rectHeight);

        fill(0); // second place here box
        rect(rectX2, rectY2, rectWidth, rectHeight);

        fill(0); // third place here box
        rect(rectX3, rectY3, rectWidth, rectHeight);
    }

    public void boxLevel() { // running lvl 1
        placeHereBox();
        gameSetup();
        // Only draw the order box if isVisible is true
        if (isVisible) {
            fill(255, 69, 69); // Red color
            rect(orderboxX, orderboxY, orderboxWidth, orderboxHeight); // Order box red
            fill(52, 161, 235); // blue color
            rect(orderboxX2, orderboxY2, orderboxWidth2, orderboxHeight2); // order box blue
        }
        fill(255, 69, 69); // moving box red
        rect(boxXred, boxYred, boxWidth, boxHeight); // Draw moving box

        fill(52, 161, 235); // moving box2 blue
        rect(boxXblue, boxYblue, boxWidth, boxHeight);

        fill(237, 185, 43); // Draw moving boxYellow
        rect(boxXyellow, boxYyellow, boxWidth, boxHeight);

        // Check if rectangles are overlapping
        if (isOverlapping(rectX, rectY, rectWidth, rectHeight, boxXred, boxYred, rectWidth, rectHeight)) {
            fill(0);
            textSize(32);
            text("The red rectangle is in the correct place", 80, 90);
            redOverlapping = true;
        } else {
            fill(0);
            textSize(32);
            text("The red rectangle is not in the correct place.", 80, 90);
        }
        if (isOverlapping(boxXblue, boxYblue, boxWidth, boxHeight, rectX2, rectY2, rectWidth,
                rectHeight)) {
            textSize(32);
            text("The blue rectangle is in the correct place", 80, 120);
            blueOverlapping = true;
        } else {
            fill(0);
            textSize(32);
            text("The blue rectangle is not in the corrct place", 80, 120);
        }

        // Move the moving box if clicked
        if (draggingColor == draggingRed) {
            boxXred = mouseX - boxWidth / 2;
            boxYred = mouseY - boxHeight / 2;
        } else if (draggingColor == draggingBlue) { // second moving box
            boxXblue = mouseX - boxWidth / 2;
            boxYblue = mouseY - boxHeight / 2;
        } else if (draggingColor == draggingYellow) { // third moving box
            boxXyellow = mouseX - boxWidth / 2;
            boxYyellow = mouseY - boxWidth / 2;
        }
        if (score == 1) {
            fill(209, 169, 199);
            rect(nextX, nextY, nextWidth, nextHeight);
            fill(0);
            text("Next", 675, 750);
            gameState = 2;
        }
        if (clicknext == true) { // not here
            System.out.println("clicknext");
            gameState = 3;
        }
    }

    public void TriangleLevel() {
        background(123, 73, 156);
        int currentTime2 = millis();
        if (currentTime2 - startTime2 > visibleDuration) {
            isVisble2 = false; // order will be invisible
        }
        gameSetup();

        if (clicknext == true && mouseX > nextX && mouseY > nextY && mouseX < nextX + nextWidth
                && mouseY < nextY + nextHeight) { // if next is clicked
            startTime2 = millis(); // new start time
            isVisble2 = true; // order will be visible
            System.out.println("hiding the tri");
        }
        placeHereBox();

        if (isVisble2) { // new count
            fill(255, 69, 69); // red
            MyTriangle(redTriX1, redTriY2); // orderbox's
            fill(52, 161, 235); // blue
            MyTriangle(blueTriX1, blueTriY1);
            fill(237, 185, 43); // yellow
            MyTriangle(yellowTriX1, yellowTriY1);
        }
        fill(255, 69, 69); // red
        MyTriangle(redTriangleX1, redTriangleY1);

        fill(52, 161, 235); // blue
        MyTriangle(blueTriangleX1, blueTriangleY1);

        fill(237, 185, 43); // yellow
        MyTriangle(yellowTriangleX1, yellowTriangleY1);

        if (clickedTriRed == true) {
            fill(255, 69, 69); // red
            redTriangleX1 = mouseX - 50;
            redTriangleY1 = mouseY + 50;
            MyTriangle(redTriangleX1, redTriangleY1);
        } else if (clickedTriBlue) {
            fill(52, 161, 235); // blue
            blueTriangleX1 = mouseX - 50;
            blueTriangleY1 = mouseY + 50;
            MyTriangle(blueTriangleX1, blueTriangleY1);
        } else if (clickedTriYellow) {
            fill(237, 185, 43); // yellow
            yellowTriangleX1 = mouseX - 50;
            yellowTriangleY1 = mouseY + 50;
            MyTriangle(yellowTriangleX1, yellowTriangleY1);
        }
    }

    public boolean isTriangleEntirellyInSquare(float triangleX, float triangleY, float triangleBase,
            float triangleHeight,
            float rectX, float rectY, float rectWidth, float rectHeight) {
        float x2 = triangleX + triangleBase;
        float y2 = triangleY;
        float x3 = triangleX + triangleBase / 2;
        float y3 = triangleY - height;

        if (isPointInSquare(triangleX, triangleY, rectX, rectY, rectWidth, rectHeight) &&
                isPointInSquare(x2, y2, rectX, rectY, rectWidth, rectHeight) &&
                isPointInSquare(x3, y3, rectX, rectY, rectWidth, rectHeight)) {
            return true;
        }
        return false;
    }

    public boolean isPointInSquare(float X, float Y, float boxX, float boxY, float boxWidth, float boxHeight) {
        if (X >= boxX && Y >= boxY && X <= boxX + boxWidth && Y <= boxY + boxHeight) {
            return true;
        }
        return false;
    }
}
// = assign
// == compare