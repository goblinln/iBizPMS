
import { Subject } from 'rxjs';
import { ViewTool } from '@/utils';
import { GridViewBase } from '@/studio-core';
import ProductPlanService from '@/service/product-plan/product-plan-service';
import ProductPlanAuthService from '@/authservice/product-plan/product-plan-auth-service';
import GridViewEngine from '@engine/view/grid-view-engine';
import ProductPlanUIService from '@/uiservice/product-plan/product-plan-ui-service';
import CodeListService from "@service/app/codelist-service";


/**
 * 产品计划表格视图视图基类
 *
 * @export
 * @class ProductPlanGridViewBase
 * @extends {GridViewBase}
 */
export class ProductPlanGridViewBase extends GridViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof ProductPlanGridViewBase
     */
    protected appDeName: string = 'productplan';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof ProductPlanGridViewBase
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof ProductPlanGridViewBase
     */
    protected appDeMajor: string = 'title';

    /**
     * 实体服务对象
     *
     * @type {ProductPlanService}
     * @memberof ProductPlanGridViewBase
     */
    protected appEntityService: ProductPlanService = new ProductPlanService;

    /**
     * 实体权限服务对象
     *
     * @type ProductPlanUIService
     * @memberof ProductPlanGridViewBase
     */
    public appUIService: ProductPlanUIService = new ProductPlanUIService(this.$store);

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof ProductPlanGridViewBase
     */
    protected model: any = {
        srfCaption: 'entities.productplan.views.gridview.caption',
        srfTitle: 'entities.productplan.views.gridview.title',
        srfSubTitle: 'entities.productplan.views.gridview.subtitle',
        dataInfo: ''
    }

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof ProductPlanGridViewBase
     */
    protected containerModel: any = {
        view_toolbar: { name: 'toolbar', type: 'TOOLBAR' },
        view_grid: { name: 'grid', type: 'GRID' },
    };

    /**
     * 工具栏模型
     *
     * @type {*}
     * @memberof ProductPlanGridView
     */
    public toolBarModels: any = {
        deuiaction3_create: { name: 'deuiaction3_create', caption: '创建计划', 'isShowCaption': true, 'isShowIcon': true, tooltip: '创建计划', iconcls: 'fa fa-plus', icon: '', disabled: false, type: 'DEUIACTION', visabled: true,noprivdisplaymode:2,dataaccaction: 'SRFUR__PROP_CREATE_BUT', uiaction: { tag: 'Create', target: 'NONE', class: '' } },

        deuiaction2: { name: 'deuiaction2', caption: '刷新', 'isShowCaption': true, 'isShowIcon': true, tooltip: '刷新', iconcls: 'fa fa-refresh', icon: '', disabled: false, type: 'DEUIACTION', visabled: true,noprivdisplaymode:2,dataaccaction: '', uiaction: { tag: 'Refresh', target: '', class: '' } },

        deuiaction1: { name: 'deuiaction1', caption: '导出', 'isShowCaption': true, 'isShowIcon': true, tooltip: '导出', iconcls: 'fa fa-file-excel-o', icon: '', disabled: false, type: 'DEUIACTION', visabled: true,noprivdisplaymode:2,dataaccaction: '', uiaction: { tag: 'ExportExcel', target: '' }, MaxRowCount: 1000, class: '' },

    };



	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof ViewBase
     */
	protected viewtag: string = '81b0cee2fedff8f4902c0d735714e5af';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof ProductPlanGridViewBase
     */
    public engine: GridViewEngine = new GridViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof ProductPlanGridViewBase
     */    
    public counterServiceArray:Array<any> = [];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof ProductPlanGridViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            opendata: (args: any[],fullargs?:any[],params?: any, $event?: any, xData?: any) => {
                this.opendata(args,fullargs, params, $event, xData);
            },
            newdata: (args: any[],fullargs?:any[],params?: any, $event?: any, xData?: any) => {
                this.newdata(args,fullargs, params, $event, xData);
            },
            grid: this.$refs.grid,
            keyPSDEField: 'productplan',
            majorPSDEField: 'title',
            isLoadDefault: false,
        });
    }

    /**
     * toolbar 部件 click 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProductPlanGridViewBase
     */
    public toolbar_click($event: any, $event2?: any): void {
        if (Object.is($event.tag, 'deuiaction3_create')) {
            this.toolbar_deuiaction3_create_click(null, '', $event2);
        }
        if (Object.is($event.tag, 'deuiaction2')) {
            this.toolbar_deuiaction2_click(null, '', $event2);
        }
        if (Object.is($event.tag, 'deuiaction1')) {
            this.toolbar_deuiaction1_click(null, '', $event2);
        }
    }

    /**
     * grid 部件 selectionchange 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProductPlanGridViewBase
     */
    public grid_selectionchange($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'selectionchange', $event);
    }

    /**
     * grid 部件 beforeload 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProductPlanGridViewBase
     */
    public grid_beforeload($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'beforeload', $event);
    }

    /**
     * grid 部件 rowdblclick 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProductPlanGridViewBase
     */
    public grid_rowdblclick($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'rowdblclick', $event);
    }

    /**
     * grid 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProductPlanGridViewBase
     */
    public grid_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'remove', $event);
    }

    /**
     * grid 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProductPlanGridViewBase
     */
    public grid_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'load', $event);
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public toolbar_deuiaction3_create_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this.$refs.grid;
        if (xData.getDatas && xData.getDatas instanceof Function) {
            datas = [...xData.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:ProductPlanUIService  = new ProductPlanUIService();
        curUIService.ProductPlan_Create(datas,contextJO, paramJO,  $event, xData,this,"ProductPlan");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public toolbar_deuiaction2_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this.$refs.grid;
        if (xData.getDatas && xData.getDatas instanceof Function) {
            datas = [...xData.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        this.Refresh(datas, contextJO,paramJO,  $event, xData,this,"ProductPlan");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public toolbar_deuiaction1_click(params: any = {}, tag?: any, $event?: any) {
        // 参数
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this.$refs.grid;
        if (xData.getDatas && xData.getDatas instanceof Function) {
            datas = [...xData.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        this.ExportExcel(datas, contextJO,paramJO,  $event, xData,this,"ProductPlan");
    }

    /**
     * 打开新建数据视图
     *
     * @param {any[]} args
     * @param {*} [params]
     * @param {*} [fullargs]
     * @param {*} [$event]
     * @param {*} [xData]
     * @memberof ProductPlanGridView
     */
    public newdata(args: any[],fullargs?:any[], params?: any, $event?: any, xData?: any) {
        let localContext:any = null;
        let localViewParam:any =null;
        const data: any = {};
        if(args[0].srfsourcekey){
            data.srfsourcekey = args[0].srfsourcekey;
        }
        if(fullargs && (fullargs as any).copymode) {
            Object.assign(data, { copymode: (fullargs as any).copymode });
        }
        let tempContext = JSON.parse(JSON.stringify(this.context));
        delete tempContext.productplan;
        if(args.length >0){
            Object.assign(tempContext,args[0]);
        }
        let deResParameters: any[] = [];
        if(tempContext.product && true){
            deResParameters = [
            { pathName: 'products', parameterName: 'product' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'productplans', parameterName: 'productplan' },
        ];
        const _this: any = this;
        const openDrawer = (view: any, data: any) => {
            let container: Subject<any> = this.$appdrawer.openDrawer(view, tempContext, data);
            container.subscribe((result: any) => {
                if (!result || !Object.is(result.ret, 'OK')) {
                    return;
                }
                if (!xData || !(xData.refresh instanceof Function)) {
                    return;
                }
                xData.refresh(result.datas);
            });
        }
        const view: any = {
            viewname: 'product-plan-edit-view', 
            height: 0, 
            width: 0,  
            title: this.$t('entities.productplan.views.editview.title'),
            placement: 'DRAWER_RIGHT',
        };
        openDrawer(view, data);
    }


    /**
     * 打开编辑数据视图
     *
     * @param {any[]} args
     * @param {*} [params]
     * @param {*} [fullargs]
     * @param {*} [$event]
     * @param {*} [xData]
     * @memberof ProductPlanGridView
     */
    public opendata(args: any[],fullargs?:any[],params?: any, $event?: any, xData?: any) {
        const localContext: any = null;
        const localViewParam: any =null;
        const data: any = {};
        let tempContext = JSON.parse(JSON.stringify(this.context));
        if(args.length >0){
            Object.assign(tempContext,args[0]);
        }
        let deResParameters: any[] = [];
        if(tempContext.product && true){
            deResParameters = [
            { pathName: 'products', parameterName: 'product' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'productplans', parameterName: 'productplan' },
        ];
        const _this: any = this;
        const openDrawer = (view: any, data: any) => {
            let container: Subject<any> = this.$appdrawer.openDrawer(view, tempContext, data);
            container.subscribe((result: any) => {
                if (!result || !Object.is(result.ret, 'OK')) {
                    return;
                }
                if (!xData || !(xData.refresh instanceof Function)) {
                    return;
                }
                xData.refresh(result.datas);
            });
        }
        const view: any = {
            viewname: 'product-plan-main-data-edit-view', 
            height: 0, 
            width: 0,  
            title: this.$t('entities.productplan.views.maindataeditview.title'),
            placement: 'DRAWER_TOP',
        };
        openDrawer(view, data);
    }


    /**
     * 刷新
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @memberof ProductPlanGridViewBase
     */
    public Refresh(args: any[],contextJO?:any, params?: any, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
        const _this: any = this;
        if (xData && xData.refresh && xData.refresh instanceof Function) {
            xData.refresh(args);
        } else if (_this.refresh && _this.refresh instanceof Function) {
            _this.refresh(args);
        }
    }
    /**
     * 导出
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @memberof ProductPlanGridViewBase
     */
    public ExportExcel(args: any[],contextJO?:any, params?: any, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
        const _this: any = this;
        if (!xData || !(xData.exportExcel instanceof Function) || !$event) {
            return ;
        }
        xData.exportExcel($event.exportparms);
    }

    /**
     * 是否启用快速分组
     *
     * @type {boolean}
     * @memberof ProductPlanGridViewBase
     */
    public isEnableQuickGroup: boolean = true;

    /**
     * 加载快速分组模型
     *
     * @protected
     * @memberof ProductPlanGridViewBase
     */
    protected loadQuickGroupModel(): void {
        const quickGroupCodeList: any = { tag: 'Zt__productplan', codelistType: 'STATIC' };
        if(quickGroupCodeList.tag && Object.is(quickGroupCodeList.codelistType, "STATIC")) {
            const codelist = this.$store.getters.getCodeList(quickGroupCodeList.tag);
            if (codelist) {
                this.quickGroupModel = [...this.handleDynamicData(JSON.parse(JSON.stringify(codelist.items)))];
            } else {
                console.log(`----${quickGroupCodeList.tag}----代码表不存在`);
            }
        } else if(quickGroupCodeList.tag && Object.is(quickGroupCodeList.codelistType, "DYNAMIC")) {
            this.codeListService.getItems(quickGroupCodeList.tag, {}, {}).then((res: any) => {
                this.quickGroupModel = res;
            }).catch((error:any) => {
                console.log(`----${quickGroupCodeList.tag}----代码表不存在`);
            });
        }
    }
}