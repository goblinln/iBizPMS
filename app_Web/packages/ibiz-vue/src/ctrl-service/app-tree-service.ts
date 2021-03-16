import { AppTreeModel, Errorlog } from 'ibiz-vue';
import { ControlServiceBase, Http, Util, IBizTreeModel, ModelParser } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';

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
    public controlInstance!: IBizTreeModel;

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
    constructor(opts: any = {}) {
        super(opts);
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
    @Errorlog
    public async getNodes(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        let { srfparentkey, srfcat, srfnodeid, srfnodefilter, query } = data;
        srfnodefilter = query ? query : srfnodefilter;
        let list: any[] = [];
        let filter: any = {};
        const { rootNode } = this.controlInstance;
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
            viewparams: JSON.parse(JSON.stringify(data)).viewparams,
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
        let nodeJson: any = this.controlInstance.getTreeNodeByNodeType(strNodeType);
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
    @Errorlog
    public async fillChildNodes(nodeJson: any, context: any = {}, filter: any, list: any[]): Promise<any> {
        // 过滤出父节点是该节点的节点关系集合
        let nodeRSs: any[] = this.controlInstance.getTreeNodeRSs().filter((nodeRS: any) => {
            if (nodeRS?.getParentPSDETreeNode?.id !== nodeJson.id) {
                return false;
            }
            // 搜索模式 1 有搜索时启用， 2 无搜索时启用， 3 全部启用
            switch (nodeRS.searchMode) {
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
                let nodeJson: any = this.controlInstance.getTreeNodeByNodeType(nodeRSs[i]?.getChildPSDETreeNode?.id);
                if (nodeJson) {
                    await this.fillNodeData(nodeJson, context, filter, list, rsNavContext, rsNavParams, rsParams);
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
    @Errorlog
    public fillNodeData(nodeJson: any, context: any = {}, filter: any, list: any[], rsNavContext?: any, rsNavParams?: any, rsParams?: any): Promise<any> {
        context = this.handleResNavContext(context, filter, rsNavContext);
        filter = this.handleResNavParams(context, filter, rsNavParams, rsParams);
        return new Promise((resolve: any, reject: any) => {
            // 静态节点
            if (nodeJson.treeNodeType == 'STATIC') {
                // 快速搜索过滤
                if (nodeJson.enableQuickSearch && filter.srfnodefilter && !Object.is(filter.srfnodefilter, "") && nodeJson.text?.toUpperCase().indexOf(filter.srfnodefilter.toUpperCase()) != -1) {
                    return resolve(list);
                }
                const treeNode: any = {
                    text: nodeJson.text,
                    nodeType: nodeJson.treeNodeType,
                    // 多语言 todo
                    isUseLangRes: false,
                    srfappctx: context,
                    srfmajortext: nodeJson.text,
                    enablecheck: nodeJson.enableCheck,
                    disabled: nodeJson.disableSelect,
                    expanded: nodeJson.expanded || filter.isAutoExpand,
                    leaf: !nodeJson.hasPSDETreeNodeRSs,
                    selected: nodeJson.selected,
                    navfilter: nodeJson.navFilter,
                    navigateContext: nodeJson.getPSNavigateContexts && ModelParser.getNavigateContext(nodeJson),
                    navigateParams: nodeJson.getPSNavigateParams && ModelParser.getNavigateParams(nodeJson),
                };

                let strNodeId: string = nodeJson.nodeType;
                // 处理静态节点值
                if (!Util.isEmpty(nodeJson.nodeValue)) {
                    Object.assign(treeNode, { srfkey: nodeJson.nodeValue });
                    strNodeId += this.TREENODE_SEPARATOR + nodeJson.nodeValue;
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
                if (nodeJson.getPSSysImage) {
                    if (nodeJson.getPSSysImage?.cssClass) {
                        Object.assign(treeNode, { iconcls: nodeJson.getPSSysImage.cssClass });
                    } else {
                        Object.assign(treeNode, { icon: nodeJson.getPSSysImage.imagePath });
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
            } else if (nodeJson.treeNodeType == 'CODELIST' && nodeJson?.$codeList) {
                let codeListItems: Array<any> = [];
                if (nodeJson.$codeList.codeListType == 'STATIC') {
                    codeListItems = nodeJson.$codeList.codeItems;
                } else if (nodeJson.$codeList.codeListType == 'DYNAMIC') {

                }
                let treeNodes: any = this.transFormCodeListData(codeListItems, context, filter, nodeJson);
                treeNodes.forEach((treeNode: any) => {
                    list.push(treeNode);
                });
                resolve(list)

                // 动态节点（实体）
            } else if (nodeJson.treeNodeType == 'DE' && nodeJson?.$appDataEntity) {
                let bFirst: boolean = true;
                let searchFilter: any = {};
                let nodeRSs: Array<any> = this.controlInstance.getTreeNodeRSs();
                if (nodeRSs?.length > 0) {
                    nodeRSs.forEach((noders: any) => {
                        if (noders.getChildPSDETreeNode?.id == nodeJson.id) {
                            let pickupfield: any =
                                noders.parentFilter
                                    ? noders.parentFilter.toLowerCase()
                                    : noders.getParentPSDER1N && noders.getParentPSAppDEField
                                        ? noders.getParentPSAppDEField.name.toLowerCase() : "";
                            if (pickupfield && !Object.is(pickupfield, "")) {
                                const tempNode: any = this.controlInstance.getTreeNodeByNodeType(noders.getParentPSDETreeNode?.id);
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
                                const deCodeName = nodeJson.$appDataEntity.codeName;
                                // 设置实体主键属性
                                let strId: string = '';
                                if (nodeJson.getIdPSAppDEField) {
                                    strId = entity[nodeJson.getIdPSAppDEField?.codeName.toLowerCase()];
                                } else {
                                    strId = entity[nodeJson.$appDataEntity?.keyField?.codeName.toLowerCase()];
                                }
                                // 设置实体主信息属性
                                let strText: string = '';
                                if (nodeJson.getTextPSAppDEField) {
                                    strText = entity[nodeJson.getTextPSAppDEField?.codeName.toLowerCase()];
                                } else {
                                    strText = entity[nodeJson.$appDataEntity?.majorField?.codeName.toLowerCase()];
                                }
                                Object.assign(treeNode, { srfparentdename: deCodeName, srfparentkey: strId });
                                let tempContext: any = JSON.parse(JSON.stringify(context));
                                Object.assign(tempContext, { srfparentdename: deCodeName, srfparentkey: strId, [deCodeName.toLowerCase()]: strId });
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
                                if (nodeJson.getPSSysImage?.cssClass) {
                                    Object.assign(treeNode, { iconcls: nodeJson.getPSSysImage.cssClass });
                                } else if (nodeJson.getPSSysImage?.imagePath) {
                                    Object.assign(treeNode, { icon: nodeJson.getPSSysImage.imagePath });
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
                                    Object.assign(treeNode, { expanded: filter.isAutoExpand });
                                }
                                Object.assign(treeNode, { leaf: !nodeJson.hasPSDETreeNodeRSs });
                                if (nodeJson.getLeafFlagPSDEField) {
                                    let objLeafFlag = entity[nodeJson.getLeafFlagPSDEField.codeName.toLowerCase()];
                                    if (objLeafFlag != null) {
                                        let strLeafFlag: string = objLeafFlag.toString().toLowerCase();
                                        if (Object.is(strLeafFlag, '1') || Object.is(strLeafFlag, 'true')) {
                                            Object.assign(treeNode, { leaf: true });
                                        }
                                    }
                                }
                                if (nodeJson.getPSDETreeNodeDataItems?.length > 0) {
                                    nodeJson.getPSDETreeNodeDataItems.forEach((item: any) => {
                                        if (item.getPSDEField) {
                                            Object.assign(treeNode, { [item.name.toLowerCase()]: entity[item.getPSDEField.codeName.toLowerCase()] });
                                        }
                                    });
                                }
                                Object.assign(treeNode, { selected: nodeJson.selected });
                                if (nodeJson.navFilter) {
                                    Object.assign(treeNode, { navfilter: nodeJson.navFilter });
                                }
                                Object.assign(treeNode, { curData: entity });
                                if (nodeJson.getPSNavigateContexts) {
                                    Object.assign(treeNode, { navigateContext: ModelParser.getNavigateContext(nodeJson) });
                                }
                                if (nodeJson.getPSNavigateParams) {
                                    Object.assign(treeNode, { navigateParams: ModelParser.getNavigateParams(nodeJson) });
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
    public async searchNodeData(nodeJson: any, context: any = {}, searchFilter: any, filter: any): Promise<any> {
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
        if (nodeJson.sortField && nodeJson.sortDir) {
            Object.assign(searchFilter, { sort: `${nodeJson.sortField.toLowerCase()},${nodeJson.sortDir.toLowerCase()}` });
        }
        let appEntityService = await this.globalService.getService(nodeJson.$appDataEntity.codeName);
        let list: any[] = [];
        if (appEntityService[nodeJson?.getPSAppDEDataSet?.id] && appEntityService[nodeJson.getPSAppDEDataSet.id] instanceof Function) {
            const response = await appEntityService[nodeJson.getPSAppDEDataSet.id](context, searchFilter, false);
            try {
                if (!response.status || response.status !== 200) {
                    console.error(`查询${nodeJson.getPSAppDEDataSet.id}数据集异常!`);
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
                console.error(`查询${nodeJson.getPSAppDEDataSet.id}数据集异常!`);
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
    public transFormCodeListData(codeItems: Array<any>, context: any, filter: any, nodeJson: any) {
        let treeNodes: Array<any> = [];
        let bFirst = true;
        if (codeItems?.length > 0) {
            for (const item of codeItems) {
                let node: any = {
                    srfappctx: context,
                    curData: item
                }
                if (nodeJson.$codeList.codeListType == 'STATIC') {
                    Object.assign(node, {
                        // todo 代码表多语言
                        text: item.text,
                        nodeType: 'STATIC',
                        // text: i18n.t(`codelist.${nodeJson.$codeList.codeName}.${item.value}`)
                        // isUseLangRes: true,
                    })
                } else {
                    Object.assign(node, { text: item.text, nodeType: nodeJson.treeNodeTye, appEntityName: nodeJson.$appDataEntity.codeName })
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
                if (nodeJson.getPSSysImage) {
                    if (nodeJson.getPSSysImage?.cssClass) {
                        Object.assign(node, { iconcls: nodeJson.getPSSysImage.cssClass });
                    } else {
                        Object.assign(node, { icon: nodeJson.getPSSysImage.imagePath });
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
                    Object.assign(node, { expanded: filter.isAutoExpand });
                }
                Object.assign(node, { selected: nodeJson.selected });
                if (nodeJson.navFilter) {
                    Object.assign(node, { navfilter: nodeJson.navFilter });
                }
                if (nodeJson.getPSNavigateContexts) {
                    Object.assign(node, { navigateContext: ModelParser.getNavigateContext(nodeJson) });
                }
                if (nodeJson.getPSNavigateParams) {
                    Object.assign(node, { navigateParams: ModelParser.getNavigateParams(nodeJson) });
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
    public getNavContext(noders: any) {
        let context: any = {};
        if (noders.getPSNavigateContexts?.length > 0) {
            noders.getPSNavigateContexts.forEach((item: any) => {
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
    public getNavParams(noders: any) {
        let params: any = {};
        if (noders.getPSNavigateParams?.length > 0) {
            noders.getPSNavigateParams.forEach((item: any) => {
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
    public getParams(noders: any) {
        let params: any = {};
        if (noders.getPSDETreeNodeRSParams?.length > 0) {
            noders.getPSDETreeNodeRSParams.forEach((item: any) => {
                params[item?.key] = {
                    value: item.value
                };
            })
        }
        return params;
    }
}
