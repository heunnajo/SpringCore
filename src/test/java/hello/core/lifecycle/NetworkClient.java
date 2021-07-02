package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {//이름 그대로 초기화해주는 인터페이스을 상속받는다!
    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);

    }
    public void setUrl(String url){
        this.url = url;
    }
    //서비스 시작 시 호출
    public void connect(){
        System.out.println("connect = " + url);
    }
    public void call(String message){
        System.out.println("call = " + url + " message = " + message);
    }
    //서비스 종료 시 호출
    public void disconnect(){
        System.out.println("close: " + url);
    }

    //말 그대로 property들 셋팅이 끝나면(DI;의존관계 주입 끝나면)
    //DI 끝나면 아래 함수 호출해준다!!!
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }
    //빈이 종료될 때 아래 destroy()호출,실행!
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
