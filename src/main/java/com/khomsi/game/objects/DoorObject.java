package main.java.com.khomsi.game.objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class DoorObject extends SuperObject {

    public DoorObject() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        } catch (IOException e) {
            System.err.println("Error in " + getClass().getSimpleName());
            e.printStackTrace();
        }
    }
}
