package com.example.subscriptiondashboard.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;

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
    private Integer price;          // 月額料金（円）※nullを許容できるInteger型
    private Integer billingDate;    // 請求日（1〜31）※nullを許容できるInteger型
    private String category;    // カテゴリ（例：動画）
    private LocalDateTime createdAt; // 登録日時

    // 請求日まで7日以内かどうかを判定するメソッド
    // trueを返すとHTMLでアラート表示される
    public boolean isAlertTarget() {
        // 今日の日付を取得
        int today = LocalDate.now().getDayOfMonth();
        // 請求日がnullの場合はアラート対象外
        if (billingDate == null) {
            return false;
        }
        // 請求日までの残り日数を計算
        int daysUntilBilling = billingDate - today;
        // 0日以上7日以内ならアラート対象
        return daysUntilBilling >= 0 && daysUntilBilling <= 7;
    }
}