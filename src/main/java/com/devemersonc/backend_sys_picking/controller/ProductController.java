package com.devemersonc.backend_sys_picking.controller;

import com.devemersonc.backend_sys_picking.DTO.picking.PickDTO;
import com.devemersonc.backend_sys_picking.DTO.productDTO.CreateUpdateProductDTO;
import com.devemersonc.backend_sys_picking.DTO.productDTO.ProductResponseDTO;
import com.devemersonc.backend_sys_picking.service.product.ProductService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long product_id) {
        ProductResponseDTO productResponseDTO = productService.getProduct(product_id);
        return ResponseEntity.ok(productResponseDTO);
    }

    @PostMapping
    public ResponseEntity<String> saveProduct(@Valid @RequestBody CreateUpdateProductDTO createUpdateProductDTO) {
        productService.createProduct(createUpdateProductDTO);
        return ResponseEntity.ok("Producto creado con éxito.");
    }

    @PutMapping("/{product_id}")
    public ResponseEntity<String> updateProduct(@Valid @RequestBody CreateUpdateProductDTO createUpdateProductDTO, @PathVariable Long product_id) {
        productService.updateProduct(product_id, createUpdateProductDTO);
        return ResponseEntity.ok("Producto actualizado.");
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long product_id) {
        productService.deleteProduct(product_id);
        return ResponseEntity.ok("Producto eliminado.");
    }

    @GetMapping("/searchSku")
    public ResponseEntity<ProductResponseDTO> getProductBySku(@RequestParam String sku) {
        ProductResponseDTO productResponseDTO = productService.searchProductBySku(sku);
        return ResponseEntity.ok(productResponseDTO);
    }

    @GetMapping("/searchName")
    public ResponseEntity<ProductResponseDTO> getProductByName(@RequestParam String name) {
        ProductResponseDTO productResponseDTO = productService.searchProdcutByName(name);
        return ResponseEntity.ok(productResponseDTO);
    }

    @PostMapping("/pick")
    public ResponseEntity<String> pickProduct(@RequestBody PickDTO dto) {
        productService.discountStock(dto.getProductId(), dto.getQuantity());
        return ResponseEntity.ok("Cantidad de producto actualizada.");
    }
}