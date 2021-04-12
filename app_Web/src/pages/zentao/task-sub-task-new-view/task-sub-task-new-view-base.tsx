
import { Subject } from 'rxjs';
import { UIActionTool, ViewTool, Util } from '@/utils';
import { GridViewBase } from '@/studio-core';
import SubTaskService from '@/service/sub-task/sub-task-service';
import SubTaskAuthService from '@/authservice/sub-task/sub-task-auth-service';
import GridViewEngine from '@engine/view/grid-view-engine';
import SubTaskUIService from '@/uiservice/sub-task/sub-task-ui-service';
import CodeListService from '@service/app/codelist-service';


/**
 * 子任务视图基类
 *
 * @export
 * @class TaskSubTaskNewViewBase
 * @extends {GridViewBase}
 */
export class TaskSubTaskNewViewBase extends GridViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof TaskSubTaskNewViewBase
     */
    protected appDeName: string = 'subtask';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof TaskSubTaskNewViewBase
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof TaskSubTaskNewViewBase
     */
    protected appDeMajor: string = 'name';

    /**
     * 数据部件名称
     *
     * @protected
     * @type {string}
     * @memberof TaskSubTaskNewViewBase
     */ 
    protected dataControl: string = 'grid';

    /**
     * 实体服务对象
     *
     * @type {SubTaskService}
     * @memberof TaskSubTaskNewViewBase
     */
    protected appEntityService: SubTaskService = new SubTaskService;

    /**
     * 实体权限服务对象
     *
     * @type SubTaskUIService
     * @memberof TaskSubTaskNewViewBase
     */
    public appUIService: SubTaskUIService = new SubTaskUIService(this.$store);

	/**
	 * 自定义视图导航上下文集合
	 *
     * @protected
	 * @type {*}
	 * @memberof TaskSubTaskNewViewBase
	 */
    protected customViewNavContexts: any = {
        'PROJECT': {
            isRawValue: false,
            value: 'project',
        }
    };

	/**
	 * 自定义视图导航参数集合
	 *
     * @protected
	 * @type {*}
	 * @memberof TaskSubTaskNewViewBase
	 */
    protected customViewParams: any = {
        'name': {
            isRawValue: true,
            value: 'null',
        }
    };

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof TaskSubTaskNewViewBase
     */
    protected model: any = {
        srfCaption: 'entities.subtask.views.subtasknewview.caption',
        srfTitle: 'entities.subtask.views.subtasknewview.title',
        srfSubTitle: 'entities.subtask.views.subtasknewview.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof TaskSubTaskNewViewBase
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
     * @memberof TaskSubTaskNewView
     */
    public toolBarModels: any = {
        deuiaction2: { name: 'deuiaction2', caption: 'entities.subtask.subtasknewviewtoolbar_toolbar.deuiaction2.caption', 'isShowCaption': true, 'isShowIcon': true, tooltip: 'entities.subtask.subtasknewviewtoolbar_toolbar.deuiaction2.tip', iconcls: 'fa fa-plus', icon: '', disabled: false, type: 'DEUIACTION', visible: true,noprivdisplaymode:2,dataaccaction: 'SRFUR__UNIVERSALCREATE', uiaction: { tag: 'NewRow', target: '', class: '' } },

        deuiaction3: { name: 'deuiaction3', caption: 'entities.subtask.subtasknewviewtoolbar_toolbar.deuiaction3.caption', 'isShowCaption': true, 'isShowIcon': true, tooltip: 'entities.subtask.subtasknewviewtoolbar_toolbar.deuiaction3.tip', iconcls: 'fa fa-save', icon: '', disabled: false, type: 'DEUIACTION', visible: true,noprivdisplaymode:2,dataaccaction: 'SRFUR__UNIVERSALSAVE', uiaction: { tag: 'SaveRow', target: '', class: '' } },

    };



	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof TaskSubTaskNewViewBase
     */
	protected viewtag: string = 'f6d0bb444705d74d6b39051dc8225eb9';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof TaskSubTaskNewViewBase
     */ 
    protected viewName: string = 'TaskSubTaskNewView';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof TaskSubTaskNewViewBase
     */
    public engine: GridViewEngine = new GridViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof TaskSubTaskNewViewBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof TaskSubTaskNewViewBase
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
            keyPSDEField: 'subtask',
            majorPSDEField: 'name',
            isLoadDefault: true,
        });
    }

    /**
     * toolbar 部件 click 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TaskSubTaskNewViewBase
     */
    public toolbar_click($event: any, $event2?: any): void {
        if (Object.is($event.tag, 'deuiaction2')) {
            this.toolbar_deuiaction2_click(null, '', $event2);
        }
        if (Object.is($event.tag, 'deuiaction3')) {
            this.toolbar_deuiaction3_click(null, '', $event2);
        }
    }

    /**
     * grid 部件 selectionchange 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TaskSubTaskNewViewBase
     */
    public grid_selectionchange($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'selectionchange', $event);
    }

    /**
     * grid 部件 beforeload 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TaskSubTaskNewViewBase
     */
    public grid_beforeload($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'beforeload', $event);
    }

    /**
     * grid 部件 rowdblclick 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TaskSubTaskNewViewBase
     */
    public grid_rowdblclick($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'rowdblclick', $event);
    }

    /**
     * grid 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TaskSubTaskNewViewBase
     */
    public grid_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'remove', $event);
    }

    /**
     * grid 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TaskSubTaskNewViewBase
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
        this.NewRow(datas, contextJO,paramJO,  $event, xData,this,"SubTask");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public toolbar_deuiaction3_click(params: any = {}, tag?: any, $event?: any) {
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
        this.SaveRow(datas, contextJO,paramJO,  $event, xData,this,"SubTask");
    }

    /**
     * 打开新建数据视图
     *
     * @param {any[]} args
     * @param {*} [params]
     * @param {*} [fullargs]
     * @param {*} [$event]
     * @param {*} [xData]
     * @memberof TaskSubTaskNewView
     */
    public newdata(args: any[],fullargs?:any[], params?: any, $event?: any, xData?: any) {
        let localContext:any = null;
        let localViewParam:any =null;
    this.$Notice.warning({ title: '错误', desc: '未指定关系视图' });
    }


    /**
     * 打开编辑数据视图
     *
     * @param {any[]} args
     * @param {*} [params]
     * @param {*} [fullargs]
     * @param {*} [$event]
     * @param {*} [xData]
     * @memberof TaskSubTaskNewView
     */
    public opendata(args: any[],fullargs?:any[],params?: any, $event?: any, xData?: any) {
    this.$Notice.warning({ title: '错误', desc: '未指定关系视图' });
    }


    /**
     * 新建行
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @memberof TaskSubTaskNewViewBase
     */
    public NewRow(args: any[],contextJO?:any, params?: any, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
        const _this: any = this;
        const data: any = {};
        if (_this.hasOwnProperty('newRow') && _this.newRow instanceof Function) {
            _this.newRow([{ ...data }], params, $event, xData);
        } else if(xData.newRow && xData.newRow instanceof Function) {
            xData.newRow([{ ...data }], params, $event, xData);
        }else{
            _this.$Notice.error({ title: '错误', desc: 'newRow 视图处理逻辑不存在，请添加!' });
        }
    }
    /**
     * 保存行
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @memberof TaskSubTaskNewViewBase
     */
    public SaveRow(args: any[],contextJO?:any, params?: any, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
        // 界面行为容器对象 _this
        const _this: any = this;
        if (xData && xData.save instanceof Function) {
            xData.save();
        } else if (_this.save && _this.save instanceof Function) {
            _this.save();
        }
    }
}