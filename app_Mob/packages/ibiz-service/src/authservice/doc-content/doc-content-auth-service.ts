import { DocContentAuthServiceBase } from './doc-content-auth-service-base';


/**
 * 文档内容权限服务对象
 *
 * @export
 * @class DocContentAuthService
 * @extends {DocContentAuthServiceBase}
 */
export default class DocContentAuthService extends DocContentAuthServiceBase {

    /**
     * Creates an instance of  DocContentAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  DocContentAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}