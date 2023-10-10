package ru.otus.lesson13.example.cor;


import ru.otus.lesson13.example.cor.data.AuthCtx;
import ru.otus.lesson13.example.cor.data.Resource;

public class PerformLogin {
    public static void main(String[] args) {
        AuthStep authorizationStep = new AuthorizationStep(null);
        AuthStep authenticationStep = new AuthenticationStep(authorizationStep);
        AuthStep getUserStep = new GetUserStep(authenticationStep);


        AuthCtx ctx = new AuthCtx("green", "greenpwd1");
        ctx.setResource(new Resource("resource1"));

        getUserStep.check(ctx);

        System.out.println(ctx.isGranted());
    }
}
