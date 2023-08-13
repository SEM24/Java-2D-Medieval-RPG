//package com.khomsi.game.objects;
//
//import com.khomsi.game.entity.Entity;
//import com.khomsi.game.entity.player.Player;
//
//public class OBJ_ReturnOrb extends Entity {
//    GamePanel gp;
//    public static final String objName = "Return Orb";
//
//    public OBJ_ReturnOrb(GamePanel gp) {
//        super(gp);
//        this.gp = gp;
//        this.type = 6;
//        this.name = "Return Orb";
//        gp.getClass();
//        gp.getClass();
//        this.down1 = this.setup("/objects/returnorb", 48, 48);
//        this.description = "[Return Orb]\nTeleport to the\nsave point.";
//        this.price = 5;
//        this.stackable = true;
//        this.setDialogue();
//    }
//
//    public void setDialogue() {
//        this.dialogues[0][0] = "You use the " + this.name + "!";
//    }
//
//    public boolean use(Entity entity) {
//        this.startDialogue(this, 0);
//        this.gp.playSE(2);
//        GamePanel var10000 = this.gp;
//        this.gp.getClass();
//        var10000.nextArea = 50;
//        this.gp.currentMap = 0;
//        Player var2 = this.gp.player;
//        this.gp.getClass();
//        var2.worldX = 48 * 23;
//        var2 = this.gp.player;
//        this.gp.getClass();
//        var2.worldY = 48 * 13;
//        this.gp.changeArea();
//        return true;
//    }
//}