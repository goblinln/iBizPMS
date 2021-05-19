import { CodeListServiceBase, Util } from "ibiz-core";
import { MDControlBase } from "./md-control-base";
import { AppTreeGridExService } from '../ctrl-service';
import { IPSDETreeGridEx, IPSDETreeNode, IPSDETreeNodeDataItem, IPSDETreeColumn } from '@ibiz/dynamic-model-api';

/**
 * 树表格部件基类
 *
 * @export
 * @class TreeControlBase
 * @extends {MDControlBase}
 */
export class TreeGridExControlBase extends MDControlBase {

    /**
     * 部件模型实例对象
     *
     * @type {*}
     * @memberof AppDefaultTreeGridExView
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
     * @memberof GanttControlBase
     */
    public codeListData: Map<string, any> = new Map();

    /**
     * 初始化列代码表
     * 
     * @public
     * @memberof GanttControlBase
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
                          let items = await this.codeListService.getDataItems({ type: codelistJson.codeListType, tag: codelistJson.codeName,data:codelistJson });
                          this.codeListData.set(dataItem.name,items);
                      }
                    }
                }
            }
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
     * 搜索获取日程事件
     *
     * @param {*} $event 日期信息
     * @memberof TreeGridExControlBase
     */
    public load(task: any = {}, resolve?: any) {
        const params: any = {
            srfnodeid: task && task.id ? task.id : "#",
            srfnodefilter: this.srfnodefilter,
        };
        let tempViewParams: any = JSON.parse(JSON.stringify(this.viewparams));
        let curNode: any = {};
        Util.deepObjectMerge(curNode, task);
        let tempContext: any = this.computecurNodeContext(curNode);
        if (curNode && curNode.srfparentdename) {
            Object.assign(tempContext, { srfparentdename: curNode.srfparentdename });
            Object.assign(tempViewParams, { srfparentdename: curNode.srfparentdename });
        }
        if (curNode && curNode.srfparentkey) {
            Object.assign(tempContext, { srfparentkey: curNode.srfparentkey });
            Object.assign(tempViewParams, { srfparentkey: curNode.srfparentkey });
        }
        Object.assign(params, { viewparams: tempViewParams });
        this.ctrlBeginLoading();
        this.service.getNodes(tempContext, params).then((response: any) => {
            this.ctrlEndLoading();
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
            this.ctrlEndLoading();
            this.$throw(response,'load');
        });
    }

    /**
     * 加载节点
     *
     * @memberof TreeTable
     */
    public loadTreeNode(tree: any, treeNode: any, resolve: any) {
        this.load(tree, resolve);
    }

    /**
     * 计算当前节点的上下文
     *
     * @param {*} curNode 当前节点
     * @memberof AppDefaultTreeGridExView
     */
    public computecurNodeContext(curNode: any) {
        let tempContext: any = {};
        if (curNode?.srfappctx) {
            tempContext = JSON.parse(JSON.stringify(curNode.srfappctx));
        } else {
            tempContext = JSON.parse(JSON.stringify(this.context));
        }
        return tempContext;
    }

    /**
     * 刷新
     *
     * @memberof TreeGridExControlBase
     */
    public refresh(args?: any) {
        this.load();
    }

    /**
     * 选中的数据
     *
     * @returns {any[]}
     * @memberof TreeGridExControlBase
     */
    public selections: any[] = [];

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof TreeGridExControlBase
     */
    public getDatas(): any[] {
        return this.selections;
    }

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof TreeGridExControlBase
     */
    public getData(): any {
        return this.selections.length > 0 ? this.selections[0] : null;
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
                icons[0].click();
            }
        }
    }

    /**
     * 选中变化
     *
     * @returns
     * @memberof TreeGridExControlBase
     */
    public select($event: any) {
        if (!$event) {
            return;
        }
        this.selections = [JSON.parse(JSON.stringify($event))];
        this.ctrlEvent({ controlname: this.controlInstance.name, action: "selectionchange", data: this.selections });
    }

    /**
     * 初始化部件数据
     *
     * @memberof AppDefaultTree
     */
    public async ctrlModelInit() {
        await super.ctrlModelInit();
        this.service = new AppTreeGridExService(this.controlInstance);
        await this.initColumnsCodeList();
    }

    /**
     * 绘制列
     *
     * @returns
     * @memberof TreeGridExControlBase
     */
    public renderColumns() {
        const treeColumns:any = this.controlInstance.getPSDETreeColumns();
        return (
            treeColumns.map((column: IPSDETreeColumn) => {
                column.widthUnit != 'STAR'
                const props: any = {
                    'show-overflow-tooltip': true,
                    "label": column.caption,
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
    }

}
