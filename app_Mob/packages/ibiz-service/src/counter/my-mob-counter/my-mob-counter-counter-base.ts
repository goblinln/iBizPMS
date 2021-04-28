import { CounterService } from '../counter-service';

/**
 * 我的地盘移动端计数器计数器服务对象基类
 *
 * @export
 * @class MyMobCounterCounterServiceBase
 */
export class MyMobCounterCounterServiceBase extends CounterService {

    /**
     * 计数器服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof MyMobCounterCounterServiceBase
     */
    protected static counterServiceMap: Map<string, any> = new Map();

    /**
     * 通过应用参数获取实例对象
     *
     * @public
     * @memberof MyMobCounterCounterServiceBase
     */
    public static getInstance(data: any): MyMobCounterCounterServiceBase {
        if (!MyMobCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid)) {
            MyMobCounterCounterServiceBase.counterServiceMap.set(data.context.srfsessionid, new MyMobCounterCounterServiceBase(data));
        }
        return MyMobCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid);
    }

    /**
     * Creates an instance of  MyMobCounterCounterServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  MyMobCounterCounterServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
        this.excuteRefreshData();
    }

    /**
     * 执行刷新数据
     * 
     * @memberof  MyMobCounterCounterServiceBase
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
     * @memberof  MyMobCounterCounterServiceBase
     */
    public async fetchCounterData(context:any,data:any){
        let _appEntityService:any = await this.appEntityService.getService('IbzMyTerritory');
        if (_appEntityService['MyTerritoryCount'] && _appEntityService['MyTerritoryCount'] instanceof Function) {
            let result = await _appEntityService['MyTerritoryCount'](context,data);
            this.counterData = result.data;
        }
    }

    /**
     * 刷新数据
     *
     * @memberof MyMobCounterCounterServiceBase
     */
    public async refreshCounterData(context:any,data:any){
        this.context = context;
        this.viewparams = data;
        this.excuteRefreshData();
    }

}