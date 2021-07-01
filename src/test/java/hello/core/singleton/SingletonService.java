package hello.core.singleton;

public class SingletonService {
    //자기 자신 선언. 자기 자신을 내부에 private static으로. - static 영역 공부해라.
    //이렇게 static으로 하면 클래스 래밸에 올라가므로 딱 하나만 존재하게 된다!
    private static final SingletonService instance = new SingletonService();

    //JVM 실행하면 객체(자기자신)를 생성해서 instance 안에 참조를 넣는다!
    //instance를 꺼낼 수 있는 방법은 getInstance() 밖에 없다.
    //instance를 생성할 수 있는 방법도 없다! private으로 막아버렸기 때문.
    public static SingletonService getInstance(){
        return instance;
    }
    //그래서 private 생성자로 막아버린다!
    private SingletonService(){

    }
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
//    public static void main(String[] args) {
//        //내부에 감추려고 해도 아래처럼 막 생성할 수 있다!
//        SingletonService singletonService1 = new SingletonService();
//        SingletonService singletonService2 = new SingletonService();
//    }
}
