import { CounterService } from '../counter-service';

/**
 * 我未提交的汇报计数器服务对象基类
 *
 * @export
 * @class MySubmitReporttCounterCounterServiceBase
 */
export class MySubmitReporttCounterCounterServiceBase extends CounterService {

    /**
     * 计数器服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof MySubmitReporttCounterCounterServiceBase
     */
    protected static counterServiceMap: Map<string, any> = new Map();

    /**
     * 通过应用参数获取实例对象
     *
     * @public
     * @memberof MySubmitReporttCounterCounterServiceBase
     */
    public static getInstance(data: any): MySubmitReporttCounterCounterServiceBase {
        if (!MySubmitReporttCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid)) {
            MySubmitReporttCounterCounterServiceBase.counterServiceMap.set(data.context.srfsessionid, new MySubmitReporttCounterCounterServiceBase(data));
        }
        return MySubmitReporttCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid);
    }

    /**
     * Creates an instance of  MySubmitReporttCounterCounterServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  MySubmitReporttCounterCounterServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
        this.excuteRefreshData();
    }

    /**
     * 执行刷新数据
     * 
     * @memberof  MySubmitReporttCounterCounterServiceBase
     */
    public excuteRefreshData(){
        this.fetchCounterData(this.context,this.viewparams);
        if(this.timer){
            clearInterval(this.timer);
        }
        this.timer = setInterval(() => {
            this.fetchCounterData(this.context,this.viewparams);
        }, 60000);
    }

    /**
     * 查询数据
     * 
     * @param {*} [opts={}]
     * @memberof  MySubmitReporttCounterCounterServiceBase
     */
    public async fetchCounterData(context:any,data:any){
    }

    /**
     * 刷新数据
     *
     * @memberof MySubmitReporttCounterCounterServiceBase
     */
    public async refreshCounterData(context:any,data:any){
        this.context = context;
        this.viewparams = data;
        this.excuteRefreshData();
    }

}