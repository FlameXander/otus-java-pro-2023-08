package ru.otus.lesson13.example.cor;

import ru.otus.lesson13.example.cor.data.AuthCtx;
import ru.otus.lesson13.example.cor.data.User;
import ru.otus.lesson13.example.cor.data.UserStorage;

public class GetUserStep extends AuthStep {

    public GetUserStep(final AuthStep next) {
        super(next);
    }

    @Override
    public void check(AuthCtx ctx) {
        System.out.println("---------> Looking for user");
        User user = UserStorage.getUser(ctx.getUserName());
        if (user != null) {
            ctx.setUser(user);
            next(ctx);
        }
    }
}
