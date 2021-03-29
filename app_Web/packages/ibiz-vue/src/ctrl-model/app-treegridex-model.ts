import { IBizTreeGridExModel } from 'ibiz-core';

export class AppTreeGridExModel {

    public treeGridExInstance!: IBizTreeGridExModel;

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
        const { allTreeNodes } = this.treeGridExInstance;
        if(allTreeNodes?.length>0) {
            let tempModel: Array<any> = [];
            allTreeNodes.forEach((node: any) => {
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