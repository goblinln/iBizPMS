import { CodeListServiceBase, ModelTool, TreeGridExControlInterface, Util } from "ibiz-core";
import { MDControlBase } from "./md-control-base";
import { AppTreeGridExService } from '../ctrl-service';
import { IPSDETreeGridEx, IPSDETreeNode, IPSDETreeNodeDataItem, IPSDETreeColumn } from '@ibiz/dynamic-model-api';

/**
 * 树表格部件基类
 *
 * @export
 * @class TreeGridExControlBase
 * @extends {MDControlBase}
 */
export class TreeGridExControlBase extends MDControlBase implements TreeGridExControlInterface{

    /**
     * 部件模型实例对象
     *
     * @type {*}
     * @memberof TreeGridExControlBase
     */
    public controlInstance!: IPSDETreeGridEx;

    /**
     * 数据集合
     *
     * @public
     * @type {any[]}
     * @memberof TreeGridExControlBase
     */
    public items: any[] = [];

    /**
     * 默认展开节点集合
     *
     * @memberof TreeGridExControlBase
     */
    public defExpands: any = [];

    /**
     * 行节点下标
     *
     * @memberof TreeGridExControlBase
     */
    public itemNum: any = {};

    /**
     * 计数下标
     *
     * @memberof TreeGridExControlBase
     */
    public num: number = 0;

    /**
     * 过滤属性
     *
     * @type {string}
     * @memberof TreeGridExControlBase
     */
    public srfnodefilter: string = '';

    /**
     * 代码表数据
     * 
     * @memberof TreeGridExControlBase
     */
    public codeListData: Map<string, any> = new Map();

    /**
     * 初始化部件数据
     *
     * @memberof TreeGridExControlBase
     */
    public async ctrlModelInit() {
        await super.ctrlModelInit();
        this.service = new AppTreeGridExService(this.controlInstance, this.context);
        await this.initColumnsCodeList();
    }

    /**
     * 初始化列代码表
     * 
     * @public
     * @memberof TreeGridExControlBase
     */
    public async initColumnsCodeList() {
        let _this: any = this;
        this.codeListData = new Map();
        this.codeListService = new CodeListServiceBase({ $store: _this.$store });
        const allTreeNodes = this.controlInstance.getPSDETreeNodes() || [];
        for (let i = 0; i < allTreeNodes.length; i++) {
            const dataItems = allTreeNodes[i].getPSDETreeNodeDataItems() || [];
            if (dataItems && dataItems.length > 0) {
                for (let j = 0; j < dataItems.length; j++) {
                    const dataItem = dataItems[j]
                    if(dataItem.getFrontPSCodeList && dataItem.getFrontPSCodeList()){
                      const codelistJson = dataItem.getFrontPSCodeList();
                      await codelistJson?.fill();
                      if(codelistJson){
                          let items = await this.codeListService.getDataItems({ type: codelistJson.codeListType, tag: codelistJson.codeName,data:codelistJson, context: this.context });
                          this.codeListData.set(dataItem.name,items);
                      }
                    }
                }
            }
        }
    }

    /**
     * 执行created后的逻辑
     *
     * @memberof TreeGridExControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
            if (!Object.is(tag, this.name)) {
                return;
            }
            if (Object.is('load', action)) {
                this.load(data);
            }
            if (Object.is('filter', action)) {
                this.srfnodefilter = data.srfnodefilter;
                this.refresh();
            }
        });
    }

    /**
     * 加载数据
     *
     * @param {*} [node={}] 节点数据
     * @param {*} [resolve] 渲染回调
     * @memberof TreeGridExControlBase
     */
    public load(node: any = {}, resolve?: any) {
        const params: any = {
            srfnodeid: node && node.id ? node.id : "#",
            srfnodefilter: this.srfnodefilter,
        };
        let tempViewParams: any = Util.deepCopy(this.viewparams);
        let curNode: any = {};
        Util.deepObjectMerge(curNode, node);
        let tempContext: any = this.computecurNodeContext(curNode);
        if (curNode && curNode.srfparentdename) {
            Object.assign(tempContext, { srfparentdename: curNode.srfparentdename });
            Object.assign(tempViewParams, { srfparentdename: curNode.srfparentdename });
        }
        if (curNode && curNode.srfparentdemapname) {
            Object.assign(tempContext, { srfparentdemapname: curNode.srfparentdemapname });
            Object.assign(tempViewParams, { srfparentdemapname: curNode.srfparentdemapname });
        }
        if (curNode && curNode.srfparentkey) {
            Object.assign(tempContext, { srfparentkey: curNode.srfparentkey });
            Object.assign(tempViewParams, { srfparentkey: curNode.srfparentkey });
        }
        Object.assign(params, { viewparams: tempViewParams });
        this.onControlRequset('load', tempContext, params);
        this.service.getNodes(tempContext, params).then((response: any) => {
            this.onControlResponse('load', response);
            if (!response || response.status !== 200) {
                this.$throw(response.info,'load');
                return;
            }
            response.data.forEach((item: any) => {
                this.itemNum[item.id] = this.num++;
                if (item.expanded) {
                    this.defExpands.push(item);
                }
            })
            if (resolve && resolve instanceof Function) {
                resolve(response.data);
            } else {
                this.items = [...response.data];
            }
            this.$nextTick(() => {
                this.expandDefNode();
            })
            this.ctrlEvent({ controlname: this.controlInstance.name, action: "load", data: this.items });
        }).catch((response: any) => {
            this.onControlResponse('load', response);
            this.$throw(response,'load');
        });
    }

    /**
     * 刷新
     *
     * @param {*} [args] 额外参数
     * @memberof TreeGridExControlBase
     */
    public refresh(args?: any) {
        this.load();
    }

    /**
     * 加载事件
     *
     * @param {*} row 行数据
     * @param {*} treeNode 节点信息
     * @param {*} resolve 渲染回调
     * @memberof TreeGridExControlBase
     */
    public loadTreeNode(row: any, treeNode: any, resolve: any) {
        this.load(row, resolve);
    }

    /**
     * 当前选中变化事件
     *
     * @param {*} $event 行数据
     * @return {*} 
     * @memberof TreeGridExControlBase
     */
    public select($event: any) {
        if (!$event) {
            return;
        }
        this.selections = [Util.deepCopy($event)];
        this.ctrlEvent({ controlname: this.controlInstance.name, action: "selectionchange", data: this.selections });
    }

    /**
     * 设置行Class
     *
     * @returns
     * @memberof TreeGridExControlBase
     */
    public setRowClass({ row, rowIndex }: { row: any, rowIndex: number }) {
        return 'treegrid' + this.itemNum[row.id];
    }

    /**
     * 展开默认节点
     *
     * @returns
     * @memberof TreeGridExControlBase
     */
    public expandDefNode() {
        if (this.defExpands.length > 0) {
            let item: any = this.defExpands[0];
            this.defExpands.splice(0, 1);
            let trs: any = this.$el.getElementsByClassName('treegrid' + this.itemNum[item.id]);
            if (trs && trs.length > 0) {
                let icons: any = trs[0].getElementsByClassName('el-table__expand-icon');
                icons[0]?.click();
            }
        }
    }

    /**
     * 绘制列
     *
     * @returns
     * @memberof TreeGridExControlBase
     */
    public renderColumns() {
        const treeColumns: IPSDETreeColumn[] = this.controlInstance.getPSDETreeColumns() || [];
        if (treeColumns.length > 0) {
            return (
                treeColumns.map((column: IPSDETreeColumn) => {
                    column.widthUnit != 'STAR'
                    const props: any = {
                        'show-overflow-tooltip': true,
                        "label": this.$tl(column.getCapPSLanguageRes()?.lanResTag,column.caption),
                        "align": column.align ? column.align.toLowerCase() : '',
                    }
                    if (column.widthUnit != 'STAR') {
                        props['width'] = column.width;
                    } else {
                        props['min-width'] = column.width;
                    }
                    return this.$createElement('el-table-column', {
                        props: props,
                        scopedSlots: {
                            default: (row: any) => {
                                if (column?.getRenderPSSysPFPlugin()) {
                                    let plugin:any = column.getRenderPSSysPFPlugin();
                                    const pluginInstance: any = this.PluginFactory.getPluginInstance("CONTROLITEM", plugin.pluginCode);
                                    if (pluginInstance) {
                                        return pluginInstance.renderCtrlItem(this.$createElement, column, this, row);
                                    }
                                } else {
                                    return (<span>{this.getColumnValue(row, column.name)}</span>)
                                }
                            },
                        }
                    });
                })
            )
        } else {
            const majorField = ModelTool.getAppEntityMajorField(this.controlInstance.getPSAppDataEntity());
            const props: any = {
                'show-overflow-tooltip': true,
                'label': majorField ? this.$tl(majorField.getLNPSLanguageRes()?.lanResTag, majorField.logicName) : this.$t('app.commonwords.srfmajortext'),
                'align': 'center',
                'width': 200
            };
            return this.$createElement('el-table-column', {
                props: props,
                scopedSlots: {
                    default: (row: any) => {
                        return <span>{row?.row?.['srfmajortext']}</span>
                    }
                }
            })
        }
    }

    /**
     * 获取列属性值
     *
     * @public
     * @memberof TreeGridExControlBase
     */
    public getColumnValue(task: any, field: string) {
        const { row } = task;
        if (row.curData) {
            Object.assign(row, row.curData)
        }
        const allTreeNodes = this.controlInstance.getPSDETreeNodes() || [];
        const node = allTreeNodes?.find((_node: IPSDETreeNode) => {
            return row.id.split(";")[0] == _node.nodeType;
        }) as IPSDETreeNode;
        const dataItems = node?.getPSDETreeNodeDataItems() || [];
        if (dataItems?.length > 0) {
            const dataItem = dataItems.find((_item: IPSDETreeNodeDataItem) => {
                return _item.name == field;
            });
            if (dataItem) {
                const codelistData = this.codeListData.get(dataItem.name);
                if (codelistData) {
                    const item = codelistData.find((item: any) => {
                        return item.value == row[field];
                    })
                    if (item) {
                        return item.text;
                    }
                }
            }
        }
        return row[field];
    }

    /**
     * 计算当前节点的上下文
     *
     * @param {*} curNode 当前节点
     * @memberof TreeGridExControlBase
     */
    public computecurNodeContext(curNode: any) {
        let tempContext: any = {};
        if (curNode?.srfappctx) {
            tempContext = Util.deepCopy(curNode.srfappctx);
        } else {
            tempContext = Util.deepCopy(this.context);
        }
        return tempContext;
    }

}
