import { IBizMdViewModel } from './ibiz-md-view-model';

export class IBizWFDynaStartViewModel extends IBizMdViewModel {

    /**
     * 支持流程
     * 
     * @readonly
     * @memberof IBizWFDynaStartViewModel
     */
    get enableWF() {
        return this.viewModelData.enableWF;
    }

    /**
     * 流程交互模式
     * 
     * @readonly
     * @memberof IBizWFDynaStartViewModel
     */
    get WFIAMode() {
        return this.viewModelData.wFIAMode;
    }

}