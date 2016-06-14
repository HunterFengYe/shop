package shop.utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
public class StringUtils {
	public static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * ������ת��Ϊjson
	 * 
	 * @param obj
	 *            ����
	 * @return ����Ϊnull����null�����򷵻�json
	 */
	public static String json(Object obj) {
		if (null != obj) {
			try {
				return MAPPER.writeValueAsString(obj);
			} catch (JsonProcessingException e) {
			}
		}
		return null;
	}
	/**
	 * �ַ���ת��Ϊint�ͼ���
	 * 
	 * @param str
	 *            �ַ���
	 * @return
	 */
	public static List<Integer> intList(String str) {
		if (null != str && str.length() > 0) {
			List<Integer> list = new ArrayList<Integer>();
			for (String s : str.split(",")) {
				s = s.trim();
				if (!s.isEmpty()) {
					try {
						list.add(Integer.parseInt(s));
					} catch (NumberFormatException e) {
					}
				}
			}
			if (!list.isEmpty()) {
				return list;
			}
		}
		return null;
	}

	/**
	 * �ַ���ת��Ϊint������
	 * 
	 * @param str
	 *            �ַ���
	 * @return
	 */
	public static Integer[] intArr(String str) {
		if (null != str && str.length() > 0) {
			List<Integer> list = new ArrayList<Integer>();
			for (String s : str.split(",")) {
				s = s.trim();
				if (s.length() > 0) {
					try {
						list.add(Integer.parseInt(s));
					} catch (NumberFormatException e) {
					}
				}
			}
			if (!list.isEmpty()) {
				return list.toArray(new Integer[list.size()]);
			}
		}
		return null;
	}

	/**
	 * �ַ���ת��Ϊlong�ͼ���
	 * 
	 * @param str
	 *            �ַ���
	 * @return
	 */
	public static List<Long> longList(String str) {
		if (null != str && str.length() > 0) {
			List<Long> list = new ArrayList<Long>();
			for (String s : str.split(",")) {
				s = s.trim();
				if (!s.isEmpty()) {
					try {
						list.add(Long.parseLong(s));
					} catch (NumberFormatException e) {
					}
				}
			}
			if (!list.isEmpty()) {
				return list;
			}
		}
		return null;
	}

	/**
	 * �ַ���ת��Ϊlong������
	 * 
	 * @param str
	 *            �ַ���
	 * @return
	 */
	public static Long[] longArr(String str) {
		if (null != str && str.length() > 0) {
			List<Long> list = new ArrayList<Long>();
			for (String s : str.split(",")) {
				s = s.trim();
				if (s.length() > 0) {
					try {
						list.add(Long.parseLong(s));
					} catch (NumberFormatException e) {
					}
				}
			}
			if (!list.isEmpty()) {
				return list.toArray(new Long[list.size()]);
			}
		}
		return null;
	}

	/**
	 * �ַ���ת��ΪString������
	 * 
	 * @param str
	 *            �ַ���
	 * @return
	 */
	public static String[] strArr(String str) {
		if (null != str && str.length() > 0) {
			List<String> list = new ArrayList<String>();
			for (String s : str.split(",")) {
				s = s.trim();
				if (s.length() > 0) {
					try {
						list.add(s);
					} catch (Exception e) {
					}
				}
			}
			if (!list.isEmpty()) {
				return list.toArray(new String[list.size()]);
			}
		}
		return null;
	}
	public static String RandomMath(){
		String math="";
		Random ra=new Random();
		for(int i=0;i<8;i++){
			math+=ra.nextInt(10);
		}
		math="U"+math;
		return math;
	}
}
