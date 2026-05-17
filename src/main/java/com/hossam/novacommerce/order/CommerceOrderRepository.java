package com.hossam.novacommerce.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommerceOrderRepository extends JpaRepository<CommerceOrder, Long> {
    List<CommerceOrder> findByTenantIdOrderByCreatedAtDesc(Long tenantId);
}
