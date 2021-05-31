import { IBIZProPluginBase } from './ibizpro-plugin-base';

/**
 * 系统插件
 *
 * @export
 * @class IBIZProPlugin
 * @extends {IBIZProPluginBase}
 * @implements {IIBIZProPlugin}
 */
export class IBIZProPlugin extends IBIZProPluginBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBIZProPlugin
     */
    clone(): IBIZProPlugin {
        return new IBIZProPlugin(this);
    }
}
export default IBIZProPlugin;
