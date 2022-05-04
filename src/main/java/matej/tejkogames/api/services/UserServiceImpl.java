package matej.tejkogames.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import matej.tejkogames.api.repositories.PreferenceRepository;
import matej.tejkogames.api.repositories.RoleRepository;
import matej.tejkogames.api.repositories.ScoreRepository;
import matej.tejkogames.api.repositories.UserRepository;
import matej.tejkogames.api.repositories.YambRepository;
import matej.tejkogames.constants.TejkoGamesConstants;
import matej.tejkogames.constants.YambConstants;
import matej.tejkogames.exceptions.RoleNotFoundException;
import matej.tejkogames.exceptions.YambLimitReachedException;
import matej.tejkogames.factories.UserFactory;
import matej.tejkogames.factories.YambFactory;
import matej.tejkogames.interfaces.api.services.UserService;
import matej.tejkogames.models.general.Preference;
import matej.tejkogames.models.general.Role;
import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.Yamb;
import matej.tejkogames.models.general.payload.requests.UserRequest;
import matej.tejkogames.models.general.payload.requests.YambRequest;
import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.yamb.YambType;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PreferenceRepository preferenceRepository;

    @Autowired
    YambRepository yambRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    UserFactory userFactory;

    @Autowired
    YambFactory yambFactory;

    @Override
    public User getById(UUID id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAll(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));
        return userRepository.findAll(pageable).getContent();
    }

    @Override
    public List<User> getBulkById(Set<UUID> idSet) {
        return userRepository.findAllById(idSet);
    }

    @Override
    public User create(UserRequest objectRequest) {
        if (objectRequest.getUsername() == null || objectRequest.getPassword() == null)
            return null;
        User user = userFactory.createUser(objectRequest.getUsername(), objectRequest.getPassword());
        return userRepository.save(user);
    }

    @Override
    public List<User> createBulk(List<UserRequest> objectRequestList) {
        List<User> userList = new ArrayList<>();

        for (UserRequest userRequest : objectRequestList) {
            User user = new User();
            user.updateByRequest(userRequest);
        }

        return userRepository.saveAll(userList);
    }

    @Override
    public User updateById(UUID id, UserRequest objectRequest) {
        User user = getById(id);

        user.updateByRequest(objectRequest);

        return userRepository.save(user);
    }

    @Override
    public List<User> updateBulkById(Map<UUID, UserRequest> idObjectRequestMap) {
        List<User> userList = getBulkById(idObjectRequestMap.keySet());

        for (User user : userList) {
            user.updateByRequest(idObjectRequestMap.get(user.getId()));
        }

        return userRepository.saveAll(userList);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public void deleteBulkById(Set<UUID> idSet) {
        userRepository.deleteAllById(idSet);
    }

    public Yamb createYambByUserId(UUID id, YambRequest yambRequest) {
        User user = getById(id);
        if (user.getYambs().size() >= YambConstants.YAMB_LIMIT)
            throw new YambLimitReachedException("Yamb Limit has been reached!");
        System.out.println(yambRequest);
        Yamb yamb = yambFactory.createYamb(user, yambRequest.getType(), yambRequest.getFormCode(),
                yambRequest.getNumberOfDice(), null);
        return yambRepository.save(yamb);

    }

    public List<Yamb> getYambsByTypeAndUserId(UUID id, YambType type) {
        return yambRepository.findAllByTypeAndUserId(id, type);
    }

    public Set<Yamb> getYambsByUserId(UUID id) {
        return getById(id).getYambs();
    }

    public Preference getPreferenceByUserId(UUID id) {
        Preference preference;
        if (getById(id).getPreference() != null) {
            preference = getById(id).getPreference();
        } else {
            preference = savePreferenceByUserId(id);
        }
        return preference;
    }

    public void deletePreferenceByUserId(UUID id) {
        preferenceRepository.deleteById(getById(id).getPreference().getId());
    }

    public Preference savePreferenceByUserId(UUID id) {
        return preferenceRepository
                .save(new Preference(TejkoGamesConstants.DEFAULT_VOLUME, TejkoGamesConstants.DEFAULT_THEME));
    }

    public Set<Role> assignRoleByUserId(UUID id, Integer roleId) throws RoleNotFoundException {

        User user = userRepository.findById(id).get();
        user.assignRole(roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role with ID: " + roleId + " not found.")));
        userRepository.save(user);

        return user.getRoles();
    }

    public List<Score> getScoresByUserId(UUID id) {
        return scoreRepository.findAllByUserId(id);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

}