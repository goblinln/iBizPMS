import { ControlServiceBase } from 'ibiz-core';
import { UtilService } from 'ibiz-service';

export class AppSearchBarService extends ControlServiceBase {

    /**
     * 工具服务对象
     *
     * @protected
     * @type {UtilService}
     * @memberof AppSearchBarService
     */
    protected utilService: UtilService = new UtilService();

    /**
     * 加载数据模型
     *
     * @param {string} serviceName
     * @param {*} context
     * @param {*} viewparams
     * @memberof AppSearchBarService
     */
    public loadModel(serviceName: string, context: any, viewparams: any) {
        return new Promise((resolve: any, reject: any) => {
            this.utilService.getService(serviceName).then((service: any) => {
                if(service) {
                    service.loadModelData(JSON.stringify(context), viewparams).then((response: any) => {
                        resolve(response);
                    }).catch((response: any) => {
                        reject(response);
                    });
                }
            }).catch((response: any) => {
                reject(response);
            });
        });
    }

    /**
     * 保存模型
     *
     * @param {string} serviceName
     * @param {*} context
     * @param {*} viewparams
     * @returns
     * @memberof AppSearchBarService
     */
    public saveModel(serviceName: string, context: any, viewparams: any) {
        return new Promise((resolve: any, reject: any) => {
            this.utilService.getService(serviceName).then((service: any) => {
                if(service) {
                    service.saveModelData(JSON.stringify(context), '', viewparams).then((response: any) => {
                        resolve(response);
                    }).catch((response: any) => {
                        reject(response);
                    });
                }
            }).catch((response: any) => {
                reject(response);
            });
        });
    }
}