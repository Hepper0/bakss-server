package com.bakss.veeam.domain.backup;

import com.alibaba.fastjson2.JSONArray;
import lombok.Data;

@Data
public class VMPointDetail {
    private String creationTime;
    private String esxName;
    private String id;
    private JSONArray nics;
    private Long size;
    private String vmName;
}
