package com.mkyong.helloworld.web;

import java.awt.Font;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

public class PSCImageCaptchaEngine extends ListImageCaptchaEngine {

    protected void buildInitialFactories() {
        //abcdefghijklmnopqrstuvwxyz
        WordGenerator wgen = new RandomWordGenerator("ABCDEFGHIJKLMNPQRSTUVWXYZ123456789");
        RandomRangeColorGenerator cgen = new RandomRangeColorGenerator(
                new int[]{0, 0},
                new int[]{0, 0},
                new int[]{0, 0});

        TextPaster textPaster = new RandomTextPaster(Integer.valueOf(8), Integer.valueOf(8), cgen);
        //(new Integer(7), new Integer(7), cgen, true);
        //BackgroundGenerator backgroundGenerator = new FileReaderRandomBackgroundGenerator(new Integer(153),new Integer(153),"./image/");
        BackgroundGenerator backgroundGenerator = new NoiseBackgroundGenerator(200, 50);
        Font[] fontsList = new Font[]{
            new Font("Comic Sans MS", Font.BOLD, 14),
            new Font("Batang", Font.BOLD, 14),
            new Font("Verdana", Font.BOLD, 14),
            new Font("NewCenturySchlbk", Font.BOLD, 14)
        };

        FontGenerator fontGenerator = new RandomFontGenerator(Integer.valueOf(20), Integer.valueOf(20), fontsList);

        WordToImage wordToImage = new ComposedWordToImage(fontGenerator, backgroundGenerator, textPaster);
        this.addFactory(new GimpyFactory(wgen, wordToImage));
    }
}