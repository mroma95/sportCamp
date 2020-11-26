package pl.mr.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mr.model.User;
import pl.mr.model.UserRole;
import pl.mr.repository.UserRepository;
import pl.mr.repository.UserRoleRepository;

import java.util.Date;

@Service
@Data
public class UserService {
    private final static String DEFAULT_ROLE = "ROLE_USER";
    private JavaMailSender javaMailSender;
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, JavaMailSender javaMailSender, UserRoleRepository userRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
        this.userRoleRepository = userRoleRepository;
    }

    public User registerUser(User user) {
        User savedUser = userRepository.save(user);
        sendConfirmationEmail(savedUser);
        return savedUser;
    }

    private void sendConfirmationEmail(User savedUser) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("m.romaniak@outlook.com");
        message.setSentDate(new Date());
        message.setTo(savedUser.getEmail());
        message.setSubject("Rejestracja na obóż piłkarski");
        message.setText("Rejestracja zakończona sukcesem");
        javaMailSender.send(message);
    }

    public void addWithDefaultRole(User user) {
        UserRole defaultRole = userRoleRepository.findByRole(DEFAULT_ROLE);
        user.getUserRoles().add(defaultRole);
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        userRepository.save(user);
    }
}
