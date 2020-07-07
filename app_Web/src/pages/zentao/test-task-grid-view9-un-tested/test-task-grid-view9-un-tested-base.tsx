
import { Subject } from 'rxjs';
import { GridView9Base } from '@/studio-core';
import TestTaskService from '@/service/test-task/test-task-service';
import GridView9Engine from '@engine/view/grid-view9-engine';
import CodeListService from "@service/app/codelist-service";


/**
 * 测试版本表格视图视图基类
 *
 * @export
 * @class TestTaskGridView9_UnTestedBase
 * @extends {GridView9Base}
 */
export class TestTaskGridView9_UnTestedBase extends GridView9Base {

    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof TestTaskGridView9_UnTestedBase
     */
    protected appDeName: string = 'testtask';

    /**
     * 实体服务对象
     *
     * @type {TestTaskService}
     * @memberof TestTaskGridView9_UnTestedBase
     */
    protected appEntityService: TestTaskService = new TestTaskService;


    /**
     * 计数器服务对象集合
     *
     * @protected
     * @type {Array<*>}
     * @memberof TestTaskGridView9_UnTestedBase
     */    
    protected counterServiceArray: Array<any> = [];

	/**
	 * 自定义视图导航参数集合
	 *
     * @protected
	 * @type {*}
	 * @memberof TestTaskGridView9_UnTestedBase
	 */
    protected customViewParams: any = {
        'n_status_eq': { isRawValue: true, value: 'wait' }
    };

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof TestTaskGridView9_UnTestedBase
     */
    protected model: any = {
        srfCaption: 'entities.testtask.views.gridview9_untested.caption',
        srfTitle: 'entities.testtask.views.gridview9_untested.title',
        srfSubTitle: 'entities.testtask.views.gridview9_untested.subtitle',
        dataInfo: ''
    }

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof TestTaskGridView9_UnTestedBase
     */
    protected containerModel: any = {
        view_grid: { name: 'grid', type: 'GRID' },
    };


	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof ViewBase
     */
	protected viewtag: string = '3091670967914b91fb4b6f46751dcd7e';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof TestTaskGridView9_UnTestedBase
     */
    public engine: GridView9Engine = new GridView9Engine();

    /**
     * 引擎初始化
     *
     * @public
     * @memberof TestTaskGridView9_UnTestedBase
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
            keyPSDEField: 'testtask',
            majorPSDEField: 'name',
            isLoadDefault: true,
        });
    }

    /**
     * grid 部件 selectionchange 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TestTaskGridView9_UnTestedBase
     */
    public grid_selectionchange($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'selectionchange', $event);
    }

    /**
     * grid 部件 beforeload 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TestTaskGridView9_UnTestedBase
     */
    public grid_beforeload($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'beforeload', $event);
    }

    /**
     * grid 部件 rowdblclick 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TestTaskGridView9_UnTestedBase
     */
    public grid_rowdblclick($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'rowdblclick', $event);
    }

    /**
     * grid 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TestTaskGridView9_UnTestedBase
     */
    public grid_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'remove', $event);
    }

    /**
     * grid 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TestTaskGridView9_UnTestedBase
     */
    public grid_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'load', $event);
    }

    /**
     * 打开新建数据视图
     *
     * @param {any[]} args
     * @param {*} [params]
     * @param {*} [fullargs]
     * @param {*} [$event]
     * @param {*} [xData]
     * @memberof TestTaskGridView9_UnTested
     */
    public newdata(args: any[],fullargs?:any[], params?: any, $event?: any, xData?: any) {
        let localContext:any = null;
        let localViewParam:any =null;
        const data: any = {};
        if(args[0].srfsourcekey){
            data.srfsourcekey = args[0].srfsourcekey;
        }
        let tempContext = JSON.parse(JSON.stringify(this.context));
        delete tempContext.testtask;
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
            { pathName: 'testtasks', parameterName: 'testtask' },
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
            viewname: 'test-task-edit-view', 
            height: 0, 
            width: 0,  
            title: this.$t('entities.testtask.views.editview.title'),
            placement: 'DRAWER_TOP',
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
     * @memberof TestTaskGridView9_UnTested
     */
    public opendata(args: any[],fullargs?:any[],params?: any, $event?: any, xData?: any) {
        let localContext:any = null;
        let localViewParam:any =null;
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
            { pathName: 'testtasks', parameterName: 'testtask' },
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
            viewname: 'test-task-edit-view', 
            height: 0, 
            width: 0,  
            title: this.$t('entities.testtask.views.editview.title'),
            placement: 'DRAWER_TOP',
        };
        openDrawer(view, data);
    }


}