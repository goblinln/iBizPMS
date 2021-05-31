import { AppServiceBase } from '../app-service/app-base.service';

/**
 * 计数器服务基类
 *
 * @export
 * @class CounterServiceBase
 */
export class CounterServiceBase {

    /**
     * 应用存储对象
     *
     * @private
     * @type {(any | null)}
     * @memberof CounterServiceBase
     */
    private $store: any;
    
    /**
     * 当前计数器数据
     * 
     * @protected
     * @type {*}
     * @memberof  CounterServiceBase
     */
    protected counterData:any ={};

    /**
     * 应用实体数据服务
     *
     * @protected
     * @type {EntityService}
     * @memberof CounterServiceBase
     */    
    protected appEntityService !:any;

    /**
     * 当前计数器导航上下文
     * 
     * @protected
     * @type {*}
     * @memberof  CounterServiceBase
     */
    protected context:any ={};

    /**
     * 当前计数器导航参数
     * 
     * @protected
     * @type {*}
     * @memberof  CounterServiceBase
     */
    protected viewparams:any ={};

    /**
     * 当前计数器定时器对象
     * 
     * @protected
     * @type {*}
     * @memberof  CounterServiceBase
     */
    protected timer:any;

    /**
     * Creates an instance of CounterServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof CounterServiceBase
     */
    constructor(opts: any = {}) {
        this.$store = AppServiceBase.getInstance().getAppStore();
        this.context = opts.context?opts.context:{};
        this.viewparams = opts.viewparams?opts.viewparams:{};
    }

    /**
     * 获取应用存储对象
     *
     * @returns {(any | null)}
     * @memberof CounterServiceBase
     */
    public getStore(){
        return this.$store;
    }

    /**
     * 获取计数器服务
     *
     * @protected
     * @param {string} name 实体名称
     * @returns {Promise<any>}
     * @memberof CounterServiceBase
     */
    public getService(name: string): Promise<any> {
        return (window as any)['counterServiceRegister'].getService(name);
    }

    /**
     * 销毁计数器
     *
     * @memberof CounterServiceBase
     */
    public destroyCounter(){
        if(this.timer) clearInterval(this.timer);
    }
  
}