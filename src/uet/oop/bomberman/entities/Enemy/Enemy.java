package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Enemy extends Entity {
    protected int animate;
    private final int maxAnimate = 7500;
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        animate = 0;
    }

    public Enemy(int x, int y, Image img, int animate) {
        super(x, y, img);
        this.animate = animate;
    }

    public void animate() {
        if (animate < 7500) {
            animate ++;
        } else animate = 0;
    }
}
