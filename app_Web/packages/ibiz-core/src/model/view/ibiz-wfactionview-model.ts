import { IBizMdViewModel } from './ibiz-md-view-model';

export class IBizWFActionViewModel extends IBizMdViewModel {

    /**
     * 支持流程
     * 
     * @readonly
     * @memberof IBizWFActionViewModel
     */
    get enableWF() {
        return this.viewModelData.enableWF;
    }

    /**
     * 流程交互模式
     * 
     * @readonly
     * @memberof IBizWFActionViewModel
     */
    get WFIAMode() {
        return this.viewModelData.wFIAMode;
    }

}