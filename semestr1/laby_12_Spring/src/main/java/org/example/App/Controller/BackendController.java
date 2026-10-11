package org.example.App.Controller;

import org.springframework.ui.Model;
import org.example.App.Repository.Repository;
import org.example.App.Model.Effort;
import org.example.App.Model.IMU;
import org.example.App.Model.LiDAR;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class BackendController {
    Repository repository = new Repository();

    @GetMapping("/efforts")
    public String getEfforts(Model model) {
        var allSensors = repository.getSensors();
        model.addAttribute("sensors", allSensors);
        return "efforts-html.html";
    }

    @ResponseBody
    @GetMapping("/analysis")
    public String analysis() {return repository.getAnalysis();}


    @ResponseBody
    @PostMapping("/effort")
    public String addNewEffort(@RequestBody Effort effort) {
        switch (effort.getSensorType()){
            case IMU -> repository.addSensor(new IMU(effort));
            case LIDAR -> repository.addSensor(new LiDAR(effort));
        }
        return "added new effort";
    }

    @ResponseBody
    @PutMapping("/effort/{id}")
    public String updateEffort(@PathVariable int id, @RequestBody Effort newEffortData) {
        boolean isUpdated = repository.updateSensor(id, newEffortData);
        if (isUpdated) {
            return "Effort with ID " + id + " updated successfully.";
        } else return "Effort not found.";

    }

    @ResponseBody
    @DeleteMapping("/effort/{id}")
    public String updateEffort(@PathVariable int id) {
        boolean isDone = repository.deleteSensor(id);
        if (isDone) {
            return "Effort with ID " + id + " deleted successfully.";
        }else return "Effort not found.";

    }

}
