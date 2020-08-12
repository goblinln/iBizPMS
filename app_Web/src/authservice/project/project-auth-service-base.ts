import AuthService from '../auth-service';

/**
 * 项目权限服务对象基类
 *
 * @export
 * @class ProjectAuthServiceBase
 * @extends {AuthService}
 */
export default class ProjectAuthServiceBase extends AuthService {

    /**
     * Creates an instance of  ProjectAuthServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectAuthServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 根据当前数据获取实体操作标识
     *
     * @param {*} mainSateOPPrivs 传入数据操作标识
     * @returns {any}
     * @memberof ProjectAuthServiceBase
     */
    public getOPPrivs(mainSateOPPrivs:any):any{
        let curDefaultOPPrivs:any = this.getSysOPPrivs();
        if(mainSateOPPrivs){
            Object.assign(curDefaultOPPrivs,mainSateOPPrivs);
        }
        return curDefaultOPPrivs;
    }

}