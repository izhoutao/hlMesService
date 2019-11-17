package cn.hutool;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HutoolTest {
    @Test
    public void testHutool() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("type_id", "type_id");
        map.put("nameId", "张三");
        Map<String, Object> camelCaseMap = MapUtil.toCamelCaseMap(map);
        System.out.println(camelCaseMap);
    }

    @Test
    public void testHutool1() {

        System.out.println(StringUtils.isEmpty(" "));
    }
}