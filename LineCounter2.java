import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class LineCounter2 {
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
            int width = img.getWidth();
            int height = img.getHeight();
            int middleRow = height / 2;

            int count = 0;
            boolean inBlackLine = false;
            int tolerance = 10;             

            for (int x = 0; x < width; x++) {
                int rgb = img.getRGB(x, middleRow);

                // Checking one of red, green or blue channels because pixels are either black or white for sure
                // Therefore, its unnecessary to check all three channels
                int r = (rgb >> 16) & 0xFF;             
                boolean isBlack = (r <= tolerance);

                // Counting vertical lines
                if (isBlack && !inBlackLine) {
                    count++;
                    inBlackLine = true;
                } else if (!isBlack) {
                    inBlackLine = false;
                }
            }

            System.out.println(count);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
