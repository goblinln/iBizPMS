
import { Subject } from 'rxjs';
import { UIActionTool, ViewTool } from '@/utils';
import { GridViewBase } from '@/studio-core';
import IbizproProductWeeklyService from '@/service/ibizpro-product-weekly/ibizpro-product-weekly-service';
import IbizproProductWeeklyAuthService from '@/authservice/ibizpro-product-weekly/ibizpro-product-weekly-auth-service';
import GridViewEngine from '@engine/view/grid-view-engine';
import IbizproProductWeeklyUIService from '@/uiservice/ibizpro-product-weekly/ibizpro-product-weekly-ui-service';
import CodeListService from '@service/app/codelist-service';


/**
 * 产品周报表格视图视图基类
 *
 * @export
 * @class IbizproProductWeeklyUsr2GridViewBase
 * @extends {GridViewBase}
 */
export class IbizproProductWeeklyUsr2GridViewBase extends GridViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */
    protected appDeName: string = 'ibizproproductweekly';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */
    protected appDeKey: string = 'ibizpro_productweeklyid';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */
    protected appDeMajor: string = 'ibizpro_productdailyname';

    /**
     * 数据部件名称
     *
     * @protected
     * @type {string}
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */ 
    protected dataControl: string = 'grid';

    /**
     * 实体服务对象
     *
     * @type {IbizproProductWeeklyService}
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */
    protected appEntityService: IbizproProductWeeklyService = new IbizproProductWeeklyService;

    /**
     * 实体权限服务对象
     *
     * @type IbizproProductWeeklyUIService
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */
    public appUIService: IbizproProductWeeklyUIService = new IbizproProductWeeklyUIService(this.$store);

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */
    protected model: any = {
        srfCaption: 'entities.ibizproproductweekly.views.usr2gridview.caption',
        srfTitle: 'entities.ibizproproductweekly.views.usr2gridview.title',
        srfSubTitle: 'entities.ibizproproductweekly.views.usr2gridview.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */
    protected containerModel: any = {
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
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */
	protected viewtag: string = 'e49ea1cccee579b8b15d125583fa6fff';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */ 
    protected viewName: string = 'IbizproProductWeeklyUsr2GridView';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */
    public engine: GridViewEngine = new GridViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof IbizproProductWeeklyUsr2GridViewBase
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
            keyPSDEField: 'ibizproproductweekly',
            majorPSDEField: 'ibizproproductweeklyname',
            isLoadDefault: true,
        });
    }

    /**
     * grid 部件 selectionchange 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */
    public grid_selectionchange($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'selectionchange', $event);
    }

    /**
     * grid 部件 beforeload 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */
    public grid_beforeload($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'beforeload', $event);
    }

    /**
     * grid 部件 rowdblclick 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */
    public grid_rowdblclick($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'rowdblclick', $event);
    }

    /**
     * grid 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */
    public grid_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'remove', $event);
    }

    /**
     * grid 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */
    public grid_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('grid', 'load', $event);
    }

    /**
     * searchform 部件 save 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */
    public searchform_save($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('searchform', 'save', $event);
    }

    /**
     * searchform 部件 search 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IbizproProductWeeklyUsr2GridViewBase
     */
    public searchform_search($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('searchform', 'search', $event);
    }

    /**
     * searchform 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IbizproProductWeeklyUsr2GridViewBase
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
     * @memberof IbizproProductWeeklyUsr2GridView
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
        delete tempContext.ibizproproductweekly;
        if(args.length >0){
            Object.assign(tempContext,args[0]);
        }
        const deResParameters: any[] = [];
        const parameters: any[] = [
            { pathName: 'ibizproproductweeklies', parameterName: 'ibizproproductweekly' },
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
            viewname: 'ibizpro-product-weekly-edit-view', 
            height: 0, 
            width: 0,  
            title: this.$t('entities.ibizproproductweekly.views.editview.title'),
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
     * @memberof IbizproProductWeeklyUsr2GridView
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
            { pathName: 'ibizproproductweeklies', parameterName: 'ibizproproductweekly' },
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
            viewname: 'ibizpro-product-weekly-edit-view', 
            height: 0, 
            width: 0,  
            title: this.$t('entities.ibizproproductweekly.views.editview.title'),
            placement: 'DRAWER_TOP',
        };
        openDrawer(view, data);
    }


}