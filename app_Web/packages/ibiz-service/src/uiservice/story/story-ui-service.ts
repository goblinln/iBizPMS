import { AppCenterService, UIActionResult } from 'ibiz-vue';
import { StoryUIServiceBase } from './story-ui-service-base';

/**
 * 需求UI服务对象
 *
 * @export
 * @class StoryUIService
 */
export default class StoryUIService extends StoryUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof StoryUIService
     */
    private static basicUIServiceInstance: StoryUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof StoryUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  StoryUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  StoryUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof StoryUIService
     */
    public static getInstance(context: any): StoryUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new StoryUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!StoryUIService.UIServiceMap.get(context.srfdynainstid)) {
                StoryUIService.UIServiceMap.set(context.srfdynainstid, new StoryUIService({context:context}));
            }
            return StoryUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

    /**
     * 执行界面行为统一入口
     *
     * @param {string} uIActionTag 界面行为tag
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * 
     * @memberof StoryUIService
     */
    protected excuteAction(uIActionTag: string, args: any[], context: any = {}, params: any = {}, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
      if (Object.is(uIActionTag, "ReturnEdit")) {
          this.ReturnEdit();
      } else {
          super.excuteAction(uIActionTag, args, context, params, $event, xData, actionContext, srfParentDeName);
      }
      return new Promise((resolve: any, reject: any) => {
        resolve(new UIActionResult({ ok: true, result: {} }))
      })
    }

    /**
     * 退出
     * 
     * @memberof StoryUIService
     */
    public async ReturnEdit() {
      AppCenterService.notifyMessage({ name: 'Story', action: 'appRefresh', data: undefined });
    }

}