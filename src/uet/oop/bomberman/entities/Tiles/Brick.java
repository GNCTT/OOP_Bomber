package uet.oop.bomberman.entities.Tiles;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Enemy.Enemy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Items.Bomb;
import uet.oop.bomberman.entities.Items.Explode;
import uet.oop.bomberman.entities.Items.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;

import javax.swing.*;

public class Brick extends Entity {

    private int animate;
    public boolean broken;
    private int timecount;
    public Brick(int x, int y, Image img) {
        super(x, y, img);
        animate = 0;
        broken = false;
        timecount = 20;
        remove = false;
        beDestroy = false;
    }

    @Override
    public void update() {
        int dx = (int) x / Sprite.SCALED_SIZE;
        int dy = (int) y / Sprite.SCALED_SIZE;
        if (animate < 7500) {
            animate ++;
        } else animate = 0;
        if (beDestroy) {
            timecount --;

            img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animate, 20).getFxImage();
            if (timecount < 0) {
                remove = true;
            }
        }
    }

    public void explode() {

    }

    @Override
    public boolean collide(Entity a) {
        if (a instanceof Explode || a instanceof Bomb || a instanceof Bomber || a instanceof Enemy) {
            return true;
        }
        return false;
    }

}
