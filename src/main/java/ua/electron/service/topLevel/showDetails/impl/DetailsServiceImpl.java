package ua.electron.service.topLevel.showDetails.impl;

import ua.electron.dao.IProductDao;
import ua.electron.entity.Product;
import ua.electron.service.topLevel.showDetails.IDetailsService;

import java.util.Optional;

public class DetailsServiceImpl implements IDetailsService {

    private IProductDao productDao;

    public DetailsServiceImpl(IProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product getDetails(int productIdFromUI) {
        Optional<Product> productDetails = productDao.getProductById(productIdFromUI);
        return productDetails.get();
    }
}
