package com.devemersonc.backend_sys_picking.service.product;

import com.devemersonc.backend_sys_picking.DTO.productDTO.CreateUpdateProductDTO;
import com.devemersonc.backend_sys_picking.DTO.productDTO.ProductResponseDTO;
import com.devemersonc.backend_sys_picking.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProduct(Long product_id);
    void createProduct(CreateUpdateProductDTO createProductDTO);
    void updateProduct(Long product_id, CreateUpdateProductDTO createProductDTO);
    void deleteProduct(Long product_id);
    Product findProductById(Long product_id);
    ProductResponseDTO searchProductBySku(String sku);
    ProductResponseDTO searchProdcutByName(String name);
    void discountStock(Long productId, int quantity);
}
