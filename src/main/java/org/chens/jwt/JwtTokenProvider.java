package org.chens.jwt;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * jwt 工具类
 * 这个博客中描述了jwt是什么东西 http://www.jianshu.com/p/576dbf44b2ae
 *
 * @author songchunlei@qq.com
 * @since 2018/3/1
 */
public class JwtTokenProvider {

    private JwtConfiguration configuration;

    public JwtTokenProvider(JwtConfiguration configuration) {
        this.setConfiguration(configuration);
    }

    /**
     * 获取用户token-key
     *
     * @return
     */
    public String getUserTokenKey() {
        return configuration.getTokenKey();
    }

    /**
     * 生成token
     *
     * @return
     */
    public String createToken(Claims claims) {
        String compactJws = Jwts.builder()
                //内容
                .setPayload(JSONObject.toJSONString(claims))
                .compressWith(CompressionCodecs.DEFLATE)
                //加密
                .signWith(SignatureAlgorithm.HS512, configuration.getSecretKeySpec()).compact();
        return compactJws;
    }

    /**
     * token转换
     */
    public Claims parseToken(String token) throws Exception {
        return Jwts.parser().setSigningKey(configuration.getSecretKeySpec()).parseClaimsJws(token).getBody();
    }

    public JwtConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(JwtConfiguration configuration) {
        this.configuration = configuration;
    }


}
