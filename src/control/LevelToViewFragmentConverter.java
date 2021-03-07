package control;

import view.ViewFragment;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

public class LevelToViewFragmentConverter {
    public static final Map<Integer, Color> NUM_TO_COLOR = new HashMap<>();
    static {
        NUM_TO_COLOR.put(0, Color.GRAY);
        NUM_TO_COLOR.put(1, Color.BLACK);
        NUM_TO_COLOR.put(2,Color.RED);
    }

    public static List<ViewFragment> convert(int[][] level){
        List<ViewFragment> res = new LinkedList<>();
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                res.add(new ViewFragment(new Rectangle2D.Double(i * ViewFragment.SIZE,j * ViewFragment.SIZE,ViewFragment.SIZE,ViewFragment.SIZE), NUM_TO_COLOR.get(level[j][i])));
            }
        }
        return res;
    }

}
