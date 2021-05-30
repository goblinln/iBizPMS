import { DocBase } from './doc-base';

/**
 * 文档
 *
 * @export
 * @class Doc
 * @extends {DocBase}
 * @implements {IDoc}
 */
export class Doc extends DocBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Doc
     */
    clone(): Doc {
        return new Doc(this);
    }
}
export default Doc;
