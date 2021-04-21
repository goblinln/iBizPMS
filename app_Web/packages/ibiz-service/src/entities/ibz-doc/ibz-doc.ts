import { IBzDocBase } from './ibz-doc-base';

/**
 * 文档
 *
 * @export
 * @class IBzDoc
 * @extends {IBzDocBase}
 * @implements {IIBzDoc}
 */
export class IBzDoc extends IBzDocBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBzDoc
     */
    clone(): IBzDoc {
        return new IBzDoc(this);
    }
}
export default IBzDoc;
