package com.example.subscriptiondashboard.controller;

import com.example.subscriptiondashboard.entity.Subscription;
import com.example.subscriptiondashboard.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// @Controller：このクラスがMVCのC（Controller）層であることをSpringに伝える
// ブラウザからのリクエストを受け取り、Serviceに処理を委譲し、Viewを返す
@Controller
// @RequiredArgsConstructor：finalフィールドへのコンストラクタインジェクションを自動生成
@RequiredArgsConstructor
// @RequestMapping：このクラス全体のURLの起点を"/subscriptions"に設定する
@RequestMapping("/subscriptions")
public class SubscriptionController {

    // ServiceをDIで受け取る（直接newしないのがポリモーフィズムの考え方）
    private final SubscriptionService service;

    // GET /subscriptions：一覧画面を表示する
    // Modelにデータを入れてHTMLに渡す（MVCのV＝Viewへのデータ受け渡し）
    @GetMapping
    public String index(Model model) {
        // 全サブスクリプション一覧をModelに追加
        model.addAttribute("subscriptions", service.findAll());
        // 月額合計をModelに追加
        model.addAttribute("total", service.calculateMonthlyTotal());
        // フォームのバインド用に空のオブジェクトをModelに追加
        model.addAttribute("subscription", new Subscription());
        // templates/subscriptions/index.htmlを返す
        return "subscriptions/index";
    }

    // POST /subscriptions：フォームから送信されたデータを登録する
    // @ModelAttribute：フォームの入力値をSubscriptionオブジェクトに自動マッピング
    @PostMapping
    public String create(@ModelAttribute Subscription subscription) {
        service.save(subscription);
        // PRGパターン：登録後にリダイレクトして二重送信を防止する
        return "redirect:/subscriptions";
    }

    // POST /subscriptions/{id}/delete：指定IDのサブスクリプションを削除する
    // @PathVariable：URLの{id}部分をLong型の変数として受け取る
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/subscriptions";
    }
}