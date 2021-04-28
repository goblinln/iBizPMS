import { CounterService } from '../counter-service';

/**
 * 移动端测试套件计数器计数器服务对象基类
 *
 * @export
 * @class MobTestSuiteCounterCounterServiceBase
 */
export class MobTestSuiteCounterCounterServiceBase extends CounterService {

    /**
     * 计数器服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof MobTestSuiteCounterCounterServiceBase
     */
    protected static counterServiceMap: Map<string, any> = new Map();

    /**
     * 通过应用参数获取实例对象
     *
     * @public
     * @memberof MobTestSuiteCounterCounterServiceBase
     */
    public static getInstance(data: any): MobTestSuiteCounterCounterServiceBase {
        if (!MobTestSuiteCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid)) {
            MobTestSuiteCounterCounterServiceBase.counterServiceMap.set(data.context.srfsessionid, new MobTestSuiteCounterCounterServiceBase(data));
        }
        return MobTestSuiteCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid);
    }

    /**
     * Creates an instance of  MobTestSuiteCounterCounterServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  MobTestSuiteCounterCounterServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
        this.excuteRefreshData();
    }

    /**
     * 执行刷新数据
     * 
     * @memberof  MobTestSuiteCounterCounterServiceBase
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
     * @memberof  MobTestSuiteCounterCounterServiceBase
     */
    public async fetchCounterData(context:any,data:any){
        let _appEntityService:any = await this.appEntityService.getService('TestSuite');
        if (_appEntityService['MobTestSuiteCount'] && _appEntityService['MobTestSuiteCount'] instanceof Function) {
            let result = await _appEntityService['MobTestSuiteCount'](context,data);
            this.counterData = result.data;
        }
    }

    /**
     * 刷新数据
     *
     * @memberof MobTestSuiteCounterCounterServiceBase
     */
    public async refreshCounterData(context:any,data:any){
        this.context = context;
        this.viewparams = data;
        this.excuteRefreshData();
    }

}