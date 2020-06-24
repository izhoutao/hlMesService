package com.haili.framework.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2018/2/6.
 */

public class WorkflowUtil {


    public static void main(String[] args) {

        String jsonTextWorkflow = "[{\"next\": [{\"index\": 1, \"condition\": \"\"}], \"label\": \"重卷\"}, {\"next\": [{\"index\": 2}], \"label\": \"轧机\"}, {\"next\": [{\"index\": 3}], \"label\": \"退火\"}, {\"next\": [], \"label\": \"精整拉矫\"}]";
        jsonTextWorkflow = "[{\"label\": \"重卷\",\"next\": [{\"index\": 1,\"condition\": \"RETURN\"},{\"index\": 2,\"condition\": \"FAIL\"}]},{\"label\": \"轧机\",\"next\": [{\"index\": 3,\"condition\": \"RETURN\"}]},{\"label\": \"退火炉\",\"next\": [{\"index\": 0,\"condition\": \"RETURN\"}]},{\"label\": \"精整拉矫\",\"next\": []}]";
        Integer index = 0;
        String checkResult = "RETURN";
        //getNextWorkflowContext
        Map workflowContext = getNextWorkflowContext(jsonTextWorkflow, index, checkResult);
//        System.out.println(workflowContext);

    }

    public static Map getNextWorkflowContext(String jsonTextWorkflow, Integer index, String checkResult) {
        Map workflowContext = WorkflowUtil.getWorkflowContext(jsonTextWorkflow, index);
        //String label = (String) workflowContext.get("label");
        Map[] nexts = (Map[]) ((JSONArray) workflowContext.get("next")).toArray(HashMap.class);
        Map nextWorkflowContext = null;
        for (int i = 0; i < nexts.length; i++) {
            String condition = (String) nexts[i].get("condition");
            if (StringUtils.isEmpty(condition) || checkResult.equals(condition)) {
                Integer nextIndex = (Integer) nexts[i].get("index");
                nextWorkflowContext = WorkflowUtil.getWorkflowContext(jsonTextWorkflow, nextIndex);
                break;
            }
        }
        return nextWorkflowContext;
    }

    public static Map getWorkflowContext(String jsonTextWorkflow, Integer index) {
        JSONArray jsonArray = JSONUtil.parseArray(jsonTextWorkflow);
        HashMap[] workflowContexts = (HashMap[]) jsonArray.toArray(HashMap.class);
        return workflowContexts[index];
    }

    public static Integer getWorkflowContextIndex(String jsonTextWorkflow, String label) {
        JSONArray jsonArray = JSONUtil.parseArray(jsonTextWorkflow);
        HashMap[] workflowContexts = (HashMap[]) jsonArray.toArray(HashMap.class);
        for (int i = 0; i < workflowContexts.length; i++) {
            String label1 = (String) workflowContexts[i].get("label");
            if (label1.equals(label)) {
                return i;
            }
        }
        return -1;
    }


    public static Map getNextWorkflowContext(String jsonTextWorkflow, String label, String checkResult) {
        Map workflowContext = WorkflowUtil.getWorkflowContext(jsonTextWorkflow, label);
        //String label = (String) workflowContext.get("label");
        Map[] nexts = (Map[]) ((JSONArray) workflowContext.get("next")).toArray(HashMap.class);
        Map nextWorkflowContext = null;
        for (int i = 0; i < nexts.length; i++) {
            String condition = (String) nexts[i].get("condition");
            if (StringUtils.isEmpty(condition) || checkResult.equals(condition)) {
                Integer nextIndex = (Integer) nexts[i].get("index");
                nextWorkflowContext = WorkflowUtil.getWorkflowContext(jsonTextWorkflow, nextIndex);
                break;
            }
        }
        return nextWorkflowContext;
    }

    public static Map getWorkflowContext(String jsonTextWorkflow, String label) {
        JSONArray jsonArray = JSONUtil.parseArray(jsonTextWorkflow);
        HashMap[] workflowContexts = (HashMap[]) jsonArray.toArray(HashMap.class);
        for (int i = 0; i < workflowContexts.length; i++) {
            String label1 = (String) workflowContexts[i].get("label");
            if (label1.equals(label)) {
                return workflowContexts[i];
            }
        }
        return null;
    }



}
