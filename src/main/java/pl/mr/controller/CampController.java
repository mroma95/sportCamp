package pl.mr.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mr.model.Camp;
import pl.mr.model.Child;
import pl.mr.model.User;
import pl.mr.repository.CampRepository;
import pl.mr.repository.ChildRepository;
import pl.mr.repository.UserRepository;
import pl.mr.service.CampService;
import pl.mr.service.CustomUserDetailService;

import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@Controller
public class CampController {
    private CampRepository campRepository;
    private ChildRepository childRepository;
    private UserRepository userRepository;
    private CampService campService;

    public CampController(CampRepository campRepository, ChildRepository childRepository, UserRepository userRepository, CampService campService) {
        this.campRepository = campRepository;
        this.childRepository = childRepository;
        this.userRepository = userRepository;
        this.campService = campService;
    }

    @GetMapping("/campRegistration")
    public String addChildForm(Model model) {
        model.addAttribute("formChild", new Child());
        List<Camp> campList = campService.initData();
        model.addAttribute("campList", campList);
        return "campRegistration";
    }

    @PostMapping("/campRegistration")
    public String addChild(@Valid @ModelAttribute("formChild") Child formChild, BindingResult result, @ModelAttribute Camp camp, Model model) {
        Child childExist=childRepository.findChildByPesel(formChild.getPesel());
        if (childExist!=null){
            result.rejectValue("pesel","error.child.pesel","Dziecko o podanym numerze pesel jest już zapisane na obóz");
            model.addAttribute("campList",campService.initData());
        }
        if (result.hasErrors()){
            model.addAttribute("campList",campService.initData());
            return "campRegistration";
        }else {
            model.addAttribute("formChild", formChild);
            campService.registerChild(formChild);
            childRepository.save(formChild);
            camp.getChildren().add(formChild);
            return "success";

        }
    }
}
