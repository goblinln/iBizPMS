import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, GridControlBase } from '@/studio-core';
import ProductService from '@/service/product/product-service';
import TestService from './test-grid-service';
import ProductUIService from '@/uiservice/product/product-ui-service';
import { FormItemModel } from '@/model/form-detail';


/**
 * grid部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {TestGridBase}
 */
export class TestGridBase extends GridControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof TestGridBase
     */
    protected controlType: string = 'GRID';

    /**
     * 建构部件服务对象
     *
     * @type {TestService}
     * @memberof TestGridBase
     */
    public service: TestService = new TestService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {ProductService}
     * @memberof TestGridBase
     */
    public appEntityService: ProductService = new ProductService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof TestGridBase
     */
    protected appDeName: string = 'product';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof TestGridBase
     */
    protected appDeLogicName: string = '产品';

    /**
     * 界面UI服务对象
     *
     * @type {ProductUIService}
     * @memberof TestBase
     */  
    public appUIService:ProductUIService = new ProductUIService(this.$store);


    /**
     * 界面行为模型
     *
     * @type {*}
     * @memberof TestBase
     */  
    public ActionModel: any = {
    };

    /**
     * 本地缓存标识
     *
     * @protected
     * @type {string}
     * @memberof TestBase
     */
    protected localStorageTag: string = 'zt_product_test_grid';

    /**
     * 排序方向
     *
     * @type {string}
     * @memberof TestGridBase
     */
    public minorSortDir: string = 'DESC';

    /**
     * 排序字段
     *
     * @type {string}
     * @memberof TestGridBase
     */
    public minorSortPSDEF: string = 'id';

    /**
     * 所有列成员
     *
     * @type {any[]}
     * @memberof TestGridBase
     */
    public allColumns: any[] = [
        {
            name: 'name',
            label: '产品名称',
            langtag: 'entities.product.test_grid.columns.name',
            show: true,
            unit: 'STAR',
            isEnableRowEdit: false,
        },
        {
            name: 'code',
            label: '产品代号',
            langtag: 'entities.product.test_grid.columns.code',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'status',
            label: '状态',
            langtag: 'entities.product.test_grid.columns.status',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'type',
            label: '产品类型',
            langtag: 'entities.product.test_grid.columns.type',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'linename',
            label: '产品线',
            langtag: 'entities.product.test_grid.columns.linename',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
    ]

    /**
     * 获取表格行模型
     *
     * @type {*}
     * @memberof TestGridBase
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
     * @memberof TestGridBase
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
     * @memberof TestBase
     */
    public hasRowEdit: any = {
        'name':false,
        'code':false,
        'status':false,
        'type':false,
        'linename':false,
    };

    /**
     * 获取对应列class
     *
     * @param {*} $args row 行数据，column 列数据，rowIndex 行索引，列索引
     * @returns {void}
     * @memberof TestBase
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
     * @memberof TestGridBase
     */
    public async formatExcelData(filterVal: any, jsonData: any, codelistColumns?: any[]): Promise<any> {
        return super.formatExcelData(filterVal, jsonData, [
            {
                name: 'status',
                srfkey: 'Product__status',
                codelistType : 'STATIC',
                renderMode: 'other',
                textSeparator: '、',
                valueSeparator: ',',
            },
            {
                name: 'type',
                srfkey: 'Product__type',
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
     * @memberof TestBase
     */
    public updateDefault(row: any){                    
    }

    /**
     * 计算数据对象类型的默认值
     * @param {string}  action 行为
     * @param {string}  param 默认值参数
     * @param {*}  data 当前行数据
     * @memberof TestBase
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