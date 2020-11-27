import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { UIActionTool, Util, ViewTool } from '@/utils';
import { Watch, MainControlBase } from '@/studio-core';
import DocLibService from '@/service/doc-lib/doc-lib-service';
import DoclibChildTreeViewService from './doclib-child-tree-view-treeview-service';
import DocUIService from '@/uiservice/doc/doc-ui-service';
import DocLibModuleUIService from '@/uiservice/doc-lib-module/doc-lib-module-ui-service';
import DocLibUIService from '@/uiservice/doc-lib/doc-lib-ui-service';

/**
 * tree部件基类
 *
 * @export
 * @class MainControlBase
 * @extends {DoclibChildTreeViewTreeBase}
 */
export class DoclibChildTreeViewTreeBase extends MainControlBase {
    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof DoclibChildTreeViewTreeBase
     */
    protected controlType: string = 'TREEVIEW';

    /**
     * 建构部件服务对象
     *
     * @type {DoclibChildTreeViewService}
     * @memberof DoclibChildTreeViewTreeBase
     */
    public service: DoclibChildTreeViewService = new DoclibChildTreeViewService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {DocLibService}
     * @memberof DoclibChildTreeViewTreeBase
     */
    public appEntityService: DocLibService = new DocLibService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof DoclibChildTreeViewTreeBase
     */
    protected appDeName: string = 'doclib';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof DoclibChildTreeViewTreeBase
     */
    protected appDeLogicName: string = '文档库';

    /**
     * 界面UI服务对象
     *
     * @type {DocLibUIService}
     * @memberof DoclibChildTreeViewBase
     */  
    public appUIService: DocLibUIService = new DocLibUIService(this.$store);

    /**
     * childmodule_cm 部件 click 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof DoclibChildTreeViewTreeBase
     */
    public childmodule_cm_click($event: any, $event2?: any) {
        if (Object.is($event.tag, 'deuiaction1')) {
            this.childmodule_cm_deuiaction1_click(null, 'childmodule_cm', $event2);
        }
        if (Object.is($event.tag, 'deuiaction4')) {
            this.childmodule_cm_deuiaction4_click(null, 'childmodule_cm', $event2);
        }
        if (Object.is($event.tag, 'deuiaction2')) {
            this.childmodule_cm_deuiaction2_click(null, 'childmodule_cm', $event2);
        }
        if (Object.is($event.tag, 'deuiaction3')) {
            this.childmodule_cm_deuiaction3_click(null, 'childmodule_cm', $event2);
        }
    }

    /**
     * rootmodule_cm 部件 click 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof DoclibChildTreeViewTreeBase
     */
    public rootmodule_cm_click($event: any, $event2?: any) {
        if (Object.is($event.tag, 'deuiaction1')) {
            this.rootmodule_cm_deuiaction1_click(null, 'rootmodule_cm', $event2);
        }
        if (Object.is($event.tag, 'deuiaction4')) {
            this.rootmodule_cm_deuiaction4_click(null, 'rootmodule_cm', $event2);
        }
        if (Object.is($event.tag, 'deuiaction2')) {
            this.rootmodule_cm_deuiaction2_click(null, 'rootmodule_cm', $event2);
        }
        if (Object.is($event.tag, 'deuiaction3')) {
            this.rootmodule_cm_deuiaction3_click(null, 'rootmodule_cm', $event2);
        }
    }

    /**
     * doclibdoc_cm 部件 click 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof DoclibChildTreeViewTreeBase
     */
    public doclibdoc_cm_click($event: any, $event2?: any) {
        if (Object.is($event.tag, 'deuiaction1')) {
            this.doclibdoc_cm_deuiaction1_click(null, 'doclibdoc_cm', $event2);
        }
        if (Object.is($event.tag, 'deuiaction4')) {
            this.doclibdoc_cm_deuiaction4_click(null, 'doclibdoc_cm', $event2);
        }
        if (Object.is($event.tag, 'deuiaction2')) {
            this.doclibdoc_cm_deuiaction2_click(null, 'doclibdoc_cm', $event2);
        }
        if (Object.is($event.tag, 'deuiaction3')) {
            this.doclibdoc_cm_deuiaction3_click(null, 'doclibdoc_cm', $event2);
        }
    }

    /**
     * moduledoc_cm 部件 click 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof DoclibChildTreeViewTreeBase
     */
    public moduledoc_cm_click($event: any, $event2?: any) {
        if (Object.is($event.tag, 'deuiaction1')) {
            this.moduledoc_cm_deuiaction1_click(null, 'moduledoc_cm', $event2);
        }
        if (Object.is($event.tag, 'deuiaction4')) {
            this.moduledoc_cm_deuiaction4_click(null, 'moduledoc_cm', $event2);
        }
        if (Object.is($event.tag, 'deuiaction2')) {
            this.moduledoc_cm_deuiaction2_click(null, 'moduledoc_cm', $event2);
        }
        if (Object.is($event.tag, 'deuiaction3')) {
            this.moduledoc_cm_deuiaction3_click(null, 'moduledoc_cm', $event2);
        }
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public doclibdoc_cm_deuiaction1_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:DocUIService  = new DocUIService();
        curUIService.Doc_Edit(datas,contextJO, paramJO,  $event, xData,this,"DocLib");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public doclibdoc_cm_deuiaction4_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:DocUIService  = new DocUIService();
        curUIService.Doc_Delete(datas,contextJO, paramJO,  $event, xData,this,"DocLib");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public doclibdoc_cm_deuiaction2_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:DocUIService  = new DocUIService();
        curUIService.Doc_OnlyCollectDoc(datas,contextJO, paramJO,  $event, xData,this,"DocLib");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public doclibdoc_cm_deuiaction3_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:DocUIService  = new DocUIService();
        curUIService.Doc_OnlyUnCollectDoc(datas,contextJO, paramJO,  $event, xData,this,"DocLib");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public rootmodule_cm_deuiaction1_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:DocLibModuleUIService  = new DocLibModuleUIService();
        curUIService.DocLibModule_edit(datas,contextJO, paramJO,  $event, xData,this,"DocLib");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public rootmodule_cm_deuiaction4_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:DocLibModuleUIService  = new DocLibModuleUIService();
        curUIService.DocLibModule_WeiHuFenLei(datas,contextJO, paramJO,  $event, xData,this,"DocLib");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public rootmodule_cm_deuiaction2_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:DocLibModuleUIService  = new DocLibModuleUIService();
        curUIService.DocLibModule_Favorite(datas,contextJO, paramJO,  $event, xData,this,"DocLib");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public rootmodule_cm_deuiaction3_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:DocLibModuleUIService  = new DocLibModuleUIService();
        curUIService.DocLibModule_NFavorite(datas,contextJO, paramJO,  $event, xData,this,"DocLib");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public childmodule_cm_deuiaction1_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:DocLibModuleUIService  = new DocLibModuleUIService();
        curUIService.DocLibModule_edit(datas,contextJO, paramJO,  $event, xData,this,"DocLib");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public childmodule_cm_deuiaction4_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:DocLibModuleUIService  = new DocLibModuleUIService();
        curUIService.DocLibModule_WeiHuFenLei(datas,contextJO, paramJO,  $event, xData,this,"DocLib");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public childmodule_cm_deuiaction2_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:DocLibModuleUIService  = new DocLibModuleUIService();
        curUIService.DocLibModule_Favorite(datas,contextJO, paramJO,  $event, xData,this,"DocLib");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public childmodule_cm_deuiaction3_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:DocLibModuleUIService  = new DocLibModuleUIService();
        curUIService.DocLibModule_NFavorite(datas,contextJO, paramJO,  $event, xData,this,"DocLib");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public moduledoc_cm_deuiaction1_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:DocUIService  = new DocUIService();
        curUIService.Doc_Edit(datas,contextJO, paramJO,  $event, xData,this,"DocLib");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public moduledoc_cm_deuiaction4_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:DocUIService  = new DocUIService();
        curUIService.Doc_Delete(datas,contextJO, paramJO,  $event, xData,this,"DocLib");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public moduledoc_cm_deuiaction2_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:DocUIService  = new DocUIService();
        curUIService.Doc_OnlyCollectDoc(datas,contextJO, paramJO,  $event, xData,this,"DocLib");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public moduledoc_cm_deuiaction3_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:DocUIService  = new DocUIService();
        curUIService.Doc_OnlyUnCollectDoc(datas,contextJO, paramJO,  $event, xData,this,"DocLib");
    }


    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof DoclibChildTreeViewBase
     */
    public getDatas(): any[] {
        return [this.currentselectedNode];
    }

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof DoclibChildTreeViewBase
     */
    public getData(): any {
        return this.currentselectedNode;
    }

    /**
     * 当前选中数据项
     *
     * @type {*}
     * @memberof DoclibChildTreeViewBase
     */
    public currentselectedNode: any = {};

    /**
     * 备份行为模型
     * 
     * @type any
     * @memberof DoclibChildTreeViewBase
     */
    public copyActionModel: any = {};

    /**
     * 部件行为--update
     *
     * @type {string}
     * @memberof DoclibChildTreeViewBase
     */
    @Prop() public updateAction!: string;

    /**
     * 部件行为--fetch
     *
     * @type {string}
     * @memberof DoclibChildTreeViewBase
     */
    @Prop() public fetchAction!: string;

    /**
     * 部件行为--remove
     *
     * @type {string}
     * @memberof DoclibChildTreeViewBase
     */
    @Prop() public removeAction!: string;

    /**
     * 部件行为--load
     *
     * @type {string}
     * @memberof DoclibChildTreeViewBase
     */
    @Prop() public loadAction!: string;

    /**
     * 部件行为--create
     *
     * @type {string}
     * @memberof DoclibChildTreeViewBase
     */
    @Prop() public createAction!: string;

    /**
     * 过滤属性
     *
     * @type {string}
     * @memberof DoclibChildTreeViewBase
     */
    public srfnodefilter: string = '';

    /**
     * 当前文件夹所含文件
     *  
     * @type {Array<any>}
     * @memberof DoclibChildTreeViewBase
     */
    public items: any[] = [];

    /**
     * 面包屑数据(默认第一项为图标)
     * 
     * @type {Array<any>}
     * @memberof DoclibChildTreeViewBase
     */
    public breadcrumbs: Array<any> = [{curData: ''}];

    /**
     * 展现形式(默认为图表)
     * 
     * @type {string}
     * @memberof DoclibChildTreeViewBase
     */
    public mode: string = 'chart';

    /**
     * 当前文件夹
     *
     * @type {*}
     * @memberofDoclibChildTreeViewBase
     */
    public currentNode = {};

    /**
     * 分页条数默认20
     *
     * @type {number}
     * @memberof DoclibChildTreeViewBase
     */
    public limit: number = 20;

    /**
     * 总条数默认0
     *
     * @type {number}
     * @memberof DoclibChildTreeViewBase
     */
    public totalRecord: number = 0;

    /**
     * 当前页默认1
     *
     * @type {number}
     * @memberof DoclibChildTreeViewBase
     */
    public curPage: number = 1;

    /**
     * 列表当前页数据
     *
     * @type {number}
     * @memberof DoclibChildTreeViewBase
     */
    public curPageItems: any[] = [];

    /**
     * 树节点上下文菜单集合
     *
     * @type {string[]}
     * @memberof DoclibChildTreeViewBase
     */
     public actionModel: any = {
        ChildModule_deuiaction1: {ctrlname: 'childmodule_cm',name:'deuiaction1',nodeOwner:'ChildModule',type: 'DEUIACTION', tag: 'edit', actiontarget: 'SINGLEKEY', noprivdisplaymode:2, dataaccaction:'SRFUR__DOCLIBMODULE_EDIT_BUT', visible: true, disabled: false,imgclass: 'fa fa-edit',caption: ''},
        ChildModule_deuiaction4: {ctrlname: 'childmodule_cm',name:'deuiaction4',nodeOwner:'ChildModule',type: 'DEUIACTION', tag: 'WeiHuFenLei', actiontarget: 'SINGLEKEY', noprivdisplaymode:2, dataaccaction:'SRFUR__DOCLIBMODULE_MAINTENANCE_BUT', visible: true, disabled: false,imgclass: 'fa fa-lock',caption: ''},
        ChildModule_deuiaction2: {ctrlname: 'childmodule_cm',name:'deuiaction2',nodeOwner:'ChildModule',type: 'DEUIACTION', tag: 'Favorite', actiontarget: 'SINGLEKEY', noprivdisplaymode:2, dataaccaction:'SRFUR__DOCLIBMODULE_FAVOUR_BUT', visible: true, disabled: false,imgclass: 'fa fa-star-o',caption: ''},
        ChildModule_deuiaction3: {ctrlname: 'childmodule_cm',name:'deuiaction3',nodeOwner:'ChildModule',type: 'DEUIACTION', tag: 'NFavorite', actiontarget: 'SINGLEKEY', noprivdisplaymode:2, dataaccaction:'SRFUR__DOCLIBMODULE_NFAVOUR_BUT', visible: true, disabled: false,imgclass: 'fa fa-star',caption: ''},
        RootModule_deuiaction1: {ctrlname: 'rootmodule_cm',name:'deuiaction1',nodeOwner:'RootModule',type: 'DEUIACTION', tag: 'edit', actiontarget: 'SINGLEKEY', noprivdisplaymode:2, dataaccaction:'SRFUR__DOCLIBMODULE_EDIT_BUT', visible: true, disabled: false,imgclass: 'fa fa-edit',caption: ''},
        RootModule_deuiaction4: {ctrlname: 'rootmodule_cm',name:'deuiaction4',nodeOwner:'RootModule',type: 'DEUIACTION', tag: 'WeiHuFenLei', actiontarget: 'SINGLEKEY', noprivdisplaymode:2, dataaccaction:'SRFUR__DOCLIBMODULE_MAINTENANCE_BUT', visible: true, disabled: false,imgclass: 'fa fa-lock',caption: ''},
        RootModule_deuiaction2: {ctrlname: 'rootmodule_cm',name:'deuiaction2',nodeOwner:'RootModule',type: 'DEUIACTION', tag: 'Favorite', actiontarget: 'SINGLEKEY', noprivdisplaymode:2, dataaccaction:'SRFUR__DOCLIBMODULE_FAVOUR_BUT', visible: true, disabled: false,imgclass: 'fa fa-star-o',caption: ''},
        RootModule_deuiaction3: {ctrlname: 'rootmodule_cm',name:'deuiaction3',nodeOwner:'RootModule',type: 'DEUIACTION', tag: 'NFavorite', actiontarget: 'SINGLEKEY', noprivdisplaymode:2, dataaccaction:'SRFUR__DOCLIBMODULE_NFAVOUR_BUT', visible: true, disabled: false,imgclass: 'fa fa-star',caption: ''},
        DocLibDoc_deuiaction1: {ctrlname: 'doclibdoc_cm',name:'deuiaction1',nodeOwner:'DocLibDoc',type: 'DEUIACTION', tag: 'Edit', actiontarget: 'SINGLEKEY', noprivdisplaymode:2, dataaccaction:'SRFUR__DOC_EDIT_BUT', visible: true, disabled: false,imgclass: 'fa fa-edit',caption: ''},
        DocLibDoc_deuiaction4: {ctrlname: 'doclibdoc_cm',name:'deuiaction4',nodeOwner:'DocLibDoc',type: 'DEUIACTION', tag: 'Delete', actiontarget: 'SINGLEKEY', noprivdisplaymode:2, dataaccaction:'SRFUR__DOC_DELETE_BUT', visible: true, disabled: false,imgclass: 'fa fa-remove',caption: ''},
        DocLibDoc_deuiaction2: {ctrlname: 'doclibdoc_cm',name:'deuiaction2',nodeOwner:'DocLibDoc',type: 'DEUIACTION', tag: 'OnlyCollectDoc', actiontarget: 'SINGLEKEY', noprivdisplaymode:2, dataaccaction:'SRFUR__DOC_FAVOUR_BUT', visible: true, disabled: false,imgclass: 'fa fa-star-o',caption: ''},
        DocLibDoc_deuiaction3: {ctrlname: 'doclibdoc_cm',name:'deuiaction3',nodeOwner:'DocLibDoc',type: 'DEUIACTION', tag: 'OnlyUnCollectDoc', actiontarget: 'SINGLEKEY', noprivdisplaymode:2, dataaccaction:'SRFUR__DOC_NFAVOUR_BUT', visible: true, disabled: false,imgclass: 'fa fa-star',caption: ''},
        ModuleDoc_deuiaction1: {ctrlname: 'moduledoc_cm',name:'deuiaction1',nodeOwner:'ModuleDoc',type: 'DEUIACTION', tag: 'Edit', actiontarget: 'SINGLEKEY', noprivdisplaymode:2, dataaccaction:'SRFUR__DOC_EDIT_BUT', visible: true, disabled: false,imgclass: 'fa fa-edit',caption: ''},
        ModuleDoc_deuiaction4: {ctrlname: 'moduledoc_cm',name:'deuiaction4',nodeOwner:'ModuleDoc',type: 'DEUIACTION', tag: 'Delete', actiontarget: 'SINGLEKEY', noprivdisplaymode:2, dataaccaction:'SRFUR__DOC_DELETE_BUT', visible: true, disabled: false,imgclass: 'fa fa-remove',caption: ''},
        ModuleDoc_deuiaction2: {ctrlname: 'moduledoc_cm',name:'deuiaction2',nodeOwner:'ModuleDoc',type: 'DEUIACTION', tag: 'OnlyCollectDoc', actiontarget: 'SINGLEKEY', noprivdisplaymode:2, dataaccaction:'SRFUR__DOC_FAVOUR_BUT', visible: true, disabled: false,imgclass: 'fa fa-star-o',caption: ''},
        ModuleDoc_deuiaction3: {ctrlname: 'moduledoc_cm',name:'deuiaction3',nodeOwner:'ModuleDoc',type: 'DEUIACTION', tag: 'OnlyUnCollectDoc', actiontarget: 'SINGLEKEY', noprivdisplaymode:2, dataaccaction:'SRFUR__DOC_NFAVOUR_BUT', visible: true, disabled: false,imgclass: 'fa fa-star',caption: ''},
    }

    /**
     * Vue声明周期(处理组件的输入属性)
     *
     * @memberof DoclibChildTreeViewBase
     */
    public created() {
        this.afterCreated();
    }

    /**
     * 执行created后的逻辑
     *
     *  @memberof DoclibChildTreeViewBase
     */    
    public afterCreated(){
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                if (Object.is('load', action)) {
                    this.breadcrumbs.splice(1);
                    this.mode = 'chart';
                    this.load();
                }
                if (Object.is('filter', action)) {
                    this.srfnodefilter  = data.srfnodefilter;
                    this.refresh();
                }
                if (Object.is('refresh_parent', action)) {
                    this.refresh();
                }
                if (Object.is('refresh_current', action)) {
                    this.refresh();
                }
            });
        }
    }

    /**
     * vue 生命周期
     *
     * @memberof DoclibChildTreeViewBase
     */
    public destroyed() {
        this.afterDestroy();
    }

    /**
     * 执行destroyed后的逻辑
     *
     * @memberof DoclibChildTreeViewBase
     */
    public afterDestroy() {
        if (this.viewStateEvent) {
            this.viewStateEvent.unsubscribe();
        }
    }

    /**
     * 刷新功能
     *
     * @param {any[]} args
     * @memberof DoclibChildTreeViewBase
     */
    public refresh(): void {
        const node = this.currentNode;
        this.load(node);
    }

    /**
     * 加载当前文件夹所有文件
     *
     * @param {*} node 当前文件夹对象
     * @memberof DoclibChildTreeViewBase
     */
    public async load(node: any = {}, resolve?: any) {
        this.items = [];
        this.currentNode = node;
        if (node.data && node.data.children) {
            return;
        }
        const params: any = {
            srfnodeid: node.data && node.data.id ? node.data.id : "#",
            srfnodefilter: this.srfnodefilter
        };
        let tempViewParams:any = JSON.parse(JSON.stringify(this.viewparams));
        let curNode:any = {}; 
        curNode = this.$util.deepObjectMerge(curNode,node);
        let tempContext:any = this.computecurNodeContext(curNode);
        if(curNode.data && curNode.data.srfparentdename){
            Object.assign(tempContext,{srfparentdename:curNode.data.srfparentdename});
            Object.assign(tempViewParams,{srfparentdename:curNode.data.srfparentdename});
        }
        if(curNode.data && curNode.data.srfparentkey){
            Object.assign(tempContext,{srfparentkey:curNode.data.srfparentkey});
            Object.assign(tempViewParams,{srfparentkey:curNode.data.srfparentkey});
        }
        Object.assign(params,{viewparams:tempViewParams});
        const response = await this.service.getNodes(tempContext,params)
        if (!response || response.status !== 200) {
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.info });
            return;
        }
        const _items = response.data;
        this.items = [..._items];
        this.totalRecord = _items.length;
        if (Object.is(this.mode,'list')) {
            await this.computeCurPageNodeState();
        }
        this.$emit("load", _items);
    }

    /**
     * 计算当前节点的上下文
     *
     * @param {*} curNode 当前节点
     * @memberof DoclibChildTreeViewBase
     */
    public computecurNodeContext(curNode:any) {
        let tempContext:any = {};
        if (curNode && curNode.data && curNode.data.srfappctx) {
            tempContext = JSON.parse(JSON.stringify(curNode.data.srfappctx));
        } else {
            tempContext = JSON.parse(JSON.stringify(this.context));
        }
        return tempContext;
    }

    /**
     * 添加面包屑数据
     * 
     * @param node 文件夹对象
     * @memberof DoclibChildTreeViewBase
     */
    public addBreadcrumbs(node: any) {
        if (this.breadcrumbs.length > 0) {
            const index: number = this.breadcrumbs.findIndex((item: any) => Object.is(item.srfkey, node.srfkey));
            if(index === -1){
                this.breadcrumbs.push(node);
            }
        } else {
            this.breadcrumbs.push(node);
        }
    }

    /**
     * 移除面包屑数据
     * 
     * @param node 文件夹对象
     * @memberof DoclibChildTreeViewBase
     */
    public removeBreadcrumbs(node: any) {
        if (this.breadcrumbs.length > 0) {
            const index: number = this.breadcrumbs.findIndex((item: any) => Object.is(item.srfkey, node.srfkey));
            if (index !== -1) {
                this.breadcrumbs.splice(index+1);
            }
        }
    }

    /**
     * 面包屑跳转文件夹
     * 
     * @param node 文件夹对象
     * @memberof DoclibChildTreeViewBase
     */
    public handleLink(node: any) {
        this.removeBreadcrumbs(node);
        this.load({data: node});
    }

    /**
     * 切换展现形式
     * 
     * @param model 展现形式
     * @memberof DoclibChildTreeViewBase
     */
    public modeChange(mode: string) {
        this.mode = mode;
        if (Object.is(mode,'list')) {
            this.computeCurPageNodeState();
        }
    }

    /**
     * 计算当前页工具栏状态
     * 
     * @memberof DoclibChildTreeViewBase
     */
    public async computeCurPageNodeState(){
        this.curPageItems = [];
        let curPageItems: Array<any> = [];
        const start = (this.curPage-1) * this.limit;
        let end = this.curPage * this.limit;
        end = end > this.items.length ? this.items.length : end;
        curPageItems = this.items.slice(start,end);
        await this.computeAllNodeState(curPageItems);
        this.curPageItems = [...curPageItems];
    }

    /**
     * 打开目标文件
     * 
     * @param file 目标文件
     * @memberof DoclibChildTreeViewBase
     */
    public openNode(node: any) {
        this.addBreadcrumbs(node);
        this.load({data: node});
    }

    /**
     * 计算指定文件的工具栏状态
     * 
     * @memberof DoclibChildTreeViewBase
     */
    public async computeAllNodeState(items: Array<any>) {
        if (items && items.length > 0) {
            for (let i=0; i < items.length; i++) {
                if (!items[i].curData || !items[i].curData.copyActionModel) {
                    await this.getNodeState(items[i]);
                }
            }
        }
    }

    /**
     * 获取当前文件夹指定文件的工具栏状态
     * 
     * @param node 指定的文件对象
     * @memberof DoclibChildTreeViewBase
     */
    public async getNodeState(node: any) {
        this.copyActionModel = {};
        const tags: string[] = node.id.split(';');
        Object.values(this.actionModel).forEach((item:any) =>{
            if(Object.is(item.nodeOwner,tags[0])){
                this.copyActionModel[item.name] = item;
            }
        })
        if(Object.keys(this.copyActionModel).length === 0){
            return;
        }
        const result = await this.computeNodeState(node,node.nodeType,node.appEntityName)
        if(Object.values(result).length>0){
            node.curData.copyActionModel = JSON.parse(JSON.stringify(this.copyActionModel));
        }
    }

    /**
     * 计算文件的工具栏状态
     *
     * @param {*} node 指定的文件对象
     * @param {*} nodeType 指定的文件类型
     * @param {*} appEntityName 应用实体名称  
     * @returns
     * @memberof DoclibChildTreeViewBase
     */
    public async computeNodeState(node: any, nodeType: string, appEntityName: string) {
        if(Object.is(nodeType,"STATIC")){
            return this.copyActionModel;
        }
        let service:any = await this.appEntityService.getService(appEntityName);
        if(this.copyActionModel && Object.keys(this.copyActionModel).length > 0) {
            if(service['Get'] && service['Get'] instanceof Function){
                let tempContext:any = this.$util.deepCopy(this.context);
                tempContext[appEntityName] = node.srfkey;
                let targetData = await service.Get(tempContext,{}, false);
                let uiservice:any = await this.appUIService.getService(appEntityName);
                let result: any[] = ViewTool.calcActionItemAuthState(targetData.data,this.copyActionModel,uiservice);
                return this.copyActionModel;
            }else{
                console.warn("获取数据异常");
                return this.copyActionModel;
            }
        }
    }

    /**
     * 图表模式下工具栏的显示
     *
     * @param {*} index 工具栏标识
     * @memberof DoclibChildTreeViewBase
     */
    public async showToolBar(item: any,index: number){
        if(!item.curData || !item.curData.copyActionModel){
            await this.getNodeState(item);
            this.$forceUpdate();
        }
        let el: any = this.$el.getElementsByClassName('chart-item-operate-'+index)[0];
        if (el) {
            el.style.display = 'block'; 
        }
    }

    /**
     * 图表模式下工具栏的隐藏
     *
     * @param {*} index 工具栏标识
     * @memberof DoclibChildTreeViewBase
     */
    public hideToolBar(index: number){
        let el: any = this.$el.getElementsByClassName('chart-item-operate-'+index)[0];
        if (el) {
            el.style.display = 'none'; 
        }
    }

    /**
     * 页面变化
     *
     * @param {*} $event
     * @returns {void}
     * @memberof GridControlBase
     */
    public pageOnChange($event: any): void {
        if (!$event || $event === this.curPage) {
            return;
        }
        this.curPage = $event;
        this.computeCurPageNodeState();
    }

    /**
     * 分页条数变化
     *
     * @param {*} $event
     * @returns {void}
     * @memberof DoclibChildTreeViewBase
     */
    public onPageSizeChange($event: any): void {
        if (!$event || $event === this.limit) {
            return;
        }
        this.limit = $event;
        this.computeCurPageNodeState();
    }

    /**
     * 分页刷新
     *
     * @memberof GridControlBase
     */
    public pageRefresh(): void {
        const node = this.currentNode;
        this.load(node);
        this.computeCurPageNodeState();
    }

    /**
     * 工具栏触发行为
     *
     * @param {*} item 触发行为文件对象
     * @param {*} tag 触发行为标识
     * @memberof DoclibChildTreeViewBase
     */
    public onAction(item: any,ctrlname: string,tag: string) {
        let _this:any = this;
        this.currentselectedNode = JSON.parse(JSON.stringify(item));
        if (Object.is('childmodule_cm',ctrlname) && _this.childmodule_cm_click && _this.childmodule_cm_click instanceof Function) {
            _this.childmodule_cm_click({ tag: tag });
        }           
        if (Object.is('rootmodule_cm',ctrlname) && _this.rootmodule_cm_click && _this.rootmodule_cm_click instanceof Function) {
            _this.rootmodule_cm_click({ tag: tag });
        }           
        if (Object.is('doclibdoc_cm',ctrlname) && _this.doclibdoc_cm_click && _this.doclibdoc_cm_click instanceof Function) {
            _this.doclibdoc_cm_click({ tag: tag });
        }           
        if (Object.is('moduledoc_cm',ctrlname) && _this.moduledoc_cm_click && _this.moduledoc_cm_click instanceof Function) {
            _this.moduledoc_cm_click({ tag: tag });
        }           
    }

}