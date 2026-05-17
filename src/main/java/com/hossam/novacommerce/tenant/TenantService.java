package com.hossam.novacommerce.tenant;

import com.hossam.novacommerce.common.BusinessRuleException;
import com.hossam.novacommerce.common.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TenantService {
    private final TenantRepository tenants;

    public TenantService(TenantRepository tenants) {
        this.tenants = tenants;
    }

    @Transactional
    public Tenant create(TenantDto.CreateRequest request) {
        if (tenants.existsBySlug(request.slug())) {
            throw new BusinessRuleException("Tenant slug already exists");
        }
        if (tenants.existsByDomain(request.domain())) {
            throw new BusinessRuleException("Tenant domain already exists");
        }
        return tenants.save(new Tenant(request.name(), request.slug(), request.domain()));
    }

    @Transactional(readOnly = true)
    public Tenant get(Long id) {
        return tenants.findById(id).orElseThrow(() -> new NotFoundException("Tenant not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Tenant> list() {
        return tenants.findAll();
    }
}
