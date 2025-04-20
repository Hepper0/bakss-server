package com.bakss.server.domain.apply;

import lombok.Data;

@Data
public class ApplyChangeUser extends ApplyBase {
    String backupIds;
    String oldUser;
    String newUser;
}
