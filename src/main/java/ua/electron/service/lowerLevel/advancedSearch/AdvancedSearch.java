package ua.electron.service.lowerLevel.advancedSearch;

import ua.electron.dao.IProductDao;
import ua.electron.entity.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AdvancedSearch {

    private Set<String> categories;
    private Set<String> brands;
    private List<Product> gotProduct = new ArrayList<>();
    private List<Product> getProduct = null;

    public List<Product> advancedSearch(IProductDao productDao, HttpServletRequest request) {
        categories = productDao.allCategory();
        brands = productDao.allBrand();
        gotProduct.clear();

        for (String categoryValue : request.getParameterValues("category")) {
            for (String brandValue : request.getParameterValues("brand")) {
                for (String categoryName : categories) {
                    for (String brandName : brands) {
                        if (categoryValue.equals(categoryName) && brandValue.equals(brandName)) {
                            getProduct = productDao.advancedSearch(categoryName, brandName);
                            gotProduct.addAll(getProduct);
                        }
                    }
                }
            }
        }
        return gotProduct;
    }

    public List<Product> advancedSearchByBrand(IProductDao productDao, HttpServletRequest request) {
        brands = productDao.allBrand();
        gotProduct.clear();

        for (String brandValue : request.getParameterValues("brand")) {
            for (String brand : brands) {
                if (brandValue.equals(brand)) {
                    getProduct = productDao.sortByBrand(brand);
                    gotProduct.addAll(getProduct);
                }
            }
        }
        return gotProduct;
    }

    public List<Product> advancedSearchByCategory(IProductDao productDao, HttpServletRequest request) {
        categories = productDao.allCategory();
        gotProduct.clear();

        for (String categoryValue : request.getParameterValues("category")) {
            for (String category : categories) {
                if (categoryValue.equals(category)) {
                    getProduct = productDao.sortByCategory(category);
                    gotProduct.addAll(getProduct);
                }
            }
        }
        return gotProduct;
    }
}
