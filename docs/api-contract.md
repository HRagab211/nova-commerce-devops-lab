# API Contract

All protected endpoints require:

```text
X-Lab-Key: devops-lab-key
```

## Health

```http
GET /actuator/health
```

## Lab check

```http
GET /api/devops/lab-check
```

## Tenants

```http
POST /api/tenants
GET /api/tenants
```

## Customers

```http
POST /api/customers
GET /api/customers?tenantId=1
```

## Products

```http
POST /api/products
GET /api/products?tenantId=1
```

## Orders

```http
POST /api/orders
GET /api/orders?tenantId=1
GET /api/orders/{id}
POST /api/orders/{id}/pay
```

## Shipments

```http
POST /api/shipments
POST /api/shipments/{id}/dispatch
POST /api/shipments/{id}/deliver
```
