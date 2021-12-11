package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Enemy.Enemy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tiles.Brick;
import uet.oop.bomberman.entities.Tiles.Grass;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.ArrayList;

public class Bomb extends Entity {
    private int animate = 0;
    private int countTime;
    private ArrayList<Entity> explodes;
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        countTime = 120;
        explodes = new ArrayList<>();
    }

    @Override
    public void update() {
        countTime --;
        if (animate < 7500) {
            animate ++;
        } else animate = 0;
        int dx = (int) x / Sprite.SCALED_SIZE;
        int dy = (int) y / Sprite.SCALED_SIZE;
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 60).getFxImage();
        if (countTime < 0 || BombermanGame.map.getEntity(dx, dy) instanceof Explode) {
            explode();
        }
    }

    @Override
    public boolean collide(Entity a) {
        if (a instanceof Explode) {
            this.explode();
        }
        if (a instanceof Enemy) {
            explode();
        }
        return false;
    }

    public void explode() {
        Sound.play("D:\\DEV_FILE\\OOP_Bomber\\res\\sound\\BOM_11_M.wav");
        int dx = (int) x / Sprite.SCALED_SIZE;
        int dy = (int) y / Sprite.SCALED_SIZE;
        remove = true;
        Entity top = BombermanGame.map.getEntity(dx, dy - 1);
        Entity bot = BombermanGame.map.getEntity(dx, dy + 1);
        Entity left = BombermanGame.map.getEntity(dx - 1, dy);
        Entity right = BombermanGame.map.getEntity(dx + 1, dy);
        Entity mid = BombermanGame.map.getEntity(dx, dy);
        if (mid instanceof Brick) {
            mid.setBeDestroy(true);
        }
        if (!(mid instanceof Brick) && BombermanGame.map.getObject(dx, dy) instanceof Grass) {
            explodes.add(new Explode(dx, dy, 4));
        }
        if (top instanceof Brick) {
            top.setBeDestroy(true);
        }
        if (!(top instanceof Brick) && BombermanGame.map.getObject(dx, dy - 1) instanceof Grass) {
            explodes.add(new Explode(dx, dy - 1, 0));
        }
        if (left instanceof Brick) {
            left.setBeDestroy(true);
        }
        if (!(left instanceof Brick) && BombermanGame.map.getObject(dx - 1, dy) instanceof Grass) {
            explodes.add(new Explode(dx - 1, dy, 3));
        }
        if (right instanceof Brick) {
            right.setBeDestroy(true);
        }
        if (!(right instanceof Brick) && BombermanGame.map.getObject(dx + 1, dy) instanceof Grass) {
            explodes.add(new Explode(dx + 1, dy, 1));
        }
        if (bot instanceof Brick) {
            bot.setBeDestroy(true);
        }
        if (!(bot instanceof Brick) && BombermanGame.map.getObject(dx, dy + 1) instanceof Grass) {
            explodes.add(new Explode(dx, dy + 1, 2));
        }

//            explodes.add(new Explode(dx, dy + 1, Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, animate, 20).getFxImage()));
//            explodes.add(new Explode(dx + 1, dy, Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1,Sprite.explosion_horizontal_right_last2, animate, 20).getFxImage()));


        BombermanGame.map.addAllExplodes(explodes);
    }

}
