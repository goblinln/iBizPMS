import { ProjectMonthlyBase } from './project-monthly-base';

/**
 * 项目月报
 *
 * @export
 * @class ProjectMonthly
 * @extends {ProjectMonthlyBase}
 * @implements {IProjectMonthly}
 */
export class ProjectMonthly extends ProjectMonthlyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProjectMonthly
     */
    clone(): ProjectMonthly {
        return new ProjectMonthly(this);
    }
}
export default ProjectMonthly;
