package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.sound.Sound;

public class MultiItem extends Entity {

    public MultiItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity a) {
        if (a instanceof Bomber) {
            ((Bomber) a).setMultibomb();
            Sound.play("D:\\DEV_FILE\\OOP_Bomber\\res\\sound\\Item.wav");
            remove = true;
        }
        return false;
    }

}
