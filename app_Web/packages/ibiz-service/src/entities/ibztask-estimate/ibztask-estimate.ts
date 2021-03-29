import { IBZTaskEstimateBase } from './ibztask-estimate-base';

/**
 * 任务预计
 *
 * @export
 * @class IBZTaskEstimate
 * @extends {IBZTaskEstimateBase}
 * @implements {IIBZTaskEstimate}
 */
export class IBZTaskEstimate extends IBZTaskEstimateBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZTaskEstimate
     */
    clone(): IBZTaskEstimate {
        return new IBZTaskEstimate(this);
    }
}
export default IBZTaskEstimate;
