//package components;
//import entities.Entity;
//import game.GameManager;
//import events.EventBus;
//
//import java.awt.Rectangle;
//
//public class Collider extends Component {
//    private final Motion motion;
//    private final Behaviour behaviour;
//    public boolean collideWithOther = false;
//    public Collider(Entity entity, EventBus eventBus, Motion motion, Behaviour behaviour) {
//        super(entity, eventBus);
//        this.motion = motion;
//        this.behaviour = behaviour;
//    }
//
//    private boolean[] detectCollision(Collider other) {
//            Rectangle withAddedDx = new Rectangle(motion.getX() + motion.getDx(),motion.getY(),motion.getWidth(), motion.getHeight() );
//            Rectangle withAddedDy = new Rectangle(motion.getX(),motion.getY() + motion.getDy(), motion.getWidth(), motion.getHeight() );
//            Motion otherMotion = other.entity.getComponent(Motion.class);
//            Rectangle otherCollider = new Rectangle(otherMotion.getX() + otherMotion.getDx(),otherMotion.getY() + otherMotion.getDy(),otherMotion.getWidth(),otherMotion.getHeight());
//            return new boolean[]{withAddedDx.intersects(otherCollider),withAddedDy.intersects(otherCollider)};
//    }
//
//    @Override
//    public void update() {
//        if (!collideWithOther)
//            return;
//        for (Collider other : GameManager.getColliders()) {
//            if (this != other) {
//                boolean[] isColliding = detectCollision(other);
//                if(isColliding[0] || isColliding[1]){
//                    if(behaviour != null)
//                        behaviour.onCollision(other, isColliding);
//                }
//            }
//        }
//    }
//
//}
