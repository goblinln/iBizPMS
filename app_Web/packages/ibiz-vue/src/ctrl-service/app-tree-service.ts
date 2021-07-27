import { AppTreeModel } from 'ibiz-vue';
import { ControlServiceBase, Util, ModelTool } from 'ibiz-core';
import { CodeListService, GlobalService } from 'ibiz-service';
import { IPSDETree, IPSDETreeNode, IPSDETreeNodeRS, IPSNavigateContext, IPSNavigateParam, IPSAppDataEntity, IPSAppDEField, IPSDETreeNodeDataItem, IPSDETreeNodeRSParam, IPSDETreeDataSetNode, IPSDETreeStaticNode, IPSDETreeCodeListNode, IPSAppCodeList } from '@ibiz/dynamic-model-api';
/**
 * 树视图部件服务对象
 *
 * @export
 * @class AppTreeService
 * @extends {ControlServiceBase}
 */
export class AppTreeService extends ControlServiceBase {
    /**
     * 树视图部件实例对象
     *
     * @memberof MainModel
     */
    public controlInstance!: IPSDETree;

    /**
     * 代码表服务对象
     *
     * @memberof MainModel
     */
    public codeListService: CodeListService = new CodeListService();

    /**
     * 全局实体服务
     *
     * @type {GlobalService}
     * @memberof AppTreeService
     */
    public globalService: GlobalService = new GlobalService();

    /**
     * 节点分隔符号
     *
     * @public
     * @type {string}
     * @memberof AppTreeService
     */
    public TREENODE_SEPARATOR: string = ';';

    /**
     * Creates an instance of AppTreeService.
     *
     * @param {*} [opts={}]
     * @memberof AppFormService
     */
    constructor(opts: any = {}, context?: any) {
        super(opts, context);
        this.initServiceParam(opts);
    }

    /**
     * 初始化服务参数
     *
     * @type {boolean}
     * @memberof AppFormService
     */
    public async initServiceParam(opts: any) {
        this.controlInstance = opts;
        this.model = new AppTreeModel(opts);
        // todo加载每个节点需要的服务
    }

    /**
     * 获取节点数据
     *
     * @param {string} action
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppTreeService
     */
    public async getNodes(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        let { srfparentkey, srfcat, srfnodeid, srfnodefilter, query } = data;
        srfnodefilter = query ? query : srfnodefilter;
        let list: any[] = [];
        let filter: any = {};
        const treeNodes = this.controlInstance.getPSDETreeNodes() || [];
        const rootNode = treeNodes.find((node: IPSDETreeNode) => {
            return node.rootNode;
        }) as IPSDETreeNode;
        // 处理是否显示根节点
        if (!srfnodeid || Object.is(srfnodeid, '#')) {
            if (this.controlInstance.rootVisible) {
                await this.fillNodeData(rootNode, context, filter, list);
                return Promise.resolve({ status: 200, data: list });
            } else {
                srfnodeid = rootNode?.nodeType;
            }
        }

        let strTreeNodeId: string = srfnodeid;
        let strRealNodeId: string = '';
        let bRootSelect: boolean = false;
        let strNodeType: string;
        let strRootSelectNode: string = '';

        if (Object.is(strTreeNodeId, rootNode?.nodeType)) {
            strNodeType = rootNode?.nodeType;
            if (srfparentkey) {
                strRealNodeId = srfparentkey;
            }
        } else {
            let nPos = strTreeNodeId.indexOf(this.TREENODE_SEPARATOR);
            if (nPos === -1) {
                return Promise.reject({
                    status: 500,
                    data: { title: '失败', message: `树节点${strTreeNodeId}标识无效` },
                });
            }
            strNodeType = strTreeNodeId.substring(0, nPos);
            strRealNodeId = strTreeNodeId.substring(nPos + 1);
        }

        // 处理过滤参数
        Object.assign(filter, {
            srfparentkey: srfparentkey,
            srfcat: srfcat,
            srfnodefilter: srfnodefilter,
            strRealNodeId: strRealNodeId,
            srfnodeid: srfnodeid,
            strNodeType: strNodeType,
            viewparams: Util.deepCopy(data).viewparams,
        });

        // 分解节点标识
        let nodeid: string[] = strRealNodeId.split(this.TREENODE_SEPARATOR);
        for (let i = 0; i < nodeid.length; i++) {
            switch (i) {
                case 0:
                    Object.assign(filter, { nodeid: nodeid[0] });
                    break;
                case 1:
                    Object.assign(filter, { nodeid2: nodeid[1] });
                    break;
                case 2:
                    Object.assign(filter, { nodeid3: nodeid[2] });
                    break;
                case 3:
                    Object.assign(filter, { nodeid4: nodeid[3] });
                    break;
                default:
                    break;
            }
        }

        // 根据节点标识填充节点
        const nodeJson = treeNodes.find((_node: IPSDETreeNode) => {
            return _node.nodeType == strNodeType;
        }) as IPSDETreeNode;
        if (nodeJson) {
            await this.fillChildNodes(nodeJson, context, filter, list);
            return Promise.resolve({ status: 200, data: list });
        } else {
            return Promise.resolve({ status: 500, data: { title: '失败', message: `树节点${strTreeNodeId}标识无效` } });
        }
    }

    /**
     * 根据节点关系填充所有子节点
     *
     * @public
     * @param {*} nodeJson
     * @param {any{}} context
     * @param {*} filter
     * @param {any[]} list
     * @returns {Promise<any>}
     * @memberof AppTreeService
     */
    public async fillChildNodes(nodeJson: IPSDETreeNode, context: any = {}, filter: any, list: any[]): Promise<any> {
        // 过滤出父节点是该节点的节点关系集合
        const treeNodeRSs = this.controlInstance.getPSDETreeNodeRSs() as IPSDETreeNodeRS[];
        if (treeNodeRSs.length > 0) {
            let nodeRSs = treeNodeRSs.filter((nodeRS: IPSDETreeNodeRS) => {
                // if (nodeRS?.getParentPSDETreeNode()?.id !== nodeJson.id) {
                //     return false;
                // }
                if (nodeRS?.getParentPSDETreeNode()?.id !== nodeJson.id) {
                    return false;
                }
                // 搜索模式 1 有搜索时启用， 2 无搜索时启用， 3 全部启用
                switch (nodeRS?.searchMode) {
                    case 1:
                        return !Util.isEmpty(filter.srfnodefilter);
                    case 2:
                        return Util.isEmpty(filter.srfnodefilter);
                    case 3:
                        return true;
                    default:
                        return false;
                }
            });
            // 填充对应节点关系的子节点
            if (nodeRSs && nodeRSs.length > 0) {
                for (let i = 0, len = nodeRSs.length; i < len; i++) {
                    let rsNavContext: any = this.getNavContext(nodeRSs[i]);
                    let rsNavParams: any = this.getNavParams(nodeRSs[i]);
                    let rsParams: any = this.getParams(nodeRSs[i]);
                    // 根据节点标识填充节点
                    const treeNodes = this.controlInstance.getPSDETreeNodes() || [];
                    let treeNode: any = treeNodes.find((_node: IPSDETreeNode) => {
                        return nodeRSs[i]?.getChildPSDETreeNode()?.id == _node.id;
                    });
                    if (treeNode) {
                        await this.fillNodeData(treeNode, context, filter, list, rsNavContext, rsNavParams, rsParams);
                    }
                }
            }
        }
    }

    /**
     * 填充树视图节点数据内容
     *
     * @public
     * @param {*} nodeJson 节点数据
     * @param {any{}} context
     * @param {*} filter
     * @param {any[]} list
     * @param {*} rsNavContext
     * @param {*} rsNavParams
     * @param {*} rsParams
     * @returns {Promise<any>}
     * @memberof AppTreeService
     */
    public fillNodeData(nodeJson: IPSDETreeNode, context: any = {}, filter: any, list: any[], rsNavContext?: any, rsNavParams?: any, rsParams?: any): Promise<any> {
        context = this.handleResNavContext(context, filter, rsNavContext);
        filter = this.handleResNavParams(context, filter, rsNavParams, rsParams);
        return new Promise((resolve: any, reject: any) => {
            // 静态节点
            if (nodeJson.treeNodeType == 'STATIC') {
                // 快速搜索过滤
                if (nodeJson.enableQuickSearch && filter.srfnodefilter && !Object.is(filter.srfnodefilter, "") && (nodeJson as IPSDETreeStaticNode)?.text?.toUpperCase().indexOf(filter.srfnodefilter.toUpperCase()) == -1) {
                    return resolve(list);
                }
                const treeNode: any = {
                    text: (nodeJson as IPSDETreeStaticNode)?.text,
                    tooltip: (nodeJson as IPSDETreeStaticNode)?.tooltip,
                    cssName: (nodeJson as IPSDETreeStaticNode)?.getPSSysCss()?.cssName,
                    nodeType: nodeJson.treeNodeType,
                    lanResTag: nodeJson.getNamePSLanguageRes()?.lanResTag,
                    isUseLangRes: false,
                    allowDrag: nodeJson.allowDrag,
                    allowDrop: nodeJson.allowDrop,
                    allowEditText: nodeJson.allowEditText,
                    allowOrder: nodeJson.allowOrder,
                    srfappctx: context,
                    srfmajortext: (nodeJson as IPSDETreeStaticNode)?.text,
                    enablecheck: nodeJson.enableCheck,
                    disabled: nodeJson.disableSelect,
                    expanded: nodeJson.expanded || filter.isAutoExpand,
                    leaf: !nodeJson.hasPSDETreeNodeRSs(),
                    selected: nodeJson.selected,
                    navfilter: nodeJson.navFilter,
                    navigateContext: nodeJson?.getPSNavigateContexts() && ModelTool.getNavigateContext(nodeJson),
                    navigateParams: nodeJson?.getPSNavigateParams() && ModelTool.getNavigateParams(nodeJson),
                };

                let strNodeId: string = nodeJson.nodeType;
                // 处理静态节点值
                if (!Util.isEmpty((nodeJson as IPSDETreeStaticNode).nodeValue)) {
                    Object.assign(treeNode, { srfkey: (nodeJson as IPSDETreeStaticNode).nodeValue });
                    strNodeId += this.TREENODE_SEPARATOR + (nodeJson as IPSDETreeStaticNode).nodeValue;
                    if (nodeJson.appendPNodeId) {
                        strNodeId += this.TREENODE_SEPARATOR + filter.strRealNodeId;
                    }
                } else {
                    // 没有指定节点值，直接使用父节点值
                    Object.assign(treeNode, { srfkey: filter.strRealNodeId });
                    strNodeId += this.TREENODE_SEPARATOR;
                    strNodeId += filter.strRealNodeId;
                }
                Object.assign(treeNode, { id: strNodeId });

                // 处理图标
                if (nodeJson.getPSSysImage()) {
                    if (nodeJson.getPSSysImage()?.cssClass) {
                        Object.assign(treeNode, { iconcls: nodeJson.getPSSysImage()?.cssClass });
                    } else {
                        Object.assign(treeNode, { icon: nodeJson.getPSSysImage()?.imagePath });
                    }
                }

                // 计数器标识
                if (nodeJson.counterId) {
                    Object.assign(treeNode, { counterId: nodeJson.counterId });
                }
                // 为1时计数器不显示0值
                if (nodeJson.counterMode) {
                    Object.assign(treeNode, { counterMode: nodeJson.counterMode });
                }

                // 补充nodeid和nodeid2
                Object.assign(treeNode, { nodeid: treeNode.srfkey, nodeid2: filter.strRealNodeId });
                list.push(treeNode);
                resolve(list);

                // 动态节点（代码表）
            } else if (nodeJson.treeNodeType == 'CODELIST') {
                let codeListItems: any = [];
                if ((nodeJson as IPSDETreeCodeListNode)?.getPSCodeList()) {
                    let curNodeCodeList: IPSAppCodeList = ((nodeJson as IPSDETreeCodeListNode)?.getPSCodeList()) as IPSAppCodeList;
                    curNodeCodeList.fill().then((result: any) => {
                        this.codeListService.getDataItems({ tag: result.codeName, type: result.codeListType, data: result, context: context }).then((items: any) => {
                            codeListItems = items;
                            let treeNodes: any = this.transFormCodeListData(codeListItems, context, filter, nodeJson);
                            treeNodes.forEach((treeNode: any) => {
                                list.push(treeNode);
                            });
                            resolve(list)
                        })
                    })
                }
                // 动态节点（实体）
            } else if (nodeJson.treeNodeType == 'DE' && nodeJson?.getPSAppDataEntity()) {
                let bFirst: boolean = true;
                let searchFilter: any = {};
                const nodeRSs = this.controlInstance.getPSDETreeNodeRSs() as IPSDETreeNodeRS[];
                if (nodeRSs?.length > 0) {
                    nodeRSs.forEach((noders: IPSDETreeNodeRS) => {
                        if (noders?.getChildPSDETreeNode()?.id == nodeJson.id) {
                            let pickupfield: any =
                                noders.parentFilter
                                    ? noders.parentFilter.toLowerCase()
                                    : noders.getParentPSDER1N() && noders.getParentPSAppDEField()
                                        ? noders.getParentPSAppDEField()?.name.toLowerCase() : "";
                            if (pickupfield && !Object.is(pickupfield, "")) {
                                const treeNodes = this.controlInstance.getPSDETreeNodes() || [];
                                const tempNode = treeNodes.find((_node: IPSDETreeNode) => {
                                    return noders?.getParentPSDETreeNode()?.id == _node.id;
                                }) as IPSDETreeNode;
                                if (Object.is(filter.strNodeType, tempNode.nodeType)) {
                                    Object.assign(searchFilter, { [`n_${pickupfield}_eq`]: filter[`nodeid${noders.parentValueLevel > 1 ? noders.parentValueLevel : ""}`] });
                                }
                            }
                        }
                    })
                }
                Object.assign(searchFilter, { total: false });
                if (nodeJson.enableQuickSearch) {
                    Object.assign(searchFilter, { query: filter.srfnodefilter });
                }
                let records: any[] = [];
                try {
                    this.searchNodeData(nodeJson, context, searchFilter, filter).then((records: any) => {
                        if (records && records.length > 0) {
                            records.forEach((entity: any) => {
                                let treeNode: any = {};
                                let appDataEntity = nodeJson.getPSAppDataEntity() as IPSAppDataEntity;
                                const deCodeName = appDataEntity?.codeName;
                                // 设置实体主键属性
                                let strId: string = '';
                                if ((nodeJson as IPSDETreeDataSetNode)?.getIdPSAppDEField()) {
                                    const codeName: any = (nodeJson as IPSDETreeDataSetNode).getIdPSAppDEField()?.codeName.toLowerCase();
                                    strId = entity[codeName];
                                } else {
                                    let keyField = ModelTool.getAppEntityKeyField(appDataEntity) as IPSAppDEField;
                                    strId = entity[keyField?.codeName.toLowerCase()];
                                }
                                // 设置实体主信息属性
                                let strText: string = '';
                                if ((nodeJson as IPSDETreeDataSetNode)?.getTextPSAppDEField()) {
                                    const codeName: any = (nodeJson as IPSDETreeDataSetNode).getTextPSAppDEField()?.codeName.toLowerCase();
                                    Object.assign(treeNode, { nodeTextField: codeName });
                                    strText = entity[codeName];
                                } else {
                                    let majorField = ModelTool.getAppEntityMajorField(appDataEntity) as IPSAppDEField;
                                    Object.assign(treeNode, { nodeTextField: majorField?.codeName.toLowerCase() });
                                    strText = entity[majorField?.codeName.toLowerCase()];
                                }
                                Object.assign(treeNode, { srfparentdename: deCodeName, srfparentdemapname: appDataEntity?.getPSDEName(), srfparentkey: strId });
                                let tempContext: any = Util.deepCopy(context);
                                Object.assign(tempContext, { srfparentdename: deCodeName, srfparentdemapname: appDataEntity?.getPSDEName(), srfparentkey: strId, [deCodeName.toLowerCase()]: strId });
                                Object.assign(treeNode, { srfappctx: tempContext });
                                Object.assign(treeNode, { [deCodeName.toLowerCase()]: strId });
                                Object.assign(treeNode, { srfkey: strId });
                                Object.assign(treeNode, { text: strText, srfmajortext: strText });
                                let strNodeId: string = nodeJson.nodeType;
                                strNodeId += this.TREENODE_SEPARATOR;
                                strNodeId += strId;
                                if (nodeJson.appendPNodeId) {
                                    strNodeId += this.TREENODE_SEPARATOR;
                                    strNodeId += filter.realnodeid;
                                }
                                Object.assign(treeNode, { id: strNodeId });
                                if (nodeJson?.getPSSysImage()?.cssClass) {
                                    Object.assign(treeNode, { iconcls: nodeJson.getPSSysImage()?.cssClass });
                                } else if (nodeJson?.getPSSysImage()?.imagePath) {
                                    Object.assign(treeNode, { icon: nodeJson.getPSSysImage()?.imagePath });
                                }
                                if (nodeJson.enableCheck) {
                                    Object.assign(treeNode, { enablecheck: true });
                                }
                                if (nodeJson.disableSelect) {
                                    Object.assign(treeNode, { disabled: true });
                                }
                                if (nodeJson.allowDrag) {
                                    Object.assign(treeNode, { allowDrag: true });
                                }
                                if (nodeJson.allowDrop) {
                                    Object.assign(treeNode, { allowDrop: true });
                                }
                                if (nodeJson.allowEditText) {
                                    Object.assign(treeNode, { allowEditText: true });
                                }
                                if (nodeJson.allowOrder) {
                                    Object.assign(treeNode, { allowOrder: true });
                                }
                                if (nodeJson.getPSSysCss()?.cssName) {
                                    Object.assign(treeNode, { cssName: nodeJson.getPSSysCss()?.cssName });
                                }
                                if (nodeJson.expanded) {
                                    Object.assign(treeNode, { expanded: nodeJson.expandFirstOnly ? bFirst : true });
                                } else {
                                    Object.assign(treeNode, { expanded: filter.isAutoExpand });
                                }
                                Object.assign(treeNode, { leaf: !nodeJson.hasPSDETreeNodeRSs() });
                                if ((nodeJson as IPSDETreeDataSetNode)?.getLeafFlagPSAppDEField()) {
                                    let objLeafFlag = entity[(nodeJson as IPSDETreeDataSetNode).getLeafFlagPSAppDEField()?.codeName.toLowerCase() as string];
                                    if (objLeafFlag != null) {
                                        let strLeafFlag: string = objLeafFlag.toString().toLowerCase();
                                        if (Object.is(strLeafFlag, '1') || Object.is(strLeafFlag, 'true')) {
                                            Object.assign(treeNode, { leaf: true });
                                        }
                                    }
                                }
                                const nodeDataItems: any = nodeJson.getPSDETreeNodeDataItems() as IPSDETreeNodeDataItem[];
                                if (nodeDataItems?.length > 0) {
                                    nodeDataItems.forEach((item: IPSDETreeNodeDataItem) => {
                                        if (item?.getPSAppDEField()) {
                                            Object.assign(treeNode, { [item.name.toLowerCase()]: entity[item.getPSAppDEField()?.codeName.toLowerCase() as string] });
                                        }
                                    });
                                }
                                Object.assign(treeNode, { selected: nodeJson.selected });
                                if (nodeJson.navFilter) {
                                    Object.assign(treeNode, { navfilter: nodeJson.navFilter });
                                }
                                Object.assign(treeNode, { curData: entity });
                                if (nodeJson?.getPSNavigateContexts()) {
                                    Object.assign(treeNode, { navigateContext: ModelTool.getNavigateContext(nodeJson) });
                                }
                                if (nodeJson?.getPSNavigateParams()) {
                                    Object.assign(treeNode, { navigateParams: ModelTool.getNavigateParams(nodeJson) });
                                }
                                // 计数器标识
                                if (nodeJson.counterId) {
                                    Object.assign(treeNode, { counterId: nodeJson.counterId });
                                }
                                // 为1时计数器不显示0值
                                if (nodeJson.counterMode) {
                                    Object.assign(treeNode, { counterMode: nodeJson.counterMode });
                                }
                                Object.assign(treeNode, { nodeid: treeNode.srfkey });
                                Object.assign(treeNode, { nodeid2: filter.strRealNodeId });

                                Object.assign(treeNode, { nodeType: nodeJson.nodeType, appEntityName: deCodeName });
                                list.push(treeNode);
                                resolve(list);
                                bFirst = false;
                            });
                        } else {
                            resolve(list);
                        }
                    });
                } catch (error) {
                    console.error(error);
                }
            } else {
                resolve(list);
            }
        });
    }

    /**
     * 获取查询集合
     *
     * @param {*} [context={}]
     * @param {*} searchFilter
     * @param {*} filter
     * @returns {Promise<any>}
     * @memberof AppTreeService
     */
    public async searchNodeData(nodeJson: IPSDETreeNode, context: any = {}, searchFilter: any, filter: any): Promise<any> {
        if (filter.viewparams) {
            Object.assign(searchFilter, filter.viewparams);
        }
        if (!searchFilter.page) {
            Object.assign(searchFilter, { page: 0 });
        }
        if (!searchFilter.size) {
            Object.assign(searchFilter, { size: 1000 });
        }
        if (context && context.srfparentdename) {
            Object.assign(searchFilter, { srfparentdename: Util.deepCopy(context).srfparentdename });
        }
        if (context && context.srfparentkey) {
            Object.assign(searchFilter, { srfparentkey: Util.deepCopy(context).srfparentkey });
        }
        const appDataEntity = nodeJson.getPSAppDataEntity() as IPSAppDataEntity;
        await appDataEntity.fill();
        if ((nodeJson as IPSDETreeDataSetNode)?.getSortPSAppDEField() && (nodeJson as IPSDETreeDataSetNode).getSortPSAppDEField()?.codeName && (nodeJson as IPSDETreeDataSetNode)?.sortDir) {
            Object.assign(searchFilter, { sort: `${(nodeJson as IPSDETreeDataSetNode).getSortPSAppDEField()?.codeName.toLowerCase()},${(nodeJson as IPSDETreeDataSetNode).sortDir.toLowerCase()}` });
        }
        let appEntityService = await this.globalService.getService(appDataEntity?.codeName, context);
        let list: any[] = [];
        if (appEntityService[(nodeJson as IPSDETreeDataSetNode)?.getPSAppDEDataSet()?.codeName as string] && appEntityService[(nodeJson as IPSDETreeDataSetNode).getPSAppDEDataSet()?.codeName as string] instanceof Function) {
            const response = await appEntityService[(nodeJson as IPSDETreeDataSetNode).getPSAppDEDataSet()?.codeName as string](context, searchFilter, false);
            try {
                if (!response.status || response.status !== 200) {
                    console.error(`查询${(nodeJson as IPSDETreeDataSetNode)?.getPSAppDEDataSet()?.codeName}数据集异常!`);
                    return [];
                }
                const data: any = response.data;
                if (Object.keys(data).length > 0) {
                    list = Util.deepCopy(data);
                    return list;
                } else {
                    return [];
                }
            } catch (error) {
                console.error(`查询${(nodeJson as IPSDETreeDataSetNode)?.getPSAppDEDataSet()?.codeName}数据集异常!`);
                return [];
            }
        }
    }


    /**
     * 转换代码表数据为树节点数据
     *
     * @param {Array<any>} codeItems
     * @param {*} context
     * @param {*} filter 过滤参数
     * @param {*} nodeJson
     * @returns
     * @memberof AppTreeService
     */
    public transFormCodeListData(codeItems: Array<any>, context: any, filter: any, nodeJson: IPSDETreeNode) {
        let treeNodes: Array<any> = [];
        let bFirst = true;
        if (codeItems?.length > 0) {
            for (const item of codeItems) {
                let node: any = {
                    srfappctx: context,
                    curData: item
                }
                if ((nodeJson as IPSDETreeCodeListNode)?.getPSCodeList()?.codeListType == 'STATIC') {
                    Object.assign(node, {
                        // todo 代码表多语言
                        text: item.text,
                        nodeType: 'STATIC',
                        // text: i18n.t(`codelist.${nodeJson.$codeList.codeName}.${item.value}`)
                        // isUseLangRes: true,
                    })
                } else {
                    Object.assign(node, { text: item.text, nodeType: nodeJson.treeNodeType, appEntityName: nodeJson.getPSAppDataEntity()?.codeName })
                }

                // 快速搜索过滤
                if (nodeJson.enableQuickSearch && filter.srfnodefilter) {
                    let pattern = new RegExp(`${node.text}`, 'i');
                    if (!pattern.test(filter.srfnodefilter)) {
                        continue;
                    }
                }
                Object.assign(node, { srfmajortext: node.text });
                Object.assign(node, { srfkey: item.value });
                let strNodeId: string = nodeJson.nodeType;
                strNodeId += this.TREENODE_SEPARATOR;
                strNodeId += item.value;
                if (nodeJson.appendPNodeId) {
                    strNodeId += this.TREENODE_SEPARATOR;
                    strNodeId += filter.realnodeid;
                }
                Object.assign(node, { id: strNodeId });

                // 处理图标
                if (nodeJson?.getPSSysImage()) {
                    if (nodeJson.getPSSysImage()?.cssClass) {
                        Object.assign(node, { iconcls: nodeJson.getPSSysImage()?.cssClass });
                    } else {
                        Object.assign(node, { icon: nodeJson.getPSSysImage()?.imagePath });
                    }
                }
                if (nodeJson.getPSSysCss()?.cssName) {
                    Object.assign(node, { cssName: nodeJson.getPSSysCss()?.cssName });
                }
                if (nodeJson.enableCheck) {
                    Object.assign(node, { enablecheck: true });
                }
                if (nodeJson.disableSelect) {
                    Object.assign(node, { disabled: true });
                }
                if (nodeJson.allowDrag) {
                    Object.assign(node, { allowDrag: true });
                }
                if (nodeJson.allowDrop) {
                    Object.assign(node, { allowDrop: true });
                }
                if (nodeJson.allowEditText) {
                    Object.assign(node, { allowEditText: true });
                }
                if (nodeJson.allowOrder) {
                    Object.assign(node, { allowOrder: true });
                }
                if (nodeJson.expanded) {
                    Object.assign(node, { expanded: nodeJson.expandFirstOnly ? bFirst : true });
                } else {
                    Object.assign(node, { expanded: filter.isAutoExpand });
                }
                Object.assign(node, { selected: nodeJson.selected });
                if (nodeJson.navFilter) {
                    Object.assign(node, { navfilter: nodeJson.navFilter });
                }
                if (nodeJson.getPSNavigateContexts()) {
                    Object.assign(node, { navigateContext: ModelTool.getNavigateContext(nodeJson) });
                }
                if (nodeJson.getPSNavigateParams()) {
                    Object.assign(node, { navigateParams: ModelTool.getNavigateParams(nodeJson) });
                }
                // 计数器标识
                if (nodeJson.counterId) {
                    Object.assign(node, { counterId: nodeJson.counterId });
                }
                // 为1时计数器不显示0值
                if (nodeJson.counterMode) {
                    Object.assign(node, { counterMode: nodeJson.counterMode });
                }
                Object.assign(node, { nodeid: node.srfkey });
                Object.assign(node, { nodeid2: filter.strRealNodeId });
                if (item.codeItems?.length > 0) {
                    let children = this.transFormCodeListData(item.codeItems, context, filter, nodeJson);
                    Object.assign(node, {
                        leaf: false,
                        children: children,
                    })
                } else {
                    // todo 动态节点的子节点
                    Object.assign(node, { leaf: true });
                }
                treeNodes.push(node)
            }
        }
        return treeNodes;
    }

    /**
     * 处理节点关系导航上下文
     *
     * @param context 应用上下文
     * @param filter 参数
     * @param resNavContext 节点关系导航上下文
     *
     * @memberof AppTreeService
     */
    public handleResNavContext(context: any, filter: any, resNavContext: any) {
        if (resNavContext && Object.keys(resNavContext).length > 0) {
            let tempContextData: any = Util.deepCopy(context);
            let tempViewParams: any = {};
            if (filter && filter.viewparams) {
                tempViewParams = filter.viewparams;
            }
            Object.keys(resNavContext).forEach((item: any) => {
                let curDataObj: any = resNavContext[item];
                this.handleCustomDataLogic(context, tempViewParams, curDataObj, tempContextData, item);
            });
            return tempContextData;
        } else {
            return context;
        }
    }

    /**
     * 处理关系导航参数
     *
     * @param context 应用上下文
     * @param filter 参数
     * @param resNavParams 节点关系导航参数
     * @param resParams 节点关系参数
     *
     * @memberof AppTreeService
     */
    public handleResNavParams(context: any, filter: any, resNavParams: any, resParams: any) {
        if ((resNavParams && Object.keys(resNavParams).length > 0) || (resParams && Object.keys(resParams).length > 0)) {
            let tempViewParamData: any = {};
            let tempViewParams: any = {};
            if (filter && filter.viewparams) {
                tempViewParams = filter.viewparams;
                tempViewParamData = Util.deepCopy(filter.viewparams);
            }
            if (Object.keys(resNavParams).length > 0) {
                Object.keys(resNavParams).forEach((item: any) => {
                    let curDataObj: any = resNavParams[item];
                    this.handleCustomDataLogic(context, tempViewParams, curDataObj, tempViewParamData, item);
                });
            }
            if (Object.keys(resParams).length > 0) {
                Object.keys(resParams).forEach((item: any) => {
                    let curDataObj: any = resParams[item];
                    tempViewParamData[item.toLowerCase()] = curDataObj.value;
                });
            }
            Object.assign(filter, { viewparams: tempViewParamData });
            return filter;
        } else {
            return filter;
        }
    }

    /**
     * 处理自定义节点关系导航数据
     *
     * @param context 应用上下文
     * @param viewparams 参数
     * @param curNavData 节点关系导航参数对象
     * @param tempData 返回数据
     * @param item 节点关系导航参数键值
     *
     * @memberof AppTreeService
     */
    public handleCustomDataLogic(context: any, viewparams: any, curNavData: any, tempData: any, item: string) {
        // 直接值直接赋值
        if (curNavData.isRawValue) {
            if (Util.isEmpty(curNavData.value)) {
                Object.defineProperty(tempData, item.toLowerCase(), {
                    value: null,
                    writable: true,
                    enumerable: true,
                    configurable: true,
                });
            } else {
                Object.defineProperty(tempData, item.toLowerCase(), {
                    value: curNavData.value,
                    writable: true,
                    enumerable: true,
                    configurable: true,
                });
            }
        } else {
            // 先从导航上下文取数，没有再从导航参数（URL）取数，如果导航上下文和导航参数都没有则为null
            if (context[curNavData.value.toLowerCase()] != null) {
                Object.defineProperty(tempData, item.toLowerCase(), {
                    value: context[curNavData.value.toLowerCase()],
                    writable: true,
                    enumerable: true,
                    configurable: true,
                });
            } else {
                if (viewparams[curNavData.value.toLowerCase()] != null) {
                    Object.defineProperty(tempData, item.toLowerCase(), {
                        value: viewparams[curNavData.value.toLowerCase()],
                        writable: true,
                        enumerable: true,
                        configurable: true,
                    });
                } else {
                    Object.defineProperty(tempData, item.toLowerCase(), {
                        value: null,
                        writable: true,
                        enumerable: true,
                        configurable: true,
                    });
                }
            }
        }
    }

    /**
     * 获取树节点关系导航上下文
     * 
     * @param noders 节点
     * @memberof AppTreeService
     */
    public getNavContext(noders: IPSDETreeNodeRS) {
        let context: any = {};
        let navcontext: any = noders.getPSNavigateContexts();
        if (navcontext?.length > 0) {
            navcontext.forEach((item: IPSNavigateContext) => {
                context[item?.key] = {
                    isRawValue: item?.rawValue,
                    value: item.value
                }
            })
        }
        return context;
    }

    /**
     * 获取树节点关系导航参数
     * 
     * @param noders 节点
     * @memberof AppTreeService
     */
    public getNavParams(noders: IPSDETreeNodeRS) {
        let params: any = {};
        let navparams: any = noders.getPSNavigateParams();
        if (navparams?.length > 0) {
            navparams.forEach((item: IPSNavigateParam) => {
                params[item?.key] = {
                    isRawValue: item?.rawValue,
                    value: item.value
                }
            })
        }
        return params;
    }

    /**
     * 获取树节点关系参数
     * 
     * @param noders 节点
     * @memberof AppTreeService
     */
    public getParams(noders: IPSDETreeNodeRS) {
        let params: any = {};
        let reparams: any = noders?.getPSDETreeNodeRSParams();
        if (reparams?.length > 0) {
            reparams.forEach((item: IPSDETreeNodeRSParam) => {
                params[item?.key] = {
                    value: item.value
                };
            })
        }
        return params;
    }

    /**
     * 修改数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {*} [service]
     * @param {boolean} [isWorkflow] 
     * @returns {Promise<any>}
     * @memberof AppTreeService
     */
     public update(action: string, context: any, data: any, service: any, isWorkflow?: boolean): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            let result: Promise<any>;
            if (service[action] && service[action] instanceof Function) {
                result = service[action](context, data);
            } else {
                result = service.Update(context, data);
            }
            result.then((response) => {
                resolve(response);
            }).catch(response => {
                reject(response);
            });
        });
    }
}
