package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Ballon2 extends Enemy {

    private int speed;
    private int index;


    public Ballon2(int x, int y, Image img) {
        super(x, y, img);
        index = 0;
        direction = 0;
    }

    @Override
    public void chooseSprite() {
        switch (direction) {
            case 0:
                img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, animate, 20).getFxImage();

                break;
            case 1:
                img = Sprite.movingSprite(Sprite.balloom_right2, Sprite.balloom_right3, animate, 20).getFxImage();

                break;
            case 2:
                img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, animate, 20).getFxImage();

                break;
            case 3:
                img = Sprite.movingSprite(Sprite.balloom_left2, Sprite.balloom_left3, animate, 20).getFxImage();

                break;
            default:
                img = Sprite.balloom_left1.getFxImage();
                break;
        }
    }

//    public boolean collide(Entity a) {
//        if ( a instanceof Bomber) {
//            ((Bomber) a).setAlive();
//        }
//        return false;
//    }

}

