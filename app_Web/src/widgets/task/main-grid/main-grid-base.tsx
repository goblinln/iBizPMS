import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { UIActionTool, Util, ViewTool } from '@/utils';
import { Watch, GridControlBase } from '@/studio-core';
import TaskService from '@/service/task/task-service';
import MainService from './main-grid-service';
import TaskUIService from '@/uiservice/task/task-ui-service';
import { FormItemModel } from '@/model/form-detail';

/**
 * grid部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {MainGridBase}
 */
export class MainGridBase extends GridControlBase {
    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof MainGridBase
     */
    protected controlType: string = 'GRID';

    /**
     * 建构部件服务对象
     *
     * @type {MainService}
     * @memberof MainGridBase
     */
    public service: MainService = new MainService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {TaskService}
     * @memberof MainGridBase
     */
    public appEntityService: TaskService = new TaskService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof MainGridBase
     */
    protected appDeName: string = 'task';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof MainGridBase
     */
    protected appDeLogicName: string = '任务';

    /**
     * 界面UI服务对象
     *
     * @type {TaskUIService}
     * @memberof MainBase
     */  
    public appUIService: TaskUIService = new TaskUIService(this.$store);

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_assignedto_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:TaskUIService  = new TaskUIService();
        curUIService.Task_AssignTask(datas,contextJO, paramJO,  $event, xData,this,"Task");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u94afee5_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:TaskUIService  = new TaskUIService();
        curUIService.Task_confirmStoryChange(datas,contextJO, paramJO,  $event, xData,this,"Task");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u9e51301_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:TaskUIService  = new TaskUIService();
        curUIService.Task_MStartTaskDash1(datas,contextJO, paramJO,  $event, xData,this,"Task");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u164e1c8_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:TaskUIService  = new TaskUIService();
        curUIService.Task_CloseTask(datas,contextJO, paramJO,  $event, xData,this,"Task");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u0e87c97_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:TaskUIService  = new TaskUIService();
        curUIService.Task_done(datas,contextJO, paramJO,  $event, xData,this,"Task");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u90f5316_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:TaskUIService  = new TaskUIService();
        curUIService.Task_MainEdit(datas,contextJO, paramJO,  $event, xData,this,"Task");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_ua6566df_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:TaskUIService  = new TaskUIService();
        curUIService.Task_NewSubTask(datas,contextJO, paramJO,  $event, xData,this,"Task");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_ue92fc99_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:TaskUIService  = new TaskUIService();
        curUIService.Task_TaskFavorites(datas,contextJO, paramJO,  $event, xData,this,"Task");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_ufbbe2a3_click(params: any = {}, tag?: any, $event?: any) {
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
        this.Copy(datas, contextJO,paramJO,  $event, xData,this,"Task");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u9190267_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:TaskUIService  = new TaskUIService();
        curUIService.Task_TaskNFavorites(datas,contextJO, paramJO,  $event, xData,this,"Task");
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
     * @memberof TaskMainGridViewBase
     */
    public Copy(args: any[],contextJO?:any, params?: any, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
        if (args.length === 0) {
            return;
        }
        const _this: any = this;
        if (_this.newdata && _this.newdata instanceof Function) {
            const data: any = { };
            if (args.length > 0) {
                Object.assign(data, { task: args[0].task });
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
     * @memberof MainBase
     */  
    public ActionModel: any = {
        AssignTask: { name: 'AssignTask',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__TASK_ASSIGN_BUT', target: 'SINGLEKEY'},
        confirmStoryChange: { name: 'confirmStoryChange',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__TASK_XQCHANGE_BUT', target: 'SINGLEKEY'},
        MStartTaskDash1: { name: 'MStartTaskDash1',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__TASK_START_BUT', target: 'SINGLEKEY'},
        StartTask: { name: 'StartTask',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__TASK_START_BUT', target: 'SINGLEKEY'},
        CloseTask: { name: 'CloseTask',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__TASK_CLOSE_BUT', target: 'SINGLEKEY'},
        done: { name: 'done',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__TASK_COMPLETE_BUT', target: 'SINGLEKEY'},
        DoneTask: { name: 'DoneTask',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__TASK_COMPLETE_BUT', target: 'SINGLEKEY'},
        MainEdit: { name: 'MainEdit',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__TASK_EDIT_BUT', target: 'SINGLEKEY'},
        NewSubTask: { name: 'NewSubTask',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__TASK_SUBTASKS_BUT', target: 'SINGLEKEY'},
        TaskFavorites: { name: 'TaskFavorites',disabled: false, visible: true,noprivdisplaymode:2,dataaccaction: 'SRFUR__TASK_NFAVOR_BUT', target: 'SINGLEKEY'},
        Copy: { name: 'Copy',disabled: false, visible: true,noprivdisplaymode:2,dataaccaction: 'SRFUR__UNIVERSALCREATE', target: 'SINGLEKEY'},
        TaskNFavorites: { name: 'TaskNFavorites',disabled: false, visible: true,noprivdisplaymode:2,dataaccaction: 'SRFUR__TASK_FAVOR_BUT', target: 'SINGLEKEY'}
    };

    /**
     * 本地缓存标识
     *
     * @protected
     * @type {string}
     * @memberof MainBase
     */
    protected localStorageTag: string = 'zt_task_main_grid';

    /**
     * 排序方向
     *
     * @type {string}
     * @memberof MainGridBase
     */
    public minorSortDir: string = 'DESC';

    /**
     * 排序字段
     *
     * @type {string}
     * @memberof MainGridBase
     */
    public minorSortPSDEF: string = 'id';

    /**
     * 所有列成员
     *
     * @type {any[]}
     * @memberof MainGridBase
     */
    public allColumns: any[] = [
        {
            name: 'id',
            label: 'ID',
            langtag: 'entities.task.main_grid.columns.id',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'pri',
            label: 'P',
            langtag: 'entities.task.main_grid.columns.pri',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'name',
            label: '任务名称',
            langtag: 'entities.task.main_grid.columns.name',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'status1',
            label: '任务状态',
            langtag: 'entities.task.main_grid.columns.status1',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'assignedto',
            label: '指派给',
            langtag: 'entities.task.main_grid.columns.assignedto',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'finishedby',
            label: '完成者',
            langtag: 'entities.task.main_grid.columns.finishedby',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'estimate',
            label: '预计',
            langtag: 'entities.task.main_grid.columns.estimate',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'consumed',
            label: '消耗',
            langtag: 'entities.task.main_grid.columns.consumed',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'left',
            label: '剩余',
            langtag: 'entities.task.main_grid.columns.left',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'progressrate',
            label: '%',
            langtag: 'entities.task.main_grid.columns.progressrate',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'deadline',
            label: '截止日期',
            langtag: 'entities.task.main_grid.columns.deadline',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'uagridcolumn1',
            label: '操作',
            langtag: 'entities.task.main_grid.columns.uagridcolumn1',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
    ]

    /**
     * 获取表格行模型
     *
     * @type {*}
     * @memberof MainGridBase
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
     * @memberof MainGridBase
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
     * @memberof MainBase
     */
    public hasRowEdit: any = {
        'id':false,
        'pri':false,
        'name':false,
        'status1':false,
        'assignedto':false,
        'finishedby':false,
        'estimate':false,
        'consumed':false,
        'left':false,
        'progressrate':false,
        'deadline':false,
        'uagridcolumn1':false,
    };

    /**
     * 获取对应列class
     *
     * @param {*} $args row 行数据，column 列数据，rowIndex 行索引，列索引
     * @returns {void}
     * @memberof MainBase
     */
    public getCellClassName(args: {row: any, column: any, rowIndex: number, columnIndex: number}): any {
        return ( this.hasRowEdit[args.column.property] && this.actualIsOpenEdit ) ? "edit-cell" : "info-cell";
    }


    /**
     * 是否为实体导出对象
     *
     * @protected
     * @type {boolean}
     * @memberof MainGridBase
     */
    protected isDeExport: boolean = true;

    /**
     * 所有导出列成员
     *
     * @type {any[]}
     * @memberof MainGridBase
     */
    public allExportColumns: any[] = [
        {
            name: 'id',
            label: 'ID',
            langtag: 'entities.task.main_grid.exportColumns.id',
            show: true,
        },
        {
            name: 'pri',
            label: 'P',
            langtag: 'entities.task.main_grid.exportColumns.pri',
            show: true,
        },
        {
            name: 'name',
            label: '任务名称',
            langtag: 'entities.task.main_grid.exportColumns.name',
            show: true,
        },
        {
            name: 'status',
            label: '任务状态',
            langtag: 'entities.task.main_grid.exportColumns.status',
            show: true,
        },
        {
            name: 'status1',
            label: '任务状态',
            langtag: 'entities.task.main_grid.exportColumns.status1',
            show: true,
        },
        {
            name: 'assignedto',
            label: '指派给',
            langtag: 'entities.task.main_grid.exportColumns.assignedto',
            show: true,
        },
        {
            name: 'finishedby',
            label: '完成者',
            langtag: 'entities.task.main_grid.exportColumns.finishedby',
            show: true,
        },
        {
            name: 'estimate',
            label: '预计',
            langtag: 'entities.task.main_grid.exportColumns.estimate',
            show: true,
        },
        {
            name: 'consumed',
            label: '消耗',
            langtag: 'entities.task.main_grid.exportColumns.consumed',
            show: true,
        },
        {
            name: 'left',
            label: '剩余',
            langtag: 'entities.task.main_grid.exportColumns.left',
            show: true,
        },
        {
            name: 'deadline',
            label: '截止日期',
            langtag: 'entities.task.main_grid.exportColumns.deadline',
            show: true,
        },
        {
            name: 'isfavorites',
            label: '是否收藏',
            langtag: 'entities.task.main_grid.exportColumns.isfavorites',
            show: true,
        },
        {
            name: 'tasktype',
            label: '任务类型',
            langtag: 'entities.task.main_grid.exportColumns.tasktype',
            show: true,
        },
        {
            name: 'product',
            label: '产品',
            langtag: 'entities.task.main_grid.exportColumns.product',
            show: true,
        },
        {
            name: 'projectname',
            label: '所属项目',
            langtag: 'entities.task.main_grid.exportColumns.projectname',
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
     * @memberof MainGridBase
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
     * 界面行为
     *
     * @param {*} row
     * @param {*} tag
     * @param {*} $event
     * @memberof MainGridBase
     */
	public uiAction(row: any, tag: any, $event: any): void {
        $event.stopPropagation();
        if(Object.is('AssignTask', tag)) {
            this.grid_assignedto_click(row, tag, $event);
        }
        if(Object.is('confirmStoryChange', tag)) {
            this.grid_uagridcolumn1_u94afee5_click(row, tag, $event);
        }
        if(Object.is('MStartTaskDash1', tag)) {
            this.grid_uagridcolumn1_u9e51301_click(row, tag, $event);
        }
        if(Object.is('CloseTask', tag)) {
            this.grid_uagridcolumn1_u164e1c8_click(row, tag, $event);
        }
        if(Object.is('done', tag)) {
            this.grid_uagridcolumn1_u0e87c97_click(row, tag, $event);
        }
        if(Object.is('MainEdit', tag)) {
            this.grid_uagridcolumn1_u90f5316_click(row, tag, $event);
        }
        if(Object.is('NewSubTask', tag)) {
            this.grid_uagridcolumn1_ua6566df_click(row, tag, $event);
        }
        if(Object.is('TaskFavorites', tag)) {
            this.grid_uagridcolumn1_ue92fc99_click(row, tag, $event);
        }
        if(Object.is('Copy', tag)) {
            this.grid_uagridcolumn1_ufbbe2a3_click(row, tag, $event);
        }
        if(Object.is('TaskNFavorites', tag)) {
            this.grid_uagridcolumn1_u9190267_click(row, tag, $event);
        }
    }

    /**
     * 表格数据加载
     *
     * @param {*} [opt={}]
     * @param {boolean} [pageReset=false]
     * @returns {void}
     * @memberof MainGridBase
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
        }).catch((response: any) => {
            if (response && response.status === 401) {
                return;
            }
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.errorMessage });
        });
    }

    /**
     * 表格数据加载
     *
     * @param {*} item
     * @returns {void}
     * @memberof MainGridBase
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
}