import { Subject } from 'rxjs';
import { UIActionTool, ViewTool, Util } from '@/utils';
import { GridExpViewBase } from '@/studio-core';
import SysOrganizationService from '@/service/sys-organization/sys-organization-service';
import SysOrganizationAuthService from '@/authservice/sys-organization/sys-organization-auth-service';
import GridExpViewEngine from '@engine/view/grid-exp-view-engine';
import SysOrganizationUIService from '@/uiservice/sys-organization/sys-organization-ui-service';

/**
 * 单位表格导航视图视图基类
 *
 * @export
 * @class SysOrganizationGridExpViewBase
 * @extends {GridExpViewBase}
 */
export class SysOrganizationGridExpViewBase extends GridExpViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof SysOrganizationGridExpViewBase
     */
    protected appDeName: string = 'sysorganization';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof SysOrganizationGridExpViewBase
     */
    protected appDeKey: string = 'orgid';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof SysOrganizationGridExpViewBase
     */
    protected appDeMajor: string = 'orgname';

    /**
     * 实体服务对象
     *
     * @type {SysOrganizationService}
     * @memberof SysOrganizationGridExpViewBase
     */
    protected appEntityService: SysOrganizationService = new SysOrganizationService;

    /**
     * 实体权限服务对象
     *
     * @type SysOrganizationUIService
     * @memberof SysOrganizationGridExpViewBase
     */
    public appUIService: SysOrganizationUIService = new SysOrganizationUIService(this.$store);

    /**
     * 是否显示信息栏
     *
     * @memberof SysOrganizationGridExpViewBase
     */
    isShowDataInfoBar: boolean = true;

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof SysOrganizationGridExpViewBase
     */
    protected model: any = {
        srfCaption: 'entities.sysorganization.views.gridexpview.caption',
        srfTitle: 'entities.sysorganization.views.gridexpview.title',
        srfSubTitle: 'entities.sysorganization.views.gridexpview.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof SysOrganizationGridExpViewBase
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
     * @memberof SysOrganizationGridExpViewBase
     */
	protected viewtag: string = '349b5cf3162e27893fb220a362576e54';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof SysOrganizationGridExpViewBase
     */ 
    protected viewName: string = 'SysOrganizationGridExpView';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof SysOrganizationGridExpViewBase
     */
    public engine: GridExpViewEngine = new GridExpViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof SysOrganizationGridExpViewBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof SysOrganizationGridExpViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            gridexpbar: this.$refs.gridexpbar,
            keyPSDEField: 'sysorganization',
            majorPSDEField: 'orgname',
            isLoadDefault: true,
        });
    }

    /**
     * gridexpbar 部件 selectionchange 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysOrganizationGridExpViewBase
     */
    public gridexpbar_selectionchange($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('gridexpbar', 'selectionchange', $event);
    }

    /**
     * gridexpbar 部件 activated 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysOrganizationGridExpViewBase
     */
    public gridexpbar_activated($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('gridexpbar', 'activated', $event);
    }

    /**
     * gridexpbar 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysOrganizationGridExpViewBase
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
     * @memberof SysOrganizationGridExpView
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
     * @memberof SysOrganizationGridExpView
     */
    public opendata(args: any[],fullargs?:any[],params?: any, $event?: any, xData?: any) {
    this.$Notice.warning({ title: '错误', desc: '未指定关系视图' });
    }



    /**
     * 视图唯一标识
     *
     * @type {string}
     * @memberof SysOrganizationGridExpView
     */
    public viewUID: string = 'ou-sys-organization-grid-exp-view';

    /**
     * 导航数据部件选中数据
     *
     * @type {*}
     * @memberof SysOrganizationGridExpView
     */
    public selectionData: any[] = [];

    /**
     * 获取数据
     *
     * @type {string}
     * @memberof SysOrganizationGridExpView
     */
    public getDatas() {
        return this.selectionData;
    }


}