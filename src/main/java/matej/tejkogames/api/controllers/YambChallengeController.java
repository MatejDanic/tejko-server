package matej.tejkogames.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<YambChallenge> getById(@PathVariable UUID id) {
        return new ResponseEntity<>(yambChallengeService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<YambChallenge>> getBulkById(@RequestBody Set<UUID> idSet) {
        return new ResponseEntity<>(yambChallengeService.getBulkById(idSet), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<YambChallenge>> getAll(@PathVariable Integer page, @PathVariable Integer size,
            @PathVariable String sort, @PathVariable String direction) {
        return new ResponseEntity<>(yambChallengeService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @Override
    public ResponseEntity<YambChallenge> create(@RequestBody YambChallengeRequest objectRequest) {
        return new ResponseEntity<>(yambChallengeService.create(objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<YambChallenge>> createBulk(@RequestBody List<YambChallengeRequest> objectRequestList) {
        return new ResponseEntity<>(yambChallengeService.createBulk(objectRequestList), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<YambChallenge> updateById(@PathVariable UUID id,
            @RequestBody YambChallengeRequest objectRequest) {
        return new ResponseEntity<>(yambChallengeService.updateById(id, objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<YambChallenge>> updateBulkById(
            @RequestBody Map<UUID, YambChallengeRequest> idObjectRequestMap) {
        return new ResponseEntity<>(yambChallengeService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteById(@PathVariable UUID id) {
        yambChallengeService.deleteById(id);
        return new ResponseEntity<>(new MessageResponse("Yamb Challenge", "Challenge have been successfully deleted"),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteBulkById(@RequestBody Set<UUID> idSet) {
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
