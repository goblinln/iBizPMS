import { FormServiceBase } from '@/ibiz-core';
import { Util, HttpResponse } from '@/ibiz-core/utils';
import { MobWaitReadModel } from '@/app-core/ctrl-model/ibz-weekly/mob-wait-read-form-model';


/**
 * MobWaitRead 部件服务对象
 *
 * @export
 * @class MobWaitReadService
 * @extends {FormServiceBase}
 */
export class MobWaitReadService extends FormServiceBase {

    /**
     * 部件模型
     *
     * @protected
     * @type {MobWaitReadModel}
     * @memberof ControlServiceBase
     */
    protected model: MobWaitReadModel = new MobWaitReadModel();

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof MobWaitReadService
     */
    protected appDEName: string = 'ibzweekly';

    /**
     * 当前应用实体主键标识
     *
     * @protected
     * @type {string}
     * @memberof MobWaitReadService
     */
    protected appDeKey: string = 'ibzweeklyid';

    /**
     * 获取跨实体数据集合
     *
     * @param {string} serviceName 服务名称
     * @param {string} interfaceName 接口名称
     * @param {*} [context]
     * @param {*} [data]
     * @param {boolean} [isLoading]
     * @returns {Promise<any[]>}
     * @memberof  MobWaitReadService
     */
    public async getItems(serviceName: string, interfaceName: string, context?: any, data?: any, isLoading?: boolean): Promise<any[]> {
        return [];
    }

    /**
     * 合并配置的默认值
     *
     * @protected
     * @param {*} [response={}]
     * @memberof MobWaitReadService
     */
    public mergeDefaults(response:any = {}): void {
        if (response.data) {
        }
    }

}
// 默认导出
export default MobWaitReadService;