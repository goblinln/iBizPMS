import { ControlServiceBase, Util, ModelTool } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { AppTreeGridExModel } from '../ctrl-model/app-treegridex-model';
import { IPSDETreeGridEx, IPSDETreeNode, IPSDETreeNodeRS, IPSNavigateContext, IPSNavigateParam, IPSAppDataEntity, IPSAppDEField, IPSDETreeNodeDataItem, IPSDETreeNodeRSParam, IPSDETreeDataSetNode, IPSDETreeStaticNode, IPSDETreeCodeListNode } from '@ibiz/dynamic-model-api';

/**
 * Main 部件服务对象
 *
 * @export
 * @class AppFormService
 */
export class AppTreeGridExService extends ControlServiceBase {

    public controlInstance: IPSDETreeGridEx;

    /**
     * 节点分隔符号
     *
     * @public
     * @type {string}
     * @memberof AppTreeGridExService
     */
    public TREENODE_SEPARATOR: string = ';';

    /**
     * 全局实体服务
     *
     * @type {GlobalService}
     * @memberof AppTreeService
     */
    public globalService: GlobalService = new GlobalService();


    constructor(opts: any = {}) {
        super(opts);
        this.controlInstance = opts;
        this.model = new AppTreeGridExModel(opts);
    }

    /**
     * 获取节点数据
     *
     * @param {string} action
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppTreeGridExService
     */
    public async getNodes(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        let {
            srfparentkey,
            srfcat,
            srfnodeid,
            srfnodefilter,
            query,
        }: { srfparentkey: string; srfcat: string; srfnodeid: string; srfnodefilter: string; query: string } = data;
        srfnodefilter = query ? query : srfnodefilter;
        let list: any[] = [];
        let filter: any = {};

        const treeNodes =  this.controlInstance.getPSDETreeNodes() || [];
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
        let strNodeType: string | null = null;
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
        Object.assign(filter,
            {
                srfparentkey: srfparentkey,
                srfcat: srfcat,
                srfnodefilter: srfnodefilter,
                strRealNodeId: strRealNodeId,
                srfnodeid: srfnodeid,
                strNodeType: strNodeType,
                viewparams: JSON.parse(JSON.stringify(data)).viewparams
            }
        );

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
     * 填充子节点
     *
     * @public
     * @param {*} nodeJson
     * @param {any{}} context         
     * @param {*} filter
     * @param {any[]} list
     * @returns {Promise<any>}
     * @memberof AppTreeGridExService
     */
    public async fillChildNodes(nodeJson: IPSDETreeNode, context: any = {}, filter: any, list: any[]): Promise<any> {
        const treeNodeRSs = this.controlInstance.getPSDETreeNodeRSs() as IPSDETreeNodeRS[];
        if (treeNodeRSs.length > 0) {
            let nodeRSs = treeNodeRSs.filter((nodeRS: IPSDETreeNodeRS) => {
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
                    const treeNodes =  this.controlInstance.getPSDETreeNodes() || [];
                    let treeNode: any = treeNodes.find((_node:  IPSDETreeNode) => {
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
     * @param {*} nodeJson
     * @param {any{}} context     
     * @param {*} filter
     * @param {any[]} list
     * @param {*} rsNavContext   
     * @param {*} rsNavParams
     * @param {*} rsParams
     * @returns {Promise<any>}
     * @memberof AppTreeGridExService
     */
    public fillNodeData(nodeJson: IPSDETreeNode, context: any = {}, filter: any, list: any[], rsNavContext?: any, rsNavParams?: any, rsParams?: any): Promise<any> {
        context = this.handleResNavContext(context, filter, rsNavContext);
        filter = this.handleResNavParams(context, filter, rsNavParams, rsParams);
        return new Promise((resolve: any, reject: any) => {
            // 静态节点
            if (nodeJson?.treeNodeType == 'STATIC') {
                // 快速搜索
                if (nodeJson.enableQuickSearch && filter.srfnodefilter && !Object.is(filter.srfnodefilter, "") && (nodeJson as IPSDETreeStaticNode).text?.toUpperCase().indexOf(filter.srfnodefilter.toUpperCase()) == -1) {
                    return Promise.reject();
                }
                const treeNode: any = {
                    text: (nodeJson as IPSDETreeStaticNode)?.text,
                    nodeType: nodeJson?.treeNodeType,
                    isUseLangRes: true,
                    srfappctx: context,
                    srfmajortext: (nodeJson as IPSDETreeStaticNode)?.text,
                    enablecheck: nodeJson?.enableCheck,
                    disabled: nodeJson?.disableSelect,
                    expanded: nodeJson?.expanded || filter.isAutoexpand,
                    leaf: nodeJson?.hasPSDETreeNodeRSs,
                    selected: nodeJson?.selected,
                    navfilter: nodeJson?.navFilter,
                };

                let strNodeId: string = nodeJson.nodeType;
                // 处理静态节点值
                if (!Util.isEmpty((nodeJson as IPSDETreeStaticNode)?.nodeValue)) {
                    Object.assign(treeNode, { srfkey: (nodeJson as IPSDETreeStaticNode).nodeValue });
                    strNodeId += this.TREENODE_SEPARATOR + (nodeJson as IPSDETreeStaticNode).nodeValue;
                    if (nodeJson?.appendPNodeId) {
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
                if (nodeJson?.getPSSysImage()) {
                    if (nodeJson?.getPSSysImage()?.cssClass) {
                        Object.assign(treeNode, { iconcls: nodeJson?.getPSSysImage()?.cssClass });
                    } else {
                        Object.assign(treeNode, { icon: nodeJson?.getPSSysImage()?.imagePath });
                    }
                }

                // 补充nodeid和nodeid2
                Object.assign(treeNode, { nodeid: treeNode.srfkey, nodeid2: filter.strRealNodeId });
                list.push(treeNode);
                resolve(list);
            } else if (nodeJson.treeNodeType == 'CODELIST' && (nodeJson as IPSDETreeCodeListNode)?.getPSCodeList()) {
                let codeListItems: any = [];
                if ((nodeJson as IPSDETreeCodeListNode)?.getPSCodeList()?.codeListType == 'STATIC') {
                    codeListItems = (nodeJson as IPSDETreeCodeListNode)?.getPSCodeList()?.getPSCodeItems();
                } else if ((nodeJson as IPSDETreeCodeListNode)?.getPSCodeList()?.codeListType == 'DYNAMIC') {

                }
                let treeNodes: any = this.transFormCodeListData(codeListItems, context, filter, nodeJson);
                treeNodes.forEach((treeNode: any) => {
                    list.push(treeNode);
                });
                resolve(list)
            }
            else if (nodeJson?.treeNodeType == 'DE' && nodeJson.getPSAppDataEntity()) {
                let searchFilter: any = {};
                const nodeRSs = this.controlInstance.getPSDETreeNodeRSs() as IPSDETreeNodeRS[];
                if (nodeRSs?.length > 0) {
                    nodeRSs.forEach((noders: IPSDETreeNodeRS) => {
                        if (noders.getChildPSDETreeNode()?.id == nodeJson.id) {
                            let pickupfield: any =
                                noders.parentFilter
                                    ? noders.parentFilter.toLowerCase()
                                    : noders.getParentPSDER1N() && noders.getParentPSAppDEField()
                                        ? noders.getParentPSAppDEField()?.name.toLowerCase() : "";
                            if (pickupfield && !Object.is(pickupfield, "")) {
                                const treeNodes =  this.controlInstance.getPSDETreeNodes() || [];
                                const tempNode = treeNodes.find((_node:  IPSDETreeNode) => {
                                    return noders.getParentPSDETreeNode()?.id == _node.id;
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
                let bFirst: boolean = true;
                let records: any[] = [];
                try {
                    this.searchNodeData(nodeJson, context, searchFilter, filter).then((records: any) => {
                        if (records && records.length > 0) {
                            records.forEach((entity: any) => {
                                let treeNode: any = {};
                                let appDataEntity = nodeJson.getPSAppDataEntity() as IPSAppDataEntity;
                                const deCodeName = appDataEntity?.codeName;
                                // 整理context
                                // 设置实体主键属性
                                let strId: string = '';
                                if ((nodeJson as IPSDETreeDataSetNode)?.getIdPSAppDEField()) {
                                    strId = entity[(nodeJson as IPSDETreeDataSetNode).getIdPSAppDEField()?.codeName.toLowerCase() as string];
                                } else {
                                    let keyField = ModelTool.getAppEntityKeyField(appDataEntity) as IPSAppDEField;
                                    strId = entity[keyField?.codeName.toLowerCase()];
                                }
                                // 设置实体主信息属性
                                let strText: string = '';
                                if ((nodeJson as IPSDETreeDataSetNode)?.getTextPSAppDEField()) {
                                    strText = entity[(nodeJson as IPSDETreeDataSetNode).getTextPSAppDEField()?.codeName.toLowerCase() as string];
                                } else {
                                    let majorField = ModelTool.getAppEntityMajorField(appDataEntity) as IPSAppDEField;
                                    strText = entity[majorField?.codeName.toLowerCase()];
                                }
                                Object.assign(treeNode, { srfparentdename: deCodeName,srfparentdemapname: appDataEntity?.getPSDEName(), srfparentkey: strId });
                                let tempContext: any = JSON.parse(JSON.stringify(context));
                                Object.assign(tempContext, { srfparentdename: deCodeName,srfparentdemapname: appDataEntity?.getPSDEName(), srfparentkey: strId, [deCodeName.toLowerCase()]: strId });
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
                                if (nodeJson.getPSSysImage()?.cssClass) {
                                    Object.assign(treeNode, { iconcls: nodeJson.getPSSysImage()?.cssClass });
                                } else if (nodeJson.getPSSysImage()?.imagePath) {
                                    Object.assign(treeNode, { icon: nodeJson.getPSSysImage()?.imagePath });
                                }
                                if (nodeJson.enableCheck) {
                                    Object.assign(treeNode, { enablecheck: true });
                                }
                                if (nodeJson.disableSelect) {
                                    Object.assign(treeNode, { disabled: true });
                                }
                                if (nodeJson.expanded) {
                                    Object.assign(treeNode, { expanded: nodeJson.expandFirstOnly ? bFirst : true });
                                } else {
                                    Object.assign(treeNode, { expanded: filter.isautoexpand });
                                }
                                Object.assign(treeNode, { leaf: nodeJson.hasPSDETreeNodeRSs });
                                if ((nodeJson as IPSDETreeDataSetNode)?.getLeafFlagPSAppDEField()) {
                                    let objLeafFlag = entity[(nodeJson as IPSDETreeDataSetNode)?.getLeafFlagPSAppDEField()?.codeName.toLowerCase() as string];
                                    if (objLeafFlag != null) {
                                        let strLeafFlag: string = objLeafFlag.toString().toLowerCase();
                                        if (Object.is(strLeafFlag, '1') || Object.is(strLeafFlag, 'true')) {
                                            Object.assign(treeNode, { leaf: true });
                                        }
                                    }
                                }
                                const nodeDataItems:any = nodeJson.getPSDETreeNodeDataItems() as IPSDETreeNodeDataItem[];
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
                                if (nodeJson.getPSNavigateContexts()) {
                                    Object.assign(treeNode, { navigateContext: ModelTool.getNavigateContext(nodeJson) });
                                }
                                if (nodeJson.getPSNavigateParams()) {
                                    Object.assign(treeNode, { navigateParams: ModelTool.getNavigateParams(nodeJson) });
                                }
                                Object.assign(treeNode, { nodeid: treeNode.srfkey });
                                Object.assign(treeNode, { nodeid2: filter.strRealNodeId });

                                Object.assign(treeNode, { nodeType: nodeJson.nodeType, appEntityName: deCodeName.toLowerCase() });
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
                resolve(list)
            }
        });
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
                if (nodeJson.enableCheck) {
                    Object.assign(node, { enablecheck: true });
                }
                if (nodeJson.disableSelect) {
                    Object.assign(node, { disabled: true });
                }
                if (nodeJson.expanded) {
                    Object.assign(node, { expanded: nodeJson.expandFirstOnly ? bFirst : true });
                } else {
                    Object.assign(node, { expanded: filter.isautoexpand });
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
            Object.assign(searchFilter, { srfparentdename: JSON.parse(JSON.stringify(context)).srfparentdename });
        }
        if (context && context.srfparentkey) {
            Object.assign(searchFilter, { srfparentkey: JSON.parse(JSON.stringify(context)).srfparentkey });
        }
        if ((nodeJson as IPSDETreeDataSetNode)?.getSortPSAppDEField() && (nodeJson as IPSDETreeDataSetNode).getSortPSAppDEField()?.codeName && (nodeJson as IPSDETreeDataSetNode)?.sortDir) {
            Object.assign(searchFilter, { sort: `${(nodeJson as IPSDETreeDataSetNode).getSortPSAppDEField()?.codeName.toLowerCase()},${(nodeJson as IPSDETreeDataSetNode).sortDir.toLowerCase()}` });
        }
        const appDataEntity = nodeJson.getPSAppDataEntity() as IPSAppDataEntity;
        await appDataEntity.fill();
        let appEntityService = await this.globalService.getService(appDataEntity.codeName);
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
                    list = JSON.parse(JSON.stringify(data));
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
     * 处理节点关系导航上下文
     *
     * @param context 应用上下文
     * @param filter 参数
     * @param resNavContext 节点关系导航上下文
     *
     * @memberof AppTreeGridExService
     */
    public handleResNavContext(context: any, filter: any, resNavContext: any) {
        if (resNavContext && Object.keys(resNavContext).length > 0) {
            let tempContextData: any = JSON.parse(JSON.stringify(context));
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
 * 处理自定义节点关系导航数据
 *
 * @param context 应用上下文
 * @param viewparams 参数
 * @param curNavData 节点关系导航参数对象
 * @param tempData 返回数据
 * @param item 节点关系导航参数键值
 *
 * @memberof AppTreeGridExService
 */
    public handleCustomDataLogic(context: any, viewparams: any, curNavData: any, tempData: any, item: string) {
        // 直接值直接赋值
        if (curNavData.isRawValue) {
            if (Object.is(curNavData.value, 'null') || Object.is(curNavData.value, '')) {
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
     * 处理关系导航参数
     *
     * @param context 应用上下文
     * @param filter 参数
     * @param resNavParams 节点关系导航参数
     * @param resParams 节点关系参数
     *
     * @memberof AppTreeGridExService
     */
    public handleResNavParams(context: any, filter: any, resNavParams: any, resParams: any) {
        if (
            (resNavParams && Object.keys(resNavParams).length > 0) ||
            (resParams && Object.keys(resParams).length > 0)
        ) {
            let tempViewParamData: any = {};
            let tempViewParams: any = {};
            if (filter && filter.viewparams) {
                tempViewParams = filter.viewparams;
                tempViewParamData = JSON.parse(JSON.stringify(filter.viewparams));
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
     * 获取树节点关系导航上下文
     * 
     * @param noders 节点
     * @memberof AppGanttService
     */
    public getNavContext(noders: IPSDETreeNodeRS) {
        let context: any = {};
        let navcontext:any = noders.getPSNavigateContexts();
        if(navcontext?.length>0) {
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
     * @memberof AppGanttService
     */
    public getNavParams(noders: IPSDETreeNodeRS) {
        let params: any = {};
        let navparams:any = noders.getPSNavigateParams();
        if(navparams?.length>0) {
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
     * @memberof AppGanttService
     */
    public getParams(noders: IPSDETreeNodeRS) {
        let params: any = {};
        let reparams:any = noders?.getPSDETreeNodeRSParams();
        if(reparams?.length>0) {
            reparams.forEach((item: IPSDETreeNodeRSParam) => {
                params[item?.key] = {
                    value: item.value
                };
            })
        }
        return params;
    }
}