package matej.tejkogames.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import matej.tejkogames.api.services.YambChallengeServiceImpl;
import matej.tejkogames.interfaces.controllers.YambChallengeController;
import matej.tejkogames.models.yamb.YambChallenge;
import matej.tejkogames.models.general.enums.MessageType;
import matej.tejkogames.models.general.payload.requests.YambChallengeRequest;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

@RestController
@RequestMapping("/api/challenges")
public class YambChallengeControllerImpl implements YambChallengeController {

    @Autowired
    YambChallengeServiceImpl yambChallengeService;

    @PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtUtil.getUsernameFromHeader(#headerAuth), #id, 'YambChallenge')")
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<YambChallenge> getById(UUID id) {
        return new ResponseEntity<>(yambChallengeService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("")
    @Override
    public ResponseEntity<List<YambChallenge>> getAll(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "desc") String direction) {
        return new ResponseEntity<>(yambChallengeService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("")
    @Override
    public ResponseEntity<YambChallenge> create(@RequestBody YambChallengeRequest requestBody) {
        return new ResponseEntity<>(yambChallengeService.create(requestBody), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<YambChallenge> updateById(@PathVariable UUID id,
            @RequestBody YambChallengeRequest requestBody) {
        return new ResponseEntity<>(yambChallengeService.updateById(id, requestBody), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("")
    @Override
    public ResponseEntity<List<YambChallenge>> updateAll(@RequestBody Map<UUID, YambChallengeRequest> idRequestMap) {
        return new ResponseEntity<>(yambChallengeService.updateAll(idRequestMap), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<MessageResponse> deleteById(@RequestHeader(value = "Authorization") String headerAuth,
            @PathVariable UUID id) {
        yambChallengeService.deleteById(id);
        return new ResponseEntity<>(new MessageResponse("", MessageType.DEFAULT, ""), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("")
    @Override
    public ResponseEntity<MessageResponse> deleteAll(@RequestHeader(value = "Authorization") String headerAuth) {
        yambChallengeService.deleteAll();
        return new ResponseEntity<>(new MessageResponse("", MessageType.DEFAULT, ""), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/bulk")
    @Override
    public ResponseEntity<MessageResponse> deleteAllById(@RequestHeader(value = "Authorization") String headerAuth,
            @RequestBody Set<UUID> idSet) {
        yambChallengeService.deleteAllById(idSet);
        return new ResponseEntity<>(
                new MessageResponse("Yamb Challenge", MessageType.DEFAULT, "All users have been successfully deleted"),
                HttpStatus.OK);
    }
}
