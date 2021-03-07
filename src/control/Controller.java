package control;

import model.CurrentLevelFragment;
import view.View;
import view.ViewFragment;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

public class Controller {
    View view;

    public Controller(View view) {
        this.view = view;
    }

    public void updateView(){
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(() -> {
                view.update(LevelToViewFragmentConverter.convert(CurrentLevelFragment.map));
            });
        }
    }

}
