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

import matej.tejko.api.services.AppService;
import matej.tejko.interfaces.api.controllers.AppControllerInterface;
import matej.tejko.models.general.Score;
import matej.tejko.models.general.App;
import matej.tejko.models.general.payload.requests.AppRequest;
import matej.tejko.models.general.payload.responses.MessageResponse;

@RestController
@RequestMapping("/api/apps")
public class AppController implements AppControllerInterface {

    @Autowired
    AppService appService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<App> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(appService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/bulk")
    @Override
    public ResponseEntity<List<App>> getBulkById(@RequestBody Set<Integer> idSet) {
        return new ResponseEntity<>(appService.getBulkById(idSet), HttpStatus.OK);
    }

    @GetMapping("")
    @Override
    public ResponseEntity<List<App>> getAll(@PathVariable Integer page, @PathVariable Integer size,
            @PathVariable String sort, @PathVariable String direction) {
        return new ResponseEntity<>(appService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("")
    @Override
    public ResponseEntity<App> create(@RequestBody AppRequest objectRequest) {
        return new ResponseEntity<>(appService.create(objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/bulk")
    @Override
    public ResponseEntity<List<App>> createBulk(@RequestBody List<AppRequest> objectRequestList) {
        return new ResponseEntity<>(appService.createBulk(objectRequestList), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<App> updateById(@PathVariable Integer id, @RequestBody JsonPatch objectRequest)
            throws JsonProcessingException, JsonPatchException {
        return new ResponseEntity<>(appService.updateById(id, objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/bulk")
    @Override
    public ResponseEntity<List<App>> updateBulkById(@RequestBody Map<Integer, JsonPatch> idObjectRequestMap)
            throws JsonProcessingException, JsonPatchException {
        return new ResponseEntity<>(appService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<MessageResponse> deleteById(@PathVariable Integer id) {
        appService.deleteById(id);
        return new ResponseEntity<>(new MessageResponse("App", "App has been successfully deleted"), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/bulk")
    @Override
    public ResponseEntity<MessageResponse> deleteBulkById(@RequestBody Set<Integer> idSet) {
        appService.deleteBulkById(idSet);
        return new ResponseEntity<>(new MessageResponse("App", "All apps have been successfully deleted"),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("")
    @Override
    public ResponseEntity<MessageResponse> deleteAll() {
        appService.deleteAll();
        return new ResponseEntity<>(new MessageResponse("App", "All apps have been successfully deleted"),
                HttpStatus.OK);
    }

    @GetMapping("/{id}/scores")
    @Override
    public ResponseEntity<List<Score>> getScoresByAppId(@PathVariable Integer id) {
        return new ResponseEntity<>(appService.getScoresByAppId(id), HttpStatus.OK);
    }

}
