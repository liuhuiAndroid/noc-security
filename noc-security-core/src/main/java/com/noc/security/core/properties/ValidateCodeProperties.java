package com.noc.security.core.properties;

/**
 * 实现InitializingBean的目的是：在
 */
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }

}
