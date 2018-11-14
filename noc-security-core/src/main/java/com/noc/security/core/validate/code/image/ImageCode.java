package com.noc.security.core.validate.code.image;

import com.noc.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 封装图片验证码信息
 */
public class ImageCode extends ValidateCode {

    // 图片
    private BufferedImage image;

    /**
     * @param image    图片
     * @param code     图片验证码
     * @param expireIn 多少秒之后过期
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
