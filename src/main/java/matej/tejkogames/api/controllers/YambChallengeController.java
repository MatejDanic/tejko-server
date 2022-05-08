package matej.tejkogames.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import matej.tejkogames.api.services.YambChallengeService;
import matej.tejkogames.interfaces.api.controllers.YambChallengeControllerInterface;
import matej.tejkogames.models.general.YambChallenge;
import matej.tejkogames.models.general.enums.MessageType;
import matej.tejkogames.models.general.payload.requests.YambChallengeRequest;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

@RestController
@RequestMapping("/api/challenges")
public class YambChallengeController implements YambChallengeControllerInterface {

    @Autowired
    YambChallengeService yambChallengeService;

    @PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), yambChallengeService.getById(#id))")
    @Override
    public ResponseEntity<YambChallenge> getById(UUID id) {
        return new ResponseEntity<>(yambChallengeService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<YambChallenge>> getBulkById(Set<UUID> idSet) {
        return new ResponseEntity<>(yambChallengeService.getBulkById(idSet), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<YambChallenge>> getAll(Integer page, Integer size, String sort, String direction) {
        return new ResponseEntity<>(yambChallengeService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @Override
    public ResponseEntity<YambChallenge> create(YambChallengeRequest objectRequest) {
        return new ResponseEntity<>(yambChallengeService.create(objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<YambChallenge>> createBulk(List<YambChallengeRequest> objectRequestList) {
        return new ResponseEntity<>(yambChallengeService.createBulk(objectRequestList), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<YambChallenge> updateById(UUID id, YambChallengeRequest objectRequest) {
        return new ResponseEntity<>(yambChallengeService.updateById(id, objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<YambChallenge>> updateBulkById(Map<UUID, YambChallengeRequest> idObjectRequestMap) {
        return new ResponseEntity<>(yambChallengeService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteById(UUID id) {
        yambChallengeService.deleteById(id);
        return new ResponseEntity<>(new MessageResponse("Yamb Challenge", "Challenge have been successfully deleted"),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteBulkById(Set<UUID> idSet) {
        yambChallengeService.deleteBulkById(idSet);
        return new ResponseEntity<>(
                new MessageResponse("Yamb Challenge", MessageType.DEFAULT,
                        "All challenges have been successfully deleted."),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteAll() {
        yambChallengeService.deleteAll();
        return new ResponseEntity<>(
                new MessageResponse("Yamb Challenge", "All challenges have been successfully deleted."), HttpStatus.OK);
    }
}
