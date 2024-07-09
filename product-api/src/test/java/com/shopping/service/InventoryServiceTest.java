package com.shopping.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.shopping.dto.InventoryRequestDTO;
import com.shopping.dto.InventoryResponseDTO;
import com.shopping.entity.Inventory;
import com.shopping.exception.CustomException;
import com.shopping.repository.InventoryRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class InventoryServiceTest {

  @Mock
  private InventoryRepository inventoryRepository;

  @InjectMocks
  private InventoryService inventoryService;

  public InventoryServiceTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void getInventoryByProductId_success() {
    Inventory inventory = Inventory.builder().id(1L).quantity(100).build();
    when(inventoryRepository.findByProductId(anyLong())).thenReturn(Optional.of(inventory));

    InventoryResponseDTO inventoryDTO = inventoryService.getInventoryByProductId(1L);

    verify(inventoryRepository, times(1)).findByProductId(anyLong());
  }

  @Test
  public void getInventoryByProductId_notFound() {
    when(inventoryRepository.findByProductId(anyLong())).thenReturn(Optional.empty());

    assertThrows(CustomException.class, () -> {
      inventoryService.getInventoryByProductId(1L);
    });

    verify(inventoryRepository, times(1)).findByProductId(anyLong());
  }

  @Test
  public void updateInventory_success() {
    Inventory inventory = Inventory.builder().id(1L).quantity(100).build();
    when(inventoryRepository.findByProductId(anyLong())).thenReturn(Optional.of(inventory));

    InventoryRequestDTO inventoryRequestDTO = InventoryRequestDTO.builder().quantity(200).build();
    InventoryResponseDTO updatedInventory = inventoryService.updateInventory(1L, inventoryRequestDTO);

    verify(inventoryRepository, times(1)).findByProductId(anyLong());
    verify(inventoryRepository, times(1)).save(any(Inventory.class));
  }

  @Test
  public void updateInventory_notFound() {
    when(inventoryRepository.findByProductId(anyLong())).thenReturn(Optional.empty());

    InventoryRequestDTO inventoryRequestDTO = InventoryRequestDTO.builder().quantity(200).build();
    assertThrows(CustomException.class, () -> {
      inventoryService.updateInventory(1L, inventoryRequestDTO);
    });

    verify(inventoryRepository, times(1)).findByProductId(anyLong());
  }
}
