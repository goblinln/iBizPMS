// import { AppServiceBase } from 'ibiz-core';
// import { AppCenterService } from 'ibiz-vue';
import { ProjectUIServiceBase } from './project-ui-service-base';

/**
 * 项目UI服务对象
 *
 * @export
 * @class ProjectUIService
 */
export default class ProjectUIService extends ProjectUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectUIService
     */
    private static basicUIServiceInstance: ProjectUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectUIService
     */
    public static getInstance(context: any): ProjectUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectUIService.UIServiceMap.set(context.srfdynainstid, new ProjectUIService({context:context}));
            }
            return ProjectUIService.UIServiceMap.get(context.srfdynainstid);
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
     * @memberof ProjectUIService
     */
    //  protected excuteAction(uIActionTag: string, args: any[], context: any = {}, params: any = {}, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
    //     if (Object.is(uIActionTag, "ReturnEdit")) {
    //         this.ReturnEdit(args, xData, actionContext);
    //     } else {
    //         super.excuteAction(uIActionTag, args, context, params, $event, xData, actionContext, srfParentDeName);
    //     }
    // }

    /**
     * 退出
     * 
     * @memberof ProjectUIService
     */
    // public async ReturnEdit(args: any[], xData: any, actionContext: any) {
    //     const _this: any = actionContext;
    //     AppCenterService.notifyMessage({ name: 'Project', action: 'appRefresh', data: undefined });
    //     const appNavDataService: any = AppServiceBase.getInstance().getAppNavDataService();
    //     const item:any = appNavDataService.historyList[appNavDataService.findHistoryIndex(_this.$route)];
    //     _this.$tabPageExp.onClose(item);

    // }

}