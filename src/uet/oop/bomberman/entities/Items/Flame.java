package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;

public class Flame extends Entity {

    public Flame(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity a) {
        if (a instanceof Bomber) {
            ((Bomber) a).setFlame();
//            Sound.play("D:\\DEV_FILE\\OOP_Bomber\\res\\sound\\Item.wav");
            remove = true;
        }
        return false;
    }

}
