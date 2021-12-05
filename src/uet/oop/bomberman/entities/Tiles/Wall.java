package uet.oop.bomberman.entities.Tiles;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character.Balloon;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;

import java.util.Random;

public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity a) {
        if (a instanceof Bomber || a instanceof Balloon) {
            if (a instanceof Balloon) {
                Random random = new Random();
                ((Balloon) a).setDirection(random.nextInt(4));
            }
            return true;
        }
        return false;
    }
}
