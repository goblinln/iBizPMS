import { AuthService } from '@ibiz-core/auth-service/auth-service-base';


/**
 * 系统日志权限服务对象基类
 *
 * @export
 * @class ActionAuthServiceBase
 * @extends {AuthService}
 */
export default class ActionAuthServiceBase extends AuthService {

    /**
     * Creates an instance of  ActionAuthServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ActionAuthServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 根据当前数据获取实体操作标识
     *
     * @param {*} mainSateOPPrivs 传入数据操作标识
     * @returns {any}
     * @memberof ActionAuthServiceBase
     */
    public getOPPrivs(mainSateOPPrivs:any):any{
        let curDefaultOPPrivs:any = JSON.parse(JSON.stringify(this.defaultOPPrivs));
        if(mainSateOPPrivs){
            Object.assign(curDefaultOPPrivs,mainSateOPPrivs);
        }
        return curDefaultOPPrivs;
    }

}