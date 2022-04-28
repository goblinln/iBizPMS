import { IPSAppCounter } from '@ibiz/dynamic-model-api';
import { LogUtil } from '../../utils';

/**
 * 计数器服务
 * 
 * @export
 * @class CounterService
 */
export class CounterService {

  /**
   * 计时器间隔时间
   * 
   * @protected
   * @type {number}
   * @memberof CounterService
   */
  protected delayTime: number = 6000;

  /**
   * 计时器对象
   * 
   * @protected
   * @type {any}
   * @memberof CounterService
   */
  protected timer: any = null;

  /**
   * 应用上下文
   * 
   * @protected
   * @type {any}
   * @memberof CounterService
   */
  protected context: any = {};

  /**
   * 视图参数
   * 
   * @protected
   * @type {any}
   * @memberof CounterService
   */
  protected viewparams: any = {};

  /**
   * 获取数据行为
   * 
   * @protected
   * @type {string}
   * @memberof CounterService
   */
  protected getAction: string = '';

  /**
   * 实体服务
   * 
   * @private
   * @type {any}
   * @memberof CounterService
   */
  private entityService: any;

  /**
   * 计数器数据
   * 
   * @private
   * @type {any}
   * @memberof CounterService
   */
  private counterData: any;

  /**
   * 计数器服务加载
   * 
   * @memberof CounterService
   */
  public async loaded(instance: IPSAppCounter, opts?: any) {
    this.context = opts?.context ? opts.context : {};
    this.viewparams = opts?.viewparams ? opts.viewparams : {};
    await this.initOptions(instance);
    await this.excuteRefreshData();
  }

  /**
   * 初始化配置
   * 
   * @memberof CounterService
   */
  public async initOptions(instance: IPSAppCounter) {
    this.delayTime = instance?.timer || 6000;
    const appDataEntity = instance?.getPSAppDataEntity();
    if (appDataEntity) {
      this.entityService = await ___ibz___.gs.getService(appDataEntity.codeName);
    }
    this.getAction = instance.getGetPSAppDEAction?.()?.codeName || '';
  }

  /**
   * 执行刷新数据
   * 
   * @memberof  CounterService
   */
  public async excuteRefreshData(){
    await this.fetchCounterData();
    this.destroyCounter();
    this.timer = setInterval(() => {
      this.fetchCounterData();
    }, this.delayTime);
  }

  /**
   * 获取计数器数据
   * 
   * @memberof  CounterService
   */
  public async fetchCounterData() {
    if (this.entityService && this.getAction && this.entityService[this.getAction] && this,this.entityService[this.getAction] instanceof Function) {
      try {
        let result = await this.entityService[this.getAction](this.context, this.viewparams);
        if (result && result.data) {
          this.counterData = result.data;
        }
      } catch (error: any) {
        LogUtil.error(error);
      }
    }
  }

  /**
   * 刷新数据
   *
   * @memberof CounterService
   */
  public async refreshCounterData(context:any,data:any){
    this.context = context;
    this.viewparams = data;
    await this.excuteRefreshData();
  }

  /**
   * 销毁计数器
   *
   * @memberof CounterServiceBase
   */
  public destroyCounter(){
    if(this.timer) clearInterval(this.timer);
  }

}