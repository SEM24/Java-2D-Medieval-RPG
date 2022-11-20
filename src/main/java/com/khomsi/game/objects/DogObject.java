package main.java.com.khomsi.game.objects;

import main.java.com.khomsi.game.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class DogObject extends SuperObject {
    public DogObject() {
        name = "Dog";
        try {
            image = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/objects/dog.png")));
            tools.scaledImage(image, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        } catch (IOException e) {
            System.err.println("Error in " + getClass().getSimpleName());
            e.printStackTrace();
        }
    }
}
