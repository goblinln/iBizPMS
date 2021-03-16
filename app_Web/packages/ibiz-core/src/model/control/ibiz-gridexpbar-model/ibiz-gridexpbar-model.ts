import { DynamicService } from '../../../service';
import { IBizExpBarModel } from '../ibiz-exp-bar-model/ibiz-exp-bar-model';

export class IBizGridExpBarModel extends IBizExpBarModel {

    /**
     * 表格部件
     * 
     * @private
     * @type {*}
     * @memberof IBizGridExpBarModel
     */
    private $grid: any = {};

    /**
     * 工具栏部件
     * 
     * @private
     * @type {*}
     * @memberof IBizGridExpBarModel
     */
    private $toolbar: any = {};

    /**
     * 导航栏标题
     * 
     * @memberof IBizGridExpBarModel
     */
    get title() {
        return this.controlModelData.title;
    }

    /**
     * 视图对象引用
     * 
     * @memberof IBizGridExpBarModel
     */
    get getPSAppViewRefs() {
        return this.controlModelData.getPSAppViewRefs;
    }

    /**
     * 应用实体
     * 
     * @memberof IBizGridExpBarModel
     */
    get getPSAppDataEntity() {
        return this.controlModelData.getPSAppDataEntity;
    }

    /**
     * 事件
     * 
     * @memberof IBizGridExpBarModel
     */
    get hookEventNames() {
        return this.controlModelData.hookEventNames;
    }

    /**
     * 加载模型数据
     * 
     * @memberof IBizGridExpBarModel
     */
    public async loaded() {
        await super.loaded();
        if(this.controls?.length>0) {
            let grid: any = this.controls.find((item: any) => {
                return item.name == "gridexpbar_grid";
            });
            const targetGrid: any = await DynamicService.getInstance(this.context).getAppCtrlModelJsonData(grid.path);
            Object.assign(grid, targetGrid);
            grid.singleSelect = true;
            this.$grid = grid;
        }
    }

    /**
     * 获取表格部件
     * 
     * @memberof IBizGridExpBarModel
     */
    get grid() {
        return this.$grid;
    }

}