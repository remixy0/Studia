package org.example;

import java.util.ArrayList;
import java.util.List;

public class Team {
    List<Developer> developers = new ArrayList<>();
    List<Project> projects = new ArrayList<>();

    public void addDeveloper(Developer developer){
        developers.add(developer);
    }

    public void addProject(Project project){
        projects.add(project);
    }

    public void showTeamInfo(){
        for(Developer developer:developers){
            developer.showInfo();
        }
        for(Project project:projects){
            project.showInfo();
        }
    }
}

class Developer{
    String name;
    String specialization;
    int yearsOfExperience;

    public void showInfo(){
        System.out.println("Developer Name: "+name);
        System.out.println("Developer Specialization: "+specialization);
        System.out.println("Developer Years of Experience: "+yearsOfExperience);
        System.out.println("");
    }
}

class Project{
    String name;
    String technology;
    String finishDate;

    public void showInfo(){
        System.out.println("Project Name: "+name);
        System.out.println("Project Technology: "+technology);
        System.out.println("Project Finish Date: "+finishDate);
        System.out.println("");
    }
}