import { EntityBase } from 'ibiz-core';
import { IPSSysApp } from '../interface';

/**
 * 系统应用基类
 *
 * @export
 * @abstract
 * @class PSSysAppBase
 * @extends {EntityBase}
 * @implements {IPSSysApp}
 */
export abstract class PSSysAppBase extends EntityBase implements IPSSysApp {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof PSSysAppBase
     */
    get srfdename(): string {
        return 'PSSYSAPP';
    }
    get srfkey() {
        return this.pssysappid;
    }
    set srfkey(val: any) {
        this.pssysappid = val;
    }
    get srfmajortext() {
        return this.pssysappname;
    }
    set srfmajortext(val: any) {
        this.pssysappname = val;
    }
    /**
     * 启用统一认证
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    uaclogin?: 'yes' | 'no';
    /**
     * 前台技术架构
     */
    pspfid?: any;
    /**
     * 用户标记
     */
    usertag?: any;
    /**
     * 自动添加应用视图
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    autoaddappview?: 'yes' | 'no';
    /**
     * 启用本地服务
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    enalocalservice?: 'yes' | 'no';
    /**
     * 用户标记2
     */
    usertag2?: any;
    /**
     * 用户标记4
     */
    usertag4?: any;
    /**
     * 系统
     */
    pssystemid?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 只发布系统引用视图（废弃）
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    pubsysrefviewonly?: 'yes' | 'no';
    /**
     * 视图主菜单方向
     */
    mainmenuside?: any;
    /**
     * 系统应用标识
     */
    pssysappid?: any;
    /**
     * 代码目录
     */
    codefolder?: any;
    /**
     * 表格适应屏宽
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    gridforcefit?: 'yes' | 'no';
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 表单项无权限显示模式
     */
    finoprivdm?: any;
    /**
     * 自定义参数
     */
    userparams?: any;
    /**
     * 移动端方向设置
     */
    orientationmode?: any;
    /**
     * 备注
     */
    memo?: any;
    /**
     * 删除模式
     */
    removeflag?: any;
    /**
     * 启用故事板
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    enablestoryboard?: 'yes' | 'no';
    /**
     * 应用主题
     */
    psstudiothemename?: any;
    /**
     * 支持动态系统
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    enabledynasys?: 'yes' | 'no';
    /**
     * 是否启用
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    validflag?: 'yes' | 'no';
    /**
     * 图标文件
     */
    iconfile?: any;
    /**
     * 应用标记
     */
    apptag?: any;
    /**
     * 默认应用
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    defaultpub?: 'yes' | 'no';
    /**
     * 应用标记2
     */
    apptag2?: any;
    /**
     * 服务代码名称
     */
    servicecodename?: any;
    /**
     * 防止XSS攻击
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    preventxss?: 'yes' | 'no';
    /**
     * 应用类型
     */
    psapptypename?: any;
    /**
     * 应用CDN
     */
    pspfcdnid?: any;
    /**
     * 表格列启用链接
     */
    gridcolenablelink?: any;
    /**
     * 应用主题
     */
    psstudiothemeid?: any;
    /**
     * 转换12列至24列布局
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    enablec12toc24?: 'yes' | 'no';
    /**
     * 应用编号
     */
    appsn?: any;
    /**
     * 应用标记4
     */
    apptag4?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 表格行激活模式
     */
    gridrowactivemode?: any;
    /**
     * 用户标记3
     */
    usertag3?: any;
    /**
     * 代码名称
     */
    apppkgname?: any;
    /**
     * 中文名称
     */
    logicname?: any;
    /**
     * 应用样式
     */
    pspfstyleid?: any;
    /**
     * 按钮无权限显示模式
     */
    btnnoprivdm?: any;
    /**
     * 应用CDN
     */
    pspfcdnname?: any;
    /**
     * 只发布引用视图
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    pubrefviewonly?: 'yes' | 'no';
    /**
     * 应用模式
     */
    appmode?: any;
    /**
     * 系统应用名称
     */
    pssysappname?: any;
    /**
     * 应用类型
     */
    psapptypeid?: any;
    /**
     * 应用目录
     */
    appfolder?: any;
    /**
     * 起始页图片文件
     */
    startpagefile?: any;
    /**
     * 用户分类
     */
    usercat?: any;
    /**
     * 应用标记3
     */
    apptag3?: any;
    /**
     * 应用样式参数
     */
    pfstyleparam?: any;
    /**
     * 系统
     */
    pssystemname?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 内建界面式样
     */
    uistyle?: any;
    /**
     * 表格列无权限显示模式
     */
    gcnoprivdm?: any;
    /**
     * 输出表单项更新权限标记
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    fiupdateprivtag?: 'yes' | 'no';
    /**
     * 默认服务接口
     */
    pssysserviceapiname?: any;
    /**
     * 默认服务接口
     */
    pssysserviceapiid?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof PSSysAppBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.pssysappid = data.pssysappid || data.srfkey;
        this.pssysappname = data.pssysappname || data.srfmajortext;
    }
}
