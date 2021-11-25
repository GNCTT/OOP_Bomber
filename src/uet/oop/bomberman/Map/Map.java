package uet.oop.bomberman.Map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Map {
    private int level;
    private int height;
    private int width;
    private String [] _lineTiles;

    public Map(int level) {
        this.level = level;
    }

    public void createMap() {
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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
