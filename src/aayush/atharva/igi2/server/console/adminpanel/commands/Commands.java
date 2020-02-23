package aayush.atharva.igi2.server.console.adminpanel.commands;

import aayush.atharva.igi2.server.console.adminpanel.Cut;
import aayush.atharva.igi2.server.console.adminpanel.Main;
import aayush.atharva.igi2.server.console.adminpanel.Player;
import static aayush.atharva.igi2.server.console.adminpanel.Main.Port;
import static aayush.atharva.igi2.server.console.adminpanel.Main.RCON;
import static aayush.atharva.igi2.server.console.adminpanel.ListsPlayers.getPlayerById;
import static aayush.atharva.igi2.server.console.adminpanel.ListsPlayers.getPlayerByNick;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


/**
 *
 * @author Aayush Atharva
 */
public class Commands extends Thread {

    public static DatagramSocket socket;

    public String Line;

    public Commands(String line) {
        this.Line = line;
    }

    @Override
    public void run() {
        try {
            StartCommand();
        } catch (Exception ex) {
            System.out.println("Error At Command: " + Line);
        }
    }

    public void StartCommand() throws Exception {
        String nick = Cut.substr(Line, 11, ": /");
        Player player1 = getPlayerByNick(nick);

        if (player1 != null) {
            String PlayerAccess = String.valueOf(player1.getAccess());
            String Cmd = "";

            if (PlayerAccess.equals("1")) {

                // Kickall
                if (Line.contains(": /kickall")) {
                    kickall();
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Kick 
                else if (Line.contains(": /kick")) {
                    kick(Cut.substr(Line, Cut.substr(Line, 11, ": /").length() + 19));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Gotomap
                else if (Line.contains(": /map")) {
                    gotomap(Cut.substr(Line, Cut.substr(Line, 11, ": /").length() + 18));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Server Name
                else if (Line.contains(": /srvname")) {
                    servername(Cut.substr(Line, Cut.substr(Line, 11, ": /srvname").length() + 22));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Server Password
                else if (Line.contains(": /srvpass")) {
                    serverpassword(Cut.substr(Line, Cut.substr(Line, 11, ": /srvpass").length() + 22));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Server Port
                else if (Line.contains(": /srvport")) {
                    serverport(Cut.substr(Line, Cut.substr(Line, 11, ": /srvport").length() + 22));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Max Players
                else if (Line.contains(": /maxplayers")) {
                    maxplayers(Cut.substr(Line, Cut.substr(Line, 11, ": /maxplayers").length() + 25));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Spectate
                else if (Line.contains(": /spectate")) {
                    spectate(Cut.substr(Line, Cut.substr(Line, 11, ": /spectate").length() + 23));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Teamdamage
                else if (Line.contains(": /teamdamage")) {
                    teamdamage(Cut.substr(Line, Cut.substr(Line, 11, ": /teamdamage").length() + 25));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Auto Balance
                else if (Line.contains(": /autobalance")) {
                    autobalance(Cut.substr(Line, Cut.substr(Line, 11, ": /autobalance").length() + 26));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Warmup
                else if (Line.contains(": /warmup")) {
                    warmup(Cut.substr(Line, Cut.substr(Line, 11, ": /warmup").length() + 21));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Activatemap
                else if (Line.contains(": /activatemap")) {
                    activatemap(Cut.substr(Line, Cut.substr(Line, 11, ": /activatemap").length() + 26));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Addmoney
                else if (Line.contains(": /addmoney")) {
                    addmoney(Cut.substr(Line, Cut.substr(Line, 11, ": /").length() + 23));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Moneycap
                else if (Line.contains(": /moneycap")) {
                    moneycap(Cut.substr(Line, Cut.substr(Line, 11, ": /").length() + 23));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Moneystart
                else if (Line.contains(": /moneystart")) {
                    moneystart(Cut.substr(Line, Cut.substr(Line, 11, ": /").length() + 25));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Moneykill
                else if (Line.contains(": /moneykill")) {
                    moneykill(Cut.substr(Line, Cut.substr(Line, 11, ": /moneykill").length() + 24));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Moneyteamkill
                else if (Line.contains(": /moneyteamkill")) {
                    moneyteamkill(Cut.substr(Line, Cut.substr(Line, 11, ": /moneyteamkill").length() + 28));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Moneyplayerobjwin 
                else if (Line.contains(": /moneyplayerobjwin")) {
                    moneyplayerobjwin(Cut.substr(Line, Cut.substr(Line, 11, ": /moneyplayerobjwin").length() + 32));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Moneyteamobjwin 
                else if (Line.contains(": /moneyteamobjwin")) {
                    moneyteamobjwin(Cut.substr(Line, Cut.substr(Line, 11, ": /moneyteamobjwin").length() + 30));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Moneyteamobjlost
                else if (Line.contains(": /moneyteamobjlost")) {
                    moneyteamobjlost(Cut.substr(Line, Cut.substr(Line, 11, ": /moneyteamobjwin").length() + 30));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Moneymissionwin
                else if (Line.contains(": /moneymissionwin")) {
                    moneymissionwin(Cut.substr(Line, Cut.substr(Line, 11, ": /moneymissionwin").length() + 30));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Moneymissionlost
                else if (Line.contains(": /moneymissionlost")) {
                    moneyteamobjlost(Cut.substr(Line, Cut.substr(Line, 11, ": /moneymissionlost ").length() + 31));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Maprounds
                else if (Line.contains(": /maprounds")) {
                    maprounds(Cut.substr(Line, Cut.substr(Line, 11, ": /maprounds").length() + 24));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Pingmax
                else if (Line.contains(": /pingmax")) {
                    pingmax(Cut.substr(Line, Cut.substr(Line, 11, ": /pingmax").length() + 22));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Plossmax
                else if (Line.contains(": /plossmax")) {
                    plossmax(Cut.substr(Line, Cut.substr(Line, 11, ": /plossmax").length() + 23));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Maptime
                else if (Line.contains(": /maptime")) {
                    maptime(Cut.substr(Line, Cut.substr(Line, 11, ": /maptime ").length() + 22));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Mapteamscore
                else if (Line.contains(": /mapteamscore")) {
                    mapteamscore(Cut.substr(Line, Cut.substr(Line, 11, ": /mapteamscore ").length() + 27));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Objtime
                else if (Line.contains(": /objtime")) {
                    objtime(Cut.substr(Line, Cut.substr(Line, 11, ": /objtime ").length() + 22));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Bombtime
                else if (Line.contains(": /bombtime")) {
                    bombtime(Cut.substr(Line, Cut.substr(Line, 11, ": /bombtime ").length() + 23));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Spawncost
                else if (Line.contains(": /spawncost")) {
                    spawmcost(Cut.substr(Line, Cut.substr(Line, 11, ": /spawncost ").length() + 24));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Spawntimer
                else if (Line.contains(": /spawntimer")) {
                    spawntimer(Cut.substr(Line, Cut.substr(Line, 11, ": /spawntimer ").length() + 25));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Spawnsafetimer
                else if (Line.contains(": /spawnsafetimer")) {
                    spawnsafetimer(Cut.substr(Line, Cut.substr(Line, 11, ": /spawnsafetimer ").length() + 29));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Allowsniperrelse ifles
                else if (Line.contains(": /allowsnipers")) {
                    allowsniperrifles(Cut.substr(Line, Cut.substr(Line, 11, ": /allowsnipers ").length() + 27));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Smooth
                else if (Line.contains(": /smooth")) {
                    smooth(Cut.substr(Line, Cut.substr(Line, 11, ": /smooth ").length() + 21));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Bandwidth
                else if (Line.contains(": /bandwidth")) {
                    bandwidth(Cut.substr(Line, Cut.substr(Line, 11, ": /bandwidth ").length() + 24));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Choke
                else if (Line.contains(": /choke")) {
                    choke(Cut.substr(Line, Cut.substr(Line, 11, ": /choke ").length() + 20));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Fillpercent
                else if (Line.contains(": /fillpercent")) {
                    fillpercent(Cut.substr(Line, Cut.substr(Line, 11, ": /fillpercent ").length() + 26));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Timeout
                else if (Line.contains(": /timeout")) {
                    timeout(Cut.substr(Line, Cut.substr(Line, 11, ": /timeout ").length() + 22));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Autokick
                else if (Line.contains(": /autokick")) {
                    autokick(Cut.substr(Line, Cut.substr(Line, 11, ": /autokick ").length() + 23));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Bombrepostime
                else if (Line.contains(": /bombrepostime")) {
                    bombrepostime(Cut.substr(Line, Cut.substr(Line, 11, ": /bombrepostime ").length() + 28));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Forcefirstspec
                else if (Line.contains(": /forcefirstspec")) {
                    forcefirstspec(Cut.substr(Line, Cut.substr(Line, 11, ": /forcefirstspec ").length() + 29));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Deactivatemap
                else if (Line.contains(": /deactivatemap")) {
                    deactivatemap(Cut.substr(Line, Cut.substr(Line, 11, ": /deactivatemap ").length() + 28));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Restartmap
                else if (Line.contains(": /restartmap")) {
                    restartmap();
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Gotonext
                else if (Line.contains(": /gotonext")) {
                    gotonext();
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Announce
                else if (Line.contains(": /announce")) {
                    sendAnnounce(Cut.substr(Line, Cut.substr(Line, 11, ": /announce").length() + 23));
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                } // Kickall
                else {
                    Cmd = "[" + player1.getId() + "] " + "[" + player1.getIp() + "] " + "[" + player1.getNick() + "] Used Invalid/Unknown Command: " + Cut.substr(Line, player1.getNick().length() + 14);
                }

                Main.echo(Cmd);
            }
        }
    }

    public void kickall() throws Exception {
        sendAnnounce("Kicking Out All Players");
        for (int count = 0; count <= 34; count++) {
            sendCommand("kick " + String.valueOf(count));
        }
        sendAnnounce("All Players Have Been Kicked Out");
    }

    // Gotonext Map
    public void gotonext() throws Exception {
        sendAnnounce("Changing Map To Next Active Map");
        Thread.sleep(1500);
        sendCommand("gotonext");
    }

    // Restartmap
    public void restartmap() throws Exception {
        sendAnnounce("Restarting Current Map");
        Thread.sleep(1500);
        sendCommand("restartmap");
    }

    // Deactivatemap
    public void deactivatemap(String map) throws Exception {
        String Mapname = "";

        switch (map) {
            case "1":
                Mapname = "Redstone";
                break;
            case "2":
                Mapname = "Forestraid";
                break;
            case "3":
                Mapname = "Sandstorm";
                break;
            case "4":
                Mapname = "Timberland";
                break;
            case "5":
                Mapname = "Chinese Temple";
                break;
            case "7":
                Mapname = "Winterland";
                break;
            case "9":
                Mapname = "Dockside";
                break;
            case "20":
                Mapname = "Prison Escape";
                break;
            case "42":
                Mapname = "Libyan Village";
                break;
            case "177":
                Mapname = "Dockbeta";
                break;
            case "814":
                Mapname = "Bridge across the Dnestr";
                break;
            case "823":
                Mapname = "Priboi's Villa";
                break;
            case "831":
                Mapname = "Island Assault";
                break;
            case "1215":
                Mapname = "Jungle X";
                break;
            case "1967":
                Mapname = "Docklands";
                break;
            case "2051":
                Mapname = "Factory";
                break;
            case "100001":
                Mapname = "Snowstone X";
                break;
            default:
                Mapname = "Not_Found";
                break;
        }

        if (!Mapname.equals("Not_Found")) {
            sendLocalCommand("deactivatemap " + map);
            sendAnnounce("Map Deactivated: " + Mapname);
        }
    }

    // Spawmtimber
    public void spawntimer(String time) throws Exception {
        sendCommand("spawntimer " + time);
        sendAnnounce("Spawn Timer Set To " + time + " Seconds");
        sendAnnounce("Players Have To Wait " + time + " Seconds");
        sendAnnounce("Before Spawning");
    }

    // Objtime
    public void objtime(String time) throws Exception {
        sendCommand("objtime " + time);
        sendAnnounce("Objective Time Set To " + time + " Minutes");
        sendAnnounce("From Next Round, Maximum Time For");
        sendAnnounce("Doing Objective Will Be " + time + " Minutes");
    }

    // Bombtime
    public void bombtime(String time) throws Exception {
        sendCommand("bombtime " + time);
        sendAnnounce("Bombtime Set To " + time + " Seconds");
        sendAnnounce("Bomb Will Explode In " + time + " Seconds");
        sendAnnounce("After Getting Placed");
    }

    // Spawncost
    public void spawmcost(String cost) throws Exception {
        sendCommand("spawncost " + cost);
        sendAnnounce("Spawncost Set To $" + cost);
        sendAnnounce("$" + cost + " Will Be Charged From Player");
        sendAnnounce("While Spawing");
    }

    // Spawnsafetimer
    public void spawnsafetimer(String time) throws Exception {
        sendCommand("spawnsafetimer " + time);
        sendAnnounce("Spawn Safe Timer Set To " + time + " Seconds");
        sendAnnounce("Till " + time + " Seconds, A Player Will Be Secured");
        sendAnnounce("By Spawn Policy Of Server");
    }

    // Allow Sniper Rifles 
    public void allowsniperrifles(String value) throws Exception {
        if (value.equals("0")) {
            sendCommand("allowsniperrifles 0");
            sendAnnounce("Sniper Rifles Blocked On Server");
        }

        if (value.equals("1")) {
            sendCommand("allowsniperrifles 1");
            sendAnnounce("Sniper Rifles Allowed On Server");
        }
    }

    // Smooth
    public void smooth(String value) throws Exception {
        sendCommand("smooth " + value);
        sendAnnounce("Smooth Set To " + value);
    }

    // Bandwidth
    public void bandwidth(String value) throws Exception {
        sendCommand("bandwidth " + value);
        sendAnnounce("Bandwidth Set To " + value);
    }

    // Choke
    public void choke(String choke) throws Exception {
        sendCommand("choke " + choke);
        sendAnnounce("Choke Set To " + choke);
    }

    // Fillpercent
    public void fillpercent(String value) throws Exception {
        sendCommand("fillpercent " + value);
        sendAnnounce("Fillpercent Set To " + value);
    }

    // Timeout
    public void timeout(String value) throws Exception {
        sendCommand("timeout " + value);
        sendAnnounce("Timeout Set To " + value + " Seconds");
        sendAnnounce("Player will removed after " + value + " Seconds");
        sendAnnounce("Of Network Inactivity");
    }

    // Autokick
    public void autokick(String value) throws Exception {
        sendCommand("autokick " + value);
        sendAnnounce("Autokick Set To " + value + " Seconds");
        sendAnnounce("Player will be removed after " + value + " Seconds");
        sendAnnounce("Of In-Game Inactivity");
    }

    // Bombrepostime
    public void bombrepostime(String time) throws Exception {
        if (time.equals("0")) {
            sendCommand("bombrepostime 0");
            sendAnnounce("Bombrepostime Set To 0 And Disabled");
        } else {
            sendCommand("bombrepostime" + time + " Seconds");
            sendAnnounce("Bombrepostime Set To " + time + " Seconds");
            sendAnnounce("Bomb Will Be Repositioned To Default Location");
            sendAnnounce("After In-Game Inactivity Of " + time + " Seconds");
        }
    }

    // Forcefirstspec
    public void forcefirstspec(String value) throws Exception {
        if (value.equals("0")) {
            sendCommand("forcefirstspec 0");
            sendAnnounce("Forcefirstspec Set To " + value + " And Disabled");
        } else if (value.equals("1")) {
            sendCommand("forcefirstspec 1");
            sendAnnounce("Forcefirstspec Set To " + value);
            sendAnnounce("Players Are Forced For First-Person View");
            sendAnnounce("While Spectating");
        }
    }

    // Maptime
    public void maptime(String time) throws Exception {
        sendCommand("maptime " + time);
        sendAnnounce("Maptime Set To " + time + " Minutes");
        sendAnnounce("Map Will Changed To Next Active Map");
        sendAnnounce("After " + time + " Minutes");
    }

    // Mapteamscore
    public void mapteamscore(String score) throws Exception {
        sendCommand("mapteamscore " + score);
        sendAnnounce("Map Team Score Set To " + score);
        sendAnnounce("After " + score + " Team Scores");
        sendAnnounce("Current Map Will Be Changed To");
        sendAnnounce("Next Active Map");
    }

    //Plossmax
    public void plossmax(String ploss) throws Exception {
        sendCommand("plossmax " + ploss + "0");
        sendAnnounce("Maximum Average Plossmax Set To " + ploss + "%");
        sendAnnounce("Player Will Be Removed After The");
        sendAnnounce("Average Packet Loss Of " + ploss + "%");
    }

    // Pingmax
    public void pingmax(String ping) throws Exception {
        sendCommand("pingmax " + ping);
        sendAnnounce("Maximum Average Ping Set To " + ping + " MS.");
        sendAnnounce("Player Will Be Removed After The");
        sendAnnounce("Average Ping Of " + ping + " MS.");
    }

    // Maprounds
    public void maprounds(String rounds) throws Exception {
        sendCommand("maprounds " + rounds);
        sendAnnounce("Maximum Map Rounds Set To " + rounds);
        sendAnnounce("Map Will Be Changed To Next Active Map");
        sendAnnounce("After " + rounds + " Rounds Of Current Map");
    }

    // Moneyplayerobjwin
    public void moneyplayerobjwin(String money) throws Exception {
        sendCommand("moneyplayerobjwin " + money);
        sendAnnounce("Money Player Objective Win Set To $" + money);
        sendAnnounce("$" + money + " Will Be Added");
        sendAnnounce("To Player Who Win Objective");
    }

    // Money Team Obj Win
    public void moneyteamobjwin(String money) throws Exception {
        sendCommand("moneyteamobjwin " + money);
        sendAnnounce("Money Team Objective Win Set To $" + money);
        sendAnnounce("$" + money + " Will Be Credited");
        sendAnnounce("To All Team Players Who Win Objective");
    }

    // Money Team Obj Lost
    public void moneyteamobjlost(String money) throws Exception {
        sendCommand("moneyteamobjlost " + money);
        sendAnnounce("Money Team Objective Lost Set To $" + money);
        sendAnnounce("$" + money + " Will Be Deducted");
        sendAnnounce("From All Team Players Who Loose Objective");
    }

    // Money Team Mission Win
    public void moneymissionwin(String money) throws Exception {
        sendCommand("moneymissionwin " + money);
        sendAnnounce("Money Team Mission Win Set To $" + money);
        sendAnnounce("$" + money + " Will Be Credited");
        sendAnnounce("To All Team Players Who Win Mission");
    }

    // Money Team Mission Lost
    public void moneymissionlost(String money) throws Exception {
        sendCommand("moneymissionlost " + money);
        sendAnnounce("Money Team Mission Lost Set To $" + money);
        sendAnnounce("$" + money + " Will Be Deducted");
        sendAnnounce("From All Team Players Who Loose Mission");
    }

    // Moneyteamkill
    public void moneyteamkill(String money) throws Exception {
        sendCommand("moneyteamkill -" + money);
        sendAnnounce("Moneyteamkill Set To -$" + money);
        sendAnnounce("$" + money + " Will Be Deduced From Player");
        sendAnnounce("Account Who Kills His Own Team Member");
    }

    // Moneykill
    public void moneykill(String money) throws Exception {
        sendCommand("moneykill " + money);
        sendAnnounce("Moneykill Set To $" + money);
        sendAnnounce("$" + money + " Will Be Credited To");
        sendAnnounce("The Player Account Who Kills");
    }

    // Addmoney
    public void addmoney(String money) throws Exception {
        sendCommand("addmoney " + money);
        sendAnnounce("$" + money + " Added To All Accounts");
    }

    // Moneycap
    public void moneycap(String money) throws Exception {
        sendCommand("moneycap " + money);
        sendAnnounce("Maximum Money Capped To $" + money);
    }

    // Moneystart
    public void moneystart(String money) throws Exception {
        sendCommand("moneystart " + money);
        sendAnnounce("Money Start Set To $" + money);
    }

    // Activatemap
    public void activatemap(String map) throws Exception {
        String Mapname = "";

        if (map.equals("1")) {
            Mapname = "Redstone";
        } else if (map.equals("2")) {
            Mapname = "Forestraid";
        } else if (map.equals("3")) {
            Mapname = "Sandstorm";
        } else if (map.equals("4")) {
            Mapname = "Timberland";
        } else if (map.equals("5")) {
            Mapname = "Chinese Temple";
        } else if (map.equals("7")) {
            Mapname = "Winterland";
        } else if (map.equals("9")) {
            Mapname = "Dockside";
        } else if (map.equals("20")) {
            Mapname = "Prison Escape";
        } else if (map.equals("42")) {
            Mapname = "Libyan Village";
        } else if (map.equals("177")) {
            Mapname = "Dockbeta";
        } else if (map.equals("814")) {
            Mapname = "Bridge across the Dnestr";
        } else if (map.equals("823")) {
            Mapname = "Priboi's Villa";
        } else if (map.equals("831")) {
            Mapname = "Island Assault";
        } else if (map.equals("1215")) {
            Mapname = "Jungle X";
        } else if (map.equals("1967")) {
            Mapname = "Docklands";
        } else if (map.equals("2051")) {
            Mapname = "Factory";
        } else if (map.equals("100001")) {
            Mapname = "Snowstone X";
        } else {
            Mapname = "Not_Found";
        }

        if (!Mapname.equals("Not_Found")) {
            sendLocalCommand("activatemap " + map);
            sendAnnounce("Map Activated: " + Mapname);
        }
    }

    // Warmup
    public void warmup(String value) throws Exception {
        if (value.equals("0")) {
            sendCommand("warmup 0");
            sendAnnounce("Warmup Disabled");
        }

        if (value.equals("1")) {
            sendCommand("warmup 1");
            sendAnnounce("Warmup Enabled");
        }
    }

    // Autobalance
    public void autobalance(String value) throws Exception {
        if (value.equals("0")) {
            sendCommand("autobalance 0");
            sendAnnounce("Auto-Balance Disabled");
            sendAnnounce("New Players Joining Server");
            sendAnnounce("Will Not Be Balanced Automatically");
        }

        if (value.equals("1")) {
            sendCommand("autobalance 1");
            sendAnnounce("Auto-Balance Enabled");
            sendAnnounce("New Players Joining Server");
            sendAnnounce("Will Be Balanced Automatically");
        }

    }

    // Team Damange
    public void teamdamage(String value) throws Exception {
        if (value.equals("0")) {
            sendCommand("teamdamage 0");
            sendAnnounce("Team Damange Disabled");
        }

        if (value.equals("1")) {
            sendCommand("teamdamage 1");
            sendAnnounce("Team Damange Enabled");
        }
    }

    // Spectate 
    public void spectate(String value) throws Exception {
        if (value.equals("0")) {
            sendCommand("specmode 0");
            sendAnnounce("Team Spectate Disabled");
        }

        if (value.equals("1")) {
            sendCommand("specmode 1");
            sendAnnounce("Team Spectate Enabled For Team Only");
        }

        if (value.equals("2")) {
            sendCommand("specmode 2");
            sendAnnounce("Team Spectate Enabled For All");
        }
    }

    // Max Players
    public void maxplayers(String players) throws Exception {
        sendCommand("maxplayers " + players);
        sendAnnounce("Maximum Players Allowed: " + players);
    }

    // Server Name
    public void servername(String name) throws Exception {
        sendLocalCommand("svname (\"" + name + "\")");
        sendAnnounce("Changing Server Name To:" + name);
    }

    // Server Password
    public void serverpassword(String password) throws Exception {
        sendCommand("svpassword(\"" + password + "\")");
        sendAnnounce("Server Password Set");
    }

    // Server Port
    public void serverport(String port) throws Exception {
        sendLocalCommand("svport " + port);
        sendAnnounce("Server Port Changed To: " + port);
    }

    // Map Change - Gotomap
    public void gotomap(String map) throws Exception {
        String Mapname = "";

        if (map.equals("1")) {
            Mapname = "Redstone";
        } else if (map.equals("2")) {
            Mapname = "Forestraid";
        } else if (map.equals("3")) {
            Mapname = "Sandstorm";
        } else if (map.equals("4")) {
            Mapname = "Timberland";
        } else if (map.equals("5")) {
            Mapname = "Chinese Temple";
        } else if (map.equals("7")) {
            Mapname = "Winterland";
        } else if (map.equals("9")) {
            Mapname = "Dockside";
        } else if (map.equals("20")) {
            Mapname = "Prison Escape";
        } else if (map.equals("42")) {
            Mapname = "Libyan Village";
        } else if (map.equals("177")) {
            Mapname = "Dockbeta";
        } else if (map.equals("814")) {
            Mapname = "Bridge across the Dnestr";
        } else if (map.equals("823")) {
            Mapname = "Priboi's Villa";
        } else if (map.equals("831")) {
            Mapname = "Island Assault";
        } else if (map.equals("1215")) {
            Mapname = "Jungle X";
        } else if (map.equals("1967")) {
            Mapname = "Docklands";
        } else if (map.equals("2051")) {
            Mapname = "Factory";
        } else if (map.equals("100001")) {
            Mapname = "Snowstone X";
        } else {
            Mapname = "Not_Found";
        }

        if (!Mapname.equals("Not_Found")) {
            sendAnnounce("Changing Map To " + Mapname);
            Thread.sleep(3000);
            sendCommand("gotomap " + map);
        }
    }

    // Kick Player
    public void kick(String ID) throws Exception {
        Player p = getPlayerById(ID);
        if (p != null) {
            sendCommand("kick " + ID);
            Thread.sleep(10);
            sendCommand("kick " + ID);
            sendAnnounce(p.getNick() + " Has Been Kicked!");
        } else {
            sendAnnounce("No Player Found On Server With ID: " + ID);
        }
    }

    public void sendCommand(String command) throws Exception {
        String Command = "/sv " + command;
        String Rcon = "/" + RCON;

        InetAddress servAddr = InetAddress.getByName(InetAddress.getLocalHost().getHostAddress());
        byte[] buf = Rcon.getBytes();
        byte[] buf2 = Command.getBytes();
        socket = new DatagramSocket();
        socket.send(new DatagramPacket(buf, buf.length, servAddr, Port));
        Thread.sleep(100);
        socket.send(new DatagramPacket(buf2, buf2.length, servAddr, Port));
        socket.close();
    }

    public void sendLocalCommand(String command) throws Exception {
        String Command = "/lo " + command;
        String Rcon = "/" + RCON;

        InetAddress servAddr = InetAddress.getByName(InetAddress.getLocalHost().getHostAddress());
        byte[] buf = Rcon.getBytes();
        byte[] buf2 = Command.getBytes();
        socket = new DatagramSocket();
        socket.send(new DatagramPacket(buf, buf.length, servAddr, Port));
        Thread.sleep(100);
        socket.send(new DatagramPacket(buf2, buf2.length, servAddr, Port));
        socket.close();
    }

    public void sendAnnounce(String msg) throws Exception {
        String Command = "/lo announce (\"" + msg + "\")";
        String Rcon = "/" + RCON;

        InetAddress servAddr = InetAddress.getByName(InetAddress.getLocalHost().getHostAddress());
        byte[] buf = Rcon.getBytes();
        byte[] buf2 = Command.getBytes();
        socket = new DatagramSocket();
        socket.send(new DatagramPacket(buf, buf.length, servAddr, Port));
        Thread.sleep(100);
        socket.send(new DatagramPacket(buf2, buf2.length, servAddr, Port));
        socket.close();
    }
}
