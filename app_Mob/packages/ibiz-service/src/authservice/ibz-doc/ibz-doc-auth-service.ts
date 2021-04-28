import { IBzDocAuthServiceBase } from './ibz-doc-auth-service-base';


/**
 * 文档权限服务对象
 *
 * @export
 * @class IBzDocAuthService
 * @extends {IBzDocAuthServiceBase}
 */
export default class IBzDocAuthService extends IBzDocAuthServiceBase {

    /**
     * Creates an instance of  IBzDocAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBzDocAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}