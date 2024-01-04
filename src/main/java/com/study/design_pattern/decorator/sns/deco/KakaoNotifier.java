package com.study.design_pattern.decorator.sns.deco;

import com.study.design_pattern.decorator.sns.Notifier;

public class KakaoNotifier extends BaseDecorator {
    public KakaoNotifier(Notifier n) {
        notifier = n;
    }

    @Override
    public void send() {
        notifier.send();
        System.out.println("Kakao message sended");
    }
}
