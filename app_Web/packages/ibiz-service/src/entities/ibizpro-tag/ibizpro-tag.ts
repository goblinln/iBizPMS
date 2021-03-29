import { IBIZProTagBase } from './ibizpro-tag-base';

/**
 * 标签
 *
 * @export
 * @class IBIZProTag
 * @extends {IBIZProTagBase}
 * @implements {IIBIZProTag}
 */
export class IBIZProTag extends IBIZProTagBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBIZProTag
     */
    clone(): IBIZProTag {
        return new IBIZProTag(this);
    }
}
export default IBIZProTag;
