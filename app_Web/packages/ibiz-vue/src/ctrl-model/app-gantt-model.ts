import { IPSDEGantt, IPSDETreeNode, IPSDETreeNodeDataItem, IPSDETreeDataSetNode, IPSAppDataEntity, IPSAppDEField } from '@ibiz/dynamic-model-api';
import { ModelTool } from 'ibiz-core';

export class AppGanttModel {

    public ganttInstance!: IPSDEGantt;

    /**
	 * 日历项类型
	 *
	 * @returns {any[]}
	 * @memberof AppGanttModel
	 */
    public itemType: string = "";

    /**
    * Creates an instance of AppGanttModel.
    * 
    * @param {*} [opts={}]
    * @memberof AppGridModel
    */
    constructor(opts: any) {
        this.ganttInstance = opts;
    }

    
    public getDataItems() {
        let dataItems: any[] = [{
            name: 'srffrontuf',
            prop: 'srffrontuf',
            dataType: 'TEXT',
        }, {
            name: 'style',
        }, {
            name: 'textColor',
        }, {
             name: 'itemType',
        }, {
            name: 'parentId'
        }, {
            name: 'query',
            prop: 'query',
        }];
        const treeNodes = this.ganttInstance.getPSDETreeNodes() || [];
        if(treeNodes.length>0) {
            let tempModel: Array<any> = [];
            treeNodes.forEach((node: IPSDETreeNode) => {
                if(node.treeNodeType == 'DE') {
                    if((node as IPSDETreeDataSetNode)?.getIdPSAppDEField()) {
                        tempModel.push({
                            name: 'id',
                            prop: (node as IPSDETreeDataSetNode)?.getIdPSAppDEField()?.codeName.toLowerCase()
                        })
                    }
                    let nodeDataItems:any = node.getPSDETreeNodeDataItems;
                    if(nodeDataItems?.length>0) {
                        nodeDataItems?.forEach((dataItem: IPSDETreeNodeDataItem) => {
                            tempModel.push({
                                name: dataItem.name == 'begin' ? "start" : node.name.toLowerCase(),
                                prop: dataItem.getPSAppDEField()?.codeName.toLowerCase()
                            })
                        })
                    }
                }
            })
            dataItems = [...dataItems, ...tempModel];
        }
        return dataItems;
    }
}