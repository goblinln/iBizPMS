import { IbzLibModuleBase } from './ibz-lib-module-base';

/**
 * 用例库模块
 *
 * @export
 * @class IbzLibModule
 * @extends {IbzLibModuleBase}
 * @implements {IIbzLibModule}
 */
export class IbzLibModule extends IbzLibModuleBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzLibModule
     */
    clone(): IbzLibModule {
        return new IbzLibModule(this);
    }
}
export default IbzLibModule;
