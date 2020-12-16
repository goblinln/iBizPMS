
import { Subject } from 'rxjs';
import { UIActionTool, ViewTool } from '@/utils';
import { ListViewBase } from '@/studio-core';
import IBIZPRO_INDEXService from '@/service/ibizpro-index/ibizpro-index-service';
import IBIZPRO_INDEXAuthService from '@/authservice/ibizpro-index/ibizpro-index-auth-service';
import ListViewEngine from '@engine/view/list-view-engine';
import IBIZPRO_INDEXUIService from '@/uiservice/ibizpro-index/ibizpro-index-ui-service';
import CodeListService from '@service/app/codelist-service';


/**
 * 索引检索列表视图视图基类
 *
 * @export
 * @class IBIZPRO_INDEXListViewBase
 * @extends {ListViewBase}
 */
export class IBIZPRO_INDEXListViewBase extends ListViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof IBIZPRO_INDEXListViewBase
     */
    protected appDeName: string = 'ibizpro_index';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof IBIZPRO_INDEXListViewBase
     */
    protected appDeKey: string = 'indexid';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof IBIZPRO_INDEXListViewBase
     */
    protected appDeMajor: string = 'indexname';

    /**
     * 数据部件名称
     *
     * @protected
     * @type {string}
     * @memberof IBIZPRO_INDEXListViewBase
     */ 
    protected dataControl: string = 'list';

    /**
     * 实体服务对象
     *
     * @type {IBIZPRO_INDEXService}
     * @memberof IBIZPRO_INDEXListViewBase
     */
    protected appEntityService: IBIZPRO_INDEXService = new IBIZPRO_INDEXService;

    /**
     * 实体权限服务对象
     *
     * @type IBIZPRO_INDEXUIService
     * @memberof IBIZPRO_INDEXListViewBase
     */
    public appUIService: IBIZPRO_INDEXUIService = new IBIZPRO_INDEXUIService(this.$store);

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof IBIZPRO_INDEXListViewBase
     */
    protected model: any = {
        srfCaption: 'entities.ibizpro_index.views.listview.caption',
        srfTitle: 'entities.ibizpro_index.views.listview.title',
        srfSubTitle: 'entities.ibizpro_index.views.listview.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof IBIZPRO_INDEXListViewBase
     */
    protected containerModel: any = {
        view_list: {
            name: 'list',
            type: 'LIST',
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
     * @memberof IBIZPRO_INDEXListViewBase
     */
	protected viewtag: string = '10aa6afcd73e77c44c698319a1b4edcd';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof IBIZPRO_INDEXListViewBase
     */ 
    protected viewName: string = 'IBIZPRO_INDEXListView';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof IBIZPRO_INDEXListViewBase
     */
    public engine: ListViewEngine = new ListViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof IBIZPRO_INDEXListViewBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof IBIZPRO_INDEXListViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            list: this.$refs.list,
            opendata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                this.opendata(args, fullargs, params, $event, xData);
            },
            newdata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                this.newdata(args, fullargs, params, $event, xData);
            },
            searchform: this.$refs.searchform,
            keyPSDEField: 'ibizpro_index',
            majorPSDEField: 'indexname',
            isLoadDefault: true,
        });
    }

    /**
     * list 部件 selectionchange 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IBIZPRO_INDEXListViewBase
     */
    public list_selectionchange($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('list', 'selectionchange', $event);
    }

    /**
     * list 部件 beforeload 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IBIZPRO_INDEXListViewBase
     */
    public list_beforeload($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('list', 'beforeload', $event);
    }

    /**
     * list 部件 rowdblclick 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IBIZPRO_INDEXListViewBase
     */
    public list_rowdblclick($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('list', 'rowdblclick', $event);
    }

    /**
     * list 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IBIZPRO_INDEXListViewBase
     */
    public list_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('list', 'remove', $event);
    }

    /**
     * list 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IBIZPRO_INDEXListViewBase
     */
    public list_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('list', 'load', $event);
    }

    /**
     * searchform 部件 save 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IBIZPRO_INDEXListViewBase
     */
    public searchform_save($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('searchform', 'save', $event);
    }

    /**
     * searchform 部件 search 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IBIZPRO_INDEXListViewBase
     */
    public searchform_search($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('searchform', 'search', $event);
    }

    /**
     * searchform 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof IBIZPRO_INDEXListViewBase
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
     * @memberof IBIZPRO_INDEXListView
     */
    public newdata(args: any[],fullargs?:any[], params?: any, $event?: any, xData?: any) {
        let localContext:any = null;
        let localViewParam:any =null;
        let wizardAppView:any = {viewname:'ibizpro-indexindex-pickup-view',height: 0,width: 0,title: '索引检索数据选择视图'};
        let container: Subject<any> = this.$appmodal.openModal(wizardAppView, JSON.parse(JSON.stringify(this.context)), args[0]);
        container.subscribe((result: any) => {
            if (!result || !Object.is(result.ret, 'OK')) {
                return;
            }
            if(result && result.datas && result.datas.length >0 ){
                let targetkey:string = result.datas[0].srfkey;
                let newDataAppViews:any ={
                task:[{ pathName: 'tasks', parameterName: 'task' },{ pathName: 'editview', parameterName: 'editview' }],
                story:[{ pathName: 'stories', parameterName: 'story' },{ pathName: 'editview', parameterName: 'editview' }],
                product:[{ pathName: 'products', parameterName: 'product' },{ pathName: 'editview', parameterName: 'editview' }],
                project:[{ pathName: 'projects', parameterName: 'project' },{ pathName: 'editview', parameterName: 'editview' }],
                bug:[{ pathName: 'bugs', parameterName: 'bug' },{ pathName: 'editview', parameterName: 'editview' }],
                case:[{ pathName: 'cases', parameterName: 'case' },{ pathName: 'editview', parameterName: 'editview' }]
                };
                const data: any = {};
                if(args[0].srfsourcekey) data.srfsourcekey = args[0].srfsourcekey;
                let tempContext = JSON.parse(JSON.stringify(this.context));
                const openIndexViewTab = (data: any) => {
                    const _data: any = { w: (new Date().getTime()) };
                    Object.assign(_data, data);
                    const routePath = this.$viewTool.buildUpRoutePath(this.$route, tempContext, [], newDataAppViews[targetkey], args, _data);
                    this.$router.push(routePath);
                }
                openIndexViewTab(data);
            }
        })
    }


    /**
     * 打开编辑数据视图
     *
     * @param {any[]} args
     * @param {*} [params]
     * @param {*} [fullargs]
     * @param {*} [$event]
     * @param {*} [xData]
     * @memberof IBIZPRO_INDEXListView
     */
    public opendata(args: any[],fullargs?:any[],params?: any, $event?: any, xData?: any) {
    this.$Notice.warning({ title: '错误', desc: '未指定关系视图' });
    }


}