import { BuildBase } from './build-base';

/**
 * 版本
 *
 * @export
 * @class Build
 * @extends {BuildBase}
 * @implements {IBuild}
 */
export class Build extends BuildBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Build
     */
    clone(): Build {
        return new Build(this);
    }
}
export default Build;
