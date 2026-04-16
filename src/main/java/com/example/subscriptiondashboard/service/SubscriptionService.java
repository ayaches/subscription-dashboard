package com.example.subscriptiondashboard.service;

import com.example.subscriptiondashboard.entity.Subscription;
import com.example.subscriptiondashboard.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service：このクラスがビジネスロジック層であることをSpringに伝える
// Springが自動でインスタンスを生成・管理してくれる（DIコンテナ）
@Service
// @RequiredArgsConstructor：finalフィールドを引数にとるコンストラクタを自動生成
// これによりコンストラクタインジェクション（推奨パターン）が実現できる
@RequiredArgsConstructor
public class SubscriptionService {

    // finalにすることでDI（依存性注入）が適用される
    // 外からRepositoryを受け取ることで、テストのときに差し替えが可能になる
    // これがカプセル化＋ポリモーフィズムの実践的な使い方
    private final SubscriptionRepository repository;

    // 全件取得：DBのsubscriptionsテーブルから全レコードを取得する
    public List<Subscription> findAll() {
        return repository.findAll();
    }

    // 登録：受け取ったSubscriptionオブジェクトをDBに保存する
    public void save(Subscription subscription) {
        repository.save(subscription);
    }

    // 削除：IDを指定してDBから該当レコードを削除する
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // 月額合計計算：全サービスのpriceを合計して返す
    // Stream APIを使って全レコードのpriceをint型で取り出し合計する
    public int calculateMonthlyTotal() {
        return repository.findAll()
                .stream()
                .mapToInt((Subscription s) -> s.getPrice())
                .sum();
    }
}