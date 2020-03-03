package ua.electron.service.topLevel.productService.impl;

import ua.electron.dao.IProductDao;
import ua.electron.service.topLevel.productService.IProductService;
import ua.electron.service.lowerLevel.advancedSearch.AdvancedSearch;
import ua.electron.service.lowerLevel.searchForm.SearchForm;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ProductServiceImpl implements IProductService {

    private  static final Logger LOGGER = Logger.getLogger(ProductServiceImpl.class);

    private IProductDao productDao;
    private SearchForm searchForm  = new SearchForm();
    private AdvancedSearch advancedSearch = new AdvancedSearch();


    public ProductServiceImpl(IProductDao productDao) {
        this.productDao = productDao;
    }


    @Override
    public List productList(HttpServletRequest request) {
        if (request.getParameter("sortByName") != null){
            LOGGER.trace("sortByName");
            return productDao.sortByName();
        }

        if (request.getParameter("sortByPriceCheap") != null){
            LOGGER.trace("sortByPriceAsc");
            return productDao.sortByPriceAsc();
        }

        if (request.getParameter("sortByPriceExpensive") != null){
            LOGGER.trace("sortByPriceDesc");
            return productDao.sortByPriceDesc();
        }

        if (request.getParameter("sortingSmartphone") != null){
            LOGGER.trace("sortingSmartphone");
            return productDao.sortByCategory("Smartphone");
        }

        if (request.getParameter("sortingTablet") != null){
            LOGGER.trace("sortingTablet");
            return productDao.sortByCategory("Tablet");
        }

        if (request.getParameter("sortingLaptop") != null){
            LOGGER.trace("sortingLaptop");
            return productDao.sortByCategory("Laptop");
        }

        if (request.getParameter("sortingTV") != null){
            LOGGER.trace("sortingTV");
            return productDao.sortByCategory("TV");
        }

        if (request.getParameter("searchButton") != null){
            LOGGER.trace("searchFormMethod");
            if (request.getParameter("searchField") != null){
                LOGGER.trace("searchField is not empty, try to search");
                return searchForm.searchFormMethod(productDao, request.getParameter("searchField"));
            }
        }

        if (request.getParameter("searchButtonInAdvancedSearch") != null){
            LOGGER.trace("Advanced Search Form Method");
            if (request.getParameter("AllBrands") != null && request.getParameter("AllCategory") != null){
                return productDao.allProducts();
            }if (request.getParameter("brand") != null && request.getParameter("AllCategory") != null){
                return advancedSearch.advancedSearchByBrand(productDao, request);
            }if (request.getParameter("category") != null && request.getParameter("AllBrands") != null){
                return advancedSearch.advancedSearchByCategory(productDao, request);
            }if (request.getParameterValues("category") != null && request.getParameterValues("brand") != null){
                return advancedSearch.advancedSearch(productDao, request);
            }else{
                return productDao.allProducts();
            }
        }

        return productDao.allProducts();
    }
}
