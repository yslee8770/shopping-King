package com.shopping.web;

import com.shopping.dto.ProductImageResponseDTO;
import com.shopping.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

  private final ImageService imageService;

  @PostMapping("/{productId}")
  public ResponseEntity<ProductImageResponseDTO> uploadImage(@PathVariable Long productId, @RequestParam("file") MultipartFile file) {
    return ResponseEntity.ok(imageService.uploadImage(productId, file));
  }

  @DeleteMapping("/{imageId}")
  public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
    imageService.deleteImage(imageId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{productId}")
  public ResponseEntity<List<ProductImageResponseDTO>> getImagesByProductId(@PathVariable Long productId) {
    return ResponseEntity.ok(imageService.getImagesByProductId(productId));
  }
}
