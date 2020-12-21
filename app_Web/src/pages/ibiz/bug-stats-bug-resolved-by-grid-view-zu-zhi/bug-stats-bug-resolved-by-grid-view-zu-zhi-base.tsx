
import { Subject } from 'rxjs';
import { UIActionTool, ViewTool } from '@/utils';
import { GridViewBase } from '@/studio-core';
import BugStatsService from '@/service/bug-stats/bug-stats-service';
import BugStatsAuthService from '@/authservice/bug-stats/bug-stats-auth-service';
import GridViewEngine from '@engine/view/grid-view-engine';
import BugStatsUIService from '@/uiservice/bug-stats/bug-stats-ui-service';
import CodeListService from '@service/app/codelist-service';


/**
 * Bug统计表格视图（Bug完成表）视图基类
 *
 * @export
 * @class BugStatsBugResolvedByGridViewZuZhiBase
 * @extends {GridViewBase}
 */
export class BugStatsBugResolvedByGridViewZuZhiBase extends GridViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */
    protected appDeName: string = 'bugstats';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */
    protected appDeMajor: string = 'title';

    /**
     * 数据部件名称
     *
     * @protected
     * @type {string}
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */ 
    protected dataControl: string = 'grid';

    /**
     * 实体服务对象
     *
     * @type {BugStatsService}
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */
    protected appEntityService: BugStatsService = new BugStatsService;

    /**
     * 实体权限服务对象
     *
     * @type BugStatsUIService
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */
    public appUIService: BugStatsUIService = new BugStatsUIService(this.$store);

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */
    protected model: any = {
        srfCaption: 'entities.bugstats.views.bugresolvedbygridviewzuzhi.caption',
        srfTitle: 'entities.bugstats.views.bugresolvedbygridviewzuzhi.title',
        srfSubTitle: 'entities.bugstats.views.bugresolvedbygridviewzuzhi.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
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
     * @memberof BugStatsBugResolvedByGridViewZuZhi
     */
    public toolBarModels: any = {
    };



	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */
	protected viewtag: string = 'd5d237bd6a57b89a912bcff2a13f1ff4';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */ 
    protected viewName: string = 'BugStatsBugResolvedByGridViewZuZhi';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */
    public engine: GridViewEngine = new GridViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
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
            keyPSDEField: 'bugstats',
            majorPSDEField: 'title',
            isLoadDefault: true,
        });
    }

    /**
     * grid 部件 selectionchange 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */
    public grid_selectionchange($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'selectionchange', $event);
    }

    /**
     * grid 部件 beforeload 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */
    public grid_beforeload($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'beforeload', $event);
    }

    /**
     * grid 部件 rowdblclick 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */
    public grid_rowdblclick($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'rowdblclick', $event);
    }

    /**
     * grid 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */
    public grid_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'remove', $event);
    }

    /**
     * grid 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */
    public grid_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'load', $event);
    }

    /**
     * searchform 部件 save 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */
    public searchform_save($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('searchform', 'save', $event);
    }

    /**
     * searchform 部件 search 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */
    public searchform_search($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('searchform', 'search', $event);
    }

    /**
     * searchform 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
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
     * @memberof BugStatsBugResolvedByGridViewZuZhi
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
        delete tempContext.bugstats;
        if(args.length >0){
            Object.assign(tempContext,args[0]);
        }
        const deResParameters: any[] = [];
        const parameters: any[] = [
            { pathName: 'bugstats', parameterName: 'bugstats' },
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
     * @memberof BugStatsBugResolvedByGridViewZuZhi
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
            { pathName: 'bugstats', parameterName: 'bugstats' },
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
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */
    protected isGridSingleSelect: boolean = true;

    /**
     * 是否展开搜索表单
     *
     * @protected
     * @type {boolean}
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
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
     * @memberof BugStatsBugResolvedByGridViewZuZhiBase
     */
    protected gridRowActiveMode: 0 | 1 | 2 = 0;
}