package com.example.helloworld.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
注意：这里和LibraryProperties 不同的是，没有 Component注解
 所以，在使用的地方，需要 使用@EnableConfigurationProperties注册我们的配置bean
 */

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "my-profile")
@Validated
public class ProfileProperties {
    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    private String email;

    //配置文件中没有读取到，就使用默认值
    private Boolean handsome = Boolean.TRUE;
}
