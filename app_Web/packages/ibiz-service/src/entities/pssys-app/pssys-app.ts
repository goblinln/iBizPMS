import { PSSysAppBase } from './pssys-app-base';

/**
 * 系统应用
 *
 * @export
 * @class PSSysApp
 * @extends {PSSysAppBase}
 * @implements {IPSSysApp}
 */
export class PSSysApp extends PSSysAppBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof PSSysApp
     */
    clone(): PSSysApp {
        return new PSSysApp(this);
    }
}
export default PSSysApp;
