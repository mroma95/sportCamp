package pl.mr.service;

import org.springframework.stereotype.Service;
import pl.mr.model.Child;
import pl.mr.repository.ChildRepository;
import pl.mr.repository.UserRepository;

@Service
public class ChildService {
    private ChildRepository childRepository;
    private UserRepository userRepository;

    public ChildService(ChildRepository childRepository, UserRepository userRepository) {
        this.childRepository = childRepository;
        this.userRepository = userRepository;
    }

    public boolean checkExist(String pesel){
        if (childRepository.findChildByPesel(pesel)!=null){
            return true;
        }else
            return false;
    }


}
