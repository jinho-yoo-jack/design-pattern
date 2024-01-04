package com.study.design_pattern.decorator.sns.deco;

import com.study.design_pattern.decorator.sns.Notifier;

public abstract class BaseDecorator implements Notifier {
    Notifier notifier;
}
