package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {//MyIncludeComponent가 붙은 건 ComponentScan에 추가하겠다.
}
