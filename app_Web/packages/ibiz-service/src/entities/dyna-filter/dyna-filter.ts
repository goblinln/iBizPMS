import { DynaFilterBase } from './dyna-filter-base';

/**
 * 动态搜索栏
 *
 * @export
 * @class DynaFilter
 * @extends {DynaFilterBase}
 * @implements {IDynaFilter}
 */
export class DynaFilter extends DynaFilterBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof DynaFilter
     */
    clone(): DynaFilter {
        return new DynaFilter(this);
    }
}
export default DynaFilter;
