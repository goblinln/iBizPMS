import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { UIActionTool,Util,ViewTool } from '@/utils';
import { Watch, GridControlBase } from '@/studio-core';
import TaskService from '@/service/task/task-service';
import StoryRelatedService from './story-related-grid-service';
import TaskUIService from '@/uiservice/task/task-ui-service';
import { FormItemModel } from '@/model/form-detail';


/**
 * grid部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {StoryRelatedGridBase}
 */
export class StoryRelatedGridBase extends GridControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof StoryRelatedGridBase
     */
    protected controlType: string = 'GRID';

    /**
     * 建构部件服务对象
     *
     * @type {StoryRelatedService}
     * @memberof StoryRelatedGridBase
     */
    public service: StoryRelatedService = new StoryRelatedService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {TaskService}
     * @memberof StoryRelatedGridBase
     */
    public appEntityService: TaskService = new TaskService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof StoryRelatedGridBase
     */
    protected appDeName: string = 'task';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof StoryRelatedGridBase
     */
    protected appDeLogicName: string = '任务';

    /**
     * 界面UI服务对象
     *
     * @type {TaskUIService}
     * @memberof StoryRelatedBase
     */  
    public appUIService:TaskUIService = new TaskUIService(this.$store);


    /**
     * 界面行为模型
     *
     * @type {*}
     * @memberof StoryRelatedBase
     */  
    public ActionModel: any = {
    };

    /**
     * 主信息表格列
     *
     * @type {string}
     * @memberof StoryRelatedBase
     */  
    public majorInfoColName:string = "name";

    /**
     * 列主键属性名称
     *
     * @type {string}
     * @memberof StoryRelatedGridBase
     */
    public columnKeyName: string = id;

    /**
     * 本地缓存标识
     *
     * @protected
     * @type {string}
     * @memberof StoryRelatedBase
     */
    protected localStorageTag: string = 'zt_task_storyrelated_grid';

    /**
     * 是否支持分页
     *
     * @type {boolean}
     * @memberof StoryRelatedGridBase
     */
    public isEnablePagingBar: boolean = false;

    /**
     * 排序方向
     *
     * @type {string}
     * @memberof StoryRelatedGridBase
     */
    public minorSortDir: string = 'DESC';

    /**
     * 排序字段
     *
     * @type {string}
     * @memberof StoryRelatedGridBase
     */
    public minorSortPSDEF: string = 'id';

    /**
     * 分页条数
     *
     * @type {number}
     * @memberof StoryRelatedGridBase
     */
    public limit: number = 500;

    /**
     * 是否显示标题
     *
     * @type {boolean}
     * @memberof StoryRelatedGridBase
     */
    public isHideHeader: boolean = true;

    /**
     * 所有列成员
     *
     * @type {any[]}
     * @memberof StoryRelatedGridBase
     */
    public allColumns: any[] = [
        {
            name: 'id',
            label: '编号',
            langtag: 'entities.task.storyrelated_grid.columns.id',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'name',
            label: '任务名称',
            langtag: 'entities.task.storyrelated_grid.columns.name',
            show: true,
            unit: 'STAR',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'projectname',
            label: '所属项目',
            langtag: 'entities.task.storyrelated_grid.columns.projectname',
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
     * @memberof StoryRelatedGridBase
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
     * @memberof StoryRelatedGridBase
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
     * @memberof StoryRelatedBase
     */
    public hasRowEdit: any = {
        'id':false,
        'name':false,
        'projectname':false,
    };

    /**
     * 获取对应列class
     *
     * @param {*} $args row 行数据，column 列数据，rowIndex 行索引，列索引
     * @returns {void}
     * @memberof StoryRelatedBase
     */
    public getCellClassName(args: {row: any, column: any, rowIndex: number, columnIndex: number}): any {
        return ( this.hasRowEdit[args.column.property] && this.actualIsOpenEdit ) ? "edit-cell" : "info-cell";
    }


    /**
     * 导出数据格式化
     *
     * @param {*} filterVal
     * @param {*} jsonData
     * @param {any[]} [codelistColumns=[]]
     * @returns {Promise<any>}
     * @memberof StoryRelatedGridBase
     */
    public async formatExcelData(filterVal: any, jsonData: any, codelistColumns?: any[]): Promise<any> {
        return super.formatExcelData(filterVal, jsonData, [
        ]);
    }


    /**
     * 更新默认值
     * @param {*}  row 行数据
     * @memberof StoryRelatedBase
     */
    public updateDefault(row: any){                    
    }

    /**
     * 计算数据对象类型的默认值
     * @param {string}  action 行为
     * @param {string}  param 默认值参数
     * @param {*}  data 当前行数据
     * @memberof StoryRelatedBase
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