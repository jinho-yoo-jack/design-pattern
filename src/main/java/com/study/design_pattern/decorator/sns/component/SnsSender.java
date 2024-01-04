package com.study.design_pattern.decorator.sns.component;

import com.study.design_pattern.decorator.sns.Notifier;

public class SnsSender implements Notifier {
    String emailAddress;

    public SnsSender(String toAddress) {
        emailAddress = toAddress;
    }

    @Override
    public void send() {
        System.out.println("Send Email ToAddress => " + emailAddress);
    }
}
