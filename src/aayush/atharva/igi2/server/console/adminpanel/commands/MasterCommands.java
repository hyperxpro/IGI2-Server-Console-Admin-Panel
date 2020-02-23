package aayush.atharva.igi2.server.console.adminpanel.commands;

import aayush.atharva.igi2.server.console.adminpanel.Cut;
import static aayush.atharva.igi2.server.console.adminpanel.ListsPlayers.getPlayerByNick;
import aayush.atharva.igi2.server.console.adminpanel.Player;
import java.net.DatagramSocket;

/**
 *
 * @author Hyper
 */
public class MasterCommands extends Thread {

    public static DatagramSocket socket;
    private String Line;

    public MasterCommands(String Line) {
        this.Line = Line;
    }

    @Override
    public void run() {

    }

    public void StartMasterCommands() {
        String nick = Cut.substr(Line, 11, ": /MASTER ");
        Player player1 = getPlayerByNick(nick);

        if (player1 != null) {
            String PlayerAccess = String.valueOf(player1.getAccess());

            if (PlayerAccess.equals("2")) {

                if (Line.contains("")) {
                    
                }
                
            }

        }
    }

}
