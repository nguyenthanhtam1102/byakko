package com.byakko.service.sales.service.production.application.rest;

import com.byakko.service.production.domain.domainapplication.dto.asset.*;
import com.byakko.service.sales.service.production.domain.domainapplication.port.input.AssetService;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.asset.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assets")
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @GetMapping
    public ResponseEntity<?> listAllAssets(@ModelAttribute ListAllAssetCommand command) {
        return ResponseEntity.ok(assetService.listAllAssets(command));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAsset(@PathVariable("id") String id) {
        return ResponseEntity.ok(assetService.getAsset(new GetAssetCommand(id)));
    }

    @PostMapping
    public ResponseEntity<?> createAsset(@RequestBody CreateAssetCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(assetService.createAsset(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAsset(@PathVariable("id") String id, @RequestBody UpdateAssetCommand command) {
        command.setId(id);
        return ResponseEntity.ok(assetService.updateAsset(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAsset(@PathVariable("id") String id) {
        assetService.deleteAsset(new DeleteAssetCommand(id));
        return ResponseEntity.ok("Delete success");
    }

}
