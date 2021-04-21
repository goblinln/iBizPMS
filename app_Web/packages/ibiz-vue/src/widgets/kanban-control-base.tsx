import { ModelTool, Util } from 'ibiz-core';
import { MDControlBase } from './md-control-base';
import { AppViewLogicService } from '../app-service';
import { AppKanbanService } from '../ctrl-service';
import { IPSCodeList, IPSDEKanban, IPSDETBUIActionItem, IPSDEToolbar, IPSDEToolbarItem, IPSDEUIAction } from '@ibiz/dynamic-model-api';
/**
 * 看板视图部件基类
 * 
 * @export
 * @class KanbanControlBase
 * @extends {MDControlBase}
 */
export class KanbanControlBase extends MDControlBase {

    /**
     * 看板数据
     * 
     * @type {Array<any>}
     * @memberof KanbanControlBase
     */
    public items: Array<any> = [];

    /**
     * 快速行为模型数据
     *
     * @protected
     * @type {boolean}
     * @memberof KanbanControlBase
     */
    public toolbarModels: Array<any> = [];

    /**
     * 看板部件模型实例
     * 
     * @type {*}
     * @memberof KanbanControlBase
     */
    public controlInstance!: IPSDEKanban;

    /**
     * 是否默认选中第一条数据
     *
     * @type {boolean}
     * @memberof KanbanControlBase
     */
    public isSelectFirstDefault: boolean = false;

    /**
     * 显示处理提示
     *
     * @type {boolean}
     * @memberof KanbanControlBase
     */
    public showBusyIndicator: boolean = true;

    /**
     * 是否单选
     *
     * @type {boolean}
     * @memberof KanbanControlBase
     */
    public isSingleSelect?: boolean;

    /**
     * 加载的数据是否附加在items之后
     *
     * @type {boolean}
     * @memberof KanbanControlBase
     */
    public isAddBehind: boolean = false;

    /**
     * 选中数组
     * @type {Array<any>}
     * @memberof KanbanControlBase
     */
    public selections: Array<any> = [];

    /**
     * 代码表数据
     *
     * @type {Array<any>}
     * @memberof KanbanControlBase
     */
    public allCodeList: Array<any> = [];

    /**
     * 是否分组
     *
     * @type {string}
     * @memberof KanbanControlBase
     */
    public isGroup: boolean = true;

    /**
     * 分组集合
     *
     * @type {string}
     * @memberof KanbanControlBase
     */
    public groups: any[] = [];

    /**
     * 分组属性名称
     *
     * @type {string}
     * @memberof KanbanControlBase
     */
    public groupField: string = '';

    /**
     * 分组模式
     *
     * @type {string}
     * @memberof KanbanControlBase
     */
    public groupMode: string = '';

    /**
     * 分组代码表
     *
     * @type {string}
     * @memberof KanbanControlBase
     */
    public groupCodeList: any;

    /**
     * 部件行为--updateGroupAction
     *
     * @type {string}
     * @memberof KanbanControlBase
     */
    public updateGroupAction?: string;

    /**
     * this引用
     *
     * @type {number}
     * @memberof KanbanControlBase
     */
    public thisRef: any = this;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof KanbanControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        super.onDynamicPropsChange(newVal, oldVal);
    }

    /**
     * 监听部件参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof KanbanControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        super.onStaticPropsChange(newVal, oldVal)
    }

    /**
     * 部件模型数据初始化
     *
     * @memberof KanbanControlBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit();
        if (!(this.Environment && this.Environment.isPreviewMode)) {
            this.service = new AppKanbanService(this.controlInstance);
        }
        this.limit = this.controlInstance?.pagingSize ? this.controlInstance.pagingSize : 20;
        this.minorSortPSDEF = this.controlInstance.getMinorSortPSAppDEField()?.codeName?.toLowerCase() || '';
        this.minorSortDir = this.controlInstance.minorSortDir;
        this.groupField = this.controlInstance.getGroupPSAppDEField()?.codeName?.toLowerCase() || "";
        this.isGroup = this.controlInstance?.enableGroup;
        this.groupMode = this.controlInstance?.groupMode;
        this.updateGroupAction = this.controlInstance.getUpdateGroupPSControlAction()?.actionName;
        const codeList: IPSCodeList | null = this.controlInstance.getGroupPSCodeList();
        await codeList?.fill();
        this.groupCodeList = codeList ? { type: codeList.codeListType, tag: codeList.codeName, data: codeList } : {};
        this.initQuickAction();
    }

    /**
     * 部件事件
     * 
     * @param {string} controlname
     * @param {string} action
     * @param {*} data
     * @memberof KanbanControlBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if (action == 'panelDataChange') {
            this.onPanelDataChange(data.item, data.data);
        } else {
            super.onCtrlEvent(controlname, action, data);
        }
    }

    /**
     * 计算项布局面板部件所需参数
     *
     * @param {*} controlInstance
     * @param {*} item
     * @returns
     * @memberof KanbanControlBase
     */
    public computeTargetCtrlData(controlInstance: any, item?: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.dynamicProps, {
            inputData: item,
            actionModel: this.actionModel,
        })
        Object.assign(targetCtrlParam.staticProps,{
            transformData: this.transformData,
            opendata: this.opendata,
            newdata: this.newdata,
            remove: this.remove,
            refresh: this.refresh,
        })
        targetCtrlEvent['ctrl-event'] = ({ controlname, action, data }: { controlname: string, action: string, data: any }) => {
            this.onCtrlEvent(controlname, action, { item: item, data: data });
        };
        return { targetCtrlName, targetCtrlParam, targetCtrlEvent };
    }

    /**
     * 执行mounted后的逻辑
     *
     *  @memberof KanbanControlBase
     */
    public ctrlMounted() {
        super.ctrlMounted();
        if (!this.isEnablePagingBar) {
            this.$el.addEventListener('scroll', () => {
                if (this.$el.scrollTop + this.$el.clientHeight >= this.$el.scrollHeight) {
                    this.loadMore();
                }
            })
        }
    }

    /**
     * 初始化看板部件
     *
     * @memberof KanbanControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        // 绑定this
        this.transformData = this.transformData.bind(this);
        this.remove = this.remove.bind(this);
        this.refresh = this.refresh.bind(this);

        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (!Object.is(this.name, tag)) {
                    return;
                }
                if (Object.is(action, 'load')) {
                    this.refresh(data)
                }
                if (Object.is(action, 'filter')) {
                    this.refresh(data)
                }
            });
        }
    }

    /**
     * 初始化快速操作栏
     * 
     * @memberof AppDefaultKanban
     */
    public initQuickAction() {
        let targetViewToolbarItems: any[] = [];
        const kanban_quicktoolbar: IPSDEToolbar = ModelTool.findPSControlByName('kanban_quicktoolbar', this.controlInstance.getPSControls())
        if (kanban_quicktoolbar?.getPSDEToolbarItems()) {
            kanban_quicktoolbar?.getPSDEToolbarItems()?.forEach((_item: IPSDEToolbarItem | any) => {
                const item: IPSDETBUIActionItem = _item as IPSDETBUIActionItem;
                const uiAction: IPSDEUIAction = item.getPSUIAction() as IPSDEUIAction;
                targetViewToolbarItems.push({ name: item.name, showCaption: item.showCaption, showIcon: item.showIcon, tooltip: item.tooltip, iconcls: item.getPSSysImage()?.cssClass, icon: item.getPSSysImage()?.imagePath, actiontarget: uiAction.actionTarget, caption: item.caption, disabled: false, itemType: item.itemType, visabled: true, noprivdisplaymode: item.noPrivDisplayMode, dataaccaction: '', uiaction: {} });
            })
        }
        this.toolbarModels = targetViewToolbarItems;
    }

    /**
     * 界面工具栏点击
     * 
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * @param $event 事件源对象
     * @memberof AppDefaultKanban
     */
    public handleItemClick(data: any, $event: any) {
        AppViewLogicService.getInstance().executeViewLogic(this.getViewLogicTag(this.controlInstance.name, 'quicktoolbar', data.tag), $event, this, undefined, this.controlInstance?.getPSAppViewLogics() as any);
    }

    /**
     * 界面行为
     *
     * @param {*} detail 界面行为
     * @param {*} $event   
     * @param {*} group 看板分组
     * @memberof AppDefaultKanban
     */
    public uiActionClick(detail: any, $event: any, group: any) {
        let row = this.selections.length > 0 && group.items.includes(this.selections[0]) ? this.selections[0] : {};
        if (!row.hasOwnProperty('srfgroup')) {
            Object.assign(row, { srfgroup: group.value });
        }
        AppViewLogicService.getInstance().executeViewLogic(this.getViewLogicTag(this.controlInstance.name, 'group', detail.name), $event, this, row, this.controlInstance?.getPSAppViewLogics() as any);
    }

    /**
     * 加载更多
     *
     * @memberof KanbanControlBase
     */
    public loadMore() {
        if (this.totalRecord > this.items.length) {
            this.curPage = ++this.curPage;
            this.isAddBehind = true;
            this.load({});
        }
    }

    /**
     * 刷新
     *
     * @param {*} [opt={}]
     * @memberof KanbanControlBase
     */
    public refresh(args?: any) {
        this.curPage = 1;
        this.load(args, true);
    }

    /**
     * 执行destroyed后的逻辑
     *
     * @memberof KanbanControlBase
     */
    public afterDestroy() {
        if (this.viewStateEvent) {
            this.viewStateEvent.unsubscribe();
        }
    }

    /**
     * 看板数据加载
     *
     * @public
     * @param {*} [arg={}]
     * @param {boolean} [isReset=false] 是否重置items
     * @memberof KanbanControlBase
     */
    public async load(opt: any = {}, isReset: boolean = false) {
        if (!this.fetchAction) {
            this.$Notice.error({ title: "警告", desc: '实体看板视图没有配置fetchAction' });
            return;
        }
        const arg: any = { ...opt };
        const page: any = {};
        if (this.isEnablePagingBar) {
            Object.assign(page, { page: this.curPage - 1, size: this.limit });
        }
        // 设置排序
        if (!Object.is(this.minorSortDir, '') && !Object.is(this.minorSortPSDEF, '')) {
            const sort: string = this.minorSortPSDEF + "," + this.minorSortDir;
            Object.assign(page, { sort: sort });
        }
        Object.assign(arg, page);
        const parentdata: any = {};
        this.$emit("ctrl-event", { controlname: "kanban", action: "beforeload", data: parentdata });
        Object.assign(arg, parentdata);
        let tempViewParams: any = parentdata.viewparams ? parentdata.viewparams : {};
        Object.assign(tempViewParams, JSON.parse(JSON.stringify(this.viewparams)));
        Object.assign(arg, { viewparams: tempViewParams });
        this.ctrlBeginLoading();
        const post: Promise<any> = this.service.search(this.fetchAction, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator);
        post.then((response: any) => {
            this.ctrlEndLoading();
            if (!response || response.status !== 200) {
                if (response.data && response.data.message) {
                    this.$Notice.error({ title: "警告", desc: response.data.message });
                }
                return;
            }
            const data: any = response.data;
            if (!this.isAddBehind) {
                this.items = [];
            }
            if (Object.keys(data).length > 0) {
                let datas = JSON.parse(JSON.stringify(data));
                datas.map((item: any) => {
                    Object.assign(item, { isselected: false });
                });
                this.totalRecord = response.total;
                if (isReset) {
                    this.items = datas;
                } else {
                    this.items.push(...datas);
                }
            }
            this.isAddBehind = false;
            this.setGroups();
            this.$emit("ctrl-event", { controlname: "kanban", action: "load", data: this.items });
            if (this.isSelectFirstDefault) {
                this.handleClick(this.items[0]);
            }
        }, (response: any) => {
            this.ctrlEndLoading();
            if (response && response.status === 401) {
                return;
            }
            this.$Notice.error({ title: "警告", desc: response.data && response.data.message ? response.data.message : "" });
        });
    }

    /**
     * 面板数据变化处理事件
     * @param {any} item 当前列数据
     * @param {any} $event 面板事件数据
     *
     * @memberof KanbanControlBase
     */
    public onPanelDataChange(item: any, $event: any) {
        Object.assign(item, $event, { rowDataState: 'update' });
    }

    /**
     * 拖拽变化
     *
     * @param {*} evt
     * @param {*} name
     * @memberof KanbanControlBase
     */
    public onDragChange(evt: any, name: string) {
        if (evt?.added?.element) {
            let item: any = JSON.parse(JSON.stringify(evt.added.element))
            item[this.groupField] = name;
            this.updateData(item)
        }
    }

    /**
     * 删除
     *
     * @param {any[]} datas
     * @returns {Promise<any>}
     * @memberof KanbanControlBase
     */
    public async remove(datas: any[]): Promise<any> {
        if (!this.removeAction) {
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: '${view.getName()}' + (this.$t('app.kanban.notConfig.removeAction') as string) });
            return;
        }
        let _datas: any[] = [];
        datas.forEach((record: any, index: number) => {
            if (Object.is(record.srfuf, '0')) {
                this.items.some((val: any, num: number) => {
                    if (JSON.stringify(val) == JSON.stringify(record)) {
                        this.items.splice(num, 1);
                        return true;
                    }
                });
            } else {
                _datas.push(datas[index]);
            }
        });
        if (_datas.length === 0) {
            return;
        }
        let dataInfo = '';
        _datas.forEach((record: any, index: number) => {
            let srfmajortext = record.srfmajortext;
            if (index < 5) {
                if (!Object.is(dataInfo, '')) {
                    dataInfo += '、';
                }
                dataInfo += srfmajortext;
            } else {
                return false;
            }
        });

        if (_datas.length < 5) {
            dataInfo = dataInfo + ' 共' + _datas.length + '条数据';
        } else {
            dataInfo = dataInfo + '...' + ' 共' + _datas.length + '条数据';
        }

        const removeData = () => {
            let keys: any[] = [];
            _datas.forEach((data: any) => {
                keys.push(data.srfkey);
            });
            let _removeAction = keys.length > 1 ? 'removeBatch' : this.removeAction;
            const context: any = JSON.parse(JSON.stringify(this.context));
            const post: Promise<any> = this.service.delete(_removeAction, Object.assign(context, { [(this.controlInstance?.getPSAppDataEntity as any)?.codeName?.toLowerCase()]: keys.join(';') }), Object.assign({ [(this.controlInstance.getPSAppDataEntity as any).codeName.toLowerCase()]: keys.join(';') }, { viewparams: this.viewparams }), this.showBusyIndicator);
            return new Promise((resolve: any, reject: any) => {
                this.ctrlBeginLoading();
                post.then((response: any) => {
                    this.ctrlEndLoading();
                    if (!response || response.status !== 200) {
                        this.$Notice.error({ title: '', desc: (this.$t('app.commonWords.delDataFail') as string) + ',' + response.info });
                        return;
                    } else {
                        this.$Notice.success({ title: '', desc: (this.$t('app.commonWords.deleteSuccess') as string) });
                    }
                    //删除items中已删除的项
                    _datas.forEach((data: any) => {
                        this.items.some((item: any, index: number) => {
                            if (Object.is(item.srfkey, data.srfkey)) {
                                this.items.splice(index, 1);
                                return true;
                            }
                        });
                    });
                    this.$emit("ctrl-event", { controlname: "kanban", action: "remove", data: null });
                    this.selections = [];
                    resolve(response);
                }).catch((response: any) => {
                    this.ctrlEndLoading();
                    if (response && response.status === 401) {
                        return;
                    }
                    if (!response || !response.status || !response.data) {
                        this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.sysException') as string) });
                        reject(response);
                        return;
                    }
                    reject(response);
                });
            });
        }

        dataInfo = dataInfo.replace(/[null]/g, '').replace(/[undefined]/g, '').replace(/[ ]/g, '');
        this.$Modal.confirm({
            title: (this.$t('app.commonWords.warning') as string),
            content: (this.$t('app.kanban.delete1') as string) + dataInfo + '，' + (this.$t('app.kanban.delete2') as string),
            onOk: () => {
                removeData();
            },
            onCancel: () => { }
        });
        return removeData;
    }

    /**
     * 设置分组集合
     *
     * @param {*}
     * @memberof KanbanControlBase
     */
    public updateData(opt: any) {
        const arg: any = { ...opt };
        Object.assign(arg, { viewparams: this.viewparams });
        let _context = JSON.parse(JSON.stringify(this.context));
        if (this.controlInstance.getPSAppDataEntity()?.codeName) {
            Object.assign(_context, { [(this.controlInstance.getPSAppDataEntity()?.codeName?.toLowerCase() as string)]: opt.srfkey });
        }
        const post: Promise<any> = this.service.update(this.updateGroupAction, _context, arg, this.showBusyIndicator);
        this.ctrlBeginLoading();
        post.then((response: any) => {
            this.ctrlEndLoading();
            if (!response.status || response.status !== 200) {
                if (response.data) {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message });
                }
                this.setGroups();
                return;
            }
            let item = this.items.find((item: any) => Object.is(item.srfkey, response.data.srfkey));
            Object.assign(item, response.data);
            this.setGroups();
            this.$emit("ctrl-event", { controlname: "kanban", action: "update", data: this.items });
        }).catch((response: any) => {
            this.ctrlEndLoading();
            if (response && response.status && response.data) {
                this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message });
                this.refresh();
                return;
            }
            if (!response || !response.status || !response.data) {
                this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.sysException') as string) });
                this.refresh();
                return;
            }
        });
    }

    /**
     * 设置分组集合
     *
     * @param {}
     * @memberof KanbanControlBase
     */
    public async setGroups() {
        let tempGroups: Array<any> = this.groups;
        if (!this.isGroup || !this.groupField || Object.is(this.groupMode, 'NONE')) {
            return;
        }
        if (Object.is(this.groupMode, 'AUTO')) {
            this.groups = [];
            this.items.forEach(item => {
                let group: any = this.groups.find((group: any) => Object.is(group.name, item[this.groupField]));
                let state: any = tempGroups.filter((temp: any) => Object.is(item[this.groupField], temp.value))[0];
                if (!group) {
                    this.groups.push({
                        name: item[this.groupField],
                        value: item[this.groupField],
                        folding: (state && !state.folding) ? state.folding : true,
                        items: this.getGroupItems(item[this.groupField])
                    })
                }
            });
        }
        if (Object.is(this.groupMode, 'CODELIST') && this.groupCodeList) {
            this.groups = [];
            let codelistItems: any = await this.codeListService.getDataItems(this.groupCodeList);
            this.allCodeList = Util.deepCopy(codelistItems);
            if (codelistItems && codelistItems.length > 0) {
                codelistItems.forEach((item: any) => {
                    let state: any = tempGroups.filter((temp: any) => Object.is(item.value, temp.value))[0];
                    this.groups.push({
                        name: item.value,
                        value: item.value,
                        color: item.color,
                        folding: (state && !state.folding) ? state.folding : true,
                        items: this.getGroupItems(item.value)
                    })
                })
            }
        }
    }

    /**
     * 设置分组集合
     *
     * @param {string} name
     * @memberof KanbanControlBase
     */
    public getGroupItems(name: string) {
        let datas: any = [];
        this.items.forEach(item => {
            if (Object.is(item[this.groupField], name)) {
                datas.push(item);
            }
        })
        return datas;
    }

    /**
     * 设置分组集合
     *
     * @param {string} name
     * @memberof KanbanControlBase
     */
    public getGroupText(name: string) {
        if (Object.is(this.groupMode, 'CODELIST') && this.groupCodeList) {
            if (this.allCodeList && this.allCodeList.length > 0) {
                if (!name) {
                    return '未定义';
                }
                let item = this.allCodeList.find((item: any) => Object.is(item.value, name));
                if (item) {
                    return item.text;
                }
            }
        } else {
            return name;
        }
    }

    /**
     * 选择数据
     * @memberof KanbanControlBase
     *
     */
    public handleClick(args: any) {
        args.isselected = !args.isselected;
        this.items.forEach((item: any) => {
            if (item.srfkey !== args.srfkey) {
                item.isselected = false;
            }
        })
        this.selectchange();
    }

    /**
     * 双击数据
     * @memberof KanbanControlBase
     *
     */
    public handleDblClick(args: any) {
        args.isselected = true;
        this.items.forEach((item: any) => {
            if (item.srfkey !== args.srfkey) {
                item.isselected = false;
            }
        })
        this.$emit("ctrl-event", { controlname: "kanban", action: "rowdblclick", data: args });
    }

    /**
     * 触发事件
     * @memberof KanbanControlBase
     *
     */
    public selectchange() {
        this.selections = [];
        this.items.map((item: any) => {
            if (item.isselected) {
                this.selections.push(item);
            }
        });
        this.$emit("ctrl-event", { controlname: "kanban", action: "selectionchange", data: this.selections });
    }

    /**
     * 点击时触发看板的展开和收起
     * 
     * @param group 分组看板
     * @param index 分组看板编号
     * @memberof KanbanControlBase
     */
    public onClick(group: any, index: number) {
        group.folding = !group.folding;
        this.$forceUpdate();
    }
}
