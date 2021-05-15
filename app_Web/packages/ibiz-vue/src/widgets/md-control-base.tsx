import { Subscription } from 'rxjs';
import { CodeListServiceBase, ModelTool } from 'ibiz-core'
import { MainControlBase } from './main-control-base';
import { GlobalService } from 'ibiz-service';
import { AppCenterService, AppViewLogicService } from '../app-service';
import { IPSAppDataEntity, IPSDETBUIActionItem, IPSDEToolbar, IPSDEToolbarItem, IPSDEUIAction, IPSMDControl } from '@ibiz/dynamic-model-api';


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
     * 多数据部件实例
     *
     * @public
     * @type {(IPSMDControl)}
     * @memberof MDControlBase
     */
    public controlInstance!: any;

    /**
     * 快速工具栏实例
     *
     * @type {IPSDEToolbar}
     * @memberof MDControlBase
     */
    public quickToolbarInstance!: IPSDEToolbar;
    
    /**
     * 批处理工具栏实例
     *
     * @type {IPSDEToolbar}
     * @memberof MDControlBase
     */
    public batchToolbarInstance!: IPSDEToolbar;

    /**
     * 快速行为模型数据
     *
     * @protected
     * @type {[]}
     * @memberof DataViewControlBase
     */
     public quickToolbarModels: Array<any> = [];

     /**
      * 批操作行为模型数据
      *
      * @protected
      * @type {[]}
      * @memberof DataViewControlBase
      */
     public batchToolbarModels: Array<any> = [];

    /**
     * 选中行数据
     *
     * @type {any[]}
     * @memberof MDControlBase
     */
    public selections: any[] = [];

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
     * 排序方向
     *
     * @type {string}
     * @memberof MDControlBase
     */
    public minorSortDir: string = "";


    /**
     * 排序字段
     *
     * @type {string}
     * @memberof MDControlBase
     */
    public minorSortPSDEF: string = "";

    /**
     * 建立数据行为
     *
     * @readonly
     * @memberof MDControlBase
     */
    public createAction: string = "";
    /**
     * 查询数据行为
     *
     * @readonly
     * @memberof MDControlBase
     */
    public fetchAction: string = "";

    /** 
     * 更新数据行为
     *
     * @readonly
     * @memberof MDControlBase
     */
    public updateAction: string = "";

    /**
     * 删除数据行为
     *
     * @readonly
     * @memberof MDControlBase
     */
    public removeAction: string = "";

    /**
     * 查询数据行为
     *
     * @readonly
     * @memberof MDControlBase
     */
    public loadAction: string = "";

    /**
     * 获取草稿数据行为
     *
     * @readonly
     * @memberof MDControlBase
     */
    public loaddraftAction: string = "";

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
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit();
        const { name } = this.controlInstance;
        if (this.controlInstance?.getPSAppDataEntity?.() && !(this.Environment && this.Environment.isPreviewMode)) {
            if(!this.controlInstance?.getPSAppDataEntity()?.isFill){
                await this.controlInstance?.getPSAppDataEntity().fill();
            }
            this.appEntityService = await new GlobalService().getService((this.controlInstance.getPSAppDataEntity() as IPSAppDataEntity).codeName);
        }
        this.loaddraftAction = this.controlInstance?.getGetDraftPSControlAction?.()?.getPSAppDEMethod?.()?.codeName || "GetDraft";
        this.loadAction = this.controlInstance?.getGetPSControlAction?.()?.getPSAppDEMethod?.()?.codeName || "Get";
        this.removeAction = this.controlInstance?.getRemovePSControlAction?.()?.getPSAppDEMethod?.()?.codeName || "Remove";
        this.updateAction = this.controlInstance?.getUpdatePSControlAction?.()?.getPSAppDEMethod?.()?.codeName || "Update";
        this.fetchAction = this.controlInstance?.getFetchPSControlAction?.()?.getPSAppDEMethod?.()?.codeName || "FetchDefault";
        this.createAction = this.controlInstance?.getCreatePSControlAction?.()?.getPSAppDEMethod?.()?.codeName || "Create";
        if(this.controlInstance?.getPSControls?.()){
            this.quickToolbarInstance = ModelTool.findPSControlByName(`${name}_quicktoolbar`, this.controlInstance.getPSControls())
            this.batchToolbarInstance = ModelTool.findPSControlByName(`${name}_batchtoolbar`, this.controlInstance.getPSControls())
        }
        this.initToolBarModels();
    }

    /**
     * 多数据部件初始化
     *
     * @memberof MDControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        let _this: any = this;
        // 全局刷新通知
        if (AppCenterService.getMessageCenter()) {
            _this.appStateEvent = AppCenterService.getMessageCenter().subscribe(({ name, action, data }: { name: string, action: string, data: any }) => {
                if (!_this.appDeCodeName || !Object.is(name, _this.appDeCodeName)) {
                    return;
                }
                if (Object.is(action, 'appRefresh')) {
                    _this.refresh(data);
                }
            })
        }
        _this.codeListService = new CodeListServiceBase({ $store: _this.$store });
    }

    /**
     * 执行destroyed后的逻辑
     *
     * @memberof MDControlBase
     */
    public ctrlDestroyed() {
        super.ctrlDestroyed();
        if (this.appStateEvent) {
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
    
    /**
     * 初始化工具栏模型
     *
     * @memberof DataViewControlBase
     */
     public initToolBarModels() {
        const getModelData = (_item: IPSDEToolbarItem) => {
            const item: IPSDETBUIActionItem = _item as IPSDETBUIActionItem;
            const uiAction: IPSDEUIAction | null = item.getPSUIAction ? (item?.getPSUIAction() as IPSDEUIAction) : null;
            return {
                name: item.name,
                showCaption: item.showCaption,
                showIcon: item.showIcon,
                tooltip: item.tooltip,
                iconcls: item.getPSSysImage()?.cssClass,
                icon: item.getPSSysImage()?.imagePath,
                actiontarget: item.uIActionTarget,
                caption: item.caption,
                disabled: false,
                itemType: item.itemType,
                visabled: true,
                noprivdisplaymode: uiAction?.noPrivDisplayMode,
                dataaccaction: '',
                uiaction: {
                    tag: uiAction?.uIActionTag ? uiAction.uIActionTag : uiAction?.id ? uiAction.id : '',
                    target: uiAction?.actionTarget,
                },
            };
        };
        if (this.quickToolbarInstance) {
            let targetViewToolbarItems: any[] = [];
            this.quickToolbarInstance.getPSDEToolbarItems()?.forEach((item: IPSDEToolbarItem) => {
                targetViewToolbarItems.push(getModelData(item));
            });
            this.quickToolbarModels = targetViewToolbarItems;
        }
        if (this.batchToolbarInstance) {
            let targetViewToolbarItems: any[] = [];
            this.batchToolbarInstance.getPSDEToolbarItems()?.forEach((item: IPSDEToolbarItem) => {
                targetViewToolbarItems.push(getModelData(item));
            });
            this.batchToolbarModels = targetViewToolbarItems;
        }
    }

    /**
     * 绘制无数据提示信息
     *
     * @return {*} 
     * @memberof MDControlBase
     */
    public renderEmptyDataTip(){
        return <div class="empty-data-tip">
            <img class="tip-img" src="./assets/img/empty-data.svg" />
            <span class="tip-text">未找到相关数据</span>
            {this.renderQuickToolbar()}
        </div>
    }

    /**
     * 绘制快速工具栏
     *
     * @return {*} 
     * @memberof MDControlBase
     */
    public renderQuickToolbar(): any{
        return <span class='quick-toolbar'>
            <view-toolbar
                toolbarModels={this.quickToolbarModels}
                on-item-click={(data: any, $event: any) => {
                    this.handleItemClick('quicktoolbar', data, $event);
                }}
            ></view-toolbar>
        </span>
    }

    /**
     * 获取视图样式
     *
     * @readonly
     * @memberof GridControlBase
     */
    get viewStyle(){
        return (this.controlInstance?.getParentPSModelObject?.() as any)?.viewStyle || 'DEFAULT'
    }

    /**
     * 绘制批处理工具栏
     *
     * @return {*} 
     * @memberof MDControlBase
     */
    public renderBatchToolbar(): any{
        for (let index = 0; index < this.batchToolbarModels.length; index++) {
            this.batchToolbarModels[index].disabled = this.selections.length <= 0;
        }
        return <span v-show={this.selections.length > 0 || this.viewStyle == 'DEFAULT'} class="batch-toolbar">
            <view-toolbar
                toolbarModels={this.batchToolbarModels}
                on-item-click={(data: any, $event: any) => {
                    this.handleItemClick('batchtoolbar', data, $event);
                }}
            ></view-toolbar>
        </span>
    }

    /**
     * 部件工具栏点击
     *
     * @param ctrl 部件
     * @param data 工具栏回传数据
     * @param $event 事件源对象
     * @memberof MDControlBase
     */
     public handleItemClick(ctrl: string, data: any, $event: any) {
        AppViewLogicService.getInstance().executeViewLogic(
            this.getViewLogicTag(this.controlInstance.name, ctrl, data.tag),
            $event,
            this,
            undefined,
            this.controlInstance.getPSAppViewLogics() as Array<any>,
        );
    }
}
