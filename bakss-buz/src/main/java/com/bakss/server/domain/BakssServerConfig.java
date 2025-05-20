package com.bakss.server.domain;

import com.bakss.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * VeeamServer对象 bakss_server_config
 *
 * @author author
 * @date 2025-05-15
 */
@Data
public class BakssServerConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    private String hostname;

    private String ip;

    private Long port;

    private Boolean status;

    private Long deleted;

}
