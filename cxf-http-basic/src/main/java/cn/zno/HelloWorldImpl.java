
package cn.zno;

import javax.jws.WebService;

@WebService(endpointInterface = "cn.zno.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        return "Hello " + text;
    }
}

