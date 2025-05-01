package com.bakss.veeam.domain.backup;

import lombok.Data;

@Data
public class Application {
     private Boolean isOracle;
     private Boolean isPostgreSQL;
     private Boolean isSQL;
     private String name;
}
