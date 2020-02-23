package aayush.atharva.igi2.server.console.adminpanel;

/**
 *
 * @author Aayush Atharva
 */
public class Player {

    private String nick;
    private String id;
    private int kills;
    private int deaths;
    private String ip;
    private int Access;
    private int warnings;

    public Player(String id, String ip, String nick, int access) {
        this.nick = nick;
        this.id = id;
        this.ip = ip;
        this.kills = 0;
        this.deaths = 0;
        this.Access = access;
        this.warnings = 0;
    }

    public Player(String id, String ip) {
        this.id = id;
        this.ip = ip;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNick() {
        return nick;
    }

    public int getAccess() {
        return Access;
    }

    public String getId() {
        return id;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void addKills() {
        this.kills++;
    }

    public void subKills() {
        this.kills--;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void addDeaths() {
        this.deaths++;
    }

    public void subDeaths() {
        this.deaths--;
    }

    public void addWarnings() {
        this.warnings = warnings + 1;
    }

    public void resetWarnings() {
        this.warnings = 0;
    }

    public int getWarnings() {
        return this.warnings;
    }
}
