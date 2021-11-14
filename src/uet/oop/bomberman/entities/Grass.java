package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends Entity {

    public Grass(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(sprite.getFxImage(), x, y);
    }

    @Override
    public void update() {
    }
}
