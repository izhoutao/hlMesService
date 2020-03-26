package com.haili.framework.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2018/2/6.
 */

public class WorkflowUtil {


    public static void main(String[] args) {
        String jsonTextWorkflow = "[{\"next\": [{\"index\": 1, \"condition\": \"\"}], \"label\": \"重卷\"}, {\"next\": [{\"index\": 2}], \"label\": \"轧机\"}, {\"next\": [{\"index\": 3}], \"label\": \"退火\"}, {\"next\": [], \"label\": \"精整拉矫\"}]";
        Map workflowContext = WorkflowUtil.getWorkflowContext(jsonTextWorkflow, 0);
        System.out.println(workflowContext);
    }

    public static Map getWorkflowContext(String jsonTextWorkflow, Integer index) {
        JSONArray jsonArray = JSONUtil.parseArray(jsonTextWorkflow);
        HashMap[] workflowContexts = (HashMap[])jsonArray.toArray(HashMap.class);
        return workflowContexts[index];
    }
}
