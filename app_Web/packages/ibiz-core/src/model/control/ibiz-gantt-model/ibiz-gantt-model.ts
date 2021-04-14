import { IBizMainControlModel } from '../ibiz-main-control-model';
import { DynamicService } from '../../../service';
import { IBizEntityModel } from '../../entity';

/**
 * 甘特图部件
 */
export class IBizGanttModel extends IBizMainControlModel {

    /**
     * 树节点集合
     * 
     * @type {Map<string, any>}
     * @memberof IBizGanttModel
     */
    private $treeNodeMap: Map<string, any> = new Map();

    /**
     * 开始时间数据项
     * 
     * @memberof IBizGanttModel
     */
    get beginDataItemName() {
        return this.controlModelData.beginDataItemName;
    }

    /**
     * 结束时间数据项
     * 
     * @memberof IBizGanttModel
     */
    get endDataItemName() {
        return this.controlModelData.endDataItemName;
    }

    /**
     * 编号数据项
     * 
     * @memberof IBizGanttModel
     */
    get sNDataItemName() {
        return this.controlModelData.sNDataItemName;
    }

    /**
     * 前置数据项
     * 
     * @memberof IBizGanttModel
     */
    get prevDataItemName() {
        return this.controlModelData.prevDataItemName;
    }

    /**
     * 总量数据项
     * 
     * @memberof IBizGanttModel
     */
    get totalDataItemName() {
        return this.controlModelData.totalDataItemName;
    }

    /**
     * 完成量数据项
     * 
     * @memberof IBizGanttModel
     */
    get finishDataItemName() {
        return this.controlModelData.finishDataItemName;
    }

    /**
     * 树表格列
     * 
     * @memberof IBizGanttModel
     */
    get treeColumns() {
        return this.controlModelData.getPSDETreeColumns;
    }

    /**
     * 树表格模式
     * 
     * @memberof IBizGanttModel
     */
    get treeGridMode() {
        return this.controlModelData.treeGridMode;
    }

    /**
     * 树节点关系集合
     * 
     * @memberof IBizGanttModel
     */
    get treeNodeRSs() {
        return this.controlModelData.getPSDETreeNodeRSs;
    }

    /**
     * 支持根选择
     * 
     * @memberof IBizGanttModel
     */
    get enableRootSelect() {
        return this.controlModelData.enableRootSelect;
    }

    /**
     * 默认输出图标
     * 
     * @memberof IBizGanttModel
     */
    get outputIconDefault() {
        return this.controlModelData.outputIconDefault;
    }

    /**
     * 默认支持搜索
     * 
     * @memberof IBizGanttModel
     */
    get enableSearchDefault() {
        return this.controlModelData.enableSearchDefault;
    }

    /**
     * 显示根节点
     * 
     * @memberof IBizGanttModel
     */
    get rootVisible() {
        return this.controlModelData.rootVisible;
    }

    /**
     * 只读
     * 
     * @memberof IBizGanttModel
     */
    get readOnly() {
        return this.controlModelData.readOnly;
    }

    /**
     * 加载模型数据
     * 
     * @memberof IBizGanttModel
     */
    public async loaded() {
        await super.loaded();
        await this.initTreeNodes();
    }

    /**
     * 初始化树节点
     * 
     * @memberof IBizGanttModel
     */
    public async initTreeNodes() {
        if(this.controlModelData.getPSDETreeNodes?.length>0) {
            for(const item of this.controlModelData.getPSDETreeNodes) {
                if(item.getPSAppDataEntity) {
                    const targetAppEntity: any = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(item.getPSAppDataEntity.path);
                    item.$appDataEntity = new IBizEntityModel(targetAppEntity);
                }
                if(item.getPSCodeList) {
                    const targetCodeList: any = await DynamicService.getInstance(this.context).getAppCodeListJsonData(item.getPSCodeList.path);
                    Object.assign(item.getPSCodeList, targetCodeList);
                }
                this.$treeNodeMap.set(item.id.toLowerCase(), item);
            }
        }
    }

    /**
     * 获取树节点
     * 
     * @memberof IBizGanttModel
     */
    get treeNodes() {
        return [...this.$treeNodeMap.values()];
    }

    /**
     * 获取根节点
     * 
     * @memberof IBizGanttModel
     */
    get rootNode() {
        if(this.treeNodes?.length>0) {
            let rootNode: any = this.treeNodes.find((node: any) => {
                return node.rootNode;
            })
            if(rootNode) {
                return rootNode;
            }
        }
    }

}