import { ViewTool, Verify } from 'ibiz-core';
import { MDControlBase } from "./md-control-base";
import { AppViewLogicService } from '../app-service/logic-service/app-viewlogic-service';
import { IPSPanel, IPSPanelField, IPSPanelItem, IPSPanelItemGroupLogic, IPSPanelItemSingleLogic, IPSSysPanelButton, IPSSysPanelContainer, IPSSysPanelField } from '@ibiz/dynamic-model-api';

/**
 * 面板部件基类
 *
 * @export
 * @class PanelControlBase
 * @extends {MDControlBase}
 */
export class PanelControlBase extends MDControlBase {

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
     * 面板服务对象
     *
     * @type {*}
     * @memberof PanelControlBase
     */
    public service !: any;

    /**
     * 数据
     *
     * @type {*}
     * @memberof PanelControlBase
     */
    public data: any = {};

    /**
     * 面板数据
     *
     * @type {*}
     * @memberof PanelControlBase
     */
    public panelData: any = null;

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
     * 接口实现
     *
     * @returns {any[]}
     * @memberof PanelControlBase
     */
    public getDatas(): any[] {
        if (!this.panelData) {
            return [];
        }
        return [this.panelData];
    }

    /**
     * 接口实现
     *
     * @returns
     * @memberof PanelControlBase
     */
    public getData() {
        return this.panelData;
    }

    /**
     * 面板数据对象
     *
     * @type {*}
     * @memberof PanelControlBase
     */
    public inputData?: any;

    /**
     * 部件模型初始化
     *
     * @type {*}
     * @memberof PanelControlBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit(args);
        if ((this.controlInstance.getRootPSPanelItems() as any)?.length > 0) {
            this.initRules(this.controlInstance.getRootPSPanelItems());
        }
    }

    /**
     * 初始化值规则
     *
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
     * 分页切换事件
     *
     * @memberof PanelControlBase
     */
    public handleTabPanelClick(name: string, $event: any) {
        this.detailsModel[name].clickPage($event.name);
    }

    /**
     * 按钮点击事件
     * 
     * @memberof PanelControlBase
     */
    public buttonClick(controlName: string, data: any, $event: any) {
        AppViewLogicService.getInstance().executeViewLogic(`${controlName?.toLowerCase()}_${data.tag}_click`, $event, this, undefined, this.controlInstance.getPSAppViewLogics());
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
    public async computedUIData(newVal: any) {
        if (this.controlInstance?.getAllPSPanelFields() && this.getDataItems().length > 0) {
            this.getDataItems().forEach((item: any) => {
                this.data[item.name] = newVal[item.prop];
            });
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
        return _result
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
     * 打开编辑数据视图
     *
     * @type {any}
     * @memberof PanelControlBase
     */
    public opendata = (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
        this.$throw('没有opendata');
    }

    /**
     * 打开新建数据视图
     *
     * @type {any}
     * @memberof PanelControlBase
     */
    public newdata = (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
        this.$throw('没有newdata');
    }

    /**
     * 删除
     *
     * @param {any[]} datas
     * @returns {Promise<any>}
     * @memberof PanelControlBase
     */
    public async remove(datas: any[]): Promise<any> {
        this.$throw('没有remove');
    }

    /**
     * 刷新
     *
     * @param {*} [args]
     * @memberof PanelControlBase
     */
    public refresh(args?: any) {
        this.$throw('没有refresh');
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
            const panelButtomItem = item as IPSSysPanelButton
            if ((panelItem?.getPSPanelItems?.() as any)?.length > 0) {
                this.initItemsActionModel(panelItem.getPSPanelItems());
            } else if (panelItem?.itemType == 'BUTTON' && panelButtomItem.getPSUIAction()) {
                const appUIAction: any = panelButtomItem.getPSUIAction();
                this.actionModel[appUIAction.uIActionTag] = Object.assign(appUIAction, { disabled: false, visabled: true, getNoPrivDisplayMode: appUIAction.getNoPrivDisplayMode ? appUIAction.getNoPrivDisplayMode : 6 });
            }
        })
    }

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof PanelControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        super.onDynamicPropsChange(newVal, oldVal);
        this.inputData = newVal?.inputData;
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
     * 监控数据对象
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof PanelControlBase
     */
    public onInputDataChange(newVal: any, oldVal: any) { }

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
        this.onInputDataChange(this.inputData, undefined);
    }

    /**
     * 初始化表单成员模型
     *
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
                    visible: !panelItem?.getPSPanelItemGroupLogics?.(),
                };
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
                        break;
                    case 'TABPANEL':
                        Object.assign(detailModel, {
                            tabPages: [
                                // todo
                            ]
                        })
                        break;
                }
                this.$set(this.detailsModel, panelItem.name, detailModel);
                if (Object.is(panelItem.itemType, 'CONTAINER')) {
                    this.initDetailsModel((panelItem as IPSSysPanelContainer)?.getPSPanelItems?.());
                }
                if (panelItem.getPSPanelItemGroupLogics()) {
                    this.allPanelItemGroupLogic.push({ name: panelItem.name, panelItemGroupLogic: panelItem.getPSPanelItemGroupLogics() })
                }
            })
        }
    }
}