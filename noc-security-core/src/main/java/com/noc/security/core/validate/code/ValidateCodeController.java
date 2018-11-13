package com.noc.security.core.validate.code;

import com.noc.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    // HttpSessionSessionStrategy 是session工具类
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    ValidateCodeGenerator imageCodeGenerator;

    /**
     * 生成图形验证码
     */
    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 根据随机数生成图片
        ImageCode imageCode = imageCodeGenerator.generate(new ServletWebRequest(request));
        // 将随机数存到Session中
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        // 再将生成的图片写到接口的响应中
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }


}
