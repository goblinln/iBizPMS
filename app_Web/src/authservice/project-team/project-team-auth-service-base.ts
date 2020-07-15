import AuthService from '../auth-service';

/**
 * 项目团队权限服务对象基类
 *
 * @export
 * @class ProjectTeamAuthServiceBase
 * @extends {AuthService}
 */
export default class ProjectTeamAuthServiceBase extends AuthService {

    /**
     * Creates an instance of  ProjectTeamAuthServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTeamAuthServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 根据当前数据获取实体操作标识
     *
     * @param {*} mainSateOPPrivs 传入数据操作标识
     * @returns {any}
     * @memberof ProjectTeamAuthServiceBase
     */
    public getOPPrivs(mainSateOPPrivs:any):any{
        let curDefaultOPPrivs:any = JSON.parse(JSON.stringify(this.defaultOPPrivs));
        if(mainSateOPPrivs){
            Object.assign(curDefaultOPPrivs,mainSateOPPrivs);
        }
        return curDefaultOPPrivs;
    }

}