package com.wuhaowen.rxsocketio;

import android.support.annotation.NonNull;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.socket.client.Socket;

public class RxSocketIO {
    public static Flowable<Msg> on(@NonNull final Socket socket, @NonNull final String event) {
        return Flowable.<Msg>create(emitter -> socket.on(event, args -> {
            if (!emitter.isCancelled()) {
                emitter.onNext(new Msg(event, args));
            }
        }), BackpressureStrategy.DROP).doFinally(() -> socket.off(event));

    }

    public static Flowable<Msg> on(@NonNull final Socket socket, @NonNull final String... events) {
        return Flowable.create((FlowableOnSubscribe<Msg>) emitter -> {
            for (String event : events) {
                socket.on(event, args -> {
                    if (!emitter.isCancelled()) {
                        emitter.onNext(new Msg(event, args));
                    }
                });
            }

        }, BackpressureStrategy.DROP).doFinally(() -> {
                    for (String event : events) {
                        socket.off(event);
                    }
                }
        );
    }

    public static Flowable<Msg> once(@NonNull final Socket socket, @NonNull final String event) {
        return Flowable.<Msg>create(emitter -> socket.once(event, args -> {
            if (!emitter.isCancelled()) {
                emitter.onNext(new Msg(event, args));
                emitter.onComplete();
            }
        }), BackpressureStrategy.DROP).doFinally(() -> socket.off(event));

    }


}
