package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Doll2 extends Enemy {

    private int speed;
    private int index;


    public Doll2(int x, int y, Image img) {
        super(x, y, img);
        index = 0;
    }

    @Override
    public void chooseSprite() {
        switch (direction) {
            case 0:
                img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, animate, 20).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.doll_right2, Sprite.doll_right3, animate, 20).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, animate, 20).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.doll_left2, Sprite.doll_left3, animate, 20).getFxImage();
                break;
            default:
                img = Sprite.doll_dead.getFxImage();
                break;
        }
    }

    @Override
    public boolean collide(Entity a) {
        if (a instanceof Bomber) {
            return true;
        }
        return false;
    }

}
