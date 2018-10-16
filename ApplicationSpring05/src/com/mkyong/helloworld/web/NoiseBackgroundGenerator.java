package com.mkyong.helloworld.web;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;

public class NoiseBackgroundGenerator implements BackgroundGenerator {

    private int height;
    private int width;
    private int noiseNum;
    private float noiseLength;
    private int round = 0;
    private Random r;

    public NoiseBackgroundGenerator(int width, int height) {
        this(width, height, 50, 100f);
    }

    public NoiseBackgroundGenerator(int width, int height, int noiseNum, float noiseLength) {
        this.width = width;
        this.height = height;
        this.noiseNum = noiseNum;
        this.noiseLength = noiseLength;
        r = new Random();
    }

    public BufferedImage getBackground() {
        BufferedImage bimgTP = new BufferedImage(getImageWidth(), getImageHeight(), 4);

        Graphics2D g2d = bimgTP.createGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, getImageWidth(), getImageHeight());

        Color[] colors = {new Color(255, 255, 64), new Color(64, 255, 255), new Color(255, 128, 128), new Color(192, 192, 192)};

        for (int i = 0; i < this.noiseNum; i++) {
            Point2D.Float init = new Point2D.Float(r.nextInt(getImageWidth()), r.nextInt(getImageHeight()));
            Point2D.Float end = getRandomPoint(init);

            g2d.setColor(colors[r.nextInt(colors.length)]);

            g2d.drawLine((int) init.getX(), (int) init.getY(), (int) end.getX(), (int) end.getY());

        }

        g2d.dispose();
        return bimgTP;
    }

    private Point2D.Float getRandomPoint(Point2D.Float init) {
        Point2D.Float p = new Point2D.Float();
        double angle = r.nextInt(90);

        double x = Math.cos(Math.toRadians(angle)) * noiseLength;
        double y = Math.sin(Math.toRadians(angle)) * noiseLength;

        int q = getNextQ();
        if (q == 0) {
            y = y * (-1);
        } else if (q == 1) {
            x = x * (-1);
        } else if (q == 2) {
            x = x * (-1);
            y = y * (-1);
        }
        p.setLocation(init.getX() + x, init.getY() + y);
        return p;
    }

    public int getNextQ() {
        round++;
        if (round > 3) {
            round = 0;
        }
        return round;
    }

    public int getImageHeight() {
        return height;
    }

    public int getImageWidth() {
        return width;
    }
}
