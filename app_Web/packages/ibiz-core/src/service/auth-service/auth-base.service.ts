import { AppServiceBase } from '../app-service/app-base.service';

/**
 * 实体权限服务基类
 *
 * @export
 * @class AuthServiceBase
 */
export class AuthServiceBase {

    /**
     * 应用存储对象
     *
     * @public
     * @type {(any | null)}
     * @memberof AuthServiceBase
     */
    private $store: any;

    /**
     * 系统操作标识映射统一资源Map
     *
     * @public
     * @type {Map<string,any>}
     * @memberof AuthServiceBase
     */
    public sysOPPrivsMap:Map<string,any> = new  Map();

    /**
     * 默认操作标识
     *
     * @public
     * @type {(any)}
     * @memberof AuthServiceBase
     */
    public defaultOPPrivs: any; 

    /**
     * Creates an instance of AuthServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof AuthServiceBase
     */
    constructor(opts: any = {}) {
        this.$store = AppServiceBase.getInstance().getAppStore();
        this.registerSysOPPrivs();
    }

    /**
     * 获取应用存储对象
     *
     * @returns {(any | null)}
     * @memberof AuthServiceBase
     */
    public getStore(): any {
        return this.$store;
    }

    /**
     * 获取计算统一资源之后的系统操作标识
     *
     * @returns {}
     * @memberof AuthServiceBase
     */
    public getSysOPPrivs(){
        let copySysOPPrivs:any = JSON.parse(JSON.stringify(this.defaultOPPrivs));
        if(Object.keys(copySysOPPrivs).length === 0) return {};
        Object.keys(copySysOPPrivs).forEach((name:any) =>{
            if(this.sysOPPrivsMap.get(name)){
                copySysOPPrivs[name] = this.getResourcePermission(this.sysOPPrivsMap.get(name))?1:0;
            }
        })
        return copySysOPPrivs;
    }

    /**
     * 获取实体权限服务
     *
     * @param {string} name 实体名称
     * @returns {Promise<any>}
     * @memberof AuthServiceBase
     */
    public getService(name: string): Promise<any> {
        return (window as any)['authServiceRegister'].getService(name);
    }

    /**
     * 注册系统操作标识统一资源
     *
     * @param {string} name 实体名称
     * @returns {Promise<any>}
     * @memberof AuthServiceBase
     */ 
    public registerSysOPPrivs(){}

    /**
     * 根据当前数据获取实体操作标识
     *
     * @param {string} name 实体名称
     * @returns {any}
     * @memberof AuthServiceBase
     */
    public getOPPrivs(data: any): any {
        return null;
    }

    /**
     * 根据菜单项获取菜单权限
     *
     * @param {*} item 菜单标识
     * @returns {boolean}
     * @memberof AuthServiceBase
     */
    public getMenusPermission(item: any): boolean {
        const Environment = AppServiceBase.getInstance().getAppEnvironment();
        if(!this.$store.getters['authresource/getEnablePermissionValid']) {
            return true;
        }
        if(Object.is(Environment.menuPermissionMode,"RT")){
            return this.$store.getters['authresource/getAuthMenuWithRT'](item);
        }else if(Object.is(Environment.menuPermissionMode,"RESOURCE")){
            return this.$store.getters['authresource/getAuthMenuWithResource'](item);
        }else{
            return this.$store.getters['authresource/getAuthMenu'](item);
        }
    }

    /**
     * 根据统一资源标识获取统一资源权限
     *
     * @param {*} tag 统一资源标识
     * @returns {boolean}
     * @memberof AuthServiceBase
     */
    public getResourcePermission(tag: any): boolean {
        if(!this.$store.getters['authresource/getEnablePermissionValid']) {
            return true;
        }
        return this.$store.getters['authresource/getResourceData'](tag);
    }

}