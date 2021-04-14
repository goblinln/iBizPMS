import { IbzPlanTempletDetailBase } from './ibz-plan-templet-detail-base';

/**
 * 计划模板详情
 *
 * @export
 * @class IbzPlanTempletDetail
 * @extends {IbzPlanTempletDetailBase}
 * @implements {IIbzPlanTempletDetail}
 */
export class IbzPlanTempletDetail extends IbzPlanTempletDetailBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzPlanTempletDetail
     */
    clone(): IbzPlanTempletDetail {
        return new IbzPlanTempletDetail(this);
    }
}
export default IbzPlanTempletDetail;
