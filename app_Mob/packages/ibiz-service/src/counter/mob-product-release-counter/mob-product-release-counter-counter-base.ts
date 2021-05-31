import { CounterService } from '../counter-service';

/**
 * 移动端产品发布计数器计数器服务对象基类
 *
 * @export
 * @class MobProductReleaseCounterCounterServiceBase
 */
export class MobProductReleaseCounterCounterServiceBase extends CounterService {

    /**
     * 计数器服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof MobProductReleaseCounterCounterServiceBase
     */
    protected static counterServiceMap: Map<string, any> = new Map();

    /**
     * 通过应用参数获取实例对象
     *
     * @public
     * @memberof MobProductReleaseCounterCounterServiceBase
     */
    public static getInstance(data: any): MobProductReleaseCounterCounterServiceBase {
        if (!MobProductReleaseCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid)) {
            MobProductReleaseCounterCounterServiceBase.counterServiceMap.set(data.context.srfsessionid, new MobProductReleaseCounterCounterServiceBase(data));
        }
        return MobProductReleaseCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid);
    }

    /**
     * Creates an instance of  MobProductReleaseCounterCounterServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  MobProductReleaseCounterCounterServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
        this.excuteRefreshData();
    }

    /**
     * 执行刷新数据
     * 
     * @memberof  MobProductReleaseCounterCounterServiceBase
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
     * @memberof  MobProductReleaseCounterCounterServiceBase
     */
    public async fetchCounterData(context:any,data:any){
    }

    /**
     * 刷新数据
     *
     * @memberof MobProductReleaseCounterCounterServiceBase
     */
    public async refreshCounterData(context:any,data:any){
        this.context = context;
        this.viewparams = data;
        this.excuteRefreshData();
    }

}