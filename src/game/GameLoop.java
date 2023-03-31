package game;

import java.lang.reflect.InvocationTargetException;

public class GameLoop {
    public static final long TARGET_FPS = 60;
    public static final long ONE_THOUSAND_MILISEC = 1000;
    public static final long FRAME_TARGET_TIME = ONE_THOUSAND_MILISEC / TARGET_FPS;
    private boolean isRunning = true;
    private final Game game;

    public GameLoop(Game game) {
        this.game = game;
    }

    public void run() throws InterruptedException, InvocationTargetException {
        long previousTime = System.currentTimeMillis();
        long elapsedTime = 0;
        while(isRunning) {
            long currentTime = System.currentTimeMillis();
            elapsedTime += currentTime - previousTime;
            previousTime = currentTime;
            while(elapsedTime >= FRAME_TARGET_TIME) {
                game.update();
                elapsedTime-= FRAME_TARGET_TIME;
            }
            game.render();
        }
    }
}
