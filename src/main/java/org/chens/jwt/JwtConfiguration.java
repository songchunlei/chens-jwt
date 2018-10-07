package org.chens.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * 自定义的配置加载类，spring启动后，会讲这个类实例化，并将配置文件中与之对应的值注入进来
 *
 * @author songchunlei@qq.com
 * @since 2018/3/1
 */
@Data
public class JwtConfiguration {
    /**
     * 加密token用的key
     */
    private String secretKey;
    /**
     * token来源
     */
    private String iss;
    /**
     * 有效期：分钟
     */
    private int expm;
    /**
     * 用户端token名
     */
    private String tokenKey = JwtConstants.AUTH_TOKEN_KEY;

    /**
     * 获取加密key
     * @return SecretKeySpec
     */
    public SecretKeySpec getSecretKeySpec() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.secretKey
                .getBytes(), SignatureAlgorithm.HS512.getJcaName());
        return secretKeySpec;
    }

    /**
     * 获取失效时间
     *
     * @return
     */
    public Date getExpiration() {
        return new Date(System.currentTimeMillis() + this.expm * 1000 * 60);
    }
}
