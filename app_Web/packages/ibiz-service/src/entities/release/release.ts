import { ReleaseBase } from './release-base';

/**
 * 发布
 *
 * @export
 * @class Release
 * @extends {ReleaseBase}
 * @implements {IRelease}
 */
export class Release extends ReleaseBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Release
     */
    clone(): Release {
        return new Release(this);
    }
}
export default Release;
