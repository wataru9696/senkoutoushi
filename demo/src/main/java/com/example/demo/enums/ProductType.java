package com.example.demo.enums;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品の種類を表す定数クラス
 *
 * @author wataru okamura
 */
@AllArgsConstructor
@Getter
public enum ProductType {

    P1("トリートメント、シャンプー", 3700),
    P2("クリーム、ギャバサポート", 6300),
    P3("エッセンスセラム", 26000),
    P4("ローション、チュアブリーズ", 5300),
    P5("フォームウォッシング、ビー ローヤルゼリードリンク", 4200),
    P6("クロアクティブ（10本）", 8500),
    P7("グリーン", 5800),
    P8("オイルセレクト", 8400),
    P9("ビタミンバランス", 7900),
    P10("ボディソープ", 3200),
    P11("フェイスアンドボディシート", 530),
    P12("ビー オーガニックバーム", 3900),
    P13("ビー プロポリス", 14700),
    P14("ビー オーガニックヘアオイル 25ml", 3600),
    P15("ビー オーガニックヘアオイル 50ml", 5100);

    // 商品名
    private String label;

    // 価格（税抜）
    private int price;

    // 商品名からタイプへの変換メソッド
    public static ProductType toType(String label) {

        return Arrays.stream(ProductType.values())
                .filter(value -> value.getLabel() == label)
                .findFirst()
                .get();
    }

}
