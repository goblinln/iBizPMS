import { Subject } from 'rxjs';
import { UIActionTool, ViewTool, Util } from '@/utils';
import { GridExpViewBase } from '@/studio-core';
import SysEmployeeService from '@/service/sys-employee/sys-employee-service';
import SysEmployeeAuthService from '@/authservice/sys-employee/sys-employee-auth-service';
import GridExpViewEngine from '@engine/view/grid-exp-view-engine';
import SysEmployeeUIService from '@/uiservice/sys-employee/sys-employee-ui-service';

/**
 * 人员表格导航视图视图基类
 *
 * @export
 * @class SysEmployeeGridExpViewProjectMemberBase
 * @extends {GridExpViewBase}
 */
export class SysEmployeeGridExpViewProjectMemberBase extends GridExpViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof SysEmployeeGridExpViewProjectMemberBase
     */
    protected appDeName: string = 'sysemployee';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof SysEmployeeGridExpViewProjectMemberBase
     */
    protected appDeKey: string = 'userid';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof SysEmployeeGridExpViewProjectMemberBase
     */
    protected appDeMajor: string = 'personname';

    /**
     * 实体服务对象
     *
     * @type {SysEmployeeService}
     * @memberof SysEmployeeGridExpViewProjectMemberBase
     */
    protected appEntityService: SysEmployeeService = new SysEmployeeService;

    /**
     * 实体权限服务对象
     *
     * @type SysEmployeeUIService
     * @memberof SysEmployeeGridExpViewProjectMemberBase
     */
    public appUIService: SysEmployeeUIService = new SysEmployeeUIService(this.$store);

    /**
     * 是否显示信息栏
     *
     * @memberof SysEmployeeGridExpViewProjectMemberBase
     */
    isShowDataInfoBar: boolean = true;

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof SysEmployeeGridExpViewProjectMemberBase
     */
    protected model: any = {
        srfCaption: 'entities.sysemployee.views.gridexpviewprojectmember.caption',
        srfTitle: 'entities.sysemployee.views.gridexpviewprojectmember.title',
        srfSubTitle: 'entities.sysemployee.views.gridexpviewprojectmember.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof SysEmployeeGridExpViewProjectMemberBase
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
     * @memberof SysEmployeeGridExpViewProjectMemberBase
     */
	protected viewtag: string = '725bda9b64e41595bdb7bca3385b73d5';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof SysEmployeeGridExpViewProjectMemberBase
     */ 
    protected viewName: string = 'SysEmployeeGridExpViewProjectMember';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof SysEmployeeGridExpViewProjectMemberBase
     */
    public engine: GridExpViewEngine = new GridExpViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof SysEmployeeGridExpViewProjectMemberBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof SysEmployeeGridExpViewProjectMemberBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            gridexpbar: this.$refs.gridexpbar,
            keyPSDEField: 'sysemployee',
            majorPSDEField: 'personname',
            isLoadDefault: true,
        });
    }

    /**
     * gridexpbar 部件 selectionchange 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysEmployeeGridExpViewProjectMemberBase
     */
    public gridexpbar_selectionchange($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('gridexpbar', 'selectionchange', $event);
    }

    /**
     * gridexpbar 部件 activated 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysEmployeeGridExpViewProjectMemberBase
     */
    public gridexpbar_activated($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('gridexpbar', 'activated', $event);
    }

    /**
     * gridexpbar 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysEmployeeGridExpViewProjectMemberBase
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
     * @memberof SysEmployeeGridExpViewProjectMember
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
     * @memberof SysEmployeeGridExpViewProjectMember
     */
    public opendata(args: any[],fullargs?:any[],params?: any, $event?: any, xData?: any) {
    this.$Notice.warning({ title: '错误', desc: '未指定关系视图' });
    }



    /**
     * 视图唯一标识
     *
     * @type {string}
     * @memberof SysEmployeeGridExpViewProjectMember
     */
    public viewUID: string = 'zentao-sys-employee-grid-exp-view-project-member';

    /**
     * 导航数据部件选中数据
     *
     * @type {*}
     * @memberof SysEmployeeGridExpViewProjectMember
     */
    public selectionData: any[] = [];

    /**
     * 获取数据
     *
     * @type {string}
     * @memberof SysEmployeeGridExpViewProjectMember
     */
    public getDatas() {
        return this.selectionData;
    }


}