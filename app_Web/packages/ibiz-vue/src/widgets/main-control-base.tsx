import { Subscription } from 'rxjs';
import { UIServiceRegister } from 'ibiz-service';
import { CtrlLoadingService } from '..';
import { ControlBase } from './control-base';
import { ModelTool, Util } from 'ibiz-core';
import { IPSAppDataEntity, IPSAppDEField } from '@ibiz/dynamic-model-api';

/**
 * 部件基础公共基类
 *
 * @export
 * @class MainControlBase
 * @extends {ControlBase}
 */
export class MainControlBase extends ControlBase {
    /**
     * 编辑视图
     *
     * @type {*}
     * @memberof ListControlBase
     */
    public opendata?: any;

    /**
     * 新建视图
     *
     * @type {*}
     * @memberof ListControlBase
     */
    public newdata?: any;

    /**
     * 视图loading服务
     *
     * @type {*}
     * @memberof MainControlBase
     */
    public viewLoadingService: any;

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof ListControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.newdata = newVal.newdata;
        this.opendata = newVal.opendata;
        super.onStaticPropsChange(newVal, oldVal);
        this.viewLoadingService = newVal.viewLoadingService;
    }

    /**
     * 加载服务
     *
     * @type {AppLoading}
     * @memberof MainControlBase
     */
    public ctrlLoadingService!: CtrlLoadingService;

    /**
     * 开始加载
     */
    public ctrlBeginLoading() {
        this.ctrlLoadingService.beginLoading(this.controlInstance);
    }

    /**
     * 结束加载
     */
    public ctrlEndLoading() {
        this.ctrlLoadingService.endLoading();
    }

    /**
     * 处理部件UI请求
     *
     * @memberof MainControlBase
     */
    public onControlRequset(action:string,context:any,viewparam:any){
        this.ctrlBeginLoading();
    }

    /**
     * 处理部件UI响应
     *
     * @memberof MainControlBase
     */
     public onControlResponse(action:string,response:any){
        this.ctrlEndLoading();
        if(Object.is(action,'load')){
            this.isControlLoaded = true;
        }
        if(response && response.status && response.status == 403){
            this.enableControlUIAuth = false;
            this.ctrlEvent({
                controlname: this.controlInstance.name,
                action: 'authlimit',
                data: response,
            });
        }
    }

    /**
     * 应用状态事件
     *
     * @public
     * @type {(Subscription | undefined)}
     * @memberof MainControlBase
     */
    public appStateEvent: Subscription | undefined;

    /**
     * 界面UI服务对象
     *
     * @type {*}
     * @memberof MainControlBase
     */
    public appUIService: any;

    /**
     * 界面行为模型
     *
     * @type {*}
     * @memberof MainControlBase
     */
    public actionModel: any = {};

    /**
     * 应用实体codeName
     *
     * @readonly
     * @memberof MainControlBase
     */
    get appDeCodeName() {
        return this.controlInstance?.getPSAppDataEntity?.()?.codeName || '';
    }

    /**
     * 应用实体映射实体名称
     *
     * @readonly
     * @memberof MainControlBase
     */
     get deName(){
        return this.controlInstance?.getPSAppDataEntity()?.getPSDEName() || '';
    }

    /**
     * 应用实体主键属性codeName
     *
     * @readonly
     * @memberof MainControlBase
     */
    get appDeKeyFieldName() {
        return (ModelTool.getAppEntityKeyField(this.controlInstance?.getPSAppDataEntity() as IPSAppDataEntity) as IPSAppDEField)?.codeName || '';
    }

    /**
     * 应用实体主信息属性codeName
     *
     * @readonly
     * @memberof MainControlBase
     */
    get appDeMajorFieldName() {
        return (ModelTool.getAppEntityMajorField(this.controlInstance?.getPSAppDataEntity() as IPSAppDataEntity) as IPSAppDEField)?.codeName || '';
    }

    /**
     * 部件模型数据初始化
     *
     * @memberof MainControlBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit();
        this.initCtrlActionModel();
        this.appUIService = await UIServiceRegister.getInstance().getService(this.context, this.appDeCodeName?.toLowerCase());
        if (this.appUIService) {
            await this.appUIService.loaded();
        }
    }

    /**
     * 初始化界面行为模型
     *
     * @type {*}
     * @memberof MainControlBase
     */
    public initCtrlActionModel() { }

    /**
     * 转化数据
     *
     * @param {*} args
     * @memberof  MainControlBase
     */
    public transformData(args: any) {
        let _this: any = this;
        if (!_this.appDeCodeName) {
            return;
        }
        if (_this.service && _this.service.handleRequestData instanceof Function && _this.service.handleRequestData('transform', Util.deepCopy(_this.context), args)) {
            return _this.service.handleRequestData('transform', Util.deepCopy(_this.context), args)['data'];
        }
    }

    /**
     * 部件初始化
     *
     * @param {*} [args]
     * @memberof MainControlBase
     */
    public ctrlInit(args?: any) {
        super.ctrlInit(args);
        // 构造部件加载服务
        this.ctrlLoadingService = new CtrlLoadingService(this.viewLoadingService);
    }

    /**
     * 部件销毁
     *
     * @memberof MainControlBase
     */
    public ctrlDestroyed() {
        super.ctrlDestroyed();
        if (this.appStateEvent) {
            this.appStateEvent.unsubscribe();
        }
    }


    /**
     * 部件刷新数据
     *
     * @param {*} args
     * @memberof  MainControlBase
     */
    public refresh(args?: any): void { }

    /**
     * 获取视图逻辑标识（拼合逻辑：部件标识_列标识_成员标识_click）
     *
     * @param {*} args
     * @memberof  MainControlBase
     */
    public getViewLogicTag(ctrlTag: string, columnTag: any, detailTag: string) {
        return `${ctrlTag}_${columnTag}_${detailTag}_click`;
    }
}
