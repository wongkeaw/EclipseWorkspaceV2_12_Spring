package com.mkyong.helloworld.web;

import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;

public class CaptchaServiceSingleton {

    private static ImageCaptchaService instance = new DefaultManageableImageCaptchaService();
    /*
     public static ImageCaptchaService getInstance(){
     return instance;
     }*/

    static {
        instance = new DefaultManageableImageCaptchaService(
                new FastHashMapCaptchaStore(),
                new PSCImageCaptchaEngine(),
                180,
                100000,
                75000);
    }

    public static ImageCaptchaService getInstance() {
        return instance;
    }
}
