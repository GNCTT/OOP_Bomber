package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tiles.Grass;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Entity {

    public SpeedItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity a) {
        if (a instanceof Bomber) {
            this.remove = true;
            ((Bomber) a).setSpeed();
//            BombermanGame.map.addObject(new Grass((int) (x / Sprite.SCALED_SIZE), (int) y / Sprite.SCALED_SIZE, Sprite.grass.getFxImage()));
        }
        return false;
    }
}
