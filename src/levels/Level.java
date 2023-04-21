package levels;
import config.ScreenSizes;
import entity.Entity;
import world.GameWorld;

import java.util.List;

public class Level {
    List<Entity> entities;

    Entity player;
    public Level(Entity player) {
        this.player = player;
        GameWorld.entities = LevelCreator.levelEntities;
        entities = GameWorld.entities;
    }

    public void update() {
        deactivatePassedByComponents();
        activateNearComponents();
    }

    public void load(List<Entity> entities){
        this.entities = entities;
    }


    private void deactivatePassedByComponents(){
        entities.stream().filter(e -> e.position.x < player.position.x - ScreenSizes.HALF_WIDTH_SIZE).forEach(e -> e.active = false);
    }

    private void activateNearComponents(){
        int currX = player.position.x;
        entities.stream().filter(e -> e.position.x >= (currX - ScreenSizes.HALF_WIDTH_SIZE) && e.position.x <= (currX + ScreenSizes.HALF_WIDTH_SIZE)).forEach(e -> e.active = true);

    }
}
