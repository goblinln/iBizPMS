import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, GridControlBase } from '@/studio-core';
import BranchService from '@/service/branch/branch-service';
import PlatformManagementService from './platform-management-grid-service';
import BranchUIService from '@/uiservice/branch/branch-ui-service';
import { FormItemModel } from '@/model/form-detail';


/**
 * grid部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {PlatformManagementGridBase}
 */
export class PlatformManagementGridBase extends GridControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof PlatformManagementGridBase
     */
    protected controlType: string = 'GRID';

    /**
     * 建构部件服务对象
     *
     * @type {PlatformManagementService}
     * @memberof PlatformManagementGridBase
     */
    public service: PlatformManagementService = new PlatformManagementService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {BranchService}
     * @memberof PlatformManagementGridBase
     */
    public appEntityService: BranchService = new BranchService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof PlatformManagementGridBase
     */
    protected appDeName: string = 'branch';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof PlatformManagementGridBase
     */
    protected appDeLogicName: string = '产品的分支和平台信息';

    /**
     * 界面UI服务对象
     *
     * @type {BranchUIService}
     * @memberof PlatformManagementBase
     */  
    public appUIService:BranchUIService = new BranchUIService(this.$store);

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_ubc7e0b1_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:BranchUIService  = new BranchUIService();
        curUIService.Branch_Edit(datas,contextJO, paramJO,  $event, xData,this,"Branch");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u7324c5a_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:BranchUIService  = new BranchUIService();
        curUIService.Branch_Delete(datas,contextJO, paramJO,  $event, xData,this,"Branch");
    }


    /**
     * 界面行为模型
     *
     * @type {*}
     * @memberof PlatformManagementBase
     */  
    public ActionModel: any = {
        Edit: { name: 'Edit',disabled: false, visabled: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__UNIVERSALDELETE', actiontarget: 'SINGLEKEY'},
        Delete: { name: 'Delete',disabled: false, visabled: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__UNIVERSALDELETE', actiontarget: 'SINGLEKEY'}
    };

    /**
     * 主信息表格列
     *
     * @type {string}
     * @memberof PlatformManagementBase
     */  
    public majorInfoColName:string = "name";

    /**
     * 本地缓存标识
     *
     * @protected
     * @type {string}
     * @memberof PlatformManagementBase
     */
    protected localStorageTag: string = 'zt_branch_platformmanagement_grid';

    /**
     * 排序方向
     *
     * @type {string}
     * @memberof PlatformManagementGridBase
     */
    public minorSortDir: string = 'DESC';

    /**
     * 排序字段
     *
     * @type {string}
     * @memberof PlatformManagementGridBase
     */
    public minorSortPSDEF: string = 'id';

    /**
     * 所有列成员
     *
     * @type {any[]}
     * @memberof PlatformManagementGridBase
     */
    public allColumns: any[] = [
        {
            name: 'id',
            label: '编号',
            langtag: 'entities.branch.platformmanagement_grid.columns.id',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'name',
            label: '名称',
            langtag: 'entities.branch.platformmanagement_grid.columns.name',
            show: true,
            unit: 'STAR',
            isEnableRowEdit: false,
        },
        {
            name: 'order',
            label: '排序',
            langtag: 'entities.branch.platformmanagement_grid.columns.order',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'uagridcolumn1',
            label: '操作',
            langtag: 'entities.branch.platformmanagement_grid.columns.uagridcolumn1',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
    ]

    /**
     * 获取表格行模型
     *
     * @type {*}
     * @memberof PlatformManagementGridBase
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
     * @memberof PlatformManagementGridBase
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
     * @memberof PlatformManagementBase
     */
    public hasRowEdit: any = {
        'id':false,
        'name':false,
        'order':false,
        'uagridcolumn1':false,
    };

    /**
     * 获取对应列class
     *
     * @param {*} $args row 行数据，column 列数据，rowIndex 行索引，列索引
     * @returns {void}
     * @memberof PlatformManagementBase
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
     * @memberof PlatformManagementGridBase
     */
    public async formatExcelData(filterVal: any, jsonData: any, codelistColumns?: any[]): Promise<any> {
        return super.formatExcelData(filterVal, jsonData, [
        ]);
    }


    /**
     * 界面行为
     *
     * @param {*} row
     * @param {*} tag
     * @param {*} $event
     * @memberof PlatformManagementGridBase
     */
	public uiAction(row: any, tag: any, $event: any): void {
        $event.stopPropagation();
        if(Object.is('Edit', tag)) {
            this.grid_uagridcolumn1_ubc7e0b1_click(row, tag, $event);
        }
        if(Object.is('Delete', tag)) {
            this.grid_uagridcolumn1_u7324c5a_click(row, tag, $event);
        }
    }

    /**
     * 更新默认值
     * @param {*}  row 行数据
     * @memberof PlatformManagementBase
     */
    public updateDefault(row: any){                    
    }

    /**
     * 计算数据对象类型的默认值
     * @param {string}  action 行为
     * @param {string}  param 默认值参数
     * @param {*}  data 当前行数据
     * @memberof PlatformManagementBase
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