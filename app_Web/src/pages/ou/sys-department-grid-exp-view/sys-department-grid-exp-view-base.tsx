import { Subject } from 'rxjs';
import { UIActionTool, ViewTool, Util } from '@/utils';
import { GridExpViewBase } from '@/studio-core';
import SysDepartmentService from '@/service/sys-department/sys-department-service';
import SysDepartmentAuthService from '@/authservice/sys-department/sys-department-auth-service';
import GridExpViewEngine from '@engine/view/grid-exp-view-engine';
import SysDepartmentUIService from '@/uiservice/sys-department/sys-department-ui-service';

/**
 * 部门表格导航视图视图基类
 *
 * @export
 * @class SysDepartmentGridExpViewBase
 * @extends {GridExpViewBase}
 */
export class SysDepartmentGridExpViewBase extends GridExpViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof SysDepartmentGridExpViewBase
     */
    protected appDeName: string = 'sysdepartment';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof SysDepartmentGridExpViewBase
     */
    protected appDeKey: string = 'deptid';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof SysDepartmentGridExpViewBase
     */
    protected appDeMajor: string = 'deptname';

    /**
     * 实体服务对象
     *
     * @type {SysDepartmentService}
     * @memberof SysDepartmentGridExpViewBase
     */
    protected appEntityService: SysDepartmentService = new SysDepartmentService;

    /**
     * 实体权限服务对象
     *
     * @type SysDepartmentUIService
     * @memberof SysDepartmentGridExpViewBase
     */
    public appUIService: SysDepartmentUIService = new SysDepartmentUIService(this.$store);

    /**
     * 是否显示信息栏
     *
     * @memberof SysDepartmentGridExpViewBase
     */
    isShowDataInfoBar: boolean = true;

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof SysDepartmentGridExpViewBase
     */
    protected model: any = {
        srfCaption: 'entities.sysdepartment.views.gridexpview.caption',
        srfTitle: 'entities.sysdepartment.views.gridexpview.title',
        srfSubTitle: 'entities.sysdepartment.views.gridexpview.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof SysDepartmentGridExpViewBase
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
     * @memberof SysDepartmentGridExpViewBase
     */
	protected viewtag: string = '92ea2f270b6525a85636e8631640718d';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof SysDepartmentGridExpViewBase
     */ 
    protected viewName: string = 'SysDepartmentGridExpView';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof SysDepartmentGridExpViewBase
     */
    public engine: GridExpViewEngine = new GridExpViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof SysDepartmentGridExpViewBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof SysDepartmentGridExpViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            gridexpbar: this.$refs.gridexpbar,
            keyPSDEField: 'sysdepartment',
            majorPSDEField: 'deptname',
            isLoadDefault: true,
        });
    }

    /**
     * gridexpbar 部件 selectionchange 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysDepartmentGridExpViewBase
     */
    public gridexpbar_selectionchange($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('gridexpbar', 'selectionchange', $event);
    }

    /**
     * gridexpbar 部件 activated 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysDepartmentGridExpViewBase
     */
    public gridexpbar_activated($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('gridexpbar', 'activated', $event);
    }

    /**
     * gridexpbar 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof SysDepartmentGridExpViewBase
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
     * @memberof SysDepartmentGridExpView
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
     * @memberof SysDepartmentGridExpView
     */
    public opendata(args: any[],fullargs?:any[],params?: any, $event?: any, xData?: any) {
    this.$Notice.warning({ title: '错误', desc: '未指定关系视图' });
    }



    /**
     * 视图唯一标识
     *
     * @type {string}
     * @memberof SysDepartmentGridExpView
     */
    public viewUID: string = 'ou-sys-department-grid-exp-view';

    /**
     * 导航数据部件选中数据
     *
     * @type {*}
     * @memberof SysDepartmentGridExpView
     */
    public selectionData: any[] = [];

    /**
     * 获取数据
     *
     * @type {string}
     * @memberof SysDepartmentGridExpView
     */
    public getDatas() {
        return this.selectionData;
    }


}