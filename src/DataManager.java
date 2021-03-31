import java.awt.image.BufferedImage;

public class DataManager {

    BufferedImage img;

    int[][] pixelLast;


    int width;
    int height;

    public DataManager(int a, int b) {
        width=a;
        height=b;
        pixelLast = new int [width][height];

    }
}