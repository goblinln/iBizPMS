/**
 * 沙箱服务
 *
 * @export
 * @class SandboxService
 */
 export class SandboxService {

    /**
     * 沙箱实例Map
     * 
     * @memberof SandboxService
     */
    private sandboxMap:Map<string,any> = new Map();

    /**
     * 单例变量声明
     *
     * @private
     * @static
     * @type {SandboxService}
     * @memberof SandboxService
     */
     private static SandboxService: SandboxService;

     /**
      * 获取 SandboxService 单例对象
      *
      * @static
      * @returns {SandboxService}
      * @memberof SandboxService
      */
     public static getInstance(): SandboxService {
         if (!SandboxService.SandboxService) {
            this.SandboxService = new SandboxService();
         }
         return this.SandboxService;
     }

    /**
     * Creates an instance of SandboxService.
     * 
     * @param {*} [opts={}]
     * @memberof SandboxService
     */
    constructor(opts: any = {}) {}

    /**
     * 获取沙箱实例
     *
     * @returns {}
     * @memberof SandboxService
     */
    public getSandBoxInstance(tag:string){
        return this.sandboxMap.get(tag);
    }

    /**
     * 设置沙箱实例到缓存Map中
     *
     * @returns {}
     * @memberof SandboxService
     */
    public setSandBoxInstance(tag:string,value:any){
        this.sandboxMap.set(tag,value);
    }

}