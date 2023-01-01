-- 接口信息
create table if not exists api.`api_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `name` varchar(256) not null comment '接口名称',
    `userId` bigInt not null comment '创建人',
    `url` varchar(512) not null comment '接口地址',
    `method` varchar(256) not null comment '请求类型',
    `description` varchar(256) null comment '描述',
    `status` int default 0 not null comment '接口状态（0 - 关闭 1 - 开启）',
    `requestHeader` text null comment '请求头',
    `responseHeader` text null comment '响应头',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDeleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息';

insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('叶耀杰', '2', 'www.luciano-miller.co', '曾擎宇', '段振家', 0, '许文', '雷志泽');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('郑峻熙', '665', 'www.roland-lehner.com', '马浩宇', '袁旭尧', 0, '曾健柏', '杜天翊');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('韩弘文', '6792', 'www.gala-durgan.io', '黎志泽', '李鹏', 0, '杜志泽', '邱煜祺');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('董子轩', '1', 'www.conchita-kirlin.co', '潘峻熙', '龙伟祺', 0, '朱煜祺', '周钰轩');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('程思远', '213', 'www.garry-keebler.io', '阎建辉', '贾锦程', 0, '叶天磊', '熊懿轩');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('顾俊驰', '5126872', 'www.darrell-lind.co', '尹航', '谢凯瑞', 0, '范博超', '龙炎彬');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('袁思远', '72601462', 'www.chong-howe.org', '程文', '任晟睿', 0, '李建辉', '宋烨伟');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('曾钰轩', '643237659', 'www.joane-schaden.co', '金展鹏', '邵瑾瑜', 0, '万耀杰', '段苑博');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('钟黎昕', '6184715378', 'www.ivory-cole.io', '曹正豪', '徐聪健', 0, '雷博文', '秦博超');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('潘天宇', '1', 'www.alexandra-wehner.info', '严浩轩', '苏荣轩', 0, '王浩然', '郭明');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('陆文', '5218', 'www.austin-bartoletti.biz', '程峻熙', '余泽洋', 0, '钱弘文', '马彬');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('蒋烨霖', '4', 'www.keven-altenwerth.name', '洪雪松', '梁思聪', 0, '冯鹏', '段皓轩');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('余越泽', '11', 'www.eliseo-aufderhar.biz', '杨智辉', '董果', 0, '萧靖琪', '段晓博');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('雷志泽', '759', 'www.lawana-hills.info', '龙智辉', '陆志泽', 0, '陆鹤轩', '蔡子涵');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('谭绍齐', '254436761', 'www.sol-kuphal.io', '贾伟泽', '蔡金鑫', 0, '程鹏', '方子默');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('武晓博', '71', 'www.lakisha-harris.com', '彭思聪', '袁鸿涛', 0, '史绍齐', '白潇然');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('唐天翊', '183280', 'www.harris-vonrueden.name', '袁智辉', '顾睿渊', 0, '任晟睿', '段驰');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('范鹭洋', '64057365', 'www.virgil-flatley.io', '徐晟睿', '孔修洁', 0, '邱鸿涛', '龙熠彤');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('朱瑞霖', '313040787', 'www.richie-borer.io', '程熠彤', '徐远航', 0, '秦睿渊', '江弘文');
insert into api.`api_info` (`name`, `userId`, `url`, `method`, `description`, `status`, `requestHeader`, `responseHeader`) values ('刘越彬', '72994998', 'www.hyman-sipes.name', '马晟睿', '赖琪', 0, '孟明', '梁鑫鹏');