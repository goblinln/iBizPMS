import moment from "moment";
import { Util, ViewTool } from 'ibiz-core';
import { MDControlBase } from './md-control-base';
import { AppMobTreeService } from '../ctrl-service';
import { AppViewLogicService } from "../app-service";
import { GlobalService, UIServiceRegister } from "ibiz-service";
import { IPSDETree, IPSDETreeNode, IPSDEToolbarItem, IPSDECMUIActionItem, IPSDEUIAction, IPSDETBUIActionItem } from '@ibiz/dynamic-model-api';


/**
 * 移动端树视图部件基类
 *
 * @export
 * @class MobTreeControlBase
 * @extends {MDControlBase}
 */
export class MobTreeControlBase extends MDControlBase {

    /**
     * 树视图部件的模型对象
     *
     * @type {*}
     * @memberof MobTreeControlBase
     */
    public controlInstance!: IPSDETree;

    /**
     * 树视图导航数组名称
     *
     * @type {string[]}
     * @memberof MobTreeControlBase
     */
    public treeNav: any = [];

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof MobTreeControlBase
     */
    public getDatas(): any[] {
        return [this.currentselectedNode];
    }

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof MobTreeControlBase
     */
    public getData(): any {
        return this.currentselectedNode;
    }

    /**
     * 枝干节点是否可用（具有数据能力，可抛出）
     *
     * @type {string}
     * @memberof MobTreeControlBase
     */
    public isBranchAvailable!: boolean;

    /**
     * 已选中数据集合
     *
     * @type {*}
     * @memberof MobTreeControlBase
     */
    public selectedNodes: any = [];

    /**
     * 当前选中数据项
     *
     * @type {*}
     * @memberof MobTreeControlBase
     */
    public currentselectedNode: any = {};

    /**
     * 选中数据字符串
     *
     * @type {string}
     * @memberof MobTreeControlBase
     */
    public selectedData?: string;

    /**
     * 选中值变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof MobTreeControlBase
     */
    public onSelectedDataValueChange(newVal: any) {
        this.echoselectedNodes = newVal ? this.isSingleSelect ? JSON.parse(newVal)[0] : JSON.parse(newVal) : [];
        this.selectedNodes = [];
        if (this.echoselectedNodes.length > 0) {
            let AllnodesObj = (this.$refs.treeexpbar_tree as any).store.nodesMap;
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
     * 节点点击加载
     *
     * @memberof MobTreeControlBase
     */
    public click_node(data: any) {
        if (this.treeNav[this.treeNav.length - 1].id == data.id) {
            return
        }
        this.treeNav.push(data);
        this.load({ data: data });
    }

    /**
     * 备份树节点上下文菜单
     * 
     * @type any
     * @memberof MainTreeBase
     */
    public copyActionModel: any;

    /**
     * 初始化树节点上下文菜单集合
     * 
     * @memberof CalendarControlBase
     */
    public initCtrlActionModel() {
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
     * @param toolbarItem 
     * @param item 
     * @param tempModel 
     * @memberof CalendarControlBase
     */
    public initActionModelItem(toolbarItem: IPSDEToolbarItem, item: IPSDETreeNode, tempModel: any) {
        if (!toolbarItem) {
            return
        }
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
        const toolbarItems = (toolbarItem as IPSDETBUIActionItem)?.getPSDEToolbarItems?.() || [];
        if (toolbarItems?.length > 0) {
            for (let toolBarChild of toolbarItems) {
                this.initActionModelItem(toolBarChild, item, tempModel)
            }
        }
    }

    /**
     * 显示上下文菜单
     * 
     * @param data 节点数据
     * @param event 事件源
     * @memberof DocLibTreeProductMobBase
     */
    public showContext(data: any, event: any) {
        let _this: any = this;
        this.copyActionModel = {};
        const tags: string[] = data.id.split(';');
        Object.values(this.actionModel).forEach((item: any) => {
            if (Object.is(item.nodeOwner, tags[0])) {
                this.copyActionModel[item.name] = item;
            }
        })
        if (Object.keys(this.copyActionModel).length === 0) {
            return;
        }
        this.computeNodeState(data, data.nodeType, data.appEntityName).then((result: any) => {
            let flag: boolean = false;
            if (Object.values(result).length > 0) {
                flag = Object.values(result).some((item: any) => {
                    return item.visabled === true;
                })
            }
            if (flag) {
                (_this.$refs.contextmenu as any).openContextMenu();
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
     * @memberof MobTreeControlBase
     */
    public async computeNodeState(node: any, nodeType: string, appEntityName: string) {
        if (Object.is(nodeType, 'STATIC')) {
            return this.copyActionModel;
        }
        try {
            let service: any = await new GlobalService().getService(appEntityName);
            if (this.copyActionModel && Object.keys(this.copyActionModel).length > 0) {
                if (service['Get'] && service['Get'] instanceof Function) {
                    let tempContext: any = Util.deepCopy(this.context);
                    tempContext[appEntityName.toLowerCase()] = node.srfkey;
                    let targetData = await service.Get(tempContext, {}, false);
                    const uiService = await UIServiceRegister.getInstance().getService(this.context, appEntityName.toLowerCase());
                    if (uiService) {
                        await uiService.loaded();
                    }
                    ViewTool.calcTreeActionItemAuthState(targetData.data, this.copyActionModel, uiService);
                } else {
                    console.warn('获取数据异常');
                }
            }
        } catch (error) {
            console.warn(error);
        }
        return this.copyActionModel;
    }

    /**
     * 导航点击行为
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof MobTreeControlBase
     */
    public nav_click(item: any) {
        if (item.id == this.treeNav[this.treeNav.length - 1].id) {
            return
        }
        const count = this.treeNav.findIndex((i: any) => item.id === i.id) + 1;
        this.treeNav.splice(count, this.treeNav.length - count);
        this.load({ data: item });
    }

    /**
     * 节点数组
     *
     * @memberof MobTreeControlBase
     */
    public rootNodes: any = [];

    /**
     * 节点值数组
     *
     * @memberof MobTreeControlBase
     */
    public valueNodes: any = [];

    /**
     * 解析节点数据
     *
     * @param {*} nodes
     * @memberof MobTreeControlBase
     */
    public parseNodes(nodes: any) {
        this.rootNodes = [];
        this.valueNodes = [];
        for (let index = 0; index < nodes.length; index++) {
            const item = nodes[index];
            if (!item.leaf) {
                this.rootNodes.push(item);
            } else {
                this.backfill(item);
                this.valueNodes.push(item);
            }
        }
        this.$forceUpdate();
    }

    /**
     * 回填已选择树节点
     *
     * @param {*} nodes
     * @memberof MobTreeControlBase
     */
    public backfill(item: any) {
        let ele: any = this.$refs[item.srfkey + 'checkbox'];
        if (this.selectedNodes.findIndex((temp: any) => { return temp.srfkey == item.srfkey }) > -1) {
            item.selected = true;
            if (ele && ele[0]) {
                ele[0].ariaChecked = true;
            }
        } else {
            item.selected = false;
            if (ele && ele[0]) {
                ele[0].ariaChecked = false;
            }
        }
    }

    /**
     * 回显选中数据集合
     *
     * @type {*}
     * @memberof MobTreeControlBase
     */
    public echoselectedNodes: any[] = [];

    /**
     * 过滤属性
     *
     * @type {string}
     * @memberof MobTreeControlBase
     */
    public srfnodefilter: string = '';

    /**
     * 树数据
     *
     * @type {any[]}
     * @memberof MobTreeControlBase
     */
    public nodes: any[] = [];

    /**
     * 数据展开主键
     *
     * @type {string[]}
     * @memberof MobTreeControlBase
     */
    public expandedKeys: string[] = [];

    /**
     * 选中数据变更事件
     *
     * @public
     * @param {*} data
     * @param {*} data 当前节点对应传入对象
     * @param {*} checkedState 树目前选中状态对象
     * @memberof MobTreeControlBase
     */
    public onCheck(data: any, checkedState: any) {
        // 处理多选数据
        if (!this.isSingleSelect) {
            let leafNodes = checkedState.checkedNodes.filter((item: any) => item.leaf);
            this.selectedNodes = JSON.parse(JSON.stringify(leafNodes));
            this.$emit('selectionchange', this.selectedNodes);
        }
    }

    /**
     * 选中数据变更事件
     *
     * @public
     * @param {*} data 节点对应传入对象
     * @param {*} node 节点对应node对象
     * @memberof MobTreeControlBase
     */
    public selectionChange(data: any, node: any) {
        // 禁用项处理
        if (data.disabled) {
            node.isCurrent = false;
            return;
        }
        // 只处理最底层子节点
        if (this.isBranchAvailable || data.leaf) {
            this.currentselectedNode = JSON.parse(JSON.stringify(data));
            // 单选直接替换
            if (this.isSingleSelect) {
                this.selectedNodes = [this.currentselectedNode];
                this.$emit('selectionchange', this.selectedNodes);
            }
            // 多选用check方法
        }
    }

    /**
     * 对树节点进行筛选操作
     * @memberof OrderTree
     */
    public filterNode(value: any, data: any) {
        if (!value) return true;
        return data.text.indexOf(value) !== -1;
    }

    /**
     * 刷新数据
     *
     * @memberof MobTreeControlBase
     */
    public refresh_all(): void {
        this.treeNav.splice(1, this.treeNav.length - 1);
        this.load();
    }

    /**
     * 刷新父节点
     *
     * @memberof MobTreeControlBase
     */
    public refresh_parent(): void {
        this.load({ data: this.treeNav[this.treeNav.length - 1] })
    }

    /**
     * 数据加载
     *
     * @param {*} node
     * @memberof MobTreeControlBase
     */
    public load(node: any = {}) {
        if (node.data && node.data.children) {
            return;
        }
        const params: any = {
            srfnodeid: node.data && node.data.id ? node.data.id : "#",
            srfnodefilter: this.srfnodefilter
        };
        let tempViewParams: any = JSON.parse(JSON.stringify(this.viewparams));
        if (tempViewParams.selectedData) {
            delete tempViewParams.selectedData;
        }
        let curNode: any = {};
        curNode = Util.deepObjectMerge(curNode, node);
        let tempContext: any = this.computecurNodeContext(curNode);
        if (curNode.data && curNode.data.srfparentdename) {
            Object.assign(tempContext, { srfparentdename: curNode.data.srfparentdename });
            Object.assign(tempViewParams, { srfparentdename: curNode.data.srfparentdename });
        }
        if (curNode.data && curNode.data.srfparentkey) {
            Object.assign(tempContext, { srfparentkey: curNode.data.srfparentkey });
            Object.assign(tempViewParams, { srfparentkey: curNode.data.srfparentkey });
        }
        Object.assign(params, { viewparams: tempViewParams });
        this.service.getNodes(tempContext, params).then((response: any) => {
            if (!response || response.status !== 200) {
                this.$Notice.error(response.info);
                return;
            }
            const _items = response.data;
            this.formatExpanded(_items);
            this.nodes = [..._items];
            this.parseNodes(this.nodes);
            let isRoot = Object.is(node.level, 0);
            let isSelectedAll = node.checked;
            this.setDefaultSelection(_items, isRoot, isSelectedAll);
            this.$emit("load", _items);
        }).catch((response: any) => {
            if (response && response.status === 401) {
                return;
            }
            this.$Notice.error(response.info);
        });
    }

    /**
     * 计算当前节点的上下文
     *
     * @param {*} curNode 当前节点
     * @memberof MobTreeControlBase
     */
    public computecurNodeContext(curNode: any) {
        let tempContext: any = {};
        if (curNode && curNode.data && curNode.data.srfappctx) {
            tempContext = JSON.parse(JSON.stringify(curNode.data.srfappctx));
        } else {
            tempContext = JSON.parse(JSON.stringify(this.context));
        }
        return tempContext;
    }

    /**
     * 刷新功能
     *
     * @param {any[]} args
     * @memberof MobTreeControlBase
     */
    public refresh(args: any[]): void {
        this.refresh_all();
    }

    /**
     * 默认展开节点
     *
     * @public
     * @param {any[]} items
     * @returns {any[]}
     * @memberof MobTreeControlBase
     */
    public formatExpanded(items: any[]): any[] {
        const data: any[] = [];
        items.forEach((item) => {
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
     * @memberof MobTreeControlBase
     */
    public setDefaultSelection(items: any[], isRoot: boolean = false, isSelectedAll: boolean = false): void {
        if (items.length == 0) {
            return;
        }
        let defaultData: any;
        // 导航中选中第一条配置的默认选中,没有选中第一条
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
                        index = 0;
                    } else {
                        return;
                    }
                }
                defaultData = items[index];
                this.setTreeNodeHighLight(defaultData);
                this.currentselectedNode = JSON.parse(JSON.stringify(defaultData));
                if (this.isBranchAvailable || defaultData.leaf) {
                    this.selectedNodes = [this.currentselectedNode];
                    this.ctrlEvent({
                        controlname: this.controlInstance.name,
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
                        this.currentselectedNode = JSON.parse(JSON.stringify(checkedNodes[0]));
                        this.selectedNodes = [this.currentselectedNode];
                    } else {
                        this.selectedNodes = this.selectedNodes.concat(checkedNodes);
                        const tree: any = this.$refs.treeexpbar_tree;
                        tree.setCheckedNodes(this.selectedNodes);
                    }
                    this.ctrlEvent({
                        controlname: this.controlInstance.name,
                        action: 'selectionchange',
                        data: this.selectedNodes,
                    });
                }
            }
        }
        // 父节点选中时，选中所有子节点
        if (isSelectedAll) {
            let leafNodes = items.filter((item: any) => item.leaf);
            this.selectedNodes = this.selectedNodes.concat(leafNodes);
            this.ctrlEvent({
                controlname: this.controlInstance.name,
                action: 'selectionchange',
                data: this.selectedNodes,
            });
        }
    }

    /**
     * 设置选中高亮
     *
     * @param {*} data
     * @memberof MobTreeControlBase
     */
    public setTreeNodeHighLight(data: any): void {
        const tree: any = this.$refs[this.controlInstance.name];
        tree?.setCurrentKey(data.id);
    }

    /**
     * 执行默认界面行为
     *
     * @param {*} node
     * @memberof MobTreeControlBase
     */
    public doDefaultAction(node: any) {
        if (node && node.data) {
            const data: any = node.data;
            const tags: string[] = data.id.split(';');
            // todo 执行默认界面行为
        }
        this.ctrlEvent({
            controlname: this.controlInstance.name,
            action: 'nodedblclick',
            data: this.selectedNodes,
        });
    }

    /**
    * 单选选择值
    *
    * @param {string} 
    * @memberof ${srfclassname('${ctrl.codeName}')}
    */
    public selectedValue: string = "";

    /**
     * 单选点击行为
     *
     * @param {*} item
     * @memberof MobTreeControlBase
     */
    public onSimpleSelChange(item: any) {
        this.ctrlEvent({
            controlname: this.controlInstance.name,
            action: 'selectchange',
            data: [item],
        });
        this.selectedValue = item.srfkey;
    }

    /**
     * 多选点击行为
     *
     * @param {*} data
     * @memberof MobTreeControlBase
     */
    public onChecked(data: any) {
        let { detail } = data;
        if (!detail) {
            return;
        }
        let { value } = detail;
        for (let index = 0; index < this.valueNodes.length; index++) {
            const item = this.valueNodes[index];
            if (Object.is(item.srfkey, value)) {
                if (detail.checked) {
                    this.selectedNodes.push(this.valueNodes[index]);
                } else {
                    let i = this.selectedNodes.findIndex((i: any) => i.srfkey === item.srfkey)
                    if (i > -1) {
                        this.selectedNodes.splice(i, 1)
                    }
                }
            }
        }
        this.ctrlEvent({
            controlname: this.controlInstance.name,
            action: 'selectchange',
            data: this.selectedNodes,
        });
    }

    /**
     * 激活节点
     *
     * @memberof MobTreeControlBase
     */
    public activeNode = "";

    /**
     * 节点长按
     *
     * @memberof MobTreeControlBase
     */
    public node_touch(item: any) {
        this.activeNode = item.id.split(';')[0];
        this.currentselectedNode = Object.assign(JSON.parse(JSON.stringify(item.curData || {})), item);
        this.showContext(item, {})
    }

    /**
     * 树前端搜索
     *
     * @memberof MobTreeControlBase
     */
    public webLoad(query: string) {
        let reNodes: any = [];
        for (let index = 0; index < this.nodes.length; index++) {
            const node = this.nodes[index];
            if (node.srfmajortext.indexOf(query) != -1) {
                reNodes.push(node);
            }
        }
        this.parseNodes(reNodes);
    }

    /**
     * 上下文菜单点击
     */
    public context_menu_click() {
        (this.$refs.contextmenu as any).closeContextMenu();
    }

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
        this.isSingleSelect = newVal?.isSingleSelect !== false;
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
     * 部件模型数据初始化
     *
     * @memberof AppDefaultMobForm
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit();
        this.service = new AppMobTreeService(this.controlInstance);
        this.treeNav = [{ id: "#", text: this.controlInstance.logicName }];
    }

    /**
     * 树视图部件初始化
     *
     * @memberof MobTreeControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                if (Object.is('load', action)) {
                    this.load();
                }
                if (Object.is('filter', action)) {
                    this.srfnodefilter = data.srfnodefilter;
                    this.refresh_all();
                }
                if (Object.is('refresh_parent', action)) {
                    this.refresh_parent();
                }
                if (Object.is('quicksearch', action)) {
                    this.webLoad(data);
                }
                if (Object.is('refresh', action)) {
                    this.selectedNodes = data;
                    this.parseNodes(this.nodes);
                    this.parseNodes(this.nodes);
                }
            });
        }
    }

    /**
     * 部件事件
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * 
     * @memberof ViewBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if (action == 'contextMenuItemClick') {
            AppViewLogicService.getInstance().executeViewLogic(`${controlname}_${data}_click`, undefined, this, undefined, this.controlInstance.getPSAppViewLogics());
        } else {
            this.ctrlEvent({ controlname, action, data });
        }
    }

}
