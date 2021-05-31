import { CounterService } from '../counter-service';

/**
 * 测试移动端计数器计数器服务对象基类
 *
 * @export
 * @class ProductTestMobCounterCounterServiceBase
 */
export class ProductTestMobCounterCounterServiceBase extends CounterService {

    /**
     * 计数器服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductTestMobCounterCounterServiceBase
     */
    protected static counterServiceMap: Map<string, any> = new Map();

    /**
     * 通过应用参数获取实例对象
     *
     * @public
     * @memberof ProductTestMobCounterCounterServiceBase
     */
    public static getInstance(data: any): ProductTestMobCounterCounterServiceBase {
        if (!ProductTestMobCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid)) {
            ProductTestMobCounterCounterServiceBase.counterServiceMap.set(data.context.srfsessionid, new ProductTestMobCounterCounterServiceBase(data));
        }
        return ProductTestMobCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid);
    }

    /**
     * Creates an instance of  ProductTestMobCounterCounterServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductTestMobCounterCounterServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
        this.excuteRefreshData();
    }

    /**
     * 执行刷新数据
     * 
     * @memberof  ProductTestMobCounterCounterServiceBase
     */
    public excuteRefreshData(){
        this.fetchCounterData(this.context,this.viewparams);
        if(this.timer){
            clearInterval(this.timer);
        }
        this.timer = setInterval(() => {
            this.fetchCounterData(this.context,this.viewparams);
        }, 5000);
    }

    /**
     * 查询数据
     * 
     * @param {*} [opts={}]
     * @memberof  ProductTestMobCounterCounterServiceBase
     */
    public async fetchCounterData(context:any,data:any){
    }

    /**
     * 刷新数据
     *
     * @memberof ProductTestMobCounterCounterServiceBase
     */
    public async refreshCounterData(context:any,data:any){
        this.context = context;
        this.viewparams = data;
        this.excuteRefreshData();
    }

}