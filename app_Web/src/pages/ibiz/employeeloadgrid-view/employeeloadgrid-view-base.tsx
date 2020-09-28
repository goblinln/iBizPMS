
import { Subject } from 'rxjs';
import { ViewTool } from '@/utils';
import { GridViewBase } from '@/studio-core';
import EmployEeloadService from '@/service/employ-eeload/employ-eeload-service';
import EmployEeloadAuthService from '@/authservice/employ-eeload/employ-eeload-auth-service';
import GridViewEngine from '@engine/view/grid-view-engine';
import EmployEeloadUIService from '@/uiservice/employ-eeload/employ-eeload-ui-service';
import CodeListService from "@service/app/codelist-service";


/**
 * 员工负载表表格视图视图基类
 *
 * @export
 * @class EMPLOYEELOADGridViewBase
 * @extends {GridViewBase}
 */
export class EMPLOYEELOADGridViewBase extends GridViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof EMPLOYEELOADGridViewBase
     */
    protected appDeName: string = 'employeeload';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof EMPLOYEELOADGridViewBase
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof EMPLOYEELOADGridViewBase
     */
    protected appDeMajor: string = 'name';

    /**
     * 实体服务对象
     *
     * @type {EmployEeloadService}
     * @memberof EMPLOYEELOADGridViewBase
     */
    protected appEntityService: EmployEeloadService = new EmployEeloadService;

    /**
     * 实体权限服务对象
     *
     * @type EmployEeloadUIService
     * @memberof EMPLOYEELOADGridViewBase
     */
    public appUIService: EmployEeloadUIService = new EmployEeloadUIService(this.$store);

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof EMPLOYEELOADGridViewBase
     */
    protected model: any = {
        srfCaption: 'entities.employeeload.views.gridview.caption',
        srfTitle: 'entities.employeeload.views.gridview.title',
        srfSubTitle: 'entities.employeeload.views.gridview.subtitle',
        dataInfo: ''
    }

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof EMPLOYEELOADGridViewBase
     */
    protected containerModel: any = {
        view_grid: { name: 'grid', type: 'GRID' },
        view_searchform: { name: 'searchform', type: 'SEARCHFORM' },
    };


	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof ViewBase
     */
	protected viewtag: string = 'e008123fe4c3014fac07b33dd79f6b13';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof EMPLOYEELOADGridViewBase
     */
    public engine: GridViewEngine = new GridViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof EMPLOYEELOADGridViewBase
     */    
    public counterServiceArray:Array<any> = [];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof EMPLOYEELOADGridViewBase
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
            searchform: this.$refs.searchform,
            keyPSDEField: 'employeeload',
            majorPSDEField: 'name',
            isLoadDefault: true,
        });
    }

    /**
     * grid 部件 selectionchange 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof EMPLOYEELOADGridViewBase
     */
    public grid_selectionchange($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'selectionchange', $event);
    }

    /**
     * grid 部件 beforeload 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof EMPLOYEELOADGridViewBase
     */
    public grid_beforeload($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'beforeload', $event);
    }

    /**
     * grid 部件 rowdblclick 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof EMPLOYEELOADGridViewBase
     */
    public grid_rowdblclick($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'rowdblclick', $event);
    }

    /**
     * grid 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof EMPLOYEELOADGridViewBase
     */
    public grid_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'remove', $event);
    }

    /**
     * grid 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof EMPLOYEELOADGridViewBase
     */
    public grid_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'load', $event);
    }

    /**
     * searchform 部件 save 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof EMPLOYEELOADGridViewBase
     */
    public searchform_save($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('searchform', 'save', $event);
    }

    /**
     * searchform 部件 search 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof EMPLOYEELOADGridViewBase
     */
    public searchform_search($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('searchform', 'search', $event);
    }

    /**
     * searchform 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof EMPLOYEELOADGridViewBase
     */
    public searchform_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('searchform', 'load', $event);
    }

    /**
     * 打开新建数据视图
     *
     * @param {any[]} args
     * @param {*} [params]
     * @param {*} [fullargs]
     * @param {*} [$event]
     * @param {*} [xData]
     * @memberof EMPLOYEELOADGridView
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
        delete tempContext.employeeload;
        if(args.length >0){
            Object.assign(tempContext,args[0]);
        }
        const deResParameters: any[] = [];
        const parameters: any[] = [
            { pathName: 'employeeloads', parameterName: 'employeeload' },
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
     * @memberof EMPLOYEELOADGridView
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
            { pathName: 'employeeloads', parameterName: 'employeeload' },
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
     * 是否单选
     *
     * @protected
     * @type {boolean}
     * @memberof EMPLOYEELOADGridViewBase
     */
    protected isGridSingleSelect: boolean = true;
}