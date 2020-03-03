package ua.electron.dao.impl;

import ua.electron.dao.ConnectionManager;
import ua.electron.dao.IProductDao;
import ua.electron.entity.Product;
import ua.electron.entity.builder.ProductBuilder;
import ua.electron.exception.DaoException;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProductDaoMySql extends ConnectionManager implements IProductDao {

    private static final Logger LOGGER = Logger.getLogger(ProductDaoMySql.class);

    private static final String SELECT_ALL_PRODUCT = "SELECT * FROM `electron.ua`.electronics ORDER BY id_of_product DESC";
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM electronics WHERE id_of_product = ?;";
    private static final String SELECT_ALL_CATEGORY= "SELECT DISTINCT category FROM `electron.ua`.electronics;";
    private static final String SELECT_ALL_BRAND= "SELECT DISTINCT brand FROM `electron.ua`.electronics;";
    private static final String SORT_BY_NAME = "SELECT * FROM `electron.ua`.electronics ORDER BY product_name;";
    private static final String SORT_BY_PRICE_ASC = "SELECT * FROM `electron.ua`.electronics ORDER BY price;";
    private static final String SORT_BY_PRICE_DESC = "SELECT * FROM `electron.ua`.electronics ORDER BY price DESC;";
    private static final String SORT_BY_CATEGORY = "SELECT * FROM `electron.ua`.electronics WHERE category = ? ORDER BY id_of_product DESC;";
    private static final String SORT_BY_BRAND = "SELECT * FROM `electron.ua`.electronics WHERE brand = ? ORDER BY id_of_product DESC;";
    private static final String ADVANCED_SEARCH = "SELECT * FROM `electron.ua`.electronics WHERE category = ? and brand = ? ORDER BY id_of_product DESC;";

    public ProductDaoMySql(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Product> allProducts() {
        List<Product> products = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
             connection = getConnection();
            statement = connection.prepareStatement(SELECT_ALL_PRODUCT);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                products.add(extractProduct(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection);
            close(statement);
            close(resultSet);
        }

        return products;
    }

    @Override
    public Optional<Product> getProductById(int idOfProduct) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(GET_PRODUCT_BY_ID);
            statement.setInt(1, idOfProduct);
            resultSet = statement.executeQuery();

            if (resultSet.next()){
                return Optional.of(extractProduct(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot get a product from DB " + e);
            throw new DaoException("Cannot get a product from DB ", e);
        }finally {
            close(connection);
            close(statement);
            close(resultSet);
        }
        return Optional.empty();
    }

    @Override
    public Set<String> allCategory() {
        Set<String> categories = new HashSet<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(SELECT_ALL_CATEGORY);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                categories.add(resultSet.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection);
            close(statement);
            close(resultSet);
        }
        return categories;
    }

    @Override
    public Set<String> allBrand() {
        Set<String> brands = new HashSet<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(SELECT_ALL_BRAND);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                brands.add(resultSet.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection);
            close(statement);
            close(resultSet);
        }
        return brands;
    }

    @Override
    public List<Product> sortByName() {
        List<Product> productsSortedByName = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(SORT_BY_NAME);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                productsSortedByName.add(extractProduct(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection);
            close(statement);
            close(resultSet);
        }
        return productsSortedByName;
    }

    @Override
    public List<Product> sortByPriceAsc() {
        List<Product> productsSortByPriceAsc = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(SORT_BY_PRICE_ASC);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                productsSortByPriceAsc.add(extractProduct(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection);
            close(statement);
            close(resultSet);
        }
        return productsSortByPriceAsc;
    }

    @Override
    public List<Product> sortByPriceDesc() {
        List<Product> productsSortByPriceDesc = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(SORT_BY_PRICE_DESC);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                productsSortByPriceDesc.add(extractProduct(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection);
            close(statement);
            close(resultSet);
        }
        return productsSortByPriceDesc;
    }

    @Override
    public List<Product> sortByCategory(String categoryName) {
        List<Product> productsSortedByCategory = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(SORT_BY_CATEGORY);
            statement.setString(1, categoryName);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                productsSortedByCategory.add(extractProduct(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection);
            close(statement);
            close(resultSet);
        }
        return productsSortedByCategory;
    }


    @Override
    public List<Product> sortByBrand(String brandName) {
        List<Product> productsSortedByBrand = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(SORT_BY_BRAND);
            statement.setString(1, brandName);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                productsSortedByBrand.add(extractProduct(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection);
            close(statement);
            close(resultSet);
        }
        return productsSortedByBrand;
    }

    @Override
    public List<Product> advancedSearch(String categoryName, String brandName) {
        List<Product> advancedSearchProduct = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement(ADVANCED_SEARCH);
            statement.setString(1, categoryName);
            statement.setString(2, brandName);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                advancedSearchProduct.add(extractProduct(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection);
            close(statement);
            close(resultSet);
        }
        return advancedSearchProduct;
    }

    private Product extractProduct(ResultSet resultSet) throws SQLException {
        ProductBuilder productBuilder = new ProductBuilder();

        productBuilder.buildIdOfProduct(resultSet.getInt("id_of_product"));
        productBuilder.buildProductName(resultSet.getString("product_name"));
        productBuilder.buildCategory(resultSet.getString("category"));
        productBuilder.buildBrand(resultSet.getString("brand"));
        productBuilder.buildPrice(resultSet.getInt("price"));
        productBuilder.buildPictureURL1(resultSet.getString("picture_URL1"));
        productBuilder.buildPictureURL2(resultSet.getString("picture_URL2"));
        productBuilder.buildPictureURL3(resultSet.getString("picture_URL3"));
        productBuilder.buildPictureURL4(resultSet.getString("picture_URL4"));
        productBuilder.buildPictureURL5(resultSet.getString("picture_URL5"));
        productBuilder.buildCharacteristic(resultSet.getString("characteristic"));
        productBuilder.buildDescription(resultSet.getString("description"));

        return productBuilder.buildProduct();
    }
}
