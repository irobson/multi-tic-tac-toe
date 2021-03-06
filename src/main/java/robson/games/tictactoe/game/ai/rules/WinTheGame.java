package robson.games.tictactoe.game.ai.rules;


import robson.games.tictactoe.game.ai.Rule;
import robson.games.tictactoe.io.Printer;
import robson.games.tictactoe.model.Path;
import robson.games.tictactoe.model.Player;

import java.util.List;

public class WinTheGame implements Rule {

    @Override
    public boolean executeSelection(Player player, List<Path> paths) {
        for (Path path : paths) {
            if (path.containsOnly(player) && path.countFreeFields() == 1) {
                path.getFirstFreeField().assign(player);
                Printer.debug("I'm the best! :-)");
                return true;
            }
        }
        return false;
    }
}