package com.hossam.novacommerce.product;

import com.hossam.novacommerce.common.BusinessRuleException;
import com.hossam.novacommerce.common.NotFoundException;
import com.hossam.novacommerce.tenant.TenantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository products;
    private final TenantService tenantService;

    public ProductService(ProductRepository products, TenantService tenantService) {
        this.products = products;
        this.tenantService = tenantService;
    }

    @Transactional
    public Product create(ProductDto.CreateRequest request) {
        tenantService.get(request.tenantId());
        if (products.existsByTenantIdAndSku(request.tenantId(), request.sku())) {
            throw new BusinessRuleException("Product SKU already exists for this tenant");
        }
        return products.save(new Product(request.tenantId(), request.sku(), request.name(), request.price(), request.stockQuantity()));
    }

    @Transactional(readOnly = true)
    public Product get(Long id) {
        return products.findById(id).orElseThrow(() -> new NotFoundException("Product not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Product> listByTenant(Long tenantId) {
        tenantService.get(tenantId);
        return products.findByTenantId(tenantId);
    }
}
