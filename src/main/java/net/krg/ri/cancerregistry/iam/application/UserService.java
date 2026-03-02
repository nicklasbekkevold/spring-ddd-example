package net.krg.ri.cancerregistry.iam.application;

import net.krg.ri.cancerregistry.iam.domain.User;
import net.krg.ri.cancerregistry.iam.domain.UserNotFoundException;
import net.krg.ri.cancerregistry.iam.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void delete(UUID id) {
        findById(id);
        userRepository.deleteById(id);
    }
}
