package com.example.subscriptiondashboard.repository;

import com.example.subscriptiondashboard.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepositoryを継承するだけで以下が自動で使えるようになる
// findAll()   → 全件取得
// save()      → 登録・更新
// deleteById()→ 削除
// これがSpring Data JPAの強み。SQLを1行も書かなくていい。
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}