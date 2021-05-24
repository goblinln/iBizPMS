import { AuthService } from '../auth-service';

/**
 * 动态数据看板权限服务对象基类
 *
 * @export
 * @class DynaDashboardAuthServiceBase
 * @extends {AuthService}
 */
export class DynaDashboardAuthServiceBase extends AuthService {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof DynaDashboardAuthServiceBase
     */
     protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/DynaDashboard.json";

    /**
     * Creates an instance of  DynaDashboardAuthServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  DynaDashboardAuthServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 根据当前数据获取实体操作标识
     *
     * @param {*} activeKey 实体权限数据缓存标识
     * @param {*} mainSateOPPrivs 传入数据操作标识
     * @returns {any}
     * @memberof DynaDashboardAuthServiceBase
     */
     public getOPPrivs(activeKey: string, mainSateOPPrivs: any): any {
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
        if (this.getActivedDeOPPrivs(activeKey)) {
            Object.assign(curDefaultOPPrivs, this.getActivedDeOPPrivs(activeKey));
        }
        return curDefaultOPPrivs;
    }

}