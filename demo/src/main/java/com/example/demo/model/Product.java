package com.example.demo.model;

import com.example.demo.enums.ProductType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品クラス
 *
 * @author wataru okamura
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    // 商品種別
    private ProductType productType;

    // 個数
    private Integer num;

    // 最小個数
    private Integer minNum;

    // 最大個数
    private Integer maxNum;

    // 限界個数
    private int limitNum;

    // コンストラクタ
    public Product(ProductType productType) {
        this.productType = productType;
    }

    // コンストラクタ
    public Product(ProductType productType, int totalPrice, Integer minNum, Integer maxNum) {
        this.productType = productType;
        this.minNum = minNum;
        this.maxNum = maxNum;
        this.limitNum = totalPrice / productType.getPrice();
    }

}
