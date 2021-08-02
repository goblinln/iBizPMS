import { Subject } from 'rxjs';
import { UIActionTool, ViewTool, Util } from '@/utils';
import { GridExpViewBase } from '@/studio-core';
import ProjectService from '@/service/project/project-service';
import ProjectAuthService from '@/authservice/project/project-auth-service';
import GridExpViewEngine from '@engine/view/grid-exp-view-engine';
import ProjectUIService from '@/uiservice/project/project-ui-service';

/**
 * 项目表格导航视图视图基类
 *
 * @export
 * @class ProjectGridExpViewBase
 * @extends {GridExpViewBase}
 */
export class ProjectGridExpViewBase extends GridExpViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof ProjectGridExpViewBase
     */
    protected appDeName: string = 'project';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof ProjectGridExpViewBase
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof ProjectGridExpViewBase
     */
    protected appDeMajor: string = 'name';

    /**
     * 实体服务对象
     *
     * @type {ProjectService}
     * @memberof ProjectGridExpViewBase
     */
    protected appEntityService: ProjectService = new ProjectService;

    /**
     * 实体权限服务对象
     *
     * @type ProjectUIService
     * @memberof ProjectGridExpViewBase
     */
    public appUIService: ProjectUIService = new ProjectUIService(this.$store);

    /**
     * 是否显示信息栏
     *
     * @memberof ProjectGridExpViewBase
     */
    isShowDataInfoBar: boolean = true;

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof ProjectGridExpViewBase
     */
    protected model: any = {
        srfCaption: 'entities.project.views.gridexpview.caption',
        srfTitle: 'entities.project.views.gridexpview.title',
        srfSubTitle: 'entities.project.views.gridexpview.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof ProjectGridExpViewBase
     */
    protected containerModel: any = {
        view_gridexpbar: {
            name: 'gridexpbar',
            type: 'GRIDEXPBAR',
        },
    };


	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof ProjectGridExpViewBase
     */
	protected viewtag: string = '407b9d94c4c324b057e3ef37d1b343c8';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof ProjectGridExpViewBase
     */ 
    protected viewName: string = 'ProjectGridExpView';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof ProjectGridExpViewBase
     */
    public engine: GridExpViewEngine = new GridExpViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof ProjectGridExpViewBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof ProjectGridExpViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            gridexpbar: this.$refs.gridexpbar,
            keyPSDEField: 'project',
            majorPSDEField: 'name',
            isLoadDefault: true,
        });
    }

    /**
     * gridexpbar 部件 selectionchange 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProjectGridExpViewBase
     */
    public gridexpbar_selectionchange($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('gridexpbar', 'selectionchange', $event);
    }

    /**
     * gridexpbar 部件 activated 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProjectGridExpViewBase
     */
    public gridexpbar_activated($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('gridexpbar', 'activated', $event);
    }

    /**
     * gridexpbar 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof ProjectGridExpViewBase
     */
    public gridexpbar_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('gridexpbar', 'load', $event);
    }

    /**
     * 打开新建数据视图
     *
     * @param {any[]} args
     * @param {*} [params]
     * @param {*} [fullargs]
     * @param {*} [$event]
     * @param {*} [xData]
     * @memberof ProjectGridExpView
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
     * @memberof ProjectGridExpView
     */
    public opendata(args: any[],fullargs?:any[],params?: any, $event?: any, xData?: any) {
    this.$Notice.warning({ title: '错误', desc: '未指定关系视图' });
    }



    /**
     * 视图唯一标识
     *
     * @type {string}
     * @memberof ProjectGridExpView
     */
    public viewUID: string = 'zentao-project-grid-exp-view';

    /**
     * 导航数据部件选中数据
     *
     * @type {*}
     * @memberof ProjectGridExpView
     */
    public selectionData: any[] = [];

    /**
     * 获取数据
     *
     * @type {string}
     * @memberof ProjectGridExpView
     */
    public getDatas() {
        return this.selectionData;
    }


}