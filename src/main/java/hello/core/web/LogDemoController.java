package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor//생성자 자동주입!
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;//mylogger를 주입하는 게 아니라! mylogger를 DL할 수 있는 애를 받는다!

    @RequestMapping("log-demo")
    @ResponseBody//원래는 뷰템플릿 거쳐서 랜더링되어 나가야하는데 문자그대로 응답으로 보낸다!
    public String logDemo(HttpServletRequest request){//자바에서 제공하는 표준 서브릿규약으로 고객 요청 정보 받을 수 있다!
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject();//getObject를 하는 시점에 만들어진다!
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }

}
