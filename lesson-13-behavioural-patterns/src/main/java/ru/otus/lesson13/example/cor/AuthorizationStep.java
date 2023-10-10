package ru.otus.lesson13.example.cor;

import ru.otus.lesson13.example.cor.data.AuthCtx;
import ru.otus.lesson13.example.cor.data.Realm;
import ru.otus.lesson13.example.cor.data.User;

public class AuthorizationStep extends AuthStep {
    public AuthorizationStep(final AuthStep next) {
        super(next);
    }

    @Override
    public void check(AuthCtx ctx) {
        System.out.println("---------> Authorisation");
        User user = ctx.getUser();
        Realm realm = user.getRealm();
        if (realm.checkResource(ctx.getResource())) {
            ctx.setGranted(true);
            next(ctx);
        }
    }
}
