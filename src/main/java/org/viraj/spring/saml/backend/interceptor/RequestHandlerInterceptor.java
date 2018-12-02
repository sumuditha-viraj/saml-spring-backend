package org.viraj.spring.saml.backend.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class RequestHandlerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(RequestHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isValid = false;
        if (request != null) {
            Cookie c = WebUtils.getCookie(request, "AUTHID");
            HttpSession s = request.getSession();
            LOG.debug(" AUTHID :: " + ((c != null) ? (c.getValue() + " MAX_AGE : " + c.getMaxAge() + " PATH : " + c.getPath()) : ""));
            if (request.getSession() != null) {
                LOG.debug("Session ID :: " + s.getId());
            }
            if (c != null && s != null && Pattern.matches(s.getId() + "(\\w*\\W*)*", c.getValue())) {
                LOG.debug("Valid Session  ");
                isValid = isAuthorizedUser(request);
            } else {
                LOG.debug("Invalid Session ");
            }
        }
        if (!isValid) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            LOG.debug("Valid User");
        }
        return isValid;
    }

    private boolean isAuthorizedUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionId = request.getRequestedSessionId();
        LOG.debug("Session ID :   " + sessionId);
        if (sessionId == null || session == null || !sessionId.equals(session.getId())) {
            return false;
        }
        if (Objects.isNull(session.getAttribute("N"))) {
            return false;
        }
        return true;
    }
}
