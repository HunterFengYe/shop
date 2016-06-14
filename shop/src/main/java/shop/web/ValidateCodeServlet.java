package shop.web;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * 楠岃瘉鐮佺敓鎴怱ervlet.
 * 
 * @author HeHongxin
 * @date 2014-2-10
 * 
 */
public class ValidateCodeServlet extends HttpServlet {

	private static final long serialVersionUID = -124247581620199710L;

	public static final String VALIDATE_CODE = "validateCode";

	private static final Logger LOG = Logger.getLogger(ValidateCodeServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 璁剧疆鐩稿簲绫诲瀷,鍛婅瘔娴忚鍣ㄨ緭鍑虹殑鍐呭涓哄浘鐗�
		resp.setContentType("image/jpeg");
		// 涓嶇紦瀛樻鍐呭
		resp.setHeader("Pragma", "No-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expire", 0);
		try {
			HttpSession session = req.getSession();
			StringBuffer code = new StringBuffer();
			BufferedImage image = ValidateCodeUtils.genRandomCodeImage(code);
			session.removeAttribute(VALIDATE_CODE);
			session.setAttribute(VALIDATE_CODE, code.toString());
			// 灏嗗唴瀛樹腑鐨勫浘鐗囬�氳繃娴佸姩褰㈠紡杈撳嚭鍒板鎴风
			ImageIO.write(image, "JPEG", resp.getOutputStream());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}