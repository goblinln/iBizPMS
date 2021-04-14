import { PlanTempletDetailBase } from './plan-templet-detail-base';

/**
 * 计划模板详情
 *
 * @export
 * @class PlanTempletDetail
 * @extends {PlanTempletDetailBase}
 * @implements {IPlanTempletDetail}
 */
export class PlanTempletDetail extends PlanTempletDetailBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof PlanTempletDetail
     */
    clone(): PlanTempletDetail {
        return new PlanTempletDetail(this);
    }
}
export default PlanTempletDetail;
