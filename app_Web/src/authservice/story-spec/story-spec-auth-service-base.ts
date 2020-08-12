import AuthService from '../auth-service';

/**
 * 需求描述权限服务对象基类
 *
 * @export
 * @class StorySpecAuthServiceBase
 * @extends {AuthService}
 */
export default class StorySpecAuthServiceBase extends AuthService {

    /**
     * Creates an instance of  StorySpecAuthServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  StorySpecAuthServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 根据当前数据获取实体操作标识
     *
     * @param {*} mainSateOPPrivs 传入数据操作标识
     * @returns {any}
     * @memberof StorySpecAuthServiceBase
     */
    public getOPPrivs(mainSateOPPrivs:any):any{
        let curDefaultOPPrivs:any = this.getSysOPPrivs();
        if(mainSateOPPrivs){
            Object.assign(curDefaultOPPrivs,mainSateOPPrivs);
        }
        return curDefaultOPPrivs;
    }

}