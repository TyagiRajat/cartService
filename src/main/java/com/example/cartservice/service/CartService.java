
package com.example.cartservice.service;

import com.example.cartservice.model.Cart;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private static final String URL = "https://fakestoreapi.com/carts";

    private final RestTemplate restTemplate;

    public CartService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Cart> getAllCarts() {
        Cart[] carts = restTemplate.getForObject(URL, Cart[].class);
        assert carts != null;
        return Arrays.asList(carts);
    }

    public Cart getCartById(Long id) {
        return restTemplate.getForObject(URL + "/" + id, Cart.class);
    }

    public List<Cart> getCartsInDateRange(Date startDate, Date endDate) {
        List<Cart> allCarts = getAllCarts();
        return allCarts.stream()
                .filter(cart -> cart.getDate().after(startDate) && cart.getDate().before(endDate))
                .collect(Collectors.toList());
    }

    public List<Cart> getUserCarts(Long userId) {
        List<Cart> allCarts = getAllCarts();
        return allCarts.stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public Cart addCart(Cart cart) {
        return restTemplate.postForObject(URL, cart, Cart.class);
    }

    public Cart updateCart(Long id, Cart cart) {
        restTemplate.put(URL + "/" + id, cart);
        return cart;
    }

    public void deleteCart(Long id) {
        restTemplate.delete(URL + "/" + id);
    }
}
