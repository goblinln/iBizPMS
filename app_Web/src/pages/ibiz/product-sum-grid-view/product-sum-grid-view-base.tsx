
import { Subject } from 'rxjs';
import { UIActionTool, ViewTool } from '@/utils';
import { GridViewBase } from '@/studio-core';
import ProductSumService from '@/service/product-sum/product-sum-service';
import ProductSumAuthService from '@/authservice/product-sum/product-sum-auth-service';
import GridViewEngine from '@engine/view/grid-view-engine';
import ProductSumUIService from '@/uiservice/product-sum/product-sum-ui-service';
import CodeListService from '@service/app/codelist-service';


/**
 * 产品汇总表表格视图视图基类
 *
 * @export
 * @class ProductSumGridViewBase
 * @extends {GridViewBase}
 */
export class ProductSumGridViewBase extends GridViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof ProductSumGridViewBase
     */
    protected appDeName: string = 'productsum';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof ProductSumGridViewBase
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof ProductSumGridViewBase
     */
    protected appDeMajor: string = 'name';

    /**
     * 数据部件名称
     *
     * @protected
     * @type {string}
     * @memberof ProductSumGridViewBase
     */ 
    protected dataControl: string = "grid";

    /**
     * 实体服务对象
     *
     * @type {ProductSumService}
     * @memberof ProductSumGridViewBase
     */
    protected appEntityService: ProductSumService = new ProductSumService;

    /**
     * 实体权限服务对象
     *
     * @type ProductSumUIService
     * @memberof ProductSumGridViewBase
     */
    public appUIService: ProductSumUIService = new ProductSumUIService(this.$store);

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof ProductSumGridViewBase
     */
    protected model: any = {
        srfCaption: 'entities.productsum.views.gridview.caption',
        srfTitle: 'entities.productsum.views.gridview.title',
        srfSubTitle: 'entities.productsum.views.gridview.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof ProductSumGridViewBase
     */
    protected containerModel: any = {
        view_toolbar: {
            name: 'toolbar',
            type: 'TOOLBAR',
        },
        view_grid: {
            name: 'grid',
            type: 'GRID',
        },
        view_searchform: {
            name: 'searchform',
            type: 'SEARCHFORM',
        },
    };

    /**
     * 工具栏模型
     *
     * @type {*}
     * @memberof ProductSumGridView
     */
    public toolBarModels: any = {
        deuiaction2: { name: 'deuiaction2', caption: '导出', 'isShowCaption': true, 'isShowIcon': true, tooltip: '导出', iconcls: 'fa fa-file-excel-o', icon: '', disabled: false, type: 'DEUIACTION', visible: true,noprivdisplaymode:2,dataaccaction: '', uiaction: { tag: 'ExportExcel', target: '' }, MaxRowCount: 1000, class: '' },

    };



	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof ProductSumGridViewBase
     */
	protected viewtag: string = '3989c98a31e037a67b27cdf7acb1f7cd';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof ProductSumGridViewBase
     */ 
    protected viewName: string = "ProductSumGridView";


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof ProductSumGridViewBase
     */
    public engine: GridViewEngine = new GridViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof ProductSumGridViewBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof ProductSumGridViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            opendata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                this.opendata(args, fullargs, params, $event, xData);
            },
            newdata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                this.newdata(args, fullargs, params, $event, xData);
            },
            grid: this.$refs.grid,
            searchform: this.$refs.searchform,
            keyPSDEField: 'productsum',
            majorPSDEField: 'name',
            isLoadDefault: true,
        });
    }

    /**
     * toolbar 部件 click 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProductSumGridViewBase
     */
    public toolbar_click($event: any, $event2?: any): void {
        if (Object.is($event.tag, 'deuiaction2')) {
            this.toolbar_deuiaction2_click(null, '', $event2);
        }
    }

    /**
     * grid 部件 selectionchange 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProductSumGridViewBase
     */
    public grid_selectionchange($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'selectionchange', $event);
    }

    /**
     * grid 部件 beforeload 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProductSumGridViewBase
     */
    public grid_beforeload($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'beforeload', $event);
    }

    /**
     * grid 部件 rowdblclick 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProductSumGridViewBase
     */
    public grid_rowdblclick($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'rowdblclick', $event);
    }

    /**
     * grid 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProductSumGridViewBase
     */
    public grid_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'remove', $event);
    }

    /**
     * grid 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProductSumGridViewBase
     */
    public grid_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'load', $event);
    }

    /**
     * searchform 部件 save 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProductSumGridViewBase
     */
    public searchform_save($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('searchform', 'save', $event);
    }

    /**
     * searchform 部件 search 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProductSumGridViewBase
     */
    public searchform_search($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('searchform', 'search', $event);
    }

    /**
     * searchform 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProductSumGridViewBase
     */
    public searchform_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('searchform', 'load', $event);
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
        this.ExportExcel(datas, contextJO,paramJO,  $event, xData,this,"ProductSum");
    }

    /**
     * 打开新建数据视图
     *
     * @param {any[]} args
     * @param {*} [params]
     * @param {*} [fullargs]
     * @param {*} [$event]
     * @param {*} [xData]
     * @memberof ProductSumGridView
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
        delete tempContext.productsum;
        if(args.length >0){
            Object.assign(tempContext,args[0]);
        }
        const deResParameters: any[] = [];
        const parameters: any[] = [
            { pathName: 'productsums', parameterName: 'productsum' },
            { pathName: 'editview', parameterName: 'editview' },
        ];
        const _this: any = this;
        const openIndexViewTab = (data: any) => {
            const _data: any = { w: (new Date().getTime()) };
            Object.assign(_data, data);
            const routePath = this.$viewTool.buildUpRoutePath(this.$route, tempContext, deResParameters, parameters, args, _data);
            this.$router.push(routePath);
        }
        openIndexViewTab(data);
    }


    /**
     * 打开编辑数据视图
     *
     * @param {any[]} args
     * @param {*} [params]
     * @param {*} [fullargs]
     * @param {*} [$event]
     * @param {*} [xData]
     * @memberof ProductSumGridView
     */
    public opendata(args: any[],fullargs?:any[],params?: any, $event?: any, xData?: any) {
        const localContext: any = null;
        const localViewParam: any =null;
        const data: any = {};
        let tempContext = JSON.parse(JSON.stringify(this.context));
        if(args.length >0){
            Object.assign(tempContext,args[0]);
        }
        const deResParameters: any[] = [];
        const parameters: any[] = [
            { pathName: 'productsums', parameterName: 'productsum' },
            { pathName: 'editview', parameterName: 'editview' },
        ];
        const _this: any = this;
        const openIndexViewTab = (data: any) => {
            const routePath = this.$viewTool.buildUpRoutePath(this.$route, tempContext, deResParameters, parameters, args, data);
            this.$router.push(routePath);
        }
        openIndexViewTab(data);
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
     * @memberof ProductSumGridViewBase
     */
    public ExportExcel(args: any[],contextJO?:any, params?: any, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
        const _this: any = this;
        if (!xData || !(xData.exportExcel instanceof Function) || !$event) {
            return ;
        }
        xData.exportExcel($event.exportparms);
    }

    /**
     * 是否单选
     *
     * @protected
     * @type {boolean}
     * @memberof ProductSumGridViewBase
     */
    protected isGridSingleSelect: boolean = true;

    /**
     * 是否展开搜索表单
     *
     * @protected
     * @type {boolean}
     * @memberof ProductSumGridViewBase
     */
    protected isExpandSearchForm: boolean = true;

    /**
     * 表格行数据默认激活模式
     * 0 不激活
     * 1 单击激活
     * 2 双击激活
     *
     * @protected
     * @type {(0 | 1 | 2)}
     * @memberof ProductSumGridViewBase
     */
    protected gridRowActiveMode: 0 | 1 | 2 = 0;
}