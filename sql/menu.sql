-- 主菜单
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `remark`)
VALUES (5, '服务中心', 0, 0, 'service', NULL, '', '', 1, 0, 'M', '0', '0', '', 'tree', 'admin', '代理管理目录');

-- 菜单
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, remark)
VALUES (2000, '备份管理', 5, 1, 'backup', 'service/BackupMgr/index', NULL, '', 1, 0, 'C', '0', '0', 'service:backup:list', 'tree', 'admin', '备份管理菜单');

-- 按钮
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`)
VALUES (2001, '备份查询', 2000, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'service:backup:query', '#', 'admin');
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`)
VALUES (2002, '备份创建', 2000, 2, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'service:backup:add', '#', 'admin');
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`)
VALUES (2003, '备份修改', 2000, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'service:backup:edit', '#', 'admin');
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`)
VALUES (2004, '备份删除', 2000, 4, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'service:backup:remove', '#', 'admin');
INSERT INTO `bakss`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`)
VALUES (2005, '备份管理器申请', 2000, 5, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'service:backup:apply', '#', 'admin');