import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { UIActionTool,Util,ViewTool } from '@/utils';
import { Watch, GridControlBase } from '@/studio-core';
import IBZCaseStepService from '@/service/ibzcase-step/ibzcase-step-service';
import Main_RowEdit_CarryOutService from './main-row-edit-carry-out-grid-service';
import IBZCaseStepUIService from '@/uiservice/ibzcase-step/ibzcase-step-ui-service';
import { FormItemModel } from '@/model/form-detail';


/**
 * grid部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {Main_RowEdit_CarryOutGridBase}
 */
export class Main_RowEdit_CarryOutGridBase extends GridControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof Main_RowEdit_CarryOutGridBase
     */
    protected controlType: string = 'GRID';

    /**
     * 建构部件服务对象
     *
     * @type {Main_RowEdit_CarryOutService}
     * @memberof Main_RowEdit_CarryOutGridBase
     */
    public service: Main_RowEdit_CarryOutService = new Main_RowEdit_CarryOutService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {IBZCaseStepService}
     * @memberof Main_RowEdit_CarryOutGridBase
     */
    public appEntityService: IBZCaseStepService = new IBZCaseStepService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof Main_RowEdit_CarryOutGridBase
     */
    protected appDeName: string = 'ibzcasestep';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof Main_RowEdit_CarryOutGridBase
     */
    protected appDeLogicName: string = '用例步骤';

    /**
     * 界面UI服务对象
     *
     * @type {IBZCaseStepUIService}
     * @memberof Main_RowEdit_CarryOutBase
     */  
    public appUIService:IBZCaseStepUIService = new IBZCaseStepUIService(this.$store);


    /**
     * 界面行为模型
     *
     * @type {*}
     * @memberof Main_RowEdit_CarryOutBase
     */  
    public ActionModel: any = {
    };

    /**
     * 主信息表格列
     *
     * @type {string}
     * @memberof Main_RowEdit_CarryOutBase
     */  
    public majorInfoColName:string = "expect";

    /**
     * 本地缓存标识
     *
     * @protected
     * @type {string}
     * @memberof Main_RowEdit_CarryOutBase
     */
    protected localStorageTag: string = 'ibz_casestep_main_rowedit_carryout_grid';

    /**
     * 是否支持分页
     *
     * @type {boolean}
     * @memberof Main_RowEdit_CarryOutGridBase
     */
    public isEnablePagingBar: boolean = false;

    /**
     * 排序方向
     *
     * @type {string}
     * @memberof Main_RowEdit_CarryOutGridBase
     */
    public minorSortDir: string = 'DESC';

    /**
     * 排序字段
     *
     * @type {string}
     * @memberof Main_RowEdit_CarryOutGridBase
     */
    public minorSortPSDEF: string = 'id';

    /**
     * 分页条数
     *
     * @type {number}
     * @memberof Main_RowEdit_CarryOutGridBase
     */
    public limit: number = 500;

    /**
     * 所有列成员
     *
     * @type {any[]}
     * @memberof Main_RowEdit_CarryOutGridBase
     */
    public allColumns: any[] = [
        {
            name: 'desc',
            label: '步骤',
            langtag: 'entities.ibzcasestep.main_rowedit_carryout_grid.columns.desc',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'expect',
            label: '预期',
            langtag: 'entities.ibzcasestep.main_rowedit_carryout_grid.columns.expect',
            show: true,
            unit: 'STAR',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'steps',
            label: '测试结果',
            langtag: 'entities.ibzcasestep.main_rowedit_carryout_grid.columns.steps',
            show: true,
            unit: 'PX',
            isEnableRowEdit: true,
            enableCond: 3 ,
        },
        {
            name: 'reals',
            label: '实际情况',
            langtag: 'entities.ibzcasestep.main_rowedit_carryout_grid.columns.reals',
            show: true,
            unit: 'PX',
            isEnableRowEdit: true,
            enableCond: 3 ,
        },
    ]

    /**
     * 获取表格行模型
     *
     * @type {*}
     * @memberof Main_RowEdit_CarryOutGridBase
     */
    public getGridRowModel(){
        return {
          id: new FormItemModel(),
          files: new FormItemModel(),
          case: new FormItemModel(),
          steps: new FormItemModel(),
          type: new FormItemModel(),
          srfkey: new FormItemModel(),
          reals: new FormItemModel(),
          version: new FormItemModel(),
        }
    }

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof Main_RowEdit_CarryOutGridBase
     */
    public rules: any = {
        id: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '编号 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '编号 值不能为空', trigger: 'blur' },
        ],
        files: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '附件 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '附件 值不能为空', trigger: 'blur' },
        ],
        case: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '用例 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '用例 值不能为空', trigger: 'blur' },
        ],
        steps: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '测试结果 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '测试结果 值不能为空', trigger: 'blur' },
        ],
        type: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '类型 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '类型 值不能为空', trigger: 'blur' },
        ],
        srfkey: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '编号 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '编号 值不能为空', trigger: 'blur' },
        ],
        reals: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '实际情况 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '实际情况 值不能为空', trigger: 'blur' },
        ],
        version: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '用例版本 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '用例版本 值不能为空', trigger: 'blur' },
        ],
    }

    /**
     * 获取对应列class
     *
     * @type {*}
     * @memberof Main_RowEdit_CarryOutBase
     */
    public hasRowEdit: any = {
        'desc':false,
        'expect':false,
        'steps':true,
        'reals':true,
    };

    /**
     * 获取对应列class
     *
     * @param {*} $args row 行数据，column 列数据，rowIndex 行索引，列索引
     * @returns {void}
     * @memberof Main_RowEdit_CarryOutBase
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
     * @memberof Main_RowEdit_CarryOutGridBase
     */
    public async formatExcelData(filterVal: any, jsonData: any, codelistColumns?: any[]): Promise<any> {
        return super.formatExcelData(filterVal, jsonData, [
            {
                name: 'steps',
                srfkey: 'Testresult__result',
                codelistType : 'STATIC',
                renderMode: 'other',
                textSeparator: '、',
                valueSeparator: ',',
            },
        ]);
    }


    /**
     * 更新默认值
     * @param {*}  row 行数据
     * @memberof Main_RowEdit_CarryOutBase
     */
    public updateDefault(row: any){                    
    }

    /**
     * 计算数据对象类型的默认值
     * @param {string}  action 行为
     * @param {string}  param 默认值参数
     * @param {*}  data 当前行数据
     * @memberof Main_RowEdit_CarryOutBase
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