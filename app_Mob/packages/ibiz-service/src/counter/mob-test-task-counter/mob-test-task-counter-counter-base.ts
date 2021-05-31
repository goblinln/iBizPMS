import { CounterService } from '../counter-service';

/**
 * 移动端测试版本计数器计数器服务对象基类
 *
 * @export
 * @class MobTestTaskCounterCounterServiceBase
 */
export class MobTestTaskCounterCounterServiceBase extends CounterService {

    /**
     * 计数器服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof MobTestTaskCounterCounterServiceBase
     */
    protected static counterServiceMap: Map<string, any> = new Map();

    /**
     * 通过应用参数获取实例对象
     *
     * @public
     * @memberof MobTestTaskCounterCounterServiceBase
     */
    public static getInstance(data: any): MobTestTaskCounterCounterServiceBase {
        if (!MobTestTaskCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid)) {
            MobTestTaskCounterCounterServiceBase.counterServiceMap.set(data.context.srfsessionid, new MobTestTaskCounterCounterServiceBase(data));
        }
        return MobTestTaskCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid);
    }

    /**
     * Creates an instance of  MobTestTaskCounterCounterServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  MobTestTaskCounterCounterServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
        this.excuteRefreshData();
    }

    /**
     * 执行刷新数据
     * 
     * @memberof  MobTestTaskCounterCounterServiceBase
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
     * @memberof  MobTestTaskCounterCounterServiceBase
     */
    public async fetchCounterData(context:any,data:any){
    }

    /**
     * 刷新数据
     *
     * @memberof MobTestTaskCounterCounterServiceBase
     */
    public async refreshCounterData(context:any,data:any){
        this.context = context;
        this.viewparams = data;
        this.excuteRefreshData();
    }

}