package matej.tejko.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import matej.tejko.api.services.UserChallengeService;
import matej.tejko.interfaces.api.controllers.UserChallengeControllerInterface;
import matej.tejko.models.general.UserChallenge;
import matej.tejko.models.general.ids.UserChallengeId;
import matej.tejko.models.general.payload.requests.UserChallengeRequest;
import matej.tejko.models.general.payload.responses.MessageResponse;

@RestController
@RequestMapping("/api/user-challenges")
public class UserChallengeController implements UserChallengeControllerInterface {

    @Autowired
    UserChallengeService userChallengeService;

    @PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userChallengeService.getById(#id))")
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<UserChallenge> getById(@PathVariable UserChallengeId id) {
        return new ResponseEntity<>(userChallengeService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/bulk")
    @Override
    public ResponseEntity<List<UserChallenge>> getBulkById(@RequestBody Set<UserChallengeId> idSet) {
        return new ResponseEntity<>(userChallengeService.getBulkById(idSet), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("")
    @Override
    public ResponseEntity<List<UserChallenge>> getAll(@PathVariable Integer page, @PathVariable Integer size,
            @PathVariable String sort, @PathVariable String direction) {
        return new ResponseEntity<>(userChallengeService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("")
    @Override
    public ResponseEntity<UserChallenge> create(@RequestBody UserChallengeRequest objectRequest) {
        return new ResponseEntity<>(userChallengeService.create(objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/bulk")
    @Override
    public ResponseEntity<List<UserChallenge>> createBulk(
            @RequestBody List<UserChallengeRequest> objectRequestList) {
        return new ResponseEntity<>(userChallengeService.createBulk(objectRequestList), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<UserChallenge> updateById(@PathVariable UserChallengeId id,
            @RequestBody JsonPatch objectRequest) throws JsonProcessingException, JsonPatchException {
        return new ResponseEntity<>(userChallengeService.updateById(id, objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/bulk")
    @Override
    public ResponseEntity<List<UserChallenge>> updateBulkById(
            @RequestBody Map<UserChallengeId, JsonPatch> idObjectRequestMap)
            throws JsonProcessingException, JsonPatchException {
        return new ResponseEntity<>(userChallengeService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<MessageResponse> deleteById(@PathVariable UserChallengeId id) {
        userChallengeService.deleteById(id);
        return new ResponseEntity<>(
                new MessageResponse("User Yamb Challenge", "User Challenge has been successfully deleted."),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/bulk")
    @Override
    public ResponseEntity<MessageResponse> deleteBulkById(@RequestBody Set<UserChallengeId> idSet) {
        userChallengeService.deleteBulkById(idSet);
        return new ResponseEntity<>(
                new MessageResponse("User Yamb Challenge",
                        "All user challenges have been successfully deleted"),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("")
    @Override
    public ResponseEntity<MessageResponse> deleteAll() {
        userChallengeService.deleteAll();
        return new ResponseEntity<>(
                new MessageResponse("User Yamb Challenge", "All user challenges have been successfully deleted."),
                HttpStatus.OK);
    }

}
