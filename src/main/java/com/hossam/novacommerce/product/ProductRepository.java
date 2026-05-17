package com.hossam.novacommerce.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTenantId(Long tenantId);
    boolean existsByTenantIdAndSku(Long tenantId, String sku);
}
