import { AuthService } from '../auth-service';

/**
 * 群组权限服务对象基类
 *
 * @export
 * @class GroupAuthServiceBase
 * @extends {AuthService}
 */
export class GroupAuthServiceBase extends AuthService {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof GroupAuthServiceBase
     */
     protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/Group.json";

    /**
     * Creates an instance of  GroupAuthServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  GroupAuthServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 根据当前数据获取实体操作标识
     *
     * @param {*} activeKey 实体权限数据缓存标识
     * @param {*} dataaccaction 操作标识
     * @param {*} mainSateOPPrivs 传入数据主状态操作标识集合
     * @returns {any}
     * @memberof GroupAuthServiceBase
     */
    public getOPPrivs(activeKey: string,dataaccaction:string, mainSateOPPrivs: any): any {
        let curDefaultOPPrivs:any = this.getSysOPPrivs();
        let copyDefaultOPPrivs:any = JSON.parse(JSON.stringify(curDefaultOPPrivs));
        if(mainSateOPPrivs){
            Object.assign(curDefaultOPPrivs,mainSateOPPrivs);
        }
        // 统一资源优先
        Object.keys(curDefaultOPPrivs).forEach((name:string) => {
            if(this.sysOPPrivsMap.get(name) && copyDefaultOPPrivs[name] === 0){
                curDefaultOPPrivs[name] = copyDefaultOPPrivs[name];
            }
        });
        // 合并实体级权限数据
        if(!this.getActivedDeOPPrivs(activeKey, dataaccaction)){
            Object.assign(curDefaultOPPrivs, { [dataaccaction]: this.getActivedDeOPPrivs(activeKey, dataaccaction) });
        }
        return curDefaultOPPrivs;
    }

}