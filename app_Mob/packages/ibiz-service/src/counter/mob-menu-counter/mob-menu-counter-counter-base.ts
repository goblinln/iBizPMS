import { CounterService } from '../counter-service';

/**
 * 移动端菜单计数器计数器服务对象基类
 *
 * @export
 * @class MobMenuCounterCounterServiceBase
 */
export class MobMenuCounterCounterServiceBase extends CounterService {

    /**
     * 计数器服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof MobMenuCounterCounterServiceBase
     */
    protected static counterServiceMap: Map<string, any> = new Map();

    /**
     * 通过应用参数获取实例对象
     *
     * @public
     * @memberof MobMenuCounterCounterServiceBase
     */
    public static getInstance(data: any): MobMenuCounterCounterServiceBase {
        if (!MobMenuCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid)) {
            MobMenuCounterCounterServiceBase.counterServiceMap.set(data.context.srfsessionid, new MobMenuCounterCounterServiceBase(data));
        }
        return MobMenuCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid);
    }

    /**
     * Creates an instance of  MobMenuCounterCounterServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  MobMenuCounterCounterServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
        this.excuteRefreshData();
    }

    /**
     * 执行刷新数据
     * 
     * @memberof  MobMenuCounterCounterServiceBase
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
     * @memberof  MobMenuCounterCounterServiceBase
     */
    public async fetchCounterData(context:any,data:any){
        let _appEntityService:any = await this.appEntityService.getService('IbzMyTerritory');
        if (_appEntityService['MobMenuCount'] && _appEntityService['MobMenuCount'] instanceof Function) {
            let result = await _appEntityService['MobMenuCount'](context,data);
            this.counterData = result.data;
        }
    }

    /**
     * 刷新数据
     *
     * @memberof MobMenuCounterCounterServiceBase
     */
    public async refreshCounterData(context:any,data:any){
        this.context = context;
        this.viewparams = data;
        this.excuteRefreshData();
    }

}