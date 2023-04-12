package main.java.com.khomsi.game.main.logic;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.objects.equipment.AxeObject;
import main.java.com.khomsi.game.objects.equipment.GoldShieldObject;
import main.java.com.khomsi.game.objects.equipment.MetalShieldObject;
import main.java.com.khomsi.game.objects.equipment.MetalSwordObject;
import main.java.com.khomsi.game.objects.gui.HeartObject;
import main.java.com.khomsi.game.objects.gui.ManaObject;
import main.java.com.khomsi.game.objects.interact.ChestObject;
import main.java.com.khomsi.game.objects.interact.CoinBObject;
import main.java.com.khomsi.game.objects.interact.KeyObject;
import main.java.com.khomsi.game.objects.light.LanternObject;
import main.java.com.khomsi.game.objects.outside.BedObject;
import main.java.com.khomsi.game.objects.outside.DoorObject;
import main.java.com.khomsi.game.objects.outside.TentObject;
import main.java.com.khomsi.game.objects.projectTiles.FireBallObject;
import main.java.com.khomsi.game.objects.projectTiles.MagicArrowObject;
import main.java.com.khomsi.game.objects.projectTiles.RockObject;
import main.java.com.khomsi.game.objects.spells.PotionObject;

public class EntityGenerator {
    GameManager gameManager;

    public EntityGenerator(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public Entity getObject(String itemNames) {
        Entity object = null;
        switch (itemNames) {
            case AxeObject.OBJ_NAME -> object = new AxeObject(gameManager);
            case GoldShieldObject.OBJ_NAME -> object = new GoldShieldObject(gameManager);
            case MetalShieldObject.OBJ_NAME -> object = new MetalShieldObject(gameManager);
            case MetalSwordObject.OBJ_NAME -> object = new MetalSwordObject(gameManager);
            case HeartObject.OBJ_NAME -> object = new HeartObject(gameManager);
            case ManaObject.OBJ_NAME -> object = new ManaObject(gameManager);
            case ChestObject.OBJ_NAME -> object = new ChestObject(gameManager);
            case CoinBObject.OBJ_NAME -> object = new CoinBObject(gameManager);
            case KeyObject.OBJ_NAME -> object = new KeyObject(gameManager);
            case LanternObject.OBJ_NAME -> object = new LanternObject(gameManager);
            case BedObject.OBJ_NAME -> object = new BedObject(gameManager);
            case DoorObject.OBJ_NAME -> object = new DoorObject(gameManager);
            case TentObject.OBJ_NAME -> object = new TentObject(gameManager);
            case FireBallObject.OBJ_NAME -> object = new FireBallObject(gameManager);
            case RockObject.OBJ_NAME -> object = new RockObject(gameManager);
            case MagicArrowObject.OBJ_NAME -> object = new MagicArrowObject(gameManager);
            case PotionObject.OBJ_NAME -> object = new PotionObject(gameManager);
        }
        return object;
    }
}
