-- 主菜单
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `remark`)
VALUES (5, '服务中心', 0, 0, 'service', NULL, '', '', 1, 0, 'M', '0', '0', '', 'tree', 'admin', '代理管理目录');
-- 菜单
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, remark)
VALUES (201, '备份管理', 5, 1, 'backup', 'service/BackupMgr/index', NULL, '', 1, 0, 'C', '0', '0', 'service:backup:list', 'tree', 'admin', '备份管理菜单');

-- 按钮
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`)
VALUES
(2011, '备份查询', 201, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'service:backup:query', '#', 'admin'),
(2012, '备份修改', 201, 2, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'service:backup:edit', '#', 'admin'),
(2013, '备份删除', 201, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'service:backup:remove', '#', 'admin');
(2014, '修改备份目录', 201, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'service:backup:dire', '#', 'admin');
(2015, '启用策略', 201, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'service:backup:strategy:enable', '#', 'admin');
(2016, '禁用策略', 201, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'service:backup:strategy:disable', '#', 'admin');
(2017, '删除策略', 201, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'service:backup:strategy:remove', '#', 'admin');
(2018, '临时备份', 201, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'service:backup:once', '#', 'admin');


-- 菜单
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, remark)
VALUES (202, '创建备份', 5, 2, 'create', 'service/CreateBackup/index', NULL, '', 1, 0, 'C', '0', '0', 'service:backup:apply', 'tree', 'admin', '申请备份管理权菜单');
-- 按钮
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`)
VALUES (2021, '备份创建', 202, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'service:backup:create', '#', 'admin');

-- 菜单
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, remark)
VALUES (203, '创建恢复', 5, 3, 'restore', 'service/RestoreBackup/index', NULL, '', 1, 0, 'C', '0', '0', 'service:backup:apply', 'tree', 'admin', '申请备份管理权菜单');
-- 按钮
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`)
VALUES (2031, '备份恢复', 203, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'service:backup:restore', '#', 'admin');

-- 菜单
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, remark)
VALUES (204, '申请备份管理权', 5, 4, 'apply', 'service/ApplyPermis/index', NULL, '', 1, 0, 'C', '0', '0', 'service:backup:apply', 'tree', 'admin', '申请备份管理权菜单');
-- 按钮
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`)
VALUES (2041, '备份管理器申请', 204, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'service:backup:apply', '#', 'admin');

INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `remark`)
VALUES (205, '更多操作', 5, 6, 'more', 'service/BackupMgr/more', NULL, '', 1, 0, 'C', '1', '0', 'service:backup:more', '', 'admin', '备份管理更多操作');

INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `remark`)
VALUES (206, '授权', 5, 7, 'grant', 'service/BackupMgr/grant', NULL, '', 1, 0, 'C', '1', '0', 'service:backup:grant', '', 'admin', '备份管理授权操作');

-- 菜单
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, remark)
VALUES (207, '申请备份管理权', 5, 8, 'applyDetail', 'service/ApplyPermis/apply', NULL, '', 1, 0, 'C', '1', '0', '', '', 'admin', '申请备份管理权明细菜单');

-- 主菜单
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `remark`)
VALUES (6, '配置管理', 0, 0, 'config', NULL, '', '', 1, 0, 'M', '0', '0', '', 'tree', 'admin', '配置管理菜单');
-- 菜单
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, remark)
VALUES
(301, 'Master管理', 6, 1, 'master', 'service/BackupMgr/index', NULL, '', 1, 0, 'C', '0', '0', 'config:master:list', 'tree', 'admin', 'Master管理菜单'),
(302, '存储服务器配置', 6, 2, 'store', 'service/BackupMgr/index', NULL, '', 1, 0, 'C', '0', '0', 'config:store:list', 'tree', 'admin', '存储服务器配置菜单'),
(303, '备份软件映射管理', 6, 3, 'mapping', 'service/BackupMgr/index', NULL, '', 1, 0, 'C', '0', '0', 'config:mapping:list', 'tree', 'admin', '备份软件映射菜单'),
(304, '备份资源配置', 6, 4, 'resource', 'service/BackupMgr/index', NULL, '', 1, 0, 'C', '0', '0', 'config:resource:list', 'tree', 'admin', '备份资源配置菜单');

-- 主菜单
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `remark`)
VALUES (7, '统计分析', 0, 0, 'statistics', NULL , '', '', 1, 0, 'M', '0', '0', '', 'tree', 'admin', '统计分析菜单');
-- 菜单
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, remark)
VALUES
(401, '报表统计', 7, 1, 'report', 'statistic/report/index', NULL, '', 1, 0, 'C', '0', '0', 'statistic:report:query', 'tree', 'admin', '报表统计菜单'),
(402, '审计日志', 7, 2, 'log', 'statistic/log/index', NULL, '', 1, 0, 'C', '0', '0', 'statistic:log:query', 'tree', 'admin', '审计日志菜单');


-- 主菜单
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `remark`)
VALUES (8, '申请查看', 0, 0, 'review/index', 'review/index', '', '', 1, 0, 'C', '0', '0', 'review:query', 'message', 'admin', '申请查看菜单');
-- 菜单
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, remark)
VALUES (601, '详情', 8, 1, '/review/detail', 'review/detail', NULL, '', 1, 0, 'C', '1', '0', 'review:detail', '', 'admin', '申请详情页面');

-- 主菜单
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `remark`)
VALUES (9, '我的任务', 0, 0, 'task', 'task/index', '', '', 1, 0, 'C', '0', '0', 'task:list', 'document', 'admin', '我的任务菜单');
-- 菜单
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, remark)
VALUES (701, '详情', 8, 1, '/task/detail', 'task/detail', NULL, '', 1, 0, 'C', '1', '0', 'task:detail', '', 'admin', '任务详情页面');


