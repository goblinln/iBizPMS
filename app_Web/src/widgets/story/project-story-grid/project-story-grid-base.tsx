import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { UIActionTool, Util, ViewTool } from '@/utils';
import { Watch, GridControlBase } from '@/studio-core';
import StoryService from '@/service/story/story-service';
import ProjectStoryService from './project-story-grid-service';
import StoryUIService from '@/uiservice/story/story-ui-service';
import { FormItemModel } from '@/model/form-detail';

/**
 * grid部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {ProjectStoryGridBase}
 */
export class ProjectStoryGridBase extends GridControlBase {
    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof ProjectStoryGridBase
     */
    protected controlType: string = 'GRID';

    /**
     * 建构部件服务对象
     *
     * @type {ProjectStoryService}
     * @memberof ProjectStoryGridBase
     */
    public service: ProjectStoryService = new ProjectStoryService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {StoryService}
     * @memberof ProjectStoryGridBase
     */
    public appEntityService: StoryService = new StoryService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof ProjectStoryGridBase
     */
    protected appDeName: string = 'story';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof ProjectStoryGridBase
     */
    protected appDeLogicName: string = '需求';

    /**
     * 界面UI服务对象
     *
     * @type {StoryUIService}
     * @memberof ProjectStoryBase
     */  
    public appUIService: StoryUIService = new StoryUIService(this.$store);

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_ucdd2824_click(params: any = {}, tag?: any, $event?: any) {
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
        curUIService.Story_Breakdowntasks(datas,contextJO, paramJO,  $event, xData,this,"Story");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u2fdcaeb_click(params: any = {}, tag?: any, $event?: any) {
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
        curUIService.Story_BatchBreakdowntasks(datas,contextJO, paramJO,  $event, xData,this,"Story");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public grid_uagridcolumn1_u247f69c_click(params: any = {}, tag?: any, $event?: any) {
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
        curUIService.Story_ProjectUnlinkStory(datas,contextJO, paramJO,  $event, xData,this,"Story");
    }


    /**
     * 界面行为模型
     *
     * @type {*}
     * @memberof ProjectStoryBase
     */  
    public ActionModel: any = {
        Breakdowntasks: { name: 'Breakdowntasks',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__STORY_FJTASK_BUT', target: 'SINGLEKEY'},
        BatchBreakdowntasks: { name: 'BatchBreakdowntasks',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__STORY_FJTASK_BUT', target: 'SINGLEKEY'},
        ProjectUnlinkStory: { name: 'ProjectUnlinkStory',disabled: false, visible: true,noprivdisplaymode:1,dataaccaction: 'SRFUR__STORY_UNLP_BUT', target: 'SINGLEKEY'}
    };

    /**
     * 本地缓存标识
     *
     * @protected
     * @type {string}
     * @memberof ProjectStoryBase
     */
    protected localStorageTag: string = 'zt_story_projectstory_grid';

    /**
     * 排序方向
     *
     * @type {string}
     * @memberof ProjectStoryGridBase
     */
    public minorSortDir: string = 'DESC';

    /**
     * 排序字段
     *
     * @type {string}
     * @memberof ProjectStoryGridBase
     */
    public minorSortPSDEF: string = 'id';

    /**
     * 所有列成员
     *
     * @type {any[]}
     * @memberof ProjectStoryGridBase
     */
    public allColumns: any[] = [
        {
            name: 'id',
            label: 'ID',
            langtag: 'entities.story.projectstory_grid.columns.id',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'pri',
            label: 'P',
            langtag: 'entities.story.projectstory_grid.columns.pri',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'title',
            label: '需求名称',
            langtag: 'entities.story.projectstory_grid.columns.title',
            show: true,
            unit: 'STAR',
            isEnableRowEdit: false,
        },
        {
            name: 'plan',
            label: '计划',
            langtag: 'entities.story.projectstory_grid.columns.plan',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'openedby',
            label: '创建',
            langtag: 'entities.story.projectstory_grid.columns.openedby',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'assignedto',
            label: '指派',
            langtag: 'entities.story.projectstory_grid.columns.assignedto',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'estimate',
            label: '预计',
            langtag: 'entities.story.projectstory_grid.columns.estimate',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'status',
            label: '状态',
            langtag: 'entities.story.projectstory_grid.columns.status',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'stage',
            label: '阶段',
            langtag: 'entities.story.projectstory_grid.columns.stage',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'uagridcolumn1',
            label: '操作',
            langtag: 'entities.story.projectstory_grid.columns.uagridcolumn1',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
    ]

    /**
     * 获取表格行模型
     *
     * @type {*}
     * @memberof ProjectStoryGridBase
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
     * @memberof ProjectStoryGridBase
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
     * @memberof ProjectStoryBase
     */
    public hasRowEdit: any = {
        'id':false,
        'pri':false,
        'title':false,
        'plan':false,
        'openedby':false,
        'assignedto':false,
        'estimate':false,
        'status':false,
        'stage':false,
        'uagridcolumn1':false,
    };

    /**
     * 获取对应列class
     *
     * @param {*} $args row 行数据，column 列数据，rowIndex 行索引，列索引
     * @returns {void}
     * @memberof ProjectStoryBase
     */
    public getCellClassName(args: {row: any, column: any, rowIndex: number, columnIndex: number}): any {
        return ( this.hasRowEdit[args.column.property] && this.actualIsOpenEdit ) ? "edit-cell" : "info-cell";
    }


    /**
     * 是否为实体导出对象
     *
     * @protected
     * @type {boolean}
     * @memberof ProjectStoryGridBase
     */
    protected isDeExport: boolean = false;

    /**
     * 所有导出列成员
     *
     * @type {any[]}
     * @memberof ProjectStoryGridBase
     */
    public allExportColumns: any[] = [
        {
            name: 'id',
            label: 'ID',
            langtag: 'entities.story.projectstory_grid.exportColumns.id',
            show: true,
        },
        {
            name: 'pri',
            label: 'P',
            langtag: 'entities.story.projectstory_grid.exportColumns.pri',
            show: true,
        },
        {
            name: 'title',
            label: '需求名称',
            langtag: 'entities.story.projectstory_grid.exportColumns.title',
            show: true,
        },
        {
            name: 'plan',
            label: '计划',
            langtag: 'entities.story.projectstory_grid.exportColumns.plan',
            show: true,
        },
        {
            name: 'openedby',
            label: '创建',
            langtag: 'entities.story.projectstory_grid.exportColumns.openedby',
            show: true,
        },
        {
            name: 'assignedto',
            label: '指派',
            langtag: 'entities.story.projectstory_grid.exportColumns.assignedto',
            show: true,
        },
        {
            name: 'estimate',
            label: '预计',
            langtag: 'entities.story.projectstory_grid.exportColumns.estimate',
            show: true,
        },
        {
            name: 'status',
            label: '状态',
            langtag: 'entities.story.projectstory_grid.exportColumns.status',
            show: true,
        },
        {
            name: 'stage',
            label: '阶段',
            langtag: 'entities.story.projectstory_grid.exportColumns.stage',
            show: true,
        },
        {
            name: 'modulename',
            label: '所属模块名称',
            langtag: 'entities.story.projectstory_grid.exportColumns.modulename',
            show: true,
        },
        {
            name: 'module',
            label: '所属模块',
            langtag: 'entities.story.projectstory_grid.exportColumns.module',
            show: true,
        },
        {
            name: 'isfavorites',
            label: '是否收藏',
            langtag: 'entities.story.projectstory_grid.exportColumns.isfavorites',
            show: true,
        },
        {
            name: 'ischild',
            label: '是否可以细分',
            langtag: 'entities.story.projectstory_grid.exportColumns.ischild',
            show: true,
        },
        {
            name: 'color',
            label: '标题颜色',
            langtag: 'entities.story.projectstory_grid.exportColumns.color',
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
     * @memberof ProjectStoryGridBase
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
                srfkey: 'UserRealName_Gird',
                codelistType : 'DYNAMIC',
                textSeparator: ',',
                renderMode: 'string',
                valueSeparator: ",",
            },
            {
                name: 'assignedto',
                srfkey: 'UserRealNameProductTeam',
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
            {
                name: 'color',
                srfkey: 'Story__color',
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
     * @memberof ProjectStoryGridBase
     */
	public uiAction(row: any, tag: any, $event: any): void {
        $event.stopPropagation();
        if(Object.is('Breakdowntasks', tag)) {
            this.grid_uagridcolumn1_ucdd2824_click(row, tag, $event);
        }
        if(Object.is('BatchBreakdowntasks', tag)) {
            this.grid_uagridcolumn1_u2fdcaeb_click(row, tag, $event);
        }
        if(Object.is('ProjectUnlinkStory', tag)) {
            this.grid_uagridcolumn1_u247f69c_click(row, tag, $event);
        }
    }

    /**
     * 表格数据加载
     *
     * @param {*} [opt={}]
     * @param {boolean} [pageReset=false]
     * @returns {void}
     * @memberof ProjectStoryGridBase
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
    * @memberof ProjectStoryBase
    */
    public addMore(){
        if(this.items.length > 0){
            this.items.forEach((item: any) => {
                if(item.hasOwnProperty('items') && item.items && item.items.length == 20){
                    const moreitem: any = {
                        children: true,
                        parent: item.id,
                        id: this.$util.createUUID(),                
                        pri: '',
                        title: '',
                        plan: '',
                        openedby: '',
                        assignedto: '',
                        estimate: '',
                        status: '',
                        stage: '',
                        Breakdowntasks:{
                            visabled: false
                        },
                        BatchBreakdowntasks:{
                            visabled: false
                        },
                        ProjectUnlinkStory:{
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
    * @memberof ProjectStoryBase
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
     * @memberof ProjectStoryGridBase
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
     * @memberof ProjectStoryGridBase
     */
    public loadMore(data: any){
    }

    /**
     * 表格数据加载
     *
     * @param {*} item
     * @returns {void}
     * @memberof ProjectStoryGridBase
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
     * @memberof ProjectStoryGridBase
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