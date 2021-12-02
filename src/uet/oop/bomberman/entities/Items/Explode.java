package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tiles.Brick;
import uet.oop.bomberman.graphics.Sprite;

public class Explode extends Entity {
    public Explode(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        int dx = (int) x / Sprite.SCALED_SIZE;
        int dy = (int) y / Sprite.SCALED_SIZE;
        explode(Bomb.explodeTime);
        Entity a = BombermanGame.map.getObject(dx, dy);
        if (a instanceof Brick) {
            BombermanGame.map.removeEntity(this);
        }
    }

    @Override
    public boolean collide(Entity a) {
        if (a instanceof Brick) {
            return true;
        }
        return false;
    }

    private void explode(int time) {
        if (time < 0) {
            BombermanGame.map.removeEntity(this);
        }
    }

    public void remove() {
        BombermanGame.map.removeEntity(this);
    }
}
