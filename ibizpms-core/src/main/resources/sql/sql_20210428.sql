ALTER TABLE `zt_storyspec`
ADD COLUMN `ID`  VARCHAR(60)  COMMENT '主键' ;

update zt_storyspec set id = MD5(CONCAT(story,'__',version)) where id is null ;
COMMIT;

ALTER TABLE `zt_storyspec` 
MODIFY COLUMN `ID` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
ADD PRIMARY KEY (`ID`);

ALTER TABLE `zt_storystage`
ADD COLUMN `ID`  VARCHAR(60)  COMMENT '主键' ;

update zt_storystage set id = MD5(CONCAT(STORY,'__',BRANCH)) where id is null ;
COMMIT;

ALTER TABLE `zt_storystage` 
MODIFY COLUMN `ID` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
ADD PRIMARY KEY (`ID`);

ALTER TABLE `zt_projectstory`
ADD COLUMN `ID`  VARCHAR(60)  COMMENT '主键' ;

update ZT_PROJECTSTORY set id = MD5(CONCAT(PROJECT,'__',STORY)) where id is null ;
COMMIT;

ALTER TABLE `ZT_PROJECTSTORY` 
MODIFY COLUMN `ID` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
ADD PRIMARY KEY (`ID`);

ALTER TABLE `zt_projectproduct`
ADD COLUMN `ID`  VARCHAR(60)  COMMENT '主键' ;

update ZT_PROJECTPRODUCT set id = MD5(CONCAT(PROJECT,'__',PRODUCT)) where id is null ;
COMMIT;

ALTER TABLE `zt_projectproduct` 
DROP PRIMARY KEY;

ALTER TABLE `ZT_PROJECTPRODUCT` 
MODIFY COLUMN `ID` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
ADD PRIMARY KEY (`ID`);

ALTER TABLE `zt_suitecase`
ADD COLUMN `ID`  VARCHAR(60)  COMMENT '主键' ;

update ZT_SUITECASE set id = MD5(CONCAT(SUITE,'__',`CASE`)) where id is null ;
COMMIT;

ALTER TABLE `ZT_SUITECASE` 
MODIFY COLUMN `ID` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
ADD PRIMARY KEY (`ID`);

ALTER TABLE `zt_burn`
ADD COLUMN `ID`  VARCHAR(60)  COMMENT '主键' ;

update ZT_BURN set id = MD5(CONCAT(PROJECT,'__',`TASK`, '--', `DATE`)) where id is null ;
COMMIT;

ALTER TABLE `zt_burn` 
DROP PRIMARY KEY;

ALTER TABLE `ZT_BURN` 
MODIFY COLUMN `ID` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
ADD PRIMARY KEY (`ID`);

ALTER TABLE `zt_repobranch`
ADD COLUMN `ID`  VARCHAR(60)  COMMENT '主键' ;

update ZT_REPOBRANCH set id = MD5(CONCAT(REPO,'__',`REVISION`, '--', `BRANCH`)) where id is null ;
COMMIT;

ALTER TABLE `ZT_REPOBRANCH` 
MODIFY COLUMN `ID` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
ADD PRIMARY KEY (`ID`);

ALTER TABLE `zt_userview`
ADD COLUMN `ID`  VARCHAR(60)  COMMENT '主键' ;

update ZT_USERVIEW set id = MD5(CONCAT(ACCOUNT)) where id is null ;
COMMIT;

ALTER TABLE `ZT_USERVIEW` 
MODIFY COLUMN `ID` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
ADD PRIMARY KEY (`ID`);

ALTER TABLE `zt_usergroup`
ADD COLUMN `ID`  VARCHAR(60)  COMMENT '主键' ;

update ZT_USERGROUP set id = MD5(CONCAT(ACCOUNT, '--', `GROUP`)) where id is null ;
COMMIT;

ALTER TABLE `ZT_USERGROUP` 
MODIFY COLUMN `ID` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
ADD PRIMARY KEY (`ID`);

ALTER TABLE `zt_grouppriv`
ADD COLUMN `ID`  VARCHAR(60)  COMMENT '主键' ;

update ZT_GROUPPRIV set id = MD5(CONCAT(`GROUP`, '--', `MODULE`,'--', `METHOD`)) where id is null ;
COMMIT;

ALTER TABLE `ZT_GROUPPRIV` 
MODIFY COLUMN `ID` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
ADD PRIMARY KEY (`ID`);

ALTER TABLE `zt_im_messagestatus`
ADD COLUMN `ID`  VARCHAR(60)  COMMENT '主键' ;

update ZT_IM_MESSAGESTATUS set id = MD5(CONCAT(`USER`, '--', `MESSAGE`)) where id is null ;
COMMIT;

ALTER TABLE `ZT_IM_MESSAGESTATUS` 
MODIFY COLUMN `ID` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
ADD PRIMARY KEY (`ID`);