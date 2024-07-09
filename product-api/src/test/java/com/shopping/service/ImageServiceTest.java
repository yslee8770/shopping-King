package com.shopping.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.shopping.dto.ProductImageResponseDTO;
import com.shopping.entity.Product;
import com.shopping.entity.ProductImage;
import com.shopping.exception.CustomException;
import com.shopping.repository.ImageRepository;
import com.shopping.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

public class ImageServiceTest {

  @Mock
  private ImageRepository imageRepository;

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private ImageService imageService;

  public ImageServiceTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void uploadImage_success() {
    Product product = Product.builder().id(1L).name("Laptop").build();
    when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

    MultipartFile file = mock(MultipartFile.class);
    ProductImage image = ProductImage.builder().id(1L).url("http://example.com/image.jpg").product(product).build();
    when(imageRepository.save(any(ProductImage.class))).thenReturn(image);

    ProductImageResponseDTO imageDTO = imageService.uploadImage(1L, file);

    verify(productRepository, times(1)).findById(anyLong());
    verify(imageRepository, times(1)).save(any(ProductImage.class));
  }

  @Test
  public void uploadImage_productNotFound() {
    when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

    MultipartFile file = mock(MultipartFile.class);
    assertThrows(CustomException.class, () -> {
      imageService.uploadImage(1L, file);
    });

    verify(productRepository, times(1)).findById(anyLong());
  }

  @Test
  public void deleteImage_success() {
    ProductImage image = ProductImage.builder().id(1L).url("http://example.com/image.jpg").build();
    when(imageRepository.findById(anyLong())).thenReturn(Optional.of(image));

    imageService.deleteImage(1L);

    verify(imageRepository, times(1)).findById(anyLong());
    verify(imageRepository, times(1)).save(any(ProductImage.class));
  }

  @Test
  public void deleteImage_notFound() {
    when(imageRepository.findById(anyLong())).thenReturn(Optional.empty());

    assertThrows(CustomException.class, () -> {
      imageService.deleteImage(1L);
    });

    verify(imageRepository, times(1)).findById(anyLong());
  }

  @Test
  public void getImagesByProductId_success() {
    ProductImage image1 = ProductImage.builder().id(1L).url("http://example.com/image1.jpg").build();
    ProductImage image2 = ProductImage.builder().id(2L).url("http://example.com/image2.jpg").build();
    when(imageRepository.findByProductId(anyLong())).thenReturn(List.of(image1, image2));

    imageService.getImagesByProductId(1L);

    verify(imageRepository, times(1)).findByProductId(anyLong());
  }

  @Test
  public void getImagesByProductId_notFound() {
    when(imageRepository.findByProductId(anyLong())).thenReturn(List.of());

    assertThrows(CustomException.class, () -> {
      imageService.getImagesByProductId(1L);
    });

    verify(imageRepository, times(1)).findByProductId(anyLong());
  }
}
