package uet.oop.bomberman.Map;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Balloon;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Character.Minvo;
import uet.oop.bomberman.entities.Character.Oneal;
import uet.oop.bomberman.entities.Enemy.*;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Items.Bomb;
import uet.oop.bomberman.entities.Items.SpeedItem;
import uet.oop.bomberman.entities.Tiles.Brick;
import uet.oop.bomberman.entities.Tiles.Grass;
import uet.oop.bomberman.entities.Tiles.Portal;
import uet.oop.bomberman.entities.Tiles.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Map {
    private int level;
    private int height;
    private int width;
    private String [] _lineTiles;
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Entity> stillObjects = new ArrayList<>();

    public Map(int level) {
        this.level = level;
    }

    public void readMap() {
        String filename = "res/levels/Level" + level + ".txt";

        try {
            File file = new File(filename);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            line = bufferedReader.readLine();
            System.out.println(line);
            StringTokenizer tokens = new StringTokenizer(line);

            level = Integer.parseInt(tokens.nextToken());
            height = Integer.parseInt(tokens.nextToken());
            width = Integer.parseInt(tokens.nextToken());

            _lineTiles = new String[height];

            for(int i = 0; i < height; ++i) {
                _lineTiles[i] = bufferedReader.readLine().substring(0, width);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

    }



    public String[] get_lineTiles() {
        return _lineTiles;
    }

    public void createMap() {
        readMap();
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
//            }
//        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; ++j) {
                switch (_lineTiles[i].charAt(j)) {
                    case '#':
                        stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                        break;
//                    case '*':
//                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
//                        break;
                    case 'x':
                        stillObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
//                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 's':
                        stillObjects.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
//                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    default:
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        break;
                }
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; ++j) {
                switch (_lineTiles[i].charAt(j)) {
                    case '1':
                        entities.add(new Ballon2(j, i, Sprite.balloom_left1.getFxImage()));
                        break;
                    case '2':
                        entities.add(new Oneal2(j, i, Sprite.oneal_right1.getFxImage()));
                        break;
                    case '3':
                        entities.add(new Doll2(j, i, Sprite.doll_right1.getFxImage()));
                        break;
                    case '4':
                        entities.add(new Kondoria2(j, i, Sprite.kondoria_right1.getFxImage()));
                        break;
                    case '5':
                        entities.add(new Minvo2(j, i, Sprite.minvo_right1.getFxImage()));
                        break;
                    case '6':
                        entities.add(new Conma2(j, i, Sprite.conma_right1.getFxImage()));
                        break;
                    case '7':
                        entities.add(new Conlon2(j, i, Sprite.conlon_right1.getFxImage()));
                        break;
//                    case '*':
//                        entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
//                        break;
                    case '*':
                        entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 'x':
//                        entities.add(new Portal(j, i, Sprite.portal.getFxImage()));
                        entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 's':
//                        entities.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                        entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 'p':
                        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
                        entities.add(bomberman);
                }

            }
        }
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public ArrayList<Entity> getStillObjects() {
        return stillObjects;
    }

    public void addEntity(Entity a) {
        entities.add(a);
    }

    public void addBomb(int x, int y) {
        entities.add(new Bomb((int) x / Sprite.SCALED_SIZE,(int) y / Sprite.SCALED_SIZE , Sprite.bomb.getFxImage()));
    }

    public void removeEntity(Entity a) {
        entities.remove(a);
    }

    public void addObject(Entity a) {
        stillObjects.add(a);
    }

    public void remove(Entity a) {
        stillObjects.remove(a);
    }

    public Entity getEntity(int i, int j) {
        i = i * Sprite.SCALED_SIZE;
        j = j * Sprite.SCALED_SIZE;
        for (int id = 0; id < entities.size(); id++) {
            if (entities.get(id).getX() == i && entities.get(id).getY() == j) {
                if (entities.get(id) instanceof Brick) {
                    return entities.get(id);
                }
                return entities.get(id);
            }
        }
        return null;
    }

    public Entity getObject(int i, int j) {
        i *= Sprite.SCALED_SIZE;
        j *= Sprite.SCALED_SIZE;
        for (int id = 0; id < stillObjects.size(); id++) {
            if (stillObjects.get(id).getX() == i && stillObjects.get(id).getY() == j) {
                if (stillObjects.get(id) instanceof Brick) {
                    return stillObjects.get(id);
                }
                if (stillObjects.get(id) instanceof Wall) {
                    return stillObjects.get(id);
                }
                if (stillObjects.get(id) instanceof Grass) {
                    return stillObjects.get(id);
                }
                return stillObjects.get(id);
            }
        }
        return null;
    }

    public void addAllEntities(ArrayList<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            this.entities.add(entities.get(i));
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void update() {
        for (int i = 0; i < stillObjects.size(); i++) {
            if (stillObjects.get(i).isRemove()) {
//                int dx = stillObjects.get(i).getX() / Sprite.SCALED_SIZE;
//                int dy = stillObjects.get(i).getY() / Sprite.SCALED_SIZE;
//                Entity a = new Grass(stillObjects.get(i).getX() / Sprite.SCALED_SIZE, stillObjects.get(i).getY() / Sprite.SCALED_SIZE, Sprite.grass.getFxImage());
//                stillObjects.add(a);
                stillObjects.remove(i);

            }
            else {
                stillObjects.get(i).update();
            }
        }
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isRemove()) {
                entities.remove(i);
            }
            else {
                entities.get(i).update();
            }

        }
    }

    public void update2() {
        for (int i = 0; i < stillObjects.size(); i++) {
            stillObjects.get(i).update();
        }
    }

    public void render(GraphicsContext gc, Canvas canvas) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = 0; i < stillObjects.size(); i++) {
            stillObjects.get(i).render(gc);
        }
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(gc);
        }

    }


}
