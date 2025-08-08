import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.Exception;

public class LineCounter {

    // Directions for neighbors (up, down, left, right)
    private static final int[][] DIRECTIONS = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static void main(String[] args) {

        // Checking if the number of arguments is correct i.e. 1
        if (args.length != 1) {
            System.out.println("Error: Invalid number of arguments");
            return;
        }

        try {
            File imgFile = new File(args[0]);
            
            // Checking if the file is an absolute path
            if (!imgFile.isAbsolute()) {
                System.out.println("Error: Please provide an absolute path of the image.");
                return;
            }

            // Checking if the file has a .jpg extension
            if (!args[0].toLowerCase().endsWith(".jpg")) {
                System.out.println("Error: Only .jpg images are supported.");
                return;
            }

            // Checking if the file exists
            if (!imgFile.exists()) {
                System.out.println("Error: File does not exist.");
                return;
            }

            // Reading the image
            BufferedImage img = ImageIO.read(imgFile);
            
            // Counting vertical lines
            int[][] grid = imageToBinaryGrid(img);
            int count = countIslands(grid);
            System.out.println(count);

        } catch (Exception e) {
            System.out.println("Error: Failed to read image - " + e.getMessage());
        }
    }

    // Converting image pixels to 2D int array, 1 = black, 0 = white
    private static int[][] imageToBinaryGrid(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int[][] grid = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = img.getRGB(x, y);

                // Checking one of red, green or blue channels because pixels are either black or white for sure
                // Therefore, its unnecessary to check all three channels
                int r = (rgb >> 16) & 0xFF;

                int tolerance = 10; 

                // If red channel is below or equal to tolerance, consider pixel black (1), else white (0)
                grid[y][x] = (r <= tolerance) ? 1 : 0;

            }
        }
        return grid;
    }

    // Counting number of islands (connected 1s) using BFS 
    private static int countIslands(int[][] grid) {
        int count = 0;
        int rows = grid.length;
        if (rows == 0) return 0;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1 && !visited[r][c]) {
                    bfs(grid, visited, r, c);
                    count++;
                }
            }
        }
        return count;
    }

    private static void bfs(int[][] grid, boolean[][] visited, int startR, int startC) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC});
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];

            for (int[] dir : DIRECTIONS) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                        && grid[nr][nc] == 1 && !visited[nr][nc]) {
                    queue.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
    }
}
