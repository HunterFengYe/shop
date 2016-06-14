package shop.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * ��֤�빤����.
 * 
 * @author HeHongxin
 * @date 2014-2-10
 * 
 */
class ValidateCodeUtils {

	private ValidateCodeUtils() {
	}

	// ����������ַ���
	private static final String RANDOM_STRS = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefhijklmnpqrstuvwxy";

	/**
	 * ����
	 */
	private static final String FONT_NAME = "Fixedsys";

	/**
	 * �����С
	 */
	private static final int FONT_SIZE = 24;
	/**
	 * �����������
	 */
	private static Random random = new Random();
	/**
	 * ͼƬ��
	 */
	private static int width = 80;
	/**
	 * ͼƬ��
	 */
	private static int height = 24;
	/***
	 * ����������
	 */
	private static int lineNum = 80;
	/***
	 * ��������ַ�����
	 */
	private static int strNum = 4;

	/**
	 * �������ͼƬ
	 */
	static BufferedImage genRandomCodeImage(StringBuffer randomCode) {
		// BufferedImage���Ǿ��л�������Image��
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		// ��ȡGraphics����,���ڶ�ͼ����и��ֻ��Ʋ���
		Graphics g = image.getGraphics();
		// ���ñ���ɫ
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		// ���ø����ߵ���ɫ
		/*g.setColor(getRandColor(110, 120));

		// ���Ƹ�����
		for (int i = 0; i <= lineNum; i++) {
			drowLine(g);
		}*/
		// ��������ַ�
		g.setFont(new Font(FONT_NAME, Font.ROMAN_BASELINE, FONT_SIZE));
		for (int i = 1; i <= strNum; i++) {
			randomCode.append(drowString(g, i));
		}
		g.dispose();
		return image;
	}

	/**
	 * ������Χ��������ɫ
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
	 * �����ַ���
	 */
	private static String drowString(Graphics g, int i) {
		g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
		String rand = String.valueOf(getRandomString(random.nextInt(RANDOM_STRS.length())));
		g.translate(random.nextInt(3), random.nextInt(3));
		g.drawString(rand, 13 * i, 16);
		return rand;
	}

	/**
	 * ���Ƹ�����
	 */
	private static void drowLine(Graphics g) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int x0 = random.nextInt(16);
		int y0 = random.nextInt(16);
		g.drawLine(x, y, x + x0, y + y0);
	}

	/**
	 * ��ȡ������ַ�
	 */
	private static String getRandomString(int num) {
		return String.valueOf(RANDOM_STRS.charAt(num));
	}
}
