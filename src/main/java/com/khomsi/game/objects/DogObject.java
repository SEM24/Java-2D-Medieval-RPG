package main.java.com.khomsi.game.objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class DogObject extends SuperObject {

    public DogObject() {
        name = "Dog";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/dog.png"));
        } catch (IOException e) {
            System.err.println("Error in " + getClass().getSimpleName());
            e.printStackTrace();
        }
    }
}
