import { BuildBaseService } from './build-base.service';

/**
 * 版本服务
 *
 * @export
 * @class BuildService
 * @extends {BuildBaseService}
 */
export class BuildService extends BuildBaseService {
    /**
     * Creates an instance of BuildService.
     * @memberof BuildService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('BuildService')) {
            return ___ibz___.sc.get('BuildService');
        }
        ___ibz___.sc.set('BuildService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {BuildService}
     * @memberof BuildService
     */
    static getInstance(): BuildService {
        if (!___ibz___.sc.has('BuildService')) {
            new BuildService();
        }
        return ___ibz___.sc.get('BuildService');
    }
}
export default BuildService;