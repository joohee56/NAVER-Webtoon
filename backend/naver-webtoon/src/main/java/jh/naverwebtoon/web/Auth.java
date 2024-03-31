package jh.naverwebtoon.web;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented //해당 어노테이션을 Javadoc에 포함
@Retention(RetentionPolicy.RUNTIME) //리플렉션을 사용할 수 있도록 런타임 시까지 유지
@Target({ElementType.METHOD}) //메서드에 적용
public @interface Auth {
}
