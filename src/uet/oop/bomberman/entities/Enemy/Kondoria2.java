package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Kondoria2 extends Enemy {

    private int speed;
    private int index;


    public Kondoria2(int x, int y, Image img) {
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
                img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, animate, 20).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.kondoria_right2, Sprite.kondoria_right3, animate, 20).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, animate, 20).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.kondoria_left2, Sprite.kondoria_left3, animate, 20).getFxImage();
                break;
            default:
                img = Sprite.kondoria_dead.getFxImage();
                break;
        }
    }

    @Override
    public void killed() {
        if (afterKill > 0) {
            afterKill--;
            img = Sprite.movingSprite(Sprite.kondoria_dead, Sprite.mob_dead1, Sprite.mob_dead2, animate, 20).getFxImage();
        }
        else {
            remove = true;
        }

    }

//    @Override
//    public boolean collide(Entity a) {
//        if (a instanceof Bomber) {
//            return true;
//        }
//        return false;
//    }

}

