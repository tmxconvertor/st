package com.st;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Action {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TextProps.class);
        TextProps tp = ac.getBean(TextProps.class);
        System.out.println(tp.getText());
//        ac.scan("");

        SomeClass t =  ac.getBean(SomeClass.class);

        System.out.println(t.text.getText());
        System.out.println(t.text.getAnother());
        t.setText(ac.getBean(TextProps.class));

        for (int i=0;i<5;i++) {
            System.out.println(new SomeClass(t.text,"fake").getNumber());
        }
        System.out.println("-----");
        for (int i=0;i<5;i++) {
            System.out.println(ac.getBean(SomeClass.class).getNumber());
        }

        ExecutorService executor = Executors.newFixedThreadPool(4);
//        for (int i = 0; i < 5; i++) {
//
//        System.out.println(t.getWebClient().getPage("http://gidonline.in/page/"+i+"/").getWebResponse().getContentAsStream());
//        }

        Future<HtmlPage>[] future = new Future[10];
        for (int i = 0; i < future.length; i++) {
            int finalI = i;
            future[i] = executor.submit(
                    () -> {
                        System.out.println("==>"+finalI);
                        return t.getWebClient().getPage("http://gidonline.in/page/" + finalI + "/");
                    });
        }
        executor.awaitTermination(100, TimeUnit.MILLISECONDS);
        System.out.println(Arrays.stream(future)
                .map((a)-> {
                    try {
                        return a.get().getWebResponse().getStatusMessage();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return "Exception";
                })
                .collect(Collectors.joining(";")));
        executor.shutdown();
    }

}
