import { LoadingServiceBase } from './loading-service-base';

/**
 * 部件加载服务类
 *
 * @export
 * @class CtrlLoadingService
 * @extends {LoadingServiceBase}
 */
export class CtrlLoadingService extends LoadingServiceBase {

    /**
     * 计算部件元素Id
     *
     * @private
     * @memberof CtrlLoadingService
     */
    private calcCtrlId(model: any) {
        return `#${model?.appDataEntity?.codeName + model?.codeName}control`;
    }

    /**
     * 部件加载
     *
     * @public
     * @memberof CtrlLoadingService
     */
    public beginLoading(controlInstance: any) {
        const selection = document.querySelector(this.calcCtrlId(controlInstance));
        if (!selection || this.isLoading) {
            return
        }
        super.beginLoading(selection);
    }
}