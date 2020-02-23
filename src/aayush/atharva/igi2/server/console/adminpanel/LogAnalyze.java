package aayush.atharva.igi2.server.console.adminpanel;

import aayush.atharva.igi2.server.console.adminpanel.commands.Commands;
import static aayush.atharva.igi2.server.console.adminpanel.ListsPlayers.getPlayerById;
import static aayush.atharva.igi2.server.console.adminpanel.ListsPlayers.poppedPlayers;
import static aayush.atharva.igi2.server.console.adminpanel.ListsPlayers.rmPlayer;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Aayush Atharva
 */
public class LogAnalyze extends Thread {

    public static String Key = "DB99A2A8EB6904F492E9DF0595ED683C";

    @Override
    public void run() {
        try {
            LogAnalyze.Analyze();
        } catch (Exception ex) {
            System.out.println("Error Occured");
        }
    }

    public static void Analyze() {
        String linia = "";
        long dl;
        long poz = 0;
        int l = 0;
        String pom1, pom2 = "";
        RandomAccessFile czyt = null;

        try {
            czyt = new RandomAccessFile("Multiplayer.log", "r");
            while (true) {

                dl = czyt.length();
                czyt.seek(poz);

                while (poz > dl) {
                    Main.echo("Server Restarted!");
                    System.exit(0);
                }

                while (poz < dl) {
                    linia = czyt.readLine();
                    poz = czyt.getFilePointer();

// ------------------------------------------------------------------------------------------ Get Players --------------------------------------------------------------------------------------------
                    if (linia.indexOf("Popped new networkID for joining client:") > -1) {  //New Player In Queue
                        String id, ip;
                        id = Cut.substr(linia, 53, "]");
                        ip = Cut.substr(linia, linia.indexOf("][") + 2, ":");
                        poppedPlayers.add(new Player(id, ip));
                    } else if (linia.indexOf("Client[") > -1 && linia.indexOf("created locally") > -1) {  //New Player Joined The Server

                        if (linia.indexOf("[]") > -1) {
                            continue;
                        }

                        // Save Player Details [ID, IP, Nick]
                        String id = Cut.substr(linia, 18, "]");
                        String nick = Cut.substrEndOf(linia, linia.indexOf("][") + 2, "] created locally");

                        AddPlayers a = new AddPlayers(id, nick);
                        a.start();

                    } else if (linia.indexOf("][") == 13 || linia.indexOf("][") == 14) { //Player Left

                        if (linia.indexOf(" left the game") > -1) {

                            String id = Cut.substr(linia, 12, "]");
                            String nick = Cut.substrEndOf(linia, linia.indexOf("][") + 2, "]");
                            rmPlayer(id);

                        } else if (linia.indexOf(" removed: Connection lost") > -1) {   //Connection Lost

                            String id = Cut.substr(linia, 12, "]");
                            String nick = Cut.substrEndOf(linia, linia.indexOf("][") + 2, "]");
                            rmPlayer(id);

                        } else if (linia.indexOf(" kicked out of the game") > -1) { // Kicked

                            String id = Cut.substr(linia, 12, "]");
                            String nick = Cut.substrEndOf(linia, linia.indexOf("][") + 2, "]");
                            rmPlayer(id);

                        }

                    } else if (linia.indexOf("Client_Deletehandler: Removing ") > -1) { // Removing Client

                        String id = Cut.substr(linia, 43, "]");
                        if (getPlayerById(id) != null) {
                            String nick = linia.substring(43 + id.length() + 1);
                            rmPlayer(id);
                        }

                    }

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
//
//
                    if (linia.contains(": /")) {
                        Commands cmd = new Commands(linia);
                        cmd.start();
                    } else if (linia.contains(": /MASTER")) {
                        
                    }

                }

                Thread.sleep(100);
            }
        } catch (IOException | InterruptedException ex) {
            try {
                czyt.close();
            } catch (IOException ex1) {
                System.out.println(ex1);
            }
        }
    }
}
