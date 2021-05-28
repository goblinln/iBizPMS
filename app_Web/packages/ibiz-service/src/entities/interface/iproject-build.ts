import { IEntityBase } from 'ibiz-core';

/**
 * 版本
 *
 * @export
 * @interface IProjectBuild
 * @extends {IEntityBase}
 */
export interface IProjectBuild extends IEntityBase {
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * Bug版本健值
     */
    ids?: any;
    /**
     * 名称编号
     */
    name?: any;
    /**
     * 后台体系
     */
    backgroundid?: any;
    /**
     * 构建者
     */
    builder?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 附件
     */
    files?: any;
    /**
     * 运行模式
     *
     * @type {('DEPLOYPKG' | 'PACKMOBAPP' | 'PACKVER' | 'PUBCODE' | 'PUBCODE2' | 'STARTMSAPI' | 'STARTMSAPP' | 'STARTMSFUNC' | 'STARTX')} DEPLOYPKG: 部署系统组件到仓库, PACKMOBAPP: 打包移动应用, PACKVER: 打包版本, PUBCODE: 代码发布, PUBCODE2: 代码发布（模板开发）, STARTMSAPI: 启动微服务, STARTMSAPP: 启动微服务应用, STARTMSFUNC: 启动微服务功能, STARTX: 启动系统
     */
    releasetype?: 'DEPLOYPKG' | 'PACKMOBAPP' | 'PACKVER' | 'PUBCODE' | 'PUBCODE2' | 'STARTMSAPI' | 'STARTMSAPP' | 'STARTMSFUNC' | 'STARTX';
    /**
     * 构建者（选择）
     */
    builderpk?: any;
    /**
     * 重新构建
     *
     * @type {(0 | 1 | 4 | 2)} 0: 无操作, 1: 快速（删除本地项目与代码仓库多余文件）, 4: 修复模型, 2: 完整（完全重建本地项目及代码仓库）
     */
    rebuild?: 0 | 1 | 4 | 2;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 描述
     */
    desc?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 运行数据库
     */
    sqlid?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 源代码地址
     */
    scmpath?: any;
    /**
     * 下载地址
     */
    filepath?: any;
    /**
     * 产生的bug
     */
    createbugcnt?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 完成的需求
     */
    stories?: any;
    /**
     * 解决的Bug
     */
    bugs?: any;
    /**
     * 系统应用
     */
    frontapplication?: any;
    /**
     * 消息通知用户
     */
    noticeusers?: any;
    /**
     * 打包日期
     */
    date?: any;
    /**
     * 产品名称
     */
    productname?: any;
    /**
     * 产品
     */
    product?: any;
    /**
     * 平台/分支
     */
    branch?: any;
    /**
     * 所属项目
     */
    project?: any;
    /**
     * 版本编号
     */
    buildsn?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
}
