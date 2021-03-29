import { IBIZProPluginAuthServiceBase } from './ibizpro-plugin-auth-service-base';


/**
 * 系统插件权限服务对象
 *
 * @export
 * @class IBIZProPluginAuthService
 * @extends {IBIZProPluginAuthServiceBase}
 */
export default class IBIZProPluginAuthService extends IBIZProPluginAuthServiceBase {

    /**
     * Creates an instance of  IBIZProPluginAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBIZProPluginAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}