package pl.mr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.mr.model.Child;
import pl.mr.repository.CampRepository;
import pl.mr.repository.ChildRepository;
import pl.mr.service.CampService;
import pl.mr.service.ChildService;

import javax.validation.Valid;

@Controller
public class ChildController {
    private ChildRepository childRepository;
    private ChildService childService;
    private CampRepository campRepository;
    private CampService campService;


    public ChildController(ChildRepository childRepository, ChildService childService, CampRepository campRepository, CampService campService) {
        this.childRepository = childRepository;
        this.childService = childService;
        this.campRepository = campRepository;
        this.campService = campService;
    }

    @GetMapping("/find")
    public String findForm(Model model){
        model.addAttribute("childbypesel",new Child());
        return "find";
    }
//    @PostMapping("/find")
//    public String find(@Valid String pesel, BindingResult result, Model model) {
//            if (childService.checkExist(pesel)) {
//                Child childByPesel = childRepository.findChildByPesel(pesel);
//                model.addAttribute("childbypesel", childByPesel);
//                return "findSuccess";
//            }else
//                return "findError";
//    }
    @PostMapping("/find")
    public String find(@Valid Child child, BindingResult result, Model model) {
            if (childService.checkExist(child.getPesel())) {
                Child childByPesel = childRepository.findChildByPesel(child.getPesel());
                model.addAttribute("childbypesel", childByPesel);
                return "findSuccess";
            } else
                return "findError";
    }
    @GetMapping("/findSuccess")
    public String success() {
        return "findSuccess";
    }

    @GetMapping("/findError")
    public String error() {
        return "findError";
    }
}
