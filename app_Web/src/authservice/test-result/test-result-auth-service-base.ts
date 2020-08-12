import AuthService from '../auth-service';

/**
 * 测试结果权限服务对象基类
 *
 * @export
 * @class TestResultAuthServiceBase
 * @extends {AuthService}
 */
export default class TestResultAuthServiceBase extends AuthService {

    /**
     * Creates an instance of  TestResultAuthServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  TestResultAuthServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 根据当前数据获取实体操作标识
     *
     * @param {*} mainSateOPPrivs 传入数据操作标识
     * @returns {any}
     * @memberof TestResultAuthServiceBase
     */
    public getOPPrivs(mainSateOPPrivs:any):any{
        let curDefaultOPPrivs:any = this.getSysOPPrivs();
        if(mainSateOPPrivs){
            Object.assign(curDefaultOPPrivs,mainSateOPPrivs);
        }
        return curDefaultOPPrivs;
    }

}