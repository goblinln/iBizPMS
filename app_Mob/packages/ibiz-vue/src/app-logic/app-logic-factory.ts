import { IPSAppDEUIAction, IPSSysPFPlugin } from "@ibiz/dynamic-model-api";
import { PluginService } from "../app-service/common-service/plugin-service";
import { AppBackEndAction } from "./app-backend-action";
import { AppFrontAction } from "./app-front-action";

export class AppLogicFactory {

    /**
     * 初始化AppLogicFactory
     * 
     * @memberof AppLogicFactory
     */
    constructor() { }

    /**
    * 获取界面行为
    * 
    * @public
    * @static
    * @memberof AppLogicFactory
    */
    public static async getInstance(modelData: IPSAppDEUIAction, context: any) {
        if (modelData.getPSSysPFPlugin()) {
            const importPlugin: any = PluginService.getInstance().getPluginInstance("UIACTION", (modelData.getPSSysPFPlugin() as IPSSysPFPlugin).pluginCode);
            if (importPlugin) {
                const importModule = await importPlugin();
                return new importModule.default(modelData, context);
            }
        } else {
            switch (modelData.uIActionMode) {
                case 'FRONT':
                    return new AppFrontAction(modelData, context);
                case 'BACKEND':
                    return new AppBackEndAction(modelData, context);
                default:
                    return undefined;
            }
        }

    }
}