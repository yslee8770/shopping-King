package com.shopping.mapper;

import com.shopping.dto.InventoryRequestDTO;
import com.shopping.dto.InventoryResponseDTO;
import com.shopping.entity.Inventory;

public class InventoryMapper {
  public static InventoryResponseDTO toResponseDTO(Inventory inventory) {
    if (inventory == null) {
      return null;
    }
    return InventoryResponseDTO.builder()
        .id(inventory.getId())
        .quantity(inventory.getQuantity())
        .build();
  }

  public static Inventory toEntity(InventoryRequestDTO inventoryRequestDTO) {
    return Inventory.builder()
        .quantity(inventoryRequestDTO.getQuantity())
        .build();
  }
}
