
import { Subject } from 'rxjs';
import { UIActionTool, ViewTool } from '@/utils';
import { GridViewBase } from '@/studio-core';
import SysEmployeeService from '@/service/sys-employee/sys-employee-service';
import SysEmployeeAuthService from '@/authservice/sys-employee/sys-employee-auth-service';
import GridViewEngine from '@engine/view/grid-view-engine';
import SysEmployeeUIService from '@/uiservice/sys-employee/sys-employee-ui-service';
import CodeListService from '@service/app/codelist-service';


/**
 * 人员表格视图视图基类
 *
 * @export
 * @class SysEmployeeUsr3GridViewBase
 * @extends {GridViewBase}
 */
export class SysEmployeeUsr3GridViewBase extends GridViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof SysEmployeeUsr3GridViewBase
     */
    protected appDeName: string = 'sysemployee';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof SysEmployeeUsr3GridViewBase
     */
    protected appDeKey: string = 'userid';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof SysEmployeeUsr3GridViewBase
     */
    protected appDeMajor: string = 'personname';

    /**
     * 数据部件名称
     *
     * @protected
     * @type {string}
     * @memberof SysEmployeeUsr3GridViewBase
     */ 
    protected dataControl: string = 'grid';

    /**
     * 实体服务对象
     *
     * @type {SysEmployeeService}
     * @memberof SysEmployeeUsr3GridViewBase
     */
    protected appEntityService: SysEmployeeService = new SysEmployeeService;

    /**
     * 实体权限服务对象
     *
     * @type SysEmployeeUIService
     * @memberof SysEmployeeUsr3GridViewBase
     */
    public appUIService: SysEmployeeUIService = new SysEmployeeUIService(this.$store);

	/**
	 * 自定义视图导航参数集合
	 *
     * @protected
	 * @type {*}
	 * @memberof SysEmployeeUsr3GridViewBase
	 */
    protected customViewParams: any = {
        'n_project_eq': {
            isRawValue: false,
            value: 'project',
        }
    };

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof SysEmployeeUsr3GridViewBase
     */
    protected model: any = {
        srfCaption: 'entities.sysemployee.views.usr3gridview.caption',
        srfTitle: 'entities.sysemployee.views.usr3gridview.title',
        srfSubTitle: 'entities.sysemployee.views.usr3gridview.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof SysEmployeeUsr3GridViewBase
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
     * @memberof SysEmployeeUsr3GridView
     */
    public toolBarModels: any = {
    };



	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof SysEmployeeUsr3GridViewBase
     */
	protected viewtag: string = '42ce56d703f9d063d6c1524bb7a559de';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof SysEmployeeUsr3GridViewBase
     */ 
    protected viewName: string = 'SysEmployeeUsr3GridView';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof SysEmployeeUsr3GridViewBase
     */
    public engine: GridViewEngine = new GridViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof SysEmployeeUsr3GridViewBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof SysEmployeeUsr3GridViewBase
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
            keyPSDEField: 'sysemployee',
            majorPSDEField: 'personname',
            isLoadDefault: true,
        });
    }

    /**
     * grid 部件 selectionchange 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysEmployeeUsr3GridViewBase
     */
    public grid_selectionchange($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'selectionchange', $event);
    }

    /**
     * grid 部件 beforeload 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysEmployeeUsr3GridViewBase
     */
    public grid_beforeload($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'beforeload', $event);
    }

    /**
     * grid 部件 rowdblclick 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysEmployeeUsr3GridViewBase
     */
    public grid_rowdblclick($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'rowdblclick', $event);
    }

    /**
     * grid 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysEmployeeUsr3GridViewBase
     */
    public grid_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'remove', $event);
    }

    /**
     * grid 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysEmployeeUsr3GridViewBase
     */
    public grid_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'load', $event);
    }

    /**
     * searchform 部件 save 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysEmployeeUsr3GridViewBase
     */
    public searchform_save($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('searchform', 'save', $event);
    }

    /**
     * searchform 部件 search 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysEmployeeUsr3GridViewBase
     */
    public searchform_search($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('searchform', 'search', $event);
    }

    /**
     * searchform 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysEmployeeUsr3GridViewBase
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
     * @memberof SysEmployeeUsr3GridView
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
        delete tempContext.sysemployee;
        if(args.length >0){
            Object.assign(tempContext,args[0]);
        }
        const deResParameters: any[] = [];
        const parameters: any[] = [
            { pathName: 'sysemployees', parameterName: 'sysemployee' },
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
     * @memberof SysEmployeeUsr3GridView
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
            { pathName: 'sysemployees', parameterName: 'sysemployee' },
            { pathName: 'editview', parameterName: 'editview' },
        ];
        const _this: any = this;
        const openIndexViewTab = (data: any) => {
            const routePath = this.$viewTool.buildUpRoutePath(this.$route, tempContext, deResParameters, parameters, args, data);
            this.$router.push(routePath);
        }
        openIndexViewTab(data);
    }


}