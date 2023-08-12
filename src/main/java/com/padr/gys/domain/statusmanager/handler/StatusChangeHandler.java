package com.padr.gys.domain.statusmanager.handler;

import java.util.concurrent.Flow;

public abstract class StatusChangeHandler<T> implements Flow.Subscriber<T> {

    protected Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
