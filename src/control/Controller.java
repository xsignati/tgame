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
    CurrentLevelFragmentController currentLevelFragmentController;
    PlayerSteering playerSteering;
    View view;

    public Controller(View view, PlayerSteering playerSteering, CurrentLevelFragmentController currentLevelFragmentController) {
        this.view = view;
        this.playerSteering = playerSteering;
        this.currentLevelFragmentController = currentLevelFragmentController;
    }

    public void updateView(){
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(() -> view.update(LevelToViewFragmentConverter.convert(currentLevelFragmentController.getCurrentLevelFragment().map)));
        }
    }

    public void update(){
        playerSteering.update();
    }

    public void updateLevelFragment(){
        currentLevelFragmentController.update();
    }

}
