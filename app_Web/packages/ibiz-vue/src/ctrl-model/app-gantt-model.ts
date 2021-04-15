import { IBizGanttModel } from 'ibiz-core';

export class AppGanttModel {

    public ganttInstance!: IBizGanttModel;

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
        const { treeNodes } = this.ganttInstance;
        if(treeNodes?.length>0) {
            let tempModel: Array<any> = [];
            treeNodes.forEach((node: any) => {
                if(node.treeNodeType == 'DE') {
                    if(node.getIdPSAppDEField) {
                        tempModel.push({
                            name: 'id',
                            prop: node.getIdPSAppDEField.codeName.toLowerCase()
                        })
                    }
                    if(node.getPSDETreeNodeDataItems?.length>0) {
                        node.getPSDETreeNodeDataItems.forEach((dataItem: any) => {
                            tempModel.push({
                                name: dataItem.name == 'begin' ? "start" : node.name.toLowerCase(),
                                prop: dataItem.getPSAppDEField?.codeName.toLowerCase()
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