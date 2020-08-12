import AuthService from '../auth-service';

/**
 * 测试套件权限服务对象基类
 *
 * @export
 * @class TestSuiteAuthServiceBase
 * @extends {AuthService}
 */
export default class TestSuiteAuthServiceBase extends AuthService {

    /**
     * Creates an instance of  TestSuiteAuthServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  TestSuiteAuthServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 根据当前数据获取实体操作标识
     *
     * @param {*} mainSateOPPrivs 传入数据操作标识
     * @returns {any}
     * @memberof TestSuiteAuthServiceBase
     */
    public getOPPrivs(mainSateOPPrivs:any):any{
        let curDefaultOPPrivs:any = this.getSysOPPrivs();
        if(mainSateOPPrivs){
            Object.assign(curDefaultOPPrivs,mainSateOPPrivs);
        }
        return curDefaultOPPrivs;
    }

}