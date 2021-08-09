import { IPSControl, IPSTabExpPanel } from '@ibiz/dynamic-model-api';
import { TabExpPanelControlInterface, Util, ViewTool } from 'ibiz-core';
import { MainControlBase } from './main-control-base';
/**
 * 分页导航面板部件基类
 *
 * @export
 * @class TabExpPanelBase
 * @extends {MainControlBase}
 */
export class TabExpPanelBase extends MainControlBase implements TabExpPanelControlInterface{

    /**
     * 分页导航部件实例对象
     *
     * @type {*}
     * @memberof TabExpPanelBase
     */
    public controlInstance!: IPSTabExpPanel;

    /**
     * 实体权限服务对象
     *
     * @type 
     * @memberof TabExpPanelBase
     */
    public appAuthService: any;

    /**
     * 是否初始化
     *
     * @returns {any}
     * @memberof TabExpPanelBase
     */
    public isInit: any = {}

    /**
     * 行为参数
     *
     * @public
     * @type {*}
     * @memberof TabExpPanelBase
     */
    public action: any = '';

     /**
      * 当前激活数据
      *
      * @public
      * @type {*}
      * @memberof TabExpPanelBase
      */
    public activeData: any = {};
 
     /**
      * 分页面板权限标识存储对象
      *
      * @public
      * @type {*}
      * @memberof TabExpPanelBase
      */
    public authResourceObject: any = {};
 
     /**
      * 被激活的分页面板
      *
      * @type {string}
      * @memberof TabExpPanelBase
      */
    public activiedTabViewPanel: string = '';

    /**
     * 部件模型数据初始化实例
     *
     * @param {*} [args]
     * @memberof TabExpPanelBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit();
        const allControls: IPSControl[] = this.controlInstance.getPSControls() as IPSControl[];
        if (allControls.length > 0) {
            this.activiedTabViewPanel = allControls[0].name;
        }
    }

    /**
     * 执行created后的逻辑
     *
     *  @memberof TabExpPanelBase
     */
    public ctrlInit() {
        //设置分页导航srfparentdename和srfparentkey
        if (this.context[this.appDeCodeName.toLowerCase()]) {
            Object.assign(this.context, { srfparentdename: this.appDeCodeName.toLowerCase(),srfparentdemapname: this.deName,srfparentkey: this.context[this.appDeCodeName.toLowerCase()] })
        }
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: { tag: string, action: string, data: any }) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                if (Object.is(action, 'changeActivedTab')) {
                    this.tabPanelClick(data);
                }
                if (Object.is(action, 'loadmodel')) {
                    this.activeData = data;
                    this.computedAuthPanel(data);
                } else {
                    this.action = action;
                    this.viewState.next({ tag: this.activiedTabViewPanel, action: action, data: data });
                    this.$forceUpdate();
                }
            });
        }
    }

    /**
     * 部件挂载
     *
     *  @memberof TabExpPanelBase
     */
    public ctrlMounted() {
        super.ctrlMounted();
        if (this.viewparams && (this.viewparams.srftabactivate || this.viewparams.srfnavtag)) {
            const activate = this.viewparams.srftabactivate?.toLowerCase() || this.viewparams.srfnavtag?.toLowerCase();
            if (activate && this.isInit[activate] !== undefined) {
                for (const key in this.isInit) {
                    if (this.isInit.hasOwnProperty(key)) {
                        this.isInit[key] = false;
                    }
                }
                this.$nextTick(() => {
                    this.tabPanelClick(activate);
                });
            }
        }
        this.ctrlEvent({ controlname: this.controlInstance.name, action: 'tabexppanelIsMounted', data: {
            activiedTabViewPanel: this.activiedTabViewPanel,
            authResourceObject: this.authResourceObject
        }})
    }

    /**
     * 分页视图面板数据变更
     *
     * @memberof TabExpPanelBase
     */
    public tabViewPanelDatasChange() {
        this.counterRefresh();
    }

    /**
     * 分页面板选中
     *
     * @param {*} $event 选中分页
     * @returns
     * @memberof TabExpPanelBase
     */
    public tabPanelClick($event: any) {
        if (!$event ||  Object.is(this.activiedTabViewPanel, $event)) {
            return;
        }
        this.isInit = [];
        this.isInit[$event] = true;
        if (!this.viewState) {
            return;
        }
        this.activiedTabViewPanel = $event;
        this.ctrlEvent({ controlname: this.controlInstance.name, action: 'viewPanelIsChange', data: this.activiedTabViewPanel });
        this.viewState.next({ tag: this.activiedTabViewPanel, action: this.action, data: this.activeData });
    }

    /**
     * 部件事件
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * 
     * @memberof TabExpPanelBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if (Object.is(controlname, 'tabviewpanel') && action == 'viewpanelDatasChange') {
            this.tabViewPanelDatasChange();
        }else{
            super.onCtrlEvent(controlname, action, data);
        }
    }
    
    /**
     * 初始化解析分页导航面板绘制参数
     *
     * @memberof TabExpPanelBase
     */
    public initRenderOptions() {
        super.initRenderOptions();
        const allControls: IPSControl[] = this.controlInstance.getPSControls() as IPSControl[];
        this.authResourceObject = [];
        allControls.forEach((item: any, index: number) => {
            this.authResourceObject[item.name] = {
                resourcetag: "",
                visabled: true,
                disabled: false
            }
            this.isInit[item.name] = index === 0 ? true : false;
        });
    }
    
    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof TabExpPanelBase
     */
    public getDatas(): any[] {
        return [];
    }

    /**
     * 获取单项数据
     *
     * @returns {*}
     * @memberof TabExpPanelBase
     */
    public getData(): any {
        return null;
    }

    /**
     * 计算分页面板权限
     *
     * @memberof TabExpPanelBase
     */
    public computedAuthPanel(data: any) {
        const _this: any = this;
        if (!data || Object.keys(data).length === 0) {
            return;
        }
        if (this.authResourceObject && Object.keys(this.authResourceObject).length > 0) {
            Object.keys(this.authResourceObject).forEach((key: string) => {
                if (this.authResourceObject[key] && this.authResourceObject[key]['dataaccaction']) {
                    let tempUIAction: any = Util.deepCopy(this.authResourceObject[key]);
                    ViewTool.calcActionItemAuthState(data, [tempUIAction], _this.appUIService);
                    this.authResourceObject[key].visabled = this.computedPanelWithResource(key, tempUIAction.visabled);
                    this.authResourceObject[key].disabled = this.computedPanelWithResource(key, tempUIAction.disabled);
                }
            })
            const keys: any = Object.keys(this.authResourceObject);
            for (let i = 0; i < keys.length; i++) {
                if (this.authResourceObject[keys[i]].visabled) {
                    // this.tabPanelClick(keys[i]);
                    return;
                }
            }
        }
    }

    /**
     * 合入统一资源权限
     *
     * @memberof TabExpPanelBase
     */
    public computedPanelWithResource(name: string, mainState: boolean) {
        if (!this.$store.getters['authresource/getEnablePermissionValid'])
            return mainState === false ? false : true;
        if (!this.authResourceObject[name])
            return mainState === false ? false : true;
        const resourceAuth: boolean = this.appAuthService.getResourcePermission(this.authResourceObject[name]['resourcetag']);
        return !resourceAuth ? false : mainState ? true : false;
    }
}
