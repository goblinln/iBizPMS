import AuthService from '../auth-service';

/**
 * 附件权限服务对象基类
 *
 * @export
 * @class FileAuthServiceBase
 * @extends {AuthService}
 */
export default class FileAuthServiceBase extends AuthService {

    /**
     * Creates an instance of  FileAuthServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  FileAuthServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 根据当前数据获取实体操作标识
     *
     * @param {*} mainSateOPPrivs 传入数据操作标识
     * @returns {any}
     * @memberof FileAuthServiceBase
     */
    public getOPPrivs(mainSateOPPrivs:any):any{
        let curDefaultOPPrivs:any = this.getSysOPPrivs();
        if(mainSateOPPrivs){
            Object.assign(curDefaultOPPrivs,mainSateOPPrivs);
        }
        return curDefaultOPPrivs;
    }

}