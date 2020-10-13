import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, GridControlBase } from '@/studio-core';
import ProductSumService from '@/service/product-sum/product-sum-service';
import ProductPlancntAndStorycnt_POService from './product-plancnt-and-storycnt-po-grid-service';
import ProductSumUIService from '@/uiservice/product-sum/product-sum-ui-service';
import { FormItemModel } from '@/model/form-detail';


/**
 * grid部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {ProductPlancntAndStorycnt_POGridBase}
 */
export class ProductPlancntAndStorycnt_POGridBase extends GridControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof ProductPlancntAndStorycnt_POGridBase
     */
    protected controlType: string = 'GRID';

    /**
     * 建构部件服务对象
     *
     * @type {ProductPlancntAndStorycnt_POService}
     * @memberof ProductPlancntAndStorycnt_POGridBase
     */
    public service: ProductPlancntAndStorycnt_POService = new ProductPlancntAndStorycnt_POService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {ProductSumService}
     * @memberof ProductPlancntAndStorycnt_POGridBase
     */
    public appEntityService: ProductSumService = new ProductSumService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof ProductPlancntAndStorycnt_POGridBase
     */
    protected appDeName: string = 'productsum';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof ProductPlancntAndStorycnt_POGridBase
     */
    protected appDeLogicName: string = '产品汇总表';

    /**
     * 界面UI服务对象
     *
     * @type {ProductSumUIService}
     * @memberof ProductPlancntAndStorycnt_POBase
     */  
    public appUIService:ProductSumUIService = new ProductSumUIService(this.$store);

    /**
     * 界面行为模型
     *
     * @type {*}
     * @memberof ProductPlancntAndStorycnt_POBase
     */  
    public ActionModel: any = {
    };

    /**
     * 本地缓存标识
     *
     * @protected
     * @type {string}
     * @memberof ProductPlancntAndStorycnt_POBase
     */
    protected localStorageTag: string = 'ibz_productsum_productplancntandstorycnt_po_grid';

    /**
     * 是否支持分页
     *
     * @type {boolean}
     * @memberof ProductPlancntAndStorycnt_POGridBase
     */
    public isEnablePagingBar: boolean = false;

    /**
     * 是否禁用排序
     *
     * @type {boolean}
     * @memberof ProductPlancntAndStorycnt_POGridBase
     */
    public isNoSort: boolean = true;

    /**
     * 分页条数
     *
     * @type {number}
     * @memberof ProductPlancntAndStorycnt_POGridBase
     */
    public limit: number = 100;

    /**
     * 所有列成员
     *
     * @type {any[]}
     * @memberof ProductPlancntAndStorycnt_POGridBase
     */
    public allColumns: any[] = [
        {
            name: 'name',
            label: '产品名称',
            langtag: 'entities.productsum.productplancntandstorycnt_po_grid.columns.name',
            show: true,
            unit: 'STAR',
            isEnableRowEdit: false,
        },
        {
            name: 'plan',
            label: '计划数',
            langtag: 'entities.productsum.productplancntandstorycnt_po_grid.columns.plan',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'storycnt',
            label: '需求数',
            langtag: 'entities.productsum.productplancntandstorycnt_po_grid.columns.storycnt',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
    ]

    /**
     * 获取表格行模型
     *
     * @type {*}
     * @memberof ProductPlancntAndStorycnt_POGridBase
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
     * @memberof ProductPlancntAndStorycnt_POGridBase
     */
    public rules: any = {
        srfkey: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '主键标识 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '主键标识 值不能为空', trigger: 'blur' },
        ],
    }

    /**
     * 获取对应列class
     *
     * @type {*}
     * @memberof ProductPlancntAndStorycnt_POBase
     */
    public hasRowEdit: any = {
        'name':false,
        'plan':false,
        'storycnt':false,
    };

    /**
     * 获取对应列class
     *
     * @param {*} $args row 行数据，column 列数据，rowIndex 行索引，列索引
     * @returns {void}
     * @memberof ProductPlancntAndStorycnt_POBase
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
     * @memberof ProductPlancntAndStorycnt_POGridBase
     */
    public async formatExcelData(filterVal: any, jsonData: any, codelistColumns?: any[]): Promise<any> {
        return super.formatExcelData(filterVal, jsonData, [
        ]);
    }



}