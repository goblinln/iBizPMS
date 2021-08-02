import { Subject } from 'rxjs';
import { UIActionTool, ViewTool, Util } from '@/utils';
import { GridExpViewBase } from '@/studio-core';
import UserContactService from '@/service/user-contact/user-contact-service';
import UserContactAuthService from '@/authservice/user-contact/user-contact-auth-service';
import GridExpViewEngine from '@engine/view/grid-exp-view-engine';
import UserContactUIService from '@/uiservice/user-contact/user-contact-ui-service';

/**
 * 用户联系方式表格导航视图视图基类
 *
 * @export
 * @class UserContactGridExpViewBase
 * @extends {GridExpViewBase}
 */
export class UserContactGridExpViewBase extends GridExpViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof UserContactGridExpViewBase
     */
    protected appDeName: string = 'usercontact';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof UserContactGridExpViewBase
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof UserContactGridExpViewBase
     */
    protected appDeMajor: string = 'listname';

    /**
     * 实体服务对象
     *
     * @type {UserContactService}
     * @memberof UserContactGridExpViewBase
     */
    protected appEntityService: UserContactService = new UserContactService;

    /**
     * 实体权限服务对象
     *
     * @type UserContactUIService
     * @memberof UserContactGridExpViewBase
     */
    public appUIService: UserContactUIService = new UserContactUIService(this.$store);

    /**
     * 是否显示信息栏
     *
     * @memberof UserContactGridExpViewBase
     */
    isShowDataInfoBar: boolean = true;

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof UserContactGridExpViewBase
     */
    protected model: any = {
        srfCaption: 'entities.usercontact.views.gridexpview.caption',
        srfTitle: 'entities.usercontact.views.gridexpview.title',
        srfSubTitle: 'entities.usercontact.views.gridexpview.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof UserContactGridExpViewBase
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
     * @memberof UserContactGridExpViewBase
     */
	protected viewtag: string = '4a012e784384c1f4ec3b0150e0885018';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof UserContactGridExpViewBase
     */ 
    protected viewName: string = 'UserContactGridExpView';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof UserContactGridExpViewBase
     */
    public engine: GridExpViewEngine = new GridExpViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof UserContactGridExpViewBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof UserContactGridExpViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            gridexpbar: this.$refs.gridexpbar,
            keyPSDEField: 'usercontact',
            majorPSDEField: 'listname',
            isLoadDefault: true,
        });
    }

    /**
     * gridexpbar 部件 selectionchange 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof UserContactGridExpViewBase
     */
    public gridexpbar_selectionchange($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('gridexpbar', 'selectionchange', $event);
    }

    /**
     * gridexpbar 部件 activated 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof UserContactGridExpViewBase
     */
    public gridexpbar_activated($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('gridexpbar', 'activated', $event);
    }

    /**
     * gridexpbar 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof UserContactGridExpViewBase
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
     * @memberof UserContactGridExpView
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
     * @memberof UserContactGridExpView
     */
    public opendata(args: any[],fullargs?:any[],params?: any, $event?: any, xData?: any) {
    this.$Notice.warning({ title: '错误', desc: '未指定关系视图' });
    }



    /**
     * 视图唯一标识
     *
     * @type {string}
     * @memberof UserContactGridExpView
     */
    public viewUID: string = 'zentao-user-contact-grid-exp-view';

    /**
     * 导航数据部件选中数据
     *
     * @type {*}
     * @memberof UserContactGridExpView
     */
    public selectionData: any[] = [];

    /**
     * 获取数据
     *
     * @type {string}
     * @memberof UserContactGridExpView
     */
    public getDatas() {
        return this.selectionData;
    }


}