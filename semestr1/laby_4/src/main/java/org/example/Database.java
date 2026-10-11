package org.example;

import java.util.ArrayList;
import java.util.List;

public class Database {
    String name;
    type type;
    List<Table> tables = new ArrayList<>();

    public Database() {
        this.name = "default";
        this.type = org.example.type.SQL;
    }

    public void adddTable(Table table) {
        tables.add(table);
    }

    public void showTables() {
        for (Table table : tables) {
            table.getInfo();
        }
    }

    public void getTotalRecords() {
        int sum = 0;
        for (Table table : tables) {
            sum += table.getNumberOfRecords();
        }
        System.out.println(sum);
    }



}


class Table {
    String name;
    int numberOfColumn;
    int numberOfRecords;

    public Table(String name, int numberOfColumn, int numberOfRecords) {
        this.name = name;
        this.numberOfColumn = numberOfColumn;
        this.numberOfRecords = numberOfRecords;
    }

    public void getInfo(){
        System.out.println(this.name);
        System.out.println(this.numberOfColumn);
        System.out.println(this.numberOfRecords);
        System.out.println("");
    }

    public int getNumberOfRecords() {
        return numberOfRecords;
    }

}


enum type{
    SQL, noSQL
}
