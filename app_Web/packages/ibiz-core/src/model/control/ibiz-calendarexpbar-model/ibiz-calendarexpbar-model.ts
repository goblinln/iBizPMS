import { DynamicService } from '../../../service';
import { IBizExpBarModel } from '../ibiz-exp-bar-model/ibiz-exp-bar-model';
/**
 * 日历导航部件
 * 
 * 
 */
export class IBizCalendarExpBarModel extends IBizExpBarModel {

    /**
     * 工具栏
     * 
     * @memberof IBizCalendarExpBarModel
     */
    private $toolBar: any = {};

    /**
     * 加载日历部件
     * 
     * @memberof IBizCalendarExpBarModel
     */
    public async loaded() {
        await super.loaded();
        this.$toolBar = this.controls.find((item: any)=>{
            return item.controlType == 'TOOLBAR';
        })
    }

    /**
     * 导航工具栏
     * 
     * @memberof IBizCalendarExpBarModel
     */
    get toolBar() {
        return this.$toolBar;
    }
}