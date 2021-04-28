import { CounterService } from '../counter-service';

/**
 * 我的收藏移动端计数器计数器服务对象基类
 *
 * @export
 * @class MyFavoriteMobCounterCounterServiceBase
 */
export class MyFavoriteMobCounterCounterServiceBase extends CounterService {

    /**
     * 计数器服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof MyFavoriteMobCounterCounterServiceBase
     */
    protected static counterServiceMap: Map<string, any> = new Map();

    /**
     * 通过应用参数获取实例对象
     *
     * @public
     * @memberof MyFavoriteMobCounterCounterServiceBase
     */
    public static getInstance(data: any): MyFavoriteMobCounterCounterServiceBase {
        if (!MyFavoriteMobCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid)) {
            MyFavoriteMobCounterCounterServiceBase.counterServiceMap.set(data.context.srfsessionid, new MyFavoriteMobCounterCounterServiceBase(data));
        }
        return MyFavoriteMobCounterCounterServiceBase.counterServiceMap.get(data.context.srfsessionid);
    }

    /**
     * Creates an instance of  MyFavoriteMobCounterCounterServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  MyFavoriteMobCounterCounterServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
        this.excuteRefreshData();
    }

    /**
     * 执行刷新数据
     * 
     * @memberof  MyFavoriteMobCounterCounterServiceBase
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
     * @memberof  MyFavoriteMobCounterCounterServiceBase
     */
    public async fetchCounterData(context:any,data:any){
        let _appEntityService:any = await this.appEntityService.getService('IbzMyTerritory');
        if (_appEntityService['MyFavoriteCount'] && _appEntityService['MyFavoriteCount'] instanceof Function) {
            let result = await _appEntityService['MyFavoriteCount'](context,data);
            this.counterData = result.data;
        }
    }

    /**
     * 刷新数据
     *
     * @memberof MyFavoriteMobCounterCounterServiceBase
     */
    public async refreshCounterData(context:any,data:any){
        this.context = context;
        this.viewparams = data;
        this.excuteRefreshData();
    }

}