package ua.electron.dao;

import ua.electron.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IProductDao {

    List<Product> allProducts();

    Optional<Product> getProductById (int idOfProduct);

    Set<String> allCategory();

    Set<String> allBrand();

    List<Product> sortByName();

    List<Product> sortByPriceAsc();

    List<Product> sortByPriceDesc();

    List<Product> sortByCategory(String categoryName);

    List<Product> sortByBrand(String brandName);

    List<Product> advancedSearch(String categoryName, String brandName);

}
