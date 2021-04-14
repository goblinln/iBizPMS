package cn.ibizlab.pms.util.helper;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;


/**
 * 对文本进行转化
 *
 * @author Administrator
 */
public class CaseFormatMethod {
    static Map<String, CaseFormat> CaseFormatMap = new HashMap<String, CaseFormat>();

    static {
        CaseFormatMap.put("l-h", CaseFormat.LOWER_HYPHEN);
        CaseFormatMap.put("lC", CaseFormat.LOWER_CAMEL);
        CaseFormatMap.put("l_u", CaseFormat.LOWER_UNDERSCORE);
        CaseFormatMap.put("U_U", CaseFormat.UPPER_UNDERSCORE);
        CaseFormatMap.put("UC", CaseFormat.UPPER_CAMEL);
    }

    private static String Format = "(value,'x2y')，xy为l-h;lC;l_u;U_U;UC";

    public static String exec(String code, String format) {

        if (StringUtils.isBlank(code)) {
            return "";
        }

		if (StringUtils.isBlank(format)) {
            return code;
        }

        String[] parts = format.split("[2]");
        if (parts.length != 2) {
            return Format;
        }

        CaseFormat from = CaseFormatMap.get(parts[0]);
        CaseFormat to = CaseFormatMap.get(parts[1]);
        if (from == null || to == null) {
            return Format;
        }

        return from.to(to, code);
    }
}
