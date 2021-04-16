package com.github.holalee.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * 字体工具类
 * @author jane
 *
 */
public class FontsUtil {
    private static final Logger logger = LoggerFactory.getLogger(FontsUtil.class);

    /**
     * 宋体
     * @param style
     * @param size
     */
    public static Font getSIMSUN(int style, float size) {
        Font font = null;
        //获取字体流
         InputStream simsunFontFile = FontsUtil.class.getResourceAsStream("/fonts/simsun.ttf");
        try {
        //创建字体
        font = Font.createFont(Font.PLAIN, simsunFontFile).deriveFont(style, size);
        } catch (FontFormatException e) {
            logger.error("",e);
        } catch (IOException e) {
            font = new Font("宋体", Font.BOLD, 6);
            logger.error("",e);
        }
        return font;
    }

}
