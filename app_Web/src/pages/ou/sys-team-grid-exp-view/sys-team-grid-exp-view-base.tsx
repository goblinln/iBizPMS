import { Subject } from 'rxjs';
import { UIActionTool, ViewTool, Util } from '@/utils';
import { GridExpViewBase } from '@/studio-core';
import SysTeamService from '@/service/sys-team/sys-team-service';
import SysTeamAuthService from '@/authservice/sys-team/sys-team-auth-service';
import GridExpViewEngine from '@engine/view/grid-exp-view-engine';
import SysTeamUIService from '@/uiservice/sys-team/sys-team-ui-service';

/**
 * 组表格导航视图视图基类
 *
 * @export
 * @class SysTeamGridExpViewBase
 * @extends {GridExpViewBase}
 */
export class SysTeamGridExpViewBase extends GridExpViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof SysTeamGridExpViewBase
     */
    protected appDeName: string = 'systeam';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof SysTeamGridExpViewBase
     */
    protected appDeKey: string = 'teamid';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof SysTeamGridExpViewBase
     */
    protected appDeMajor: string = 'teamname';

    /**
     * 实体服务对象
     *
     * @type {SysTeamService}
     * @memberof SysTeamGridExpViewBase
     */
    protected appEntityService: SysTeamService = new SysTeamService;

    /**
     * 实体权限服务对象
     *
     * @type SysTeamUIService
     * @memberof SysTeamGridExpViewBase
     */
    public appUIService: SysTeamUIService = new SysTeamUIService(this.$store);

    /**
     * 是否显示信息栏
     *
     * @memberof SysTeamGridExpViewBase
     */
    isShowDataInfoBar: boolean = true;

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof SysTeamGridExpViewBase
     */
    protected model: any = {
        srfCaption: 'entities.systeam.views.gridexpview.caption',
        srfTitle: 'entities.systeam.views.gridexpview.title',
        srfSubTitle: 'entities.systeam.views.gridexpview.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof SysTeamGridExpViewBase
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
     * @memberof SysTeamGridExpViewBase
     */
	protected viewtag: string = '4a861140b27c2972c173b07a4d00b7e1';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof SysTeamGridExpViewBase
     */ 
    protected viewName: string = 'SysTeamGridExpView';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof SysTeamGridExpViewBase
     */
    public engine: GridExpViewEngine = new GridExpViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof SysTeamGridExpViewBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof SysTeamGridExpViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            gridexpbar: this.$refs.gridexpbar,
            keyPSDEField: 'systeam',
            majorPSDEField: 'teamname',
            isLoadDefault: true,
        });
    }

    /**
     * gridexpbar 部件 selectionchange 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysTeamGridExpViewBase
     */
    public gridexpbar_selectionchange($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('gridexpbar', 'selectionchange', $event);
    }

    /**
     * gridexpbar 部件 activated 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysTeamGridExpViewBase
     */
    public gridexpbar_activated($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('gridexpbar', 'activated', $event);
    }

    /**
     * gridexpbar 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysTeamGridExpViewBase
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
     * @memberof SysTeamGridExpView
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
     * @memberof SysTeamGridExpView
     */
    public opendata(args: any[],fullargs?:any[],params?: any, $event?: any, xData?: any) {
    this.$Notice.warning({ title: '错误', desc: '未指定关系视图' });
    }



    /**
     * 视图唯一标识
     *
     * @type {string}
     * @memberof SysTeamGridExpView
     */
    public viewUID: string = 'ou-sys-team-grid-exp-view';

    /**
     * 导航数据部件选中数据
     *
     * @type {*}
     * @memberof SysTeamGridExpView
     */
    public selectionData: any[] = [];

    /**
     * 获取数据
     *
     * @type {string}
     * @memberof SysTeamGridExpView
     */
    public getDatas() {
        return this.selectionData;
    }


}