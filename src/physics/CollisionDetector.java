package physics;

import components.Collider;
import events.CollisionEvent;
import world.GameWorld;

public class CollisionDetector {
    public static final int X_AXIS_INDEX = 0;
    public static final int Y_AXIS_INDEX = 1;

    public void update() {
        detectCollisions();
    }
    private void detectCollisions(){
        java.util.List<Collider> collidingBodies = GameWorld.getActiveComponents(Collider.class);
        for (Collider first : collidingBodies){
            for (Collider second : collidingBodies) {
                if (first == second) {
                   continue;
                }
                detectCollisionBetween(first,second);
            }
        }
    }
    private void detectCollisionBetween(Collider first, Collider second){
        boolean[] isColliding = first.collidesWith(second);
        if(isColliding[X_AXIS_INDEX] || isColliding[Y_AXIS_INDEX]){
            broadcastCollisionEvent(first, second, isColliding);
        }
    }

    private void broadcastCollisionEvent(Collider first, Collider second, boolean[] isColliding){
        first.eventBus.post(new CollisionEvent(first.entity,second.entity,isColliding));
    }

}
