import { MDControlBase } from "./md-control-base";
import { AppViewLogicService } from '../app-service/logic-service/app-viewlogic-service';
import { ViewTool, Verify, PanelControlInterface, PanelButtonModel, PanelTabPanelModel, PanelTabPageModel, PanelContainerModel, PanelFieldModel, PanelRawitemModel, PanelControlModel, PanelUserControlModel, LogUtil, Util } from 'ibiz-core';
import { IPSPanel, IPSPanelField, IPSPanelItem, IPSPanelItemGroupLogic, IPSPanelItemSingleLogic, IPSPanelTabPage, IPSPanelTabPanel, IPSSysPanelButton, IPSSysPanelContainer, IPSSysPanelField, IPSSysPanelTabPanel } from '@ibiz/dynamic-model-api';
import { GlobalService } from 'ibiz-service';
/**
 * 面板部件基类
 *
 * @export
 * @class PanelControlBase
 * @extends {MDControlBase}
 */
export class PanelControlBase extends MDControlBase implements PanelControlInterface{

    /**
     * 值规则对象
     *
     * @type {*}
     * @memberof PanelControlBase
     */
    public rules: any = {};

    /**
     * 面板的模型对象
     *
     * @type {*}
     * @memberof PanelControlBase
     */
    public controlInstance!: IPSPanel;

    /**
     * 代码表服务
     *
     * @type {*}
     * @memberof PanelControlBase
     */
    public codeListService: any;

    /**
     * 数据
     *
     * @type {*}
     * @memberof PanelControlBase
     */
    public data: any = {};

    /**
     * 详情模型集合
     *
     * @type {*}
     * @memberof PanelControlBase
     */
    public detailsModel: any = {};

    /**
     * 面板成员动态逻辑集合
     * 
     * @memberof PanelControlBase
     */
    public allPanelItemGroupLogic: any[] = [];

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof PanelControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        super.onDynamicPropsChange(newVal, oldVal);
    }

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof PanelControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        super.onStaticPropsChange(newVal, oldVal);
        this.transformData = newVal?.transformData || this.transformData;
        this.opendata = newVal?.opendata || this.opendata;
        this.newdata = newVal?.newdata || this.newdata;
        this.remove = newVal?.remove || this.remove;
        this.refresh = newVal?.refresh || this.refresh;
        this.dataMap = newVal?.dataMap;
        // 初始化面板详情模型集合
        this.detailsModel = {};
        this.allPanelItemGroupLogic = [];
        this.initDetailsModel(this.controlInstance.getRootPSPanelItems());
    }

    /**
     * 部件模型初始化
     *
     * @param {*} [args]
     * @memberof PanelControlBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit(args);
        if ((this.controlInstance.getRootPSPanelItems() as any)?.length > 0) {
            this.initRules(this.controlInstance.getRootPSPanelItems());
        }
        this.computedUIData();
        this.computeButtonState(this.data);
        this.panelLogic({ name: '', newVal: null, oldVal: null });
    }

    /**
     * 初始化值规则
     *
     * @param {(IPSPanelItem[] | null)} panelItems 面板项
     * @memberof PanelControlBase
     */
    public initRules(panelItems: IPSPanelItem[] | null) {
        // 初始化非空值规则和数据类型值规则
        panelItems?.forEach((item: IPSPanelItem) => {
            const panelItem = item as IPSSysPanelContainer;
            if ((panelItem?.getPSPanelItems?.() as any)?.length > 0) {
                this.initRules(panelItem.getPSPanelItems());
            } else {
                const panelItem = item as IPSSysPanelField
                if (panelItem?.getPSEditor?.()) {
                    let editorRules: any = Verify.buildVerConditions(panelItem.getPSEditor());
                    this.rules[panelItem.name] = [
                        // 非空值规则
                        { validator: (rule: any, value: any, callback: any) => { return !(this.detailsModel[panelItem.name].required && !value) }, message: `${panelItem.caption} 必须填写` },
                        // 编辑器基础值规则
                        ...editorRules
                    ]
                }
            }
        })
    }

    /**
     * 面板部件初始化
     *
     * @memberof MDControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        // 面板不需要应用全局刷新
        if (this.appStateEvent) {
            this.appStateEvent.unsubscribe();
        }
    }

    /**
     * 初始化表单成员模型
     *
     * @param {(IPSPanelItem[] | null)} panelItems 面板项
     * @memberof PanelControlBase
     */
    public initDetailsModel(panelItems: IPSPanelItem[] | null) {
        if (panelItems && panelItems?.length > 0) {
            panelItems?.forEach((panelItem: IPSPanelItem) => {
                let detailModel: any = {
                    panel: this,
                    disabled: false,
                    name: panelItem.name,
                    caption: panelItem.caption,
                    itemType: panelItem.itemType,
                    visible: true,
                };
                let model: any;
                switch (panelItem.itemType) {
                    case 'BUTTON':
                        const panelButtomItem = panelItem as IPSSysPanelButton
                        Object.assign(detailModel, {
                            uiaction: {
                                type: panelButtomItem.getPSUIAction()?.uIActionType,
                                tag: panelButtomItem.getPSUIAction()?.uIActionTag,
                                actiontarget: panelButtomItem.getPSUIAction()?.actionTarget,
                                noprivdisplaymode: panelButtomItem.getPSUIAction()?.uIActionMode,
                                dataaccaction: panelButtomItem.getPSUIAction()?.dataAccessAction,
                                visible: true,
                                disabled: false
                            }
                        });
                        model = new PanelButtonModel(detailModel);
                        break;
                    case 'TABPANEL':
                        const tabPages: IPSPanelTabPage[] = (panelItem as IPSPanelTabPanel).getPSPanelTabPages() || [];
                        const pageNames: any[] = [];
                        if (tabPages.length > 0) {
                            tabPages.forEach((page: IPSPanelTabPage) => {
                                pageNames.push({ name: page.name });
                            })
                        }
                        Object.assign(detailModel, {
                            tabPages: pageNames
                        });
                        model = new PanelTabPanelModel(detailModel);
                        break;
                    case 'TABPAGE':
                        model = new PanelTabPageModel(detailModel);
                        break;
                    case 'CONTAINER':
                        model = new PanelContainerModel(detailModel);
                        break;
                    case 'FIELD':
                        model = new PanelFieldModel(detailModel);
                        break;
                    case 'RAWITEM':
                        model = new PanelRawitemModel(detailModel);
                        break;
                    case 'CONTROL':
                        model = new PanelControlModel(detailModel);
                        break;
                    case 'USERCONTROL':
                        model = new PanelUserControlModel(detailModel);
                        break;
                }
                this.$set(this.detailsModel, panelItem.name, model);
                if ((panelItem as any).getPSPanelItems?.()?.length > 0) {
                    this.initDetailsModel((panelItem as any)?.getPSPanelItems?.());
                }
                if ((panelItem as any).getPSPanelTabPages?.()?.length > 0) {
                    (panelItem as any).getPSPanelTabPages?.().forEach((tabpage: any) => {
                        this.initDetailsModel(tabpage?.getPSPanelItems?.() || []);
                    })
                }
                if (panelItem.getPSPanelItemGroupLogics()) {
                    this.allPanelItemGroupLogic.push({ name: panelItem.name, panelItemGroupLogic: panelItem.getPSPanelItemGroupLogics() })
                }
            })
        }
    }

    /**
     * 打开编辑数据视图
     *
     * @param {any[]} args
     * @param {any[]} [fullargs]
     * @param {*} [params]
     * @param {*} [$event]
     * @param {*} [xData]
     * @memberof PanelControlBase
     */
    public opendata = (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
        this.$throw(this.$t('app.warn.unopendata'), 'opendata');
    }

    /**
     * 打开新建数据视图
     *
     * @param {any[]} args
     * @param {any[]} [fullargs]
     * @param {*} [params]
     * @param {*} [$event]
     * @param {*} [xData]
     * @memberof PanelControlBase
     */
    public newdata = (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
        this.$throw(this.$t('app.warn.unnewdata'), 'newdata');
    }

    /**
     * 删除
     *
     * @param {any[]} datas
     * @returns {Promise<any>}
     * @memberof PanelControlBase
     */
    public async remove(datas: any[]): Promise<any> {
        this.$throw(this.$t('app.warn.unremove'), 'remove');
    }

    /**
     * 刷新
     *
     * @param {*} [args]
     * @memberof PanelControlBase
     */
    public refresh(args?: any) {
        this.$throw(this.$t('app.warn.unrefresh'), 'refresh');
    }

    /**
     * 设置面板编辑项值变更
     *
     * @param data 面板数据
     * @param {{ name: string, value: any }} $event
     * @returns {void}
     * @memberof PanelControlBase
     */
    public onPanelItemValueChange(data: any, $event: { name: string; value: any }): void {
        if (!$event) {
            return;
        }
        if (!$event.name || Object.is($event.name, '') || !data.hasOwnProperty($event.name)) {
            return;
        }
        data[$event.name] = $event.value;
        this.panelEditItemChange(data, $event.name, $event.value);
    }

    /**
     * 面板编辑项值变化
     *
     * @public
     * @param data 面板数据
     * @param property 编辑项名
     * @param value 编辑项值
     * @returns {void}
     * @memberof PanelControlBase
     */
    public panelEditItemChange(data: any, property: string, value: any) {
        // 面板数据变化事件
        if (this.getDataItems().length > 0) {
            let modelitem = this.getDataItems().find((item: any) => {
                return item.name === property;
            });
            if (modelitem) {
                this.ctrlEvent({
                    controlname: this.controlInstance.controlType,
                    action: 'panelDataChange',
                    data: { [modelitem.prop]: value },
                });
            }
        }
    }

    /**
     * 分页切换事件
     *
     * @param {string} name 分页名
     * @param {*} $event 回调对象
     * @memberof PanelControlBase
     */
    public handleTabPanelClick(name: string, $event: any) {
        this.detailsModel[name]?.clickPage($event.name);
    }

    /**
     * 按钮点击事件
     * 
     * @param {string} controlName 部件名称
     * @param {*} data 数据
     * @param {*} $event 事件源
     * @memberof PanelControlBase
     */
    public buttonClick(controlName: string, data: any, $event: any) {
        AppViewLogicService.getInstance().executeViewLogic(`${controlName?.toLowerCase()}_${data.tag}_click`, $event, this, this.data, this.controlInstance.getPSAppViewLogics());
    }

    /**
     * 面板逻辑
     *
     * @public
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @memberof PanelControlBase
     */
    public panelLogic({ name, newVal, oldVal }: { name: string; newVal: any; oldVal: any }): void {
        const allPanelItemGroupLogic = this.allPanelItemGroupLogic;
        if (allPanelItemGroupLogic.length > 0) {
            allPanelItemGroupLogic.forEach((panelItem: any) => {
                panelItem.panelItemGroupLogic?.forEach((logic: any) => {
                    if (Object.is(name, '') || Object.is(name, panelItem.name)) {
                        let ret = this.verifyGroupLogic(this.data, logic);
                        switch (logic.logicCat) {
                            // 动态空输入，不满足则必填
                            case 'ITEMBLANK':
                                this.detailsModel[panelItem.name].required = !ret;
                                break;
                            // 动态启用，满足则启用
                            case 'ITEMENABLE':
                                this.detailsModel[panelItem.name].disabled = !ret;
                                break;
                            // 动态显示，满足则显示
                            case 'PANELVISIBLE':
                                this.detailsModel[panelItem.name].visible = ret;
                                break;
                        }
                    }
                })
            })
        }
    }

    /**
     * 校验动态逻辑结果
     *
     * @param {*} data 数据对象
     * @param {*} logic 逻辑对象
     * @returns
     * @memberof PanelControlBase
     */
    public verifyGroupLogic(data: any, logic: any) {
        if (logic.logicType == 'GROUP' && (logic?.getPSPanelItemLogics() as any)?.length > 0) {
            const _logic = logic as IPSPanelItemGroupLogic
            let result: boolean = true;
            if (_logic.groupOP == 'AND') {
                let falseItem: any = _logic?.getPSPanelItemLogics()?.find((childLogic: any) => {
                    return !this.verifyGroupLogic(data, childLogic);
                })
                result = falseItem == undefined;
            } else if (_logic.groupOP == 'OR') {
                let trueItem: any = _logic?.getPSPanelItemLogics()?.find((childLogic: any) => {
                    return this.verifyGroupLogic(data, childLogic);
                })
                result = trueItem != undefined;
            }
            // 是否取反
            return logic.notMode ? !result : result;
        } else if (logic.logicType == 'SINGLE') {
            const _logic = logic as IPSPanelItemSingleLogic
            return Verify.testCond(data[_logic.dstModelField.toLowerCase()], _logic.condOp, _logic.value)
        }
        return false;
    }

    /**
     * 计算UI展示数据
     *
     * @param {*} newVal
     * @memberof PanelControlBase
     */
    public async computedUIData(newVal?: any) {
        if (this.controlInstance?.getAllPSPanelFields() && this.getDataItems().length > 0) {
            this.getDataItems().forEach((item: any) => {
                this.$set(this.data, item.name, null);
            });
        }
        await this.computeLoadState();
    }

    /**
     * 计算数据加载模式
     *
     * @memberof PanelControlBase
     */
    public async computeLoadState() {
        const dataMode = this.controlInstance.dataMode;
        if (dataMode === 0) {
            //  0：不获取，使用传入数据
            if (this.navdatas && (this.navdatas.length > 0)) {
                this.data = this.navdatas[0];
            }
        } else if (dataMode === 1) {
            //  1：存在传入数据时，不获取
            if (this.navdatas && this.navdatas.length > 0) {
                if (this.navdatas && (this.navdatas.length > 0)) {
                    this.data = this.navdatas[0];
                }
            } else {
                await this.loadPanelData();
            }
        } else if (dataMode === 2) {
            //  2：始终获取
            await this.loadPanelData();
        }
    }

    /**
     * 加载数据
     *
     * @memberof PanelControlBase
     */
    public async loadPanelData() {
        const action = this.controlInstance.getGetPSControlAction?.()?.getPSAppDEMethod?.()?.codeName || '';
        if (!action) {
            LogUtil.warn(this.$t('app.viewpanel.nofconfig.getaction'));
        }
        const service = await new GlobalService().getService(this.appDeCodeName, this.context).catch((error: any) => {
            LogUtil.warn(this.$t('app.viewpanel.error.notgetservice'));
        });
        if (service && service[action] && service[action] instanceof Function) {
            try {
                const response: any = await service[action](Util.deepCopy(this.context),this.data);
                if (response && response.status == 200 && response.data) {
                    this.data = response.data;
                }
            } catch (response: any) {
                this.$throw(response, 'load');
            }
        }
    }

    /**
     * 获取name找到对应field
     *
     * @returns {any[]}
     * @memberof AppPanelModel
     */
    public findField(arr: Array<IPSPanelItem> | null, name: string): any {
        let _result = null;
        if (!arr) {
            return
        }
        for (let i = 0; i < arr.length; i++) {
            if (arr[i].name == name) return arr[i];
            if ((arr[i] as any)?.getPSPanelItems?.()) _result = this.findField((arr[i] as any)?.getPSPanelItems?.(), name)
            if (_result != null) return _result;
        }
        return _result;
    }

    /**
      * 获取数据项集合
      *
      * @returns {any[]}
      * @memberof AppPanelModel
      */
    public getDataItems(): any[] {
        let arr: any = [];
        this.controlInstance.M?.getAllPSPanelFields?.forEach((datafield: IPSPanelField) => {
            let field: any = this.findField(this.controlInstance.getRootPSPanelItems(), datafield.id);
            let obj: any = {};
            obj.name = field?.name.toLowerCase();
            obj.prop = field?.viewFieldName?.toLowerCase();
            arr.push(obj);
        });
        return arr
    }

    /**
     * 获取所有代码表
     *
     * @param {Array<any>} codelistArray 代码表模型数组
     * @param {boolean} [reverse=false]
     * @returns {Promise<any>}
     * @memberof PanelControlBase
     */
    public getAllCodeList(codelistArray: Array<any>, reverse: boolean = false): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            let codeListMap: Map<string, any> = new Map();
            let promiseArray: Array<any> = [];
            codelistArray.forEach((item: any) => {
                if (!codeListMap.get(item.tag)) {
                    promiseArray.push(this.getCodeList(item));
                    Promise.all(promiseArray).then((result: any) => {
                        if (result && result.length > 0) {
                            result.forEach((codeList: any) => {
                                let tempCodeListMap: Map<number, any> = new Map();
                                if (codeList.length > 0) {
                                    codeList.forEach((codeListItem: any) => {
                                        if (reverse) {
                                            tempCodeListMap.set(codeListItem.text, codeListItem.value);
                                        } else {
                                            tempCodeListMap.set(codeListItem.value, codeListItem.text);
                                        }
                                    });
                                }
                                codeListMap.set(item.tag, tempCodeListMap);
                            });
                            resolve(codeListMap);
                        }
                    });
                }
            });
        });
    }

    /**
     * 获取代码表
     *
     * @param codeListObject 传入代码表对象
     * @memberof PanelControlBase
     */
    public getCodeList(codeListObject: any): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            if (codeListObject.tag && Object.is(codeListObject.codelistType, 'STATIC')) {
                const codelist = this.$store.getters.getCodeList(codeListObject.tag);
                if (codelist) {
                    resolve([...JSON.parse(JSON.stringify(codelist.items))]);
                } else {
                    resolve([]);
                }
            } else if (codeListObject.tag && Object.is(codeListObject.codelistType, 'DYNAMIC')) {
                this.codeListService
                    .getItems(codeListObject.tag)
                    .then((res: any) => {
                        resolve(res);
                    })
                    .catch((error: any) => {
                        resolve([]);
                    });
            }
        });
    }

    /**
     * 计算面板按钮权限状态
     *
     * @param {*} data
     * @memberof PanelControlBase
     */
    public computeButtonState(data: any) {
        const targetData: any = this.transformData(data);
        ViewTool.calcActionItemAuthState(targetData, this.actionModel, this.appUIService);
        // 更新detailsModel里的按钮的权限状态值
        if (this.detailsModel && Object.keys(this.detailsModel).length > 0) {
            Object.keys(this.detailsModel).forEach((name: any) => {
                const model = this.detailsModel[name];
                if (model?.itemType == 'BUTTON' && model.uiaction?.tag) {
                    model.visible = this.actionModel[model.uiaction.tag].visabled;
                    model.disabled = this.actionModel[model.uiaction.tag].disabled;
                    model.isPower = this.actionModel[model.uiaction.tag].dataActionResult === 1 ? true : false;
                }
            });
        }
    }

    /**
     * 初始化界面行为模型
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public initCtrlActionModel() {
        if ((this.controlInstance.getRootPSPanelItems() as any)?.length > 0) {
            this.initItemsActionModel(this.controlInstance.getRootPSPanelItems());
        }
    }

    /**
     * 初始化面板项的界面行为模型
     *
     * @param {any[]} panelItems
     * @memberof PanelControlBase
     */
    public initItemsActionModel(panelItems: IPSPanelItem[] | null) {
        panelItems?.forEach((item: IPSPanelItem) => {
            const panelItem = item as IPSSysPanelContainer;
            const panelButtomItem = item as IPSSysPanelButton;
            if ((panelItem?.getPSPanelItems?.() as any)?.length > 0) {
                this.initItemsActionModel(panelItem.getPSPanelItems());
            } else if (panelItem?.itemType == 'BUTTON' && panelButtomItem.getPSUIAction()) {
                const appUIAction: any = panelButtomItem.getPSUIAction();
                this.actionModel[appUIAction.uIActionTag] = Object.assign(appUIAction, { disabled: false, visabled: true, getNoPrivDisplayMode: appUIAction.getNoPrivDisplayMode ? appUIAction.getNoPrivDisplayMode : 6 });
            } else if (item.itemType == 'TABPANEL') {
                const tabPages: IPSPanelTabPage[] = (item as IPSSysPanelTabPanel).getPSPanelTabPages() || [];
                tabPages.forEach((page: IPSPanelTabPage) => {
                    this.initItemsActionModel(page.getPSPanelItems());
                })
            }
        })
    }

    /**
     * 设置已经绘制完成状态
     *
     * @memberof ControlBase
     */
    public setIsMounted(name: string = 'self') { }

    /**
     * 监控数据对象
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof PanelControlBase
     */
    public onInputDataChange(newVal: any, oldVal: any) { }

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof PanelControlBase
     */
    public getDatas(): any[] {
        if (!this.data) {
            return [];
        }
        return [this.data];
    }

    /**
     * 获取单项数据
     *
     * @returns
     * @memberof PanelControlBase
     */
    public getData() {
        return this.data;
    }
}