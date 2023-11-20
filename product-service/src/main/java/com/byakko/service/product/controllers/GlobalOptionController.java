package com.byakko.service.product.controllers;

import com.byakko.service.product.dtos.global_option.*;
import com.byakko.service.product.services.GlobalOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/globaloptions")
@RequiredArgsConstructor
public class GlobalOptionController {

    private final GlobalOptionService globalOptionService;

    @GetMapping
    public ResponseEntity<?> listAllOptions(@ModelAttribute ListAllOptionsCommand command) {
        return ResponseEntity.ok(globalOptionService.listAllOptions(command));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOption(@PathVariable("id") String id) {
        return ResponseEntity.ok(globalOptionService.getOption(new GetOptionCommand(id)));
    }

    @PostMapping
    public ResponseEntity<?> createOption(@RequestBody CreateOptionCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(globalOptionService.createOption(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOption(@PathVariable("id") String id, @RequestBody UpdateOptionCommand command) {
        command.setId(id);
        return ResponseEntity.ok(globalOptionService.updateOption(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOption(@PathVariable("id") String id) {
        globalOptionService.deleteOption(new DeleteOptionCommand(id));
        return ResponseEntity.ok("Delete success");
    }

}
