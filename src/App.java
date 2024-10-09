import processing.core.*;

public class App extends PApplet {
    int checkCount = 0;

    boolean clicked = false; // red moving block click
    boolean clicked2 = false; // blue moving box cick 
    boolean clicked3 = false; // yellow moving block click
    boolean clickstart = false;

    int boxX = 100; // red moving block
    int boxY = 675; // red moving block
    int boxWidth = 100;  // red moving block
    int boxHeight = 100;  // red moving block

    int boxXblue = 250; // blue moving box
    int boxYblue = 675;  // blue moving box
    int boxWidthblue = 100;  // blue moving box
    int boxHeightblue = 100;  // blue moving box

    int boxXyellow = 400; // yellow moving box
    int boxYyellow = 675;  // yellow moving box
    int boxWidthyellow = 100;  // yellow moving box
    int boxHeightyellow = 100;  // yellow moving box

    int startX = 325;
    int startY = 125;
    int startWidth = 200;
    int startHeight = 100;

    float orderboxX = 500, orderboxY = 15, orderboxWidth = 50, orderboxHeight = 50;
    float orderboxX2 = 560, orderboxY2 = 15, orderboxWidth2 = 50, orderboxHeight2 = 50;
    float rectX = 325, rectY = 300, rectWidth = 150, rectHeight = 150; // Place here box
    float rectX2 = 480, rectY2 = 300, rectWidth2nd = 150, rectHeight2nd = 150; // Place here box 2
    float rectWidth2 = boxWidth, rectHeight2 = boxHeight; // Moving block

    boolean isVisible = true; // A flag to control visibility
    int visibleDuration = 3000; // 3 seconds (3000 milliseconds)
    int startTime; // Variable to store the time when the shape became visible

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        startTime = millis(); // Record the time when the shape becomes visible
        background(108, 213, 217); // light lue grey background
        fill(209, 169, 199);
        rect(325, 125, 200, 100);
        fill (0);
        textSize(60);
        text("Start!",350,200);
      
    

       
    }

    public void settings() {
        size(800, 800); // Set the size of the window
    }

    public void draw() {
        if (clickstart = true) {
    gamestart();
}
       
    }

    public void mousetap () {
        if (mouseX > startX && mouseY > startY && mouseX < startX + startWidth && mouseY < startY + startHeight) {
            clickstart = true;
        }
    }

    public void mousePressed() {
        System.out.println("you clicked!");
        if (mouseX > boxX && mouseY > boxY && mouseX < boxX + boxWidth && mouseY < boxY + boxHeight) {
            clicked = !clicked; // Toggle clicked state
        }
        else if (mouseX > boxXblue && mouseY > boxYblue && mouseX < boxXblue + boxWidth && mouseY < boxYblue + boxHeight){ // second moving box
            clicked2 = !clicked2; 
        }
        else if (mouseX > boxXyellow && mouseY > boxYyellow && mouseX < boxXyellow + boxWidthyellow && mouseY < boxYyellow + boxHeightyellow){
            clicked3 = !clicked3;
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
    public void gamestart () {
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

        fill (0); // second half place here box
        rect(rectX2, rectY2, rectWidth2nd, rectHeight2nd);

        // Only draw the order box if isVisible is true
        if (isVisible) {
            fill(255, 69, 69); // Red color
            rect(orderboxX, orderboxY, orderboxWidth, orderboxHeight); // Order box
            fill (52, 161, 235);
            rect (orderboxX2, orderboxY2, orderboxWidth2, orderboxHeight2);
        }

            // Draw the moving box (boxX, boxY, boxWidth, boxHeight)
            fill(255, 69, 69); // moving box red
            rect(boxX, boxY, boxWidth, boxHeight); // Draw moving box 

           // Draw the moving box2 (boxX, boxY, boxWidth, boxHeight)
           fill (52, 161, 235); // moving box2 blue
           rect (boxXblue, boxYblue, boxWidthblue, boxHeightblue);

            // Draw moving boxYellow
            fill (237, 185, 43);
            rect (boxXyellow, boxYyellow, boxWidthyellow, boxHeightyellow);

        // Check if rectangles are overlapping
        if (isOverlapping(rectX, rectY, rectWidth, rectHeight, boxX, boxY, rectWidth2, rectHeight2)
                && checkCount == 0) {
            fill(0); // Change fill color to black if overlapping
            textSize(32);
            text("Rectangles are overlapping!", 80, 80);
             } else { fill(0); 
                textSize(32);
                text("Rectangles are NOT overlapping.", 80, 80);
             }
            if(isOverlapping(boxXblue, boxYblue, boxWidthblue,boxHeightblue, rectX2, rectY2, rectWidth2nd, rectHeight2nd)
                && checkCount == 0 ){
            fill (0); // Change fill color to black if overlapping
            textSize(32);
            text("Rectangles are overlapping!", 80, 120);
             } else {
                fill(0); 
                textSize(32);
                text("Rectangles are NOT overlapping.", 80, 120);
        }

        // Move the moving box if clicked
        if (clicked) {
            boxX = mouseX - boxWidth / 2;
            boxY = mouseY - boxHeight / 2;
        }
       else if (clicked2) { // second moving box
             boxXblue = mouseX - boxWidthblue / 2;
             boxYblue = mouseY - boxWidthblue / 2;
        }
        else if (clicked3) {
            boxXyellow = mouseX - boxWidthyellow / 2;
            boxYyellow = mouseY - boxWidthyellow / 2;
        }
    }
}



// import processing.core.*;

// public class App extends PApplet {
//     int checkCount = 0;

//     boolean clicked = false; // red moving block click
//     boolean clicked2 = false; // blue moving box cick 
//     boolean clicked3 = false; // yellow moving block click

//     int boxX = 100; // red moving block
//     int boxY = 675; // red moving block
//     int boxWidth = 100;  // red moving block
//     int boxHeight = 100;  // red moving block

//     int boxXblue = 250; // blue moving box
//     int boxYblue = 675;  // blue moving box
//     int boxWidthblue = 100;  // blue moving box
//     int boxHeightblue = 100;  // blue moving box

//     int boxXyellow = 400; // yellow moving box
//     int boxYyellow = 675;  // yellow moving box
//     int boxWidthyellow = 100;  // yellow moving box
//     int boxHeightyellow = 100;  // yellow moving box



//     float orderboxX = 500, orderboxY = 15, orderboxWidth = 50, orderboxHeight = 50;
//     float orderboxX2 = 560, orderboxY2 = 15, orderboxWidth2 = 50, orderboxHeight2 = 50;
//     float rectX = 325, rectY = 300, rectWidth = 150, rectHeight = 150; // Place here box
//     float rectX2 = 480, rectY2 = 300, rectWidth2nd = 150, rectHeight2nd = 150; // Place here box 2
//     float rectWidth2 = boxWidth, rectHeight2 = boxHeight; // Moving block

//     boolean isVisible = true; // A flag to control visibility
//     int visibleDuration = 3000; // 3 seconds (3000 milliseconds)
//     int startTime; // Variable to store the time when the shape became visible

//     public static void main(String[] args) {
//         PApplet.main("App");
//     }

//     public void setup() {
//         startTime = millis(); // Record the time when the shape becomes visible
//         background(99, 119, 122); // Blue grey background
        
//     }

//     public void settings() {
//         size(800, 800); // Set the size of the window
//     }

//     public void draw() {
//         background(99, 119, 122); // Clear the screen every frame

//         // Check if 5 seconds have passed since the shape became visible
//         int currentTime = millis();
//         if (currentTime - startTime > visibleDuration) {
//             isVisible = false; // Set visibility to false after 5 seconds
//         }

//         // Draw text and other set up elements
//         fill(0);
//         textSize(50);
//         text("Order:", 350, 50);

//         fill(0); // Black text
//         text("You have:", 50, 650);
//         text("Place here:", 75, 375);

//         // Draw the "place here" box
//         fill(0); // Black
//         rect(rectX, rectY, rectWidth, rectHeight);

//         fill (0); // second half place here box
//         rect(rectX2, rectY2, rectWidth2nd, rectHeight2nd);

//         // Only draw the order box if isVisible is true
//         if (isVisible) {
//             fill(255, 69, 69); // Red color
//             rect(orderboxX, orderboxY, orderboxWidth, orderboxHeight); // Order box
//             fill (52, 161, 235);
//             rect (orderboxX2, orderboxY2, orderboxWidth2, orderboxHeight2);
//         }

//             // Draw the moving box (boxX, boxY, boxWidth, boxHeight)
//             fill(255, 69, 69); // moving box red
//             rect(boxX, boxY, boxWidth, boxHeight); // Draw moving box 

//            // Draw the moving box2 (boxX, boxY, boxWidth, boxHeight)
//            fill (52, 161, 235); // moving box2 blue
//            rect (boxXblue, boxYblue, boxWidthblue, boxHeightblue);

//             // Draw moving boxYellow
//             fill (237, 185, 43);
//             rect (boxXyellow, boxYyellow, boxWidthyellow, boxHeightyellow);

//         // Check if rectangles are overlapping
//         if (isOverlapping(rectX, rectY, rectWidth, rectHeight, boxX, boxY, rectWidth2, rectHeight2)
//                 && checkCount == 0) {
//             fill(0); // Change fill color to black if overlapping
//             textSize(32);
//             text("Rectangles are overlapping!", 80, 80);
//              } else { fill(0); 
//                 textSize(32);
//                 text("Rectangles are NOT overlapping.", 80, 80);
//              }
//             if(isOverlapping(boxXblue, boxYblue, boxWidthblue,boxHeightblue, rectX2, rectY2, rectWidth2nd, rectHeight2nd)
//                 && checkCount == 0 ){
//             fill (0); // Change fill color to black if overlapping
//             textSize(32);
//             text("Rectangles are overlapping!", 80, 120);
//              } else {
//                 fill(0); 
//                 textSize(32);
//                 text("Rectangles are NOT overlapping.", 80, 120);
//         }
    


//         // Move the moving box if clicked
//         if (clicked) {
//             boxX = mouseX - boxWidth / 2;
//             boxY = mouseY - boxHeight / 2;
//         }
//        else if (clicked2) { // second moving box
//              boxXblue = mouseX - boxWidthblue / 2;
//              boxYblue = mouseY - boxWidthblue / 2;
//         }
//         else if (clicked3) {
//             boxXyellow = mouseX - boxWidthyellow / 2;
//             boxYyellow = mouseY - boxWidthyellow / 2;
//         }

//     }

//     public void mousePressed() {
//         System.out.println("you clicked!");
//         if (mouseX > boxX && mouseY > boxY && mouseX < boxX + boxWidth && mouseY < boxY + boxHeight) {
//             clicked = !clicked; // Toggle clicked state
//         }
//         else if (mouseX > boxXblue && mouseY > boxYblue && mouseX < boxXblue + boxWidth && mouseY < boxYblue + boxHeight){ // second moving box
//             clicked2 = !clicked2; 
//         }
//         else if (mouseX > boxXyellow && mouseY > boxYyellow && mouseX < boxXyellow + boxWidthyellow && mouseY < boxYyellow +boxHeightyellow){
//             clicked3 = !clicked3;
//         }
//     }

//     // Function to check if two rectangles are overlapping
//     boolean isOverlapping(float targetX, float targetY, float targetWidth, float targetHeight, float movingBoxX,
//             float movingBoxY, float movingBoxWidth, float movingBoxHeight) {
//         if (movingBoxX < targetX + targetWidth && movingBoxX + movingBoxWidth > targetX) {
//             if (movingBoxY < targetY + targetHeight && movingBoxY + movingBoxHeight > targetY) {
//                 return true;
//             }
//         }
//         return false;
//     }
//     public void game () {

//     }
// }



