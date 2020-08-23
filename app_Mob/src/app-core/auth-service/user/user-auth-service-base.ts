import { AuthService } from '@ibiz-core/auth-service/auth-service-base';


/**
 * 用户权限服务对象基类
 *
 * @export
 * @class UserAuthServiceBase
 * @extends {AuthService}
 */
export default class UserAuthServiceBase extends AuthService {

    /**
     * Creates an instance of  UserAuthServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  UserAuthServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 根据当前数据获取实体操作标识
     *
     * @param {*} mainSateOPPrivs 传入数据操作标识
     * @returns {any}
     * @memberof UserAuthServiceBase
     */
    public getOPPrivs(mainSateOPPrivs:any):any{
        let curDefaultOPPrivs:any = JSON.parse(JSON.stringify(this.defaultOPPrivs));
        if(mainSateOPPrivs){
            Object.assign(curDefaultOPPrivs,mainSateOPPrivs);
        }
        return curDefaultOPPrivs;
    }

}