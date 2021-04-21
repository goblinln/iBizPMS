import { ControlServiceBase, ModelTool } from 'ibiz-core';
import { IPSDEGantt, IPSDETreeNode, IPSDETreeNodeDataItem, IPSDETreeNodeRS, IPSNavigateContext, IPSNavigateParam, IPSDETreeNodeRSParam, IPSDETreeDataSetNode, IPSDETreeStaticNode, IPSDETreeCodeListNode } from '@ibiz/dynamic-model-api';
import { GlobalService } from 'ibiz-service';
import { AppGanttModel } from '../ctrl-model/app-gantt-model';

export class AppGanttService extends ControlServiceBase {

    /**
     * 甘特图实例对象
     *
     * @memberof AppGanttService
     */
    public controlInstance !: IPSDEGantt;

    /**
     * 实体数据服务对象
     *
     * @type {any}
     * @memberof AppGanttService
     */
    public appEntityService!: any;

    /**
     * 节点实体服务集合
     *
     * @type {Map<string, any>}
     * @memberof AppGanttService
     */
    private $nodeEntityServiceMap: Map<string, any> = new Map();

    /**
     * 节点分隔符集合
     * 
     * @type {Map<string, any>}
     * @memberof AppGanttService
     */
    private $treeNodeDelimiterMap: Map<string, any> = new Map();

    /**
     * 节点数据模型
     *
     * @type {any}
     * @memberof AppGanttService
     */
    public dataModel: any = {};

    /**
     * 初始化服务参数
     *
     * @type {boolean}
     * @memberof AppGanttService
     */
    public async initServiceParam() {
        this.appEntityService = await new GlobalService().getService(this.appDeCodeName);
        await this.initTreeNodeEntityService();
    }

    /**
     * Creates an instance of AppGanttService.
     * 
     * @param {*} [opts={}]
     * @memberof AppGanttService
     */
    constructor(opts: any = {}) {
        super(opts);
        this.controlInstance = opts;
        this.model = new AppGanttModel(opts);
        this.initServiceParam();
        this.initDelimiter();
        this.initDataModel();
    }

    /**
     * 初始化树节点实体服务集合
     * 
     * @memberof AppGanttService
     */
    public async initTreeNodeEntityService() {
        const treeNodes =  this.controlInstance.getPSDETreeNodes() || [];
        if(treeNodes?.length>0) {
            for(const node of treeNodes) {
                const treeNodeType = node.treeNodeType;
                const appde = node.getPSAppDataEntity();
                await appde?.fill();
                if(appde && appde.codeName && treeNodeType == "DE") {
                    let tempService: any = await new GlobalService().getService(appde?.codeName);
                    this.$nodeEntityServiceMap.set(appde?.codeName, tempService);
                }
            }
        }
    }

    /**
     * 初始化分隔符
     * 
     * @memberof AppGanttService
     */
    public initDelimiter() {
        //  分割项
        this.$treeNodeDelimiterMap.set("SEPARATOR", ';');
        const treeNodes =  this.controlInstance.getPSDETreeNodes() || [];
        if(treeNodes?.length>0) {
            treeNodes.forEach((node: IPSDETreeNode) => {
                if(node.nodeType) {
                    this.$treeNodeDelimiterMap.set(node.nodeType.toUpperCase(), node.nodeType);
                }
            })
        }
    }

    /**
     * 初始化节点数据模型
     * 
     * @memberof AppGanttService
     */
    public initDataModel() {
        const treeNodes =  this.controlInstance.getPSDETreeNodes() || [];
        let tempModel: any= {};
        if(!treeNodes || treeNodes.length == 0) {
            return;
        }
        treeNodes.forEach((node: IPSDETreeNode) => {
            const nodeType = node.nodeType;
            if(nodeType) {
                let content: any = {};
                const nodeDataItems:any = node.getPSDETreeNodeDataItems();
                if(nodeDataItems?.length>0) {
                    nodeDataItems.forEach((dataItem: IPSDETreeNodeDataItem) => {
                        if(dataItem.name == "barstyle") {
                            Object.assign(content, {
                                style: {
                                    prop: dataItem.getPSAppDEField()?.codeName?.toLowerCase(),
                                    default: dataItem.defaultValue ? dataItem.defaultValue : null
                                }
                            })
                        } else {
                            content[dataItem.name == "begin" ? "start" : dataItem.name] = {
                                prop: dataItem.getPSAppDEField()?.codeName?.toLowerCase(),
                                default: dataItem.defaultValue ? dataItem.defaultValue : null
                            }
                        }
                    })
                }
                tempModel[nodeType] = content;
            }
        });
        this.dataModel = {};
        Object.assign(this.dataModel, tempModel);
    }

    /**
     * 获取节点数据
     *
     * @param {string} action
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppGanttService
     */
    public async getNodes(context:any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let { srfparentkey, srfcat, srfnodeid, srfnodefilter, query }: { srfparentkey: string, srfcat: string, srfnodeid: string, srfnodefilter: string, query:string } = data;
        const { rootVisible } = this.controlInstance;
        const treeNodes =  this.controlInstance.getPSDETreeNodes() || [];
        const rootNode: any = treeNodes.find((node: IPSDETreeNode) => {
            return node.rootNode;
        })
        srfnodefilter = query ? query : srfnodefilter;
        let list: any[] = [];
        let filter: any = {};
        let srfotherkey: string = '';

        if (!srfnodeid || Object.is(srfnodeid, '#')) {
            if(rootVisible) {
                await this.fillNodes(rootNode, {
                    context: context,
                    filter: filter,
                    list: list
                });
                return Promise.resolve({ status: 200, data: list });
            } else {
                srfnodeid = this.$treeNodeDelimiterMap.get(rootNode?.nodeType.toUpperCase());
            }
        } else {
            srfotherkey = srfnodeid;
            srfnodeid = srfnodeid.split('_#_')[0];
        }
        let strTreeNodeId: string = srfnodeid;
        let strRealNodeId: string = '';
        let bRootSelect: boolean = false;
        let strNodeType: string | null = null;
        let strRootSelectNode: string = '';
        //TODO 待测试
        if(Object.is(strTreeNodeId, this.$treeNodeDelimiterMap.get(rootNode?.nodeType.toUpperCase()))) {
            strNodeType = this.$treeNodeDelimiterMap.get(rootNode?.nodeType.toUpperCase());
            if(srfparentkey) {
                strRealNodeId = srfparentkey;
            }
        } else {
            let nPos = strTreeNodeId.indexOf(this.$treeNodeDelimiterMap.get("SEPARATOR"));
            if(nPos === -1) {
                return Promise.reject({
                    status: 500,
                    data: {
                        title: "失败",
                        message: `树节点${strTreeNodeId}标识无效`
                    }
                });
            }
            strNodeType = strTreeNodeId.substring(0, nPos);
            strRealNodeId = strTreeNodeId.substring(nPos + 1);
        }
        Object.assign(filter,{
            srfparentkey: srfparentkey,
            srfcat: srfcat,
            srfnodefilter: srfnodefilter,
            strRealNodeId: strRealNodeId,
            srfnodeid: srfnodeid,
            srfotherkey: srfotherkey,
            strNodeType: strNodeType,
            viewparams: JSON.parse(JSON.stringify(data)).viewparams
        });
        // 分解节点标识
        let nodeid: string[] = strRealNodeId.split(this.$treeNodeDelimiterMap.get("SEPARATOR"));
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
        const node = treeNodes.find((_node: IPSDETreeNode) => {
            return _node.nodeType == strNodeType;
        });
        if(node) {
            await this.fillNodeChilds(node, context, filter, list);
            return Promise.resolve({ status:200, data: list });
        }
        return Promise.resolve({ status: 500, data: { title: '失败', message: `树节点标识无效` } });
    }

    /**
     * 获取节点数据
     * 
     * @param node 节点
     * @param param1 
     * @memberof AppGanttService
     */
    public async fillNodes(node: IPSDETreeNode, args:{context: any, filter: any, list: any[], rsNavContext?: any, rsNavParams?: any, rsParams?: any}): Promise<any> {
        let { context, filter, list, rsNavContext, rsNavParams, rsParams } = args;
        context = this.handleResNavContext(context, filter, rsNavContext);
        filter = this.handleResNavParams(context, filter, rsNavParams, rsParams);
        let response: any;
        if(node.treeNodeType == "STATIC") {
            response = await this.fillStaticNodes(node, args);
        } else if(node.treeNodeType == "CODELIST" && (node as IPSDETreeCodeListNode)?.getPSCodeList()) {
            //TODO  代码表待测试
            response = await this.fillCodeListNodes(node, args);
        } else if(node.treeNodeType == "DE" && node.getPSAppDataEntity()) {
            response = await this.fillDeNodes(node, args);
        }
        return new Promise((resolve: any) => resolve(response));
    }

    /**
     * 处理静态节点
     * 
     * @param node 节点
     * @param param1 
     * @memberof AppGanttService
     */
    public fillStaticNodes(node: IPSDETreeNode, args:{context: any, filter: any, list: any[], rsNavContext?: any, rsNavParams?: any, rsParams?: any}) {
        return new Promise((resolve:any,reject:any) => {
            let treeNode: any = {};
            let { context, filter, list, rsNavContext, rsNavParams, rsParams } = args;
            const hasPSDETreeNodeRSs = node?.hasPSDETreeNodeRSs();
            const nodeValue = (node as IPSDETreeStaticNode).nodeValue;
            const {
                nodeType,
                enableQuickSearch,
                appendPNodeId,
                getPSSysImage,
                enableCheck,
                disableSelect,
                expanded,
                selected } = node;
            //TODO  等待国际化
            Object.assign(treeNode, {
                text: (node as IPSDETreeStaticNode)?.text,
                isUseLangRes: true,
                srfappctx: context,
                srfmajortext: (node as IPSDETreeStaticNode)?.text
            });
            if(enableQuickSearch && filter.srfnodefilter && !Object.is(filter.srfnodefilter,"")) {
                //TODO  国际化
                if((treeNode.text.toUpperCase().indexOf(filter.getSrfnodefilter().toUpperCase())==-1))
                    return Promise.reject();
            }
            let strNodeId: string = nodeType;
            if(nodeValue) {
                Object.assign(treeNode, {
                    srfkey: nodeValue
                });
                strNodeId += this.$treeNodeDelimiterMap.get("SEPARATOR");
                strNodeId += nodeValue;
                //TODO  待确认
                if(appendPNodeId) {
                    strNodeId += this.$treeNodeDelimiterMap.get("SEPARATOR");
                    strNodeId += filter.strRealNodeId;
                }
            } else {
                Object.assign(treeNode, { srfkey: filter.strRealNodeId });
                strNodeId += this.$treeNodeDelimiterMap.get("SEPARATOR");
                strNodeId += filter.strRealNodeId;
            }
            Object.assign(treeNode, { id: strNodeId + '_#_' + filter.srfnodeid });
            //  图标
            // if(getPSSysImage?.()?.cssClass) {
            //     Object.assign(treeNode, { iconcls: getPSSysImage?.()?.cssClass })
            // } else if(getPSSysImage?.()?.imagePath) {
            //     Object.assign(treeNode, { icon: getPSSysImage?.()?.imagePath })
            // }
            Object.assign(treeNode, {
                enablecheck: enableCheck ? true : false,
                disabled: disableSelect ? true : false,
                collapsed: !expanded ? true : false,
                leaf: !hasPSDETreeNodeRSs ? true : false,
                selected: selected ? true : false,
                //TODO 待补充
                // navfilter: node.getNavFilter,
                // navparams: node.getNavViewParam
                nodeid: treeNode.srfkey,
                nodeid2: filter.strRealNodeId
            });
            //TODO  待补充rooTreeNode
            const treeNodes =  this.controlInstance.getPSDETreeNodes() || [];
            const rootNode = treeNodes.find((node: IPSDETreeNode) => {
                return node.rootNode;
            })
            if(rootNode && Object.is(filter.srfnodeid, this.$treeNodeDelimiterMap.get(rootNode.nodeType.toUpperCase()))) {
                Object.assign(treeNode, { parentId: filter.srfotherkey });
            }
            list.push(treeNode);
            resolve(list);
        })
    }

    /**
     * 处理动态（代码表）节点
     * 
     * @param node 节点
     * @param param1 
     * @memberof AppGanttService
     */
    public async fillCodeListNodes(node: IPSDETreeNode, args:{context: any, filter: any, list: any[], rsNavContext?: any, rsNavParams?: any, rsParams?: any}) {
        const { context, filter, list } = args;
        const codeList = (node as IPSDETreeCodeListNode)?.getPSCodeList();
        const {
            nodeType,
            appendPNodeId,
            getPSSysImage,
            enableCheck,
            disableSelect,
            expanded,
            selected,
            enableQuickSearch
        } = node;
        const tempCodeList: any = {
            tag: codeList?.codeName,
            type: codeList?.codeListType,
            data: codeList
        };
        return new Promise((resolve: any, reject: any) => {
            this.codeListService.getDataItems(tempCodeList).then((codeListItems: Array<any>) => {
                if(codeListItems?.length>0) {
                    //TODO  待补充国际化
                    const handleChildData = (context: any, item: any) => {
                        if(codeList?.codeListType == "STATIC") {
                            Object.assign(item, {
                                text: item.text,
                                isUseLangRes: true
                            });
                        }
                        Object.assign(item, {
                            srfmajortext: item.text
                        });
                        let strNodeId: string = nodeType;
                        Object.assign(item, { srfkey: item.value });
                        strNodeId += this.$treeNodeDelimiterMap.get("SEPARATOR");
                        strNodeId += item.value;
                        if(appendPNodeId) {
                            strNodeId += this.$treeNodeDelimiterMap.get("SEPARATOR");
                            strNodeId +=  filter.getReanlnodeid;
                        }
                        Object.assign(item, {
                            id: strNodeId + '_#_' + filter.srfnodeid
                        });
                        // if(getPSSysImage?.()) {
                        //     if(getPSSysImage?.()?.cssClass) {
                        //         item["iconcls"] = getPSSysImage?.()?.cssClass;
                        //     } else if(getPSSysImage?.()?.imagePath) {
                        //         item["icon"] = getPSSysImage?.()?.imagePath;
                        //     }
                        // }
                        Object.assign(item, {
                            enablecheck: enableCheck ? true : false,
                            disabled: disableSelect ? true : false,
                            collapsed: !expanded ? true : false,
                            selected: selected ? true : false,
                            //TODO待确认
                            // navfilter: node.navFilter,
                            // navparams: node.navViewParam,
                            nodeid: item.srfkey,
                            nodeid2: item.pvalue
                        });
                    }
                    codeListItems = this.handleDataSet(JSON.parse(JSON.stringify(codeListItems)), context, handleChildData);
                    codeListItems.forEach((item: any) => {
                        let treeNode: any = {};
                        Object.assign(treeNode, { srfappctx: context });
                        if(codeList?.codeListType == "STATIC") {
                            Object.assign(treeNode, {
                                text: item.text,
                                isUseLangRes: true
                            });
                        } else {
                            Object.assign(treeNode, { text: item.text });
                        }
                        if(enableQuickSearch && filter.srfnodefilter && !Object.is(filter.srfnodefilter,"")) {
                            //TODO  国际化
                            if((treeNode.text.toUpperCase().indexOf(filter.getSrfnodefilter().toUpperCase())==-1))
                                return Promise.reject();
                        }
                        Object.assign(treeNode, {
                            srfmajortext: treeNode.text
                        });
                        let strNodeId: string = nodeType;
                        Object.assign(treeNode, { srfkey: item.value });
                        strNodeId += this.$treeNodeDelimiterMap.get("SEPARATOR");
                        strNodeId += item.value;
                        if(appendPNodeId) {
                            strNodeId += this.$treeNodeDelimiterMap.get("SEPARATOR");
                            strNodeId +=  filter.getReanlnodeid;
                        }
                        Object.assign(treeNode, {
                            id: strNodeId + '_#_' + filter.srfnodeid
                        });
                        // if(getPSSysImage()) {
                        //     if(getPSSysImage?.()?.cssClass) {
                        //         treeNode["iconcls"] = getPSSysImage?.()?.cssClass;
                        //     } else if(getPSSysImage?.()?.imagePath) {
                        //         treeNode["icon"] = getPSSysImage?.()?.imagePath;
                        //     }
                        // }
                        Object.assign(treeNode, {
                            enablecheck: enableCheck ? true : false,
                            disabled: disableSelect ? true : false,
                            collapsed: !expanded ? true : false,
                            selected: selected ? true : false,
                            //TODO待确认
                            // navfilter: node.navFilter,
                            // navparams: node.navViewParam,
                            nodeid: item.srfkey,
                            nodeid2: item.pvalue
                        });
                        if(item.children?.length>0) {
                            Object.assign(treeNode, { chidlren: item.children });
                        }
                        const treeNodes =  this.controlInstance.getPSDETreeNodes() || [];
                        const rootNode:any = treeNodes.find((node: IPSDETreeNode) => {
                            return node.rootNode;
                        })
                        if(!Object.is(filter.srfnodeid, this.$treeNodeDelimiterMap.get(rootNode.nodeType.toUpperCase()))) {
                            Object.assign(treeNode, { parentId: filter.srfotherkey });
                        }
                        list.push(treeNode);
                        resolve(list);
                    })
                } else {
                    resolve(list);
                }
            });  
        })
    }

    /**
     * 处理动态（实体）节点
     * 
     * @param node 节点
     * @param param1 
     * @memberof AppGanttService
     */
    public fillDeNodes(node: IPSDETreeNode, args:{context: any, filter: any, list: any[], rsNavContext?: any, rsNavParams?: any, rsParams?: any}) {
        return new Promise((resolve:any,reject:any) => {
            const { context, filter, list } = args;
            const appde = node.getPSAppDataEntity();
            const getIdPSAppDEField = (node as IPSDETreeDataSetNode)?.getIdPSAppDEField();
            const getTextPSAppDEField = (node as IPSDETreeDataSetNode)?.getTextPSAppDEField();
            const getLeafFlagPSDEField = (node as IPSDETreeDataSetNode)?.getLeafFlagPSAppDEField();
            const hasPSDETreeNodeRSs = node?.hasPSDETreeNodeRSs();
            const {
                id,
                enableQuickSearch,
                nodeType,
                getPSSysImage,
                enableCheck,
                expanded,
                disableSelect,
                selected,
                appendPNodeId,
            } = node;
            let searchFilter: any = {};
            let nodeRSs:any = this.controlInstance.getPSDETreeNodeRSs() || [];
            if(nodeRSs?.length>0) {
                nodeRSs.forEach((noders: IPSDETreeNodeRS) => {
                    if(noders.getChildPSDETreeNode()?.id == id) {
                        let pickupfield: any = 
                            noders.parentFilter 
                            ? noders.parentFilter.toLowerCase()
                            : noders.getParentPSDER1N() && noders.getParentPSAppDEField() 
                            ? noders.getParentPSAppDEField()?.name.toLowerCase() : "";
                        if(pickupfield && !Object.is(pickupfield, "")) {
                            let parentTreeNodeId:any = noders.getParentPSDETreeNode()?.id.toUpperCase()
                            if (Object.is(filter.strNodeType, this.$treeNodeDelimiterMap.get(parentTreeNodeId))) {
                                Object.assign(searchFilter, { [`n_${pickupfield}_eq`]: filter[`nodeid${noders.parentValueLevel>1 ? noders.parentValueLevel : ""}`] });
                            }
                        }
                    }
                })
            }
            Object.assign(searchFilter, { total: false });
            if (enableQuickSearch) {
                Object.assign(searchFilter, { query: filter.srfnodefilter });
            }
            let bFirst: boolean = true;
            let recors: any[] = [];
            try {
                // TODO  方法名待确认
                this.searchNodeType(node, context, searchFilter, filter).then((records: any) => {
                    if(records?.length>0) {
                        records.forEach((entity: any) => {
                            let treeNode: any = {};
                            //整理Context
                            let keyField:any = ModelTool.getAppEntityKeyField(appde);
                            let strId: string = getIdPSAppDEField ? entity[getIdPSAppDEField?.codeName.toLowerCase()] : entity[keyField?.codeName?.toLowerCase()];
                            let majorField:any = ModelTool.getAppEntityMajorField(appde);
                            let strText: string = getTextPSAppDEField ? entity[getTextPSAppDEField?.codeName.toLowerCase()] : entity[majorField?.codeName.toLowerCase()];
                            Object.assign(treeNode, {
                                srfparentdename: appde?.codeName,
                                srfparentkey: getIdPSAppDEField ? entity[getIdPSAppDEField?.codeName.toLowerCase()] : entity[keyField?.codeName.toLowerCase()]
                            });
                            let tempContext: any = JSON.parse(JSON.stringify(context));
                            Object.assign(tempContext, {
                                srfparentdename: appde?.codeName,
                                srfparentkey: getIdPSAppDEField ? entity[getIdPSAppDEField?.codeName.toLowerCase()] : entity[keyField?.codeName.toLowerCase()]
                            });
                            Object.assign(treeNode, {
                                srfappctx: tempContext,
                                srfkey: strId,
                                text: strText,
                                srfmajortext: strText
                            });
                            if(appde) {
                                treeNode[appde?.codeName.toLowerCase()] = strId;
                            }
                            let strNodeId: string = nodeType;
                            strNodeId += this.$treeNodeDelimiterMap.get("SEPARATOR");
                            strNodeId += strId;
                            if(appendPNodeId) {
                                strNodeId += this.$treeNodeDelimiterMap.get("SEPARATOR");
                                strNodeId += filter.strRealNodeId;
                            }
                            Object.assign(treeNode, { id: strNodeId + '_#_' + filter.srfnodeid });
                            //  图标 
                            // todo 图标接口使用报错
                            // if(getPSSysImage?.()?.cssClass) {
                            //     Object.assign(treeNode, { iconcls: getPSSysImage?.()?.cssClass })
                            // } else if(getPSSysImage?.()?.imagePath) {
                            //     Object.assign(treeNode, { icon: getPSSysImage?.()?.imagePath })
                            // }
                            Object.assign(treeNode, {
                                enablecheck: enableCheck ? true : false,
                                disabled: disableSelect ? true : false,
                                collapsed: !expanded ? true : false,
                                leaf: !hasPSDETreeNodeRSs ? true : false,
                                selected: selected ? true : false,
                                //TODO 待补充
                                // navfilter: node.getNavFilter,
                                // navparams: node.getNavViewParam
                                nodeid: treeNode.srfkey,
                                nodeid2: filter.strRealNodeId
                            });
                            const treeNodes =  this.controlInstance.getPSDETreeNodes() || [];
                            const rootNode: any = treeNodes.find((node: IPSDETreeNode) => {
                                return node.rootNode;
                            })
                            if(!Object.is(filter.srfnodeid, this.$treeNodeDelimiterMap.get(rootNode?.nodeType.toUpperCase()))) {
                                Object.assign(treeNode, { parentId: filter.srfotherkey });
                            }
                            if(getLeafFlagPSDEField) {
                                let objLeafFlag = entity[getLeafFlagPSDEField?.codeName?.toLowerCase()];
                                if(objLeafFlag != null) {
                                    let strLeafFlag: string = objLeafFlag.toString().toLowerCase();
                                    if (Object.is(strLeafFlag, '1') || Object.is(strLeafFlag, 'true')){
                                        Object.assign(treeNode, { leaf: true });
                                    }
                                }
                            }
                            for(let key in this.dataModel[nodeType]) {
                                let item = this.dataModel[nodeType][key];
                                let propVal: any = entity[item.prop];
                                try {
                                    let def: any = JSON.parse(item.default);
                                    propVal = propVal != null ? propVal : def.value;
                                    if (def.hasOwnProperty('bkcolor')) {
                                        Object.assign(treeNode.style, { base: { fill: def.bkcolor, stroke: def.bkcolor }});
                                    }
                                    if (def.hasOwnProperty('color')) {
                                        Object.assign(treeNode.style, { text: { color: def.color }});
                                    }
                                } catch(e) {
                                    propVal = propVal != null ? propVal : item.default
                                }
                                treeNode[key] = propVal
                            }
                            list.push(treeNode);
                            resolve(list);
                            bFirst = false;
                        });
                    } else {
                        resolve(list);
                    }
                })
            } catch (error) {
                console.log(error);
            }
        })
    }

    /**
     * 获取查询集合
     * 
     * @param node 节点
     * @param param1 
     * @memberof AppGanttService
     */
    public searchNodeType(node: IPSDETreeNode, context: any, searchFilter: any, filter: any) {
        return new Promise((resolve: any, reject: any) => {
            if(filter.viewparams) {
                Object.assign(searchFilter, filter.viewparams);
            }
            if(!searchFilter.page) {
                Object.assign(searchFilter, { page: 0 });
            }
            if(!searchFilter.size) {
                Object.assign(searchFilter, { size: 1000 });
            }
            if(context && context.srfparentdename){
                Object.assign(searchFilter,{srfparentdename:JSON.parse(JSON.stringify(context)).srfparentdename});
            }
            if(context && context.srfparentkey){
                Object.assign(searchFilter,{srfparentkey:JSON.parse(JSON.stringify(context)).srfparentkey});
            }
            const appde = node.getPSAppDataEntity();
            const sortField= (node as IPSDETreeDataSetNode)?.getSortPSAppDEField();
            const sortDir = (node as IPSDETreeDataSetNode)?.sortDir;
            const dataSet = (node as IPSDETreeDataSetNode)?.getPSAppDEDataSet();
            if(sortField && sortField.codeName && sortDir) {
                Object.assign(searchFilter,{sort: `${sortField.codeName.toLowerCase()},${sortDir.toLowerCase()}`});
            }
            if(appde && appde.codeName) {
              let _appEntityService: any = this.$nodeEntityServiceMap.get(appde.codeName);
              let list: any[] = [];
                if(_appEntityService && _appEntityService[dataSet?.codeName as string] && _appEntityService[dataSet?.codeName as string] instanceof Function) {
                    const response: Promise<any> = _appEntityService[dataSet?.codeName as string](context, searchFilter, false);
                    response.then((response: any) => {
                        if (!response.status || response.status !== 200) {
                            resolve([]);
                            console.error(`查询${dataSet?.codeName}数据集异常!`);
                        }
                        const data: any = response.data;
                        if (Object.keys(data).length > 0) {
                            list = JSON.parse(JSON.stringify(data));
                            resolve(list);
                        } else {
                            resolve([]);
                        }
                    }).catch((response: any) => {
                            resolve([]);
                            console.error(`查询${dataSet?.codeName}数据集异常!`);
                    });
                }
            }
            
        })
    }

    /**
     * 填充树视图子节点
     * 
     * @param node 节点
     * @param param1 
     * @memberof AppGanttService
     */
    public async fillNodeChilds(node: IPSDETreeNode, context: any = {}, filter: any, list: any[]) {
        const treeNodeRSs:any = this.controlInstance.getPSDETreeNodeRSs();
        if(treeNodeRSs?.length>0) {
            let nodeRs: any = {};
            if(filter.srfnodefilter && !Object.is(filter.srfnodefilter, "")) {
                nodeRs = treeNodeRSs.find((_item: IPSDETreeNodeRS) => {
                    let flag: boolean = false;
                    if(_item.getParentPSDETreeNode()?.id == node.id && (_item.searchMode == 1 || _item.searchMode == 3)) {
                        flag = true;
                    }
                    return flag;
                });
            } else {
                nodeRs = treeNodeRSs.find((_item: IPSDETreeNodeRS) => {
                    let flag: boolean = false;
                    if(_item.getParentPSDETreeNode()?.id == node.id && (_item.searchMode == 2 || _item.searchMode == 3)) {
                        flag = true;
                    }
                    return flag;
                });
            }
            if(nodeRs) {
                let rsNavContext: any = this.getNavContext(nodeRs);
                let rsNavParams: any = this.getNavParams(nodeRs);
                let rsParams: any = this.getParams(nodeRs);
                const treeNodes =  this.controlInstance.getPSDETreeNodes() || [];
                let treeNode: any = treeNodes.find((_node:  IPSDETreeNode) => {
                    return nodeRs.getChildPSDETreeNode()?.id == _node.id;
                });
                if(treeNode) {
                    await this.fillNodes(treeNode, {
                        context: context,
                        filter: filter,
                        list: list,
                        rsNavContext: rsNavContext,
                        rsNavParams: rsNavParams,
                        rsParams: rsParams
                    });
                }
            }
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

    /**
     * 处理代码表返回数据(树状结构)
     * 
     * @param result 返回数组
     * @param context 应用上下文
     * @param callBack 回调
     * @memberof AppGanttService
     */
    public handleDataSet(result:Array<any>,context:any,callBack:any){
        let list:Array<any> = [];
        if(result.length === 0){
            return list;
        }
        result.forEach((codeItem:any) =>{
            if(!codeItem.pvalue){
                let valueField:string = codeItem.value;
                this.setChildCodeItems(valueField,result,codeItem);
                list.push(codeItem);
            }
        })
        this.setNodeData(list,context,callBack);
        return list;
    }

    /**
     * 处理非根节点数据
     * 
     * @param result 返回数组
     * @param context 应用上下文
     * @param callBack 回调
     * @memberof AppGanttService
     */
    public setChildCodeItems(pValue:string,result:Array<any>,codeItem:any){
        result.forEach((item:any) =>{
            if(item.pvalue == pValue){
                let valueField:string = item.value;
                this.setChildCodeItems(valueField,result,item);
                if(!codeItem.children){
                    codeItem.children = [];
                }
                codeItem.children.push(item);
            }
        })
    }

    /**
     * 设置节点UI数据
     * 
     * @param result 返回数组
     * @param context 应用上下文
     * @param callBack 回调
     * @memberof AppGanttService
     */
    public setNodeData(result:Array<any>,context:any,callBack:any){
        result.forEach((item:any) =>{
            if(item.children){
                item.leaf = false;
                this.setNodeData(item.children,context,callBack);
            }else{
                item.leaf = true;
            }
            callBack(context,item);
        })
    }

    /**
     * 处理节点关系导航上下文
     *
     * @param context 应用上下文
     * @param filter 参数 
     * @param resNavContext 节点关系导航上下文
     *
     * @memberof AppGanttService
     */
    public handleResNavContext(context:any,filter:any,resNavContext:any){
        if(resNavContext && Object.keys(resNavContext).length > 0){
            let tempContextData:any = JSON.parse(JSON.stringify(context));
            let tempViewParams:any = {};
            if(filter && filter.viewparams){
                tempViewParams = filter.viewparams;
            }
            Object.keys(resNavContext).forEach((item:any) =>{
                let curDataObj:any = resNavContext[item];
                this.handleCustomDataLogic(context,tempViewParams,curDataObj,tempContextData,item);
            })
            return tempContextData;
        }else{
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
     * @memberof AppGanttService
     */
	public handleResNavParams(context:any,filter:any,resNavParams:any,resParams:any){
        if((resNavParams && Object.keys(resNavParams).length >0) || (resParams && Object.keys(resParams).length >0)){
            let tempViewParamData:any = {};
            let tempViewParams:any = {};
            if(filter && filter.viewparams){
                tempViewParams = filter.viewparams;
                tempViewParamData = JSON.parse(JSON.stringify(filter.viewparams));
            }
            if( Object.keys(resNavParams).length > 0){
                Object.keys(resNavParams).forEach((item:any) =>{
                    let curDataObj:any = resNavParams[item];
                    this.handleCustomDataLogic(context,tempViewParams,curDataObj,tempViewParamData,item);
                })
            }
            if( Object.keys(resParams).length > 0){
                Object.keys(resParams).forEach((item:any) =>{
                    let curDataObj:any = resParams[item];
                    tempViewParamData[item.toLowerCase()] = curDataObj.value;
                })
            }
            Object.assign(filter,{viewparams:tempViewParamData});
            return filter;
        }else{
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
     * @memberof AppGanttService
     */
	public handleCustomDataLogic(context:any,viewparams:any,curNavData:any,tempData:any,item:string){
		// 直接值直接赋值
		if(curNavData.isRawValue){
			if(Object.is(curNavData.value,"null") || Object.is(curNavData.value,"")){
                Object.defineProperty(tempData, item.toLowerCase(), {
                    value: null,
                    writable : true,
                    enumerable : true,
                    configurable : true
                });
            }else{
                Object.defineProperty(tempData, item.toLowerCase(), {
                    value: curNavData.value,
                    writable : true,
                    enumerable : true,
                    configurable : true
                });
            }
		}else{
			// 先从导航上下文取数，没有再从导航参数（URL）取数，如果导航上下文和导航参数都没有则为null
			if(context[(curNavData.value).toLowerCase()] != null){
				Object.defineProperty(tempData, item.toLowerCase(), {
					value: context[(curNavData.value).toLowerCase()],
					writable : true,
					enumerable : true,
					configurable : true
				});
			}else{
				if(viewparams[(curNavData.value).toLowerCase()] != null){
					Object.defineProperty(tempData, item.toLowerCase(), {
						value: viewparams[(curNavData.value).toLowerCase()],
						writable : true,
						enumerable : true,
						configurable : true
					});
				}else{
					Object.defineProperty(tempData, item.toLowerCase(), {
						value: null,
						writable : true,
						enumerable : true,
						configurable : true
					});
				}
			}
		}
	}
}