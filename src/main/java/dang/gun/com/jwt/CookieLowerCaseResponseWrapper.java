package dang.gun.com.jwt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CookieLowerCaseResponseWrapper extends HttpServletResponseWrapper {
    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response The response to be wrapped
     * @throws IllegalArgumentException if the response is null
     */
    public CookieLowerCaseResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public void addCookie(Cookie cookie, HttpServletResponse response) {
        String value = cookie.getValue();
        String toLowerCase = value.toLowerCase();
        cookie.setValue(toLowerCase);
        response.addCookie(cookie);
    }
}
