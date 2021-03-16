import { CounterService } from '../counter-service';

/**
 * 我收到的未读汇报计数器服务对象基类
 *
 * @export
 * @class MyReportCounterCounterServiceBase
 */
export class MyReportCounterCounterServiceBase extends CounterService {

    /**
     * 计数器服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof MyReportCounterCounterServiceBase
     */
    protected static counterServiceMap: Map<string, any> = new Map();

    /**
     * 通过应用参数获取实例对象
     *
     * @public
     * @memberof MyReportCounterCounterServiceBase
     */
    public static getInstance(data: any): MyReportCounterCounterServiceBase {
        if (!MyReportCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid)) {
            MyReportCounterCounterServiceBase.counterServiceMap.set(data.context.srfsessionid, new MyReportCounterCounterServiceBase(data));
        }
        return MyReportCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid);
    }

    /**
     * Creates an instance of  MyReportCounterCounterServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  MyReportCounterCounterServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
        this.excuteRefreshData();
    }

    /**
     * 执行刷新数据
     * 
     * @memberof  MyReportCounterCounterServiceBase
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
     * @memberof  MyReportCounterCounterServiceBase
     */
    public async fetchCounterData(context:any,data:any){
        let _appEntityService:any = await this.appEntityService.getService('IbzReport');
        if (_appEntityService['ReportIReceived'] && _appEntityService['ReportIReceived'] instanceof Function) {
            let result = await _appEntityService['ReportIReceived'](context,data);
            this.counterData = result.data;
        }
    }

    /**
     * 刷新数据
     *
     * @memberof MyReportCounterCounterServiceBase
     */
    public async refreshCounterData(context:any,data:any){
        this.context = context;
        this.viewparams = data;
        this.excuteRefreshData();
    }

}