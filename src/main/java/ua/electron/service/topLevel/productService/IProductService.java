package ua.electron.service.topLevel.productService;



import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IProductService {

    List productList(HttpServletRequest request);

}
