import { AppEmbeddedUILogic, LogUtil } from 'ibiz-core';
import { getPSAppDEUILogicByModelObject } from '@ibiz/dynamic-model-api';

/**
 * 界面触发引擎
 *
 * @export
 * @class AppUITriggerEngine
 */
export class AppUITriggerEngine {
    /**
     * 传入参数
     * @memberof AppUITriggerEngine
     */
    public options: any;

    /**
     * 名称
     * @memberof AppUITriggerEngine
     */
    public name: string = '';

    /**
     * 目标逻辑类型
     * @memberof AppUITriggerEngine
     */
    public logicType: 'DEUILOGIC' | 'SYSVIEWLOGIC' | 'PFPLUGIN' | 'SCRIPT' | null | undefined;

    /**
     * 执行脚本代码
     * @memberof AppUITriggerEngine
     */
    public scriptCode: string | null | undefined;

    /**
     * Creates an instance of AppUITriggerEngine.
     * @memberof AppUITriggerEngine
     */
    constructor(opts: any) {
        this.options = opts;
        this.name = opts.name;
        this.scriptCode = opts.scriptCode;
        this.logicType = opts.logicType;
    }

    /**
     * 执行界面逻辑
     * @memberof AppUITriggerEngine
     */
    public async executeAsyncUILogic(opts: any) {
        if (this.logicType) {
            switch (this.logicType) {
                case 'DEUILOGIC':
                    return await this.executeAsyncDeUILogic(opts);
                case 'SYSVIEWLOGIC':
                    return await this.executeAsyncSysViewLogic(opts);
                case 'PFPLUGIN':
                    return await this.executeAsyncPluginLogic(opts);
                case 'SCRIPT':
                    return await this.executeAsyncScriptLogic(opts);
                default:
                    LogUtil.warn(`${this.logicType}暂未实现`);
                    break;
            }
        }
    }

    /**
     * 执行界面逻辑
     * @memberof AppUITriggerEngine
     */
    public executeUILogic(opts: any) {
        if (this.logicType) {
            switch (this.logicType) {
                case 'DEUILOGIC':
                    return this.executeDeUILogic(opts);
                case 'SYSVIEWLOGIC':
                    return this.executeSysViewLogic(opts);
                case 'PFPLUGIN':
                    return this.executePluginLogic(opts);
                case 'SCRIPT':
                    return this.executeScriptLogic(opts);
                default:
                    LogUtil.warn(`${this.logicType}暂未实现`);
                    break;
            }
        }
    }

    /**
     * 执行实体界面逻辑(异步)
     * @memberof AppUITriggerEngine
     */
    public async executeAsyncDeUILogic(opts: any) {
        const appDEUILogic = await getPSAppDEUILogicByModelObject(this.options);
        if (appDEUILogic) {
            const { context, viewparams: params, data: args, event: $event, xData, actionContext, srfParentDeName } = opts;
            await (window as any).UILogicService.onExecute(appDEUILogic, args, context, params, $event, xData, actionContext, srfParentDeName);
            return args;
        } else {
            LogUtil.warn('未找到实体界面处理逻辑对象');
            return false;
        }
    }

    /**
     * 执行系统预置界面逻辑(异步)
     * @memberof AppUITriggerEngine
     */
    public async executeAsyncSysViewLogic(opts: any) {
        LogUtil.warn('系统预置界面逻辑暂未实现');
    }

    /**
     * 执行插件界面逻辑(异步)
     * @memberof AppUITriggerEngine
     */
    public async executeAsyncPluginLogic(opts: any) {
        LogUtil.warn('插件界面逻辑暂未实现');
    }

    /**
     * 执行脚本界面逻辑(异步)
     * @memberof AppUITriggerEngine
     */
    public async executeAsyncScriptLogic(opts: any) {
        if (this.scriptCode) {
            return eval(this.scriptCode);
        }
    }

    /**
     * 执行实体界面逻辑
     * @memberof AppUITriggerEngine
     */
    public executeDeUILogic(opts: any) {
        LogUtil.warn('实体界面逻辑暂未实现');
    }

    /**
     * 执行系统预置界面逻辑
     * @memberof AppUITriggerEngine
     */
    public executeSysViewLogic(opts: any) {
        LogUtil.warn('系统预置界面逻辑暂未实现');
    }

    /**
     * 执行插件界面逻辑
     * @memberof AppUITriggerEngine
     */
    public executePluginLogic(opts: any) {
        LogUtil.warn('插件界面逻辑暂未实现');
    }

    /**
     * 执行脚本界面逻辑
     * @memberof AppUITriggerEngine
     */
    public executeScriptLogic(opts: any) {
        if (this.scriptCode) {
            return this.dispenseUILogic(opts, this.handleScriptCode(this.scriptCode));
        }
    }

    /**
     * 分发UI逻辑参数
     * @memberof AppUITriggerEngine
     */
    public dispenseUILogic(opts: any, scriptCode: string) {
        if (this.name) {
            switch (this.name.toLowerCase()) {
                case 'calcrowstyle':
                    return AppEmbeddedUILogic.calcRowStyle(opts, scriptCode);
                case 'calccellstyle':
                    return AppEmbeddedUILogic.calcCellStyle(opts, scriptCode);
                case 'calcheaderrowstyle':
                    return AppEmbeddedUILogic.calcRowStyle(opts, scriptCode);
                case 'calcheadercellstyle':
                    return AppEmbeddedUILogic.calcRowStyle(opts, scriptCode);
                default:
                    break;
            }
        }
    }

    /**
     * 处理自定义脚本
     *
     * @param scriptCode 自定义脚本
     * @memberof AppUITriggerEngine
     */
    public handleScriptCode(scriptCode: string) {
        if (scriptCode.indexOf('return') !== -1) {
            scriptCode = scriptCode.replace(new RegExp('return', 'g'), `result =`);
        }
        return scriptCode;
    }

}