package main.java.com.khomsi.game.objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class KeyObject extends SuperObject {
    public KeyObject() {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        } catch (IOException e) {
            System.err.println("Error in " + getClass().getSimpleName());
            e.printStackTrace();
        }
    }
}
