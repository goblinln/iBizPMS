import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { UIActionTool,Util,ViewTool } from '@/utils';
import { Watch, GridControlBase } from '@/studio-core';
import TaskService from '@/service/task/task-service';
import Main2MyService from './main2-my-grid-service';
import TaskUIService from '@/uiservice/task/task-ui-service';
import { FormItemModel } from '@/model/form-detail';


/**
 * grid部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {Main2MyGridBase}
 */
export class Main2MyGridBase extends GridControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof Main2MyGridBase
     */
    protected controlType: string = 'GRID';

    /**
     * 建构部件服务对象
     *
     * @type {Main2MyService}
     * @memberof Main2MyGridBase
     */
    public service: Main2MyService = new Main2MyService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {TaskService}
     * @memberof Main2MyGridBase
     */
    public appEntityService: TaskService = new TaskService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof Main2MyGridBase
     */
    protected appDeName: string = 'task';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof Main2MyGridBase
     */
    protected appDeLogicName: string = '任务';

    /**
     * 界面UI服务对象
     *
     * @type {TaskUIService}
     * @memberof Main2MyBase
     */  
    public appUIService:TaskUIService = new TaskUIService(this.$store);


    /**
     * 界面行为模型
     *
     * @type {*}
     * @memberof Main2MyBase
     */  
    public ActionModel: any = {
    };

    /**
     * 主信息表格列
     *
     * @type {string}
     * @memberof Main2MyBase
     */  
    public majorInfoColName:string = "name";

    /**
     * 列主键属性名称
     *
     * @type {string}
     * @memberof Main2MyGridBase
     */
    public columnKeyName: string = "id";

    /**
     * 本地缓存标识
     *
     * @protected
     * @type {string}
     * @memberof Main2MyBase
     */
    protected localStorageTag: string = 'zt_task_main2my_grid';

    /**
     * 是否支持分页
     *
     * @type {boolean}
     * @memberof Main2MyGridBase
     */
    public isEnablePagingBar: boolean = false;

    /**
     * 排序方向
     *
     * @type {string}
     * @memberof Main2MyGridBase
     */
    public minorSortDir: string = 'DESC';

    /**
     * 排序字段
     *
     * @type {string}
     * @memberof Main2MyGridBase
     */
    public minorSortPSDEF: string = 'id';

    /**
     * 所有列成员
     *
     * @type {any[]}
     * @memberof Main2MyGridBase
     */
    public allColumns: any[] = [
        {
            name: 'id',
            label: '编号',
            langtag: 'entities.task.main2my_grid.columns.id',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'pri',
            label: 'P',
            langtag: 'entities.task.main2my_grid.columns.pri',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'name',
            label: '任务名称',
            langtag: 'entities.task.main2my_grid.columns.name',
            show: true,
            unit: 'STAR',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'deadline',
            label: '截止日期',
            langtag: 'entities.task.main2my_grid.columns.deadline',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
    ]

    /**
     * 获取表格行模型
     *
     * @type {*}
     * @memberof Main2MyGridBase
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
     * @memberof Main2MyGridBase
     */
    public rules: any = {
        srfkey: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '编号 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '编号 值不能为空', trigger: 'blur' },
        ],
    }

    /**
     * 获取对应列class
     *
     * @type {*}
     * @memberof Main2MyBase
     */
    public hasRowEdit: any = {
        'id':false,
        'pri':false,
        'name':false,
        'deadline':false,
    };

    /**
     * 获取对应列class
     *
     * @param {*} $args row 行数据，column 列数据，rowIndex 行索引，列索引
     * @returns {void}
     * @memberof Main2MyBase
     */
    public getCellClassName(args: {row: any, column: any, rowIndex: number, columnIndex: number}): any {
        return ( this.hasRowEdit[args.column.property] && this.actualIsOpenEdit ) ? "edit-cell" : "info-cell";
    }


    /**
     * 是否为实体导出对象
     *
     * @protected
     * @type {boolean}
     * @memberof Main2MyGridBase
     */
    protected isDeExport: boolean = true;

    /**
     * 所有导出列成员
     *
     * @type {any[]}
     * @memberof Main2MyGridBase
     */
    public allExportColumns: any[] = [
        {
            name: 'id',
            label: 'ID',
            langtag: 'entities.task.main2my_grid.exportColumns.id',
            show: true,
        },
        {
            name: 'pri',
            label: 'P',
            langtag: 'entities.task.main2my_grid.exportColumns.pri',
            show: true,
        },
        {
            name: 'name',
            label: '任务名称',
            langtag: 'entities.task.main2my_grid.exportColumns.name',
            show: true,
        },
        {
            name: 'status',
            label: '任务状态',
            langtag: 'entities.task.main2my_grid.exportColumns.status',
            show: true,
        },
        {
            name: 'status1',
            label: '任务状态',
            langtag: 'entities.task.main2my_grid.exportColumns.status1',
            show: true,
        },
        {
            name: 'assignedto',
            label: '指派给',
            langtag: 'entities.task.main2my_grid.exportColumns.assignedto',
            show: true,
        },
        {
            name: 'finishedby',
            label: '完成者',
            langtag: 'entities.task.main2my_grid.exportColumns.finishedby',
            show: true,
        },
        {
            name: 'estimate',
            label: '预计',
            langtag: 'entities.task.main2my_grid.exportColumns.estimate',
            show: true,
        },
        {
            name: 'consumed',
            label: '消耗',
            langtag: 'entities.task.main2my_grid.exportColumns.consumed',
            show: true,
        },
        {
            name: 'left',
            label: '剩余',
            langtag: 'entities.task.main2my_grid.exportColumns.left',
            show: true,
        },
        {
            name: 'deadline',
            label: '截止日期',
            langtag: 'entities.task.main2my_grid.exportColumns.deadline',
            show: true,
        },
        {
            name: 'isfavorites',
            label: '是否收藏',
            langtag: 'entities.task.main2my_grid.exportColumns.isfavorites',
            show: true,
        },
        {
            name: 'tasktype',
            label: '任务类型',
            langtag: 'entities.task.main2my_grid.exportColumns.tasktype',
            show: true,
        },
        {
            name: 'product',
            label: '产品',
            langtag: 'entities.task.main2my_grid.exportColumns.product',
            show: true,
        },
        {
            name: 'projectname',
            label: '所属项目',
            langtag: 'entities.task.main2my_grid.exportColumns.projectname',
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
     * @memberof Main2MyGridBase
     */
    public async formatExcelData(filterVal: any, jsonData: any, codelistColumns?: any[]): Promise<any> {
        return super.formatExcelData(filterVal, jsonData, [
            {
                name: 'pri',
                srfkey: 'Task__pri',
                codelistType : 'STATIC',
                renderMode: 'other',
                textSeparator: '、',
                valueSeparator: ',',
            },
            {
                name: 'status',
                srfkey: 'Task__status',
                codelistType : 'STATIC',
                renderMode: 'other',
                textSeparator: '、',
                valueSeparator: ',',
            },
            {
                name: 'status1',
                srfkey: 'TaskStatusCK',
                codelistType : 'STATIC',
                renderMode: 'other',
                textSeparator: '、',
                valueSeparator: ',',
            },
            {
                name: 'assignedto',
                srfkey: 'UserRealName',
                codelistType : 'DYNAMIC',
                textSeparator: ',',
                renderMode: 'string',
                valueSeparator: ",",
            },
            {
                name: 'finishedby',
                srfkey: 'UserRealName',
                codelistType : 'DYNAMIC',
                textSeparator: ',',
                renderMode: 'string',
                valueSeparator: ",",
            },
        ]);
    }


    /**
     * 更新默认值
     * @param {*}  row 行数据
     * @memberof Main2MyBase
     */
    public updateDefault(row: any){                    
    }

    /**
     * 计算数据对象类型的默认值
     * @param {string}  action 行为
     * @param {string}  param 默认值参数
     * @param {*}  data 当前行数据
     * @memberof Main2MyBase
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