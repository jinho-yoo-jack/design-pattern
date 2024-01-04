package com.study.design_pattern.decorator;

import com.study.design_pattern.decorator.sns.Notifier;
import com.study.design_pattern.decorator.sns.component.SnsSender;
import com.study.design_pattern.decorator.sns.deco.KakaoNotifier;
import com.study.design_pattern.decorator.starbucks.deco.MokaCoffee;

public class main {
    public static void main(String[] args) {
        Notifier notifier = new SnsSender("jhyoo01@kcp.co.kr");
        notifier = new KakaoNotifier(notifier);
        notifier.send();
    }
}
