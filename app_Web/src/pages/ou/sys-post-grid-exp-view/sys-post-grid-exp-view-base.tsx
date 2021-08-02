import { Subject } from 'rxjs';
import { UIActionTool, ViewTool, Util } from '@/utils';
import { GridExpViewBase } from '@/studio-core';
import SysPostService from '@/service/sys-post/sys-post-service';
import SysPostAuthService from '@/authservice/sys-post/sys-post-auth-service';
import GridExpViewEngine from '@engine/view/grid-exp-view-engine';
import SysPostUIService from '@/uiservice/sys-post/sys-post-ui-service';

/**
 * 岗位表格导航视图视图基类
 *
 * @export
 * @class SysPostGridExpViewBase
 * @extends {GridExpViewBase}
 */
export class SysPostGridExpViewBase extends GridExpViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof SysPostGridExpViewBase
     */
    protected appDeName: string = 'syspost';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof SysPostGridExpViewBase
     */
    protected appDeKey: string = 'postid';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof SysPostGridExpViewBase
     */
    protected appDeMajor: string = 'postname';

    /**
     * 实体服务对象
     *
     * @type {SysPostService}
     * @memberof SysPostGridExpViewBase
     */
    protected appEntityService: SysPostService = new SysPostService;

    /**
     * 实体权限服务对象
     *
     * @type SysPostUIService
     * @memberof SysPostGridExpViewBase
     */
    public appUIService: SysPostUIService = new SysPostUIService(this.$store);

    /**
     * 是否显示信息栏
     *
     * @memberof SysPostGridExpViewBase
     */
    isShowDataInfoBar: boolean = true;

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof SysPostGridExpViewBase
     */
    protected model: any = {
        srfCaption: 'entities.syspost.views.gridexpview.caption',
        srfTitle: 'entities.syspost.views.gridexpview.title',
        srfSubTitle: 'entities.syspost.views.gridexpview.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof SysPostGridExpViewBase
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
     * @memberof SysPostGridExpViewBase
     */
	protected viewtag: string = '84ad1f110ad2f5100d78d5a23c67fd02';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof SysPostGridExpViewBase
     */ 
    protected viewName: string = 'SysPostGridExpView';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof SysPostGridExpViewBase
     */
    public engine: GridExpViewEngine = new GridExpViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof SysPostGridExpViewBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof SysPostGridExpViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            gridexpbar: this.$refs.gridexpbar,
            keyPSDEField: 'syspost',
            majorPSDEField: 'postname',
            isLoadDefault: true,
        });
    }

    /**
     * gridexpbar 部件 selectionchange 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysPostGridExpViewBase
     */
    public gridexpbar_selectionchange($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('gridexpbar', 'selectionchange', $event);
    }

    /**
     * gridexpbar 部件 activated 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysPostGridExpViewBase
     */
    public gridexpbar_activated($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('gridexpbar', 'activated', $event);
    }

    /**
     * gridexpbar 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysPostGridExpViewBase
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
     * @memberof SysPostGridExpView
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
     * @memberof SysPostGridExpView
     */
    public opendata(args: any[],fullargs?:any[],params?: any, $event?: any, xData?: any) {
    this.$Notice.warning({ title: '错误', desc: '未指定关系视图' });
    }



    /**
     * 视图唯一标识
     *
     * @type {string}
     * @memberof SysPostGridExpView
     */
    public viewUID: string = 'ou-sys-post-grid-exp-view';

    /**
     * 导航数据部件选中数据
     *
     * @type {*}
     * @memberof SysPostGridExpView
     */
    public selectionData: any[] = [];

    /**
     * 获取数据
     *
     * @type {string}
     * @memberof SysPostGridExpView
     */
    public getDatas() {
        return this.selectionData;
    }


}