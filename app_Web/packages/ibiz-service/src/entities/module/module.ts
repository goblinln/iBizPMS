import { ModuleBase } from './module-base';

/**
 * 模块
 *
 * @export
 * @class Module
 * @extends {ModuleBase}
 * @implements {IModule}
 */
export class Module extends ModuleBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Module
     */
    clone(): Module {
        return new Module(this);
    }
}
export default Module;
