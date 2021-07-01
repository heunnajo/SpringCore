package hello.core.singleton;

public class StatefulService {//어떤 statefulservice라는 서비스가 있다.
    private int price;//상태 유지하는 필드. \
    public void order(String name,int price){
        System.out.println("name = "+name+" price = "+price);
        this.price = price;//여기가 문제!
    }
    public int getPrice(){
        return  price;
    }
}
