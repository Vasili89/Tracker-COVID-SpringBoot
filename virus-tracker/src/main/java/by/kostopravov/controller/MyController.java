package by.kostopravov.controller;

import by.kostopravov.model.Location;
import by.kostopravov.service.VirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    VirusDataService virusDataService;

    @GetMapping("/covid")
    public String homePage(Model model) {
        List<Location> reportList = virusDataService.getVirusData();
        int totalCases = reportList.stream().mapToInt(element -> element.getLastTotalCases()).sum();
        int dayTotalCases = reportList.stream().mapToInt(element -> element.getDelta()).sum();
        model.addAttribute("locations", reportList);
        model.addAttribute("totalCases", totalCases);
        model.addAttribute("dayTotalCases", dayTotalCases);
        return "index";
    }
}
