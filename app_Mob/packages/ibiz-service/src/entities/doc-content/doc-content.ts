import { DocContentBase } from './doc-content-base';

/**
 * 文档内容
 *
 * @export
 * @class DocContent
 * @extends {DocContentBase}
 * @implements {IDocContent}
 */
export class DocContent extends DocContentBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof DocContent
     */
    clone(): DocContent {
        return new DocContent(this);
    }
}
export default DocContent;
