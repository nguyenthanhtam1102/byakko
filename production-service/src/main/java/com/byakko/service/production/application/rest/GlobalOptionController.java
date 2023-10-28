package com.byakko.service.production.application.rest;

import com.byakko.service.production.domain.domainapplication.dto.global_option.*;
import com.byakko.service.production.domain.domainapplication.port.input.GlobalOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/globaloptions")
@RequiredArgsConstructor
public class GlobalOptionController {

    private final GlobalOptionService globalOptionService;

    @GetMapping
    public ResponseEntity<?> listAllGlobalOption(@ModelAttribute ListAllGlobalOptionCommand command) {
        return ResponseEntity.ok(globalOptionService.listAllGlobalOption(command));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGlobalOption(@PathVariable("id") String id) {
        return ResponseEntity.ok(globalOptionService.getGlobalOption(new GetGlobalOptionCommand(id)));
    }

    @PostMapping
    public ResponseEntity<?> createGlobalOption(@RequestBody CreateGlobalOptionCommand command) {
        System.out.println("Create global option");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(globalOptionService.createGlobalOption(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGlobalOption(@PathVariable("id") String id,
                                                @RequestBody UpdateGlobalOptionCommand command) {
        command.setId(id);
        return ResponseEntity.ok(globalOptionService.updateGlobalOption(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGlobalOption(@PathVariable("id") String id) {
        globalOptionService.deleteGlobalOption(new DeleteGlobalOptionCommand(id));
        return ResponseEntity.ok("Delete success");
    }

}
