import { DynamicService, Util } from '../../../..';
import { ViewFactory } from '../../../../utils';
import { IBizFormDetailModel } from './ibiz-form-detail-model';

/**
 * 数据关系界面模型
 *
 * @export
 * @class IBizFormDruipartModel
 * @extends {IBizFormDetailModel}
 */
export class IBizFormDruipartModel extends IBizFormDetailModel {

    private $appView : any = {};

    /**
     *Creates an instance of IBizFormDruipartModel.
     * @param {*} [{ formRef, parentRef, modelData }={}]
     * @memberof IBizFormDruipartModel
     */
    constructor({ formRef, parentRef, modelData, context }: any = {}) {
        super({ formRef, parentRef, modelData, context });
    }

    public async loaded() {
        let appView: any = this.detailModelData.getPSAppView;
        if (appView) {
            if(appView.modelref && appView.path) {
                const targetView = await DynamicService.getInstance(this.context).getAppViewModelJsonData(appView.path);
                Object.assign(appView, targetView);
                delete appView.modelref;
            }
            let appViewModel: any = ViewFactory.getInstance(appView, this.context);
            if(appViewModel) {
                await appViewModel.loadedAppDERSPathParam();
                this.$appView = appViewModel;
            }
        }
        const openView = this.form.getView();
        if (openView && openView.appDataEntity.modelref && openView.appDataEntity.path) {
            const res = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(openView.appDataEntity.path);
            Object.assign(openView.appDataEntity, res)
        }
    }

    get appView() {
        return this.detailModelData.getPSAppView;
    }

    get parentdata(){
        return this.detailModelData.parentDataJO;
    }

    get localContext(){
        return this.detailModelData.getPSNavigateContexts;
    }

    get localParam(){
        return this.detailModelData.getPSNavigateParams;
    }

    get appDERSPaths(){
        return this.$appView.getPSAppDERSPaths;
    }

    /**
     * 关系界面高度
     * 
     * @readonly
     * @memberof IBizFormDruipartModel
     */
    get height() {
        return this.detailModelData.height;
    }
}