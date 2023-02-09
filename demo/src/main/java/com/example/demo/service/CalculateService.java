package com.example.demo.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.example.demo.model.Product;
import com.example.demo.model.Products;

import org.springframework.stereotype.Service;

/**
 * 計算サービス
 *
 * @author wataru okamura
 */
@Service
public class CalculateService {

    static int price = 0;

    public void calculate(Products products) {

        int totalPrice = products.getTotalPrice();
        List<Product> productList = products.getList();

        // 個数をセットする関数式
        Consumer<Product> numSetter = (product) -> {
            product.setLimitNum(totalPrice / product.getProductType().getPrice());
            if (product.getMinNum() == null) {
                product.setMinNum(0);
            }
            if (product.getMaxNum() == null) {
                product.setMaxNum(product.getLimitNum());
            }
        };

        // 個数セットの実行
        productList.forEach(product -> numSetter.accept(product));

        try {
            // 出力ファイルの作成
            FileWriter fileWriter = new FileWriter("result.csv", false);

            // PrintWriterクラスのオブジェクトを生成
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter));

            // 個数を計算する関数式
            BiConsumer<Product, Runnable> calculator = (product, runner) -> {

                for (int i = product.getMinNum(); i <= product.getMaxNum(); i++) {
                    product.setNum(i);
                    price = price + product.getProductType().getPrice() * product.getNum();

                    // 金額が上限を超えたら計算結果を一つ戻して処理を抜ける
                    if (price > totalPrice) {
                        price = price - product.getProductType().getPrice() * product.getNum();
                        break;
                    }

                    // ネストした次商品群の計算を実行
                    runner.run();

                    price = price - product.getProductType().getPrice() * product.getNum();
                }
            };

            // TOTAL_PRICEと一致した計算結果を出力する関数式
            Runnable resultPrinter = () -> {
                if (price == totalPrice) {
                    productList.forEach(p -> printWriter.print(p.getNum() + ","));
                    printWriter.println();
                }
            };

            // 各商品群の処理を設定
            Runnable runner15 = () -> {
                calculator.accept(productList.get(14), resultPrinter);
            };
            Runnable runner14 = () -> {
                calculator.accept(productList.get(13), runner15);
            };
            Runnable runner13 = () -> {
                calculator.accept(productList.get(12), runner14);
            };
            Runnable runner12 = () -> {
                calculator.accept(productList.get(11), runner13);
            };
            Runnable runner11 = () -> {
                calculator.accept(productList.get(10), runner12);
            };
            Runnable runner10 = () -> {
                calculator.accept(productList.get(9), runner11);
            };
            Runnable runner9 = () -> {
                calculator.accept(productList.get(8), runner10);
            };
            Runnable runner8 = () -> {
                calculator.accept(productList.get(7), runner9);
            };
            Runnable runner7 = () -> {
                calculator.accept(productList.get(6), runner8);
            };
            Runnable runner6 = () -> {
                calculator.accept(productList.get(5), runner7);
            };
            Runnable runner5 = () -> {
                calculator.accept(productList.get(4), runner6);
            };
            Runnable runner4 = () -> {
                calculator.accept(productList.get(3), runner5);
            };
            Runnable runner3 = () -> {
                calculator.accept(productList.get(2), runner4);
            };
            Runnable runner2 = () -> {
                calculator.accept(productList.get(1), runner3);
            };
            Runnable runner1 = () -> {
                calculator.accept(productList.get(0), runner2);
            };

            // 商品名の一覧を出力する
            productList.forEach(p -> printWriter.print(p.getProductType().getLabel() + ","));
            printWriter.println();

            // 商品価格の一覧を出力する
            productList.forEach(p -> printWriter.print(p.getProductType().getPrice() + ","));
            printWriter.println();

            // 計算を実行
            runner1.run();

            // ファイルを閉じる
            printWriter.close();

            System.out.println("--- END ---");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
