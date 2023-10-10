package ru.otus.lesson13.example.cor;

import ru.otus.lesson13.example.cor.data.AuthCtx;
import ru.otus.lesson13.example.cor.data.User;

public class AuthenticationStep extends AuthStep {

    public AuthenticationStep(final AuthStep next) {
        super(next);
    }

    @Override
    public void check(AuthCtx ctx) {
        System.out.println("---------> Authentication");
        User user = ctx.getUser();
        if (user != null && user.getPasswd().equals(ctx.getPassword())) {
            next(ctx);
        }
    }
}
