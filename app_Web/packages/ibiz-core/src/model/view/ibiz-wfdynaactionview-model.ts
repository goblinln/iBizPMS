import { IBizMdViewModel } from './ibiz-md-view-model';

export class IBizWFDynaActionViewModel extends IBizMdViewModel {

    /**
     * 支持流程
     * 
     * @readonly
     * @memberof IBizWFDynaActionViewModel
     */
    get enableWF() {
        return this.viewModelData.enableWF;
    }

    /**
     * 流程交互模式
     * 
     * @readonly
     * @memberof IBizWFDynaActionViewModel
     */
    get WFIAMode() {
        return this.viewModelData.wFIAMode;
    }

}