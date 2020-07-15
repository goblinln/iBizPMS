import AuthService from '../auth-service';

/**
 * 模块权限服务对象基类
 *
 * @export
 * @class ModuleAuthServiceBase
 * @extends {AuthService}
 */
export default class ModuleAuthServiceBase extends AuthService {

    /**
     * Creates an instance of  ModuleAuthServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ModuleAuthServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 根据当前数据获取实体操作标识
     *
     * @param {*} mainSateOPPrivs 传入数据操作标识
     * @returns {any}
     * @memberof ModuleAuthServiceBase
     */
    public getOPPrivs(mainSateOPPrivs:any):any{
        let curDefaultOPPrivs:any = JSON.parse(JSON.stringify(this.defaultOPPrivs));
        if(mainSateOPPrivs){
            Object.assign(curDefaultOPPrivs,mainSateOPPrivs);
        }
        return curDefaultOPPrivs;
    }

}