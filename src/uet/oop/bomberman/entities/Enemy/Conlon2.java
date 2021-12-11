package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Conlon2 extends Enemy {

    private int speed;
    private int index;


    public Conlon2(int x, int y, Image img) {
        super(x, y, img);
        index = 0;
        direction = 0;
        afterKill = 50;
        alive = true;
    }

    @Override
    public void chooseSprite() {
        switch (direction) {
            case 0:
                img = Sprite.movingSprite(Sprite.conlon_right1, Sprite.conlon_right2, animate, 20).getFxImage();

                break;
            case 1:
                img = Sprite.movingSprite(Sprite.conlon_right2, Sprite.conlon_right3, animate, 20).getFxImage();

                break;
            case 2:
                img = Sprite.movingSprite(Sprite.conlon_left1, Sprite.conlon_left2, animate, 20).getFxImage();

                break;
            case 3:
                img = Sprite.movingSprite(Sprite.conlon_left2, Sprite.conlon_left3, animate, 20).getFxImage();

                break;
            default:
                img = Sprite.conlon_dead.getFxImage();
                break;
        }
    }

    @Override
    public void killed() {
        if (afterKill > 0) {
            afterKill --;
            img = Sprite.movingSprite(Sprite.conlon_dead, Sprite.mob_dead1, Sprite.mob_dead2, animate, 20).getFxImage();
        }
        else
            remove = true;

    }
//    @Override
//    public boolean collide(Entity a) {
//        if (a instanceof Bomber) {
//            return true;
//        }
//        return false;
//    }

}

