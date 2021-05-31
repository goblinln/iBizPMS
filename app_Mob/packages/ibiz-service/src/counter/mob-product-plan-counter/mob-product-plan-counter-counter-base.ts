import { CounterService } from '../counter-service';

/**
 * 产品计划移动端计数器计数器服务对象基类
 *
 * @export
 * @class MobProductPlanCounterCounterServiceBase
 */
export class MobProductPlanCounterCounterServiceBase extends CounterService {

    /**
     * 计数器服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof MobProductPlanCounterCounterServiceBase
     */
    protected static counterServiceMap: Map<string, any> = new Map();

    /**
     * 通过应用参数获取实例对象
     *
     * @public
     * @memberof MobProductPlanCounterCounterServiceBase
     */
    public static getInstance(data: any): MobProductPlanCounterCounterServiceBase {
        if (!MobProductPlanCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid)) {
            MobProductPlanCounterCounterServiceBase.counterServiceMap.set(data.context.srfsessionid, new MobProductPlanCounterCounterServiceBase(data));
        }
        return MobProductPlanCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid);
    }

    /**
     * Creates an instance of  MobProductPlanCounterCounterServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  MobProductPlanCounterCounterServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
        this.excuteRefreshData();
    }

    /**
     * 执行刷新数据
     * 
     * @memberof  MobProductPlanCounterCounterServiceBase
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
     * @memberof  MobProductPlanCounterCounterServiceBase
     */
    public async fetchCounterData(context:any,data:any){
    }

    /**
     * 刷新数据
     *
     * @memberof MobProductPlanCounterCounterServiceBase
     */
    public async refreshCounterData(context:any,data:any){
        this.context = context;
        this.viewparams = data;
        this.excuteRefreshData();
    }

}