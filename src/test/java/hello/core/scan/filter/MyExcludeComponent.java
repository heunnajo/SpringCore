package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {//MyExcludeComponent가 붙은 건 ComponentScan에서 제외하겠다.
}
