import { CounterServiceBase } from "ibiz-core";
import { GlobalService } from "../service";

/**
 * 计数器服务基类
 *
 * @export
 * @class CounterService
 */
export class CounterService extends CounterServiceBase{

    /**
     * 全局实体服务
     *
     * @protected
     * @type {GlobalService}
     * @memberof CounterService
     */    
    protected appEntityService:any = new GlobalService();

    /**
     * Creates an instance of CounterService.
     * 
     * @param {*} [opts={}]
     * @memberof CounterService
     */
    constructor(opts: any = {}) {
        super(opts);
        this.appEntityService = new GlobalService();
    }
  
}