package com.bakss.veeam.domain.session;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

@Data
public class BackupSessionDetail {
    private String id;
    private String jobName;
    private String name;
    private String description;
    private String createTime;
    private String endTime;
    private Integer jobType;
    private Integer result;
    private Integer status;
    private JSONObject backupStatus;
    private JSONObject info;
    private JSONObject progress;
    private JSONArray logs;
    private JSONArray tasks;
}
