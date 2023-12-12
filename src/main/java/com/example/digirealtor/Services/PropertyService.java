package com.example.digirealtor.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.digirealtor.Controllers.CategoryController;
import com.example.digirealtor.Dtos.ProductDto;
import com.example.digirealtor.Exceptions.NotFoundException;
import com.example.digirealtor.Models.Category;
import com.example.digirealtor.Models.Property;
import com.example.digirealtor.Models.UserModel;
import com.example.digirealtor.Repositories.CategoryRepository;
import com.example.digirealtor.Repositories.ProductRepository;
import com.example.digirealtor.Repositories.UserRepository;

@Service
public class PropertyService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDto createProduct(ProductDto productDto, String ownerId) {
        Optional<UserModel> user = userRepository.findById(ownerId);
        Optional<Category> category = categoryRepository.findById(productDto.getCategory());

        if (!user.isPresent()) {
            throw new NotFoundException("user with the id not found");
        }
        if (!category.isPresent()) {
            throw new NotFoundException("category with the id not found");
        }

        Property product = mapToProduct(productDto, user.get());
        productRepository.save(product);
        productDto.setId(product.getId());
        return productDto;
    }

    public ProductDto getProductById(String id) {
        Optional<Property> foundProduct = productRepository.findById(id);
        if (!foundProduct.isPresent()) {
            throw new NotFoundException("product with the id not found");
        }
        return mapProductToDto(foundProduct.get());
    }

    public ProductDto updateProductById(ProductDto productDto, String id) {
        Optional<Property> foundProduct = productRepository.findById(id);
        if (!foundProduct.isPresent()) {
            throw new NotFoundException("product with the id not found");
        }
        Property product = mapToProduct(productDto, foundProduct.get().getOwner());
        productRepository.save(product);
        return mapProductToDto(product);
    }

    public void deleteProduct(String id) {
        Optional<Property> foundProduct = productRepository.findById(id);
        if (!foundProduct.isPresent()||!foundProduct.get().getDeleted()==true) {
            throw new NotFoundException("product with the id not found");
        }
        Property product = foundProduct.get();
        product.setDeleted(true);
        productRepository.save(product);
    }

    private ProductDto mapProductToDto(Property product) {
        return ProductDto.builder()
                .amenities(product.getAmenities())
                .balconies(product.getBalconies())
                .bathRooms(product.getBathRooms())
                .bedRooms(product.getBedRooms())
                .category(product.getCategory())
                .description(product.getDescription())
                .id(product.getId())
                .images(product.getImages())
                .kitchens(product.getKitchens())
                .location(product.getLocation())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .roomSize(product.getRoomSize())
                .type(product.getType())

                .build();
    }

    private Property mapToProduct(ProductDto productDto, UserModel owner) {
        return Property.builder()
                .createdAt(new Date(System.currentTimeMillis()))
                .amenities(productDto.getAmenities())
                .balconies(productDto.getBalconies())
                .bathRooms(productDto.getBathRooms())
                .bedRooms(productDto.getBedRooms())
                .category(productDto.getCategory())
                .description(productDto.getDescription())
                .featured(false)
                .images(productDto.getImages())
                .kitchens(productDto.getKitchens())
                .location(productDto.getLocation())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .type(productDto.getType())
                .roomSize(productDto.getRoomSize())
                .owner(owner)
                .deleted(false)
                .build();
    }

    public List<ProductDto> getProductByFilters(String type, String category, int bedrooms, int bathrooms, int kitchens,
            int startPrice, int endPrice) {

        List<Property> products= productRepository.findAll();

        return products.stream().map(e->mapProductToDto(e)).toList();
    }



}
