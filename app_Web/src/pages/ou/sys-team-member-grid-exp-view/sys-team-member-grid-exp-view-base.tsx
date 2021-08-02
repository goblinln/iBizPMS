import { Subject } from 'rxjs';
import { UIActionTool, ViewTool, Util } from '@/utils';
import { GridExpViewBase } from '@/studio-core';
import SysTeamMemberService from '@/service/sys-team-member/sys-team-member-service';
import SysTeamMemberAuthService from '@/authservice/sys-team-member/sys-team-member-auth-service';
import GridExpViewEngine from '@engine/view/grid-exp-view-engine';
import SysTeamMemberUIService from '@/uiservice/sys-team-member/sys-team-member-ui-service';

/**
 * 组成员表格导航视图视图基类
 *
 * @export
 * @class SysTeamMemberGridExpViewBase
 * @extends {GridExpViewBase}
 */
export class SysTeamMemberGridExpViewBase extends GridExpViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof SysTeamMemberGridExpViewBase
     */
    protected appDeName: string = 'systeammember';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof SysTeamMemberGridExpViewBase
     */
    protected appDeKey: string = 'teammemberid';
// 应用实体「SysTeamMember」未设置主文本属性

    /**
     * 实体服务对象
     *
     * @type {SysTeamMemberService}
     * @memberof SysTeamMemberGridExpViewBase
     */
    protected appEntityService: SysTeamMemberService = new SysTeamMemberService;

    /**
     * 实体权限服务对象
     *
     * @type SysTeamMemberUIService
     * @memberof SysTeamMemberGridExpViewBase
     */
    public appUIService: SysTeamMemberUIService = new SysTeamMemberUIService(this.$store);

	/**
	 * 自定义视图导航参数集合
	 *
     * @protected
	 * @type {*}
	 * @memberof SysTeamMemberGridExpViewBase
	 */
    protected customViewParams: any = {
        'n_postid_eq': {
            isRawValue: false,
            value: 'srfparentkey',
        }
    };

    /**
     * 是否显示信息栏
     *
     * @memberof SysTeamMemberGridExpViewBase
     */
    isShowDataInfoBar: boolean = true;

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof SysTeamMemberGridExpViewBase
     */
    protected model: any = {
        srfCaption: 'entities.systeammember.views.gridexpview.caption',
        srfTitle: 'entities.systeammember.views.gridexpview.title',
        srfSubTitle: 'entities.systeammember.views.gridexpview.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof SysTeamMemberGridExpViewBase
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
     * @memberof SysTeamMemberGridExpViewBase
     */
	protected viewtag: string = '6cd4488bcc889f01236d20be8d2f5786';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof SysTeamMemberGridExpViewBase
     */ 
    protected viewName: string = 'SysTeamMemberGridExpView';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof SysTeamMemberGridExpViewBase
     */
    public engine: GridExpViewEngine = new GridExpViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof SysTeamMemberGridExpViewBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof SysTeamMemberGridExpViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            gridexpbar: this.$refs.gridexpbar,
            keyPSDEField: 'systeammember',
            isLoadDefault: true,
        });
    }

    /**
     * gridexpbar 部件 selectionchange 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysTeamMemberGridExpViewBase
     */
    public gridexpbar_selectionchange($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('gridexpbar', 'selectionchange', $event);
    }

    /**
     * gridexpbar 部件 activated 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysTeamMemberGridExpViewBase
     */
    public gridexpbar_activated($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('gridexpbar', 'activated', $event);
    }

    /**
     * gridexpbar 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysTeamMemberGridExpViewBase
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
     * @memberof SysTeamMemberGridExpView
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
     * @memberof SysTeamMemberGridExpView
     */
    public opendata(args: any[],fullargs?:any[],params?: any, $event?: any, xData?: any) {
    this.$Notice.warning({ title: '错误', desc: '未指定关系视图' });
    }



    /**
     * 视图唯一标识
     *
     * @type {string}
     * @memberof SysTeamMemberGridExpView
     */
    public viewUID: string = 'ou-sys-team-member-grid-exp-view';

    /**
     * 导航数据部件选中数据
     *
     * @type {*}
     * @memberof SysTeamMemberGridExpView
     */
    public selectionData: any[] = [];

    /**
     * 获取数据
     *
     * @type {string}
     * @memberof SysTeamMemberGridExpView
     */
    public getDatas() {
        return this.selectionData;
    }


}