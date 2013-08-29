package io.loli.askloli.controller.ajax;

import javax.inject.Named;

@Named
public class TestBean {
    public String say(){
        return "Hello Grizzly !";
    }
}
