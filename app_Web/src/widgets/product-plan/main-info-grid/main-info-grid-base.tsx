import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { UIActionTool, Util, ViewTool } from '@/utils';
import { Watch, GridControlBase } from '@/studio-core';
import ProductPlanService from '@/service/product-plan/product-plan-service';
import MainInfoService from './main-info-grid-service';
import ProductPlanUIService from '@/uiservice/product-plan/product-plan-ui-service';
import { FormItemModel } from '@/model/form-detail';

/**
 * grid部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {MainInfoGridBase}
 */
export class MainInfoGridBase extends GridControlBase {
    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof MainInfoGridBase
     */
    protected controlType: string = 'GRID';

    /**
     * 建构部件服务对象
     *
     * @type {MainInfoService}
     * @memberof MainInfoGridBase
     */
    public service: MainInfoService = new MainInfoService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {ProductPlanService}
     * @memberof MainInfoGridBase
     */
    public appEntityService: ProductPlanService = new ProductPlanService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof MainInfoGridBase
     */
    protected appDeName: string = 'productplan';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof MainInfoGridBase
     */
    protected appDeLogicName: string = '产品计划';

    /**
     * 界面UI服务对象
     *
     * @type {ProductPlanUIService}
     * @memberof MainInfoBase
     */  
    public appUIService: ProductPlanUIService = new ProductPlanUIService(this.$store);

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_actions_ua470664_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:ProductPlanUIService  = new ProductPlanUIService();
        curUIService.ProductPlan_AddProject(datas,contextJO, paramJO,  $event, xData,this,"ProductPlan");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_actions_u54b2dce_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:ProductPlanUIService  = new ProductPlanUIService();
        curUIService.ProductPlan_RelationStory(datas,contextJO, paramJO,  $event, xData,this,"ProductPlan");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_actions_u02b47f0_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:ProductPlanUIService  = new ProductPlanUIService();
        curUIService.ProductPlan_RelationBug(datas,contextJO, paramJO,  $event, xData,this,"ProductPlan");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_actions_u7d4e6c8_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:ProductPlanUIService  = new ProductPlanUIService();
        curUIService.ProductPlan_MainEdit(datas,contextJO, paramJO,  $event, xData,this,"ProductPlan");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_actions_ud7f4fc7_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:ProductPlanUIService  = new ProductPlanUIService();
        curUIService.ProductPlan_NewSubPlan(datas,contextJO, paramJO,  $event, xData,this,"ProductPlan");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_actions_u38dee22_click(params: any = {}, tag?: any, $event?: any) {
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
        this.Copy(datas, contextJO,paramJO,  $event, xData,this,"ProductPlan");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_actions_ub056033_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:ProductPlanUIService  = new ProductPlanUIService();
        curUIService.ProductPlan_Delete(datas,contextJO, paramJO,  $event, xData,this,"ProductPlan");
    }

    /**
     * 拷贝
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @memberof ProductPlanGridViewBase
     */
    public Copy(args: any[],contextJO?:any, params?: any, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
        if (args.length === 0) {
            return;
        }
        const _this: any = this;
        if (_this.newdata && _this.newdata instanceof Function) {
            const data: any = { };
            if (args.length > 0) {
                Object.assign(data, { productplan: args[0].productplan });
            }
            if(!params) params = {};
            Object.assign(params,{copymode:true});
            _this.newdata([{ ...data }], params, $event, xData);
        } else {
            Object.assign(this.viewparams,{copymode:true});
        }
    }


    /**
     * 界面行为模型
     *
     * @type {*}
     * @memberof MainInfoBase
     */  
    public ActionModel: any = {
        AddProject: { name: 'AddProject',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__PROJ_CREATE_BUT', target: 'SINGLEKEY'},
        RelationStory: { name: 'RelationStory',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__PROP_LSTORY_BUT', target: 'SINGLEKEY'},
        RelationBug: { name: 'RelationBug',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__PROP_LBUG_BUT', target: 'SINGLEKEY'},
        MainEdit: { name: 'MainEdit',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__PROP_EDIT_BUT', target: 'SINGLEKEY'},
        NewSubPlan: { name: 'NewSubPlan',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__PROP_CHILD_BUT', target: 'SINGLEKEY'},
        Copy: { name: 'Copy',disabled: false, visible: true,noprivdisplaymode:2,dataaccaction: 'SRFUR__UNIVERSALCREATE', target: 'SINGLEKEY'},
        Delete: { name: 'Delete',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__PROP_DELETE_BUT', target: 'SINGLEKEY'}
    };

    /**
     * 本地缓存标识
     *
     * @protected
     * @type {string}
     * @memberof MainInfoBase
     */
    protected localStorageTag: string = 'zt_productplan_maininfo_grid';

    /**
     * 排序方向
     *
     * @type {string}
     * @memberof MainInfoGridBase
     */
    public minorSortDir: string = 'DESC';

    /**
     * 排序字段
     *
     * @type {string}
     * @memberof MainInfoGridBase
     */
    public minorSortPSDEF: string = 'id';

    /**
     * 所有列成员
     *
     * @type {any[]}
     * @memberof MainInfoGridBase
     */
    public allColumns: any[] = [
        {
            name: 'id',
            label: '编号',
            langtag: 'entities.productplan.maininfo_grid.columns.id',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'title',
            label: '名称',
            langtag: 'entities.productplan.maininfo_grid.columns.title',
            show: true,
            unit: 'STAR',
            isEnableRowEdit: false,
        },
        {
            name: 'beginstr',
            label: '开始日期',
            langtag: 'entities.productplan.maininfo_grid.columns.beginstr',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'endstr',
            label: '结束日期',
            langtag: 'entities.productplan.maininfo_grid.columns.endstr',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'storycnt',
            label: '需求数',
            langtag: 'entities.productplan.maininfo_grid.columns.storycnt',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'bugcnt',
            label: 'bug数',
            langtag: 'entities.productplan.maininfo_grid.columns.bugcnt',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'actions',
            label: '操作',
            langtag: 'entities.productplan.maininfo_grid.columns.actions',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
    ]

    /**
     * 获取表格行模型
     *
     * @type {*}
     * @memberof MainInfoGridBase
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
     * @memberof MainInfoGridBase
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
     * 获取对应列class
     *
     * @type {*}
     * @memberof MainInfoBase
     */
    public hasRowEdit: any = {
        'id':false,
        'title':false,
        'beginstr':false,
        'endstr':false,
        'storycnt':false,
        'bugcnt':false,
        'actions':false,
    };

    /**
     * 获取对应列class
     *
     * @param {*} $args row 行数据，column 列数据，rowIndex 行索引，列索引
     * @returns {void}
     * @memberof MainInfoBase
     */
    public getCellClassName(args: {row: any, column: any, rowIndex: number, columnIndex: number}): any {
        return ( this.hasRowEdit[args.column.property] && this.actualIsOpenEdit ) ? "edit-cell" : "info-cell";
    }


    /**
     * 是否为实体导出对象
     *
     * @protected
     * @type {boolean}
     * @memberof MainInfoGridBase
     */
    protected isDeExport: boolean = false;

    /**
     * 所有导出列成员
     *
     * @type {any[]}
     * @memberof MainInfoGridBase
     */
    public allExportColumns: any[] = [
        {
            name: 'id',
            label: '编号',
            langtag: 'entities.productplan.maininfo_grid.exportColumns.id',
            show: true,
        },
        {
            name: 'title',
            label: '名称',
            langtag: 'entities.productplan.maininfo_grid.exportColumns.title',
            show: true,
        },
        {
            name: 'beginstr',
            label: '开始日期',
            langtag: 'entities.productplan.maininfo_grid.exportColumns.beginstr',
            show: true,
        },
        {
            name: 'begin',
            label: '预计开始日期',
            langtag: 'entities.productplan.maininfo_grid.exportColumns.begin',
            show: true,
        },
        {
            name: 'end',
            label: '预计结束日期',
            langtag: 'entities.productplan.maininfo_grid.exportColumns.end',
            show: true,
        },
        {
            name: 'endstr',
            label: '结束日期',
            langtag: 'entities.productplan.maininfo_grid.exportColumns.endstr',
            show: true,
        },
        {
            name: 'storycnt',
            label: '需求数',
            langtag: 'entities.productplan.maininfo_grid.exportColumns.storycnt',
            show: true,
        },
        {
            name: 'bugcnt',
            label: 'bug数',
            langtag: 'entities.productplan.maininfo_grid.exportColumns.bugcnt',
            show: true,
        },
        {
            name: 'statuss',
            label: '状态',
            langtag: 'entities.productplan.maininfo_grid.exportColumns.statuss',
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
     * @memberof MainInfoGridBase
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
     * @memberof MainInfoGridBase
     */
	public uiAction(row: any, tag: any, $event: any): void {
        $event.stopPropagation();
        if(Object.is('AddProject', tag)) {
            this.grid_actions_ua470664_click(row, tag, $event);
        }
        if(Object.is('RelationStory', tag)) {
            this.grid_actions_u54b2dce_click(row, tag, $event);
        }
        if(Object.is('RelationBug', tag)) {
            this.grid_actions_u02b47f0_click(row, tag, $event);
        }
        if(Object.is('MainEdit', tag)) {
            this.grid_actions_u7d4e6c8_click(row, tag, $event);
        }
        if(Object.is('NewSubPlan', tag)) {
            this.grid_actions_ud7f4fc7_click(row, tag, $event);
        }
        if(Object.is('Copy', tag)) {
            this.grid_actions_u38dee22_click(row, tag, $event);
        }
        if(Object.is('Delete', tag)) {
            this.grid_actions_ub056033_click(row, tag, $event);
        }
    }

    /**
     * 表格数据加载
     *
     * @param {*} [opt={}]
     * @param {boolean} [pageReset=false]
     * @returns {void}
     * @memberof MainInfoGridBase
     */
    public load(opt: any = {}, pageReset: boolean = false): void {
        if (!this.fetchAction) {
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.gridpage.notConfig.fetchAction') as string) });
            return;
        }
        if (pageReset) {
            this.curPage = 1;
        }
        const arg: any = { ...opt };
        const page: any = {};
        if (this.isEnablePagingBar) {
            Object.assign(page, { page: this.curPage - 1, size: this.limit });
        }
        // 设置排序
        if (!this.isNoSort && !Object.is(this.minorSortDir, '') && !Object.is(this.minorSortPSDEF, '')) {
            const sort: string = this.minorSortPSDEF + "," + this.minorSortDir;
            Object.assign(page, { sort: sort });
        }
        Object.assign(arg, page);
        const parentdata: any = {};
        this.$emit('beforeload', parentdata);
        Object.assign(arg, parentdata);
        let tempViewParams: any = parentdata.viewparams ? parentdata.viewparams : {};
        Object.assign(tempViewParams, JSON.parse(JSON.stringify(this.viewparams)));
        Object.assign(arg, { viewparams: tempViewParams });
        const post: Promise<any> = this.service.search(this.fetchAction, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator);
        post.then((response: any) => {
            if (!response.status || response.status !== 200) {
                if (response.errorMessage) {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.errorMessage });
                }
                return;
            }
            const data: any = response.data;
            if(data.length === 0 && this.curPage > 1) {
                this.curPage--;
                this.load(opt, pageReset);
                return;
            }
            this.totalRecord = response.total;
            this.items = JSON.parse(JSON.stringify(data));
            // 清空selections,gridItemsModel
            this.selections = [];
            this.gridItemsModel = [];
            this.items.forEach(() => { this.gridItemsModel.push(this.getGridRowModel()) });
            this.items.forEach((item: any) => {
                this.setActionState(item);
            });
            this.$emit('load', this.items);
            // 向上下文中填充当前数据
            this.$appService.contextStore.setContextData(this.context, this.appDeName, { items: this.items });
            // 设置默认选中
            setTimeout(() => {
                if (this.isSelectFirstDefault) {
                    this.rowClick(this.items[0]);
                }
                if (this.selectedData) {
                    const refs: any = this.$refs;
                    if (refs.multipleTable) {
                        refs.multipleTable.clearSelection();
                        JSON.parse(this.selectedData).forEach((selection: any) => {
                            let selectedItem = this.items.find((item: any) => {
                                return Object.is(item.srfkey, selection.srfkey);
                            });
                            if (selectedItem) {
                                this.rowClick(selectedItem);
                            }
                        });
                    }
                }
            }, 300);
            // 
            this.addMore();
            this.copyData();
        }).catch((response: any) => {
            if (response && response.status === 401) {
                return;
            }
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.errorMessage });
        });
    }

    /**
    * 添加更多行
    * 
    * @memberof MainInfoBase
    */
    public addMore(){
        if(this.items.length > 0){
            this.items.forEach((item: any) => {
                if(item.hasOwnProperty('items') && item.items && item.items.length == 20){
                    const moreitem: any = {
                        children: true,
                        parent: item.id,
                        id: this.$util.createUUID(),                
                        title: '',
                        beginstr: '',
                        endstr: '',
                        storycnt: '',
                        bugcnt: '',
                        AddProject:{
                            visabled: false
                        },
                        RelationStory:{
                            visabled: false
                        },
                        RelationBug:{
                            visabled: false
                        },
                        MainEdit:{
                            visabled: false
                        },
                        NewSubPlan:{
                            visabled: false
                        },
                        Copy:{
                            visabled: false
                        },
                        Delete:{
                            visabled: false
                        },
                    }
                    item.items.push(moreitem);
                }
            })
        }
    }

    /**
    * 合并更多行
    * 
    * @param {{ row, column, rowIndex, columnIndex }} row 行数据 column 列数据 rowIndex 行索引 columnIndex 列索引
    * @memberof MainInfoBase
    */
    public arraySpanMethod({row, column, rowIndex, columnIndex} : any) {
        if(row && row.children) {
            if(columnIndex == this.allColumns.length) {
                return [1, this.allColumns.length+1];
            } else {
                return [0,0];
            }
        }
    }

    /**
     * 获取对应行class
     *
     * @param {{ row: any, rowIndex: number }} args row 行数据，rowIndex 行索引
     * @returns {string}
     * @memberof MainInfoGridBase
     */
    public getRowClassName(args: { row: any; rowIndex: number }): string {
        if(args.row.children){
            return 'grid-selected-row grid-more-row';
        }else{
            let isSelected = this.selections.some((item: any) => {
                return Object.is(item[this.appDeName], args.row[this.appDeName]);
            });
            return isSelected ? 'grid-selected-row' : '';
        }
    }

    /**
     * 加载更多
     *  
     * @param data
     * @memberof MainInfoGridBase
     */
    public loadMore(data: any){
    }

    /**
     * 表格数据加载
     *
     * @param {*} item
     * @returns {void}
     * @memberof MainInfoGridBase
     */
    public setActionState(item: any) {
        Object.assign(item, this.getActionState(item));
        if(item.items && item.items.length > 0) {
            item.items.forEach((data: any) => {
                let _data: any = this.service.handleResponseData('', data);
                Object.assign(data, _data);
                this.setActionState(data);
            })
        }
    }

    /**
     * 数据导出
     *
     * @param {*} [data={}]
     * @returns {void}
     * @memberof MainInfoGridBase
     */
    public exportExcel(data: any = {}): void {
        // 导出Excel
        const doExport = async (_data: any) => {
            const tHeader: Array<any> = [];
            const filterVal: Array<any> = [];
            (this.isDeExport ? this.allExportColumns : this.allColumns).forEach((item: any) => {
                item.show && item.label ? tHeader.push(this.$t(item.langtag)) : '';
                item.show && item.name ? filterVal.push(item.name) : '';
            });
            const data = await this.formatExcelData(filterVal, _data);
            this.$export.exportExcel().then((excel: any) => {
                excel.export_json_to_excel({
                    header: tHeader, //表头 必填
                    data, //具体数据 必填
                    filename: this.appDeLogicName + (this.$t('app.gridpage.grid') as string), //非必填
                    autoWidth: true, //非必填
                    bookType: 'xlsx', //非必填
                });
            });
        };
        const page: any = {};
        // 设置page，size
        if (Object.is(data.type, 'maxRowCount')) {
            Object.assign(page, { page: 0, size: data.maxRowCount });
        } else if (Object.is(data.type, 'activatedPage')) {
            if (this.isDeExport) {
                Object.assign(page, { page: this.curPage - 1, size: this.limit });
            } else {
                try {
                    const datas = [...this.items];
                    let exportData: Array<any> = [];
                    datas.forEach((data: any) => {
                        exportData.push(data);
                        if(data.hasOwnProperty('items') && data.items && data.items instanceof Array){
                            data.items.forEach((item: any) => {
                                exportData.push(item);
                            });
                        }
                    });
                    doExport(JSON.parse(JSON.stringify(exportData)));
                } catch (error) {
                    console.error(error);
                }
                return;
            }
        }
        // 设置排序
        if (!this.isNoSort && !Object.is(this.minorSortDir, '') && !Object.is(this.minorSortPSDEF, '')) {
            const sort: string = this.minorSortPSDEF + ',' + this.minorSortDir;
            Object.assign(page, { sort: sort });
        }
        const arg: any = {};
        Object.assign(arg, page);
        // 获取query,搜索表单，viewparams等父数据
        const parentdata: any = {};
        this.$emit('beforeload', parentdata);
        Object.assign(arg, parentdata);
        let tempViewParams: any = parentdata.viewparams ? parentdata.viewparams : {};
        Object.assign(tempViewParams, JSON.parse(JSON.stringify(this.viewparams)));
        Object.assign(arg, { viewparams: tempViewParams });
        let post: any;
        if (this.isDeExport) {
            post = this.service.searchDEExportData(
                this.fetchAction,
                JSON.parse(JSON.stringify(this.context)),
                arg,
                this.showBusyIndicator
            );
        } else {
            post = this.service.search(
                this.fetchAction,
                JSON.parse(JSON.stringify(this.context)),
                arg,
                this.showBusyIndicator
            );
        }
        post.then((response: any) => {
            if (!response || response.status !== 200) {
                this.$Notice.error({
                    title: '',
                    desc: (this.$t('app.gridpage.exportFail') as string) + ',' + response.info,
                });
                return;
            }
            try {
                const datas = [...response.data];
                let exportData: Array<any> = [];
                datas.forEach((data: any) => {
                    exportData.push(data);
                    if(data.hasOwnProperty('items') && data.items && data.items instanceof Array){
                        data.items.forEach((item: any) => {
                            exportData.push(item);
                        });
                    }
                });
                doExport(JSON.parse(JSON.stringify(exportData)));
            } catch (error) {
                console.error(error);
            }
        }).catch((response: any) => {
            if (response && response.status === 401) {
                return;
            }
            this.$Notice.error({ title: '', desc: this.$t('app.gridpage.exportFail') as string });
        });
    }
}