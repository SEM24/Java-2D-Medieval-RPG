package main.java.com.khomsi.game.entity.npc.sellers;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.objects.equipment.GoldShieldObject;
import main.java.com.khomsi.game.objects.equipment.MetalSwordObject;
import main.java.com.khomsi.game.objects.spells.PotionObject;

import java.awt.*;

public class NpcSeller1 extends Entity {
    public NpcSeller1(GameManager gameManager) {
        super(gameManager);
        name = "Vlad";
        direction = "down";
        speed = 0;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        //boundaries of npc
        solidArea.width = 31;
        solidArea.height = 32;
        getImage();
        setDialog();
        setItems();
    }

    public void getImage() {
        up = setup("/npc/gardener_down");
        up1 = setup("/npc/gardener_down_1");
        up2 = setup("/npc/gardener_down_2");
        up3 = setup("/npc/gardener_down_3");
        down = setup("/npc/gardener_down");
        down1 = setup("/npc/gardener_down_1");
        down2 = setup("/npc/gardener_down_2");
        down3 = setup("/npc/gardener_down_3");
        left = setup("/npc/gardener_down");
        left1 = setup("/npc/gardener_down_1");
        left2 = setup("/npc/gardener_down_2");
        left3 = setup("/npc/gardener_down_3");
        right = setup("/npc/gardener_down");
        right1 = setup("/npc/gardener_down_1");
        right2 = setup("/npc/gardener_down_2");
        right3 = setup("/npc/gardener_down_3");
    }

    @Override
    public void update() {
        super.update();
    }

    private void setDialog() {
        dialogues[0][0] = "Hi, I'm the seller.\nDo you want to buy something?";
        dialogues[1][0] = "See you later!";
        dialogues[2][0] = "You need more coins to buy it!";
        dialogues[3][0] = "Your inventory is full!";
        dialogues[4][0] = "You can't sell the equipped item!";
    }

    public void setItems() {
        inventory.add(new PotionObject(gameManager));
        inventory.add(new MetalSwordObject(gameManager));
        inventory.add(new GoldShieldObject(gameManager));
    }

    @Override
    public void speak() {
        facePlayer();
        gameManager.gameState = GameManager.TRADE_STATE;
        gameManager.ui.npc = this;
    }
}
