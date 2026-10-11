package org.example;


public class Main {
    public static void main(String[] args) {
    System.out.println("ZAD 1");
    Server server = new Server();
    server.addHardDrive(new HardDrive());
    server.addHardDrive(new HardDrive(2048,800,"HDD"));
    server.showHardwareInfo();

    Server server_2 = new Server(21212121,128,64);
    server_2.addHardDrive(new HardDrive(4096,4000,"SSD"));
    server_2.addHardDrive(new HardDrive(1024,650,"HDD"));
    server_2.showHardwareInfo();
    System.out.println("");

    System.out.println("ZAD 2");

    AdminPanel adminPanel = new AdminPanel();
    adminPanel.addUser(new UserAccount("marek","haslo123"));
    adminPanel.showUsers();

    System.out.println("ZAD 3");
    Employee employee1 = new Employee("Marek","Kowalski","acountant");
    Device device1 = new Device("Monitor","Acer","X27U");
    Device device2 = new Device("Computer","Dell","2X34D");

    Employee employee2 = new Employee("Andrzej","Smith","manager");
    Device device3 = new Device("laptop","HP","D2RT");
    Device device4 = new Device("Mouse","logitech","G-Pro");

    BookingSystem bookingSystem = new BookingSystem();

    bookingSystem.bookDevice(employee1,device1);
    bookingSystem.bookDevice(employee1,device2);

    bookingSystem.bookDevice(employee2,device3);
    bookingSystem.bookDevice(employee2,device4);

    bookingSystem.showActiveBookings();


    System.out.println("ZAD 4");
    DataCenter dataCenter = new DataCenter();

    Server2 server2 = new Server2();
    NetworkSwitch networkSwitch = new NetworkSwitch();

    dataCenter.addServer(server2);
    dataCenter.addServer(server2);
    dataCenter.addSwitch(networkSwitch);
    dataCenter.showInfrasctructure();

    System.out.println("ZAD 6");
    Database database = new Database();
    Table table = new Table("tabelka1",6,8);
    Table table2 = new Table("tabelka2",4,12);
    database.adddTable(table);
    database.adddTable(table2);
    database.showTables();
    database.getTotalRecords();

    System.out.println("ZAD 7");
    WebApplication webApplication = new WebApplication("Company","1.2",true);
    Developer2 developer = new Developer2("Marek","Admin");
    Module module = new Module("Strona","companys new website","1.3.2");
    Developer2 developer2 = new Developer2("Stefan","Admin");
    Module module2 = new Module("Aplikacja","companys new website","1.3.2");

    webApplication.addModule(module);
    webApplication.assignDeveloperToModule(module, developer);

    webApplication.addModule(module2);
    webApplication.assignDeveloperToModule(module2, developer2);

    webApplication.showModules();



    }
}