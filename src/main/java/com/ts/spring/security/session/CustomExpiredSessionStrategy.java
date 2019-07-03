package com.ts.spring.security.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//旧用户被踢出后执行的类
public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy {
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    //旧用户被踢出后执行的方法
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("code","0");
        map.put("msg","已经另一台机器登录，你被迫下线。"+
                sessionInformationExpiredEvent.getSessionInformation().getLastRequest());
        String json = objectMapper.writeValueAsString(map);
        sessionInformationExpiredEvent.getResponse().getWriter().write(json);
    }
}
