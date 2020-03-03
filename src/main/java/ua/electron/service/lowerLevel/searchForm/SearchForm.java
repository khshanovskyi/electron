package ua.electron.service.lowerLevel.searchForm;

import ua.electron.dao.IProductDao;
import ua.electron.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class SearchForm {

    public List searchFormMethod(IProductDao productDao, String enteredDataForSearch) {
        List<Product> searchProduct = productDao.allProducts();
        List<Product> theSameProduct = new ArrayList<>();
        for (Product product: searchProduct
             ) {
            if (product.getProductName().contains(enteredDataForSearch) ||
                    product.getBrand().contains(enteredDataForSearch)||
                    product.getCategory().contains(enteredDataForSearch)){
                theSameProduct.add(product);
            }
        }
        return theSameProduct;
    }
}
