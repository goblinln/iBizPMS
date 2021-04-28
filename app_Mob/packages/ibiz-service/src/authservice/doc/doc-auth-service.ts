import { DocAuthServiceBase } from './doc-auth-service-base';


/**
 * 文档权限服务对象
 *
 * @export
 * @class DocAuthService
 * @extends {DocAuthServiceBase}
 */
export default class DocAuthService extends DocAuthServiceBase {

    /**
     * Creates an instance of  DocAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  DocAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}