package components;

import config.ScreenSizes;
import entity.Entity;
import events.CollisionEvent;
import events.EventBus;
import levels.LevelCreator;
import world.GameWorld;

import java.awt.*;

import static config.ScreenSizes.EVERY_ENTITY_SIZE;
import static config.ScreenSizes.HEIGHT_SIZE;
import static levels.LevelCreator.LEVEL_LENGTH;

public class LevelBehavior extends Component{
    Entity player;
    public LevelBehavior(Entity entity, EventBus eventBus, Entity player) {
        super(entity, eventBus);
        this.player = player;
        eventBus.register(CollisionEvent.class, this::onCollision);
    }

    public void onCollision(CollisionEvent collisionEvent) {

        if (collisionEvent.sourceCollider == entity && collisionEvent.targetCollider.tag.equals("player")) {

            if(player.position.y <= 0){
               player.position.y = 0;

            }
            if(player.position.x <= player.getComponent(Camera.class).position.x){
                player.position.x =  player.getComponent(Camera.class).position.x;

            }
            if(player.position.y >= HEIGHT_SIZE){
               Camera.shapesToDraw.forEach(s -> s.color = Color.RED);
                GameWorld.entities.remove(player);
                System.out.println("DEAD");

            }
            if(player.position.x >= LEVEL_LENGTH){
                Camera.shapesToDraw.forEach(s -> s.color = Color.GREEN);
            }
        }
    }

    @Override
    public void update() {
        entity.position.x = player.position.x;
        entity.position.y = player.position.y;


    }
}
