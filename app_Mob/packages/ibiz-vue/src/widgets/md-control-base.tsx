import { Subject, Subscription } from 'rxjs';
import { CodeListServiceBase } from 'ibiz-core'
import { MainControlBase } from './main-control-base';
import { GlobalService } from 'ibiz-service';


/**
 * 多数据部件基类
 *
 * @export
 * @class MDControlBase
 * @extends {MainControlBase}
 */
export class MDControlBase extends MainControlBase {
    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof MDControlBase
     */  
    public codeListService!: CodeListServiceBase;

    /**
     * 应用状态事件
     *
     * @public
     * @type {(Subscription | undefined)}
     * @memberof MDControlBase
     */
    public appStateEvent: Subscription | undefined;

    /**
     * 选中行数据
     *
     * @type {any[]}
     * @memberof MDControlBase
     */
    public selections: any[] = [];

    /**
     * 部件行为--update
     *
     * @type {string}
     * @memberof MDControlBase
     */
    public updateAction?: string;

    /**
     * 部件行为--fetch
     *
     * @type {string}
     * @memberof MDControlBase
     */
    public fetchAction?: string;

    /**
     * 部件行为--remove
     *
     * @type {string}
     * @memberof MDControlBase
     */
    public removeAction?: string;

    /**
     * 部件行为--load
     *
     * @type {string}
     * @memberof MDControlBase
     */
    public loadAction?: string;

    /**
     * 部件行为--loaddraft
     *
     * @type {string}
     * @memberof MDControlBase
     */
    public loaddraftAction?: string;

    /**
     * 部件行为--create
     *
     * @type {string}
     * @memberof MDControlBase
     */
    public createAction?: string;

    /**
     * 当前页
     *
     * @type {number}
     * @memberof MDControlBase
     */
    public curPage: number = 1;

    /**
     * 数据
     *
     * @type {any[]}
     * @memberof MDControlBase
     */
    public items: any[] = [];

    /**
     * 是否支持分页
     *
     * @type {boolean}
     * @memberof MDControlBase
     */
    public isEnablePagingBar: boolean = true;
    
    /**
     * 是否禁用排序
     *
     * @type {boolean}
     * @memberof MDControlBase
     */
    public isNoSort: boolean = false;

    /**
     * 排序方向
     *
     * @type {string}
     * @memberof MDControlBase
     */
    public minorSortDir: string = '';

    /**
     * 排序字段
     *
     * @type {string}
     * @memberof MDControlBase
     */
    public minorSortPSDEF: string = '';

    /**
     * 分页条数
     *
     * @type {number}
     * @memberof MDControlBase
     */
    public limit: number = 20;

    /**
     * 总条数
     *
     * @type {number}
     * @memberof MDControlBase
     */
    public totalRecord: number = 0;

    /**
     * 是否单选
     *
     * @type {boolean}
     * @memberof GridControlBase
     */
    public isSingleSelect?: boolean;

    /**
     * 是否默认选中第一条数据
     *
     * @type {boolean}
     * @memberof MDControlBase
     */
    public isSelectFirstDefault: boolean = false;

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof ListControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isSingleSelect = newVal.isSingleSelect !== false;
        this.isSelectFirstDefault = newVal.isSelectFirstDefault === true;
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
     * 部件模型数据初始化
     *
     * @memberof MEditViewPanelControlBase
     */
    public async ctrlModelInit(args?:any) {
        await super.ctrlModelInit();
        if(this.controlInstance.appDataEntity && !(this.Environment?.isPreviewMode)) {
            this.appEntityService = await new GlobalService().getService(this.controlInstance.appDataEntity.codeName);
        }
        this.limit = this.controlInstance?.pagingSize ? this.controlInstance.pagingSize : 20;
        this.minorSortDir = this.controlInstance?.minorSortDir ? this.controlInstance.minorSortDir : "";
        this.minorSortPSDEF = this.controlInstance?.getMinorSortPSAppDEField?.()?.codeName?.toLowerCase() || '';
        this.loaddraftAction = this.controlInstance?.getGetDraftPSControlAction?.()?.getPSAppDEMethod?.()?.codeName || "GetDraft";
        this.loadAction = this.controlInstance?.getGetPSControlAction?.()?.getPSAppDEMethod?.()?.codeName || "Get";
        this.removeAction = this.controlInstance?.getRemovePSControlAction?.()?.getPSAppDEMethod?.()?.codeName || "Remove";
        this.updateAction = this.controlInstance?.getUpdatePSControlAction?.()?.getPSAppDEMethod?.()?.codeName || "Update";
        this.fetchAction = this.controlInstance?.getFetchPSControlAction?.()?.getPSAppDEMethod?.()?.codeName || "FetchDefault";
        this.createAction = this.controlInstance?.getCreatePSControlAction?.()?.getPSAppDEMethod?.()?.codeName || "Create";
    }

    /**
     * 多数据部件初始化
     *
     * @memberof MDControlBase
     */
    public ctrlInit(){
        super.ctrlInit();
        let _this: any = this;
        this.codeListService = new CodeListServiceBase({ $store: _this.$store });
    }

    /**
     * 执行destroyed后的逻辑
     *
     * @memberof MDControlBase
     */
    public ctrlDestroyed(){
        super.ctrlDestroyed();
        if(this.appStateEvent){
            this.appStateEvent.unsubscribe();
        }
    }

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof MDControlBase
     */
    public getDatas(): any[] {
        return this.selections;
    }

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof MDControlBase
     */
    public getData(): any {
        return this.selections[0];
    }
}
