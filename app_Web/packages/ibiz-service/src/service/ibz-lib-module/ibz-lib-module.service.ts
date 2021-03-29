import { IbzLibModuleBaseService } from './ibz-lib-module-base.service';

/**
 * 用例库模块服务
 *
 * @export
 * @class IbzLibModuleService
 * @extends {IbzLibModuleBaseService}
 */
export class IbzLibModuleService extends IbzLibModuleBaseService {
    /**
     * Creates an instance of IbzLibModuleService.
     * @memberof IbzLibModuleService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzLibModuleService')) {
            return ___ibz___.sc.get('IbzLibModuleService');
        }
        ___ibz___.sc.set('IbzLibModuleService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzLibModuleService}
     * @memberof IbzLibModuleService
     */
    static getInstance(): IbzLibModuleService {
        if (!___ibz___.sc.has('IbzLibModuleService')) {
            new IbzLibModuleService();
        }
        return ___ibz___.sc.get('IbzLibModuleService');
    }
}
export default IbzLibModuleService;