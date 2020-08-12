import AuthService from '../auth-service';

/**
 * 发布权限服务对象基类
 *
 * @export
 * @class ReleaseAuthServiceBase
 * @extends {AuthService}
 */
export default class ReleaseAuthServiceBase extends AuthService {

    /**
     * Creates an instance of  ReleaseAuthServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ReleaseAuthServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 根据当前数据获取实体操作标识
     *
     * @param {*} mainSateOPPrivs 传入数据操作标识
     * @returns {any}
     * @memberof ReleaseAuthServiceBase
     */
    public getOPPrivs(mainSateOPPrivs:any):any{
        let curDefaultOPPrivs:any = this.getSysOPPrivs();
        if(mainSateOPPrivs){
            Object.assign(curDefaultOPPrivs,mainSateOPPrivs);
        }
        return curDefaultOPPrivs;
    }

}