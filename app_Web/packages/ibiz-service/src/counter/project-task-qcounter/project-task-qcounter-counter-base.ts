import { CounterService } from '../counter-service';

/**
 * 项目任务快速分组计数器计数器服务对象基类
 *
 * @export
 * @class ProjectTaskQCounterCounterServiceBase
 */
export class ProjectTaskQCounterCounterServiceBase extends CounterService {

    /**
     * 计数器服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectTaskQCounterCounterServiceBase
     */
    protected static counterServiceMap: Map<string, any> = new Map();

    /**
     * 通过应用参数获取实例对象
     *
     * @public
     * @memberof ProjectTaskQCounterCounterServiceBase
     */
    public static getInstance(data: any): ProjectTaskQCounterCounterServiceBase {
        if (!ProjectTaskQCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid)) {
            ProjectTaskQCounterCounterServiceBase.counterServiceMap.set(data.context.srfsessionid, new ProjectTaskQCounterCounterServiceBase(data));
        }
        return ProjectTaskQCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid);
    }

    /**
     * Creates an instance of  ProjectTaskQCounterCounterServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTaskQCounterCounterServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
        this.excuteRefreshData();
    }

    /**
     * 执行刷新数据
     * 
     * @memberof  ProjectTaskQCounterCounterServiceBase
     */
    public excuteRefreshData(){
        this.fetchCounterData(this.context,this.viewparams);
        if(this.timer){
            clearInterval(this.timer);
        }
        this.timer = setInterval(() => {
            this.fetchCounterData(this.context,this.viewparams);
        }, 120000);
    }

    /**
     * 查询数据
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTaskQCounterCounterServiceBase
     */
    public async fetchCounterData(context:any,data:any){
    }

    /**
     * 刷新数据
     *
     * @memberof ProjectTaskQCounterCounterServiceBase
     */
    public async refreshCounterData(context:any,data:any){
        this.context = context;
        this.viewparams = data;
        this.excuteRefreshData();
    }

}