package ru.otus.lesson13.example.cor;


import ru.otus.lesson13.example.cor.data.AuthCtx;

public abstract class AuthStep {

    private final AuthStep next;

    public AuthStep(final AuthStep next) {
        this.next = next;
    }

    public abstract void check(AuthCtx ctx);

    public final void next(AuthCtx ctx) {
        if (next != null) {
            next.check(ctx);
        }
    }
}
