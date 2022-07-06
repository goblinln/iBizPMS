import { EntityBase } from 'ibiz-core';
import { IProductRelease } from '../interface';

/**
 * 发布基类
 *
 * @export
 * @abstract
 * @class ProductReleaseBase
 * @extends {EntityBase}
 * @implements {IProductRelease}
 */
export abstract class ProductReleaseBase extends EntityBase implements IProductRelease {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof ProductReleaseBase
     */
    get srfdename(): string {
        return 'ZT_RELEASE';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.name;
    }
    set srfmajortext(val: any) {
        this.name = val;
    }
    /**
     * 完成的需求
     */
    stories?: any;
    /**
     * 里程碑
     *
     * @type {('1' | '0')} 1: 是, 0: 否
     */
    marker?: '1' | '0';
    /**
     * 归属组织
     */
    org?: any;
    /**
     * ID
     */
    id?: any;
    /**
     * 发布编号
     */
    releasesn?: any;
    /**
     * 运行数据库
     */
    sqlid?: any;
    /**
     * 遗留的Bug
     */
    leftbugs?: any;
    /**
     * 解决的Bug
     */
    bugs?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 消息通知用户
     */
    noticeusers?: any;
    /**
     * 重新构建
     *
     * @type {(0 | 1 | 4 | 2)} 0: 无操作, 1: 快速（删除本地项目与代码仓库多余文件）, 4: 修复模型, 2: 完整（完全重建本地项目及代码仓库）
     */
    rebuild?: 0 | 1 | 4 | 2;
    /**
     * 发布名称
     */
    name?: any;
    /**
     * 发布日期
     */
    date?: any;
    /**
     * 状态
     *
     * @type {('normal' | 'terminate')} normal: 正常, terminate: 停止维护
     */
    status?: 'normal' | 'terminate';
    /**
     * 运行模式
     *
     * @type {('DEPLOYPKG' | 'PACKMOBAPP' | 'PACKVER' | 'PUBCODE' | 'PUBCODE2' | 'STARTMSAPI' | 'STARTMSAPP' | 'STARTMSFUNC' | 'STARTX')} DEPLOYPKG: 部署系统组件到仓库, PACKMOBAPP: 打包移动应用, PACKVER: 打包版本, PUBCODE: 代码发布, PUBCODE2: 代码发布（模板开发）, STARTMSAPI: 启动微服务, STARTMSAPP: 启动微服务应用, STARTMSFUNC: 启动微服务功能, STARTX: 启动系统
     */
    releasetype?: 'DEPLOYPKG' | 'PACKMOBAPP' | 'PACKVER' | 'PUBCODE' | 'PUBCODE2' | 'STARTMSAPI' | 'STARTMSAPP' | 'STARTMSFUNC' | 'STARTX';
    /**
     * 子状态
     */
    substatus?: any;
    /**
     * 后台体系
     */
    backgroundid?: any;
    /**
     * 描述
     */
    desc?: any;
    /**
     * 附件
     */
    files?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 系统应用
     */
    frontapplication?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 产品名称
     */
    productname?: any;
    /**
     * 构建者
     */
    builder?: any;
    /**
     * 版本
     */
    buildname?: any;
    /**
     * 打包日期
     */
    builddate?: any;
    /**
     * 产品
     */
    product?: any;
    /**
     * 版本
     */
    build?: any;
    /**
     * 平台/分支
     */
    branch?: any;
    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof ProductReleaseBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
