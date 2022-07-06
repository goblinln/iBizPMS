import { MobViewInterface } from "./mob-view";

/**
 * Main视图基类接口
 *
 * @interface MobMainViewInterface
 */
export interface MobMainViewInterface extends MobViewInterface {


    /**
     * 引擎初始化
     *
     * @param {*} opts 引擎参数
     * @memberof MobMainViewInterface
     */
    engineInit(opts: any): void;


    /**
     * 工具栏点击
     *
     * @param {*} data 时间对象
     * @param {*} $event 事件源对象
     * @memberof MobMainViewInterface
     */
    handleItemClick(data: any, $event: any): void;


    /**
     * 打开目标视图
     *
     * @param {*} openView 目标视图模型对象
     * @param {*} view 视图对象
     * @param {*} tempContext 临时上下文
     * @param {*} data 数据
     * @param {*} xData 数据部件实例
     * @param {*} $event 事件源
     * @param {*} deResParameters 关系实体参数对象
     * @param {*} parameters 当前应用视图参数对象
     * @param {*} args 额外参数
     * @param {Function} callback 回调
     * @memberof MobMainViewInterface
     */
    openTargtView(openView: any, view: any, tempContext: any, data: any, xData: any, $event: any, deResParameters: any, parameters: any, args: any, callback: Function): void;



    /**
     * 打开编辑数据视图
     *
     * @param {any[]} args 数据参数
     * @param {*} [fullargs] 全量参数
     * @param {*} [params]  额外参数
     * @param {*} [$event] 事件源数据
     * @param {*} [xData] 数据部件
     * @memberof MobMainViewInterface
     */
    opendata(args: any[], fullargs?: any, params?: any, $event?: any, xData?: any): void;


    /**
     * 打开新建数据视图
     *
     * @param {any[]} args 数据参数
     * @param {*} [fullargs] 全量参数
     * @param {*} [params]  额外参数
     * @param {*} [$event] 事件源数据
     * @param {*} [xData] 数据部件
     * @memberof MobMainViewInterface
     */
    newdata(args: any[], fullargs?: any, params?: any, $event?: any, xData?: any): void;
}