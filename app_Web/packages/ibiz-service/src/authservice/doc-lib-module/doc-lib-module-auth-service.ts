import { DocLibModuleAuthServiceBase } from './doc-lib-module-auth-service-base';


/**
 * 文档库分类权限服务对象
 *
 * @export
 * @class DocLibModuleAuthService
 * @extends {DocLibModuleAuthServiceBase}
 */
export default class DocLibModuleAuthService extends DocLibModuleAuthServiceBase {

    /**
     * Creates an instance of  DocLibModuleAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  DocLibModuleAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}