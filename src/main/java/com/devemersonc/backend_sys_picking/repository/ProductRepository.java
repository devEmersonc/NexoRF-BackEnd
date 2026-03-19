package com.devemersonc.backend_sys_picking.repository;

import com.devemersonc.backend_sys_picking.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findBySkuIgnoreCase(String sku);
    Product findByNameIgnoreCase(String name);
    Product findByLocationIgnoreCase(String location);
}
