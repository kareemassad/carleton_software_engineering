import Models.Game;
import Models.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        Game game = new Game();
//        game.setPlayerList(new ArrayList<Player>(Arrays.asList(new Player("p1"),
//                new Player("p2"),
//                new Player("p3"))));

//        System.out.println(String.format("Players starting the game:\n %s\n\r%s\n\r%s",
//                game.getPlayerList().get(0),
//                game.getPlayerList().get(1),
//                game.getPlayerList().get(2)));

        GamePlay gamePlay = new GamePlay(game);

        GameGUI gameGUI = new GameGUI(gamePlay);
        //gameGUI.RunGame();

    }
}