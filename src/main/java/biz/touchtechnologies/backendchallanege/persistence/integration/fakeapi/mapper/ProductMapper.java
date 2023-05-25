package biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.mapper;

import biz.touchtechnologies.backendchallanege.application.Product;
import biz.touchtechnologies.backendchallanege.application.dto.SaveProduct;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.request.SaveProductApi;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.response.GetProduct;
import biz.touchtechnologies.backendchallanege.persistence.integration.fakeapi.util.Rating;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ProductMapper {

    public static Product toDomainDTO(GetProduct apiDTO){

        if(apiDTO == null) return null;

        Product product = new Product(
                apiDTO.getId(),
                apiDTO.getTitle(),
                apiDTO.getPrice(),
                apiDTO.getCategory(),
                apiDTO.getDescription(),
                apiDTO.getImage(),
                Optional.ofNullable(apiDTO.getRating()).map(Rating::getRate).orElse(0.0),
                Optional.ofNullable(apiDTO.getRating()).map(Rating::getCount).orElse(0)
        );

        return product;

    }

    public static List<Product> toDomainDTOList(List<GetProduct> apiDTOList){
        if(apiDTOList == null) return null;

        return apiDTOList.stream().map(ProductMapper::toDomainDTO)
                .collect(Collectors.toList());
    }

    public static SaveProductApi toSaveProductApiDTO(SaveProduct saveProduct){
        if(saveProduct == null) return null;

        SaveProductApi product = new SaveProductApi(
                saveProduct.getTitle(),
                saveProduct.getPrice(),
                saveProduct.getCategory(),
                saveProduct.getDescription(),
                saveProduct.getImage(),
                saveProduct.getRate(),
                saveProduct.getCount()
        );

        return product;
    }

}
