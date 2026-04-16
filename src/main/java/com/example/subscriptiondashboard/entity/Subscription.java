package com.example.subscriptiondashboard.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

// @Entity：このクラスがDBのテーブルと対応していることをJPAに伝える
@Entity
// @Data：getter/setter/toString等をLombokが自動生成（カプセル化を楽に実現）
@Data
// @NoArgsConstructor：JPAが必要とする引数なしコンストラクタを自動生成
@NoArgsConstructor
public class Subscription {

    // @Id：この項目が主キー（レコードの識別番号）
    @Id
    // @GeneratedValue：IDをDBが自動で採番する（1,2,3...と増えていく）
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // サービス名（例：Netflix）
    private int price;          // 月額料金（円）
    private int billingDate;    // 請求日（1〜31）
    private String category;    // カテゴリ（例：動画）
    private LocalDateTime createdAt; // 登録日時
}