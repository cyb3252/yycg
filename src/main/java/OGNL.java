import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * 此类主要用于 mapper 文件中非空验证
 *
 *  @author cyb
 *  @date 2019/4/6 - 17:35
 * @demo <if test="orderColumn!= null and orderColumn != ''"> <br/>
 * ORDER BY t.${orderColumn} <if test="orderColumn!= null and orderDirection != ''">${orderDirection}</if><br/>
 * </if><br/>
 * <br/>
 * 就可以修改为 ：<br/>
 * <if test="@Ognl@isNotEmpty(orderColumn)"> <br/>
 * ORDER BY t.${orderColumn} <if test="@Ognl@isNotEmpty(orderColumn)">${orderDirection}</if><br/>
 * </if><br/>
 */
public class OGNL {

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    /**
     *
     * @param o
     * @return
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            if (((String) o).length() == 0) {
                return true;//空字符串放开验证，执行数据库操作
            }
        } else if (o instanceof Collection) {
            if (((Collection) o).isEmpty()) {
                return true;
            }
        } else if (o.getClass().isArray()) {
            if (Array.getLength(o) == 0) {
                return true;
            }
        } else if (o instanceof Map) {
            if (((Map) o).isEmpty()) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }
}