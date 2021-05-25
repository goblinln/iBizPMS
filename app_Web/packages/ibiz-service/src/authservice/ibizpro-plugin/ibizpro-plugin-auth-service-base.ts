import { AuthService } from '../auth-service';

/**
 * 系统插件权限服务对象基类
 *
 * @export
 * @class IBIZProPluginAuthServiceBase
 * @extends {AuthService}
 */
export class IBIZProPluginAuthServiceBase extends AuthService {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IBIZProPluginAuthServiceBase
     */
     protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IBIZProPlugin.json";

    /**
     * Creates an instance of  IBIZProPluginAuthServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IBIZProPluginAuthServiceBase
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
     * @memberof IBIZProPluginAuthServiceBase
     */
    public getOPPrivs(activeKey: string,dataaccaction:string, mainSateOPPrivs: any): any {
        let result: any = { [dataaccaction]: 1 };
        const curDefaultOPPrivs: any = this.getSysOPPrivs();
        // 统一资源权限
        if (curDefaultOPPrivs && (Object.keys(curDefaultOPPrivs).length > 0) && curDefaultOPPrivs.hasOwnProperty(dataaccaction)) {
            result[dataaccaction] = result[dataaccaction] && curDefaultOPPrivs[dataaccaction];
        }
        // 主状态权限
        if (mainSateOPPrivs && (Object.keys(mainSateOPPrivs).length > 0) && mainSateOPPrivs.hasOwnProperty(dataaccaction)) {
            result[dataaccaction] = result[dataaccaction] && mainSateOPPrivs[dataaccaction];
        }
        // 实体级权限
         if (!this.getActivedDeOPPrivs(activeKey, dataaccaction)) {
            result[dataaccaction] = 0;
        }
        return result;
    }

}