SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ibzauthlog
-- ----------------------------
DROP TABLE IF EXISTS `ibzauthlog`;
CREATE TABLE `ibzauthlog` (
  `LOGID` varchar(100) NOT NULL COMMENT '标识',
  `USERNAME` varchar(100) DEFAULT NULL COMMENT '用户全局名',
  `PERSONNAME` varchar(100) DEFAULT NULL COMMENT '用户名称',
  `DOMAIN` varchar(100) DEFAULT NULL COMMENT '域',
  `AUTHTIME` datetime DEFAULT '0000-00-00 00:00:00',
  `IPADDR` varchar(100) DEFAULT NULL COMMENT 'IP地址',
  `MACADDR` varchar(100) DEFAULT NULL COMMENT 'MAC地址',
  `USERAGENT` varchar(100) DEFAULT NULL COMMENT '客户端',
  `AUTHCODE` varchar(15) DEFAULT NULL COMMENT '认证结果',
  PRIMARY KEY (`LOGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='认证日志';

-- ----------------------------
-- Table structure for ibzcfg
-- ----------------------------
DROP TABLE IF EXISTS `ibzcfg`;
CREATE TABLE `ibzcfg` (
  `CFGID` varchar(100) NOT NULL,
  `SYSTEMID` varchar(100) DEFAULT NULL COMMENT '系统标识',
  `CFGTYPE` varchar(100) DEFAULT NULL COMMENT '配置类型',
  `TARGETTYPE` varchar(100) DEFAULT NULL COMMENT '引用对象',
  `USERID` varchar(100) DEFAULT NULL COMMENT '用户标识',
  `CFG` mediumtext DEFAULT NULL COMMENT '配置',
  `UPDATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`CFGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置';

-- ----------------------------
-- Table structure for ibzdataaudit
-- ----------------------------
DROP TABLE IF EXISTS `ibzdataaudit`;
CREATE TABLE `ibzdataaudit` (
  `DATAAUDITID` varchar(100) NOT NULL,
  `OPPERSONID` varchar(100) DEFAULT NULL COMMENT '操作人标识',
  `OPPERSONNAME` varchar(100) DEFAULT NULL COMMENT '操作人名称',
  `AUDITTYPE` varchar(60) DEFAULT NULL COMMENT '审计行为类型',
  `OPTIME` date DEFAULT '0000-00-00',
  `IPADDRESS` varchar(100) DEFAULT NULL COMMENT '访问地址',
  `AUDITOBJECTDATA` varchar(100) DEFAULT NULL COMMENT '审计对象（表数据）',
  `AUDITOBJECT` varchar(100) DEFAULT NULL COMMENT '审计对象（表）',
  `AUDITINFO` longtext DEFAULT NULL COMMENT '审计明细',
  `ISDATACHANGED` int(11) DEFAULT NULL COMMENT '审计数据是否发生变化',
  PRIMARY KEY (`DATAAUDITID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据审计';

-- ----------------------------
-- Table structure for ibzdept
-- ----------------------------
DROP TABLE IF EXISTS `ibzdept`;
CREATE TABLE `ibzdept` (
  `DEPTID` varchar(100) NOT NULL COMMENT '部门标识',
  `DEPTCODE` varchar(100) DEFAULT NULL COMMENT '部门代码',
  `DEPTNAME` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `ORGID` varchar(100) DEFAULT NULL COMMENT '单位',
  `PDEPTID` varchar(100) DEFAULT NULL COMMENT '上级部门',
  `SHORTNAME` varchar(100) DEFAULT NULL COMMENT '部门简称',
  `DEPTLEVEL` int(11) DEFAULT NULL COMMENT '部门级别',
  `DOMAINS` varchar(100) DEFAULT NULL COMMENT '区属',
  `SHOWORDER` int(11) DEFAULT NULL COMMENT '排序',
  `BCODE` varchar(100) DEFAULT NULL COMMENT '业务编码',
  `LEADERID` varchar(100) DEFAULT NULL COMMENT '分管领导标识',
  `LEADERNAME` varchar(100) DEFAULT NULL COMMENT '分管领导',
  `ENABLE` int(11) DEFAULT NULL COMMENT '逻辑有效',
  `CREATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `UPDATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`DEPTID`),
  KEY `F223F0F447DB8D2FFC` (`ORGID`),
  KEY `F80B5DF0BBDD97B3A4` (`PDEPTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Table structure for ibzdeptmember
-- ----------------------------
DROP TABLE IF EXISTS `ibzdeptmember`;
CREATE TABLE `ibzdeptmember` (
  `MEMBERID` varchar(100) NOT NULL COMMENT '标识',
  `DEPTID` varchar(100) DEFAULT NULL COMMENT '部门标识',
  `USERID` varchar(100) DEFAULT NULL COMMENT '用户标识',
  `POSTID` varchar(100) DEFAULT NULL COMMENT '岗位标识',
  `DOMAINS` varchar(100) DEFAULT NULL COMMENT '区属',
  PRIMARY KEY (`MEMBERID`),
  KEY `F9BCDD858DBCC07B50` (`DEPTID`),
  KEY `F7FA2FDE871DCB4A40` (`POSTID`),
  KEY `F9D98C90BBC4E2653C` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门成员';

-- ----------------------------
-- Table structure for ibzemp
-- ----------------------------
DROP TABLE IF EXISTS `ibzemp`;
CREATE TABLE `ibzemp` (
  `USERID` varchar(100) NOT NULL COMMENT '用户标识',
  `USERNAME` varchar(100) DEFAULT NULL COMMENT '用户全局名',
  `PERSONNAME` varchar(100) DEFAULT NULL COMMENT '姓名',
  `USERCODE` varchar(100) DEFAULT NULL COMMENT '用户工号',
  `LOGINNAME` varchar(100) DEFAULT NULL COMMENT '登录名',
  `PASSWORD` varchar(100) DEFAULT NULL COMMENT '密码',
  `DOMAINS` varchar(100) DEFAULT NULL COMMENT '区属',
  `MDEPTID` varchar(100) DEFAULT NULL COMMENT '主部门',
  `MDEPTCODE` varchar(100) DEFAULT NULL COMMENT '主部门代码',
  `MDEPTNAME` varchar(100) DEFAULT NULL COMMENT '主部门名称',
  `BCODE` varchar(100) DEFAULT NULL COMMENT '业务编码',
  `POSTID` varchar(100) DEFAULT NULL COMMENT '岗位标识',
  `POSTCODE` varchar(100) DEFAULT NULL COMMENT '岗位代码',
  `POSTNAME` varchar(100) DEFAULT NULL COMMENT '岗位名称',
  `ORGID` varchar(100) DEFAULT NULL COMMENT '单位',
  `ORGCODE` varchar(100) DEFAULT NULL COMMENT '单位代码',
  `ORGNAME` varchar(100) DEFAULT NULL COMMENT '单位名称',
  `NICKNAME` varchar(100) DEFAULT NULL COMMENT '昵称别名',
  `SEX` varchar(20) DEFAULT NULL COMMENT '性别',
  `CERTCODE` varchar(100) DEFAULT NULL COMMENT '证件号码',
  `PHONE` varchar(100) DEFAULT NULL COMMENT '联系方式',
  `BIRTHDAY` datetime DEFAULT '0000-00-00 00:00:00',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '邮件',
  `AVATAR` varchar(100) DEFAULT NULL COMMENT '社交账号',
  `ADDR` varchar(255) DEFAULT NULL COMMENT '地址',
  `USERICON` varchar(255) DEFAULT NULL COMMENT '照片',
  `IPADDR` varchar(100) DEFAULT NULL COMMENT 'ip地址',
  `THEME` varchar(100) DEFAULT NULL COMMENT '样式',
  `LANG` varchar(100) DEFAULT NULL COMMENT '语言',
  `FONTSIZE` varchar(10) DEFAULT NULL COMMENT '字号',
  `MEMO` varchar(255) DEFAULT NULL COMMENT '备注',
  `RESERVER` varchar(255) DEFAULT NULL COMMENT '保留',
  `SHOWORDER` int(11) DEFAULT NULL COMMENT '排序',
  `ENABLE` int(11) DEFAULT NULL COMMENT '逻辑有效',
  `CREATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `UPDATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`USERID`),
  KEY `FDC89D199944690EAA` (`MDEPTID`),
  KEY `F8B9F71B72F82C0C2E` (`ORGID`),
  KEY `F08B347CA05ECDAC50` (`POSTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员';

-- ----------------------------
-- Table structure for ibzfile
-- ----------------------------
DROP TABLE IF EXISTS `ibzfile`;
CREATE TABLE `ibzfile` (
  `FILEID` varchar(100) NOT NULL,
  `FILENAME` varchar(200) DEFAULT NULL,
  `FILEPATH` varchar(500) DEFAULT NULL,
  `FOLDER` varchar(20) DEFAULT NULL,
  `CREATEMAN` varchar(60) DEFAULT NULL,
  `CREATEDATE` date DEFAULT '0000-00-00',
  `UPDATEMAN` varchar(60) DEFAULT NULL,
  `UPDATEDATE` date DEFAULT '0000-00-00',
  `FILEEXT` varchar(10) DEFAULT NULL,
  `FILESIZE` int(11) DEFAULT NULL,
  `DIGESTCODE` varchar(64) DEFAULT NULL,
  `OWNERTYPE` varchar(100) DEFAULT NULL,
  `OWNERID` varchar(100) DEFAULT NULL,
  `MEMO` varchar(500) DEFAULT NULL,
  `RESERVER` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`FILEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件';

-- ----------------------------
-- Table structure for ibzmsgbody
-- ----------------------------
DROP TABLE IF EXISTS `ibzmsgbody`;
CREATE TABLE `ibzmsgbody` (
  `TEMPLATEID` varchar(100) DEFAULT NULL COMMENT '消息模板标识',
  `MSGTYPE` int(11) DEFAULT NULL COMMENT '消息类型',
  `TOUSERS` varchar(1000) DEFAULT NULL COMMENT '目标用户',
  `TEMPLPARAMS` mediumtext DEFAULT NULL COMMENT '消息模板参数',
  `ISSEND` int(11) DEFAULT NULL COMMENT '是否发送',
  `ISERROR` int(11) DEFAULT NULL COMMENT '是否出错',
  `SUBJECT` varchar(100) DEFAULT NULL COMMENT '消息标题',
  `CONTENT` varchar(100) DEFAULT NULL COMMENT '消息内容',
  `MSGLINK` varchar(100) DEFAULT NULL COMMENT '消息链接',
  `USERDATA` varchar(100) DEFAULT NULL COMMENT '用户数据',
  `MSGID` varchar(100) NOT NULL COMMENT '消息标识',
  `MSGNAME` varchar(100) DEFAULT NULL COMMENT '消息名称',
  `ERRORINFO` mediumtext DEFAULT NULL COMMENT '错误信息',
  `USERDATA2` varchar(1000) DEFAULT NULL COMMENT '业务数据',
  `MSGLINK_PC` varchar(100) DEFAULT NULL COMMENT '消息链接(PC)',
  PRIMARY KEY (`MSGID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息';

-- ----------------------------
-- Table structure for ibzmsgtempl
-- ----------------------------
DROP TABLE IF EXISTS `ibzmsgtempl`;
CREATE TABLE `ibzmsgtempl` (
  `TID` varchar(100) NOT NULL COMMENT '模板标识',
  `TEMPLATE_NAME` varchar(100) DEFAULT NULL COMMENT '模板标题',
  `TEMPLATE_TYPE` varchar(100) DEFAULT NULL COMMENT '模板类型',
  `CONTENT` varchar(4000) DEFAULT NULL COMMENT '模板内容',
  `TEMPLATE_ID` varchar(100) DEFAULT NULL COMMENT '模板标识',
  `TEMPLATE_URL` varchar(1000) DEFAULT NULL COMMENT 'URL',
  `ACCESSID` varchar(100) DEFAULT NULL COMMENT '开放平台接入标识',
  `ACCESSNAME` varchar(100) DEFAULT NULL COMMENT '开放平台',
  `OPEN_TYPE` varchar(100) DEFAULT NULL COMMENT '开放平台类型',
  PRIMARY KEY (`TID`),
  KEY `FECE93947F061CF841` (`ACCESSID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息模板';

-- ----------------------------
-- Table structure for ibzopenaccess
-- ----------------------------
DROP TABLE IF EXISTS `ibzopenaccess`;
CREATE TABLE `ibzopenaccess` (
  `ACCESSID` varchar(100) NOT NULL,
  `ACCESSNAME` varchar(100) DEFAULT NULL,
  `OPEN_TYPE` varchar(100) DEFAULT NULL,
  `ACCESS_KEY` varchar(100) DEFAULT NULL,
  `SECRET_KEY` varchar(100) DEFAULT NULL,
  `REGION_ID` varchar(100) DEFAULT NULL,
  `ACCESS_TOKEN` varchar(1000) DEFAULT NULL,
  `EXPIRES_TIME` timestamp NULL DEFAULT NULL,
  `DISABLED` int(11) DEFAULT NULL,
  `REDIRECT_URI` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ACCESSID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ibzorg
-- ----------------------------
DROP TABLE IF EXISTS `ibzorg`;
CREATE TABLE `ibzorg` (
  `ORGID` varchar(100) NOT NULL COMMENT '单位标识',
  `ORGCODE` varchar(100) DEFAULT NULL COMMENT '单位代码',
  `ORGNAME` varchar(100) DEFAULT NULL COMMENT '名称',
  `PORGID` varchar(100) DEFAULT NULL COMMENT '上级单位',
  `SHORTNAME` varchar(100) DEFAULT NULL COMMENT '单位简称',
  `ORGLEVEL` int(11) DEFAULT NULL COMMENT '单位级别',
  `SHOWORDER` int(11) DEFAULT NULL COMMENT '排序',
  `DOMAINS` varchar(100) DEFAULT NULL COMMENT '区属',
  `ENABLE` int(11) DEFAULT NULL COMMENT '逻辑有效',
  `CREATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `UPDATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ORGID`),
  KEY `FA6203C15E7EFB55C0` (`PORGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单位机构';

-- ----------------------------
-- Table structure for ibzpermission
-- ----------------------------
DROP TABLE IF EXISTS `ibzpermission`;
CREATE TABLE `ibzpermission` (
  `SYS_PERMISSIONID` varchar(200) NOT NULL COMMENT '资源标识',
  `SYS_PERMISSIONNAME` varchar(200) DEFAULT NULL COMMENT '资源名称',
  `PERMISSIONTYPE` varchar(60) DEFAULT NULL COMMENT '资源类别',
  `PSSYSTEMID` varchar(100) DEFAULT NULL COMMENT '系统',
  `ENABLE` int(11) DEFAULT NULL COMMENT '逻辑有效',
  `CREATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `UPDATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`SYS_PERMISSIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限/资源';

-- ----------------------------
-- Table structure for ibzpost
-- ----------------------------
DROP TABLE IF EXISTS `ibzpost`;
CREATE TABLE `ibzpost` (
  `POSTID` varchar(100) NOT NULL COMMENT '岗位标识',
  `POSTCODE` varchar(100) DEFAULT NULL COMMENT '岗位编码',
  `POSTNAME` varchar(100) DEFAULT NULL COMMENT '岗位名称',
  `DOMAINS` varchar(100) DEFAULT NULL COMMENT '区属',
  `MEMO` varchar(2000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`POSTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位';

-- ----------------------------
-- Table structure for ibzpssystem
-- ----------------------------
DROP TABLE IF EXISTS `ibzpssystem`;
CREATE TABLE `ibzpssystem` (
  `PSSYSTEMID` varchar(100) NOT NULL COMMENT '系统标识',
  `PSSYSTEMNAME` varchar(100) DEFAULT NULL COMMENT '系统名称',
  `SYSSTRUCTURE` mediumtext DEFAULT NULL COMMENT '结构',
  `APPS` mediumtext DEFAULT NULL COMMENT '应用',
  `MD5CHECK` varchar(100) DEFAULT NULL COMMENT '校验',
  `SHOWORDER` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`PSSYSTEMID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统';

-- ----------------------------
-- Table structure for ibzrole
-- ----------------------------
DROP TABLE IF EXISTS `ibzrole`;
CREATE TABLE `ibzrole` (
  `SYS_ROLEID` varchar(100) NOT NULL COMMENT '角色标识',
  `SYS_ROLENAME` varchar(200) DEFAULT NULL COMMENT '角色名称',
  `MEMO` varchar(100) DEFAULT NULL COMMENT '备注',
  `PROLEID` varchar(100) DEFAULT NULL COMMENT '父角色标识',
  `CREATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `UPDATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`SYS_ROLEID`),
  KEY `FC9A58A7D79B5CB5C3` (`PROLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色';

-- ----------------------------
-- Table structure for ibzrole_permission
-- ----------------------------
DROP TABLE IF EXISTS `ibzrole_permission`;
CREATE TABLE `ibzrole_permission` (
  `SYS_ROLE_PERMISSIONID` varchar(100) NOT NULL COMMENT '角色权限关系表标识',
  `SYS_ROLEID` varchar(100) DEFAULT NULL COMMENT '角色表标识',
  `SYS_PERMISSIONID` varchar(200) DEFAULT NULL COMMENT '权限表标识',
  `CREATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `UPDATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`SYS_ROLE_PERMISSIONID`),
  KEY `FCC4D55F90426B0F15` (`SYS_ROLEID`),
  KEY `FE283EACB26C3C950C` (`SYS_PERMISSIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关系';

-- ----------------------------
-- Table structure for ibzteam
-- ----------------------------
DROP TABLE IF EXISTS `ibzteam`;
CREATE TABLE `ibzteam` (
  `TEAMID` varchar(100) NOT NULL COMMENT '组标识',
  `TEAMNAME` varchar(100) DEFAULT NULL COMMENT '组名称',
  `MEMO` varchar(2000) DEFAULT NULL COMMENT '备注',
  `DOMAINS` varchar(100) DEFAULT NULL COMMENT '区属',
  PRIMARY KEY (`TEAMID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组';

-- ----------------------------
-- Table structure for ibzteammember
-- ----------------------------
DROP TABLE IF EXISTS `ibzteammember`;
CREATE TABLE `ibzteammember` (
  `TEAMMEMBERID` varchar(100) NOT NULL COMMENT '组成员标识',
  `TEAMID` varchar(100) DEFAULT NULL COMMENT '组标识',
  `USERID` varchar(100) DEFAULT NULL COMMENT '用户标识',
  `POSTID` varchar(100) DEFAULT NULL COMMENT '岗位标识',
  `DOMAINS` varchar(100) DEFAULT NULL COMMENT '区属',
  PRIMARY KEY (`TEAMMEMBERID`),
  KEY `F85616C3A567AA2EEA` (`POSTID`),
  KEY `FACE47503B6D6CE4F9` (`TEAMID`),
  KEY `F18491F92BEEBCF773` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组成员';

-- ----------------------------
-- Table structure for ibzuser
-- ----------------------------
DROP TABLE IF EXISTS `ibzuser`;
CREATE TABLE `ibzuser` (
  `USERID` varchar(100) NOT NULL COMMENT '用户标识',
  `USERNAME` varchar(200) DEFAULT NULL COMMENT '用户名称',
  `PERSONNAME` varchar(100) DEFAULT NULL COMMENT '用户姓名',
  `LOGINNAME` varchar(100) DEFAULT NULL COMMENT '登录名',
  `PASSWORD` varchar(100) DEFAULT NULL COMMENT '登录密码',
  `DOMAINS` varchar(100) DEFAULT NULL COMMENT '区属',
  `USERCODE` varchar(100) DEFAULT NULL COMMENT '用户代码',
  `MDEPTID` varchar(100) DEFAULT NULL COMMENT '主部门ID',
  `MDEPTCODE` varchar(100) DEFAULT NULL COMMENT '主部门代码',
  `MDEPTNAME` varchar(200) DEFAULT NULL COMMENT '主部门名称',
  `BCODE` varchar(100) DEFAULT NULL COMMENT '业务编码',
  `ORGID` varchar(100) DEFAULT NULL COMMENT '单位ID',
  `ORGCODE` varchar(100) DEFAULT NULL COMMENT '单位代码',
  `ORGNAME` varchar(200) DEFAULT NULL COMMENT '单位名称',
  `POSTID` varchar(100) DEFAULT NULL COMMENT '岗位ID',
  `POSTCODE` varchar(100) DEFAULT NULL COMMENT '岗位代码',
  `POSTNAME` varchar(200) DEFAULT NULL COMMENT '岗位名称',
  `NICKNAME` varchar(36) DEFAULT NULL COMMENT '昵称',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `AVATAR` varchar(100) DEFAULT NULL COMMENT '通信账号',
  `PHONE` varchar(100) DEFAULT NULL COMMENT '联系电话',
  `USERICON` varchar(100) DEFAULT NULL COMMENT '头像',
  `SEX` varchar(10) DEFAULT NULL COMMENT '性别',
  `BIRTHDAY` datetime DEFAULT '0000-00-00 00:00:00',
  `CERTCODE` varchar(36) DEFAULT NULL COMMENT '证件号码',
  `ADDR` varchar(200) DEFAULT NULL COMMENT '地址',
  `MEMO` varchar(500) DEFAULT NULL COMMENT '备注',
  `THEME` varchar(100) DEFAULT NULL COMMENT '主题',
  `LANG` varchar(100) DEFAULT NULL COMMENT '语言',
  `FONTSIZE` varchar(10) DEFAULT NULL COMMENT '字号',
  `RESERVER` varchar(100) DEFAULT NULL COMMENT '保留字段',
  `SUPERUSER` int(255) DEFAULT NULL,
  PRIMARY KEY (`USERID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Table structure for ibzuserauth
-- ----------------------------
DROP TABLE IF EXISTS `ibzuserauth`;
CREATE TABLE `ibzuserauth` (
  `AUTHID` varchar(100) NOT NULL COMMENT '标识',
  `USERID` varchar(100) DEFAULT NULL COMMENT '用户标识',
  `IDENTITY_TYPE` varchar(100) DEFAULT NULL COMMENT '认证类型',
  `CREDENTIAL` varchar(500) DEFAULT NULL COMMENT '凭据',
  `IDENTIFIER` varchar(200) DEFAULT NULL COMMENT '认证标识',
  PRIMARY KEY (`AUTHID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实体';

-- ----------------------------
-- Table structure for ibzuser_role
-- ----------------------------
DROP TABLE IF EXISTS `ibzuser_role`;
CREATE TABLE `ibzuser_role` (
  `SYS_USER_ROLEID` varchar(100) NOT NULL COMMENT '用户角色关系标识',
  `SYS_ROLEID` varchar(100) DEFAULT NULL COMMENT '角色标识',
  `SYS_USERID` varchar(100) DEFAULT NULL COMMENT '用户标识',
  `CREATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `UPDATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`SYS_USER_ROLEID`),
  KEY `F6472927DB7FA72043` (`SYS_ROLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系';

-- ----------------------------
-- Table structure for t_ibizpro_keyword
-- ----------------------------
DROP TABLE IF EXISTS `t_ibizpro_keyword`;
CREATE TABLE `t_ibizpro_keyword` (
  `IBIZPRO_KEYWORDID` varchar(100) NOT NULL COMMENT '关键词标识',
  `IBIZPRO_KEYWORDNAME` varchar(200) DEFAULT NULL COMMENT '关键词名称',
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `CREATEDATE` datetime DEFAULT NULL COMMENT '建立时间',
  `UPDATEDATE` datetime DEFAULT NULL COMMENT '更新时间',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`IBIZPRO_KEYWORDID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关键词';

-- ----------------------------
-- Table structure for t_ibizpro_message
-- ----------------------------
DROP TABLE IF EXISTS `t_ibizpro_message`;
CREATE TABLE `t_ibizpro_message` (
  `IBIZPRO_MESSAGENAME` varchar(200) DEFAULT NULL COMMENT '消息名称',
  `IBIZPRO_MESSAGEID` varchar(100) NOT NULL COMMENT '消息标识',
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `UPDATEDATE` datetime DEFAULT NULL COMMENT '更新时间',
  `CREATEDATE` datetime DEFAULT NULL COMMENT '建立时间',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  `SUBJECT` varchar(100) DEFAULT NULL COMMENT '标题',
  `CONTENT` mediumtext DEFAULT NULL COMMENT '内容',
  `TYPE` varchar(60) DEFAULT NULL COMMENT '消息类型',
  `ISLINK` int(11) DEFAULT NULL COMMENT '是否是链接消息	',
  `ISSYNC` int(11) DEFAULT NULL COMMENT '是否同步',
  `ISRETRY` int(11) DEFAULT NULL COMMENT '是否重发',
  `RETRYTIMES` int(11) DEFAULT NULL COMMENT '重发次数',
  `RETRYINTERVALTIME` int(11) DEFAULT NULL COMMENT '重发间隔时间',
  `FROM` mediumtext DEFAULT NULL COMMENT '发送方',
  `TO` mediumtext DEFAULT NULL COMMENT '接收方',
  `CC` mediumtext DEFAULT NULL COMMENT '抄送方',
  `BCC` mediumtext DEFAULT NULL COMMENT '密件抄送方',
  `PARAM` mediumtext DEFAULT NULL COMMENT '消息参数',
  `ISREAD` int(11) DEFAULT NULL COMMENT '是否已读',
  `ISDONE` int(11) DEFAULT NULL COMMENT '是否完成',
  PRIMARY KEY (`IBIZPRO_MESSAGEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息';

-- ----------------------------
-- Table structure for t_ibizpro_messageobject
-- ----------------------------
DROP TABLE IF EXISTS `t_ibizpro_messageobject`;
CREATE TABLE `t_ibizpro_messageobject` (
  `IBIZPRO_MESSAGEOBJECTID` varchar(100) NOT NULL COMMENT '消息对象标识',
  `CREATEDATE` datetime DEFAULT NULL COMMENT '建立时间',
  `IBIZPRO_MESSAGEOBJECTNAME` varchar(200) DEFAULT NULL COMMENT '消息对象名称',
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `UPDATEDATE` datetime DEFAULT NULL COMMENT '更新时间',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  `OBJECTTYPE` varchar(60) DEFAULT NULL COMMENT '对象类型',
  `OBJECTID` varchar(100) DEFAULT NULL COMMENT '对象标识',
  PRIMARY KEY (`IBIZPRO_MESSAGEOBJECTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息对象';

-- ----------------------------
-- Table structure for t_ibizpro_messageproxy
-- ----------------------------
DROP TABLE IF EXISTS `t_ibizpro_messageproxy`;
CREATE TABLE `t_ibizpro_messageproxy` (
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `IBIZPRO_MESSAGEPROXYID` varchar(100) NOT NULL COMMENT '消息代理标识',
  `IBIZPRO_MESSAGEPROXYNAME` varchar(200) DEFAULT NULL COMMENT '消息代理名称',
  `UPDATEDATE` datetime DEFAULT NULL COMMENT '更新时间',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  `CREATEDATE` datetime DEFAULT NULL COMMENT '建立时间',
  `PARAM` mediumtext DEFAULT NULL COMMENT '处理参数',
  `LINKSUPPORTTYPE` varchar(60) DEFAULT NULL COMMENT '消息链接形式',
  `LINKURL` varchar(100) DEFAULT NULL COMMENT '消息链接地址',
  `LINKEXMSG` varchar(2000) DEFAULT NULL COMMENT '消息补充信息',
  `PLUGINID` varchar(100) DEFAULT NULL COMMENT '代理标识',
  `HANDLER` varchar(2000) DEFAULT NULL COMMENT '处理对象',
  PRIMARY KEY (`IBIZPRO_MESSAGEPROXYID`),
  KEY `FFC86BCEAFAF246A7F` (`PLUGINID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息代理';

-- ----------------------------
-- Table structure for t_ibizpro_messagesend
-- ----------------------------
DROP TABLE IF EXISTS `t_ibizpro_messagesend`;
CREATE TABLE `t_ibizpro_messagesend` (
  `IBIZPRO_MESSAGESENDID` varchar(100) NOT NULL COMMENT '消息发送标识',
  `CREATEDATE` datetime DEFAULT NULL COMMENT '建立时间',
  `UPDATEDATE` datetime DEFAULT NULL COMMENT '更新时间',
  `IBIZPRO_MESSAGESENDNAME` varchar(200) DEFAULT NULL COMMENT '消息发送名称',
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  `MSGID` varchar(100) DEFAULT NULL COMMENT '消息标识',
  `ISSEND` int(11) DEFAULT NULL COMMENT '是否发送',
  `ISREAD` int(11) DEFAULT NULL COMMENT '是否已读',
  `ISERROR` int(11) DEFAULT NULL COMMENT '是否发生错误',
  `ERRORMSG` mediumtext DEFAULT NULL COMMENT '最新一次发生错误的消息',
  `RETRYCOUNT` int(11) DEFAULT NULL COMMENT '重试次数',
  `SENDPROXYID` varchar(100) DEFAULT NULL COMMENT '发送代理标识',
  `ISDONE` int(11) DEFAULT NULL COMMENT '是否已完成',
  `RECIVER` varchar(100) DEFAULT NULL COMMENT '接收人',
  `SENDER` varchar(100) DEFAULT NULL COMMENT '发送者',
  PRIMARY KEY (`IBIZPRO_MESSAGESENDID`),
  KEY `FD3C16A900930ED1F7` (`MSGID`),
  KEY `FFBCC3A87C220A3380` (`SENDPROXYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息发送';

-- ----------------------------
-- Table structure for t_ibizpro_plugin
-- ----------------------------
DROP TABLE IF EXISTS `t_ibizpro_plugin`;
CREATE TABLE `t_ibizpro_plugin` (
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `CREATEDATE` datetime DEFAULT NULL COMMENT '建立时间',
  `IBIZPRO_PLUGINNAME` varchar(200) DEFAULT NULL COMMENT '系统插件名称',
  `IBIZPRO_PLUGINID` varchar(100) NOT NULL COMMENT '系统插件标识',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  `UPDATEDATE` datetime DEFAULT NULL COMMENT '更新时间',
  `TYPE` varchar(10) DEFAULT NULL COMMENT '类型',
  `VERSION` int(11) DEFAULT NULL COMMENT '版本',
  `DOWNLOADURL` varchar(100) DEFAULT NULL COMMENT '最新版本下载地址',
  `TAG` varchar(2000) DEFAULT NULL COMMENT '标签',
  `KEYWORD` varchar(2000) DEFAULT NULL COMMENT '关键字',
  PRIMARY KEY (`IBIZPRO_PLUGINID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统插件';

-- ----------------------------
-- Table structure for t_ibizpro_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_ibizpro_tag`;
CREATE TABLE `t_ibizpro_tag` (
  `CREATEDATE` datetime DEFAULT NULL COMMENT '建立时间',
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  `IBIZPRO_TAGNAME` varchar(200) DEFAULT NULL COMMENT '标签名称',
  `IBIZPRO_TAGID` varchar(100) NOT NULL COMMENT '标签标识',
  `UPDATEDATE` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`IBIZPRO_TAGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签';

-- ----------------------------
-- Table structure for t_ibizpro_todomessage
-- ----------------------------
DROP TABLE IF EXISTS `t_ibizpro_todomessage`;
CREATE TABLE `t_ibizpro_todomessage` (
  `CREATEDATE` datetime DEFAULT NULL COMMENT '建立时间',
  `UPDATEDATE` datetime DEFAULT NULL COMMENT '更新时间',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `SUBJECT` varchar(100) DEFAULT NULL COMMENT '主题',
  `FROM` varchar(100) DEFAULT NULL COMMENT '发送方',
  `IBIZPRO_MESSAGEID` varchar(100) NOT NULL COMMENT '消息标识',
  `IBIZPRO_MESSAGENAME` varchar(200) DEFAULT NULL COMMENT '消息名称',
  `ISREAD` int(11) DEFAULT NULL COMMENT '是否已读',
  `TYPE` varchar(60) DEFAULT NULL COMMENT '消息类型',
  `CONTENT` varchar(2000) DEFAULT NULL COMMENT '内容',
  `TO` varchar(100) DEFAULT NULL COMMENT '接收方',
  `PARAM` varchar(2000) DEFAULT NULL COMMENT '消息参数',
  `SENDTIME` datetime DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`IBIZPRO_MESSAGEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ToDo消息';

-- ----------------------------
-- Table structure for t_ibzpro_system
-- ----------------------------
DROP TABLE IF EXISTS `t_ibzpro_system`;
CREATE TABLE `t_ibzpro_system` (
  `IBZPRO_SYSTEMID` varchar(100) NOT NULL COMMENT '运行生产系统标识',
  `IBZPRO_SYSTEMNAME` varchar(200) DEFAULT NULL COMMENT '运行生产系统名称',
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  `CREATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `UPDATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`IBZPRO_SYSTEMID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运行生产系统';

-- ----------------------------
-- Table structure for t_ibzpro_systpl
-- ----------------------------
DROP TABLE IF EXISTS `t_ibzpro_systpl`;
CREATE TABLE `t_ibzpro_systpl` (
  `IBZPRO_SYSTPLID` varchar(100) NOT NULL COMMENT '系统模板标识',
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `CREATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `IBZPRO_SYSTPLNAME` varchar(200) DEFAULT NULL COMMENT '系统模板名称',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  `UPDATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `PUBLIC` varchar(1) DEFAULT NULL COMMENT '是否公开',
  `IBIZ_SOURCEOBJECT` varchar(100) DEFAULT NULL COMMENT '来源对象',
  `CONTENT` mediumtext DEFAULT NULL COMMENT '模板内容',
  `TPLTYPE` varchar(60) DEFAULT NULL COMMENT 'IBIZ模板类型',
  `FILE` bigint(20) unsigned DEFAULT NULL COMMENT 'id',
  PRIMARY KEY (`IBZPRO_SYSTPLID`),
  KEY `FFD0FBE185A072627C` (`FILE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统模板';

-- ----------------------------
-- Table structure for t_ibz_daily
-- ----------------------------
DROP TABLE IF EXISTS `t_ibz_daily`;
CREATE TABLE `t_ibz_daily` (
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `IBZ_DAILYID` bigint(100) unsigned NOT NULL AUTO_INCREMENT COMMENT '日报标识',
  `IBZ_DAILYNAME` varchar(200) DEFAULT NULL COMMENT '日报名称',
  `UPDATEDATE` datetime DEFAULT NULL COMMENT '更新时间',
  `CREATEDATE` datetime DEFAULT NULL COMMENT '建立时间',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  `DATE` datetime DEFAULT NULL COMMENT '日期',
  `ACCOUNT` varchar(60) DEFAULT NULL COMMENT '用户',
  `WORKTODAY` mediumtext DEFAULT NULL COMMENT '今日工作',
  `MAILTO` varchar(2000) DEFAULT NULL COMMENT '抄送给',
  `TODAYTASK` varchar(2000) DEFAULT NULL COMMENT '完成任务',
  `ISSUBMIT` varchar(60) DEFAULT NULL COMMENT '是否提交',
  `PLANSTOMORROW` mediumtext DEFAULT NULL COMMENT '明日计划',
  `TOMORROWPLANSTASK` varchar(2000) DEFAULT NULL COMMENT '明日计划任务',
  `REPORTTO` varchar(60) DEFAULT NULL COMMENT '汇报给',
  `COMMENT` mediumtext DEFAULT NULL COMMENT '其他事项',
  `CREATEMANNAME` varchar(60) DEFAULT NULL COMMENT '建立人名称',
  `UPDATEMANNAME` varchar(60) DEFAULT NULL COMMENT '更新人名称',
  `REPORTSTATUS` varchar(60) DEFAULT NULL COMMENT '状态',
  `SUBMITTIME` datetime DEFAULT NULL COMMENT '提交时间',
  PRIMARY KEY (`IBZ_DAILYID`)
) ENGINE=InnoDB AUTO_INCREMENT=313 DEFAULT CHARSET=utf8 COMMENT='日报';

-- ----------------------------
-- Table structure for t_ibz_favorites
-- ----------------------------
DROP TABLE IF EXISTS `t_ibz_favorites`;
CREATE TABLE `t_ibz_favorites` (
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `IBZ_FAVORITESID` varchar(100) NOT NULL COMMENT '收藏标识',
  `CREATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `IBZ_FAVORITESNAME` varchar(200) DEFAULT NULL COMMENT '收藏名称',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  `UPDATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `OBJECTID` int(11) DEFAULT NULL COMMENT '数据对象标识',
  `ACCOUNT` varchar(60) DEFAULT NULL COMMENT '收藏用户',
  `TYPE` varchar(60) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`IBZ_FAVORITESID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏';

-- ----------------------------
-- Table structure for t_ibz_monthly
-- ----------------------------
DROP TABLE IF EXISTS `t_ibz_monthly`;
CREATE TABLE `t_ibz_monthly` (
  `IBZ_MONTHLYID` bigint(100) NOT NULL AUTO_INCREMENT COMMENT '月报标识',
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  `CREATEDATE` datetime DEFAULT NULL COMMENT '建立时间',
  `IBZ_MONTHLYNAME` varchar(200) DEFAULT NULL COMMENT '月报名称',
  `UPDATEDATE` datetime DEFAULT NULL COMMENT '更新时间',
  `DATE` datetime DEFAULT NULL COMMENT '日期',
  `ACCOUNT` varchar(100) DEFAULT NULL COMMENT '用户',
  `WORKTHISMONTH` mediumtext DEFAULT NULL COMMENT '本月工作',
  `PLANSNEXTMONTH` mediumtext DEFAULT NULL COMMENT '下月计划',
  `REPORTTO` varchar(60) DEFAULT NULL COMMENT '汇报',
  `MAILTO` varchar(2000) DEFAULT NULL COMMENT '抄送给',
  `COMMENT` mediumtext DEFAULT NULL COMMENT '其他事项',
  `THISMONTHTASK` varchar(2000) DEFAULT NULL COMMENT '本月完成任务',
  `NEXTMONTHPLANSTASK` varchar(2000) DEFAULT NULL COMMENT '下月计划任务',
  `ISSUBMIT` varchar(100) DEFAULT NULL COMMENT '是否提交',
  `UPDATEMANNAME` varchar(60) DEFAULT NULL COMMENT '更新人名称',
  `REPORTSTATUS` varchar(60) DEFAULT NULL COMMENT '状态',
  `CREATEMANNAME` varchar(60) DEFAULT NULL COMMENT '建立人名称',
  `SUBMITTIME` datetime DEFAULT NULL COMMENT '提交时间',
  PRIMARY KEY (`IBZ_MONTHLYID`)
) ENGINE=InnoDB AUTO_INCREMENT=325 DEFAULT CHARSET=utf8 COMMENT='月报';

-- ----------------------------
-- Table structure for t_ibz_report_role_config
-- ----------------------------
DROP TABLE IF EXISTS `t_ibz_report_role_config`;
CREATE TABLE `t_ibz_report_role_config` (
  `IBZ_REPORT_ROLE_CONFIGNAME` varchar(200) DEFAULT NULL COMMENT '汇报角色配置名称',
  `IBZ_REPORT_ROLE_CONFIGID` varchar(100) NOT NULL COMMENT '汇报角色配置标识',
  `CREATEDATE` datetime DEFAULT NULL COMMENT '建立时间',
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  `UPDATEDATE` datetime DEFAULT NULL COMMENT '更新时间',
  `REPORT_ROLE` varchar(2000) DEFAULT NULL COMMENT '角色',
  `TYPE` varchar(60) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`IBZ_REPORT_ROLE_CONFIGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='汇报角色配置';

-- ----------------------------
-- Table structure for t_ibz_systpl
-- ----------------------------
DROP TABLE IF EXISTS `t_ibz_systpl`;
CREATE TABLE `t_ibz_systpl` (
  `IBZ_SYSTPLID` varchar(100) NOT NULL COMMENT '系统模板标识',
  `CREATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `IBZ_SYSTPLNAME` varchar(200) DEFAULT NULL COMMENT '系统模板名称',
  `UPDATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`IBZ_SYSTPLID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统模板';

-- ----------------------------
-- Table structure for t_ibz_top
-- ----------------------------
DROP TABLE IF EXISTS `t_ibz_top`;
CREATE TABLE `t_ibz_top` (
  `IBZ_TOPID` varchar(100) NOT NULL COMMENT '标识',
  `CREATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  `IBZ_TOPNAME` varchar(200) DEFAULT NULL COMMENT '置顶名称',
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `UPDATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `TYPE` varchar(60) DEFAULT NULL COMMENT '类型',
  `OBJECTID` int(11) DEFAULT NULL COMMENT '数据对象标识',
  `OBJECTORDER` int(11) DEFAULT NULL COMMENT '排序',
  `ACCOUNT` varchar(60) DEFAULT NULL COMMENT '置顶用户',
  PRIMARY KEY (`IBZ_TOPID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='置顶';

-- ----------------------------
-- Table structure for t_ibz_weekly
-- ----------------------------
DROP TABLE IF EXISTS `t_ibz_weekly`;
CREATE TABLE `t_ibz_weekly` (
  `IBZ_WEEKLYNAME` varchar(200) DEFAULT NULL COMMENT '周报名称',
  `IBZ_WEEKLYID` bigint(100) unsigned NOT NULL AUTO_INCREMENT COMMENT '周报标识',
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `CREATEDATE` datetime DEFAULT NULL COMMENT '建立时间',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  `UPDATEDATE` datetime DEFAULT NULL COMMENT '更新时间',
  `ACCOUNT` varchar(60) DEFAULT NULL COMMENT '用户',
  `MAILTO` varchar(2000) DEFAULT NULL COMMENT '抄送给',
  `ISSUBMIT` varchar(60) DEFAULT NULL COMMENT '是否提交',
  `REPORTTO` varchar(60) DEFAULT NULL COMMENT '汇报给',
  `COMMENT` varchar(100) DEFAULT NULL COMMENT '其他事项',
  `DATE` datetime DEFAULT NULL COMMENT '日期',
  `WORKTHISWEEK` mediumtext DEFAULT NULL COMMENT '本周工作',
  `PLANNEXTWEEK` mediumtext DEFAULT NULL COMMENT '下周计划',
  `THISWEEKTASK` varchar(100) DEFAULT NULL COMMENT '本周完成任务',
  `NEXTWEEKTASK` varchar(100) DEFAULT NULL COMMENT '下周计划任务',
  `UPDATEMANNAME` varchar(60) DEFAULT NULL COMMENT '更新人名称',
  `CREATEMANNAME` varchar(60) DEFAULT NULL COMMENT '建立人名称',
  `REPORTSTATUS` varchar(60) DEFAULT NULL COMMENT '状态',
  `SUBMITTIME` datetime DEFAULT NULL COMMENT '提交时间',
  PRIMARY KEY (`IBZ_WEEKLYID`)
) ENGINE=InnoDB AUTO_INCREMENT=351 DEFAULT CHARSET=utf8 COMMENT='周报';

-- ----------------------------
-- Table structure for t_sys_update_features
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_update_features`;
CREATE TABLE `t_sys_update_features` (
  `SYS_UPDATE_FEATURESNAME` varchar(200) DEFAULT NULL COMMENT '系统更新功能名称',
  `SYS_UPDATE_FEATURESID` varchar(100) NOT NULL COMMENT '系统更新功能标识',
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  `CREATEDATE` datetime DEFAULT NULL COMMENT '建立时间',
  `UPDATEDATE` datetime DEFAULT NULL COMMENT '更新时间',
  `SYS_UPDATE_LOG` varchar(100) DEFAULT NULL COMMENT '系统更新日志标识',
  `SYS_UPDATE_LOGID` varchar(100) DEFAULT NULL COMMENT '系统更新日志标识',
  `UPFEATURES` varchar(100) DEFAULT NULL COMMENT '更新功能',
  `FEATURESDESC` mediumtext DEFAULT NULL COMMENT '功能描述',
  `TYPE` varchar(60) DEFAULT NULL COMMENT '更新类型',
  `DISPLAYORDER` int(4) DEFAULT NULL COMMENT '展示顺序',
  PRIMARY KEY (`SYS_UPDATE_FEATURESID`),
  KEY `F4E719C7322A682CBD` (`SYS_UPDATE_LOG`),
  KEY `F4F285C7ACBBE2262F` (`SYS_UPDATE_LOGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统更新功能';

-- ----------------------------
-- Table structure for t_sys_update_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_update_log`;
CREATE TABLE `t_sys_update_log` (
  `SYS_UPDATE_LOGID` varchar(100) NOT NULL COMMENT '系统更新日志标识',
  `SYS_UPDATE_LOGNAME` varchar(200) DEFAULT NULL COMMENT '更新名称',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `CREATEDATE` datetime DEFAULT NULL COMMENT '建立时间',
  `UPDATEDATE` datetime DEFAULT NULL COMMENT '更新时间',
  `UPDATE` datetime DEFAULT NULL COMMENT '更新日期',
  `UPDESC` mediumtext DEFAULT NULL COMMENT '更新说明',
  `LATESTUPDATE` int(11) DEFAULT NULL COMMENT '最新更新',
  `UPDATEBRANCH` varchar(3) DEFAULT NULL COMMENT '更新平台',
  PRIMARY KEY (`SYS_UPDATE_LOGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统更新日志';

-- ----------------------------
-- Table structure for t_taskmsgrecord
-- ----------------------------
DROP TABLE IF EXISTS `t_taskmsgrecord`;
CREATE TABLE `t_taskmsgrecord` (
  `TASKMSGRECORDID` varchar(100) NOT NULL COMMENT '待办消息记录标识',
  `CREATEMAN` varchar(60) DEFAULT NULL COMMENT '建立人',
  `TASKMSGRECORDNAME` varchar(200) DEFAULT NULL COMMENT '待办消息记录名称',
  `ENABLE` int(11) DEFAULT NULL COMMENT '逻辑有效标志',
  `UPDATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `UPDATEMAN` varchar(60) DEFAULT NULL COMMENT '更新人',
  `CREATEDATE` datetime DEFAULT '0000-00-00 00:00:00',
  `TASKUSERID` varchar(100) DEFAULT NULL COMMENT '待办人标识',
  `APPTASKID` varchar(100) DEFAULT NULL COMMENT '第三方应用待办标识',
  `TASKTYPE` varchar(100) DEFAULT NULL COMMENT '待办类型',
  `MEMO` varchar(100) DEFAULT NULL COMMENT '备注',
  `TITLE` varchar(100) DEFAULT NULL COMMENT '标题',
  `DATAID` varchar(100) DEFAULT NULL COMMENT '数据标识',
  PRIMARY KEY (`TASKMSGRECORDID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='待办消息记录';

-- ----------------------------
-- Table structure for zt_action
-- ----------------------------
DROP TABLE IF EXISTS `zt_action`;
CREATE TABLE `zt_action` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `objectType` varchar(30) NOT NULL DEFAULT '',
  `objectID` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `product` varchar(255) NOT NULL,
  `project` mediumint(9) NOT NULL,
  `actor` varchar(100) NOT NULL DEFAULT '',
  `action` varchar(30) NOT NULL DEFAULT '',
  `date` datetime DEFAULT '0000-00-00 00:00:00',
  `comment` text NOT NULL,
  `extra` text NOT NULL,
  `read` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `date` (`date`),
  KEY `actor` (`actor`),
  KEY `project` (`project`),
  KEY `objectID` (`objectID`)
) ENGINE=InnoDB AUTO_INCREMENT=48502 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_block
-- ----------------------------
DROP TABLE IF EXISTS `zt_block`;
CREATE TABLE `zt_block` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `account` char(30) NOT NULL,
  `module` varchar(20) NOT NULL,
  `title` varchar(100) NOT NULL,
  `source` varchar(20) NOT NULL,
  `block` varchar(20) NOT NULL,
  `params` text NOT NULL,
  `order` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `grid` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `height` smallint(5) unsigned NOT NULL DEFAULT 0,
  `hidden` tinyint(1) unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `accountModuleOrder` (`account`,`module`,`order`),
  KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=810 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_branch
-- ----------------------------
DROP TABLE IF EXISTS `zt_branch`;
CREATE TABLE `zt_branch` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `product` mediumint(8) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `order` smallint(5) unsigned NOT NULL,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `product` (`product`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_bug
-- ----------------------------
DROP TABLE IF EXISTS `zt_bug`;
CREATE TABLE `zt_bug` (
  `id` mediumint(8) NOT NULL AUTO_INCREMENT,
  `product` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `branch` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `module` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `project` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `plan` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `story` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `storyVersion` smallint(6) NOT NULL DEFAULT 1,
  `task` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `toTask` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `toStory` mediumint(8) NOT NULL DEFAULT 0,
  `title` varchar(255) NOT NULL,
  `keywords` varchar(255) NOT NULL,
  `severity` tinyint(4) NOT NULL DEFAULT 0,
  `pri` tinyint(3) unsigned NOT NULL,
  `type` varchar(30) NOT NULL DEFAULT '',
  `os` varchar(30) NOT NULL DEFAULT '',
  `browser` varchar(30) NOT NULL DEFAULT '',
  `hardware` varchar(30) NOT NULL,
  `found` varchar(30) NOT NULL DEFAULT '',
  `steps` text NOT NULL,
  `status` enum('active','resolved','closed') NOT NULL DEFAULT 'active',
  `subStatus` varchar(30) NOT NULL DEFAULT '',
  `color` char(7) NOT NULL,
  `confirmed` tinyint(1) NOT NULL DEFAULT 0,
  `activatedCount` smallint(6) NOT NULL,
  `activatedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `mailto` text DEFAULT NULL,
  `openedBy` varchar(30) NOT NULL DEFAULT '',
  `openedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `openedBuild` varchar(255) NOT NULL,
  `assignedTo` varchar(30) NOT NULL DEFAULT '',
  `assignedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `deadline` date DEFAULT '0000-00-00',
  `resolvedBy` varchar(30) NOT NULL DEFAULT '',
  `resolution` varchar(30) NOT NULL DEFAULT '',
  `resolvedBuild` varchar(30) NOT NULL DEFAULT '',
  `resolvedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `closedBy` varchar(30) NOT NULL DEFAULT '',
  `closedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `duplicateBug` mediumint(8) unsigned NOT NULL,
  `linkBug` varchar(255) NOT NULL,
  `case` mediumint(8) unsigned NOT NULL,
  `caseVersion` smallint(6) NOT NULL DEFAULT 1,
  `result` mediumint(8) unsigned NOT NULL,
  `repo` mediumint(8) unsigned NOT NULL,
  `entry` varchar(255) NOT NULL,
  `lines` varchar(10) NOT NULL,
  `v1` varchar(40) NOT NULL,
  `v2` varchar(40) NOT NULL,
  `repoType` varchar(30) NOT NULL DEFAULT '',
  `testtask` mediumint(8) unsigned NOT NULL,
  `lastEditedBy` varchar(30) NOT NULL DEFAULT '',
  `lastEditedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `product` (`product`),
  KEY `project` (`project`),
  KEY `status` (`status`),
  KEY `plan` (`plan`),
  KEY `story` (`story`),
  KEY `case` (`case`),
  KEY `assignedTo` (`assignedTo`),
  KEY `FE78608389B0F8256E` (`branch`),
  KEY `F2121957B58E9AC70E` (`duplicateBug`),
  KEY `F2D9B199EFCE9D11C9` (`entry`),
  KEY `F2F5AE3C453BA66928` (`module`),
  KEY `FADCD439791EF71575` (`repo`),
  KEY `F35B0FF93CF3498B28` (`task`),
  KEY `F2AAA7501573EA258E` (`testtask`),
  KEY `FBC549EB20D0091CA8` (`toStory`),
  KEY `FE9BAFE0B99BFA950C` (`toTask`)
) ENGINE=InnoDB AUTO_INCREMENT=1236 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_build
-- ----------------------------
DROP TABLE IF EXISTS `zt_build`;
CREATE TABLE `zt_build` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `product` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `branch` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `project` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `name` char(150) NOT NULL,
  `scmPath` char(255) NOT NULL,
  `filePath` char(255) NOT NULL,
  `date` date DEFAULT '0000-00-00',
  `stories` text NOT NULL,
  `bugs` text NOT NULL,
  `builder` char(30) NOT NULL DEFAULT '',
  `desc` text NOT NULL,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  `CREATEBUGCNT` mediumtext DEFAULT NULL COMMENT '产生的bug',
  PRIMARY KEY (`id`),
  KEY `product` (`product`),
  KEY `project` (`project`),
  KEY `F8A46A551C9DC16F40` (`branch`)
) ENGINE=InnoDB AUTO_INCREMENT=394 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_burn
-- ----------------------------
DROP TABLE IF EXISTS `zt_burn`;
CREATE TABLE `zt_burn` (
  `project` mediumint(8) unsigned NOT NULL,
  `task` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `date` date NOT NULL DEFAULT '0000-00-00',
  `estimate` float NOT NULL,
  `left` float NOT NULL,
  `consumed` float NOT NULL,
  PRIMARY KEY (`project`,`date`,`task`),
  KEY `F50C7A44EC35D2C2C5` (`task`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_case
-- ----------------------------
DROP TABLE IF EXISTS `zt_case`;
CREATE TABLE `zt_case` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `product` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `branch` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `lib` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `module` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `path` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `story` mediumint(30) unsigned NOT NULL DEFAULT 0,
  `storyVersion` smallint(6) NOT NULL DEFAULT 1,
  `title` varchar(255) NOT NULL,
  `precondition` text NOT NULL,
  `keywords` varchar(255) NOT NULL,
  `pri` tinyint(3) unsigned NOT NULL DEFAULT 3,
  `type` char(30) NOT NULL DEFAULT '1',
  `auto` varchar(10) NOT NULL DEFAULT 'no',
  `frame` varchar(10) NOT NULL,
  `stage` varchar(255) NOT NULL,
  `howRun` varchar(30) NOT NULL,
  `scriptedBy` varchar(30) NOT NULL,
  `scriptedDate` date DEFAULT '0000-00-00',
  `scriptStatus` varchar(30) NOT NULL,
  `scriptLocation` varchar(255) NOT NULL,
  `status` char(30) NOT NULL DEFAULT '1',
  `subStatus` varchar(30) NOT NULL DEFAULT '',
  `color` char(7) NOT NULL,
  `frequency` enum('1','2','3') NOT NULL DEFAULT '1',
  `order` tinyint(30) unsigned NOT NULL DEFAULT 0,
  `openedBy` char(30) NOT NULL DEFAULT '',
  `openedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `reviewedBy` varchar(255) NOT NULL,
  `reviewedDate` date DEFAULT '0000-00-00',
  `lastEditedBy` char(30) NOT NULL DEFAULT '',
  `lastEditedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `version` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `linkCase` varchar(255) NOT NULL,
  `fromBug` mediumint(8) unsigned NOT NULL,
  `fromCaseID` mediumint(8) unsigned NOT NULL,
  `fromCaseVersion` mediumint(8) unsigned NOT NULL,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  `lastRunner` varchar(30) NOT NULL,
  `lastRunDate` datetime DEFAULT '0000-00-00 00:00:00',
  `lastRunResult` char(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product` (`product`),
  KEY `story` (`story`),
  KEY `module` (`module`),
  KEY `F5BDF27B698BD5339F` (`branch`),
  KEY `F30260955C984B92AD` (`fromBug`),
  KEY `FC94FB7363E9E779B1` (`fromCaseID`),
  KEY `FDDBEF4CB165D6CF31` (`lib`)
) ENGINE=InnoDB AUTO_INCREMENT=335 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_casestep
-- ----------------------------
DROP TABLE IF EXISTS `zt_casestep`;
CREATE TABLE `zt_casestep` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `parent` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `case` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `version` smallint(3) unsigned NOT NULL DEFAULT 0,
  `type` varchar(10) NOT NULL DEFAULT 'step',
  `desc` text NOT NULL,
  `expect` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `case` (`case`),
  KEY `version` (`version`),
  KEY `FF94559ADBCA21DAD8` (`parent`)
) ENGINE=InnoDB AUTO_INCREMENT=987 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_company
-- ----------------------------
DROP TABLE IF EXISTS `zt_company`;
CREATE TABLE `zt_company` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(120) DEFAULT NULL,
  `phone` char(20) DEFAULT NULL,
  `fax` char(20) DEFAULT NULL,
  `address` char(120) DEFAULT NULL,
  `zipcode` char(10) DEFAULT NULL,
  `website` char(120) DEFAULT NULL,
  `backyard` char(120) DEFAULT NULL,
  `guest` enum('1','0') NOT NULL DEFAULT '0',
  `admins` char(255) DEFAULT NULL,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_compile
-- ----------------------------
DROP TABLE IF EXISTS `zt_compile`;
CREATE TABLE `zt_compile` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `job` mediumint(8) unsigned NOT NULL,
  `queue` mediumint(8) NOT NULL,
  `status` varchar(255) NOT NULL,
  `logs` text DEFAULT NULL,
  `atTime` varchar(10) NOT NULL,
  `testtask` mediumint(8) unsigned NOT NULL,
  `tag` varchar(255) NOT NULL,
  `createdBy` varchar(30) NOT NULL,
  `createdDate` datetime DEFAULT '0000-00-00 00:00:00',
  `updateDate` datetime DEFAULT '0000-00-00 00:00:00',
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_config
-- ----------------------------
DROP TABLE IF EXISTS `zt_config`;
CREATE TABLE `zt_config` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `owner` char(30) NOT NULL DEFAULT '',
  `module` varchar(30) NOT NULL,
  `section` char(30) NOT NULL DEFAULT '',
  `key` char(30) NOT NULL DEFAULT '',
  `value` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`owner`,`module`,`section`,`key`)
) ENGINE=InnoDB AUTO_INCREMENT=713 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_cron
-- ----------------------------
DROP TABLE IF EXISTS `zt_cron`;
CREATE TABLE `zt_cron` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `m` varchar(20) NOT NULL,
  `h` varchar(20) NOT NULL,
  `dom` varchar(20) NOT NULL,
  `mon` varchar(20) NOT NULL,
  `dow` varchar(20) NOT NULL,
  `command` text NOT NULL,
  `remark` varchar(255) NOT NULL,
  `type` varchar(20) NOT NULL,
  `buildin` tinyint(1) NOT NULL DEFAULT 0,
  `status` varchar(20) NOT NULL,
  `lastTime` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `lastTime` (`lastTime`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_dept
-- ----------------------------
DROP TABLE IF EXISTS `zt_dept`;
CREATE TABLE `zt_dept` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(60) NOT NULL,
  `parent` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `path` char(255) NOT NULL DEFAULT '',
  `grade` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `order` smallint(4) unsigned NOT NULL DEFAULT 0,
  `position` char(30) NOT NULL DEFAULT '',
  `function` char(255) NOT NULL DEFAULT '',
  `manager` char(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `parent` (`parent`),
  KEY `path` (`path`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_doc
-- ----------------------------
DROP TABLE IF EXISTS `zt_doc`;
CREATE TABLE `zt_doc` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `product` mediumint(8) unsigned NOT NULL,
  `project` mediumint(8) unsigned NOT NULL,
  `lib` varchar(30) NOT NULL,
  `module` varchar(30) NOT NULL,
  `title` varchar(255) NOT NULL,
  `keywords` varchar(255) NOT NULL,
  `type` varchar(30) NOT NULL,
  `views` smallint(5) unsigned NOT NULL,
  `collector` text NOT NULL,
  `addedBy` varchar(30) NOT NULL,
  `addedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `editedBy` varchar(30) NOT NULL,
  `editedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `acl` varchar(10) NOT NULL DEFAULT 'open',
  `groups` varchar(255) NOT NULL,
  `users` text NOT NULL,
  `version` smallint(5) unsigned NOT NULL DEFAULT 1,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `product` (`product`),
  KEY `project` (`project`),
  KEY `lib` (`lib`),
  KEY `F6B68C678D04BE731E` (`module`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_doccontent
-- ----------------------------
DROP TABLE IF EXISTS `zt_doccontent`;
CREATE TABLE `zt_doccontent` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `doc` mediumint(8) unsigned NOT NULL,
  `title` varchar(255) NOT NULL,
  `digest` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `files` text NOT NULL,
  `type` varchar(10) NOT NULL,
  `version` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `doc_version` (`doc`,`version`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_doclib
-- ----------------------------
DROP TABLE IF EXISTS `zt_doclib`;
CREATE TABLE `zt_doclib` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL,
  `product` mediumint(8) unsigned NOT NULL,
  `project` mediumint(8) unsigned NOT NULL,
  `name` varchar(60) NOT NULL,
  `acl` varchar(10) NOT NULL DEFAULT 'open',
  `groups` varchar(255) NOT NULL,
  `users` text NOT NULL,
  `main` enum('0','1') NOT NULL DEFAULT '0',
  `collector` text NOT NULL,
  `order` tinyint(5) unsigned NOT NULL,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  `ORGID` varchar(100) DEFAULT NULL COMMENT '组织标识',
  `MDEPTID` varchar(100) DEFAULT NULL COMMENT '部门标识',
  PRIMARY KEY (`id`),
  KEY `product` (`product`),
  KEY `project` (`project`)
) ENGINE=InnoDB AUTO_INCREMENT=327 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_effort
-- ----------------------------
DROP TABLE IF EXISTS `zt_effort`;
CREATE TABLE `zt_effort` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `user` char(30) NOT NULL DEFAULT '',
  `todo` enum('1','0') NOT NULL DEFAULT '1',
  `date` date DEFAULT '0000-00-00',
  `begin` datetime DEFAULT '0000-00-00 00:00:00',
  `end` datetime DEFAULT '0000-00-00 00:00:00',
  `type` enum('1','2','3') NOT NULL DEFAULT '1',
  `idvalue` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `name` char(30) NOT NULL DEFAULT '',
  `desc` char(255) NOT NULL DEFAULT '',
  `status` enum('1','2','3') NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `user` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_entry
-- ----------------------------
DROP TABLE IF EXISTS `zt_entry`;
CREATE TABLE `zt_entry` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `account` varchar(30) NOT NULL DEFAULT '',
  `code` varchar(20) NOT NULL,
  `key` varchar(32) NOT NULL,
  `freePasswd` enum('0','1') NOT NULL DEFAULT '0',
  `ip` varchar(100) NOT NULL,
  `desc` text NOT NULL,
  `createdBy` varchar(30) NOT NULL,
  `createdDate` datetime DEFAULT '0000-00-00 00:00:00',
  `calledTime` int(10) unsigned NOT NULL DEFAULT 0,
  `editedBy` varchar(30) NOT NULL,
  `editedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_extension
-- ----------------------------
DROP TABLE IF EXISTS `zt_extension`;
CREATE TABLE `zt_extension` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `code` varchar(30) NOT NULL,
  `version` varchar(50) NOT NULL,
  `author` varchar(100) NOT NULL,
  `desc` text NOT NULL,
  `license` text NOT NULL,
  `type` varchar(20) NOT NULL DEFAULT 'extension',
  `site` varchar(150) NOT NULL,
  `zentaoCompatible` varchar(100) NOT NULL,
  `installedTime` datetime DEFAULT '0000-00-00 00:00:00',
  `depends` varchar(100) NOT NULL,
  `dirs` mediumtext NOT NULL,
  `files` mediumtext NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `name` (`name`),
  KEY `installedTime` (`installedTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_file
-- ----------------------------
DROP TABLE IF EXISTS `zt_file`;
CREATE TABLE `zt_file` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `pathname` char(100) NOT NULL,
  `title` char(255) NOT NULL,
  `extension` char(30) NOT NULL,
  `size` int(10) unsigned NOT NULL DEFAULT 0,
  `objectType` char(30) NOT NULL,
  `objectID` mediumint(9) NOT NULL,
  `addedBy` char(30) NOT NULL DEFAULT '',
  `addedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `downloads` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `extra` varchar(255) NOT NULL,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `objectType` (`objectType`),
  KEY `objectID` (`objectID`)
) ENGINE=InnoDB AUTO_INCREMENT=4826 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_group
-- ----------------------------
DROP TABLE IF EXISTS `zt_group`;
CREATE TABLE `zt_group` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(30) NOT NULL,
  `role` char(30) NOT NULL DEFAULT '',
  `desc` char(255) NOT NULL DEFAULT '',
  `acl` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_grouppriv
-- ----------------------------
DROP TABLE IF EXISTS `zt_grouppriv`;
CREATE TABLE `zt_grouppriv` (
  `group` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `module` char(30) NOT NULL DEFAULT '',
  `method` char(30) NOT NULL DEFAULT '',
  UNIQUE KEY `group` (`group`,`module`,`method`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_history
-- ----------------------------
DROP TABLE IF EXISTS `zt_history`;
CREATE TABLE `zt_history` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `action` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `field` varchar(30) NOT NULL DEFAULT '',
  `old` text NOT NULL,
  `new` text NOT NULL,
  `diff` mediumtext NOT NULL,
  PRIMARY KEY (`id`),
  KEY `action` (`action`)
) ENGINE=InnoDB AUTO_INCREMENT=63608 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_im_chat
-- ----------------------------
DROP TABLE IF EXISTS `zt_im_chat`;
CREATE TABLE `zt_im_chat` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `gid` char(40) NOT NULL DEFAULT '',
  `name` varchar(60) NOT NULL DEFAULT '',
  `type` varchar(20) NOT NULL DEFAULT 'group',
  `admins` varchar(255) NOT NULL DEFAULT '',
  `committers` varchar(255) NOT NULL DEFAULT '',
  `subject` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `public` enum('0','1') NOT NULL DEFAULT '0',
  `createdBy` varchar(30) NOT NULL DEFAULT '',
  `createdDate` datetime DEFAULT '0000-00-00 00:00:00',
  `editedBy` varchar(30) NOT NULL DEFAULT '',
  `editedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `lastActiveTime` datetime DEFAULT '0000-00-00 00:00:00',
  `dismissDate` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `gid` (`gid`),
  KEY `name` (`name`),
  KEY `type` (`type`),
  KEY `public` (`public`),
  KEY `createdBy` (`createdBy`),
  KEY `editedBy` (`editedBy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_im_chatuser
-- ----------------------------
DROP TABLE IF EXISTS `zt_im_chatuser`;
CREATE TABLE `zt_im_chatuser` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `cgid` char(40) NOT NULL DEFAULT '',
  `user` mediumint(8) NOT NULL DEFAULT 0,
  `order` smallint(5) NOT NULL DEFAULT 0,
  `star` enum('0','1') NOT NULL DEFAULT '0',
  `hide` enum('0','1') NOT NULL DEFAULT '0',
  `mute` enum('0','1') NOT NULL DEFAULT '0',
  `freeze` enum('0','1') NOT NULL DEFAULT '0',
  `join` datetime DEFAULT '0000-00-00 00:00:00',
  `quit` datetime DEFAULT '0000-00-00 00:00:00',
  `category` varchar(40) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `chatuser` (`cgid`,`user`),
  KEY `cgid` (`cgid`),
  KEY `user` (`user`),
  KEY `order` (`order`),
  KEY `star` (`star`),
  KEY `hide` (`hide`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_im_client
-- ----------------------------
DROP TABLE IF EXISTS `zt_im_client`;
CREATE TABLE `zt_im_client` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `version` char(30) NOT NULL DEFAULT '',
  `desc` varchar(100) NOT NULL DEFAULT '',
  `changeLog` text NOT NULL,
  `strategy` varchar(10) NOT NULL DEFAULT '',
  `downloads` text NOT NULL,
  `createdDate` datetime DEFAULT '0000-00-00 00:00:00',
  `createdBy` varchar(30) NOT NULL DEFAULT '',
  `editedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `editedBy` varchar(30) NOT NULL DEFAULT '',
  `status` enum('released','wait') NOT NULL DEFAULT 'wait',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_im_conference
-- ----------------------------
DROP TABLE IF EXISTS `zt_im_conference`;
CREATE TABLE `zt_im_conference` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `rid` char(24) NOT NULL DEFAULT '',
  `cgid` char(40) NOT NULL DEFAULT '',
  `status` enum('closed','open') NOT NULL DEFAULT 'closed',
  `participants` text NOT NULL,
  `openedBy` mediumint(8) NOT NULL DEFAULT 0,
  `openedDate` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_im_conferenceaction
-- ----------------------------
DROP TABLE IF EXISTS `zt_im_conferenceaction`;
CREATE TABLE `zt_im_conferenceaction` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `rid` char(24) NOT NULL DEFAULT '',
  `type` enum('create','join','leave','close') NOT NULL DEFAULT 'create',
  `user` mediumint(8) NOT NULL DEFAULT 0,
  `date` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_im_message
-- ----------------------------
DROP TABLE IF EXISTS `zt_im_message`;
CREATE TABLE `zt_im_message` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `gid` char(40) NOT NULL DEFAULT '',
  `cgid` char(40) NOT NULL DEFAULT '',
  `user` varchar(30) NOT NULL DEFAULT '',
  `date` datetime DEFAULT '0000-00-00 00:00:00',
  `order` bigint(8) unsigned NOT NULL,
  `type` enum('normal','broadcast','notify') NOT NULL DEFAULT 'normal',
  `content` text NOT NULL,
  `contentType` enum('text','plain','emotion','image','file','object','code') NOT NULL DEFAULT 'text',
  `data` text NOT NULL,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `mgid` (`gid`),
  KEY `mcgid` (`cgid`),
  KEY `muser` (`user`),
  KEY `mtype` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=4328 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_im_messagestatus
-- ----------------------------
DROP TABLE IF EXISTS `zt_im_messagestatus`;
CREATE TABLE `zt_im_messagestatus` (
  `user` mediumint(8) NOT NULL DEFAULT 0,
  `message` int(11) unsigned NOT NULL,
  `status` enum('waiting','sent','readed','deleted') NOT NULL DEFAULT 'waiting',
  UNIQUE KEY `user` (`user`,`message`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_im_queue
-- ----------------------------
DROP TABLE IF EXISTS `zt_im_queue`;
CREATE TABLE `zt_im_queue` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `type` char(30) NOT NULL,
  `content` text NOT NULL,
  `addDate` datetime DEFAULT '0000-00-00 00:00:00',
  `processDate` datetime DEFAULT '0000-00-00 00:00:00',
  `result` text NOT NULL,
  `status` char(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_jenkins
-- ----------------------------
DROP TABLE IF EXISTS `zt_jenkins`;
CREATE TABLE `zt_jenkins` (
  `id` smallint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `account` varchar(30) DEFAULT NULL,
  `password` varchar(30) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `createdBy` varchar(30) NOT NULL,
  `createdDate` datetime DEFAULT '0000-00-00 00:00:00',
  `editedBy` varchar(30) NOT NULL,
  `editedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_job
-- ----------------------------
DROP TABLE IF EXISTS `zt_job`;
CREATE TABLE `zt_job` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `repo` mediumint(8) unsigned NOT NULL,
  `product` mediumint(8) unsigned NOT NULL,
  `frame` varchar(20) NOT NULL,
  `jkHost` mediumint(8) unsigned NOT NULL,
  `jkJob` varchar(500) NOT NULL,
  `triggerType` varchar(255) NOT NULL,
  `svnDir` varchar(255) NOT NULL,
  `atDay` varchar(255) DEFAULT NULL,
  `atTime` varchar(10) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `createdBy` varchar(30) NOT NULL,
  `createdDate` datetime DEFAULT '0000-00-00 00:00:00',
  `editedBy` varchar(30) NOT NULL,
  `editedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `lastExec` datetime DEFAULT '0000-00-00 00:00:00',
  `lastStatus` varchar(255) DEFAULT NULL,
  `lastTag` varchar(255) DEFAULT NULL,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_lang
-- ----------------------------
DROP TABLE IF EXISTS `zt_lang`;
CREATE TABLE `zt_lang` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `lang` varchar(30) NOT NULL,
  `module` varchar(30) NOT NULL,
  `section` varchar(30) NOT NULL,
  `key` varchar(60) NOT NULL,
  `value` text NOT NULL,
  `system` enum('0','1') NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `lang` (`lang`,`module`,`section`,`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_log
-- ----------------------------
DROP TABLE IF EXISTS `zt_log`;
CREATE TABLE `zt_log` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `objectType` varchar(30) NOT NULL,
  `objectID` mediumint(8) unsigned NOT NULL,
  `action` mediumint(8) unsigned NOT NULL,
  `date` datetime DEFAULT '0000-00-00 00:00:00',
  `url` varchar(255) NOT NULL,
  `contentType` varchar(30) NOT NULL,
  `data` text NOT NULL,
  `result` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `objectType` (`objectType`),
  KEY `obejctID` (`objectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_module
-- ----------------------------
DROP TABLE IF EXISTS `zt_module`;
CREATE TABLE `zt_module` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `root` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `branch` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `name` char(60) NOT NULL DEFAULT '',
  `parent` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `path` char(255) NOT NULL DEFAULT '',
  `grade` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `order` smallint(5) unsigned NOT NULL DEFAULT 0,
  `type` char(30) NOT NULL,
  `owner` varchar(30) NOT NULL,
  `collector` text NOT NULL,
  `short` varchar(30) NOT NULL,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  `IBIZ_ID` varchar(100) DEFAULT NULL COMMENT 'IBIZ标识',
  `IBIZ_STORYTYPE` varchar(60) DEFAULT NULL COMMENT '需求模块类型',
  `MDEPTID` varchar(100) DEFAULT NULL COMMENT '部门标识',
  `ORGID` varchar(100) DEFAULT NULL COMMENT '组织标识',
  PRIMARY KEY (`id`),
  KEY `root` (`root`),
  KEY `type` (`type`),
  KEY `path` (`path`),
  KEY `FFFF259E27212EF105` (`branch`),
  KEY `FFA8C1CE8C188BAC20` (`parent`)
) ENGINE=InnoDB AUTO_INCREMENT=969 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_notify
-- ----------------------------
DROP TABLE IF EXISTS `zt_notify`;
CREATE TABLE `zt_notify` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `objectType` varchar(50) NOT NULL,
  `objectID` mediumint(8) unsigned NOT NULL,
  `action` mediumint(9) NOT NULL,
  `toList` varchar(255) NOT NULL,
  `ccList` text NOT NULL,
  `subject` varchar(255) NOT NULL,
  `data` text NOT NULL,
  `createdBy` char(30) NOT NULL,
  `createdDate` datetime DEFAULT '0000-00-00 00:00:00',
  `sendTime` datetime DEFAULT '0000-00-00 00:00:00',
  `status` varchar(10) NOT NULL DEFAULT 'wait',
  `failReason` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3795 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_oauth
-- ----------------------------
DROP TABLE IF EXISTS `zt_oauth`;
CREATE TABLE `zt_oauth` (
  `account` varchar(30) NOT NULL,
  `openID` varchar(255) NOT NULL,
  `providerType` varchar(30) NOT NULL,
  `providerID` mediumint(8) unsigned NOT NULL,
  KEY `account` (`account`),
  KEY `providerType` (`providerType`),
  KEY `providerID` (`providerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_product
-- ----------------------------
DROP TABLE IF EXISTS `zt_product`;
CREATE TABLE `zt_product` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(90) NOT NULL,
  `code` varchar(45) NOT NULL,
  `line` mediumint(8) NOT NULL,
  `type` varchar(30) NOT NULL DEFAULT 'normal',
  `status` varchar(30) NOT NULL DEFAULT '',
  `subStatus` varchar(30) NOT NULL DEFAULT '',
  `desc` text NOT NULL,
  `PO` varchar(30) NOT NULL,
  `QD` varchar(30) NOT NULL,
  `RD` varchar(30) NOT NULL,
  `acl` enum('open','private','custom') NOT NULL DEFAULT 'open',
  `whitelist` text NOT NULL,
  `createdBy` varchar(30) NOT NULL,
  `createdDate` datetime DEFAULT '0000-00-00 00:00:00',
  `createdVersion` varchar(20) NOT NULL,
  `order` mediumint(8) unsigned NOT NULL,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  `IBIZ_ID` varchar(100) DEFAULT NULL COMMENT 'IBIZ标识',
  `ORGID` varchar(100) DEFAULT NULL COMMENT '组织标识',
  `MDEPTID` varchar(100) DEFAULT NULL COMMENT '部门标识',
  PRIMARY KEY (`id`),
  KEY `acl` (`acl`),
  KEY `order` (`order`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_productplan
-- ----------------------------
DROP TABLE IF EXISTS `zt_productplan`;
CREATE TABLE `zt_productplan` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `product` mediumint(8) unsigned NOT NULL,
  `branch` mediumint(8) unsigned NOT NULL,
  `parent` mediumint(9) NOT NULL DEFAULT 0,
  `title` varchar(90) NOT NULL,
  `desc` text NOT NULL,
  `begin` date DEFAULT '0000-00-00',
  `end` date DEFAULT '0000-00-00',
  `order` text NOT NULL,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `product` (`product`),
  KEY `end` (`end`),
  KEY `F3867C90B35CA7708F` (`branch`),
  KEY `FD318C2A1115FC7997` (`parent`)
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_project
-- ----------------------------
DROP TABLE IF EXISTS `zt_project`;
CREATE TABLE `zt_project` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `isCat` enum('1','0') NOT NULL DEFAULT '0',
  `catID` mediumint(8) unsigned NOT NULL,
  `type` varchar(20) NOT NULL DEFAULT 'sprint',
  `parent` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `name` varchar(90) NOT NULL,
  `code` varchar(45) NOT NULL,
  `begin` date NOT NULL DEFAULT '0000-00-00',
  `end` date NOT NULL DEFAULT '0000-00-00',
  `days` smallint(5) unsigned NOT NULL,
  `status` varchar(10) NOT NULL,
  `subStatus` varchar(30) NOT NULL DEFAULT '',
  `statge` enum('1','2','3','4','5') NOT NULL DEFAULT '1',
  `pri` enum('1','2','3','4') NOT NULL DEFAULT '1',
  `desc` text NOT NULL,
  `openedBy` varchar(30) NOT NULL DEFAULT '',
  `openedDate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `openedVersion` varchar(20) NOT NULL,
  `closedBy` varchar(30) NOT NULL DEFAULT '',
  `closedDate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `canceledBy` varchar(30) NOT NULL DEFAULT '',
  `canceledDate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `PO` varchar(30) NOT NULL DEFAULT '',
  `PM` varchar(30) NOT NULL DEFAULT '',
  `QD` varchar(30) NOT NULL DEFAULT '',
  `RD` varchar(30) NOT NULL DEFAULT '',
  `team` varchar(90) NOT NULL,
  `acl` enum('open','private','custom') NOT NULL DEFAULT 'open',
  `whitelist` text NOT NULL,
  `order` mediumint(8) unsigned NOT NULL,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  `ORGID` varchar(100) DEFAULT NULL COMMENT '组织标识',
  `MDEPTID` varchar(100) DEFAULT NULL COMMENT '部门标识',
  PRIMARY KEY (`id`),
  KEY `parent` (`parent`),
  KEY `begin` (`begin`),
  KEY `end` (`end`),
  KEY `status` (`status`),
  KEY `acl` (`acl`),
  KEY `order` (`order`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_projectproduct
-- ----------------------------
DROP TABLE IF EXISTS `zt_projectproduct`;
CREATE TABLE `zt_projectproduct` (
  `project` mediumint(8) unsigned NOT NULL,
  `product` mediumint(8) unsigned NOT NULL,
  `branch` mediumint(8) unsigned NOT NULL,
  `plan` mediumint(8) unsigned NOT NULL,
  PRIMARY KEY (`project`,`product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_projectstory
-- ----------------------------
DROP TABLE IF EXISTS `zt_projectstory`;
CREATE TABLE `zt_projectstory` (
  `project` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `product` mediumint(8) unsigned NOT NULL,
  `story` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `version` smallint(6) NOT NULL DEFAULT 1,
  `order` smallint(6) unsigned NOT NULL,
  UNIQUE KEY `project` (`project`,`story`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_relation
-- ----------------------------
DROP TABLE IF EXISTS `zt_relation`;
CREATE TABLE `zt_relation` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `program` mediumint(8) NOT NULL,
  `product` mediumint(8) NOT NULL,
  `project` mediumint(8) NOT NULL,
  `AType` char(30) NOT NULL,
  `AID` mediumint(8) NOT NULL,
  `AVersion` char(30) NOT NULL,
  `relation` char(30) NOT NULL,
  `BType` char(30) NOT NULL,
  `BID` mediumint(8) NOT NULL,
  `BVersion` char(30) NOT NULL,
  `extra` char(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `relation` (`relation`,`AType`,`BType`,`AID`,`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_release
-- ----------------------------
DROP TABLE IF EXISTS `zt_release`;
CREATE TABLE `zt_release` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `product` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `branch` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `build` mediumint(8) unsigned NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `marker` enum('0','1') NOT NULL DEFAULT '0',
  `date` date DEFAULT '0000-00-00',
  `stories` text NOT NULL,
  `bugs` text NOT NULL,
  `leftBugs` text NOT NULL,
  `desc` text NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'normal',
  `subStatus` varchar(30) NOT NULL DEFAULT '',
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `product` (`product`),
  KEY `build` (`build`),
  KEY `FAE99ACA3C05B3E9D1` (`branch`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_repo
-- ----------------------------
DROP TABLE IF EXISTS `zt_repo`;
CREATE TABLE `zt_repo` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `prefix` varchar(100) NOT NULL,
  `encoding` varchar(20) NOT NULL,
  `SCM` varchar(10) NOT NULL,
  `client` varchar(100) NOT NULL,
  `commits` mediumint(8) unsigned NOT NULL,
  `account` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `encrypt` varchar(30) NOT NULL DEFAULT 'plain',
  `acl` text NOT NULL,
  `synced` tinyint(1) NOT NULL DEFAULT 0,
  `lastSync` datetime DEFAULT '0000-00-00 00:00:00',
  `desc` text NOT NULL,
  `deleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_repobranch
-- ----------------------------
DROP TABLE IF EXISTS `zt_repobranch`;
CREATE TABLE `zt_repobranch` (
  `repo` mediumint(8) unsigned NOT NULL,
  `revision` mediumint(8) unsigned NOT NULL,
  `branch` varchar(255) NOT NULL,
  UNIQUE KEY `repo_revision_branch` (`repo`,`revision`,`branch`),
  KEY `branch` (`branch`),
  KEY `revision` (`revision`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_repofiles
-- ----------------------------
DROP TABLE IF EXISTS `zt_repofiles`;
CREATE TABLE `zt_repofiles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `repo` mediumint(8) unsigned NOT NULL,
  `revision` mediumint(8) unsigned NOT NULL,
  `path` varchar(255) NOT NULL,
  `parent` varchar(255) NOT NULL,
  `type` varchar(20) NOT NULL,
  `action` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `path` (`path`),
  KEY `parent` (`parent`),
  KEY `repo` (`repo`),
  KEY `revision` (`revision`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_repohistory
-- ----------------------------
DROP TABLE IF EXISTS `zt_repohistory`;
CREATE TABLE `zt_repohistory` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `repo` mediumint(9) NOT NULL,
  `revision` varchar(40) NOT NULL,
  `commit` mediumint(8) unsigned NOT NULL,
  `comment` text NOT NULL,
  `committer` varchar(100) NOT NULL,
  `time` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `repo` (`repo`),
  KEY `revision` (`revision`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_score
-- ----------------------------
DROP TABLE IF EXISTS `zt_score`;
CREATE TABLE `zt_score` (
  `id` bigint(12) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(30) NOT NULL,
  `module` varchar(30) NOT NULL DEFAULT '',
  `method` varchar(30) NOT NULL,
  `desc` varchar(250) NOT NULL DEFAULT '',
  `before` int(11) NOT NULL DEFAULT 0,
  `score` int(11) NOT NULL DEFAULT 0,
  `after` int(11) NOT NULL DEFAULT 0,
  `time` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `account` (`account`),
  KEY `model` (`module`),
  KEY `method` (`method`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_story
-- ----------------------------
DROP TABLE IF EXISTS `zt_story`;
CREATE TABLE `zt_story` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `parent` mediumint(9) NOT NULL DEFAULT 0,
  `product` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `branch` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `module` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `plan` text DEFAULT NULL,
  `source` varchar(20) NOT NULL,
  `sourceNote` varchar(255) NOT NULL,
  `fromBug` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `title` varchar(255) NOT NULL,
  `keywords` varchar(255) NOT NULL,
  `type` varchar(30) NOT NULL DEFAULT 'story',
  `pri` tinyint(3) unsigned NOT NULL DEFAULT 3,
  `estimate` float unsigned NOT NULL,
  `status` enum('','changed','active','draft','closed') NOT NULL DEFAULT '',
  `subStatus` varchar(30) NOT NULL DEFAULT '',
  `color` char(7) NOT NULL,
  `stage` enum('','wait','planned','projected','developing','developed','testing','tested','verified','released','closed') NOT NULL DEFAULT 'wait',
  `stagedBy` char(30) NOT NULL,
  `mailto` text DEFAULT NULL,
  `openedBy` varchar(30) NOT NULL DEFAULT '',
  `openedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `assignedTo` varchar(30) NOT NULL DEFAULT '',
  `assignedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `lastEditedBy` varchar(30) NOT NULL DEFAULT '',
  `lastEditedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `reviewedBy` varchar(255) NOT NULL,
  `reviewedDate` date DEFAULT '0000-00-00',
  `closedBy` varchar(30) NOT NULL DEFAULT '',
  `closedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `closedReason` varchar(30) NOT NULL,
  `toBug` mediumint(8) unsigned NOT NULL,
  `childStories` varchar(255) NOT NULL,
  `linkStories` varchar(255) NOT NULL,
  `duplicateStory` mediumint(8) unsigned NOT NULL,
  `version` smallint(6) NOT NULL DEFAULT 1,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  `IBIZ_ID` varchar(100) DEFAULT NULL COMMENT 'IBIZ标识',
  `IBIZ_SOURCEOBJECT` varchar(100) DEFAULT NULL COMMENT '来源对象',
  `IBIZ_SOURCENAME` varchar(100) DEFAULT NULL COMMENT '来源对象名称',
  `IBIZ_SOURCEID` varchar(100) DEFAULT NULL COMMENT '来源对象标识',
  `SOURCEOBJECT` varchar(100) DEFAULT NULL COMMENT '来源对象',
  `SOURCENAME` varchar(100) DEFAULT NULL COMMENT '来源对象名称',
  `SOURCEID` varchar(100) DEFAULT NULL COMMENT '来源对象标识',
  `STORYPOINTS` varchar(60) DEFAULT NULL COMMENT '故事点',
  PRIMARY KEY (`id`),
  KEY `product` (`product`),
  KEY `status` (`status`),
  KEY `assignedTo` (`assignedTo`),
  KEY `F9F731D9A54D01FF64` (`fromBug`),
  KEY `FA787C05588AF9E613` (`parent`),
  KEY `FDE512F36A3394B2F0` (`module`),
  KEY `F527F557C5C23076D4` (`duplicateStory`),
  KEY `F7AB21BF59AE8B5F19` (`branch`),
  KEY `F3AFBA8646F5E252B0` (`toBug`)
) ENGINE=InnoDB AUTO_INCREMENT=1067 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_storyspec
-- ----------------------------
DROP TABLE IF EXISTS `zt_storyspec`;
CREATE TABLE `zt_storyspec` (
  `story` mediumint(9) NOT NULL,
  `version` smallint(6) NOT NULL,
  `title` varchar(255) NOT NULL,
  `spec` text NOT NULL,
  `verify` text NOT NULL,
  UNIQUE KEY `story` (`story`,`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_storystage
-- ----------------------------
DROP TABLE IF EXISTS `zt_storystage`;
CREATE TABLE `zt_storystage` (
  `story` mediumint(8) unsigned NOT NULL,
  `branch` mediumint(8) unsigned NOT NULL,
  `stage` varchar(50) NOT NULL,
  `stagedBy` char(30) NOT NULL,
  UNIQUE KEY `story_branch` (`story`,`branch`),
  KEY `story` (`story`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_suitecase
-- ----------------------------
DROP TABLE IF EXISTS `zt_suitecase`;
CREATE TABLE `zt_suitecase` (
  `suite` mediumint(8) unsigned NOT NULL,
  `product` mediumint(8) unsigned NOT NULL,
  `case` mediumint(8) unsigned NOT NULL,
  `version` smallint(5) unsigned NOT NULL,
  UNIQUE KEY `suitecase` (`suite`,`case`),
  KEY `FB036A4369AEBF5F5A` (`product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_task
-- ----------------------------
DROP TABLE IF EXISTS `zt_task`;
CREATE TABLE `zt_task` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `parent` mediumint(8) NOT NULL DEFAULT 0,
  `project` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `module` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `story` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `storyVersion` smallint(6) NOT NULL DEFAULT 1,
  `fromBug` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `name` varchar(255) NOT NULL,
  `type` varchar(20) NOT NULL,
  `pri` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `estimate` float unsigned NOT NULL,
  `consumed` float unsigned NOT NULL,
  `left` float unsigned NOT NULL,
  `deadline` date DEFAULT '0000-00-00',
  `status` enum('wait','doing','done','pause','cancel','closed') NOT NULL DEFAULT 'wait',
  `subStatus` varchar(30) NOT NULL DEFAULT '',
  `color` char(7) NOT NULL,
  `mailto` text DEFAULT NULL,
  `desc` text NOT NULL,
  `openedBy` varchar(30) NOT NULL,
  `openedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `assignedTo` varchar(30) NOT NULL,
  `assignedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `estStarted` date DEFAULT '0000-00-00',
  `realStarted` date DEFAULT '0000-00-00',
  `finishedBy` varchar(30) NOT NULL,
  `finishedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `finishedList` text NOT NULL,
  `canceledBy` varchar(30) NOT NULL,
  `canceledDate` datetime DEFAULT '0000-00-00 00:00:00',
  `closedBy` varchar(30) NOT NULL,
  `closedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `closedReason` varchar(30) NOT NULL,
  `lastEditedBy` varchar(30) NOT NULL,
  `lastEditedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  `ASSIGN` varchar(100) DEFAULT NULL COMMENT '是否指派',
  PRIMARY KEY (`id`),
  KEY `project` (`project`),
  KEY `story` (`story`),
  KEY `assignedTo` (`assignedTo`),
  KEY `FABB39F1104D7A0988` (`parent`),
  KEY `F5A6E508731D6A28E8` (`fromBug`),
  KEY `F289B4B264489CC840` (`module`)
) ENGINE=InnoDB AUTO_INCREMENT=2565 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_taskestimate
-- ----------------------------
DROP TABLE IF EXISTS `zt_taskestimate`;
CREATE TABLE `zt_taskestimate` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `task` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `date` date DEFAULT '0000-00-00',
  `left` float unsigned NOT NULL DEFAULT 0,
  `consumed` float unsigned NOT NULL,
  `account` char(30) NOT NULL DEFAULT '',
  `work` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `task` (`task`)
) ENGINE=InnoDB AUTO_INCREMENT=3518 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_team
-- ----------------------------
DROP TABLE IF EXISTS `zt_team`;
CREATE TABLE `zt_team` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `root` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `type` enum('project','task') NOT NULL DEFAULT 'project',
  `account` char(30) NOT NULL DEFAULT '',
  `role` char(30) NOT NULL DEFAULT '',
  `limited` char(8) NOT NULL DEFAULT 'no',
  `join` date DEFAULT '0000-00-00',
  `days` smallint(5) unsigned NOT NULL,
  `hours` float(2,1) unsigned NOT NULL DEFAULT 0.0,
  `estimate` decimal(12,2) unsigned NOT NULL DEFAULT 0.00,
  `consumed` decimal(12,2) unsigned NOT NULL DEFAULT 0.00,
  `left` decimal(12,2) unsigned NOT NULL DEFAULT 0.00,
  `order` tinyint(3) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `team` (`root`,`type`,`account`)
) ENGINE=InnoDB AUTO_INCREMENT=5479 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_testreport
-- ----------------------------
DROP TABLE IF EXISTS `zt_testreport`;
CREATE TABLE `zt_testreport` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `product` mediumint(8) unsigned NOT NULL,
  `project` mediumint(8) unsigned NOT NULL,
  `tasks` varchar(255) NOT NULL,
  `builds` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `begin` date DEFAULT '0000-00-00',
  `end` date DEFAULT '0000-00-00',
  `owner` char(30) NOT NULL,
  `members` text NOT NULL,
  `stories` text NOT NULL,
  `bugs` text NOT NULL,
  `cases` text NOT NULL,
  `report` text NOT NULL,
  `objectType` varchar(20) NOT NULL,
  `objectID` mediumint(8) unsigned NOT NULL,
  `createdBy` char(30) NOT NULL,
  `createdDate` datetime DEFAULT '0000-00-00 00:00:00',
  `deleted` enum('0','1') NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FA3B31D7443A91D8D3` (`product`),
  KEY `FC00A2ED8D564367CA` (`project`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_testresult
-- ----------------------------
DROP TABLE IF EXISTS `zt_testresult`;
CREATE TABLE `zt_testresult` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `run` mediumint(8) unsigned NOT NULL,
  `case` mediumint(8) unsigned NOT NULL,
  `version` smallint(5) unsigned NOT NULL,
  `job` mediumint(8) unsigned NOT NULL,
  `compile` mediumint(8) unsigned NOT NULL,
  `caseResult` char(30) NOT NULL,
  `stepResults` text NOT NULL,
  `lastRunner` varchar(30) NOT NULL,
  `date` datetime DEFAULT '0000-00-00 00:00:00',
  `duration` float NOT NULL,
  `xml` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `case` (`case`),
  KEY `version` (`version`),
  KEY `run` (`run`),
  KEY `F54B7F130A43530537` (`compile`),
  KEY `F6AD166F82D8D03198` (`job`)
) ENGINE=InnoDB AUTO_INCREMENT=470 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_testrun
-- ----------------------------
DROP TABLE IF EXISTS `zt_testrun`;
CREATE TABLE `zt_testrun` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `task` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `case` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `version` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `assignedTo` char(30) NOT NULL DEFAULT '',
  `lastRunner` varchar(30) NOT NULL,
  `lastRunDate` datetime DEFAULT '0000-00-00 00:00:00',
  `lastRunResult` char(30) NOT NULL,
  `status` char(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `task` (`task`,`case`)
) ENGINE=InnoDB AUTO_INCREMENT=362 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_testsuite
-- ----------------------------
DROP TABLE IF EXISTS `zt_testsuite`;
CREATE TABLE `zt_testsuite` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `product` mediumint(8) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `desc` text NOT NULL,
  `type` varchar(20) NOT NULL,
  `addedBy` char(30) NOT NULL,
  `addedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `lastEditedBy` char(30) NOT NULL,
  `lastEditedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `deleted` enum('0','1') NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product` (`product`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_testtask
-- ----------------------------
DROP TABLE IF EXISTS `zt_testtask`;
CREATE TABLE `zt_testtask` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(90) NOT NULL,
  `product` mediumint(8) unsigned NOT NULL,
  `project` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `build` char(30) NOT NULL,
  `owner` varchar(30) NOT NULL,
  `pri` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `begin` date DEFAULT '0000-00-00',
  `end` date DEFAULT '0000-00-00',
  `mailto` text DEFAULT NULL,
  `desc` text NOT NULL,
  `report` text NOT NULL,
  `status` enum('blocked','doing','wait','done') NOT NULL DEFAULT 'wait',
  `auto` varchar(10) NOT NULL DEFAULT 'no',
  `subStatus` varchar(30) NOT NULL DEFAULT '',
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `product` (`product`),
  KEY `build` (`build`),
  KEY `FE5DF49DF0D94F4758` (`project`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_todo
-- ----------------------------
DROP TABLE IF EXISTS `zt_todo`;
CREATE TABLE `zt_todo` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `account` char(30) NOT NULL,
  `date` date DEFAULT '0000-00-00',
  `begin` smallint(4) unsigned zerofill NOT NULL,
  `end` smallint(4) unsigned zerofill NOT NULL,
  `type` char(10) NOT NULL,
  `cycle` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `idvalue` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `pri` tinyint(3) unsigned NOT NULL,
  `name` char(150) NOT NULL,
  `desc` text NOT NULL,
  `status` enum('wait','doing','done','closed') NOT NULL DEFAULT 'wait',
  `private` tinyint(1) NOT NULL,
  `config` varchar(255) NOT NULL,
  `assignedTo` varchar(30) NOT NULL DEFAULT '',
  `assignedBy` varchar(30) NOT NULL DEFAULT '',
  `assignedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `finishedBy` varchar(30) NOT NULL DEFAULT '',
  `finishedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `closedBy` varchar(30) NOT NULL DEFAULT '',
  `closedDate` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `account` (`account`),
  KEY `assignedTo` (`assignedTo`),
  KEY `finishedBy` (`finishedBy`),
  KEY `date` (`date`)
) ENGINE=InnoDB AUTO_INCREMENT=1292 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_user
-- ----------------------------
DROP TABLE IF EXISTS `zt_user`;
CREATE TABLE `zt_user` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `dept` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `account` char(30) NOT NULL DEFAULT '',
  `password` char(32) NOT NULL DEFAULT '',
  `role` char(10) NOT NULL DEFAULT '',
  `realname` varchar(100) NOT NULL DEFAULT '',
  `nickname` char(60) NOT NULL DEFAULT '',
  `commiter` varchar(100) NOT NULL,
  `avatar` char(30) NOT NULL DEFAULT '',
  `birthday` date DEFAULT '0000-00-00',
  `gender` enum('f','m') NOT NULL DEFAULT 'f',
  `email` char(90) NOT NULL DEFAULT '',
  `skype` char(90) NOT NULL DEFAULT '',
  `qq` char(20) NOT NULL DEFAULT '',
  `mobile` char(11) NOT NULL DEFAULT '',
  `phone` char(20) NOT NULL DEFAULT '',
  `weixin` varchar(90) NOT NULL DEFAULT '',
  `dingding` varchar(90) NOT NULL DEFAULT '',
  `slack` varchar(90) NOT NULL DEFAULT '',
  `whatsapp` varchar(90) NOT NULL DEFAULT '',
  `address` char(120) NOT NULL DEFAULT '',
  `zipcode` char(10) NOT NULL DEFAULT '',
  `join` date DEFAULT '0000-00-00',
  `visits` mediumint(8) unsigned NOT NULL DEFAULT 0,
  `ip` char(15) NOT NULL DEFAULT '',
  `last` int(10) unsigned NOT NULL DEFAULT 0,
  `fails` tinyint(5) NOT NULL DEFAULT 0,
  `locked` datetime DEFAULT '0000-00-00 00:00:00',
  `ranzhi` char(30) NOT NULL DEFAULT '',
  `score` int(11) NOT NULL DEFAULT 0,
  `scoreLevel` int(11) NOT NULL DEFAULT 0,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  `clientStatus` enum('online','away','busy','offline') NOT NULL DEFAULT 'offline',
  `clientLang` varchar(10) NOT NULL DEFAULT 'zh-cn',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`),
  KEY `dept` (`dept`),
  KEY `email` (`email`),
  KEY `commiter` (`commiter`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_usercontact
-- ----------------------------
DROP TABLE IF EXISTS `zt_usercontact`;
CREATE TABLE `zt_usercontact` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `account` char(30) NOT NULL,
  `listName` varchar(60) NOT NULL,
  `userList` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_usergroup
-- ----------------------------
DROP TABLE IF EXISTS `zt_usergroup`;
CREATE TABLE `zt_usergroup` (
  `account` char(30) NOT NULL DEFAULT '',
  `group` mediumint(8) unsigned NOT NULL DEFAULT 0,
  UNIQUE KEY `account` (`account`,`group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_userquery
-- ----------------------------
DROP TABLE IF EXISTS `zt_userquery`;
CREATE TABLE `zt_userquery` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `account` char(30) NOT NULL,
  `module` varchar(30) NOT NULL,
  `title` varchar(90) NOT NULL,
  `form` text NOT NULL,
  `sql` text NOT NULL,
  `shortcut` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `account` (`account`),
  KEY `module` (`module`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_usertpl
-- ----------------------------
DROP TABLE IF EXISTS `zt_usertpl`;
CREATE TABLE `zt_usertpl` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `account` char(30) NOT NULL,
  `type` char(30) NOT NULL,
  `title` varchar(150) NOT NULL,
  `content` text NOT NULL,
  `public` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_userview
-- ----------------------------
DROP TABLE IF EXISTS `zt_userview`;
CREATE TABLE `zt_userview` (
  `account` char(30) NOT NULL,
  `products` mediumtext NOT NULL,
  `projects` mediumtext NOT NULL,
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zt_webhook
-- ----------------------------
DROP TABLE IF EXISTS `zt_webhook`;
CREATE TABLE `zt_webhook` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(15) NOT NULL DEFAULT 'default',
  `name` varchar(50) NOT NULL,
  `url` varchar(255) NOT NULL,
  `domain` varchar(255) NOT NULL,
  `secret` varchar(255) NOT NULL,
  `contentType` varchar(30) NOT NULL DEFAULT 'application/json',
  `sendType` enum('sync','async') NOT NULL DEFAULT 'sync',
  `products` text NOT NULL,
  `projects` text NOT NULL,
  `params` varchar(100) NOT NULL,
  `actions` text NOT NULL,
  `desc` text NOT NULL,
  `createdBy` varchar(30) NOT NULL,
  `createdDate` datetime DEFAULT '0000-00-00 00:00:00',
  `editedBy` varchar(30) NOT NULL,
  `editedDate` datetime DEFAULT '0000-00-00 00:00:00',
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
