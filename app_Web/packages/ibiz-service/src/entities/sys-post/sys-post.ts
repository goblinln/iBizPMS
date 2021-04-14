import { SysPostBase } from './sys-post-base';

/**
 * 岗位
 *
 * @export
 * @class SysPost
 * @extends {SysPostBase}
 * @implements {ISysPost}
 */
export class SysPost extends SysPostBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof SysPost
     */
    clone(): SysPost {
        return new SysPost(this);
    }
}
export default SysPost;
