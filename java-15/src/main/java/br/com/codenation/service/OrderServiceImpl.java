package br.com.codenation.service;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private static final double DISCOUNT = 0.2;
    private final ProductRepository productRepository = new ProductRepositoryImpl();

    /**
     * Calculate the sum of all OrderItems
     */
    @Override
    public Double calculateOrderValue(List<OrderItem> items) {
        return items.stream().mapToDouble((orderItem) -> {
            final Optional<Product> product = productRepository.findById(orderItem.getProductId());
            Double value = product.get().getValue();
            if (product.get().getIsSale()) {
                value -= value * DISCOUNT;
            }
            return orderItem.getQuantity() * value;
        }).sum();
    }

    /**
     * Map from idProduct List to Product Set
     */
    @Override
    public Set<Product> findProductsById(List<Long> ids) {
        return ids.stream()
                .map(id -> productRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    /**
     * Calculate the sum of all Orders(List<OrderIten>)
     */
    @Override
    public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
        return orders.stream().mapToDouble(this::calculateOrderValue).sum();
    }

    /**
     * Group products using isSale attribute as the map key
     */
    @Override
    public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
        return findProductsById(productIds).stream().collect(Collectors.groupingBy(Product::getIsSale));
    }

}