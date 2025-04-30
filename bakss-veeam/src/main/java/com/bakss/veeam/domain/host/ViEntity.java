package com.bakss.veeam.domain.host;

import lombok.Data;

@Data
public class ViEntity {
        private String type;
        private String id;
        private String name;
        private String path;
        private Long usedSize;
}
