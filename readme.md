## How to use the program

Download the files called LineCounter.java and LineCounter2.java to your local system. Ensure you have Java installed on your system. Ensure you have images with the .jpg extension to test the program with.

To run the program, first compile it using:
```bash
javac LineCounter.java
```

Then run it with:
```bash
java LineCounter <absolute_image_path>
```

Example:
```bash
java LineCounter "C:\TMMC_interview_assignment\img_1.jpg"
```

Do similarly for LineCounter2.java to run the LineCounter2 program.

## My Approach 

When we visualize the entire image as a matrix of pixels with each cell having either one of two values i.e. black and white. I convert the image into a 2d grid of 1s and 0s. 1 representing black and 0 representing white. Then, I use BFS (Breadth First Search) Algorithm approach to find the number of islands of 1s in a sea of 0s. We only consider up, down, left, right as connected cells and diagonal cells as not connected. That is 4-Connectivity (No Diagonal). The answer could be extended to 8-Connectivity. However, I did not think that was necessary since we are dealing with vertical lines and not zig-zag.

Step 1: Reading the image by loading the .jpg image from the given absolute path into a BufferedImage object.

Step 2: Converting to binary grid. For each pixel, marking 1 if black, else 0, creating a 2D grid.

Step 3: Counting black regions by using BFS to find and count connected groups of 1s, marking visited pixels.

## More Efficient Solution (LineCounter2.java)

NOTE: This solution will work if the following assumption is true.
ASSUMPTION: Every vertical line would cross through the vertical center of the image. 

In the question, it says "The same line will exist on both the top half of the image and the bottom half as a continuous straight line." 


#### My Approach for LineCounter2.java

If we look at the middle row of the image. You could think of it as a 1d array/list. Connected/adjacent black cells represent one line. If there is a white cell between the two black cells, then that is a separation between two lines. So the question comes down to counting the number of subarrays with all black cells.

Step 1: Reading the image by loading the .jpg image from the given absolute path into a BufferedImage object.

Step 2: Scanning the middle row and checking each pixel to determine if it’s black (red ≤ tolerance) or white.

Step 3: Counting vertical lines by incrementing the count when a new black segment starts, using a flag to track line boundaries.

