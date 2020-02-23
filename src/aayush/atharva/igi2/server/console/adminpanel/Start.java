package aayush.atharva.igi2.server.console.adminpanel;

import static aayush.atharva.igi2.server.console.adminpanel.Main.Port;
import static aayush.atharva.igi2.server.console.adminpanel.Main.RCON;
import static aayush.atharva.igi2.server.console.adminpanel.Main.TakePort;
import static aayush.atharva.igi2.server.console.adminpanel.Main.echo;
import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Aayush Atharva
 */
public class Start {

    public static void startrun() {
        Scanner sc = new Scanner(System.in);

        echo("-------------------------------------------------------------");
        echo("IGI-2 Server Console Admin Panel v1.0");
        echo("Created By: Aayush Atharva");
        echo("Email: aayush@igi2.co.in");
        echo("Website: www.igi-2.com");
        echo("-------------------------------------------------------------");
        echo("-------------------------------------------------------------");
        echo("Enter Server Port");
        TakePort = sc.next();

        try {
            Port = Integer.parseInt(TakePort);
        } catch (Exception e) {
            System.out.println("Port Is Invalid");
            JOptionPane.showMessageDialog(null, "Server Not Running...", "Server Not Running...", JOptionPane.ERROR_MESSAGE);
        }

        echo("Server Port: " + Port);
        echo("Enter Server RCON");
        RCON = sc.next();
        echo("Server RCON: " + Port);
        echo("--------------------------");
        echo("Server Port:" + Port + " | Server RCON: " + RCON);
        echo("Press Y To Confirm");

        String confirm = sc.next();
        if (confirm.equals("y") || confirm.equals("Y")) {
            if (!new File("Multiplayer.log").exists()) {
                JOptionPane.showMessageDialog(null, "Server Not Running...", "Server Not Running...", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            LogAnalyze log = new LogAnalyze();
            log.start();
        } else {
            echo("Cancelled");
            System.exit(0);
        }
    }
}
