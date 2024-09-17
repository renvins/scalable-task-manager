package org.github.scalabletaskmanager.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class JwtUtil {

    /* IMPORTANT: Use only inside request contexts */
    public static String getJwtFromRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String authHeader = attributes.getRequest().getHeader("Authorization");

        return authHeader.substring(7);
    }
}
