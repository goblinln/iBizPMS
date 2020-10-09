import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, GridControlBase } from '@/studio-core';
import ProjectService from '@/service/project/project-service';
import InvolvedProjectService from './involved-project-grid-service';
import ProjectUIService from '@/uiservice/project/project-ui-service';
import { FormItemModel } from '@/model/form-detail';


/**
 * grid部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {InvolvedProjectGridBase}
 */
export class InvolvedProjectGridBase extends GridControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof InvolvedProjectGridBase
     */
    protected controlType: string = 'GRID';

    /**
     * 建构部件服务对象
     *
     * @type {InvolvedProjectService}
     * @memberof InvolvedProjectGridBase
     */
    public service: InvolvedProjectService = new InvolvedProjectService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {ProjectService}
     * @memberof InvolvedProjectGridBase
     */
    public appEntityService: ProjectService = new ProjectService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof InvolvedProjectGridBase
     */
    protected appDeName: string = 'project';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof InvolvedProjectGridBase
     */
    protected appDeLogicName: string = '项目';

    /**
     * 界面UI服务对象
     *
     * @type {ProjectUIService}
     * @memberof InvolvedProjectBase
     */  
    public appUIService:ProjectUIService = new ProjectUIService(this.$store);

    /**
     * 界面行为模型
     *
     * @type {*}
     * @memberof InvolvedProjectBase
     */  
    public ActionModel: any = {
    };

    /**
     * 本地缓存标识
     *
     * @protected
     * @type {string}
     * @memberof InvolvedProjectBase
     */
    protected localStorageTag: string = 'zt_project_involvedproject_grid';

    /**
     * 是否支持分页
     *
     * @type {boolean}
     * @memberof InvolvedProjectGridBase
     */
    public isEnablePagingBar: boolean = false;

    /**
     * 所有列成员
     *
     * @type {any[]}
     * @memberof InvolvedProjectGridBase
     */
    public allColumns: any[] = [
        {
            name: 'name',
            label: '项目名称',
            langtag: 'entities.project.involvedproject_grid.columns.name',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'storycnt',
            label: '需求总数',
            langtag: 'entities.project.involvedproject_grid.columns.storycnt',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'ycompletetaskcnt',
            label: '已完成任务数',
            langtag: 'entities.project.involvedproject_grid.columns.ycompletetaskcnt',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
        {
            name: 'bugcnt',
            label: 'Bug总数',
            langtag: 'entities.project.involvedproject_grid.columns.bugcnt',
            show: true,
            unit: 'PX',
            isEnableRowEdit: false,
        },
    ]

    /**
     * 获取表格行模型
     *
     * @type {*}
     * @memberof InvolvedProjectGridBase
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
     * @memberof InvolvedProjectGridBase
     */
    public rules: any = {
        srfkey: [
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '项目编号 值不能为空', trigger: 'change' },
            { required: false, validator: (rule:any, value:any, callback:any) => { return (rule.required && (value === null || value === undefined || value === "")) ? false : true;}, message: '项目编号 值不能为空', trigger: 'blur' },
        ],
    }

    /**
     * 获取对应列class
     *
     * @type {*}
     * @memberof InvolvedProjectBase
     */
    public hasRowEdit: any = {
        'name':false,
        'storycnt':false,
        'ycompletetaskcnt':false,
        'bugcnt':false,
    };

    /**
     * 获取对应列class
     *
     * @param {*} $args row 行数据，column 列数据，rowIndex 行索引，列索引
     * @returns {void}
     * @memberof InvolvedProjectBase
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
     * @memberof InvolvedProjectGridBase
     */
    public async formatExcelData(filterVal: any, jsonData: any, codelistColumns?: any[]): Promise<any> {
        return super.formatExcelData(filterVal, jsonData, [
        ]);
    }



}