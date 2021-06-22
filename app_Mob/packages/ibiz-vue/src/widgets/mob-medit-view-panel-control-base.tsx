import { IPSAppDEField, IPSAppDEView, IPSAppView, IPSDEMultiEditViewPanel } from '@ibiz/dynamic-model-api';
import { Util, ViewTool, ViewState, ModelTool } from 'ibiz-core';
import { Subject } from 'rxjs';
import { AppMobMEditViewPanelService } from '../ctrl-service';
import { MDControlBase } from './md-control-base';

/**
 * 多编辑面板部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {MDControlBase}
 */
export class MobMeditViewPanelControlBase extends MDControlBase {


    /**
     * 多编辑视图面板部件实例
     * 
     * @memberof MobMeditViewPanelControlBase
     */
    public controlInstance!: IPSDEMultiEditViewPanel;

    /**
     * 部件模型数据初始化
     *
     * @memberof MobMeditViewPanelControlBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit(args);
        if (!(this.Environment?.isPreviewMode)) {
            this.service = new AppMobMEditViewPanelService(this.controlInstance);
            await this.service.initServiceParam(this.controlInstance)
        }
        await this.controlInstance.getEmbeddedPSAppView()?.fill();
        this.loaddraftAction = this.controlInstance?.getGetDraftPSControlAction?.()?.getPSAppDEMethod?.()?.codeName || "GetDraft";
        await this.initParameters();
    }

    /**
     * 面板状态订阅对象
     *
     * @public
     * @type {Subject<{action: string, data: any}>}
     * @memberof MobMeditViewPanelControlBase
     */
    public panelState: Subject<ViewState> = new Subject();


    /**
     * 视图参数对象集合
     *
     * @type {any[]}
     * @memberof MobMeditViewPanelControlBase
     */
    public items: any[] = [];

    /**
     * 计数器
     *
     * @type number
     * @memberof MobMeditViewPanelControlBase
     */
    public count: number = 0;

    /**
    * 关系实体参数对象
    *
    * @public
    * @type {any[]}
    * @memberof MobMeditViewPanelControlBase
    */
    public deResParameters: any[] = [];

    /**
     * 当前应用视图参数对象
     *
     * @public
     * @type {any[]}
     * @memberof MobMeditViewPanelControlBase
     */
    public parameters: any[] = [];

    /**
     * 多编辑视图面板初始化
     *
     * @memberof MobMeditViewPanelControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (Object.is(action, 'load')) {
                    this.load(data);
                }
                if (Object.is(action, 'save')) {
                    this.saveData(data);
                }
            });
        }
    }

    /**
     * 初始化嵌入应用视图及实体参数对象
     *
     * @memberof MobMeditViewPanelControlBase
     */
    public async initParameters() {
        const emView = this.controlInstance.getEmbeddedPSAppView() as IPSAppView;
        const emViewEntity = emView?.getPSAppDataEntity();
        if (emView && emViewEntity) {
            if(!emViewEntity.isFill){
                await emViewEntity.fill();
            }
            this.deResParameters = Util.formatAppDERSPath(this.context, (emView as IPSAppDEView).getPSAppDERSPaths());
            this.parameters = [{
                pathName: Util.srfpluralize(emViewEntity.codeName).toLowerCase(),
                parameterName: emViewEntity.codeName?.toLowerCase(),
                srfmajortext: (ModelTool.getAppEntityMajorField(emViewEntity) as IPSAppDEField).codeName?.toLowerCase(),
            }];
        } else {
            this.deResParameters = [];
            this.parameters = [];
        }
    }

    /**
     * 保存数据
     *
     * @memberof MobMeditViewPanelControlBase
     */
    public saveData(data?: any) {
        this.count = 0;
        if (this.items.length > 0) {
            Object.assign(data, { showResultInfo: false });
            this.panelState.next({ tag: 'meditviewpanel', action: 'save', data: data });
        } else {
            
            this.ctrlEvent({ controlname: "meditviewpanel", action: "drdatasaved", data: { action: 'drdatasaved' } });
        }
    }

    /**
     * 处理数据
     *
     * @public
     * @param {any[]} datas
     * @memberof MobMeditViewPanelControlBase
     */
    public doItems(datas: any[]): void {
        const [{ parameterName }] = this.parameters;
        datas.forEach((arg: any) => {
            let id: string = arg[parameterName] ? arg[parameterName] : this.$util.createUUID();
            let item: any = { id: id, _context: {}, viewparam: {} };
            Object.assign(item._context, ViewTool.getIndexViewParam());
            Object.assign(item._context, this.context);

            // 关系应用实体参数
            this.deResParameters.forEach(({ pathName, parameterName }: { pathName: string, parameterName: string }) => {
                if (this.context[parameterName] && !Object.is(this.context[parameterName], '')) {
                    Object.assign(item._context, { [parameterName]: this.context[parameterName] });
                } else if (arg[parameterName] && !Object.is(arg[parameterName], '')) {
                    Object.assign(item._context, { [parameterName]: arg[parameterName] });
                }
            });

            // 当前视图参数（应用实体视图）
            this.parameters.forEach(({ pathName, parameterName, srfmajortext }: { pathName: string, parameterName: string, srfmajortext: string }) => {
                if (arg[parameterName] && !Object.is(arg[parameterName], '')) {
                    Object.assign(item._context, { [parameterName]: arg[parameterName] });
                }
                // 当前页面实体主信息
                if (arg[srfmajortext] && !Object.is(arg[srfmajortext], '')) {
                    Object.assign(item, { srfmajortext: arg[srfmajortext] });
                }
            });

            //合并视图参数
            Object.assign(item.viewparam, this.viewparams);
            this.items.push(item);
        });
    }

    /**
     * 数据加载
     *
     * @public
     * @param {*} data
     * @memberof MobMeditViewPanelControlBase
     */
    public load(data: any): void {
        if (!this.fetchAction) {
            this.$Notice.error(`${this.$t('app.viewName.meditView')}fetchAction${this.$t('app.commonWords.noAction')}`);
            return;
        }
        let arg: any = {};
        Object.assign(arg, data, { viewparams: this.viewparams });
        this.items = [];
        const promice: Promise<any> = this.service.get(this.fetchAction, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator);
        promice.then((response: any) => {
            if (!response.status || response.status !== 200) {
                if (response.errorMessage) {
                    this.$Notice.error(response.errorMessage);
                }
                return;
            }
            if (response?.data?.length > 0) {
                const items = Util.deepCopy(response.data);
                this.doItems(items);
            }
            this.ctrlEvent({ controlname: "meditviewpanel", action: "load", data: this.items });
        }).catch((response: any) => {
            if (response && response.status === 401) {
                return;
            }
            this.$Notice.error(response.errorMessage);
        });
    }

    /**
     * 增加数据
     * 
     * @memberof MobMeditViewPanelControlBase
     */
    public handleAdd() {
        if (!this.loaddraftAction) {
            this.$Notice.error(`${this.$t('app.viewName.meditView')}loaddraftAction${this.$t('app.commonWords.noAction')}`);
            return;
        }
        const promice: Promise<any> = this.service.loadDraft(this.loaddraftAction, JSON.parse(JSON.stringify(this.context)), { viewparams: this.viewparams }, this.showBusyIndicator);
        promice.then((response: any) => {
            if (!response.status || response.status !== 200) {
                if (response.errorMessage) {
                    this.$Notice.error(response.errorMessage);
                }
                return;
            }
            const data: any = response.data;
            this.doItems([data]);
        }).catch((response: any) => {
            if (response && response.status === 401) {
                return;
            }
            this.$Notice.error(response.errorMessage);
        });
    }

    /**
    * 部件抛出事件
    * @memberof MobMeditViewPanelControlBase
    */
    public viewDataChange($event: any) {
        if (!$event) {
            return
        }
        try {
            $event = JSON.parse($event);
        } catch (error) {
            return;
        }
        if (Object.is($event.action, 'save')) {
            this.count++;
            if (this.items.length === this.count) {
                this.ctrlEvent({ controlname: "meditviewpanel", action: "drdatasaved", data: { action: 'save' } });
            }
        }
    }

    /**
     * deleteItem
     */
    public deleteItem(item: any) {
        if (this.appDeCodeName) {
            let resultIndex = this.items.findIndex((value: any) => {
                return value[this.appDeCodeName.toLowerCase()] === item[this.appDeCodeName.toLowerCase()];
            });
            if (resultIndex !== -1) {
                this.items.splice(resultIndex, 1);
            }
        }
    }
}
