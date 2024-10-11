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

    int triangleX1 = 100; // lvl 2 triangle red
    int triangleY1 = 775;
    int triangleX2 = 200;
    int triangleY2 = 775;
    int triangleX3 = 150;
    int triangleY3 = 675;

    int centroidX = (triangleX1 + triangleX2 + triangleX3) / 3;
    int centroidY = (triangleY1 + triangleY2 + triangleY3) / 3;   

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

    float rectWidth2 = boxWidth, rectHeight2 = boxHeight; // Moving block

    boolean isVisible = true; // A flag to control visibility
    int visibleDuration = 1000; // 1 second (1000 milliseconds)
    int startTime; // Variable to store the time when the shape became visible

    // Variables to store positions of the two rectangles
    float rect1X = 50, rect1Y = 100; // Initial position of Rectangle 1
    float rect2X = 700, rect2Y = 400; // Initial position of Rectangle 2

    // Variables to control the movement speed of the rectangles
    float speed1X = 2, speed1Y = 1; // Speed for Rectangle 1
    float speed2X = -2, speed2Y = -1; // Speed for Rectangle 2 (moving left and up)

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
    }

    public void settings() {
        size(800, 800); // Set the size of the window
    }

    public void draw() {
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
            gamestart(); // method called again and again
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
        // Check if 3 seconds have passed since the shape became visible
        int currentTime = millis();
        if (currentTime - startTime > visibleDuration) {
            isVisible = false; // Set visibility to false after 5 seconds
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
        } else if (mouseX > nextX && mouseY > nextY && mouseX < nextX + nextWidth && mouseY < nextY + nextHeight) {
            clicknext = !clicknext;
            System.out.println("you clicked next");
        } else if (mouseX > triangleX1 && mouseY > triangleY3 && mouseX < triangleX2) {
            clickedtriRed = !clickedtriRed;
        }
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
         if (isOverlapping(boxXblue, boxYblue, boxWidthblue, boxHeightblue, rectX2, rectY2, rectWidth2nd, rectHeight2nd)) {
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
        }
        // mousePressed ();
        if (clicknext == true) {
            TheNextlevel();
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
        gameSetup();
         // Draw the "place here" box
         fill(0); // Black
         rect(rectX, rectY, rectWidth, rectHeight);
 
         fill(0); // second place here box
         rect(rectX2, rectY2, rectWidth2nd, rectHeight2nd);
 
         fill(0); // third place here box
         rect(rectX3, rectY3, rectWidth3, rectHeight3);

         fill(255, 69, 69); //red
         triangle(triangleX1,triangleY1,triangleX2,triangleY2,triangleX3,triangleY3);

         fill(52, 161, 235); //blue
         triangle (blueTriangleX1,blueTriangleY1,blueTriangleX2,blueTriangleY2,blueTriangleX3,blueTriangleY3);

         fill(237, 185, 43); // yellow
         triangle (yellowTriangleX1,yellowTriangleY1,yellowTriangleX2,yellowtTriangleY2,yellowTriangleX3,yellowTriangleY3);

        
         if (clickedtriRed) {
             
            mouseX = centroidX;
            mouseY = centroidY;
        }
    }
   
    //  public float pointInTriangle (float x1, float y1, float x2, float y2, float x3, float y3, float x, float y) {
    
    // }
}
 // G ( x , y ) = ( x 1 + x 2 + x 3 3 , y 1 + y 2 + y 3 3 ) 
// CHATGPT What do i do with triangles ?
// public void draw() {
//     background(200);

//     // Triangle vertices
//     float x1 = 100, y1 = 100;
//     float x2 = 200, y2 = 300;
//     float x3 = 300, y3 = 150;

//     // Draw the triangle
//     fill(150, 150, 250);
//     triangle(x1, y1, x2, y2, x3, y3);

//     // Check if the mouse is inside the triangle
//     if (isMouseInTriangle(x1, y1, x2, y2, x3, y3)) {
//         fill(0); // Black color if inside
//         text("Mouse inside triangle!", 50, 50);
//     } else {
//         fill(255, 0, 0); // Red if outside
//         text("Mouse outside triangle", 50, 50);
//     }
// }

// // Function to calculate the area of a triangle given its three vertices
// float area(float x1, float y1, float x2, float y2, float x3, float y3) {
//     // Casting the result to float to avoid the double type
//     return abs((float) ((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0)); // abs is absulute value 
// }

// // Function to check if the mouse is inside a triangle
// boolean isMouseInTriangle(float x1, float y1, float x2, float y2, float x3, float y3) {
//     // Calculate the area of the main triangle
//     float A = area(x1, y1, x2, y2, x3, y3);

//     // Calculate the areas of the triangles formed by the mouse point
//     float A1 = area(mouseX, mouseY, x2, y2, x3, y3); // Mouse with two vertices of triangle
//     float A2 = area(x1, y1, mouseX, mouseY, x3, y3); // Another combination
//     float A3 = area(x1, y1, x2, y2, mouseX, mouseY); // Last combination

//     // Check if the sum of the smaller triangle areas is equal to the main triangle's area
//     return (A == A1 + A2 + A3);
// }
// }
// // chat gpt 
