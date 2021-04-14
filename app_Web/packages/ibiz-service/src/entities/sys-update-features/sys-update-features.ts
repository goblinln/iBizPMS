import { SysUpdateFeaturesBase } from './sys-update-features-base';

/**
 * 系统更新功能
 *
 * @export
 * @class SysUpdateFeatures
 * @extends {SysUpdateFeaturesBase}
 * @implements {ISysUpdateFeatures}
 */
export class SysUpdateFeatures extends SysUpdateFeaturesBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof SysUpdateFeatures
     */
    clone(): SysUpdateFeatures {
        return new SysUpdateFeatures(this);
    }
}
export default SysUpdateFeatures;
