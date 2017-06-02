package org.java10.plum.config;

import org.java10.plum.filters.TokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tate<i@ridog.me>
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean authFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new TokenFilter());
        registrationBean.addUrlPatterns("/api/admin/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
