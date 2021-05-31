import { CounterService } from '../counter-service';

/**
 * 项目移动端计数器计数器服务对象基类
 *
 * @export
 * @class ProjectCounterCounterServiceBase
 */
export class ProjectCounterCounterServiceBase extends CounterService {

    /**
     * 计数器服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectCounterCounterServiceBase
     */
    protected static counterServiceMap: Map<string, any> = new Map();

    /**
     * 通过应用参数获取实例对象
     *
     * @public
     * @memberof ProjectCounterCounterServiceBase
     */
    public static getInstance(data: any): ProjectCounterCounterServiceBase {
        if (!ProjectCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid)) {
            ProjectCounterCounterServiceBase.counterServiceMap.set(data.context.srfsessionid, new ProjectCounterCounterServiceBase(data));
        }
        return ProjectCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid);
    }

    /**
     * Creates an instance of  ProjectCounterCounterServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectCounterCounterServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
        this.excuteRefreshData();
    }

    /**
     * 执行刷新数据
     * 
     * @memberof  ProjectCounterCounterServiceBase
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
     * @memberof  ProjectCounterCounterServiceBase
     */
    public async fetchCounterData(context:any,data:any){
    }

    /**
     * 刷新数据
     *
     * @memberof ProjectCounterCounterServiceBase
     */
    public async refreshCounterData(context:any,data:any){
        this.context = context;
        this.viewparams = data;
        this.excuteRefreshData();
    }

}