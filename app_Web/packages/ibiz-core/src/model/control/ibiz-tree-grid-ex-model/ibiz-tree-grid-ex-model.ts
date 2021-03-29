import { IBizMainControlModel } from '../ibiz-main-control-model';
import { DynamicService } from '../../../service';
import { IBizCodeListModel } from '../../common/ibiz-codelist-model';
/**
 * 树表格部件
 */
export class IBizTreeGridExModel extends IBizMainControlModel {

    /**
     * 树表格列
     */
    get treeColumns() {
        return this.controlModelData?.getPSDETreeColumns ? this.controlModelData.getPSDETreeColumns : []
    }

    /**
     * 所有树节点
     */
    get allTreeNodes() {
        return this.controlModelData?.getPSDETreeNodes;
    }

    /**
     * 根节点
     */
    get rootNode() {
        return this.allTreeNodes.find((item: any) => { return item.rootNode })
    }

    /**
     * 通过节点id返回节点
     */
    public getTreeNodeByNodeId(id: string) {
        return this.allTreeNodes.find((item: any) => { return Object.is(item.id, id) })
    }

    /**
     * 返回allTreeNodeRS
     */
    get allTreeNodeRS() {
        return this.controlModelData?.getPSDETreeNodeRSs;
    }

    /**
     * getTreeNodefromTreeNodeRS
     */
    public getTreeNodefromTreeNodeRS(id: string) {
        return this.allTreeNodeRS.find((item: any) => { return Object.is(item?.getChildPSDETreeNode?.id, id) })
    }

    public async loaded() {
        await super.loaded();
        this.allTreeNodes?.forEach(async (item: any) => {
            if (Object.is(item.treeNodeType, 'DE') && item.getPSAppDataEntity?.modelref) {
                const res = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(item.getPSAppDataEntity.path);
                Object.assign(item.getPSAppDataEntity, res);
            }
            // 加载并创建代码表对象
            if (item?.getPSCodeList?.modelref === true && item.getPSCodeList?.path) {
                const targetCodeList: any = await DynamicService.getInstance(this.context).getAppCodeListJsonData(item.getPSCodeList.path);
                if (targetCodeList) {
                    Object.assign(item.getPSCodeList, targetCodeList);
                    delete item.getPSCodeList.modelref;
                    item.$codeList = new IBizCodeListModel(item.getPSCodeList,this.context);
                }
            }
        });
    }
}
