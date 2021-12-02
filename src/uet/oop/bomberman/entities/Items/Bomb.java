package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class Bomb extends Entity {
    private int animate = 0;
    private int countTime;
    public static int explodeTime;
    private ArrayList<Entity> explodes;
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        countTime = 50;
        explodeTime = 20;
        explodes = new ArrayList<>();
    }

    @Override
    public void update() {
        countTime --;
        if (countTime < 0) {
            explodeTime --;
        }
        if (animate < 7500) {
            animate ++;
        } else animate = 0;
        img = Sprite.movingSprite(Sprite.bomb_1, Sprite.bomb_2, animate, 20).getFxImage();
        explode(countTime, explodeTime);
    }

    @Override
    public boolean collide(Entity a) {
//        if (a instanceof Bomber) {
//            return false;
//        }
        return false;
    }

    private void explode(int time, int time2) {
        if (time < 0) {
            int dx = (int) x / Sprite.SCALED_SIZE;
            int dy = (int) y / Sprite.SCALED_SIZE;
            img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, animate, 20).getFxImage();
            explodes.add(new Explode(dx, dy - 1, Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1,Sprite.explosion_vertical_top_last2, animate, 20).getFxImage()));
            explodes.add(new Explode(dx, dy + 1, Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, animate, 20).getFxImage()));
            explodes.add(new Explode(dx + 1, dy, Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1,Sprite.explosion_horizontal_right_last2, animate, 20).getFxImage()));
            explodes.add(new Explode(dx - 1, dy, Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1,Sprite.explosion_horizontal_left_last2, animate, 20).getFxImage()));

            BombermanGame.map.addAllEntities(explodes);
        }
        if (time2 < 0) {
            BombermanGame.map.removeEntity(this);
        }


    }

}
