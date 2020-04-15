package pl.ryzykowski.mapdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.ryzykowski.mapdemo.service.Covid19Parser;

@Controller
public class MapController {

    @Autowired
    private Covid19Parser covid19Parser;

    @GetMapping
    public String getMap(Model model){
        model.addAttribute("points", covid19Parser.getCovidData());
        return "map";
    }

}
