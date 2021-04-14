import { DynamicService } from '../../../service';
import { IBizCodeListModel } from '../../common/ibiz-codelist-model';
import { IBizEntityModel } from '../../entity/ibiz-entity-model';
import { IBizMainControlModel } from '../ibiz-main-control-model';

/**
 * 树视图部件模型
 *
 * @export
 * @class IBizTreeModel
 */
export class IBizTreeModel extends IBizMainControlModel {

    /**
     * 根节点
     *
     * @private
     * @type {*}
     * @memberof IBizTreeModel
     */
    private $rootNode!: any;

    /**
     * Creates an instance of IBizTreeModel.
     * IBizTreeModel 实例
     * 
     * @param {*} [opts={}]
     * @memberof IBizTreeModel
     */
    constructor(opts: any = {}, viewRef?: any, parentRef?: any, runtimeData?: any) {
        super(opts, viewRef, parentRef, runtimeData);
        Object.defineProperty(this, '$rootNode', { enumerable: false, writable: true })
        this.initTree()
    }

    /**
     * 初始化树视图部件
     *
     * @protected
     * @memberof IBizTreeModel
     */
    protected initTree() {
        this.$rootNode = this.getAllTreeNodes()?.find((node: any) => {
            return node?.rootNode;
        })
    }

    /**
     * 加载模型数据(应用实体)
     *
     * @memberof IBizMainControlModel
     */
    public async loaded() {
        await super.loaded();
        if (this.getAllTreeNodes()?.length > 0) {
            for (const node of this.getAllTreeNodes()) {
                // 加载并创建实体对象
                if (node?.getPSAppDataEntity?.modelref === true && node.getPSAppDataEntity?.path) {
                    const targetAppEntity: any = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(node.getPSAppDataEntity.path);
                    if (targetAppEntity) {
                        Object.assign(node.getPSAppDataEntity, targetAppEntity);
                        delete node.getPSAppDataEntity.modelref;
                        node.$appDataEntity = new IBizEntityModel(node.getPSAppDataEntity);
                    }
                }
                // 加载并创建代码表对象
                if (node?.getPSCodeList?.modelref === true && node.getPSCodeList?.path) {
                    const targetCodeList: any = await DynamicService.getInstance(this.context).getAppCodeListJsonData(node.getPSCodeList.path);
                    if (targetCodeList) {
                        Object.assign(node.getPSCodeList, targetCodeList);
                        delete node.getPSCodeList.modelref;
                        node.$codeList = new IBizCodeListModel(node.getPSCodeList, this.context);
                    }
                }
            }
        }
    }

    /**
     * 是否默认输出图标
     *
     * @readonly
     * @memberof IBizTreeModel
     */
    get outputIconDefault() {
        return this.controlModelData.outputIconDefault;
    }

    /**
     * 是否显示根节点
     *
     * @readonly
     * @memberof IBizTreeModel
     */
    get rootVisible() {
        return this.controlModelData.rootVisible;
    }

    /**
     * 获取树的根节点
     *
     * @readonly
     * @memberof IBizTreeModel
     */
    get rootNode() {
        return this.$rootNode;
    }

    get isSingleSelect() {
        return this.controlModelData.isSingleSelect;
    }

    /**
     * 获取所有的节点集合
     *
     * @returns
     * @memberof IBizTreeModel
     */
    public getAllTreeNodes() {
        return this.controlModelData.getPSDETreeNodes;
    }

    /**
     * 获取节点关系集合
     *
     * @returns
     * @memberof IBizTreeModel
     */
    public getTreeNodeRSs() {
        return this.controlModelData.getPSDETreeNodeRSs;
    }

    /**
     * 通过节点类型查找节点
     *
     * @param {string} nodeType
     * @memberof IBizTreeModel
     */
    public getTreeNodeByNodeType(nodeType: string) {
        return this.getAllTreeNodes()?.find((node: any) => nodeType == node.nodeType);
    }

}