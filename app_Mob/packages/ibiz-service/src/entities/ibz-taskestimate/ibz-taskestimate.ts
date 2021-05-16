import { IbzTaskestimateBase } from './ibz-taskestimate-base';

/**
 * 任务预计
 *
 * @export
 * @class IbzTaskestimate
 * @extends {IbzTaskestimateBase}
 * @implements {IIbzTaskestimate}
 */
export class IbzTaskestimate extends IbzTaskestimateBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzTaskestimate
     */
    clone(): IbzTaskestimate {
        return new IbzTaskestimate(this);
    }
    get srfmajortext() {
        return '';
    }
}
export default IbzTaskestimate;
