package io.loli.askloli.controller.api;

import javax.inject.Named;

@Named
public class TestBean {
    public String say(){
        return "Hello Grizzly !";
    }
}
