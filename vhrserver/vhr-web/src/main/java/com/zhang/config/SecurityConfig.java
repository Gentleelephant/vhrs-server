package com.zhang.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhang.model.Hr;
import com.zhang.model.RespBean;
import com.zhang.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author:Zpg
 * @Date:2020/3/23 11:51
 * @Version:1.0
 * @Description:
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    HrService hrService;
    @Autowired
    CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;
    @Autowired
    CustomUrlDecisionManager customUrlDecisionManager;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(customUrlDecisionManager);
                        object.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                        return object;
                    }
                })
                .and()
                .formLogin(formLogin ->
                        formLogin
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .loginProcessingUrl("/doLogin")     // 表单登录提交请求
                                .loginPage("/login")
                                .successHandler(new AuthenticationSuccessHandler() {    // 登陆成功的方法
                                    @Override
                                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                        response.setContentType("application/json;charset=utf-8");
                                        PrintWriter out = response.getWriter();
                                        Hr principal = (Hr) authentication.getPrincipal();
                                        principal.setPassword(null);
                                        RespBean ok = RespBean.ok("登陆成功", principal);
                                        String string = new ObjectMapper().writeValueAsString(ok);
                                        out.write(string);
                                        out.flush();
                                        out.close();
                                    }
                                })
                                .failureHandler(new AuthenticationFailureHandler() {
                                    @Override
                                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                                        response.setContentType("application/json;charset=utf-8");
                                        PrintWriter out = response.getWriter();
                                        RespBean respBean = RespBean.error("登陆失败");
                                        if (e instanceof LockedException) {
                                            respBean.setMsg("账户被锁定,请联系管理员");
                                        } else if (e instanceof CredentialsExpiredException) {
                                            respBean.setMsg("密码过期,请联系管理员");
                                        } else if (e instanceof AccountExpiredException) {
                                            respBean.setMsg("账户过期,请联系管理员");
                                        } else if (e instanceof DisabledException) {
                                            respBean.setMsg("账户被禁用,请联系管理员");
                                        } else if (e instanceof BadCredentialsException) {
                                            respBean.setMsg("用户名或者密码输入错误,请重新输入!");
                                        }
                                        String string = new ObjectMapper().writeValueAsString(respBean);
                                        out.write(string);
                                        out.flush();
                                        out.close();
                                    }
                                })
                                .permitAll()
                )
                .logout()
                // 注销登录
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = response.getWriter();
                        writer.write(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功!")));
                        writer.flush();
                        writer.close();
                    }
                })
                .permitAll()
                .and()
                .csrf().disable()
                // 没有认证时，在这里处理结果，不要重定向
                .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        response.setStatus(401);
                        PrintWriter out = response.getWriter();
                        RespBean respBean = RespBean.error("登陆失败");
                        if (e instanceof InsufficientAuthenticationException) {
                            respBean.setMsg("尚未登陆！");
                        }
                        String string = new ObjectMapper().writeValueAsString(respBean);
                        out.write(string);
                        out.flush();
                        out.close();
                    }
        });
    }
}
