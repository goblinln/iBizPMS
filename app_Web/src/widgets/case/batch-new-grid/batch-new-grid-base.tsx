import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { UIActionTool,Util,ViewTool } from '@/utils';
import { Watch, GridControlBase } from '@/studio-core';
import CaseService from '@/service/case/case-service';
import BatchNewService from './batch-new-grid-service';
import CaseUIService from '@/uiservice/case/case-ui-service';
import { FormItemModel } from '@/model/form-detail';


/**
 * grid部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {BatchNewGridBase}
 */
export class BatchNewGridBase extends GridControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof BatchNewGridBase
     */
    protected controlType: string = 'GRID';

    /**
     * 建构部件服务对象
     *
     * @type {BatchNewService}
     * @memberof BatchNewGridBase
     */
    public service: BatchNewService = new BatchNewService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {CaseService}
     * @memberof BatchNewGridBase
     */
    public appEntityService: CaseService = new CaseService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof BatchNewGridBase
     */
    protected appDeName: string = 'case';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof BatchNewGridBase
     */
    protected appDeLogicName: string = '测试用例';

    /**
     * 界面UI服务对象
     *
     * @type {CaseUIService}
     * @memberof BatchNewBase
     */  
    public appUIService:CaseUIService = new CaseUIService(this.$store);


    /**
     * 界面行为模型
     *
     * @type {*}
     * @memberof BatchNewBase
     */  
    public ActionModel: any = {
    };

    /**
     * 主信息表格列
     *
     * @type {string}
     * @memberof BatchNewBase
     */  
    public majorInfoColName:string = "title";


    /**
     * 本地缓存标识
     *
     * @protected
     * @type {string}
     * @memberof BatchNewBase
     */
    protected localStorageTag: string = 'zt_case_batchnew_grid';

    /**
     * 是否支持分页
     *
     * @type {boolean}
     * @memberof BatchNewGridBase
     */
    public isEnablePagingBar: boolean = false;

    /**
     * 排序方向
     *
     * @type {string}
     * @memberof BatchNewGridBase
     */
    public minorSortDir: string = 'DESC';

    /**
     * 排序字段
     *
     * @type {string}
     * @memberof BatchNewGridBase
     */
    public minorSortPSDEF: string = 'id';

    /**
     * 分页条数
     *
     * @type {number}
     * @memberof BatchNewGridBase
     */
    public limit: number = 100;

    /**
     * 所有列成员
     *
     * @type {any[]}
     * @memberof BatchNewGridBase
     */
    public allColumns: any[] = [
        {
            name: 'modulename',
            label: '所属模块',
            langtag: 'entities.case.batchnew_grid.columns.modulename',
            show: true,
            unit: 'PX',
            isEnableRowEdit: true,
            enableCond: 3 ,
        },
        {
            name: 'storyname',
            label: '相关需求',
            langtag: 'entities.case.batchnew_grid.columns.storyname',
            show: true,
            unit: 'PX',
            isEnableRowEdit: true,
            enableCond: 3 ,
        },
        {
            name: 'title',
            label: '用例标题',
            langtag: 'entities.case.batchnew_grid.columns.title',
            show: true,
            unit: 'STAR',
            isEnableRowEdit: true,
            enableCond: 3 ,
        },
        {
            name: 'type',
            label: '用例类型',
            langtag: 'entities.case.batchnew_grid.columns.type',
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
     * @memberof BatchNewGridBase
     */
    public getGridRowModel(){
        return {
          product: new FormItemModel(),
          story: new FormItemModel(),
          modulename: new FormItemModel(),
          module: new FormItemModel(),
          title: new FormItemModel(),
          storyname: new FormItemModel(),
          type: new FormItemModel(),
          srfkey: new FormItemModel(),
        }
    }

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof BatchNewGridBase
     */
    public rules() {
        return {
        product: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '所属产品 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '所属产品 值不能为空', trigger: 'blur' },
        ],
        story: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '相关需求 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '相关需求 值不能为空', trigger: 'blur' },
        ],
        modulename: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '所属模块 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '所属模块 值不能为空', trigger: 'blur' },
        ],
        module: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '所属模块 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '所属模块 值不能为空', trigger: 'blur' },
        ],
        title: [
            { required: true, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '用例标题 值不能为空', trigger: 'change' },
            { required: true, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '用例标题 值不能为空', trigger: 'blur' },
        ],
        storyname: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '相关需求 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '相关需求 值不能为空', trigger: 'blur' },
        ],
        type: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '用例类型 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '用例类型 值不能为空', trigger: 'blur' },
        ],
        srfkey: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '用例编号 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '用例编号 值不能为空', trigger: 'blur' },
        ],
    }
    }

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof BatchNewBase
     */
    public deRules:any = {
    };

    /**
     * 获取对应列class
     *
     * @type {*}
     * @memberof BatchNewBase
     */
    public hasRowEdit: any = {
        'modulename':true,
        'storyname':true,
        'title':true,
        'type':true,
    };

    /**
     * 获取对应列class
     *
     * @param {*} $args row 行数据，column 列数据，rowIndex 行索引，列索引
     * @returns {void}
     * @memberof BatchNewBase
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
     * @memberof BatchNewGridBase
     */
    public async formatExcelData(filterVal: any, jsonData: any, codelistColumns?: any[]): Promise<any> {
        return super.formatExcelData(filterVal, jsonData, [
            {
                name: 'type',
                srfkey: 'Testcase__type',
                codelistType : 'STATIC',
                renderMode: 'other',
                textSeparator: '、',
                valueSeparator: ',',
            },
        ]);
    }


    /**
     * 新建默认值
     * @param {*}  row 行数据
     * @memberof BatchNewBase
     */
    public createDefault(row: any){                    
        if (row.hasOwnProperty('product')) {
            row['product'] = this.viewparams['product'];
        }
    }


    /**
     * 更新默认值
     * @param {*}  row 行数据
     * @memberof BatchNewBase
     */
    public updateDefault(row: any){                    
    }

    /**
     * 计算数据对象类型的默认值
     * @param {string}  action 行为
     * @param {string}  param 默认值参数
     * @param {*}  data 当前行数据
     * @memberof BatchNewBase
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