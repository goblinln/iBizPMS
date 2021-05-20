import { EntityBase } from 'ibiz-core';
import { IProduct } from '../interface';

/**
 * 产品基类
 *
 * @export
 * @abstract
 * @class ProductBase
 * @extends {EntityBase}
 * @implements {IProduct}
 */
export abstract class ProductBase extends EntityBase implements IProduct {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof ProductBase
     */
    get srfdename(): string {
        return 'ZT_PRODUCT';
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
     * 组织标识
     */
    orgid?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 是否置顶
     */
    istop?: any;
    /**
     * 备注
     */
    comment?: any;
    /**
     * 测试负责人
     */
    qd?: any;
    /**
     * 产品分类
     */
    productclass?: any;
    /**
     * 未确认Bug数
     */
    unconfirmbugcnt?: any;
    /**
     * 访问控制
     *
     * @type {('open' | 'private' | 'custom')} open: 默认设置(有产品视图权限，即可访问), private: 私有产品(相关负责人和项目团队成员才能访问), custom: 自定义白名单(团队成员和白名单的成员可以访问)
     */
    acl?: 'open' | 'private' | 'custom';
    /**
     * 产品名称
     */
    name?: any;
    /**
     * 移动端图片
     */
    mobimage?: any;
    /**
     * 测试单数
     */
    testtaskcnt?: any;
    /**
     * 套件数
     */
    testsuitecnt?: any;
    /**
     * 计划总数
     */
    productplancnt?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 已关闭需求
     */
    closedstorycnt?: any;
    /**
     * 相关Bug数
     */
    relatedbugcnt?: any;
    /**
     * 分组白名单
     */
    whitelist?: any;
    /**
     * 部门标识
     */
    mdeptid?: any;
    /**
     * 发布总数
     */
    releasecnt?: any;
    /**
     * 发布负责人
     */
    rd?: any;
    /**
     * 产品负责人（选择）
     */
    popk?: any;
    /**
     * 未关闭Bug数
     */
    notclosedbugcnt?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 支持产品汇报
     *
     * @type {('1' | '0')} 1: 是, 0: 否
     */
    supproreport?: '1' | '0';
    /**
     * 排序
     */
    order?: any;
    /**
     * 产品类型
     *
     * @type {('normal' | 'branch' | 'platform')} normal: 正常, branch: 多分支, platform: 多平台
     */
    type?: 'normal' | 'branch' | 'platform';
    /**
     * 归属部门名
     */
    mdeptname?: any;
    /**
     * 产品负责人
     */
    po?: any;
    /**
     * 测试负责人（选择）
     */
    qdpk?: any;
    /**
     * 产品描述	
     */
    desc?: any;
    /**
     * 状态
     *
     * @type {('normal' | 'closed')} normal: 正常, closed: 结束
     */
    status?: 'normal' | 'closed';
    /**
     * 已变更需求
     */
    changedstorycnt?: any;
    /**
     * 未解决Bug数
     */
    activebugcnt?: any;
    /**
     * 由谁创建
     */
    createdby?: any;
    /**
     * 发布负责人（选择）
     */
    rdpk?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 当前系统版本
     */
    createdversion?: any;
    /**
     * 草稿需求
     */
    draftstorycnt?: any;
    /**
     * 文档数
     */
    doccnt?: any;
    /**
     * 用例数
     */
    casecnt?: any;
    /**
     * 关联项目数
     */
    relatedprojects?: any;
    /**
     * IBIZ标识
     */
    ibiz_id?: any;
    /**
     * 子状态
     */
    substatus?: any;
    /**
     * 产品代号
     */
    code?: any;
    /**
     * 属性
     */
    srfcount?: any;
    /**
     * 排序
     */
    order1?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * BUILD数
     */
    buildcnt?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 创建日期
     */
    createddate?: any;
    /**
     * 消息通知用户
     */
    noticeusers?: any;
    /**
     * 激活需求数
     */
    activestorycnt?: any;
    /**
     * 产品编号
     */
    productsn?: any;
    /**
     * id
     */
    line?: any;
    /**
     * 产品线名称
     */
    linename?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof ProductBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
