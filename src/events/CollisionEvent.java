package events;

import entity.Entity;

public class CollisionEvent {
    public Entity sourceCollider;
    public Entity targetCollider;
    public boolean[] collidingXY;

    public CollisionEvent(Entity sourceCollider, Entity targetCollider, boolean[] isCollidingXY) {
        this.sourceCollider = sourceCollider;
        this.targetCollider = targetCollider;
        this.collidingXY = isCollidingXY;
    }
}
