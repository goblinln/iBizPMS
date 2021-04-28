import { DocLibBase } from './doc-lib-base';

/**
 * 文档库
 *
 * @export
 * @class DocLib
 * @extends {DocLibBase}
 * @implements {IDocLib}
 */
export class DocLib extends DocLibBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof DocLib
     */
    clone(): DocLib {
        return new DocLib(this);
    }
}
export default DocLib;
