
import { Subject } from 'rxjs';
import { UIActionTool, ViewTool } from '@/utils';
import { GridViewBase } from '@/studio-core';
import ProjectStatsService from '@/service/project-stats/project-stats-service';
import ProjectStatsAuthService from '@/authservice/project-stats/project-stats-auth-service';
import GridViewEngine from '@engine/view/grid-view-engine';
import ProjectStatsUIService from '@/uiservice/project-stats/project-stats-ui-service';
import CodeListService from '@service/app/codelist-service';


/**
 * 项目质量表视图基类
 *
 * @export
 * @class ProjectStatsProjectQualityUsr2GridViewBase
 * @extends {GridViewBase}
 */
export class ProjectStatsProjectQualityUsr2GridViewBase extends GridViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
     */
    protected appDeName: string = 'projectstats';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
     */
    protected appDeMajor: string = 'name';

    /**
     * 数据部件名称
     *
     * @protected
     * @type {string}
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
     */ 
    protected dataControl: string = 'grid';

    /**
     * 实体服务对象
     *
     * @type {ProjectStatsService}
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
     */
    protected appEntityService: ProjectStatsService = new ProjectStatsService;

    /**
     * 实体权限服务对象
     *
     * @type ProjectStatsUIService
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
     */
    public appUIService: ProjectStatsUIService = new ProjectStatsUIService(this.$store);

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
     */
    protected model: any = {
        srfCaption: 'entities.projectstats.views.projectqualityusr2gridview.caption',
        srfTitle: 'entities.projectstats.views.projectqualityusr2gridview.title',
        srfSubTitle: 'entities.projectstats.views.projectqualityusr2gridview.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
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
    };

    /**
     * 工具栏模型
     *
     * @type {*}
     * @memberof ProjectStatsProjectQualityUsr2GridView
     */
    public toolBarModels: any = {
        deuiaction1: { name: 'deuiaction1', caption: 'entities.projectstats.projectqualityusr2gridviewtoolbar_toolbar.deuiaction1.caption', 'isShowCaption': true, 'isShowIcon': true, tooltip: 'entities.projectstats.projectqualityusr2gridviewtoolbar_toolbar.deuiaction1.tip', iconcls: 'fa fa-file-excel-o', icon: '', disabled: false, type: 'DEUIACTION', visible: true,noprivdisplaymode:2,dataaccaction: '', uiaction: { tag: 'ExportExcel', target: '' }, MaxRowCount: 1000, class: '' },

    };



	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
     */
	protected viewtag: string = 'ab5df14b40f3a74e59053c76e1f48744';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
     */ 
    protected viewName: string = 'ProjectStatsProjectQualityUsr2GridView';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
     */
    public engine: GridViewEngine = new GridViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
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
            keyPSDEField: 'projectstats',
            majorPSDEField: 'name',
            isLoadDefault: true,
        });
    }

    /**
     * toolbar 部件 click 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
     */
    public toolbar_click($event: any, $event2?: any): void {
        if (Object.is($event.tag, 'deuiaction1')) {
            this.toolbar_deuiaction1_click(null, '', $event2);
        }
    }

    /**
     * grid 部件 selectionchange 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
     */
    public grid_selectionchange($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'selectionchange', $event);
    }

    /**
     * grid 部件 beforeload 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
     */
    public grid_beforeload($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'beforeload', $event);
    }

    /**
     * grid 部件 rowdblclick 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
     */
    public grid_rowdblclick($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'rowdblclick', $event);
    }

    /**
     * grid 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
     */
    public grid_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'remove', $event);
    }

    /**
     * grid 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
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
        this.ExportExcel(datas, contextJO,paramJO,  $event, xData,this,"ProjectStats");
    }

    /**
     * 打开新建数据视图
     *
     * @param {any[]} args
     * @param {*} [params]
     * @param {*} [fullargs]
     * @param {*} [$event]
     * @param {*} [xData]
     * @memberof ProjectStatsProjectQualityUsr2GridView
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
        delete tempContext.projectstats;
        if(args.length >0){
            Object.assign(tempContext,args[0]);
        }
        const deResParameters: any[] = [];
        const parameters: any[] = [
            { pathName: 'projectstats', parameterName: 'projectstats' },
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
     * @memberof ProjectStatsProjectQualityUsr2GridView
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
            { pathName: 'projectstats', parameterName: 'projectstats' },
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
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
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
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
     */
    protected isGridSingleSelect: boolean = true;

    /**
     * 表格行数据默认激活模式
     * 0 不激活
     * 1 单击激活
     * 2 双击激活
     *
     * @protected
     * @type {(0 | 1 | 2)}
     * @memberof ProjectStatsProjectQualityUsr2GridViewBase
     */
    protected gridRowActiveMode: 0 | 1 | 2 = 0;
}