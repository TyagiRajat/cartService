
package com.example.cartservice.model;

import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class Cart {
    private Long id;
    private Long userId;
    private Date date;
    private List<Product> products;

    }



