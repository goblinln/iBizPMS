import { IPSDETreeGridEx, IPSDETreeNode, IPSDETreeNodeDataItem, IPSDETreeDataSetNode, IPSAppDataEntity, IPSAppDEField, IPSAppDERS } from '@ibiz/dynamic-model-api';

export class AppTreeGridExModel {

    public treeGridExInstance!: IPSDETreeGridEx;

    /**
	 * 日历项类型
	 *
	 * @returns {any[]}
	 * @memberof AppTreeGridExModel
	 */
    public itemType: string = "";

    /**
    * Creates an instance of AppTreeGridExModel.
    * 
    * @param {*} [opts={}]
    * @memberof AppGridModel
    */
    constructor(opts: any) {
        this.treeGridExInstance = opts;
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
        const allTreeNodes = this.treeGridExInstance.getPSDETreeNodes() || [];
        if(allTreeNodes?.length>0) {
            let tempModel: Array<any> = [];
            allTreeNodes.forEach((node: IPSDETreeNode) => {
                if(node.treeNodeType == 'DE') {
                    if((node as IPSDETreeDataSetNode)?.getIdPSAppDEField()) {
                        tempModel.push({
                            name: 'id',
                            prop: (node as IPSDETreeDataSetNode)?.getIdPSAppDEField()?.codeName.toLowerCase()
                        })
                    }
                    let nodeDataItems:any = node.getPSDETreeNodeDataItems();
                    if(nodeDataItems?.length>0) {
                        nodeDataItems.forEach((dataItem: IPSDETreeNodeDataItem) => {
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