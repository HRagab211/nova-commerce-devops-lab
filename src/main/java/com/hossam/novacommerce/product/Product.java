package com.hossam.novacommerce.product;

import com.hossam.novacommerce.common.BusinessRuleException;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "products", uniqueConstraints = {
        @UniqueConstraint(name = "uk_products_tenant_sku", columnNames = {"tenant_id", "sku"})
}, indexes = {
        @Index(name = "idx_products_tenant", columnList = "tenant_id")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;

    @Column(nullable = false, length = 80)
    private String sku;

    @Column(nullable = false, length = 180)
    private String name;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private int stockQuantity;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    protected Product() {
    }

    public Product(Long tenantId, String sku, String name, BigDecimal price, int stockQuantity) {
        this.tenantId = tenantId;
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Long getId() { return id; }
    public Long getTenantId() { return tenantId; }
    public String getSku() { return sku; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public boolean isActive() { return active; }
    public Instant getCreatedAt() { return createdAt; }

    public void reserve(int quantity) {
        if (quantity <= 0) {
            throw new BusinessRuleException("Quantity must be greater than zero");
        }
        if (stockQuantity < quantity) {
            throw new BusinessRuleException("Insufficient stock for product " + sku);
        }
        stockQuantity -= quantity;
    }
}
