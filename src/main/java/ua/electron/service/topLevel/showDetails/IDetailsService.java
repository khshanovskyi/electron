package ua.electron.service.topLevel.showDetails;

import ua.electron.entity.Product;

public interface IDetailsService {

    Product getDetails(int productIdFromUI);
}
