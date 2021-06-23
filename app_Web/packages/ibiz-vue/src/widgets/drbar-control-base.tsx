import { IPSAppDEEditView, IPSAppView, IPSAppViewRef, IPSDEDRBar, IPSDEDRCtrlItem, IPSDEEditForm } from '@ibiz/dynamic-model-api';
import { DrbarControlInterface, ModelTool, Util } from "ibiz-core";
import { MainControlBase } from "./main-control-base";

/**
 * 数据关系栏部件基类
 *
 * @export
 * @class DrbarControlBase
 * @extends {MainControlBase}
 */
export class DrbarControlBase extends MainControlBase implements DrbarControlInterface{

    /**
     * 数据关系栏部件实例对象
     * 
     * @type {IPSDEDRBar}
     * @memberof DrbarControlBase
     */
    public controlInstance!: IPSDEDRBar;

    /**
     * 表单实例事项
     * 
     * @type {IPSDEEditForm}
     * @memberof DrbarControlBase
     */
    public formInstance?: IPSDEEditForm;

    /**
     * 表单名称
     * 
     * @type {string}
     * @memberof DrbarControlBase
     */
    public formName: string = '';

    /**
     * 数据选中项
     *
     * @type {*}
     * @memberof DrbarControlBase
     */
    public selection: any = {};

    /**
     * 关系栏数据项
     * 
     * @type {any[]}
     * @memberof DrbarControlBase
     */
    public items: any[] = [];

    /**
     * 关系栏数据项导航参数集合
     * 
     * @type {any[]}
     * @memberof DrbarControlBase
     */
    public navParamsArray: any[] = [];

    /**
     * 默认打开项
     * 
     * @type {string[]}
     * @memberof DrbarControlBase
     */
    public defaultOpeneds: string[] = [];

    /**
     * 父数据
     * 
     * @type {*}
     * @memberof DrbarControlBase
     */
    public parentData: any = {};

    /**
     * 宽度
     * 
     * @type {number}
     * @memberof DrbarControlBase
     */
    public width: number = 240;

    /**
     * 表单数据
     * 
     * @type {*}
     * @memberof DrbarControlBase
     */
    public formData: any;

    /**
     * 监听静态参数变化
     * 
     * @memberof DrbarControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.formData = newVal.formData;
        this.formInstance = newVal.formInstance as IPSDEEditForm;
        this.formName = this.formInstance?.name?.toLowerCase();
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
     * 部件模型初始化
     * 
     * @memberof DrbarControlBase
     */
    public async ctrlModelInit() {
        await super.ctrlModelInit();
        this.width = this.controlInstance.width >= 240 ? this.controlInstance.width : 240;
        this.initItems();
        this.initNavParams();
    }

    /**
     * 部件初始化
     * 
     * @memberof DrbarControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                if (Object.is('state', action)) {
                    const state = !this.context[this.appDeCodeName.toLowerCase()] ? true : false;
                    this.setItemDisabled(this.items, state);
                }
            });
        }
        this.onSelect(this.items[0].id);
    }

    /**
     * 初始化关系栏数据项
     * 
     * @memberof DrbarControlBase
     */
    public initItems() {
        this.items = [];
        if (this.formInstance) {
            this.items.push({
                text: this.formInstance.logicName,
                disabled: false,
                id: this.formName
            })
        }
        const ctrlItems: Array<IPSDEDRCtrlItem> = this.controlInstance.getPSDEDRCtrlItems() || [];
        ctrlItems.forEach((item: IPSDEDRCtrlItem) => {
            this.items.push({
                text: this.$tl(item.getCapPSLanguageRes()?.lanResTag, item.caption),
                disabled: false,
                id: item.name?.toLowerCase(),
                iconcls: (item as any).getPSSysImage?.()?.cssClass,
                icon: (item as any).getPSSysImage?.()?.imagePath
            })
        })
    }

    /**
     * 初始化关系栏数据项导航参数
     * 
     * @memberof DrbarControlBase
     */
    public initNavParams() {
        const ctrlItems: Array<IPSDEDRCtrlItem> = this.controlInstance.getPSDEDRCtrlItems() || [];
        ctrlItems.forEach((item: IPSDEDRCtrlItem) => {
            this.navParamsArray.push({
                id: item.name?.toLowerCase(),
                localContext: ModelTool.getNavigateContext(item),
                localViewParam: ModelTool.getNavigateParams(item)
            })
        })
    }

    /**
     * 获取关系项
     *
     * @public
     * @param {*} [arg={}]
     * @returns {*}
     * @memberof DrbarControlBase
     */
    public getDRBarItem(arg: any = {}): any {
        let expmode = arg.nodetype;
        if (!expmode) {
            expmode = '';
        }
        let item: any = undefined;
        const ctrlItems: Array<IPSDEDRCtrlItem> = this.controlInstance.getPSDEDRCtrlItems() || [];
        ctrlItems.forEach((_item: IPSDEDRCtrlItem) => {
            if (Object.is(expmode, _item.name?.toLowerCase())) {
                const viewRef = _item.getPSAppView?.() as IPSAppView;
                item = {
                    viewModelData: viewRef,
                }
            }
        })
        return item;
    }

    /**
     * 处理数据
     *
     * @public
     * @param {any[]} items
     * @memberof DrbarControlBase
     */
    public dataProcess(items: any[]): void {
        items.forEach((_item: any) => {
            if (_item.expanded) {
                this.defaultOpeneds.push(_item.id);
            }
            _item.disabled = false;
            if (_item.items && Array.isArray(_item.items) && _item.items.length > 0) {
                this.dataProcess(_item.items);
            }
        });
    }

    /**
     * 获取子项
     *
     * @param {any[]} items
     * @param {string} id
     * @returns {*}
     * @memberof DrbarControlBase
     */
    public getItem(items: any[], id: string): any {
        const item: any = {};
        items.some((_item: any) => {
            if (Object.is(_item.id, id)) {
                Object.assign(item, _item);
                return true;
            }
            if (_item.items && _item.items.length > 0) {
                const subItem = this.getItem(_item.items, id);
                if (Object.keys(subItem).length > 0) {
                    Object.assign(item, subItem);
                    return true;
                }
            }
            return false;
        });
        return item;
    }

    /**
     * 初始化导航参数
     *
     * @param {*} drItem
     * @memberof DrbarControlBase
     */
    public initNavParam(drItem:any){
        let returnNavParam:any = {};
        if(drItem && drItem.id){
            let curDRItem:any = this.navParamsArray.find((item:any) =>{
                return Object.is(item.id,drItem.id);
            })
            if(curDRItem){
                let localContext:any = curDRItem.localContext;
                let localViewParam:any = curDRItem.localViewParam;
                if(localContext && Object.keys(localContext).length >0){
                    let _context:any = this.$util.computedNavData(this.formData,this.context,this.viewparams,localContext);
                    returnNavParam.localContext = _context;
                }
                if(localViewParam && Object.keys(localViewParam).length >0){
                    let _params:any = this.$util.computedNavData(this.formData,this.context,this.viewparams,localViewParam);
                    returnNavParam.localViewParam = _params;
                }
                return returnNavParam;
            }else{
                return null;
            }
        }
    }

    /**
     * 节点选中
     *
     * @param {*} $event
     * @memberof DrbarControlBase
     */
    public onSelect($event: any): void {
        const item = this.getItem(this.items, $event);
        if (Object.is(item.id, this.selection.id)) {
            return;
        }
        this.ctrlEvent({ controlname: this.controlInstance.name, action: 'selectionchange', data: [item] })
        let localNavParam:any = this.initNavParam(item);
        const refview = this.getDRBarItem({ nodetype: item.id });
        this.selection = {};
        const _context: any = { ...JSON.parse(JSON.stringify(this.context)) };
        if(localNavParam && localNavParam.localContext){
            Object.assign(_context,localNavParam.localContext);
        }
        const _params: any = {};
        if(localNavParam && localNavParam.localViewParam){
            Object.assign(_params,localNavParam.localViewParam);
        }
        if (refview && refview.viewModelData) {
            Object.assign(this.selection, { view: refview.viewModelData, data: _context, param: _params });
        }
        Object.assign(this.selection, item);
    }

    /**
     * 子节点打开
     *
     * @param {*} $event
     * @memberof DrbarControlBase
     */
    public onOpen($event: any): void {
        const item = this.getItem(this.items, $event);
        if (Object.is(item.id, this.selection.id)) {
            return;
        }
        this.selection = {};
        Object.assign(this.selection, item);
        if (Object.is(item.id, this.formName) || (item.viewname && !Object.is(item.viewname, ''))) {
            this.ctrlEvent({ controlname: this.controlInstance.name, action: 'selectionchange', data: [this.selection] });
        }
    }

    /**
     * 子节点关闭
     *
     * @param {*} $event
     * @memberof DrbarControlBase
     */
    public onClose($event: any): void {
        const item = this.getItem(this.items, $event);
        if (Object.is(item.id, this.selection.id)) {
            return;
        }
        this.selection = {};
        Object.assign(this.selection, item);
        if (Object.is(item.id, this.formName) || (item.viewname && !Object.is(item.viewname, ''))) {
            this.ctrlEvent({ controlname: this.controlInstance.name, action: 'selectionchange', data: [this.selection] });
        }
    }

    /**
     * 设置关系项状态
     *
     * @param {any[]} items
     * @param {boolean} state
     * @memberof DrbarControlBase
     */
    public setItemDisabled(items: any[], state: boolean) {
        items.forEach((item: any) => {
            if (!Object.is(item.id, this.formName)) {
                item.disabled = state;
            }
            if (item.items && Array.isArray(item.items)) {
                this.setItemDisabled(item.items, state);
            }
        });
    }

}
