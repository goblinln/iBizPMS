import { ControlServiceBase, Util, IBizTreeGridExModel, ModelParser } from 'ibiz-core';
import { Errorlog } from 'ibiz-vue';
import { GlobalService } from 'ibiz-service';
import { AppTreeGridExModel } from '../ctrl-model/app-treegridex-model';

/**
 * Main 部件服务对象
 *
 * @export
 * @class AppFormService
 */
export class AppTreeGridExService extends ControlServiceBase {

    public controlInstance: IBizTreeGridExModel;

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
    @Errorlog
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

        // 处理是否显示根节点
        if (!srfnodeid || Object.is(srfnodeid, '#')) {
            if (this.controlInstance.controlModelData.rootVisible) {
                await this.fillNodeData(this.controlInstance.rootNode, context, filter, list);
                return Promise.resolve({ status: 200, data: list });
            } else {
                srfnodeid = this.controlInstance.rootNode?.nodeType;
            }
        }

        let strTreeNodeId: string = srfnodeid;
        let strRealNodeId: string = '';
        let bRootSelect: boolean = false;
        let strNodeType: string | null = null;
        let strRootSelectNode: string = '';

        if (Object.is(strTreeNodeId, this.controlInstance.rootNode?.nodeType)) {
            strNodeType = this.controlInstance.rootNode?.nodeType;
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
        let nodeJson: any = this.controlInstance.allTreeNodes.find((node: any) => {
            return strNodeType == node.nodeType;
        });
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
    @Errorlog
    public async fillChildNodes(nodeJson: any, context: any = {}, filter: any, list: any[]): Promise<any> {
        let nodeRSs: any[] = this.controlInstance.controlModelData?.getPSDETreeNodeRSs.filter((nodeRS: any) => {
            // if(nodeRS?.parentPSDETreeNode?.id !== nodeJson?.id){
            if (nodeRS?.getParentPSDETreeNode?.id !== nodeJson?.id) {
                return false;
            }
            // 搜索模式 1 有搜索时启用， 2 无搜索时启用， 3 全部启用
            switch (nodeRS.searchMode) {
                case 1:
                    return !Util.isEmpty(filter.srfnodefilter)
                case 2:
                    return Util.isEmpty(filter.srfnodefilter)
                case 3:
                    return true
                default:
                    return false;
            }
        })
        if (nodeRSs && nodeRSs.length > 0) {
            for (let i = 0, len = nodeRSs.length; i < len; i++) {
                // 导航参数
                let rsNavContext: any = this.getNavContext(nodeRSs[i]);
                let rsNavParams: any = this.getNavParams(nodeRSs[i]);
                let rsParams: any = this.getParams(nodeRSs[i]);
                // 根据节点标识填充节点
                let nodeJson: any = this.controlInstance.getTreeNodeByNodeId(nodeRSs[i]?.getChildPSDETreeNode?.id);
                await this.fillNodeData(nodeJson, context, filter, list, rsNavContext, rsNavParams, rsParams);
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
    @Errorlog
    public fillNodeData(nodeJson: any, context: any = {}, filter: any, list: any[], rsNavContext?: any, rsNavParams?: any, rsParams?: any): Promise<any> {
        context = this.handleResNavContext(context, filter, rsNavContext);
        filter = this.handleResNavParams(context, filter, rsNavParams, rsParams);
        return new Promise((resolve: any, reject: any) => {
            // 静态节点
            if (nodeJson?.treeNodeType == 'STATIC') {
                // 快速搜索
                if (nodeJson.enableQuickSearch && filter.srfnodefilter && !Object.is(filter.srfnodefilter, "") && nodeJson.text?.toUpperCase().indexOf(filter.srfnodefilter.toUpperCase()) != -1) {
                    return Promise.reject();
                }
                const treeNode: any = {
                    text: nodeJson?.text,
                    nodeType: nodeJson?.treeNodeType,
                    isUseLangRes: true,
                    srfappctx: context,
                    srfmajortext: nodeJson?.text,
                    enablecheck: nodeJson?.enableCheck,
                    disabled: nodeJson?.disableSelect,
                    expanded: nodeJson?.expanded || filter.isAutoexpand,
                    leaf: nodeJson?.hasPSDETreeNodeRSs,
                    selected: nodeJson?.selected,
                    navfilter: nodeJson?.navFilter,
                };

                let strNodeId: string = nodeJson.nodeType;
                // 处理静态节点值
                if (!Util.isEmpty(nodeJson?.nodeValue)) {
                    Object.assign(treeNode, { srfkey: nodeJson.nodeValue });
                    strNodeId += this.TREENODE_SEPARATOR + nodeJson.nodeValue;
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
                if (nodeJson?.getPSSysImage) {
                    if (nodeJson?.getPSSysImage?.cssClass) {
                        Object.assign(treeNode, { iconcls: nodeJson?.getPSSysImage.cssClass });
                    } else {
                        Object.assign(treeNode, { icon: nodeJson?.getPSSysImage.imagePath });
                    }
                }

                // 补充nodeid和nodeid2
                Object.assign(treeNode, { nodeid: treeNode.srfkey, nodeid2: filter.strRealNodeId });
                list.push(treeNode);
                resolve(list);
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
            }
            else if (nodeJson?.treeNodeType == 'DE' && nodeJson.getPSAppDataEntity) {
                let searchFilter: any = {};
                let nodeRSs: Array<any> = this.controlInstance.allTreeNodeRS;
                if (nodeRSs?.length > 0) {
                    nodeRSs.forEach((noders: any) => {
                        if (noders.getChildPSDETreeNode?.id == nodeJson.id) {
                            let pickupfield: any =
                                noders.parentFilter
                                    ? noders.parentFilter.toLowerCase()
                                    : noders.getParentPSDER1N && noders.getParentPSAppDEField
                                        ? noders.getParentPSAppDEField.name.toLowerCase() : "";
                            if (pickupfield && !Object.is(pickupfield, "")) {
                                const tempNode: any = this.controlInstance.getTreeNodefromTreeNodeRS(noders.getParentPSDETreeNode?.id);
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
                                const deCodeName = nodeJson.getPSAppDataEntity.codeName;

                                // 整理context
                                // 设置实体主键属性
                                let strId: string = '';
                                if (nodeJson.getIdPSAppDEField) {
                                    strId = entity[nodeJson.getIdPSAppDEField?.codeName.toLowerCase()];
                                } else {
                                    strId = entity[nodeJson.getPSAppDataEntity?.keyField?.codeName.toLowerCase()];
                                }
                                // 设置实体主信息属性
                                let strText: string = '';
                                if (nodeJson.getTextPSAppDEField) {
                                    strText = entity[nodeJson.getTextPSAppDEField?.codeName.toLowerCase()];
                                } else {
                                    strText = entity[nodeJson.getPSAppDataEntity?.majorField?.codeName.toLowerCase()];
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
                                    Object.assign(treeNode, { expanded: filter.isautoexpand });
                                }
                                Object.assign(treeNode, { leaf: nodeJson.hasPSDETreeNodeRSs });
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
                    Object.assign(node, { text: item.text, nodeType: nodeJson.treeNodeTye, appEntityName: nodeJson.$appDataEntity.codeName.toLowerCase() })
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
                    Object.assign(node, { expanded: filter.isautoexpand });
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
        let appEntityService = await this.globalService.getService(nodeJson.getPSAppDataEntity.codeName);
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
     * @memberof AppGanttService
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
     * @memberof AppGanttService
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