import { IBIZProKeywordBase } from './ibizpro-keyword-base';

/**
 * 关键字
 *
 * @export
 * @class IBIZProKeyword
 * @extends {IBIZProKeywordBase}
 * @implements {IIBIZProKeyword}
 */
export class IBIZProKeyword extends IBIZProKeywordBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBIZProKeyword
     */
    clone(): IBIZProKeyword {
        return new IBIZProKeyword(this);
    }
}
export default IBIZProKeyword;
