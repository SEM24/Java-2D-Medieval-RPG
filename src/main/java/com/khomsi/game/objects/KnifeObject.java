package main.java.com.khomsi.game.objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class KnifeObject extends SuperObject {
    public KnifeObject() {
        name = "Knife";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/knife.png"));
        } catch (IOException e) {
            System.err.println("Error in " + getClass().getSimpleName());
            e.printStackTrace();
        }
    }
}
