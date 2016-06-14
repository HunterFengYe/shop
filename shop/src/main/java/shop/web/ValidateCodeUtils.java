package shop.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码工具类.
 * 
 * @author HeHongxin
 * @date 2014-2-10
 * 
 */
class ValidateCodeUtils {

	private ValidateCodeUtils() {
	}

	// 随机产生的字符串
	private static final String RANDOM_STRS = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefhijklmnpqrstuvwxy";

	/**
	 * 字体
	 */
	private static final String FONT_NAME = "Fixedsys";

	/**
	 * 字体大小
	 */
	private static final int FONT_SIZE = 24;
	/**
	 * 随机数生成器
	 */
	private static Random random = new Random();
	/**
	 * 图片宽
	 */
	private static int width = 80;
	/**
	 * 图片高
	 */
	private static int height = 24;
	/***
	 * 干扰线数量
	 */
	private static int lineNum = 80;
	/***
	 * 随机产生字符数量
	 */
	private static int strNum = 4;

	/**
	 * 生成随机图片
	 */
	static BufferedImage genRandomCodeImage(StringBuffer randomCode) {
		// BufferedImage类是具有缓冲区的Image类
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		// 获取Graphics对象,便于对图像进行各种绘制操作
		Graphics g = image.getGraphics();
		// 设置背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		// 设置干扰线的颜色
		/*g.setColor(getRandColor(110, 120));

		// 绘制干扰线
		for (int i = 0; i <= lineNum; i++) {
			drowLine(g);
		}*/
		// 绘制随机字符
		g.setFont(new Font(FONT_NAME, Font.ROMAN_BASELINE, FONT_SIZE));
		for (int i = 1; i <= strNum; i++) {
			randomCode.append(drowString(g, i));
		}
		g.dispose();
		return image;
	}

	/**
	 * 给定范围获得随机颜色
	 */
	private static Color getRandColor(int fc, int bc) {
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 绘制字符串
	 */
	private static String drowString(Graphics g, int i) {
		g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
		String rand = String.valueOf(getRandomString(random.nextInt(RANDOM_STRS.length())));
		g.translate(random.nextInt(3), random.nextInt(3));
		g.drawString(rand, 13 * i, 16);
		return rand;
	}

	/**
	 * 绘制干扰线
	 */
	private static void drowLine(Graphics g) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int x0 = random.nextInt(16);
		int y0 = random.nextInt(16);
		g.drawLine(x, y, x + x0, y + y0);
	}

	/**
	 * 获取随机的字符
	 */
	private static String getRandomString(int num) {
		return String.valueOf(RANDOM_STRS.charAt(num));
	}
}
