package com.shopping.service;

import com.shopping.dto.InventoryRequestDTO;
import com.shopping.dto.InventoryResponseDTO;
import com.shopping.entity.Inventory;
import com.shopping.exception.CustomException;
import com.shopping.mapper.InventoryMapper;
import com.shopping.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

  private final InventoryRepository inventoryRepository;

  public InventoryResponseDTO getInventoryByProductId(Long productId) {
    Inventory inventory = inventoryRepository.findByProductId(productId)
        .orElseThrow(() -> new CustomException("Inventory not found"));
    return InventoryMapper.toResponseDTO(inventory);
  }

  public InventoryResponseDTO updateInventory(Long productId, InventoryRequestDTO inventoryRequestDTO) {
    Inventory inventory = inventoryRepository.findByProductId(productId)
        .orElseThrow(() -> new CustomException("Inventory not found"));
    inventory.updateQuantity(inventoryRequestDTO.getQuantity());
    return InventoryMapper.toResponseDTO(inventoryRepository.save(inventory));
  }
}
