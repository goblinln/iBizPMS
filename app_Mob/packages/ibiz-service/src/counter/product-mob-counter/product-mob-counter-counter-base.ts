import { CounterService } from '../counter-service';

/**
 * 产品移动端计数器计数器服务对象基类
 *
 * @export
 * @class ProductMobCounterCounterServiceBase
 */
export class ProductMobCounterCounterServiceBase extends CounterService {

    /**
     * 计数器服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductMobCounterCounterServiceBase
     */
    protected static counterServiceMap: Map<string, any> = new Map();

    /**
     * 通过应用参数获取实例对象
     *
     * @public
     * @memberof ProductMobCounterCounterServiceBase
     */
    public static getInstance(data: any): ProductMobCounterCounterServiceBase {
        if (!ProductMobCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid)) {
            ProductMobCounterCounterServiceBase.counterServiceMap.set(data.context.srfsessionid, new ProductMobCounterCounterServiceBase(data));
        }
        return ProductMobCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid);
    }

    /**
     * Creates an instance of  ProductMobCounterCounterServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductMobCounterCounterServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
        this.excuteRefreshData();
    }

    /**
     * 执行刷新数据
     * 
     * @memberof  ProductMobCounterCounterServiceBase
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
     * @memberof  ProductMobCounterCounterServiceBase
     */
    public async fetchCounterData(context:any,data:any){
        let _appEntityService:any = await this.appEntityService.getService('Product');
        if (_appEntityService['MobProductCounter'] && _appEntityService['MobProductCounter'] instanceof Function) {
            let result = await _appEntityService['MobProductCounter'](context,data);
            this.counterData = result.data;
        }
    }

    /**
     * 刷新数据
     *
     * @memberof ProductMobCounterCounterServiceBase
     */
    public async refreshCounterData(context:any,data:any){
        this.context = context;
        this.viewparams = data;
        this.excuteRefreshData();
    }

}