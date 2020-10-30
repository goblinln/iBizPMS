import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { UIActionTool,Util,ViewTool } from '@/utils';
import { Watch, GridControlBase } from '@/studio-core';
import BugService from '@/service/bug/bug-service';
import Main_PlanSubService from './main-plan-sub-grid-service';
import BugUIService from '@/uiservice/bug/bug-ui-service';
import { FormItemModel } from '@/model/form-detail';


/**
 * grid部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {Main_PlanSubGridBase}
 */
export class Main_PlanSubGridBase extends GridControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof Main_PlanSubGridBase
     */
    protected controlType: string = 'GRID';

    /**
     * 建构部件服务对象
     *
     * @type {Main_PlanSubService}
     * @memberof Main_PlanSubGridBase
     */
    public service: Main_PlanSubService = new Main_PlanSubService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {BugService}
     * @memberof Main_PlanSubGridBase
     */
    public appEntityService: BugService = new BugService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof Main_PlanSubGridBase
     */
    protected appDeName: string = 'bug';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof Main_PlanSubGridBase
     */
    protected appDeLogicName: string = 'Bug';

    /**
     * 界面UI服务对象
     *
     * @type {BugUIService}
     * @memberof Main_PlanSubBase
     */  
    public appUIService:BugUIService = new BugUIService(this.$store);

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_uda12208_click(params: any = {}, tag?: any, $event?: any) {
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
        curUIService.Bug_UnlinkBug(datas,contextJO, paramJO,  $event, xData,this,"Bug");
    }


    /**
     * 界面行为模型
     *
     * @type {*}
     * @memberof Main_PlanSubBase
     */  
    public ActionModel: any = {
        UnlinkBug: { name: 'UnlinkBug',disabled: false, visabled: true,noprivdisplaymode:2,dataaccaction: 'SRFUR__BUG_UNLINK_BUT', actiontarget: 'SINGLEKEY'}
    };

    /**
     * 主信息表格列
     *
     * @type {string}
     * @memberof Main_PlanSubBase
     */  
    public majorInfoColName:string = "title";

    /**
     * 列主键属性名称
     *
     * @type {string}
     * @memberof Main_PlanSubGridBase
     */
    public columnKeyName: string = "id";

    /**
     * 本地缓存标识
     *
     * @protected
     * @type {string}
     * @memberof Main_PlanSubBase
     */
    protected localStorageTag: string = 'zt_bug_main_plansub_grid';

    /**
     * 排序方向
     *
     * @type {string}
     * @memberof Main_PlanSubGridBase
     */
    public minorSortDir: string = 'DESC';

    /**
     * 排序字段
     *
     * @type {string}
     * @memberof Main_PlanSubGridBase
     */
    public minorSortPSDEF: string = 'id';

    /**
     * 所有列成员
     *
     * @type {any[]}
     * @memberof Main_PlanSubGridBase
     */
    public allColumns: any[] = [
        {
            name: 'id',
            label: 'ID',
            langtag: 'entities.bug.main_plansub_grid.columns.id',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'pri',
            label: 'P',
            langtag: 'entities.bug.main_plansub_grid.columns.pri',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'title',
            label: 'Bug标题',
            langtag: 'entities.bug.main_plansub_grid.columns.title',
            show: true,
            unit: 'STAR',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'status',
            label: 'Bug状态',
            langtag: 'entities.bug.main_plansub_grid.columns.status',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'openedby',
            label: '由谁创建',
            langtag: 'entities.bug.main_plansub_grid.columns.openedby',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'openeddate',
            label: '创建日期',
            langtag: 'entities.bug.main_plansub_grid.columns.openeddate',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'assignedto',
            label: '指派给',
            langtag: 'entities.bug.main_plansub_grid.columns.assignedto',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'uagridcolumn1',
            label: '操作',
            langtag: 'entities.bug.main_plansub_grid.columns.uagridcolumn1',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'isfavorites',
            label: '是否收藏',
            langtag: 'entities.bug.main_plansub_grid.columns.isfavorites',
            show: false,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
    ]

    /**
     * 获取表格行模型
     *
     * @type {*}
     * @memberof Main_PlanSubGridBase
     */
    public getGridRowModel(){
        return {
          srfkey: new FormItemModel(),
        }
    }

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof Main_PlanSubGridBase
     */
    public rules: any = {
        srfkey: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: 'Bug编号 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: 'Bug编号 值不能为空', trigger: 'blur' },
        ],
    }

    /**
     * 获取对应列class
     *
     * @type {*}
     * @memberof Main_PlanSubBase
     */
    public hasRowEdit: any = {
        'id':false,
        'pri':false,
        'title':false,
        'status':false,
        'openedby':false,
        'openeddate':false,
        'assignedto':false,
        'uagridcolumn1':false,
        'isfavorites':false,
    };

    /**
     * 获取对应列class
     *
     * @param {*} $args row 行数据，column 列数据，rowIndex 行索引，列索引
     * @returns {void}
     * @memberof Main_PlanSubBase
     */
    public getCellClassName(args: {row: any, column: any, rowIndex: number, columnIndex: number}): any {
        return ( this.hasRowEdit[args.column.property] && this.actualIsOpenEdit ) ? "edit-cell" : "info-cell";
    }


    /**
     * 是否为实体导出对象
     *
     * @protected
     * @type {boolean}
     * @memberof Main_PlanSubGridBase
     */
    protected isDeExport: boolean = true;

    /**
     * 所有导出列成员
     *
     * @type {any[]}
     * @memberof Main_PlanSubGridBase
     */
    public allExportColumns: any[] = [
        {
            name: 'id',
            label: 'ID',
            langtag: 'entities.bug.main_plansub_grid.exportColumns.id',
            show: true,
        },
        {
            name: 'pri',
            label: 'P',
            langtag: 'entities.bug.main_plansub_grid.exportColumns.pri',
            show: true,
        },
        {
            name: 'confirmed',
            label: '确认',
            langtag: 'entities.bug.main_plansub_grid.exportColumns.confirmed',
            show: true,
        },
        {
            name: 'title',
            label: 'Bug标题',
            langtag: 'entities.bug.main_plansub_grid.exportColumns.title',
            show: true,
        },
        {
            name: 'status',
            label: 'Bug状态',
            langtag: 'entities.bug.main_plansub_grid.exportColumns.status',
            show: true,
        },
        {
            name: 'openedby',
            label: '由谁创建',
            langtag: 'entities.bug.main_plansub_grid.exportColumns.openedby',
            show: true,
        },
        {
            name: 'openeddate',
            label: '创建日期',
            langtag: 'entities.bug.main_plansub_grid.exportColumns.openeddate',
            show: true,
        },
        {
            name: 'assignedto',
            label: '指派给',
            langtag: 'entities.bug.main_plansub_grid.exportColumns.assignedto',
            show: true,
        },
        {
            name: 'resolution',
            label: '方案',
            langtag: 'entities.bug.main_plansub_grid.exportColumns.resolution',
            show: true,
        },
        {
            name: 'lastediteddate',
            label: '修改日期',
            langtag: 'entities.bug.main_plansub_grid.exportColumns.lastediteddate',
            show: true,
        },
        {
            name: 'activateddate',
            label: '激活日期',
            langtag: 'entities.bug.main_plansub_grid.exportColumns.activateddate',
            show: true,
        },
        {
            name: 'isfavorites',
            label: '是否收藏',
            langtag: 'entities.bug.main_plansub_grid.exportColumns.isfavorites',
            show: true,
        },
        {
            name: 'product',
            label: '所属产品',
            langtag: 'entities.bug.main_plansub_grid.exportColumns.product',
            show: true,
        },
        {
            name: 'projectname',
            label: '项目',
            langtag: 'entities.bug.main_plansub_grid.exportColumns.projectname',
            show: true,
        },
    ]

    /**
     * 导出数据格式化
     *
     * @param {*} filterVal
     * @param {*} jsonData
     * @param {any[]} [codelistColumns=[]]
     * @returns {Promise<any>}
     * @memberof Main_PlanSubGridBase
     */
    public async formatExcelData(filterVal: any, jsonData: any, codelistColumns?: any[]): Promise<any> {
        return super.formatExcelData(filterVal, jsonData, [
            {
                name: 'pri',
                srfkey: 'Bug__pri',
                codelistType : 'STATIC',
                renderMode: 'other',
                textSeparator: '、',
                valueSeparator: ',',
            },
            {
                name: 'confirmed',
                srfkey: 'YesNo2',
                codelistType : 'STATIC',
                renderMode: 'other',
                textSeparator: '、',
                valueSeparator: ',',
            },
            {
                name: 'status',
                srfkey: 'Bug__status',
                codelistType : 'STATIC',
                renderMode: 'other',
                textSeparator: '、',
                valueSeparator: ',',
            },
            {
                name: 'openedby',
                srfkey: 'UserRealName',
                codelistType : 'DYNAMIC',
                textSeparator: ',',
                renderMode: 'string',
                valueSeparator: ",",
            },
            {
                name: 'assignedto',
                srfkey: 'BugUserRealName',
                codelistType : 'DYNAMIC',
                textSeparator: ',',
                renderMode: 'string',
                valueSeparator: ",",
            },
            {
                name: 'resolution',
                srfkey: 'Bug__resolution',
                codelistType : 'STATIC',
                renderMode: 'other',
                textSeparator: '、',
                valueSeparator: ',',
            },
        ]);
    }


    /**
     * 界面行为
     *
     * @param {*} row
     * @param {*} tag
     * @param {*} $event
     * @memberof Main_PlanSubGridBase
     */
	public uiAction(row: any, tag: any, $event: any): void {
        $event.stopPropagation();
        if(Object.is('UnlinkBug', tag)) {
            this.grid_uagridcolumn1_uda12208_click(row, tag, $event);
        }
    }

    /**
     * 更新默认值
     * @param {*}  row 行数据
     * @memberof Main_PlanSubBase
     */
    public updateDefault(row: any){                    
    }

    /**
     * 计算数据对象类型的默认值
     * @param {string}  action 行为
     * @param {string}  param 默认值参数
     * @param {*}  data 当前行数据
     * @memberof Main_PlanSubBase
     */
    public computeDefaultValueWithParam(action:string,param:string,data:any){
        if(Object.is(action,"UPDATE")){
            const nativeData:any = this.service.getCopynativeData();
            if(nativeData && (nativeData instanceof Array) && nativeData.length >0){
                let targetData:any = nativeData.find((item:any) =>{
                    return item.id === data.srfkey;
                })
                if(targetData){
                    return targetData[param]?targetData[param]:null;
                }else{
                    return null;
                }
            }else{
                return null;
            }
        }else{
           return this.service.getRemoteCopyData()[param]?this.service.getRemoteCopyData()[param]:null;
        }
    }


}