import { Provide } from 'vue-property-decorator';
import { LogUtil, TreeControlInterface, Util, ViewTool } from 'ibiz-core';
import { AppTreeService } from '../ctrl-service';
import { MDControlBase } from './md-control-base';
import { GlobalService, UIServiceRegister } from 'ibiz-service';
import { AppViewLogicService } from '../app-service';
import { IPSDETree, IPSDETreeNode, IPSDEToolbarItem, IPSDECMUIActionItem, IPSDEUIAction, IPSDETBUIActionItem } from '@ibiz/dynamic-model-api';

/**
 * 树视图部件基类
 *
 * @export
 * @class TreeControlBase
 * @extends {MDControlBase}
 */
export class TreeControlBase extends MDControlBase implements TreeControlInterface {
    /**
     * 部件模型实例对象
     *
     * @type {*}
     * @memberof TreeControlBase
     */
    public controlInstance!: IPSDETree;

    /**
     * 初始化完成
     *
     * @type {boolean}
     * @memberof TreeControlBase
     */
    public inited: boolean = false;

    /**
     * 枝干节点是否可用（具有数据能力，可抛出）
     *
     * @type {boolean}
     * @memberof TreeControlBase
     */
    public isBranchAvailable: boolean = true;

    /**
     * 是否开启树拖拽节点功能
     * 
     * @type {boolean}
     * @memberof TreeControlBase
     */
    public draggable: boolean = true;

    /**
     * 已选中数据集合
     *
     * @type {*}
     * @memberof TreeControlBase
     */
    public selectedNodes: any = [];

    /**
     * 选中数据字符串
     *
     * @type {string}
     * @memberof TreeControlBase
     */
    public selectedData!: string;

    /**
     * 当前选中数据项
     *
     * @type {*}
     * @memberof TreeControlBase
     */
    public currentselectedNode: any = {};

    /**
     * 拖拽中的节点的完整树数据
     *
     * @type {*}
     * @memberof TreeControlBase
     */
    public draggingNode: any;

    /**
     * 缓存拖拽节点界面行为的结果集
     * 
     * @type {*}
     * @memberof TreeControlBase
     */
    public cacheDragNodeMap: Map<string, string> = new Map();

    /**
     * 数据展开主键
     *
     * @type {string[]}
     * @memberof TreeControlBase
     */
    @Provide()
    public expandedKeys: string[] = [];

    /**
     * 回显选中数据集合
     *
     * @type {*}
     * @memberof TreeControlBase
     */
    public echoselectedNodes: any[] = [];

    /**
     * 过滤属性
     *
     * @type {string}
     * @memberof TreeControlBase
     */
    public srfnodefilter: string = '';

    /**
     * 备份树节点上下文菜单
     *
     * @type any
     * @memberof TreeControlBase
     */
    public copyActionModel: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof TreeControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        super.onDynamicPropsChange(newVal, oldVal);
        if (newVal?.selectedData && newVal.selectedData != oldVal?.selectedData) {
            this.selectedData = newVal.selectedData;
            this.onSelectedDataValueChange(newVal.selectedData)
        }
    }

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof TreeControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isBranchAvailable = newVal?.isBranchAvailable !== false;
        this.isSingleSelect = newVal.isSingleSelect
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
     * 部件模型数据初始化
     *
     * @memberof TreeControlBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit();
        this.service = new AppTreeService(this.controlInstance, this.context);
        this.initActionModel();
    }

    /**
     * 初始化树节点上下文菜单集合
     * 
     * @memberof TreeControlBase
     */
    public initActionModel() {
        const allTreeNodes = this.controlInstance.getPSDETreeNodes() || [];
        let tempModel: any = {};
        if (allTreeNodes?.length > 0) {
            allTreeNodes.forEach((item: IPSDETreeNode) => {
                if (item?.getPSDEContextMenu()) {
                    let toobarItems: any = item.getPSDEContextMenu()?.getPSDEToolbarItems();
                    if (toobarItems.length > 0) {
                        toobarItems.forEach((toolbarItem: IPSDEToolbarItem) => {
                            this.initActionModelItem(toolbarItem, item, tempModel)
                        })
                    }
                }
            })
        }
        this.actionModel = {};
        Object.assign(this.actionModel, tempModel);
    }

    /**
     * 初始化上下菜单项
     * 
     * @param toolbarItem 工具栏菜单模型
     * @param item 节点模型
     * @param tempModel 界面行为模型对象
     * @memberof TreeControlBase
     */
    public initActionModelItem(toolbarItem: IPSDEToolbarItem, item: IPSDETreeNode, tempModel: any) {
        let tempItem: any = {
            name: toolbarItem.name,
            ctrlname: item.getPSDEContextMenu()?.name,
            nodeOwner: item.nodeType
        }
        if (toolbarItem.itemType == 'DEUIACTION') {
            const uiAction: IPSDEUIAction = (toolbarItem as IPSDECMUIActionItem).getPSUIAction() as IPSDEUIAction;
            if (uiAction) {
                tempItem.type = uiAction.uIActionType;
                tempItem.tag = uiAction.uIActionTag;
                tempItem.visabled = true;
                tempItem.disabled = false;
                if (uiAction?.actionTarget && uiAction?.actionTarget != "") {
                    tempItem.actiontarget = uiAction.actionTarget;
                }
                if (uiAction.noPrivDisplayMode) {
                    tempItem.noprivdisplaymode = uiAction.noPrivDisplayMode;
                }
                if (uiAction.dataAccessAction) {
                    tempItem.dataaccaction = uiAction.dataAccessAction;
                }
            }
        }
        tempItem.imgclass = toolbarItem.showIcon && toolbarItem.getPSSysImage() ? toolbarItem.getPSSysImage()?.cssClass : '';
        tempItem.caption = toolbarItem.showCaption ? toolbarItem.caption : '';
        tempItem.title = toolbarItem.tooltip;
        tempModel[`${item.nodeType}_${toolbarItem.name}`] = tempItem;
        const toolbarItems = (toolbarItem as IPSDETBUIActionItem)?.getPSDEToolbarItems() || [];
        if (toolbarItems?.length > 0) {
            for (let toolBarChild of toolbarItems) {
                this.initActionModelItem(toolBarChild, item, tempModel)
            }
        }
    }

    /**
     * 树视图部件初始化
     *
     * @memberof TreeControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                if (Object.is('load', action)) {
                    this.inited = false;
                    this.$nextTick(() => {
                        this.inited = true;
                    });
                }
                if (Object.is('filter', action)) {
                    this.srfnodefilter = data.srfnodefilter;
                    this.refresh_all();
                }
                if (Object.is('refresh_parent', action)) {
                    this.refresh_parent();
                }
                if (Object.is('refresh_current', action)) {
                    this.refresh_current();
                }
            });
        }
    }

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof TreeControlBase
     */
    public getDatas(): any[] {
        return this.selectedNodes.map((item: any) => {
            return item.curData;
        });
    }

    /**
     * 获取单项数据
     *
     * @returns {*}
     * @memberof TreeControlBase
     */
    public getData(): any {
        return this.selectedNodes?.[0]?.curData;
    }

    /**
     * 数据加载
     *
     * @param {*} [node={}] 节点数据
     * @param {*} [resolve] 渲染树节点回调
     * @return {*} 
     * @memberof TreeControlBase
     */
    public load(node: any = {}, resolve?: any) {
        if (node.data && node.data.children) {
            resolve(node.data.children);
            return;
        }
        const params: any = {
            srfnodeid: node.data && node.data.id ? node.data.id : '#',
            srfnodefilter: this.srfnodefilter,
        };
        let tempViewParams: any = Util.deepCopy(this.viewparams);
        let curNode: any = {};
        curNode = Util.deepObjectMerge(curNode, node);
        let tempContext: any = this.computecurNodeContext(curNode);
        if (curNode.data && curNode.data.srfparentdename) {
            Object.assign(tempContext, { srfparentdename: curNode.data.srfparentdename });
            Object.assign(tempViewParams, { srfparentdename: curNode.data.srfparentdename });
        }
        if (curNode.data && curNode.data.srfparentdemapname) {
            Object.assign(tempContext, { srfparentdemapname: curNode.data.srfparentdemapname });
            Object.assign(tempViewParams, { srfparentdemapname: curNode.data.srfparentdemapname });
        }
        if (curNode.data && curNode.data.srfparentkey) {
            Object.assign(tempContext, { srfparentkey: curNode.data.srfparentkey });
            Object.assign(tempViewParams, { srfparentkey: curNode.data.srfparentkey });
        }
        Object.assign(params, { viewparams: tempViewParams });
        this.onControlRequset('load', tempContext, params);
        this.service
            .getNodes(tempContext, params)
            .then((response: any) => {
                this.onControlResponse('load', response);
                if (!response || response.status !== 200) {
                    this.$throw(response.info, 'load');
                    resolve([]);
                    return;
                }
                const _items = response.data;
                this.formatExpanded(_items);
                resolve([..._items]);
                let isRoot = Object.is(node.level, 0);
                let isSelectedAll = node.checked;
                this.setDefaultSelection(_items, isRoot, isSelectedAll);
                this.ctrlEvent({
                    controlname: this.name,
                    action: 'load',
                    data: _items.filter((item: any) => item.enablecheck)
                });
            })
            .catch((response: any) => {
                this.onControlResponse('load', response);
                resolve([]);
                this.$throw(response, 'load');
            });
    }

    /**
     * 节点复选框选中事件
     *
     * @public
     * @param {*} data 当前节点对应传入对象
     * @param {*} checkedState 树目前选中状态对象
     * @memberof TreeControlBase
     */
    public onCheck(data: any, checkedState: any) {
        // 处理多选数据
        if (!this.isSingleSelect) {
            // let leafNodes = checkedState.checkedNodes.filter((item: any) => item.leaf);
            const checkedNodes: any[] = Util.deepCopy(checkedState.checkedNodes);
            this.selectedNodes = checkedNodes.filter((checkedNode: any) => checkedNode.enablecheck);
            this.ctrlEvent({
                controlname: this.name,
                action: 'selectionchange',
                data: this.selectedNodes,
            });
        }
    }

    /**
     * 当前选中节点变更事件
     *
     * @public
     * @param {*} data 节点对应传入对象
     * @param {*} node 节点对应node对象
     * @memberof TreeControlBase
     */
    public selectionChange(data: any, node: any) {
        // 禁用项处理
        if (data.disabled) {
            node.isCurrent = false;
            return;
        }
        // 只处理最底层子节点
        if (this.isBranchAvailable || data.leaf) {
            //  修改之前节点的选中状态值
            if (this.currentselectedNode && Object.keys(this.currentselectedNode).length > 0) {
                this.currentselectedNode.srfchecked = 0;
            }
            //  添加选中状态值
            data.srfchecked = 1;
            this.currentselectedNode = data;
            // 单选直接替换
            if (this.isSingleSelect) {
                this.selectedNodes = [this.currentselectedNode];
                this.ctrlEvent({
                    controlname: this.name,
                    action: 'selectionchange',
                    data: this.selectedNodes,
                });
            }
            // 多选用check方法
        }
    }

    /**
     * 刷新
     *
     * @param {*} [args] 额外参数
     * @memberof TreeControlBase
     */
    public refresh(args?: any): void {
        this.refresh_all();
    }

    /**
     * 刷新整个树
     *
     * @memberof TreeControlBase
     */
    public refresh_all(): void {
        this.inited = false;
        this.$nextTick(() => {
            this.inited = true;
        });
    }

    /**
     * 刷新节点
     *
     * @public
     * @param {*} [curContext] 当前节点上下文
     * @param {*} [arg={}] 当前节点附加参数
     * @param {boolean} parentnode 是否是刷新父节点
     * @memberof TreeControlBase
     */
    public refresh_node(curContext: any, arg: any = {}, parentnode?: boolean): void {
        const { srfnodeid: id } = arg;
        Object.assign(arg, { viewparams: this.viewparams });
        let tempContext: any = Util.deepCopy(curContext);
        this.onControlRequset('refresh_node', tempContext, arg);
        const get: Promise<any> = this.service.getNodes(tempContext, arg);
        get.then((response: any) => {
            this.onControlResponse('refresh_node', response);
            if (!response || response.status !== 200) {
                this.$throw(response.info, 'refresh_node');
                return;
            }
            const _items = [...response.data];
            this.formatExpanded(_items);
            const tree: any = this.$refs[this.name];
            tree.updateKeyChildren(id, _items);
            if (parentnode) {
                this.currentselectedNode = {};
            }
            this.$forceUpdate();
            this.setDefaultSelection(_items);
        }).catch((response: any) => {
            this.onControlResponse('refresh_node', response);
            this.$throw(response, 'refresh_node');
        });
    }

    /**
     * 刷新当前节点
     *
     * @memberof TreeControlBase
     */
    public refresh_current(): void {
        if (Object.keys(this.currentselectedNode).length === 0) {
            return;
        }
        const tree: any = this.$refs[this.name];
        const node: any = tree.getNode(this.currentselectedNode.id);
        if (!node || !node.parent) {
            return;
        }
        let curNode: any = {};
        curNode = Util.deepObjectMerge(curNode, node);
        let tempContext: any = {};
        if (curNode.data && curNode.data.srfappctx) {
            Object.assign(tempContext, curNode.data.srfappctx);
        } else {
            Object.assign(tempContext, this.context);
        }
        const id: string = node.key ? node.key : '#';
        const param: any = { srfnodeid: id };
        this.refresh_node(tempContext, param, false);
    }

    /**
     * 刷新当前节点的父节点
     *
     * @memberof TreeControlBase
     */
    public refresh_parent(): void {
        if (Object.keys(this.currentselectedNode).length === 0) {
            return;
        }
        const tree: any = this.$refs[this.name];
        const node: any = tree.getNode(this.currentselectedNode.id);
        if (!node || !node.parent) {
            return;
        }
        let curNode: any = {};
        const { parent: _parent } = node;
        curNode = Util.deepObjectMerge(curNode, _parent);
        let tempContext: any = {};
        if (curNode.data && curNode.data.srfappctx) {
            Object.assign(tempContext, curNode.data.srfappctx);
        } else {
            Object.assign(tempContext, this.context);
        }
        const id: string = _parent.key ? _parent.key : '#';
        const param: any = { srfnodeid: id };
        this.refresh_node(tempContext, param, true);
    }

    /**
     * 执行默认界面行为(树节点双击事件)
     *
     * @param {*} node 节点数据
     * @memberof TreeControlBase
     */
    public doDefaultAction(node: any) {
        // todo 默认界面行为

        this.ctrlEvent({
            controlname: this.name,
            action: 'nodedblclick',
            data: this.selectedNodes,
        });
    }

    /**
     * 显示上下文菜单事件
     *
     * @param data 节点数据
     * @param event 事件源
     * @memberof TreeControlBase
     */
    public showContext(data: any, event: any) {
        let _this: any = this;
        this.copyActionModel = {};
        const tags: string[] = data.id.split(';');
        Object.values(this.actionModel).forEach((item: any) => {
            if (Object.is(item.nodeOwner, tags[0])) {
                this.copyActionModel[item.name] = item;
            }
        });
        if (Object.keys(this.copyActionModel).length === 0) {
            return;
        }
        this.computeNodeState(data, data.nodeType, data.appEntityName).then((result: any) => {
            let flag: boolean = false;
            if (Object.values(result).length > 0) {
                flag = Object.values(result).some((item: any) => {
                    return item.visabled === true;
                });
            }
            if (flag) {
                (_this.$refs[data.id] as any).showContextMenu(event.clientX, event.clientY);
            }
        });
    }

    /**
     * 计算节点右键权限
     *
     * @param {*} node 节点数据
     * @param {*} nodeType 节点类型
     * @param {*} appEntityName 应用实体名称
     * @returns
     * @memberof TreeControlBase
     */
    public async computeNodeState(node: any, nodeType: string, appEntityName: string) {
        if (Object.is(nodeType, 'STATIC')) {
            return this.copyActionModel;
        }
        let service: any = await new GlobalService().getService(appEntityName, this.context);
        if (this.copyActionModel && Object.keys(this.copyActionModel).length > 0) {
            if (service['Get'] && service['Get'] instanceof Function) {
                let tempContext: any = Util.deepCopy(this.context);
                tempContext[appEntityName.toLowerCase()] = node.srfkey;
                let targetData = await service.Get(tempContext, {}, false);
                const nodeUIService = await UIServiceRegister.getInstance().getService(this.context, appEntityName.toLowerCase());
                if (nodeUIService) {
                    await nodeUIService.loaded();
                }
                ViewTool.calcTreeActionItemAuthState(targetData.data, this.copyActionModel, nodeUIService);
                return this.copyActionModel;
            } else {
                LogUtil.warn(this.$t('app.warn.geterror'));
                return this.copyActionModel;
            }
        }
    }

    /**
     * 部件事件
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * 
     * @memberof TreeControlBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any, selectedNode?: any) {
        if (action == 'contextMenuItemClick') {
            AppViewLogicService.getInstance().executeViewLogic(`${controlname}_${data}_click`, undefined, this, selectedNode.curData, this.controlInstance?.getPSAppViewLogics() || []);
        } else {
            this.ctrlEvent({ controlname, action, data });
        }
    }

    /**
     * 自定义树节点筛选操作逻辑
     *
     * @param {*} value 过滤值
     * @param {*} data 节点值
     * @return {*} 
     * @memberof TreeControlBase
     */
    public filterNode(value: any, data: any) {
        if (!value) return true;
        return data.text.indexOf(value) !== -1;
    }

    /**
     * 计算当前节点的上下文
     *
     * @param {*} curNode 当前节点
     * @memberof TreeControlBase
     */
    public computecurNodeContext(curNode: any) {
        let tempContext: any = {};
        if (curNode && curNode.data && curNode.data.srfappctx) {
            tempContext = Util.deepCopy(curNode.data.srfappctx);
        } else {
            tempContext = Util.deepCopy(this.context);
        }
        return tempContext;
    }

    /**
     * 设置默认展开节点
     *
     * @public
     * @param {any[]} items 节点集合
     * @returns {any[]}
     * @memberof TreeControlBase
     */
    public formatExpanded(items: any[]): any[] {
        const data: any[] = [];
        items.forEach(item => {
            if (item.expanded || (item.children && item.children.length > 0)) {
                this.expandedKeys.push(item.id);
            }
        });
        return data;
    }

    /**
     * 设置默认选中,回显数项，选中所有子节点
     *
     * @param {any[]} items 当前节点所有子节点集合
     * @param {boolean} isRoot 是否是加载根节点
     * @param {boolean} isSelectedAll 是否选中所有子节点
     * @memberof TreeControlBase
     */
    public setDefaultSelection(items: any[], isRoot: boolean = false, isSelectedAll: boolean = false): void {
        if (items.length == 0) {
            return;
        }
        let defaultData: any;
        //在导航视图中，如已有选中数据，则右侧展开已选中数据的视图，如无选中数据则默认选中第一条
        if (this.isSelectFirstDefault) {
            if (this.isSingleSelect) {
                let index: number = -1;
                if (this.selectedNodes && this.selectedNodes.length > 0) {
                    this.selectedNodes.forEach((select: any) => {
                        index = items.findIndex((item: any) => {
                            if (Util.isEmpty(item.srfkey)) {
                                return select.id == item.id;
                            } else {
                                return select.srfkey == item.srfkey;
                            }
                        });
                    });
                }
                if (index === -1) {
                    if (isRoot) {
                        if (this.viewparams && this.viewparams.srfnavtag && items.length > 0) {
                            const activate = this.viewparams.srfnavtag;
                            index = items.findIndex((item: any) => {
                                return item.id && item.id.split(';') && (item.id.split(';')[0] == activate);
                            });
                            if (index === -1) index = 0;
                        } else {
                            index = 0;
                        }
                    } else {
                        return;
                    }
                }
                defaultData = items[index];
                this.setTreeNodeHighLight(defaultData);
                this.currentselectedNode = Util.deepCopy(defaultData);
                if (this.isBranchAvailable || defaultData.leaf) {
                    this.selectedNodes = [this.currentselectedNode];
                    this.ctrlEvent({
                        controlname: this.name,
                        action: 'selectionchange',
                        data: this.selectedNodes,
                    });
                }
            }
        }
        // 已选数据的回显
        if (this.echoselectedNodes && this.echoselectedNodes.length > 0) {
            let checkedNodes = items.filter((item: any) => {
                return this.echoselectedNodes.some((val: any) => {
                    if (Object.is(item.srfkey, val.srfkey) && Object.is(item.srfmajortext, val.srfmajortext)) {
                        val.used = true;
                        this.selectedNodes.push(val);
                        this.ctrlEvent({
                            controlname: this.name,
                            action: 'selectionchange',
                            data: this.selectedNodes,
                        });
                        return true;
                    }
                });
            });
            if (checkedNodes.length > 0) {
                this.echoselectedNodes = this.echoselectedNodes.filter((item: any) => !item.used);
                // 父节点选中时，不需要执行这段，会选中所有子节点
                if (!isSelectedAll) {
                    if (this.isSingleSelect) {
                        this.setTreeNodeHighLight(checkedNodes[0]);
                        this.currentselectedNode = Util.deepCopy(checkedNodes[0]);
                        this.selectedNodes = [this.currentselectedNode];
                    } else {
                        this.selectedNodes = this.selectedNodes.concat(checkedNodes);
                        const tree: any = this.$refs[this.name];
                        tree.setCheckedNodes(this.selectedNodes);
                    }
                }
            }
        }
        // 父节点选中时，选中所有子节点
        if (isSelectedAll) {
            let leafNodes = items.filter((item: any) => item.leaf);
            this.selectedNodes = this.selectedNodes.concat(leafNodes);
            this.ctrlEvent({
                controlname: this.name,
                action: 'selectionchange',
                data: this.selectedNodes,
            });
        }
    }

    /**
     * 设置选中高亮
     *
     * @param {*} data 节点数据
     * @memberof TreeControlBase
     */
    public setTreeNodeHighLight(data: any): void {
        const tree: any = this.$refs[this.name];
        tree.setCurrentKey(data.id);
    }

    /**
     * selectedData选中值变化
     *
     * @param {*} newVal
     * @memberof TreeControlBase
     */
    public onSelectedDataValueChange(newVal: any) {
        this.echoselectedNodes = newVal ? this.isSingleSelect ? [JSON.parse(newVal)[0]] : JSON.parse(newVal) : [];
        this.selectedNodes = [];
        if (this.controlIsLoaded && this.echoselectedNodes.length > 0) {
            const { name } = this.controlInstance;
            let AllnodesObj = (this.$refs[name] as any).store.nodesMap;
            let AllnodesArray: any[] = [];
            for (const key in AllnodesObj) {
                if (AllnodesObj.hasOwnProperty(key)) {
                    AllnodesArray.push(AllnodesObj[key].data);
                }
            }
            this.setDefaultSelection(AllnodesArray);
        }
    }

    /**
     * 节点能否被拖拽
     * 
     * @param node 拖拽节点
     * @returns 要拖拽的节点能否被拖拽
     */
    public allowDrag(node: any) {
        return new Promise((resolve: any, reject: any) => {
            if (node.data?.allowDrag || node.data?.allowOrder) {
                this.draggingNode = Util.deepCopy(node);
                resolve(true);
            } else {
                resolve(false);
            }
        });
    }

    /**
     * 能否放入目标节点
     * 
     * @param draggingNode 正在拖拽的节点
     * @param dropNode 目标节点
     * @param type 'prev'|'inner'|'next' 拖拽到相对目标节点的位置（前，插入，后）
     * @returns 拖拽的节点能否放置到目标节点
     * @memberof TreeControlBase
     */
    public allowDrop(draggingNode: any, dropNode: any, type: string) {
        return new Promise((resolve: any, reject: any) => {
            if ((dropNode.data?.allowDrop || dropNode.data?.allowOrder) && !Object.is('inner', type)) {
                if (this.ctrlTriggerLogicMap.get('calcnodeallowdrop')) {
                    const draggingNodeData = draggingNode.data;
                    const dropNodeData = dropNode.data;
                    if (this.cacheDragNodeMap && this.cacheDragNodeMap.get(draggingNode.data?.id + dropNode.data?.id)) {
                        const isAllowDrop = this.cacheDragNodeMap.get(draggingNode.data.id + dropNode.data.id) == 'true' ? true : false;
                        return resolve(isAllowDrop);
                    }
                    let arg: any = { draggingNode: draggingNodeData.curData, dropNode: dropNodeData.curData };
                    this.ctrlTriggerLogicMap.get('calcnodeallowdrop').executeUILogic({
                        context: dropNodeData.srfappctx,
                        viewparams: this.viewparams,
                        utils: this.viewCtx,
                        data: [arg],
                        event: null,
                        xData: this,
                        actionContext: this,
                        srfParentDeName: dropNodeData.srfparentdename,
                        arg: Object.assign(arg, { data: [arg] })
                    })
                    if (arg && arg.srfret) {
                        if (dropNode.data?.allowDrop) {
                            this.cacheDragNodeMap.set(draggingNode.data.id + dropNode.data.id, 'true');
                            resolve(true);
                        } else if (dropNode.data?.allowOrder && Object.is(this.draggingNode.parent?.id, dropNode.parent?.id)) {
                            this.cacheDragNodeMap.set(draggingNode.data.id + dropNode.data.id, 'true');
                            resolve(true);
                        } else {
                            this.cacheDragNodeMap.set(draggingNode.data.id + dropNode.data.id, 'false');
                            resolve(false);
                        }
                    } else {
                        this.cacheDragNodeMap.set(draggingNode.data.id + dropNode.data.id, 'false');
                        resolve(false);
                    }
                } else {
                    if (dropNode.data?.allowDrop) {
                        resolve(true);
                    } else if (dropNode.data?.allowOrder && Object.is(this.draggingNode.parent?.id, dropNode.parent?.id)) {
                        resolve(true);
                    } else {
                        resolve(false);
                    }
                }
            } else {
                resolve(false);
            }
        });
    }

    /**
     * 树节点值变化
     * 
     * @param value 变化值
     * @param node 节点数据
     * @param event 额外参数
     * @memberof TreeControlBase   
     */
    public nodeValueChange(value: string, node: any, event: any) {
        this.$set(node.data, 'text', value);
        this.$set(node.data, 'srfmajortext', value);
        if (node.data.curData && node.data.nodeTextField) {
            this.$set(node.data.curData, node.data.nodeTextField, value);
        }
    }

    /**
     * 保存并刷新
     * 
     * @param node 节点
     * @param event 额外参数
     * @memberof TreeControlBase
     */
    public async saveAndRefresh(node: any, event: any) {
        const nodeData = node.data;
        if (Object.is(nodeData.nodeType, 'STATIC')) {
            return
        }
        let tempContext: any = this.computecurNodeContext(node);
        let service: any = await new GlobalService().getService(nodeData.appEntityName, this.context);
        let viewparams = Util.deepCopy(this.viewparams);
        this.onControlRequset('saveAndRefresh', tempContext, viewparams);
        this.service.update('Update', tempContext, nodeData.curData, service).then((response: any) => {
            this.onControlResponse('saveAndRefresh', response);
            if (!response.status || response.status !== 200) {
                this.$throw(response, 'update');
            } else {
                this.$success((nodeData.srfmajortext ? nodeData.srfmajortext : '') + '变更' + (this.$t('app.commonwords.success') as string), 'update');
            }
            this.refreshEditNodeParent(node);
        }).catch((response: any) => {
            this.onControlResponse('saveAndRefresh', response);
            this.$throw(response, 'update');
            this.refreshEditNodeParent(node);
        });
    }

    /**
     * 刷新编辑节点的父节点
     * 
     * @param editNode 编辑节点
     * @memberof TreeControlBase
     */
    public refreshEditNodeParent(editNode: any) {
        let curNode: any = {};
        const { parent: _parent } = editNode;
        curNode = Util.deepObjectMerge(curNode, _parent);
        let tempContext: any = {};
        if (curNode.data && curNode.data.srfappctx) {
            Object.assign(tempContext, curNode.data.srfappctx);
        } else {
            Object.assign(tempContext, this.context);
        }
        const id: string = _parent.key ? _parent.key : '#';
        const param: any = { srfnodeid: id };
        this.refresh_node(tempContext, param, false);
    }
}
