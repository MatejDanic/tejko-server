package matej.tejkogames.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import matej.tejkogames.api.services.UserYambChallengeService;
import matej.tejkogames.models.general.UserYambChallenge;
import matej.tejkogames.models.general.ids.UserYambChallengeId;
import matej.tejkogames.models.general.payload.requests.UserYambChallengeRequest;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

@RestController
@RequestMapping("/api/user-challenges")
public class UserYambChallengeController {

    @Autowired
    UserYambChallengeService userYambChallengeService;

    @PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userYambChallengeService.getById(#id))")
    @GetMapping("/{id}")
    public ResponseEntity<UserYambChallenge> getById(UserYambChallengeId id) {
        return new ResponseEntity<>(userYambChallengeService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<UserYambChallenge>> getAll() {
        return new ResponseEntity<>(userYambChallengeService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("")
    public ResponseEntity<UserYambChallenge> create(@RequestBody UserYambChallengeRequest objectRequest) {
        return new ResponseEntity<>(userYambChallengeService.create(objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/bulk")
    public ResponseEntity<List<UserYambChallenge>> createBulk(
            @RequestBody List<UserYambChallengeRequest> objectRequestList) {
        return new ResponseEntity<>(userYambChallengeService.createBulk(objectRequestList), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserYambChallenge> updateById(@PathVariable UserYambChallengeId id,
            @RequestBody UserYambChallengeRequest objectRequest) {
        return new ResponseEntity<>(userYambChallengeService.updateById(id, objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/bulk")
    public ResponseEntity<List<UserYambChallenge>> updateBulkById(
            @RequestBody Map<UserYambChallengeId, UserYambChallengeRequest> idObjectRequestMap) {
        return new ResponseEntity<>(userYambChallengeService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteById(@RequestHeader(value = "Authorization") String headerAuth,
            @PathVariable UserYambChallengeId id) {
        userYambChallengeService.deleteById(id);
        return new ResponseEntity<>(
                new MessageResponse("User Yamb Challenge", "User Challenge has been successfully deleted."),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("")
    public ResponseEntity<MessageResponse> deleteAll(@RequestHeader(value = "Authorization") String headerAuth) {
        userYambChallengeService.deleteAll();
        return new ResponseEntity<>(
                new MessageResponse("User Yamb Challenge", "All user challenges have been successfully deleted."),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/bulk")
    public ResponseEntity<MessageResponse> deleteBulkById(@RequestHeader(value = "Authorization") String headerAuth,
            @RequestBody Set<UserYambChallengeId> idSet) {
        userYambChallengeService.deleteBulkById(idSet);
        return new ResponseEntity<>(
                new MessageResponse("User Yamb Challenge",
                        "All user challenges have been successfully deleted"),
                HttpStatus.OK);
    }
}
