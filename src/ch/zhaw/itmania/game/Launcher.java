package ch.zhaw.itmania.game;

/**
 * Created by ungerpet on 12.12.2015.
 */
public class Launcher {

    private Game game;

    public static void main(String[] args) {
        new Launcher();
    }

    public Launcher() {
        game = new Game();
        game.start();
    }
}
