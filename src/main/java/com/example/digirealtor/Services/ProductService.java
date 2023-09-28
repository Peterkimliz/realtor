package com.example.digirealtor.Services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.digirealtor.Dtos.ProductDto;
import com.example.digirealtor.Exceptions.NotFoundException;
import com.example.digirealtor.Models.Product;
import com.example.digirealtor.Models.UserModel;
import com.example.digirealtor.Repositories.ProductRepository;
import com.example.digirealtor.Repositories.UserRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public ProductDto createProduct(ProductDto productDto,String ownerId) {
       Optional<UserModel> user =userRepository.findById(ownerId);

       if(!user.isPresent()){
        throw new NotFoundException("user with the id not found");
       }
       Product product = Product.builder()
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
                .owner(user.get())
                .build();

        productRepository.save(product);
        productDto.setId(product.getId());
        return productDto;
    }

}
