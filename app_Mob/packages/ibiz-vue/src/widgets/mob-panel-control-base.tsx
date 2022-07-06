import { ViewTool, Verify, Util, MobPanelControlInterface, LogUtil, PanelButtonModel, PanelTabPanelModel, PanelTabPageModel, PanelContainerModel, PanelFieldModel, PanelRawitemModel, PanelControlModel, PanelUserControlModel } from 'ibiz-core';
import { MDControlBase } from "./md-control-base";
import { AppViewLogicService } from '../app-service/logic-service/app-viewlogic-service';
import { IPSPanel, IPSPanelField, IPSPanelItem, IPSPanelItemGroupLogic, IPSPanelItemSingleLogic, IPSPanelTabPage, IPSPanelTabPanel, IPSSysPanelButton, IPSSysPanelContainer, IPSSysPanelField, IPSSysPanelTabPanel } from '@ibiz/dynamic-model-api';
import { GlobalService } from 'ibiz-service';

/**
 * 面板部件基类
 *
 * @export
 * @class MobPanelControlBase
 * @extends {MDControlBase}
 */
export class MobPanelControlBase extends MDControlBase implements MobPanelControlInterface{

    /**
     * 值规则对象
     *
     * @type {*}
     * @memberof MobPanelControlBase
     */
    public rules: any = {};

    /**
     * 面板的模型对象
     *
     * @type {*}
     * @memberof MobPanelControlBase
     */
    public controlInstance!: IPSPanel;

    /**
     * 面板服务对象
     *
     * @type {*}
     * @memberof MobPanelControlBase
     */
    public service !: any;

    /**
     * 数据
     *
     * @type {*}
     * @memberof MobPanelControlBase
     */
    public data: any = {};

    /**
     * 详情模型集合
     *
     * @type {*}
     * @memberof MobPanelControlBase
     */
    public detailsModel: any = {};

    /**
     * 面板成员动态逻辑集合
     * 
     * @memberof MobPanelControlBase
     */
    public allPanelItemGroupLogic: any[] = [];

    /**
     * 是否需要查找属性
     * 
     * @type {boolean}
     * @memberof MobPanelControlBase
     */
    public needFindDEField: boolean = false;

    /**
     * 父容器数据项
     * 
     * @type {any[]}
     * @memberof MobPanelControlBase
     */
    public parentDataItems: any[] = [];

    /**
    * 默认加载
    *
    * @type {boolean}
    * @memberof MobPanelControlBase
    */
    public isLoadDefault!: boolean;    

    /**
     * 接口实现
     *
     * @returns {any[]}
     * @memberof MobPanelControlBase
     */
    public getDatas(): any[] {
        if (!this.data) {
            return [];
        }
        return [this.data];
    }

    /**
     * 接口实现
     *
     * @returns
     * @memberof MobPanelControlBase
     */
    public getData() {
        return this.data;
    }

    /**
     * 部件模型初始化
     *
     * @type {*}
     * @memberof MobPanelControlBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit(args);
        if ((this.controlInstance.getRootPSPanelItems() as any)?.length > 0) {
            this.initRules(this.controlInstance.getRootPSPanelItems());
        }
        this.computedUIData();
        this.computeButtonState(this.data);
        this.panelLogic({ name: '', newVal: null, oldVal: null });
        this.computeParentDataItems();        
    }

    /**
     * 初始化值规则
     *
     * @memberof MobPanelControlBase
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
                        { validator: (rule: any, value: any, callback: any) => { return !(this.detailsModel[panelItem.name].required && !value) }, message: `${panelItem.caption} ${this.$t('app.form.rules.required')}` },
                        // 编辑器基础值规则
                        ...editorRules
                    ]
                }
            }
        })
    }

    /**
     * 分页切换事件
     *
     * @memberof MobPanelControlBase
     */
    public handleTabPanelClick(name: string, $event: any) {
        this.detailsModel[name].clickPage($event.name);
    }

    /**
     * 按钮点击事件
     * 
     * @memberof MobPanelControlBase
     */
    public buttonClick(controlName: string, data: any, $event: any) {
        AppViewLogicService.getInstance().executeViewLogic(`${controlName?.toLowerCase()}_${data.tag}_click`, $event, this, data, this.controlInstance.getPSAppViewLogics());
    }

    /**
     * 面板逻辑
     *
     * @public
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @memberof MobPanelControlBase
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
     * @memberof MobPanelControlBase
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
     * @memberof MobPanelControlBase
     */
    public async computedUIData(newVal?: any) {
        if (this.controlInstance?.getAllPSPanelFields() && this.getDataItems().length > 0) {
            this.getDataItems().forEach((item: any) => {
                // this.data[item.name] = newVal[item.prop];
                this.$set(this.data, item.name, null);
            });
        }
        const dataMode = this.controlInstance.dataMode;
        if (dataMode === 3) {
            this.viewCtx.appGlobal[this.controlInstance.M.dataName] = this.data;
        } else if (dataMode === 4) {
            this.viewCtx.routeViewGlobal[this.controlInstance.M.dataName] = this.data;
        } else if (dataMode === 5) {
            this.viewCtx.viewGlobal[this.controlInstance.M.dataName] = this.data;
        } else {
            if (this.isLoadDefault) {
                await this.computeLoadState(dataMode);
            }
        }        
    }

    /**
     * 计算数据加载模式
     *
     * @memberof PanelControlBase
     */
    public async computeLoadState(dataMode: number, args?: any) {
        if (dataMode === 0) {
            //  0：不获取，使用传入数据
            if (this.navdatas && (this.navdatas.length > 0)) {
                this.data = this.navdatas[0];
                this.fillPanelData(this.data);            
            }
        } else if (dataMode === 1) {
            //  1：存在传入数据时，不获取
            if (this.navdatas && this.navdatas.length > 0) {
                if (this.navdatas && (this.navdatas.length > 0)) {
                    this.data = this.navdatas[0];
                }
            } else {
                await this.loadPanelData(args);
            }
        } else if (dataMode === 2) {
            //  2：始终获取
            await this.loadPanelData(args);
        }
    }

    /**
     * 加载数据
     *
     * @memberof PanelControlBase
     */
    public async loadPanelData(args?: any) {
        const action = this.controlInstance.getGetPSControlAction?.()?.getPSAppDEMethod?.()?.codeName || '';
        if (!action) {
            LogUtil.warn(this.$t('app.viewpanel.nofconfig.getaction'));
        }
        const service = await new GlobalService().getService(this.appDeCodeName, this.context).catch((error: any) => {
            LogUtil.warn(this.$t('app.viewpanel.error.notgetservice'));
        });
        if (args && Object.keys(args).length > 0) {
            Object.assign(this.data, args);
        }
        if (service && service[action] && service[action] instanceof Function) {
            try {
                const response: any = await service[action](Util.deepCopy(this.context), this.data);
                if (response && response.status == 200 && response.data) {
                    this.fillPanelData(response.data);
                }
            } catch (response: any) {
                // this.$throw(response, 'load');
            }
        }
    }

    /**
      * 获取数据项集合
      *
      * @returns {any[]}
      * @memberof AppPanelModel
      */
    public getDataItems(): any[] {
        let arr: any = [];
        this.controlInstance.M?.getAllPSPanelFields?.forEach((datafield: any) => {
            let obj: any = {};
            obj.name = datafield?.id?.toLowerCase();
            obj.prop = datafield?.viewFieldName?.toLowerCase();
            arr.push(obj);
        });
        return arr;
    }

    /**
     * 填充面板数据
     *
     * @param {*} data
     * @memberof PanelControlBase
     */
    public fillPanelData(data: any) {
        this.getDataItems().forEach((item: any) => {
            if (item?.prop) {
                this.data[item.name] = data?.[item.prop];
            } else {
                this.data[item.name] = data?.[item.name];
            }
        });
    }    

    /**
     * 获取所有代码表
     *
     * @param {Array<any>} codelistArray 代码表模型数组
     * @param {boolean} [reverse=false]
     * @returns {Promise<any>}
     * @memberof MobPanelControlBase
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
     * @memberof MobPanelControlBase
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
     * 打开编辑数据视图
     *
     * @type {any}
     * @memberof MobPanelControlBase
     */
    public opendata = (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
        this.$Notice.error(this.$t('app.error.unopendata'));
    }

    /**
     * 打开新建数据视图
     *
     * @type {any}
     * @memberof MobPanelControlBase
     */
    public newdata = (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
        this.$Notice.error(this.$t('app.error.unnewdata'));
    }

    /**
     * 删除
     *
     * @param {any[]} datas
     * @returns {Promise<any>}
     * @memberof MobPanelControlBase
     */
    public async remove(datas: any[]): Promise<any> {
        this.$Notice.error(this.$t('app.error.unremove'));
    }

    /**
     * 刷新
     *
     * @param {*} [args]
     * @memberof MobPanelControlBase
     */
    public async refresh(args?: any) {
        const dataMode = this.controlInstance.dataMode;
        if ((dataMode !== 3) && (dataMode !== 4) && (dataMode !== 5)) {
            await this.computeLoadState(dataMode, args);
        }
    }

    /**
     * 设置面板编辑项值变更
     *
     * @param data 面板数据
     * @param {{ name: string, value: any }} $event
     * @returns {void}
     * @memberof MobPanelControlBase
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
     * @memberof MobPanelControlBase
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
     * 计算面板按钮权限状态
     *
     * @param {*} data
     * @memberof MobPanelControlBase
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
     * @memberof MobPanelControlBase
     */
    public initItemsActionModel(panelItems: IPSPanelItem[] | null) {
        panelItems?.forEach((item: IPSPanelItem) => {
            const panelItem = item as IPSSysPanelContainer;
            const panelButtomItem = item as IPSSysPanelButton
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
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof MobPanelControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        super.onDynamicPropsChange(newVal, oldVal);
        if (this.controlIsLoaded && newVal?.navdatas?.[0] != oldVal?.navdatas?.[0]) {
            this.computedUIData();
        }

    }

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof MobPanelControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        super.onStaticPropsChange(newVal, oldVal);
        this.transformData = newVal?.transformData || this.transformData;
        this.opendata = newVal?.opendata || this.opendata;
        this.newdata = newVal?.newdata || this.newdata;
        this.remove = newVal?.remove || this.remove;
        this.refresh = newVal?.refresh || this.refresh;
        this.isLoadDefault = newVal?.isLoadDefault;        
        // 初始化面板详情模型集合
        this.detailsModel = {};
        this.allPanelItemGroupLogic = [];
        this.initDetailsModel(this.controlInstance.getRootPSPanelItems());
    }


    /**
     * 设置已经绘制完成状态
     *
     * @memberof ControlBase
     */
    public setIsMounted(name: string = 'self') {

    }

    /**
     * 面板部件初始化
     *
     * @memberof MDControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(async ({ tag, action, data }: { tag: string, action: string, data: any }) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                if (Object.is('load', action)) {
                    const dataMode = this.controlInstance.dataMode;
                    if ((dataMode !== 3) && (dataMode !== 4) && (dataMode !== 5)) {
                        await this.computeLoadState(dataMode, data);
                    }
                }
            });
        }        
        // 面板不需要应用全局刷新
        if (this.appStateEvent) {
            this.appStateEvent.unsubscribe();
        }
    }

    /**
     * 初始化表单成员模型
     *
     * @memberof MobPanelControlBase
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
                    visible: !panelItem?.getPSPanelItemGroupLogics?.(),
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
                if (Object.is(panelItem.itemType, 'CONTAINER')) {
                    this.initDetailsModel((panelItem as IPSSysPanelContainer)?.getPSPanelItems?.());
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
     * 计算父容器数据项
     *
     * @param {*} [args]
     * @memberof PanelControlBase
     */
    public computeParentDataItems() {
        const parent: any = this.controlInstance.getParentPSModelObject?.();
        if (
            parent.controlType &&
            (parent.controlType == 'MOBMDCTRL')
        ) {
            this.needFindDEField = true;
        }
        if (!this.needFindDEField) {
            return;
        }
        switch (parent.controlType) {
            case 'MOBMDCTRL':
                this.parentDataItems = parent.getPSDEListDataItems?.();
                break;
        }
    }

    /**
     * 面板属性项查找对应父容器实体实体属性项
     *
     * @returns
     * @memberof PanelControlBase
     */
    public findDEFieldForPanelField(target: any) {
        const parent: any = this.controlInstance.getParentPSModelObject();
        const entity = parent.getPSAppDataEntity?.();
        if (entity && this.parentDataItems.length > 0) {
            const valueItemName = (this.controlInstance.getAllPSPanelFields()?.find((item: any) => Object.is(item.id.toLowerCase(), target.name.toLowerCase())) as any)?.viewFieldName;
            const dataItem = this.parentDataItems.find((item: any) => Object.is(valueItemName?.toLowerCase(), item.name?.toLowerCase()));
            if (dataItem) {
                Object.assign(target, {
                    getPSAppDEField: () => {
                        return dataItem.getPSAppDEField?.();
                    }
                })
            }

        }
    }    
}