package com.shopping.service;

import com.shopping.dto.ProductImageResponseDTO;
import com.shopping.entity.Product;
import com.shopping.entity.ProductImage;
import com.shopping.exception.CustomException;
import com.shopping.mapper.ProductImageMapper;
import com.shopping.repository.ImageRepository;
import com.shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

  private final ImageRepository imageRepository;
  private final ProductRepository productRepository;

  public ProductImageResponseDTO uploadImage(Long productId, MultipartFile file) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new CustomException("Product not found"));
    String imageUrl = saveFile(file);
    ProductImage image = ProductImage.builder()
        .url(imageUrl)
        .product(product)
        .build();
    return ProductImageMapper.toResponseDTO(imageRepository.save(image));
  }

  public void deleteImage(Long imageId) {
    ProductImage image = imageRepository.findById(imageId)
        .orElseThrow(() -> new CustomException("Image not found"));
    image.delete();
    imageRepository.save(image);
  }

  public List<ProductImageResponseDTO> getImagesByProductId(Long productId) {
    List<ProductImage> images = imageRepository.findByProductId(productId);
    if (images.isEmpty()) {
      throw new CustomException("No images found for the given product ID");
    }
    return images.stream()
        .map(ProductImageMapper::toResponseDTO)
        .collect(Collectors.toList());
  }

  private String saveFile(MultipartFile file) {
    // 파일을 저장하고 URL을 반환하는 로직
    return "http://example.com/images/" + file.getOriginalFilename();
  }
}
