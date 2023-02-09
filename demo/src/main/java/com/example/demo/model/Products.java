package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.demo.enums.ProductType;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 商品リストを保持するクラス
 *
 * @author wataru okamura
 */
@Data
@AllArgsConstructor
public class Products implements Serializable {

    // 合計金額
    Integer totalPrice;

    // 商品
    private List<Product> list;

    // コンストラクタ・初期値の設定
    public Products() {
        // 商品情報の設定
        List<Product> productList = new ArrayList<>();
        Arrays.stream(ProductType.values()).forEach(productType -> {
            productList.add(new Product(productType));
        });

        // 初期値の設定
        // - 合計金額の初期設定
        this.totalPrice = 139000;
        // - 通常販売のない商品の最大個数設定
        productList.get(10).setMaxNum(0);
        productList.get(11).setMaxNum(0);
        productList.get(12).setMaxNum(0);
        productList.get(13).setMaxNum(0);
        productList.get(14).setMaxNum(0);
        setList(productList);
    }

    public Products clone() {
        Products cloneProducts = new Products();
        cloneProducts.setTotalPrice(getTotalPrice());
        for (int i = 0; i < getList().size(); i++) {
            Product product = getList().get(i);
            Product cloneProduct = cloneProducts.getList().get(i);

            cloneProduct.setProductType(product.getProductType());
            cloneProduct.setNum(product.getNum());
            cloneProduct.setMinNum(product.getMinNum());
            cloneProduct.setMaxNum(product.getMaxNum());
            cloneProduct.setLimitNum(product.getLimitNum());
        }

        return cloneProducts;
    }

}
