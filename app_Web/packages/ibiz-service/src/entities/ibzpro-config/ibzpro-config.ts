import { IbzproConfigBase } from './ibzpro-config-base';

/**
 * 系统配置表
 *
 * @export
 * @class IbzproConfig
 * @extends {IbzproConfigBase}
 * @implements {IIbzproConfig}
 */
export class IbzproConfig extends IbzproConfigBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzproConfig
     */
    clone(): IbzproConfig {
        return new IbzproConfig(this);
    }
}
export default IbzproConfig;
