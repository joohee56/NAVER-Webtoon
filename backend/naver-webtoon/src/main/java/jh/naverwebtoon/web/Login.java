package jh.naverwebtoon.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  //파라미터에만 적용
@Retention(RetentionPolicy.RUNTIME)  //리플렉션을 활용할 수 있도록 런타임까지 애노테이션 정보가 남아있다.
public @interface Login {
}
