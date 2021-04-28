import { FileAuthServiceBase } from './file-auth-service-base';


/**
 * 附件权限服务对象
 *
 * @export
 * @class FileAuthService
 * @extends {FileAuthServiceBase}
 */
export default class FileAuthService extends FileAuthServiceBase {

    /**
     * Creates an instance of  FileAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  FileAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}