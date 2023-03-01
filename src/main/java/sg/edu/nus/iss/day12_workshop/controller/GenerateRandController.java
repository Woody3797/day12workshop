package sg.edu.nus.iss.day12_workshop.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.day12_workshop.exception.RandNoException;
import sg.edu.nus.iss.day12_workshop.model.Generate;

@Controller
@RequestMapping(path = "/rand")
public class GenerateRandController {

    @GetMapping(path = "/show")
    public String showRandForm(Model model) {
        Generate g = new Generate();
        model.addAttribute("generateObj", g);
        return "generate";
    }

    @GetMapping(path = "/generate")
    public String generate(@RequestParam Integer numberVal, Model model) {
        this.randomiseNum(model, numberVal.intValue());
        return "result";
    }

    @GetMapping(path = "/generate/{numberVal}")
    public String generate2(@PathVariable Integer numberVal, Model model) {
        this.randomiseNum(model, numberVal.intValue());
        return "result";
    }

    private void randomiseNum(Model model, int numOfGenNo) {
        int maxGen = 31;
        String[] imgNum = new String[maxGen];
        if (numOfGenNo < 1 || numOfGenNo > maxGen - 1) {
            throw new RandNoException();
        }

        for (int i = 0; i < maxGen; i++) {
            imgNum[i] = "number" + i + ".jpg";
        }

        List<String> selectedImg = new ArrayList<>();
        Random rand = new Random();
        Set<Integer> uniqueResult = new LinkedHashSet<>();

        while (uniqueResult.size() < numOfGenNo) {
            Integer randNumberVal = rand.nextInt(maxGen);
            if (randNumberVal != null) {
                uniqueResult.add(randNumberVal);
            }
        }

        Iterator<Integer> i = uniqueResult.iterator();
        Integer currElem = null;
        while (i.hasNext()) {
            currElem = i.next();
            selectedImg.add(imgNum[currElem.intValue()]);
        }

        model.addAttribute("numberRandNum", numOfGenNo);
        model.addAttribute("randNumResult", selectedImg.toArray());

    }
}
