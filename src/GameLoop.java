
public class GameLoop {
    public static long FPS = 60;
    public static long ONE_SEC_TO_MILISEC = 1000;
    public static long DELAY = ONE_SEC_TO_MILISEC / FPS;
    private final SquareGame game;

    public GameLoop(SquareGame game) {
        this.game = game;
    }

    public void run() throws InterruptedException {
        while(true) {
            long startTime = System.currentTimeMillis();
            game.update();
            long endTime = System.currentTimeMillis() - startTime;
            delayIfTooFast(DELAY - endTime);
        }
    }

    private  void delayIfTooFast(long sleepTime) throws InterruptedException {
        if (sleepTime >= 0) Thread.sleep(sleepTime);
    }
}
