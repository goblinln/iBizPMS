import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { UIActionTool,Util,ViewTool } from '@/utils';
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
        Edit: { name: 'Edit',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__UNIVERSALDELETE', actiontarget: 'SINGLEKEY'},
        Delete: { name: 'Delete',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__UNIVERSALDELETE', actiontarget: 'SINGLEKEY'}
    };

    /**
     * 主信息表格列
     *
     * @type {string}
     * @memberof PlatformManagementBase
     */  
    public majorInfoColName:string = "name";

    /**
     * 列主键属性名称
     *
     * @type {string}
     * @memberof PlatformManagementGridBase
     */
    public columnKeyName: string = "id";

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
            enableCond: 3 ,
        },
        {
            name: 'name',
            label: '名称',
            langtag: 'entities.branch.platformmanagement_grid.columns.name',
            show: true,
            unit: 'STAR',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'order',
            label: '排序',
            langtag: 'entities.branch.platformmanagement_grid.columns.order',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'uagridcolumn1',
            label: '操作',
            langtag: 'entities.branch.platformmanagement_grid.columns.uagridcolumn1',
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
     * @memberof PlatformManagementGridBase
     */
    public getGridRowModel(){
        return {
          srfkey: new FormItemModel(),
        }
    }

    /**
     * 是否启用分组
     *
     * @type {boolean}
     * @memberof PlatformManagementBase
     */
    public isEnableGroup:boolean = false;

    /**
     * 分组属性
     *
     * @type {string}
     * @memberof PlatformManagementBase
     */
    public groupAppField:string ="";

    /**
     * 分组属性代码表标识
     *
     * @type {string}
     * @memberof PlatformManagementBase
     */
    public groupAppFieldCodelistTag:string ="";

    /**
     * 分组属性代码表类型
     * 
     * @type {string}
     * @memberof PlatformManagementBase
     */
    public groupAppFieldCodelistType: string = "";

    /**
     * 分组模式
     *
     * @type {string}
     * @memberof PlatformManagementBase
     */
    public groupMode:string ="NONE";

    /**
     * 分组代码表标识
     * 
     * @type {string}
     * @memberof PlatformManagementBase
     */
    public codelistTag: string = "";

    /**
     * 分组代码表类型
     * 
     * @type {string}
     * @memberof PlatformManagementBase
     */
    public codelistType: string = "";

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof PlatformManagementGridBase
     */
    public rules() {
        return {
        srfkey: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '编号 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '编号 值不能为空', trigger: 'blur' },
        ],
    }
    }

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof PlatformManagementBase
     */
    public deRules:any = {
    };

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
     * 是否为实体导出对象
     *
     * @protected
     * @type {boolean}
     * @memberof PlatformManagementGridBase
     */
    protected isDeExport: boolean = true;

    /**
     * 所有导出列成员
     *
     * @type {any[]}
     * @memberof PlatformManagementGridBase
     */
    public allExportColumns: any[] = [
        {
            name: 'id',
            label: '编号',
            langtag: 'entities.branch.platformmanagement_grid.exportColumns.id',
            show: true,
        },
        {
            name: 'name',
            label: '名称',
            langtag: 'entities.branch.platformmanagement_grid.exportColumns.name',
            show: true,
        },
        {
            name: 'order',
            label: '排序',
            langtag: 'entities.branch.platformmanagement_grid.exportColumns.order',
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
     * 分组方法
     * 
     * @memberof PlatformManagementBase
     */
    public group(){
        if(Object.is(this.groupMode,"AUTO")){
            this.drawGroup();
        }else if(Object.is(this.groupMode,"CODELIST")){
            this.drawCodelistGroup();
        }
    }

    /**
     * 获取表格分组相关代码表
     * 
     * @param {string}  codelistType 代码表类型
     * @param {string}  codelistTag 代码表标识
     * @memberof PlatformManagementBase
     */
    public getGroupCodelist(codelistType: string,codelistTag:string){
        let codelist: Array<any> = [];
        // 动态代码表
        if (Object.is(codelistType, "DYNAMIC")) {
            this.codeListService.getItems(codelistTag).then((res: any)=>{
                codelist = res;
            }).catch((error: any) => {
                
            });
        // 静态代码表
        } else if(Object.is(codelistType, "STATIC")){
            codelist = this.$store.getters.getCodeListItems(codelistTag);
        }
        return codelist;
    }

    /**
     * 根据分组代码表绘制分组列表
     * 
     * @memberof PlatformManagementBase
     */
    public drawCodelistGroup(){
        if(!this.isEnableGroup) return;
        // 分组
        let allGroup: Array<any> = [];
        let allGroupField: Array<any> =[];
        let groupTree:Array<any> = [];
        allGroup = this.getGroupCodelist(this.codelistType,this.codelistTag);
        allGroupField = this.getGroupCodelist(this.groupAppFieldCodelistType,this.groupAppFieldCodelistTag);
        if(allGroup.length == 0){
            console.warn("分组数据无效");
        }
        allGroup.forEach((group: any,i: number)=>{
            let children:Array<any> = [];
            this.items.forEach((item: any,j: number)=>{
                if(allGroupField && allGroupField.length > 0){
                    const arr:Array<any> = allGroupField.filter((field:any)=>{return field.value == item[this.groupAppField]});
                    if(Object.is(group.label,arr[0].label)){
                        item.groupById = Number((i+1) * 100 + (i+1) * 1);
                        item.group = '';
                        children.push(item);
                    }
                }else if(Object.is(group.label,item[this.groupAppField])){
                    item.groupById = Number((i+1) * 100 + (i+1) * 1);
                    item.group = '';
                    children.push(item);
                }
            });
            const tree: any ={
                groupById: Number((i+1)*100),
                group: group.label,
                id:'',
                name:'',
                order:'',
                Edit:{
                    visible: false
                },
                Delete:{
                    visible: false
                },
                children: children
            }
            groupTree.push(tree);
        });
        let child:Array<any> = [];
        this.items.forEach((item: any,index: number)=>{
            let i: number = 0;
            if(allGroupField && allGroupField.length > 0){
                const arr:Array<any> = allGroupField.filter((field:any)=>{return field.value == item[this.groupAppField]});
                i = allGroup.findIndex((group: any)=>Object.is(group.label,arr[0].label));
            }else{
                i = allGroup.findIndex((group: any)=>Object.is(group.label,item[this.groupAppField]));
            }
            if(i < 0){
                item.groupById = Number((allGroup.length+1) * 100 + (index+1) * 1);
                item.group = '';
                child.push(item);
            }
        })
        const Tree: any = {
            groupById: Number((allGroup.length+1)*100),
            group: '其他',
            id:'',
            name:'',
            order:'',
            Edit:{
                visible: false
            },
            Delete:{
                visible: false
            },
            children: child
        }
        if(child && child.length > 0){
            groupTree.push(Tree);
        }
        this.items = groupTree;
        if(this.actualIsOpenEdit) {
            for(let i = 0; i < this.items.length; i++) {
                this.gridItemsModel.push(this.getGridRowModel());
            }
        }
    }

    /**
     * 绘制分组
     * 
     * @memberof PlatformManagementBase
     */
    public drawGroup(){
        if(!this.isEnableGroup) return;
        // 分组
        let allGroup: Array<any> = [];
        let allGroupField: Array<any> =[];
        allGroupField = this.getGroupCodelist(this.groupAppFieldCodelistType,this.groupAppFieldCodelistTag);
        this.items.forEach((item: any)=>{
            if(item.hasOwnProperty(this.groupAppField)){
                if(allGroupField && allGroupField.length > 0){
                    const arr:Array<any> = allGroupField.filter((field:any)=>{return field.value == item[this.groupAppField]});
                    allGroup.push(arr[0].label);
                }else{
                    allGroup.push(item[this.groupAppField]);
                }
            }
        });
        let groupTree:Array<any> = [];
        allGroup = [...new Set(allGroup)];
        if(allGroup.length == 0){
            console.warn("分组数据无效");
        }
        // 组装数据
        allGroup.forEach((group: any, groupIndex: number)=>{
            let children:Array<any> = [];
            this.items.forEach((item: any,itemIndex: number)=>{
                if(allGroupField && allGroupField.length > 0){
                    const arr:Array<any> = allGroupField.filter((field:any)=>{return field.value == item[this.groupAppField]});
                    if(Object.is(group,arr[0].label)){
                        item.groupById = Number((groupIndex+1) * 100 + (groupIndex+1) * 1);
                        item.group = '';
                        children.push(item);
                    }
                }else if(Object.is(group,item[this.groupAppField])){
                    item.groupById = Number((groupIndex+1) * 100 + (groupIndex+1) * 1);
                    item.group = '';
                    children.push(item);
                }
            });
            group = group ? group : '其他';
            const tree: any ={
                groupById: Number((groupIndex+1)*100),
                group: group,
                id:'',
                name:'',
                order:'',
                Edit:{
                    visible: false
                },
                Delete:{
                    visible: false
                },
                children: children,
            }
            groupTree.push(tree);
        });
        this.items = groupTree;
        if(this.actualIsOpenEdit) {
            for(let i = 0; i < this.items.length; i++) {
                this.gridItemsModel.push(this.getGridRowModel());
            }
        }
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