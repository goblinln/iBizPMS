import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { UIActionTool, Util, ViewTool } from '@/utils';
import { Watch, GridControlBase } from '@/studio-core';
import CaseService from '@/service/case/case-service';
import MainGridService from './main-grid-grid-service';
import CaseUIService from '@/uiservice/case/case-ui-service';
import { FormItemModel } from '@/model/form-detail';

/**
 * grid部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {MainGridGridBase}
 */
export class MainGridGridBase extends GridControlBase {
    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof MainGridGridBase
     */
    protected controlType: string = 'GRID';

    /**
     * 建构部件服务对象
     *
     * @type {MainGridService}
     * @memberof MainGridGridBase
     */
    public service: MainGridService = new MainGridService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {CaseService}
     * @memberof MainGridGridBase
     */
    public appEntityService: CaseService = new CaseService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof MainGridGridBase
     */
    protected appDeName: string = 'case';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof MainGridGridBase
     */
    protected appDeLogicName: string = '测试用例';

    /**
     * 界面UI服务对象
     *
     * @type {CaseUIService}
     * @memberof MainGridBase
     */  
    public appUIService: CaseUIService = new CaseUIService(this.$store);

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u82280f9_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:CaseUIService  = new CaseUIService();
        curUIService.Case_confirmstorychange(datas,contextJO, paramJO,  $event, xData,this,"Case");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u333b640_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:CaseUIService  = new CaseUIService();
        curUIService.Case_OpenTestRunResultView(datas,contextJO, paramJO,  $event, xData,this,"Case");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_ufc5dbb7_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:CaseUIService  = new CaseUIService();
        curUIService.Case_Execute(datas,contextJO, paramJO,  $event, xData,this,"Case");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u5c4f2da_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:CaseUIService  = new CaseUIService();
        curUIService.Case_MainEdit(datas,contextJO, paramJO,  $event, xData,this,"Case");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_uc64fb7f_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:CaseUIService  = new CaseUIService();
        curUIService.Case_NewBugByCaseResult(datas,contextJO, paramJO,  $event, xData,this,"Case");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u8403ebe_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:CaseUIService  = new CaseUIService();
        curUIService.Case_caseFavorite(datas,contextJO, paramJO,  $event, xData,this,"Case");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u57243e1_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:CaseUIService  = new CaseUIService();
        curUIService.Case_CaseNFavorite(datas,contextJO, paramJO,  $event, xData,this,"Case");
    }

    /**
     * 刷新
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @memberof CaseMainMyGridViewBase
     */
    public Refresh(args: any[],contextJO?:any, params?: any, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
        const _this: any = this;
        if (xData && xData.refresh && xData.refresh instanceof Function) {
            xData.refresh(args);
        } else if (_this.refresh && _this.refresh instanceof Function) {
            _this.refresh(args);
        }
    }


    /**
     * 界面行为模型
     *
     * @type {*}
     * @memberof MainGridBase
     */  
    public ActionModel: any = {
        confirmstorychange: { name: 'confirmstorychange',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__CASE_CONFIRM_BUT', actiontarget: 'SINGLEKEY'},
        OpenTestRunResultView: { name: 'OpenTestRunResultView',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__CASE_RESULT_BUT', actiontarget: 'SINGLEKEY'},
        Execute: { name: 'Execute',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__CASE_CASERUN_BUT', actiontarget: 'SINGLEKEY'},
        MainEdit: { name: 'MainEdit',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__CASE_EDIT_BUT', actiontarget: 'SINGLEKEY'},
        Refresh: { name: 'Refresh',disabled: false, visible: true,noprivdisplaymode:2,dataaccaction: '', actiontarget: ''},
        NewBugByCaseResult: { name: 'NewBugByCaseResult',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__CASE_TOBUG_BUT', actiontarget: 'SINGLEKEY'},
        caseFavorite: { name: 'caseFavorite',disabled: false, visible: true,noprivdisplaymode:2,dataaccaction: 'SRFUR__CASE_NFAVOR_BUT', actiontarget: 'SINGLEKEY'},
        CaseNFavorite: { name: 'CaseNFavorite',disabled: false, visible: true,noprivdisplaymode:2,dataaccaction: 'SRFUR__CASE_FAVOR_BUT', actiontarget: 'SINGLEKEY'}
    };

    /**
     * 主信息表格列
     *
     * @type {string}
     * @memberof MainGridBase
     */  
    public majorInfoColName:string = "title";

    /**
     * 列主键属性名称
     *
     * @type {string}
     * @memberof MainGridGridBase
     */
    public columnKeyName: string = "id";

    /**
     * 本地缓存标识
     *
     * @protected
     * @type {string}
     * @memberof MainGridBase
     */
    protected localStorageTag: string = 'zt_case_maingrid_grid';

    /**
     * 排序方向
     *
     * @type {string}
     * @memberof MainGridGridBase
     */
    public minorSortDir: string = 'DESC';

    /**
     * 排序字段
     *
     * @type {string}
     * @memberof MainGridGridBase
     */
    public minorSortPSDEF: string = 'id';

    /**
     * 所有列成员
     *
     * @type {any[]}
     * @memberof MainGridGridBase
     */
    public allColumns: any[] = [
        {
            name: 'id',
            label: 'id',
            langtag: 'entities.case.maingrid_grid.columns.id',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'pri',
            label: 'P',
            langtag: 'entities.case.maingrid_grid.columns.pri',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'title',
            label: '用例标题',
            langtag: 'entities.case.maingrid_grid.columns.title',
            show: true,
            unit: 'STAR',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'type',
            label: '用例类型',
            langtag: 'entities.case.maingrid_grid.columns.type',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'openedby',
            label: '创建',
            langtag: 'entities.case.maingrid_grid.columns.openedby',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'lastrunner',
            label: '执行人',
            langtag: 'entities.case.maingrid_grid.columns.lastrunner',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'lastrundate',
            label: '执行时间',
            langtag: 'entities.case.maingrid_grid.columns.lastrundate',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'lastrunresult',
            label: '结果',
            langtag: 'entities.case.maingrid_grid.columns.lastrunresult',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'status1',
            label: '状态',
            langtag: 'entities.case.maingrid_grid.columns.status1',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'tobugcnt',
            label: 'B',
            langtag: 'entities.case.maingrid_grid.columns.tobugcnt',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'resultcnt',
            label: 'R',
            langtag: 'entities.case.maingrid_grid.columns.resultcnt',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'casesteps',
            label: 'S',
            langtag: 'entities.case.maingrid_grid.columns.casesteps',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'uagridcolumn1',
            label: '操作',
            langtag: 'entities.case.maingrid_grid.columns.uagridcolumn1',
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
     * @memberof MainGridGridBase
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
     * @memberof MainGridBase
     */
    public isEnableGroup:boolean = false;

    /**
     * 分组属性
     *
     * @type {string}
     * @memberof MainGridBase
     */
    public groupAppField:string ="";

    /**
     * 分组属性代码表标识
     *
     * @type {string}
     * @memberof MainGridBase
     */
    public groupAppFieldCodelistTag:string ="";

    /**
     * 分组属性代码表类型
     * 
     * @type {string}
     * @memberof MainGridBase
     */
    public groupAppFieldCodelistType: string = "";

    /**
     * 分组模式
     *
     * @type {string}
     * @memberof MainGridBase
     */
    public groupMode:string ="NONE";

    /**
     * 分组代码表标识
     * 
     * @type {string}
     * @memberof MainGridBase
     */
    public codelistTag: string = "";

    /**
     * 分组代码表类型
     * 
     * @type {string}
     * @memberof MainGridBase
     */
    public codelistType: string = "";

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof MainGridGridBase
     */
    public rules() {
        return {
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
     * @memberof MainGridBase
     */
    public deRules:any = {
    };

    /**
     * 获取对应列class
     *
     * @type {*}
     * @memberof MainGridBase
     */
    public hasRowEdit: any = {
        'id':false,
        'pri':false,
        'title':false,
        'type':false,
        'openedby':false,
        'lastrunner':false,
        'lastrundate':false,
        'lastrunresult':false,
        'status1':false,
        'tobugcnt':false,
        'resultcnt':false,
        'casesteps':false,
        'uagridcolumn1':false,
    };

    /**
     * 获取对应列class
     *
     * @param {*} $args row 行数据，column 列数据，rowIndex 行索引，列索引
     * @returns {void}
     * @memberof MainGridBase
     */
    public getCellClassName(args: {row: any, column: any, rowIndex: number, columnIndex: number}): any {
        let className: string = '';
        if(args.column.property){
          let col = this.allColumns.find((item:any)=>{
              return Object.is(args.column.property,item.name);
          })
          if(col !== undefined){
              if(col.isEnableRowEdit && this.actualIsOpenEdit ){
                className += 'edit-cell ';
              }
          } else {
              className += 'info-cell';
          }
        }
        if(this.groupAppField && args.columnIndex === 0 && !this.isSingleSelect) {
            if(args.row.children && args.row.children.length > 0) {
                className += this.computeGroupRow(args.row.children, args.row);
            }
        }
        return className;
    }
    
    /**
     * 计算分组行checkbox选中样式
     *
     * @param {*} rows 当前分组行下的所有数据
     * @returns {*} currentRow 当前分组行
     * @memberof MainBase
     */
    public computeGroupRow(rows: any[], currentRow: any) {
        let count: number = 0;
        this.selections.forEach((select: any) => {
            rows.forEach((row: any) => {
                if(row.groupById === select.groupById) {
                    count++;
                }
            })
        })
        if(count === rows.length) {
            (this.$refs.multipleTable as any).toggleRowSelection(currentRow, true);
            return 'cell-select-all ';
        } else if(count !== 0 && count < rows.length) {
            return 'cell-indeterminate '
        } else if(count === 0) {
            (this.$refs.multipleTable as any).toggleRowSelection(currentRow, false);
            return '';
        }
    }

    /**
     * 是否为实体导出对象
     *
     * @protected
     * @type {boolean}
     * @memberof MainGridGridBase
     */
    protected isDeExport: boolean = true;

    /**
     * 所有导出列成员
     *
     * @type {any[]}
     * @memberof MainGridGridBase
     */
    public allExportColumns: any[] = [
        {
            name: 'id',
            label: 'id',
            langtag: 'entities.case.maingrid_grid.exportColumns.id',
            show: true,
        },
        {
            name: 'pri',
            label: 'P',
            langtag: 'entities.case.maingrid_grid.exportColumns.pri',
            show: true,
        },
        {
            name: 'title',
            label: '用例标题',
            langtag: 'entities.case.maingrid_grid.exportColumns.title',
            show: true,
        },
        {
            name: 'type',
            label: '用例类型',
            langtag: 'entities.case.maingrid_grid.exportColumns.type',
            show: true,
        },
        {
            name: 'openedby',
            label: '创建',
            langtag: 'entities.case.maingrid_grid.exportColumns.openedby',
            show: true,
        },
        {
            name: 'lastrunner',
            label: '执行人',
            langtag: 'entities.case.maingrid_grid.exportColumns.lastrunner',
            show: true,
        },
        {
            name: 'lastrundate',
            label: '执行时间',
            langtag: 'entities.case.maingrid_grid.exportColumns.lastrundate',
            show: true,
        },
        {
            name: 'lastrunresult',
            label: '结果',
            langtag: 'entities.case.maingrid_grid.exportColumns.lastrunresult',
            show: true,
        },
        {
            name: 'status',
            label: '状态',
            langtag: 'entities.case.maingrid_grid.exportColumns.status',
            show: true,
        },
        {
            name: 'status1',
            label: '状态',
            langtag: 'entities.case.maingrid_grid.exportColumns.status1',
            show: true,
        },
        {
            name: 'tobugcnt',
            label: 'B',
            langtag: 'entities.case.maingrid_grid.exportColumns.tobugcnt',
            show: true,
        },
        {
            name: 'resultcnt',
            label: 'R',
            langtag: 'entities.case.maingrid_grid.exportColumns.resultcnt',
            show: true,
        },
        {
            name: 'casesteps',
            label: 'S',
            langtag: 'entities.case.maingrid_grid.exportColumns.casesteps',
            show: true,
        },
        {
            name: 'lastrunresult1',
            label: '测试用例结果',
            langtag: 'entities.case.maingrid_grid.exportColumns.lastrunresult1',
            show: true,
        },
        {
            name: 'isfavorites',
            label: '是否收藏',
            langtag: 'entities.case.maingrid_grid.exportColumns.isfavorites',
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
     * @memberof MainGridGridBase
     */
    public async formatExcelData(filterVal: any, jsonData: any, codelistColumns?: any[]): Promise<any> {
        return super.formatExcelData(filterVal, jsonData, [
            {
                name: 'pri',
                srfkey: 'Testcase__pri',
                codelistType : 'STATIC',
                renderMode: 'other',
                textSeparator: '、',
                valueSeparator: ',',
            },
            {
                name: 'type',
                srfkey: 'Testcase__type',
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
                name: 'lastrunner',
                srfkey: 'UserRealName',
                codelistType : 'DYNAMIC',
                textSeparator: ',',
                renderMode: 'string',
                valueSeparator: ",",
            },
            {
                name: 'lastrunresult',
                srfkey: 'Testcase__result',
                codelistType : 'STATIC',
                renderMode: 'other',
                textSeparator: '、',
                valueSeparator: ',',
            },
            {
                name: 'status',
                srfkey: 'Testcase__status',
                codelistType : 'STATIC',
                renderMode: 'other',
                textSeparator: '、',
                valueSeparator: ',',
            },
            {
                name: 'status1',
                srfkey: 'TestCaseStatusGrid',
                codelistType : 'STATIC',
                renderMode: 'other',
                textSeparator: '、',
                valueSeparator: ',',
            },
            {
                name: 'lastrunresult1',
                srfkey: 'Testcase__result',
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
     * @memberof MainGridGridBase
     */
	public uiAction(row: any, tag: any, $event: any): void {
        $event.stopPropagation();
        if(Object.is('confirmstorychange', tag)) {
            this.grid_uagridcolumn1_u82280f9_click(row, tag, $event);
        }
        if(Object.is('OpenTestRunResultView', tag)) {
            this.grid_uagridcolumn1_u333b640_click(row, tag, $event);
        }
        if(Object.is('Execute', tag)) {
            this.grid_uagridcolumn1_ufc5dbb7_click(row, tag, $event);
        }
        if(Object.is('MainEdit', tag)) {
            this.grid_uagridcolumn1_u5c4f2da_click(row, tag, $event);
        }
        if(Object.is('NewBugByCaseResult', tag)) {
            this.grid_uagridcolumn1_uc64fb7f_click(row, tag, $event);
        }
        if(Object.is('caseFavorite', tag)) {
            this.grid_uagridcolumn1_u8403ebe_click(row, tag, $event);
        }
        if(Object.is('CaseNFavorite', tag)) {
            this.grid_uagridcolumn1_u57243e1_click(row, tag, $event);
        }
    }

    /**
     * 更新默认值
     * @param {*}  row 行数据
     * @memberof MainGridBase
     */
    public updateDefault(row: any){                    
    }

    /**
    * 合并分组行
    * 
    * @memberof MainGridBase
    */
    public arraySpanMethod({row, column, rowIndex, columnIndex} : any) {
        let allColumns:Array<any> = ['id','pri','title','type','openedby','lastrunner','lastrundate','lastrunresult','status1','tobugcnt','resultcnt','casesteps','uagridcolumn1'];
        if(row && row.children) {
            if(columnIndex == (this.isSingleSelect ? 0:1)) {
                return [1, allColumns.length+1];
            } else if(columnIndex > (this.isSingleSelect ? 0:1)) {
                return [0,0];
            }
        }
    }

	/**
     * 分组方法
     * 
     * @memberof MainGridBase
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
     * @memberof MainGridBase
     */
    public async getGroupCodelist(codelistType: string,codelistTag:string){
        let codelist: Array<any> = [];
        // 动态代码表
        if (Object.is(codelistType, "DYNAMIC")) {
             codelist = await this.codeListService.getItems(codelistTag);
        // 静态代码表
        } else if(Object.is(codelistType, "STATIC")){
            codelist = this.$store.getters.getCodeListItems(codelistTag);
        }
        return codelist;
    }

    /**
     * 根据分组代码表绘制分组列表
     * 
     * @memberof MainGridBase
     */
    public async drawCodelistGroup(){
        if(!this.isEnableGroup) return;
        // 分组
        let allGroup: Array<any> = [];
        let allGroupField: Array<any> =[];
        let groupTree:Array<any> = [];
        allGroup = await this.getGroupCodelist(this.codelistType,this.codelistTag);
        allGroupField = await this.getGroupCodelist(this.groupAppFieldCodelistType,this.groupAppFieldCodelistTag);
        if(allGroup.length == 0){
            console.warn("分组数据无效");
        }
        allGroup.forEach((group: any,i: number)=>{
            let children:Array<any> = [];
            this.items.forEach((item: any,j: number)=>{
                if(allGroupField && allGroupField.length > 0){
                    const arr:Array<any> = allGroupField.filter((field:any)=>{return field.value == item[this.groupAppField]});
                    if(arr && arr.length>0) {
                        if(Object.is(group.value,arr[0].value)){
                            item.groupById = Number((i+1) * 100 + (j+1) * 1);
                            item.group = '';
                            children.push(item);
                        }
                    }
                }else if(Object.is(group.value,item[this.groupAppField])){
                    item.groupById = Number((i+1) * 100 + (j+1) * 1);
                    item.group = '';
                    children.push(item);
                }
            });
            const tree: any ={
                groupById: Number((i+1)*100),
                group: group.label,
                id:'',
                pri:'',
                title:'',
                type:'',
                openedby:'',
                lastrunner:'',
                lastrundate:'',
                lastrunresult:'',
                status1:'',
                tobugcnt:'',
                resultcnt:'',
                casesteps:'',
                confirmstorychange:{
                    visible: false
                },
                OpenTestRunResultView:{
                    visible: false
                },
                Execute:{
                    visible: false
                },
                MainEdit:{
                    visible: false
                },
                NewBugByCaseResult:{
                    visible: false
                },
                caseFavorite:{
                    visible: false
                },
                CaseNFavorite:{
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
                if(arr && arr.length>0) {
                    i = allGroup.findIndex((group: any)=>Object.is(group.value,arr[0].value));
                }
            }else{
                i = allGroup.findIndex((group: any)=>Object.is(group.value,item[this.groupAppField]));
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
            pri:'',
            title:'',
            type:'',
            openedby:'',
            lastrunner:'',
            lastrundate:'',
            lastrunresult:'',
            status1:'',
            tobugcnt:'',
            resultcnt:'',
            casesteps:'',
            confirmstorychange:{
                visible: false
            },
            OpenTestRunResultView:{
                visible: false
            },
            Execute:{
                visible: false
            },
            MainEdit:{
                visible: false
            },
            NewBugByCaseResult:{
                visible: false
            },
            caseFavorite:{
                visible: false
            },
            CaseNFavorite:{
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
     * @memberof MainGridBase
     */
    public async drawGroup(){
        if(!this.isEnableGroup) return;
        // 分组
        let allGroup: Array<any> = [];
        let allGroupField: Array<any> =[];
        allGroupField = await this.getGroupCodelist(this.groupAppFieldCodelistType,this.groupAppFieldCodelistTag);
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
                        item.groupById = Number((groupIndex+1) * 100 + (itemIndex+1) * 1);
                        item.group = '';
                        children.push(item);
                    }
                }else if(Object.is(group,item[this.groupAppField])){
                    item.groupById = Number((groupIndex+1) * 100 + (itemIndex+1) * 1);
                    item.group = '';
                    children.push(item);
                }
            });
            group = group ? group : '其他';
            const tree: any ={
                groupById: Number((groupIndex+1)*100),
                group: group,
                id:'',
                pri:'',
                title:'',
                type:'',
                openedby:'',
                lastrunner:'',
                lastrundate:'',
                lastrunresult:'',
                status1:'',
                tobugcnt:'',
                resultcnt:'',
                casesteps:'',
                confirmstorychange:{
                    visible: false
                },
                OpenTestRunResultView:{
                    visible: false
                },
                Execute:{
                    visible: false
                },
                MainEdit:{
                    visible: false
                },
                NewBugByCaseResult:{
                    visible: false
                },
                caseFavorite:{
                    visible: false
                },
                CaseNFavorite:{
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
     * @memberof MainGridBase
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