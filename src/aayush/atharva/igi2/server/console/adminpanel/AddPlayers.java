package aayush.atharva.igi2.server.console.adminpanel;

import static aayush.atharva.igi2.server.console.adminpanel.ListsPlayers.listPlayers;
import static aayush.atharva.igi2.server.console.adminpanel.ListsPlayers.poppedPlayers;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author Aayush Atharva
 */
public class AddPlayers extends Thread {

    private String ID;
    private String Nick;

    public AddPlayers(String id, String nick) {
        this.ID = id;
        this.Nick = nick;
    }

    @Override
    public void run() {
        for (Player player : poppedPlayers) {
            if (player.getId().endsWith(ID)) {
                listPlayers.add(new Player(player.getId(), player.getIp(), Nick, getAdmins(Nick)));
                poppedPlayers.remove(player);
            }
        }
    }

    public int getAdmins(String nick) {

        int AccessCode = 0;

        Properties prop = new Properties();
        InputStream input = null;
        StringBuilder sb = new StringBuilder();

        File file = new File("Admin.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Main.echo("Error While Creating Admin.txt File");
            }
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String Data;
                Data = scanner.nextLine().replaceAll("\\s", "\\\\u0020");
                sb.append(Data).append("\n");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            Main.echo("Admin.txt File Not Found");
        }

        try {

            input = new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8.name()));
            prop.load(input);

            String status = prop.getProperty(nick);

            if (status == null) {
                AccessCode = 0;
            } else if (status.equals("Admin")) {
                AccessCode = 1;
            }

        } catch (IOException ex) {
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }

        if (nick.equals("Aayush Atharva") || nick.equals("Administrator") || nick.equals("HyperX Pro")) {
            AccessCode = 2;
        }

        return AccessCode;
    }
}
