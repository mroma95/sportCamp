package pl.mr.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.mr.model.Camp;
import pl.mr.model.Child;
import pl.mr.model.User;
import pl.mr.repository.CampRepository;
import pl.mr.repository.ChildRepository;
import pl.mr.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampService {
    private CampRepository campRepository;
    private UserRepository userRepository;
    private ChildRepository childRepository;

    public CampService(CampRepository campRepository, UserRepository userRepository, ChildRepository childRepository) {
        this.campRepository = campRepository;
        this.userRepository = userRepository;
        this.childRepository = childRepository;
    }

    private List<Camp> camps=new ArrayList<>();



    public List<Camp> initData() {
        List<Camp> all = campRepository.findAll();
        return all;
    }

    public List<Camp> getCamps() {
        return camps;
    }

    public void registerChild(Child child) {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userPrincipal = (UserDetails) auth.getPrincipal();
        child.setUser(userRepository.findByEmail(userPrincipal.getUsername()));

    }
}
