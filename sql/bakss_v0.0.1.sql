-- 备份配置
create table bakss_backup (
    id int primary auto_increment,
    backupSoftware varchar(50),
    softwareVersion varchar(10),
    clientName varchar(50),
    backupContent varchar(50),
    backupIP varchar(50),
    appName varchar(50),
    platform varchar(50),
    owner varchar(100),
    deleted int default 0,
    create_time datetime default now(),
    create_by varchar(100),
    update_time datetime
);

-- 控制被授权人员的有效期
create table bakss_backup_validate (
    id int primary key auto_increment,
    backup_id int,
    username varchar(100),
    exp_type int,
    start_date date,
    end_date date,
    deleted int default 0,
    create_time datetime default now(),
    create_by varchar(100),
    update_time datetime
)

-- 备份任务表
create table bakss_backup_task (

);

create table bakss_app(
    id varchar(50) primary key,
    app_type int,
    app_user varchar(100),
    app_time datetime,
    backup_id varchar(200),
    backup_time datetime,
    backup_status int,
    flow_id int,
    remark varchar(1000),
    status int default 1,
    deleted int default 0,
    create_time datetime default now(),
    create_by varchar(100),
    update_time datetime
);

create table bakss_app_flow (
    id int primary key auto_increment,
    flow_step varchar(20) comment 'assign,leader,owner,dba,dbaLeader,admin',
    flow_order int,
    app_id varchar(50),
    review_user varchar(50) comment '当前环节最后审批的人',
    review_status int default 0,
    remark varchar(500),
    deleted int default 0,
    create_time datetime default now(),
    create_by varchar(100),
    update_time datetime
)

-- create table bakss_apply_backup(
--     id int primary key auto_increment,
--     app_id int,
--     backup_id int,
--     deleted int default 0,
--     create_time datetime default now(),
--     create_by varchar(100),
--     update_time datetime
-- )

-- 备份目录：修改目录
create table bakss_apply_directory(
    id int primary key auto_increment,
    app_id varchar(50),
    old_dir varchar(500),
    new_dir varchar(500)
    deleted int default 0,
    create_time datetime default now(),
    create_by varchar(100),
    update_time datetime
);

-- 备份策略：删除，禁用，启用策略
create table bakss_apply_strategy(
    id int primary key auto_increment,
    app_id varchar(50),
    strategy_id int,
    type int comment '1启用 2禁用 3删除',
    deleted int default 0,
    create_time datetime default now(),
    create_by varchar(100),
    update_time datetime
)

-- 创建备份计划
-- create table bakss_apply_backup(
--    id int primary key auto_increment,
--    app_id int,
--    deleted int default 0,
--    create_time datetime default now(),
--    create_by varchar(100),
--    update_time datetime
-- )

-- 创建恢复
create table bakss_apply_restore(
    id int primary key auto_increment,
    app_id varchar(50),
    restore_id int,
    deleted int default 0,
    create_time datetime default now(),
    create_by varchar(100),
    update_time datetime
);

-- -- 备份执行
-- create table bakss_apply_backup_once(
--     id int primary key auto_increment,
--     app_id int,
--     backup_time datetime,
--     deleted int default 0,
--     create_time datetime default now(),
--     create_by varchar(100),
--     update_time datetime
-- )

-- 备份管理权, 可以同时给多个用户授权
create table bakss_apply_backup_permis(
      id int primary key auto_increment,
      app_id varchar(50),
      backup_id int,
      grant_user varchar(100),
      expiration int,
      start_time datetime,
      end_time datetime,
      deleted int default 0,
      create_time datetime default now(),
      create_by varchar(100),
      update_time datetime
);

-- 修改owner与修改管理员都用同一个表
create table bakss_apply_backup_owner(
      id int primary key auto_increment,
      app_id varchar(50),
      backup_id int,
      old_user varchar(100),
      new_user varchar(100),
      deleted int default 0,
      create_time datetime default now(),
      create_by varchar(100),
      update_time datetime
);

--
create table bakss_master_config (
    id int primary key auto_increment,
    master_ip varchar(30),
    backup_software int,
    db_ip varchar(30),
    db_port int,
    db_name varchar(100),
    db_user varchar(100),
    db_password varchar(100),
    api_protocol int,
    api_ip varchar(30),
    api_port int,
    api_user varchar(100),
    api_host varchar(100),
    api_host_type varchar(20),
    os_platform varchar(20),
    os_access varchar(10),
    os_user varchar(100),
    os_password varchar(100),
    os_access_port int,
    os_ip varchar(30),
    os_host varchar(30),
    deleted int default 0,
    create_time datetime default now(),
    update_time datetime
)

create table bakss_ushare_store_config (
    id int primary key auto_increment,
    master varchar(100),
    store_name varchar(100),
    client_threshold int,
    capture_threshold varchar(10),
    support varchar(30),
    status int default 0,
    deleted int default 0,
    create_time datetime default now(),
    update_time datetime
)

create table bakss_network_store_config (
    id int primary key auto_increment,
    master varchar(100),
    store_name varchar(100),
    support varchar(30),
    status int default 0,
    deleted int default 0,
    create_time datetime default now(),
    update_time datetime
)

create table bakss_app_step(
    id int primary key auto_increment,
    app_type int,
    app_steps varchar(50) comment 'assign,leader,owner,dba,dbaLeader,admin',
    is_db int default 0,
    deleted int default 0,
    create_time datetime default now(),
    update_time datetime
)

    insert into bakss_app_step(app_type, app_steps, is_db) values(0, 'dba,admin', 1); -- 申请备份管理权
insert into bakss_app_step(app_type, app_steps, is_db) values(1, 'assign,dba,admin', 1); -- 授权备份管理权
insert into bakss_app_step(app_type, app_steps, is_db) values(2, 'dbaLeader,admin', 1); -- 恢复创建
insert into bakss_app_step(app_type, app_steps, is_db) values(3, 'dba,admin', 1); -- 备份创建
insert into bakss_app_step(app_type, app_steps, is_db) values(4, 'dba,admin', 1); -- 立即备份
insert into bakss_app_step(app_type, app_steps, is_db) values(5, 'dba,admin', 1); -- 定时备份
insert into bakss_app_step(app_type, app_steps, is_db) values(6, 'dba,admin', 1); -- 修改备份目录
insert into bakss_app_step(app_type, app_steps, is_db) values(7, 'dba,admin', 1); -- 启用备份
insert into bakss_app_step(app_type, app_steps, is_db) values(8, 'dba,admin', 1); -- 策略禁用
insert into bakss_app_step(app_type, app_steps, is_db) values(9, 'dba,admin', 1); -- 策略删除
insert into bakss_app_step(app_type, app_steps, is_db) values(10, 'owner,dba,admin', 1); -- 修改owner

insert into bakss_app_step(app_type, app_steps) values(0, 'admin'); -- 申请备份管理权
insert into bakss_app_step(app_type, app_steps) values(1, 'assign,admin'); -- 授权备份管理权
insert into bakss_app_step(app_type, app_steps) values(2, 'leader,admin'); -- 恢复创建
insert into bakss_app_step(app_type, app_steps) values(3, 'admin'); -- 备份创建
insert into bakss_app_step(app_type, app_steps) values(4, 'admin'); -- 立即备份
insert into bakss_app_step(app_type, app_steps) values(5, 'admin'); -- 定时备份
insert into bakss_app_step(app_type, app_steps) values(6, 'admin'); -- 修改备份目录
insert into bakss_app_step(app_type, app_steps) values(7, 'admin'); -- 启用备份
insert into bakss_app_step(app_type, app_steps) values(8, 'admin'); -- 策略禁用
insert into bakss_app_step(app_type, app_steps) values(9, 'admin'); -- 策略删除
insert into bakss_app_step(app_type, app_steps) values(10, 'owner,admin'); -- 修改owner
