import { AppServiceBase, AuthServiceBase } from 'ibiz-core';

/**
 * 实体权限服务
 *
 * @export
 * @class AuthService
 */
export class AuthService extends AuthServiceBase {

    /**
     * 默认操作标识
     *
     * @public
     * @type {(any)}
     * @memberof AuthService
     */
    public defaultOPPrivs: any = {CREATE: 1,DELETE: 1,DENY: 1,NONE: 1,READ: 1,SRFUR__RESOURCE1: 1,SRFUR__RESOURCE2: 1,UPDATE: 1,WFSTART: 1}; 

    /**
     * Creates an instance of AuthService.
     * 
     * @param {*} [opts={}]
     * @memberof AuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 注册系统操作标识统一资源
     *
     * @param {string} name 实体名称
     * @returns {Promise<any>}
     * @memberof AuthService
     */ 
    public registerSysOPPrivs(){
        this.sysOPPrivsMap.set('SRFUR__RESOURCE1','RESOURCE1');
        this.sysOPPrivsMap.set('SRFUR__RESOURCE2','RESOURCE2');
    }

}