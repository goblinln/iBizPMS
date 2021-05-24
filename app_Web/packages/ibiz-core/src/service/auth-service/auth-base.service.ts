import { IPSAppDataEntity, IPSDEOPPriv } from '@ibiz/dynamic-model-api';
import { AppServiceBase } from '../app-service/app-base.service';
import { GetModelService } from '../model-service/model-service';

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
    public sysOPPrivsMap: Map<string, any> = new Map();

    /**
     * 实体操作标识映射Map
     *
     * @public
     * @type {Map<string,any>}
     * @memberof AuthServiceBase
     */
    public deOPPrivsMap: Map<string, any> = new Map();

	/**
	 * 实体数据访问控制方式
	 * @description 值模式 [云实体数据访问控制方式] {0：无控制、 1：自控制、 2：附属主实体控制、 3：附属主实体控制（未映射自控） }
	 * @type {( number | 0 | 1 | 2 | 3)} 
     * @memberof AuthServiceBase
	 */
    public dataAccCtrlMode:number = 0;

    /**
     * 应用上下文
     *
     * @protected
     * @type {any}
     * @memberof AuthServiceBase
     */
     protected context: any;

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof AuthServiceBase
     */
    protected dynaModelFilePath: string = '';

    /**
     * 默认操作标识
     *
     * @public
     * @type {(any)}
     * @memberof AuthServiceBase
     */
    public defaultOPPrivs: any;

    /**
     * 应用实体模型对象
     *
     * @protected
     * @type {IBizEntityModel}
     * @memberof AuthServiceBase
     */
     protected entityModel!: IPSAppDataEntity;

    /**
     * Creates an instance of AuthServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof AuthServiceBase
     */
    constructor(opts: any = {}) {
        this.$store = AppServiceBase.getInstance().getAppStore();
        this.context = opts.context ? opts.context : {};
        this.registerSysOPPrivs();
    }

    /**
     * 加载应用实体模型数据
     *
     * @memberof  AuthServiceBase
     */
    protected async loaded() {
        this.entityModel = await (await GetModelService(this.context)).getPSAppDataEntity(this.dynaModelFilePath);  
        this.registerDEOPPrivs(this.entityModel);
        this.dataAccCtrlMode =  this.entityModel.dataAccCtrlMode;
    }

    /**
     * 注册实体操作标识Map
     *
     * @memberof  AuthServiceBase
     */
    public registerDEOPPrivs(opts:IPSAppDataEntity){
        if(opts.getAllPSDEOPPrivs()){
            opts.getAllPSDEOPPrivs()?.forEach((item:IPSDEOPPriv) =>{
                this.deOPPrivsMap.set(item.name,item);
            })
        }
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
    public getSysOPPrivs() {
        let copySysOPPrivs: any = JSON.parse(JSON.stringify(this.defaultOPPrivs));
        if (Object.keys(copySysOPPrivs).length === 0) return {};
        Object.keys(copySysOPPrivs).forEach((name: any) => {
            if (this.sysOPPrivsMap.get(name)) {
                copySysOPPrivs[name] = this.getResourcePermission(this.sysOPPrivsMap.get(name)) ? 1 : 0;
            }
        })
        return copySysOPPrivs;
    }

    /**
     * 注册系统操作标识统一资源
     *
     * @param {string} name 实体名称
     * @returns {Promise<any>}
     * @memberof AuthServiceBase
     */
    public registerSysOPPrivs() { }

    /**
     * 根据当前数据获取实体操作标识
     *
     * @param {string} name 实体名称
     * @returns {any}
     * @memberof AuthServiceBase
     */
    public getOPPrivs(activeKey: string, data: any): any {
        return null;
    }

    /**
     * 获取实体级数据操作标识
     *
     * @returns {}
     * @memberof AuthServiceBase
     */
     public getActivedDeOPPrivs(key: string) {
        if(this.dataAccCtrlMode == 0){
            return true;
        }else if(this.dataAccCtrlMode == 1){
            return this.getStore().getters['authresource/getSrfappdeData'](key);
        }else {
            // todo 
            return this.getStore().getters['authresource/getSrfappdeData'](key);
        }
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
        if (!this.$store.getters['authresource/getEnablePermissionValid']) {
            return true;
        }
        if (Object.is(Environment.menuPermissionMode, "RT")) {
            return this.$store.getters['authresource/getAuthMenuWithRT'](item);
        } else if (Object.is(Environment.menuPermissionMode, "RESOURCE")) {
            return this.$store.getters['authresource/getAuthMenuWithResource'](item);
        } else {
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
        if (!this.$store.getters['authresource/getEnablePermissionValid']) {
            return true;
        }
        return this.$store.getters['authresource/getResourceData'](tag);
    }

}