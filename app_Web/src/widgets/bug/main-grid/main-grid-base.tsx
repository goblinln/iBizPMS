import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, GridControllerBase } from '@/studio-core';
import BugService from '@/service/bug/bug-service';
import MainService from './main-grid-service';
import BugUIService from '@/uiservice/bug/bug-ui-service';
import { FormItemModel } from '@/model/form-detail';


/**
 * grid部件基类
 *
 * @export
 * @class GridControllerBase
 * @extends {MainGridBase}
 */
export class MainGridBase extends GridControllerBase {

    /**
     * 建构部件服务对象
     *
     * @type {MainService}
     * @memberof MainGridBase
     */
    public service: MainService = new MainService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {BugService}
     * @memberof MainGridBase
     */
    public appEntityService: BugService = new BugService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof MainGridBase
     */
    protected appDeName: string = 'bug';

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_ua4634db_click(params: any = {}, tag?: any, $event?: any) {
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:BugUIService  = new BugUIService();
        curUIService.Bug_ConfirmBug(datas,contextJO, paramJO,  $event, xData,this,"Bug");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u0efb050_click(params: any = {}, tag?: any, $event?: any) {
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:BugUIService  = new BugUIService();
        curUIService.Bug_ResolveBug(datas,contextJO, paramJO,  $event, xData,this,"Bug");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_uc6d84c5_click(params: any = {}, tag?: any, $event?: any) {
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:BugUIService  = new BugUIService();
        curUIService.Bug_CloseBug(datas,contextJO, paramJO,  $event, xData,this,"Bug");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u953838c_click(params: any = {}, tag?: any, $event?: any) {
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:BugUIService  = new BugUIService();
        curUIService.Bug_MainEdit(datas,contextJO, paramJO,  $event, xData,this,"Bug");
    }

    /**
     * 本地缓存标识
     *
     * @protected
     * @type {string}
     * @memberof GridControllerBase
     */
    protected localStorageTag: string = 'zt_bug_main_grid';

    /**
     * 排序方向
     *
     * @type {string}
     * @memberof MainGridBase
     */
    public minorSortDir: string = '';

    /**
     * 所有列成员
     *
     * @type {any[]}
     * @memberof MainGridBase
     */
    public allColumns: any[] = [
        {
            name: 'id',
            label: 'ID',
            langtag: 'entities.bug.main_grid.columns.id',
            show: true,
            util: 'PX'
        },
        {
            name: 'pri',
            label: 'P',
            langtag: 'entities.bug.main_grid.columns.pri',
            show: true,
            util: 'PX'
        },
        {
            name: 'confirmed',
            label: '确认',
            langtag: 'entities.bug.main_grid.columns.confirmed',
            show: true,
            util: 'PX'
        },
        {
            name: 'title',
            label: 'Bug标题',
            langtag: 'entities.bug.main_grid.columns.title',
            show: true,
            util: 'STAR'
        },
        {
            name: 'status',
            label: 'Bug状态',
            langtag: 'entities.bug.main_grid.columns.status',
            show: true,
            util: 'PX'
        },
        {
            name: 'openedby',
            label: '由谁创建',
            langtag: 'entities.bug.main_grid.columns.openedby',
            show: true,
            util: 'PX'
        },
        {
            name: 'openeddate',
            label: '创建日期',
            langtag: 'entities.bug.main_grid.columns.openeddate',
            show: true,
            util: 'PX'
        },
        {
            name: 'assignedto',
            label: '指派给',
            langtag: 'entities.bug.main_grid.columns.assignedto',
            show: true,
            util: 'PX'
        },
        {
            name: 'resolution',
            label: '方案',
            langtag: 'entities.bug.main_grid.columns.resolution',
            show: true,
            util: 'PX'
        },
        {
            name: 'uagridcolumn1',
            label: '操作',
            langtag: 'entities.bug.main_grid.columns.uagridcolumn1',
            show: true,
            util: 'PX'
        },
    ]


    /**
     * 界面行为
     *
     * @param {*} row
     * @param {*} tag
     * @param {*} $event
     * @memberof MainGridBase
     */
	public uiAction(row: any, tag: any, $event: any) {
        $event.stopPropagation();
        if(Object.is('ConfirmBug', tag)) {
            this.grid_uagridcolumn1_ua4634db_click(row, tag, $event);
        }
        if(Object.is('ResolveBug', tag)) {
            this.grid_uagridcolumn1_u0efb050_click(row, tag, $event);
        }
        if(Object.is('CloseBug', tag)) {
            this.grid_uagridcolumn1_uc6d84c5_click(row, tag, $event);
        }
        if(Object.is('MainEdit', tag)) {
            this.grid_uagridcolumn1_u953838c_click(row, tag, $event);
        }
    }
}