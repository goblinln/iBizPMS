import { DocLibAuthServiceBase } from './doc-lib-auth-service-base';


/**
 * 文档库权限服务对象
 *
 * @export
 * @class DocLibAuthService
 * @extends {DocLibAuthServiceBase}
 */
export default class DocLibAuthService extends DocLibAuthServiceBase {

    /**
     * Creates an instance of  DocLibAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  DocLibAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}