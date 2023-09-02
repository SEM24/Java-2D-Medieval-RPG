package com.khomsi.game.main.logic;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;
import com.khomsi.game.objects.dungeon.DungeonDoorClosedObject;
import com.khomsi.game.objects.dungeon.DungeonDoorOpenedObject;
import com.khomsi.game.objects.equipment.*;
import com.khomsi.game.objects.inside.*;
import com.khomsi.game.objects.interact.ChestObject;
import com.khomsi.game.objects.interact.CoinBObject;
import com.khomsi.game.objects.interact.KeyObject;
import com.khomsi.game.objects.interact.MagicNecklaceObject;
import com.khomsi.game.objects.light.LanternObject;
import com.khomsi.game.objects.outside.DoorObject;
import com.khomsi.game.objects.outside.RoundTreeObject;
import com.khomsi.game.objects.outside.TentObject;
import com.khomsi.game.objects.projectTiles.FireBallObject;
import com.khomsi.game.objects.projectTiles.MagicArrowObject;
import com.khomsi.game.objects.projectTiles.RockObject;
import com.khomsi.game.objects.spells.HeartObject;
import com.khomsi.game.objects.spells.ManaObject;
import com.khomsi.game.objects.spells.PotionObject;

public class EntityGenerator {
    //TODO refactor this code since it's uncomfortable to use it (always needs to update switch)
    //Since it's not in one package(it's in subpackages, it has problems to implement)
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
            case StairsDownObject.OBJ_NAME -> object = new StairsDownObject(gameManager);
            case HookObject.OBJ_NAME -> object = new HookObject(gameManager);
            case DungeonDoorClosedObject.OBJ_NAME -> object = new DungeonDoorClosedObject(gameManager);
            case DungeonDoorOpenedObject.OBJ_NAME -> object = new DungeonDoorOpenedObject(gameManager);
            case RoundTreeObject.OBJ_NAME -> object = new RoundTreeObject(gameManager);
            case MagicNecklaceObject.OBJ_NAME -> object = new MagicNecklaceObject(gameManager);
            case MapObject.OBJ_NAME -> object = new MapObject(gameManager);
            case WindowObject.OBJ_NAME -> object = new WindowObject(gameManager);
            case PictureObject.OBJ_NAME -> object = new PictureObject(gameManager);
        }
        return object;
    }
}
