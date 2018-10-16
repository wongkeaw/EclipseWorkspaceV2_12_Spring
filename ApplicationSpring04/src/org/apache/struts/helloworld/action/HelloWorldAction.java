package org.apache.struts.helloworld.action;

public class HelloWorldAction {

    public String execute() {
    	System.out.println("Struts Action");
        return "SUCCESS";
    }

}