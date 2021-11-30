package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    private int animate = 0;
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (animate < 7500) {
            animate ++;
        } else animate = 0;
        img = Sprite.movingSprite(Sprite.bomb_1, Sprite.bomb_2, animate, 20).getFxImage();
    }

    @Override
    public boolean collide(Entity a) {
//        if (a instanceof Bomber) {
//            return false;
//        }
        return false;
    }

}
