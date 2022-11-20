package main.java.com.khomsi.game.objects;

import main.java.com.khomsi.game.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class KnifeObject extends SuperObject {

    public KnifeObject() {
        name = "Knife";
        try {
            image = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/objects/knife.png")));
            tools.scaledImage(image, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        } catch (IOException e) {
            System.err.println("Error in " + getClass().getSimpleName());
            e.printStackTrace();
        }
    }
}
