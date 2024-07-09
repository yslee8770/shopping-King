package com.shopping.web;

import com.shopping.dto.InventoryRequestDTO;
import com.shopping.dto.InventoryResponseDTO;
import com.shopping.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

  private final InventoryService inventoryService;

  @GetMapping("/{productId}")
  public ResponseEntity<InventoryResponseDTO> getInventoryByProductId(@PathVariable Long productId) {
    return ResponseEntity.ok(inventoryService.getInventoryByProductId(productId));
  }

  @PutMapping("/{productId}")
  public ResponseEntity<InventoryResponseDTO> updateInventory(@PathVariable Long productId, @RequestBody InventoryRequestDTO inventoryRequestDTO) {
    return ResponseEntity.ok(inventoryService.updateInventory(productId, inventoryRequestDTO));
  }
}
