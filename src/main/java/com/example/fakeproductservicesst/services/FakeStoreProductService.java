package com.example.fakeproductservicesst.services;
import com.example.fakeproductservicesst.Exceptions.CategoryNotFound;
import com.example.fakeproductservicesst.Exceptions.ProductNotFound;
import com.example.fakeproductservicesst.dtos.FakeStoreProductDto;
import com.example.fakeproductservicesst.models.Category;
import com.example.fakeproductservicesst.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate = new RestTemplate();
    public List<Product> getAllProducts(){
        FakeStoreProductDto[]  listOfProducts = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        if(listOfProducts == null || listOfProducts.length == 0){
            throw new RuntimeException("No products found");
        }
        ArrayList<Product> productList = new ArrayList<>();
        for(int i = 0; i<(Objects.requireNonNull(listOfProducts)).length; i++){
            Product newProduct = new Product();
            FakeStoreProductDto temp = listOfProducts[i];
            newProduct.setTitle(temp.getTitle());
            newProduct.setId(temp.getId());
            newProduct.setDescription(temp.getDescription());
            newProduct.setCategory(new Category());
            newProduct.getCategory().setName(temp.getCategory());
            newProduct.setImageUrl(temp.getImage());
            productList.add(newProduct);
        }
        return productList;
    }
    public List<Product> getProductsInCategory(String category){
        FakeStoreProductDto[]  listOfProducts = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/"+ category,
                FakeStoreProductDto[].class
        );
        if(listOfProducts==null || listOfProducts.length==0){
            throw new CategoryNotFound("No products found for this category", category);
        }
        ArrayList<Product> productList = new ArrayList<>();
        for(int i = 0; i<(Objects.requireNonNull(listOfProducts)).length; i++){
            Product newProduct = new Product();
            FakeStoreProductDto temp = listOfProducts[i];
            newProduct.setTitle(temp.getTitle());
            newProduct.setId(temp.getId());
            newProduct.setDescription(temp.getDescription());
            newProduct.setCategory(new Category());
            newProduct.getCategory().setName(temp.getCategory());
            newProduct.setImageUrl(temp.getImage());
            productList.add(newProduct);
        }
        return productList;
    }
    public Product getSingleProduct(Long id){
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );
        Product product = new Product();
        if(fakeStoreProductDto != null) {
            product.setTitle(fakeStoreProductDto.getTitle());
            product.setId(fakeStoreProductDto.getId());
            product.setDescription(fakeStoreProductDto.getDescription());
            product.setCategory(new Category());
            product.getCategory().setName(fakeStoreProductDto.getCategory());
            product.setImageUrl(fakeStoreProductDto.getImage());
            return product;
        }
        throw new ProductNotFound("Product not found", id);
    }
    public Product createProduct(Product product){

        FakeStoreProductDto temp = new FakeStoreProductDto();
        temp.setTitle(product.getTitle());
        temp.setDescription(product.getDescription());
        temp.setId(product.getId());
        temp.setImage(product.getImageUrl());
        temp.setCategory(product.getCategory().getName());
        temp.setPrice(product.getPrice());

        FakeStoreProductDto fakeStoreProductDto = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                temp,
                FakeStoreProductDto.class
        );
        return product;
    }
    public void updateProduct(Product product, Long id){
        FakeStoreProductDto temp = new FakeStoreProductDto();
        temp.setTitle(product.getTitle());
        temp.setDescription(product.getDescription());
        temp.setId(product.getId());
        temp.setImage(product.getImageUrl());
        temp.setCategory(product.getCategory().getName());
        temp.setPrice(product.getPrice());

        restTemplate.put(
                "https://fakestoreapi.com/products/" + id,
                temp,
                FakeStoreProductDto.class
        );
    }
    public void deleteProduct(Long id){
        restTemplate.delete(
                "https://fakestoreapi.com/products/" + id
        );
    }
}
