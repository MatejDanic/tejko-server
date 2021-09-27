package matej.tejkogames.api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import matej.tejkogames.api.repositories.YambMatchRepository;
import matej.tejkogames.interfaces.services.YambMatchService;
import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.payload.requests.YambMatchRequest;
import matej.tejkogames.models.yamb.YambMatch;

@Service
public class YambMatchServiceImpl implements YambMatchService {

    @Autowired
    YambMatchRepository yambMatchRepository;

    @Override
    public YambMatch getById(UUID id) {
        return yambMatchRepository.findById(id).get();
    }

    @Override
    public List<YambMatch> getAll() {
        return yambMatchRepository.findAll();
    }

    @Override
    public YambMatch updateById(UUID id, YambMatchRequest requestBody) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public YambMatch create(YambMatchRequest requestBody) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        yambMatchRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        yambMatchRepository.deleteAll();
    }

    @Override
    public boolean hasPermission(UUID id, String username) {
        for (User user : getById(id).getUsers()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

}
