package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tiles.Brick;
import uet.oop.bomberman.entities.Tiles.Grass;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

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
            Entity top = BombermanGame.map.getEntity(dx, dy - 1);
            Entity bot = BombermanGame.map.getEntity(dx, dy + 1);
            Entity left = BombermanGame.map.getEntity(dx - 1, dy);
            Entity right = BombermanGame.map.getEntity(dx + 1, dy);
            if (top instanceof Brick) {
                top.setBeDestroy(true);
            }
            if (!(top instanceof Brick) && BombermanGame.map.getObject(dx, dy - 1) instanceof Grass) {
                explodes.add(new Explode(dx, dy - 1, Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1,Sprite.explosion_vertical_top_last2, animate, 20).getFxImage()));
            }
            if (left instanceof Brick) {
                left.setBeDestroy(true);
            }
            if (!(left instanceof Brick) && BombermanGame.map.getObject(dx - 1, dy) instanceof Grass) {
                explodes.add(new Explode(dx - 1, dy, Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1,Sprite.explosion_horizontal_left_last2, animate, 20).getFxImage()));
            }
            if (right instanceof Brick) {
                right.setBeDestroy(true);
            }
            if (!(right instanceof Brick) && BombermanGame.map.getObject(dx + 1, dy) instanceof Grass) {
                explodes.add(new Explode(dx + 1, dy, Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1,Sprite.explosion_horizontal_right_last2, animate, 20).getFxImage()));
            }
            if (bot instanceof Brick) {
                bot.setBeDestroy(true);
            }
            if (!(bot instanceof Brick) && BombermanGame.map.getObject(dx, dy + 1) instanceof Grass) {
                explodes.add(new Explode(dx, dy + 1, Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, animate, 20).getFxImage()));
            }

//            explodes.add(new Explode(dx, dy + 1, Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, animate, 20).getFxImage()));
//            explodes.add(new Explode(dx + 1, dy, Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1,Sprite.explosion_horizontal_right_last2, animate, 20).getFxImage()));


            BombermanGame.map.addAllEntities(explodes);
        }
        if (time2 < 0) {
            Sound.play("D:\\OOP-Dic\\OOP_Bomber\\res\\sound\\BOM_11_M.wav");
            remove = true;
            BombermanGame.map.removeEntity(this);
        }


    }

}
