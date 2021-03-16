import { IBizMdViewModel } from './ibiz-md-view-model';

/**
 * 实体看板视图模型类
 * 
 * @class IBizKanbanViewModel
 */
export class IBizKanbanViewModel extends IBizMdViewModel {
    /**
     * 实体看板
     *
     * @memberof IBizKanbanViewModel
     */
    private $kanban: any = {};

    /**
     * 加载模型数据（部件）
     *
     * @memberof IBizKanbanViewModel
     */
    public async loaded() {
        await super.loaded();
        this.$kanban = this.getControlByName('kanban');
    }

    /**
     * 获取看板视图部件
     * 
     * @memberof IBizKanbanViewModel
     */
    get viewKanban(){
        return this.$kanban;
    }
}
