import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        User marek = new User("mareczek","haslo_marka", User.Role.GUEST);
        User pawel = new User("pawelos","haslo_pawla", User.Role.OPERATOR);
        marek.wyswietlDane();
        pawel.wyswietlDane();
        marek.zmienhaslo("zmienione_haslo_marka");

        Server minecraft_server = new Server("123.123","minecraft",22,46.8, Server.Status.running);
        Server plex_server = new Server("123.255","plex",68,67.2, Server.Status.running);
        plex_server.increaseCpuUsage(12);
        plex_server.showStatus();
        minecraft_server.increaseCpuUsage(32);
        minecraft_server.showStatus();

        TemperatureSensor czujnik_rzepin = new TemperatureSensor(123,"rzepin",52);
        TemperatureSensor czujnik_slubice = new TemperatureSensor(125,"slubice",55);

        for(int i = 0; i < 10; i++) {
            System.out.println(czujnik_rzepin.readTemperature());

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


        }

        while(czujnik_slubice.didOverheat() == false) {
            System.out.println(czujnik_slubice.readTemperature());

        }




        Connection con1 = new Connection();
        Connection con2 = new Connection("komputer","9999",2222,"admin",true);
        con1.connect();
        con2.connect();
        con1.disconnect();
        con2.disconnect();



    }
}


class User{
    private String login;
    private String password;
    private Role rola;
    private IPaddress ipaddress = new IPaddress();


    enum Role{
        ADMINISTRATOR, OPERATOR, GUEST
    }

    public User(String login, String password, Role rola ){
        this.login = login;
        this.password = password;
        this.rola = rola;
    }

    public void wyswietlDane(){
        System.out.println("Login: " + login + "   Rola: " + rola + " IPAddress: " + ipaddress.toString());
    }

    public void zmienhaslo(String password){
        this.password = password;
    }
}

class Server {
    String ip_address;
    String name;
    Status status;

    enum Status {
        running, error, rebooting
    }

    int cpuUsage;
    double temperature;

    public Server(String ip_address, String name, int cpuUsage, double temperature, Status status) {
        this.ip_address = ip_address;
        this.name = name;
        this.cpuUsage = cpuUsage;
        this.temperature = temperature;
        this.status = status;


    }

    public void showStatus() {
        System.out.println("Server status: " + status);
    }

    public void increaseCpuUsage(int percentage) {

        if(cpuUsage + percentage <= 100){
            cpuUsage += percentage;
        }else{
            cpuUsage = 100;
        }

        temperature += (percentage / 10) * 0.5;
        Random rand = new Random();
        int randomNumber = rand.nextInt(4);
        if (randomNumber == 1) {
            status = Status.error;
        }
    }

}

class TemperatureSensor{
    int id;
    String location;
    int lastReadingValue;
    public TemperatureSensor(int id, String location, int lastReadingValue){
        this.id = id;
        this.location = location;
        this.lastReadingValue = lastReadingValue;
    }

    public int readTemperature(){
        Random rand = new Random();
        lastReadingValue = rand.nextInt(100);
        return lastReadingValue;
    }

    public boolean didOverheat(){
        return lastReadingValue > 50;
    }


}

class Connection{
    String baseName;
    String serverAddress;
    int port;
    String user;
    boolean activeConnection;

    public Connection(){
        this.serverAddress = "localhost";
        this.port = 3306;
        this.user = "root";
        this.activeConnection = true;
    }
    public Connection(String baseName){
        this.baseName = baseName;
    }

    public Connection(String baseName, String serverAddress, int port, String user, boolean activeConnection){
        this.baseName = baseName;
        this.serverAddress = serverAddress;
        this.port = port;
        this.user = user;
        this.activeConnection = activeConnection;
    }

    public void connect(){
        System.out.println("Connected to: " + baseName + " on server: " +serverAddress+":"+port);
        this.activeConnection = true;
    }

    public void disconnect(){
        this.activeConnection = false;
    }



}

class IPaddress{
    String ipAddress = "127.0.0.1";
    String mask = "111.111";
    public String toString(){
        return ipAddress + ":" + mask;
    }
}

