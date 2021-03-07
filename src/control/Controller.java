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
    PlayerSteering playerSteering;
    View view;

    public Controller(View view, PlayerSteering playerSteering) {
        this.view = view;
        this.playerSteering = playerSteering;
    }

    public void updateView(){
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(() -> view.update(LevelToViewFragmentConverter.convert(CurrentLevelFragment.map)));
        }
    }

    public void update(){
        playerSteering.update();
    }

}
