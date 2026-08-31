package com.config;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Resource
    private JwtUtils jwtUtils ;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //设置响应验证码
        response.setContentType("text/html;charset=utf-8");
        //拦截器允许 跨域请求
        if(request.getMethod().equals("OPTIONS")){//在拦截器中设置允许跨域
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Headers","*");
            response.setHeader("Access-Control-Allow-Methods","*");
            response.setHeader("Access-Control-Allow-Credentials","true");
            response.setHeader("Access-Control-Max-Age","3600");
            return true;
        }
        /** 地址过滤 */
        String uri = request.getRequestURI();
        if (uri.contains("/login")){
            return true ;
        }
        /** Token 验证 */
        String token = request.getHeader(jwtUtils.getHeader());
        //如果token为空，代表   未登录状态
        if(StringUtils.isEmpty(token)){
            HashMap<String, Object> map = new HashMap<>();
            map.put("data","未登录，请先登录");
            Object json = JSONObject.toJSON(map);
            response.getWriter().write(json.toString());
            return false;
        }
        //token 不为空时，进行token失效性 校验
        Claims claims = null;
        try{
            claims = jwtUtils.getTokenClaim(token);
            //判断 token是否有效，无效时返回 失效信息
            if(claims == null || jwtUtils.isTokenExpired(claims.getExpiration())){
                HashMap<String, Object> map = new HashMap<>();
                map.put("data","Token失效，请重新登录！");
                Object json = JSONObject.toJSON(map);
                response.getWriter().write(json.toString());
                return false;
            }
        }catch (Exception e){
            HashMap<String, Object> map = new HashMap<>();
            map.put("data","Token失效，请重新登录！");
            Object json = JSONObject.toJSON(map);
            response.getWriter().write(json.toString());
            return false;
        }
        /** 设置 identityId 用户身份ID */
        request.setAttribute("identityId", claims.getSubject());
        return true;
    }
}
