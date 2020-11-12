import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { UIActionTool,Util,ViewTool } from '@/utils';
import { Watch, GridControlBase } from '@/studio-core';
import StoryService from '@/service/story/story-service';
import MyGroupMainService from './my-group-main-grid-service';
import StoryUIService from '@/uiservice/story/story-ui-service';
import { FormItemModel } from '@/model/form-detail';


/**
 * grid部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {MyGroupMainGridBase}
 */
export class MyGroupMainGridBase extends GridControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof MyGroupMainGridBase
     */
    protected controlType: string = 'GRID';

    /**
     * 建构部件服务对象
     *
     * @type {MyGroupMainService}
     * @memberof MyGroupMainGridBase
     */
    public service: MyGroupMainService = new MyGroupMainService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {StoryService}
     * @memberof MyGroupMainGridBase
     */
    public appEntityService: StoryService = new StoryService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof MyGroupMainGridBase
     */
    protected appDeName: string = 'story';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof MyGroupMainGridBase
     */
    protected appDeLogicName: string = '需求';

    /**
     * 界面UI服务对象
     *
     * @type {StoryUIService}
     * @memberof MyGroupMainBase
     */  
    public appUIService:StoryUIService = new StoryUIService(this.$store);

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u7b97712_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:StoryUIService  = new StoryUIService();
        curUIService.Story_ChangeStoryDetail(datas,contextJO, paramJO,  $event, xData,this,"Story");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_uc74c61c_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:StoryUIService  = new StoryUIService();
        curUIService.Story_ReviewStory(datas,contextJO, paramJO,  $event, xData,this,"Story");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u824d7d6_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:StoryUIService  = new StoryUIService();
        curUIService.Story_CloseStory(datas,contextJO, paramJO,  $event, xData,this,"Story");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u7480d3d_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:StoryUIService  = new StoryUIService();
        curUIService.Story_OpenBaseInfoEditView(datas,contextJO, paramJO,  $event, xData,this,"Story");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u5aaa4ae_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:StoryUIService  = new StoryUIService();
        curUIService.Story_OpenCaseCreateView(datas,contextJO, paramJO,  $event, xData,this,"Story");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u1c090d0_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:StoryUIService  = new StoryUIService();
        curUIService.Story_SubStory(datas,contextJO, paramJO,  $event, xData,this,"Story");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_ucfd61f1_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:StoryUIService  = new StoryUIService();
        curUIService.Story_StoryFavorites(datas,contextJO, paramJO,  $event, xData,this,"Story");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u0ac3876_click(params: any = {}, tag?: any, $event?: any) {
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
        const curUIService:StoryUIService  = new StoryUIService();
        curUIService.Story_StoryNFavorites(datas,contextJO, paramJO,  $event, xData,this,"Story");
    }


    /**
     * 界面行为模型
     *
     * @type {*}
     * @memberof MyGroupMainBase
     */  
    public ActionModel: any = {
        ChangeStoryDetail: { name: 'ChangeStoryDetail',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__STORY_CHANGED_BUT', actiontarget: 'SINGLEKEY'},
        ReviewStory: { name: 'ReviewStory',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__STORY_REVIEW_BUT', actiontarget: 'SINGLEKEY'},
        CloseStory: { name: 'CloseStory',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__STORY_CLOSED_BUT', actiontarget: 'SINGLEKEY'},
        OpenBaseInfoEditView: { name: 'OpenBaseInfoEditView',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__STORY_EDIT_BUT', actiontarget: 'SINGLEKEY'},
        OpenCaseCreateView: { name: 'OpenCaseCreateView',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__STORY_CCASE_BUT', actiontarget: 'SINGLEKEY'},
        SubStory: { name: 'SubStory',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__STORY_XQXF_BUT', actiontarget: 'SINGLEKEY'},
        StoryFavorites: { name: 'StoryFavorites',disabled: false, visible: true,noprivdisplaymode:2,dataaccaction: 'SRFUR__STORY_NFAVOR_BUT', actiontarget: 'SINGLEKEY'},
        StoryNFavorites: { name: 'StoryNFavorites',disabled: false, visible: true,noprivdisplaymode:2,dataaccaction: 'SRFUR__STORY_FAVOR_BUT', actiontarget: 'SINGLEKEY'}
    };

    /**
     * 主信息表格列
     *
     * @type {string}
     * @memberof MyGroupMainBase
     */  
    public majorInfoColName:string = "title";

    /**
     * 列主键属性名称
     *
     * @type {string}
     * @memberof MyGroupMainGridBase
     */
    public columnKeyName: string = "id";

    /**
     * 本地缓存标识
     *
     * @protected
     * @type {string}
     * @memberof MyGroupMainBase
     */
    protected localStorageTag: string = 'zt_story_mygroupmain_grid';

    /**
     * 排序方向
     *
     * @type {string}
     * @memberof MyGroupMainGridBase
     */
    public minorSortDir: string = 'DESC';

    /**
     * 排序字段
     *
     * @type {string}
     * @memberof MyGroupMainGridBase
     */
    public minorSortPSDEF: string = 'id';

    /**
     * 所有列成员
     *
     * @type {any[]}
     * @memberof MyGroupMainGridBase
     */
    public allColumns: any[] = [
        {
            name: 'id',
            label: 'ID',
            langtag: 'entities.story.mygroupmain_grid.columns.id',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'pri',
            label: 'P',
            langtag: 'entities.story.mygroupmain_grid.columns.pri',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'prodoctname',
            label: '产品名称',
            langtag: 'entities.story.mygroupmain_grid.columns.prodoctname',
            show: true,
            unit: 'STAR',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'title',
            label: '需求名称',
            langtag: 'entities.story.mygroupmain_grid.columns.title',
            show: true,
            unit: 'STAR',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'plan',
            label: '计划',
            langtag: 'entities.story.mygroupmain_grid.columns.plan',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'openedby',
            label: '创建',
            langtag: 'entities.story.mygroupmain_grid.columns.openedby',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'assignedto',
            label: '指派',
            langtag: 'entities.story.mygroupmain_grid.columns.assignedto',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'estimate',
            label: '预计',
            langtag: 'entities.story.mygroupmain_grid.columns.estimate',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'status',
            label: '状态',
            langtag: 'entities.story.mygroupmain_grid.columns.status',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'stage',
            label: '阶段',
            langtag: 'entities.story.mygroupmain_grid.columns.stage',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'modulename',
            label: '所属模块名称',
            langtag: 'entities.story.mygroupmain_grid.columns.modulename',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
            enableCond: 3 ,
        },
        {
            name: 'uagridcolumn1',
            label: '操作',
            langtag: 'entities.story.mygroupmain_grid.columns.uagridcolumn1',
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
     * @memberof MyGroupMainGridBase
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
     * @memberof MyGroupMainBase
     */
    public isEnableGroup:boolean = false;

    /**
     * 分组属性
     *
     * @type {string}
     * @memberof MyGroupMainBase
     */
    public groupAppField:string ="";

    /**
     * 分组属性代码表标识
     *
     * @type {string}
     * @memberof MyGroupMainBase
     */
    public groupAppFieldCodelistTag:string ="";

    /**
     * 分组属性代码表类型
     * 
     * @type {string}
     * @memberof MyGroupMainBase
     */
    public groupAppFieldCodelistType: string = "";

    /**
     * 分组模式
     *
     * @type {string}
     * @memberof MyGroupMainBase
     */
    public groupMode:string ="NONE";

    /**
     * 分组代码表标识
     * 
     * @type {string}
     * @memberof MyGroupMainBase
     */
    public codelistTag: string = "";

    /**
     * 分组代码表类型
     * 
     * @type {string}
     * @memberof MyGroupMainBase
     */
    public codelistType: string = "";

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof MyGroupMainGridBase
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
     * @memberof MyGroupMainBase
     */
    public deRules:any = {
    };

    /**
     * 获取对应列class
     *
     * @type {*}
     * @memberof MyGroupMainBase
     */
    public hasRowEdit: any = {
        'id':false,
        'pri':false,
        'prodoctname':false,
        'title':false,
        'plan':false,
        'openedby':false,
        'assignedto':false,
        'estimate':false,
        'status':false,
        'stage':false,
        'modulename':false,
        'uagridcolumn1':false,
    };

    /**
     * 获取对应列class
     *
     * @param {*} $args row 行数据，column 列数据，rowIndex 行索引，列索引
     * @returns {void}
     * @memberof MyGroupMainBase
     */
    public getCellClassName(args: {row: any, column: any, rowIndex: number, columnIndex: number}): any {
        return ( this.hasRowEdit[args.column.property] && this.actualIsOpenEdit ) ? "edit-cell" : "info-cell";
    }


    /**
     * 是否为实体导出对象
     *
     * @protected
     * @type {boolean}
     * @memberof MyGroupMainGridBase
     */
    protected isDeExport: boolean = true;

    /**
     * 所有导出列成员
     *
     * @type {any[]}
     * @memberof MyGroupMainGridBase
     */
    public allExportColumns: any[] = [
        {
            name: 'id',
            label: 'ID',
            langtag: 'entities.story.mygroupmain_grid.exportColumns.id',
            show: true,
        },
        {
            name: 'pri',
            label: 'P',
            langtag: 'entities.story.mygroupmain_grid.exportColumns.pri',
            show: true,
        },
        {
            name: 'title',
            label: '需求名称',
            langtag: 'entities.story.mygroupmain_grid.exportColumns.title',
            show: true,
        },
        {
            name: 'plan',
            label: '计划',
            langtag: 'entities.story.mygroupmain_grid.exportColumns.plan',
            show: true,
        },
        {
            name: 'openedby',
            label: '创建',
            langtag: 'entities.story.mygroupmain_grid.exportColumns.openedby',
            show: true,
        },
        {
            name: 'assignedto',
            label: '指派',
            langtag: 'entities.story.mygroupmain_grid.exportColumns.assignedto',
            show: true,
        },
        {
            name: 'estimate',
            label: '预计',
            langtag: 'entities.story.mygroupmain_grid.exportColumns.estimate',
            show: true,
        },
        {
            name: 'status',
            label: '状态',
            langtag: 'entities.story.mygroupmain_grid.exportColumns.status',
            show: true,
        },
        {
            name: 'stage',
            label: '阶段',
            langtag: 'entities.story.mygroupmain_grid.exportColumns.stage',
            show: true,
        },
        {
            name: 'modulename',
            label: '所属模块名称',
            langtag: 'entities.story.mygroupmain_grid.exportColumns.modulename',
            show: true,
        },
        {
            name: 'module',
            label: '所属模块',
            langtag: 'entities.story.mygroupmain_grid.exportColumns.module',
            show: true,
        },
        {
            name: 'isfavorites',
            label: '是否收藏',
            langtag: 'entities.story.mygroupmain_grid.exportColumns.isfavorites',
            show: true,
        },
        {
            name: 'ischild',
            label: '是否可以细分',
            langtag: 'entities.story.mygroupmain_grid.exportColumns.ischild',
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
     * @memberof MyGroupMainGridBase
     */
    public async formatExcelData(filterVal: any, jsonData: any, codelistColumns?: any[]): Promise<any> {
        return super.formatExcelData(filterVal, jsonData, [
            {
                name: 'pri',
                srfkey: 'Story__pri',
                codelistType : 'STATIC',
                renderMode: 'other',
                textSeparator: '、',
                valueSeparator: ',',
            },
            {
                name: 'plan',
                srfkey: 'CurProductPlan',
                codelistType : 'DYNAMIC',
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
                name: 'assignedto',
                srfkey: 'UserRealName',
                codelistType : 'DYNAMIC',
                textSeparator: ',',
                renderMode: 'string',
                valueSeparator: ",",
            },
            {
                name: 'status',
                srfkey: 'Story__status',
                codelistType : 'STATIC',
                renderMode: 'other',
                textSeparator: '、',
                valueSeparator: ',',
            },
            {
                name: 'stage',
                srfkey: 'Story__stage',
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
     * @memberof MyGroupMainGridBase
     */
	public uiAction(row: any, tag: any, $event: any): void {
        $event.stopPropagation();
        if(Object.is('ChangeStoryDetail', tag)) {
            this.grid_uagridcolumn1_u7b97712_click(row, tag, $event);
        }
        if(Object.is('ReviewStory', tag)) {
            this.grid_uagridcolumn1_uc74c61c_click(row, tag, $event);
        }
        if(Object.is('CloseStory', tag)) {
            this.grid_uagridcolumn1_u824d7d6_click(row, tag, $event);
        }
        if(Object.is('OpenBaseInfoEditView', tag)) {
            this.grid_uagridcolumn1_u7480d3d_click(row, tag, $event);
        }
        if(Object.is('OpenCaseCreateView', tag)) {
            this.grid_uagridcolumn1_u5aaa4ae_click(row, tag, $event);
        }
        if(Object.is('SubStory', tag)) {
            this.grid_uagridcolumn1_u1c090d0_click(row, tag, $event);
        }
        if(Object.is('StoryFavorites', tag)) {
            this.grid_uagridcolumn1_ucfd61f1_click(row, tag, $event);
        }
        if(Object.is('StoryNFavorites', tag)) {
            this.grid_uagridcolumn1_u0ac3876_click(row, tag, $event);
        }
    }

    /**
     * 更新默认值
     * @param {*}  row 行数据
     * @memberof MyGroupMainBase
     */
    public updateDefault(row: any){                    
    }

    /**
    * 合并分组行
    * 
    * @memberof MyGroupMainBase
    */
    public arraySpanMethod({row, column, rowIndex, columnIndex} : any) {
        let allColumns:Array<any> = ['id','pri','prodoctname','title','plan','openedby','assignedto','estimate','status','stage','modulename','uagridcolumn1'];
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
     * @memberof MyGroupMainBase
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
     * @memberof MyGroupMainBase
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
     * @memberof MyGroupMainBase
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
                pri:'',
                prodoctname:'',
                title:'',
                plan:'',
                openedby:'',
                assignedto:'',
                estimate:'',
                status:'',
                stage:'',
                modulename:'',
                ChangeStoryDetail:{
                    visible: false
                },
                ReviewStory:{
                    visible: false
                },
                CloseStory:{
                    visible: false
                },
                OpenBaseInfoEditView:{
                    visible: false
                },
                OpenCaseCreateView:{
                    visible: false
                },
                SubStory:{
                    visible: false
                },
                StoryFavorites:{
                    visible: false
                },
                StoryNFavorites:{
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
            pri:'',
            prodoctname:'',
            title:'',
            plan:'',
            openedby:'',
            assignedto:'',
            estimate:'',
            status:'',
            stage:'',
            modulename:'',
            ChangeStoryDetail:{
                visible: false
            },
            ReviewStory:{
                visible: false
            },
            CloseStory:{
                visible: false
            },
            OpenBaseInfoEditView:{
                visible: false
            },
            OpenCaseCreateView:{
                visible: false
            },
            SubStory:{
                visible: false
            },
            StoryFavorites:{
                visible: false
            },
            StoryNFavorites:{
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
     * @memberof MyGroupMainBase
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
                pri:'',
                prodoctname:'',
                title:'',
                plan:'',
                openedby:'',
                assignedto:'',
                estimate:'',
                status:'',
                stage:'',
                modulename:'',
                ChangeStoryDetail:{
                    visible: false
                },
                ReviewStory:{
                    visible: false
                },
                CloseStory:{
                    visible: false
                },
                OpenBaseInfoEditView:{
                    visible: false
                },
                OpenCaseCreateView:{
                    visible: false
                },
                SubStory:{
                    visible: false
                },
                StoryFavorites:{
                    visible: false
                },
                StoryNFavorites:{
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
     * @memberof MyGroupMainBase
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