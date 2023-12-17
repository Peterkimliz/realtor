package com.example.digirealtor.Services;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.digirealtor.Dtos.PropertyDto;
import com.example.digirealtor.Dtos.PropertyRequestDto;
import com.example.digirealtor.Exceptions.NotFoundException;
import com.example.digirealtor.Models.Category;
import com.example.digirealtor.Models.Property;
import com.example.digirealtor.Models.UserModel;
import com.example.digirealtor.Repositories.CategoryRepository;
import com.example.digirealtor.Repositories.PropertyRepository;
import com.example.digirealtor.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository productRepository;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;
    private final MongoTemplate mongoTemplate;

    public PropertyDto createProperty(PropertyRequestDto productDto, String ownerId) {

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

        return mapProductToDto(product);
    }

    public PropertyDto getProductById(String id) {
        Optional<Property> foundProduct = productRepository.findById(id);
        if (!foundProduct.isPresent()) {
            throw new NotFoundException("product with the id not found");
        }
        return mapProductToDto(foundProduct.get());
    }

    public PropertyDto updateProductById(PropertyRequestDto productDto, String id) {
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
        if (!foundProduct.isPresent()) {
            throw new NotFoundException("product with the id not found");
        }
        productRepository.deleteById(id);
        // Property product = foundProduct.get();
        // product.setDeleted(true);
        // productRepository.save(product);
    }

    private PropertyDto mapProductToDto(Property product) {
        return PropertyDto.builder()
                .utilities(product.getUtilities())
                .floorCovering(product.getFloorCovering())
                .other(product.getOthers())
                .appliances(product.getAppliances())
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
                .rent(product.getRent())
                .security(product.getSecurity())
                .roomSize(product.getRoomSize())
                .type(product.getType())
                .owner(product.getOwner())
                .build();
    }

    private Property mapToProduct(PropertyRequestDto productDto, UserModel owner) {
        return Property.builder()
                .createdAt(new Date(System.currentTimeMillis()))
                .utilities(getCategoryById(productDto.getUtilitiesList()))
                .appliances(getCategoryById(productDto.getAppliancesList()))
                .floorCovering(getCategoryById(productDto.getFloorCoveringList()))
                .others(getCategoryById(productDto.getOtherList()))
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
                .rent(productDto.getRent())
                .security(productDto.getSecurity())
                .type(productDto.getType())
                .roomSize(productDto.getRoomSize())
                .owner(owner)
                .deleted(false)
                .build();
    }

    private List<Category> getCategoryById(List<String> utilities) {
        System.out.println("mmmmmh" + utilities);
        List<Category> cats = categoryRepository.findAllById(utilities);
        System.out.println(cats);

        return cats;
    }

    public List<PropertyDto> getProductByFilters(String landlord) {
        List<Property> products = new ArrayList<Property>();
        Query query = new Query();
        if (landlord != null) {
            query.addCriteria(Criteria.where("owner.id").is(landlord)).with(PageRequest.of(0, 1));
            products = mongoTemplate.find(query, Property.class);
        } else {
            products = productRepository.findAll();
        }

        return products.stream().map(e -> mapProductToDto(e)).toList();

    }
    public Map<String,List<Property>> getProductByMonths(String landlord) {
        List<Property> products = new ArrayList<Property>();
  
        Query query = new Query();
        if (landlord != null) {
            query.addCriteria(Criteria.where("owner.id").is(landlord)).with(PageRequest.of(0, 1));
            products = mongoTemplate.find(query, Property.class);
        } else {
            products = productRepository.findAll();
        }
         Map<String, List<Property>> data= products.stream().collect(Collectors.groupingBy(product -> Month.of((product.getCreatedAt().getMonth()+1)).name()));
         
         return data;

    }

}
/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findProductsNearLocation(Point point, double maxDistanceInKilometers) {
        Distance maxDistance = new Distance(maxDistanceInKilometers, Metrics.KILOMETERS);
        return productRepository.findByLocationNear(point, maxDistance);
    }

    public List<Product> findProductsWithinArea(double centerX, double centerY, double radiusInKilometers) {
        Point center = new Point(centerX, centerY);
        Circle circle = new Circle(center, new Distance(radiusInKilometers, Metrics.KILOMETERS));
        return productRepository.findByLocationWithin(circle);
    }

    // Add more geospatial query methods as needed
}
*/

/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/near")
    public List<Product> getProductsNearLocation(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam double maxDistanceInKilometers) {
        Point location = new Point(longitude, latitude);
        return productService.findProductsNearLocation(location, maxDistanceInKilometers);
    }

    @GetMapping("/within")
    public List<Product> getProductsWithinArea(
            @RequestParam double centerX,
            @RequestParam double centerY,
            @RequestParam double radiusInKilometers) {
        return productService.findProductsWithinArea(centerX, centerY, radiusInKilometers);
    }

    // Add more geospatial API endpoints as needed
}
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByLocationNear(Point point, Distance distance);
    List<Product> findByLocationWithin(Circle circle);
    // Add more geospatial queries as needed
}
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private GeoJsonPoint location;

    // Getters and setters
}
db.products.createIndex({ location: "2dsphere" })
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByLocationNear(Point point, Distance distance);
    List<Product> findByLocationWithin(Circle circle);
    // Add more geospatial queries as needed
}

 */