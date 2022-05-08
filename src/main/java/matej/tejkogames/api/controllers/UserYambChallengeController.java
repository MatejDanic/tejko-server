package matej.tejkogames.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import matej.tejkogames.api.services.UserYambChallengeService;
import matej.tejkogames.interfaces.api.controllers.UserYambChallengeControllerInterface;
import matej.tejkogames.models.general.UserYambChallenge;
import matej.tejkogames.models.general.ids.UserYambChallengeId;
import matej.tejkogames.models.general.payload.requests.UserYambChallengeRequest;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

@RestController
@RequestMapping("/api/user-challenges")
public class UserYambChallengeController implements UserYambChallengeControllerInterface {

    @Autowired
    UserYambChallengeService userYambChallengeService;

    @PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userYambChallengeService.getById(#id))")
    @Override
    public ResponseEntity<UserYambChallenge> getById(UserYambChallengeId id) {
        return new ResponseEntity<>(userYambChallengeService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<UserYambChallenge>> getBulkById(Set<UserYambChallengeId> idSet) {
        return new ResponseEntity<>(userYambChallengeService.getBulkById(idSet), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<UserYambChallenge>> getAll(Integer page, Integer size, String sort, String direction) {
        return new ResponseEntity<>(userYambChallengeService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @Override
    public ResponseEntity<UserYambChallenge> create(UserYambChallengeRequest objectRequest) {
        return new ResponseEntity<>(userYambChallengeService.create(objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<UserYambChallenge>> createBulk(List<UserYambChallengeRequest> objectRequestList) {
        return new ResponseEntity<>(userYambChallengeService.createBulk(objectRequestList), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<UserYambChallenge> updateById(UserYambChallengeId id,
            UserYambChallengeRequest objectRequest) {
        return new ResponseEntity<>(userYambChallengeService.updateById(id, objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<UserYambChallenge>> updateBulkById(
            Map<UserYambChallengeId, UserYambChallengeRequest> idObjectRequestMap) {
        return new ResponseEntity<>(userYambChallengeService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteById(UserYambChallengeId id) {
        userYambChallengeService.deleteById(id);
        return new ResponseEntity<>(
                new MessageResponse("User Yamb Challenge", "User Challenge has been successfully deleted."),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteAll() {
        userYambChallengeService.deleteAll();
        return new ResponseEntity<>(
                new MessageResponse("User Yamb Challenge", "All user challenges have been successfully deleted."),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteBulkById(Set<UserYambChallengeId> idSet) {
        userYambChallengeService.deleteBulkById(idSet);
        return new ResponseEntity<>(
                new MessageResponse("User Yamb Challenge",
                        "All user challenges have been successfully deleted"),
                HttpStatus.OK);
    }
}
