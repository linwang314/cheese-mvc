package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.launchcode.cheesemvc.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute("cheese", new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }
;
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese, Errors errors, Model model) {

        if (errors.hasErrors()){
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/add";
        }

        CheeseData.add(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", CheeseData.getAll());
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            CheeseData.remove(cheeseId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        Cheese theCheese = CheeseData.getById(cheeseId);
        model.addAttribute("cheeseTypes", CheeseType.values());
        model.addAttribute("cheese", theCheese);
        model.addAttribute("title", "Edit Cheese " + theCheese.getName() + " (id=" + theCheese.getCheeseId() + ")");

        return "cheese/edit";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.POST)
    public String processEditForm(@PathVariable int cheeseId, @ModelAttribute @Valid Cheese aCheese, Errors errors, Model model) {

        if (errors.hasErrors()) {
            Cheese theCheese = CheeseData.getById(cheeseId);
            model.addAttribute("cheeseTypes", CheeseType.values());
            // model.addAttribute("cheese", theCheese);
            model.addAttribute("title", "Edit Cheese " + theCheese.getName() + " (id=" + theCheese.getCheeseId() + ")");
            return "cheese/edit";
        }

        CheeseData.getById(cheeseId).setName(aCheese.getName());
        CheeseData.getById(cheeseId).setDescription(aCheese.getDescription());
        CheeseData.getById(cheeseId).setType(aCheese.getType());
        CheeseData.getById(cheeseId).setRating(aCheese.getRating());

        // "redirect:" doesn't work...
        return "redirect:/cheese";
    }

}
