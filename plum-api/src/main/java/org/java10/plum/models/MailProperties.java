package org.java10.plum.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Tate on 2017/5/3.
 */
@Component
@ConfigurationProperties(prefix="mail")
@Getter
@Setter
public class MailProperties {

    private Boolean notification;
    private String from;
    private String to;
    private String subject;
    private String text;

}
