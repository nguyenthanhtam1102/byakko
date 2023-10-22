package com.byakko.service.authentication.application.rest;

import com.byakko.service.authentication.domain.domainapplication.dto.role.CreateRoleCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.role.DeleteRoleCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.role.ListAllRoleCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.role.UpdateRoleCommand;
import com.byakko.service.authentication.domain.domainapplication.port.input.service.AuthenticationApplicationService;
import com.byakko.service.authentication.domain.domainapplication.port.input.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<?> listAllRoles(@ModelAttribute ListAllRoleCommand command) {
        return ResponseEntity.ok(roleService.listAllRoles(command));
    }

    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody CreateRoleCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable("id") String id, @RequestBody UpdateRoleCommand command) {
        command.setId(id);
        return ResponseEntity.ok(roleService.updateRole(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable("id") String id) {
        roleService.deleteRole(new DeleteRoleCommand(id));
        return ResponseEntity.ok("Delete success");
    }

}
