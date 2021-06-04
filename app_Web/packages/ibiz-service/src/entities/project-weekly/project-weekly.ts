import { ProjectWeeklyBase } from './project-weekly-base';

/**
 * 项目周报
 *
 * @export
 * @class ProjectWeekly
 * @extends {ProjectWeeklyBase}
 * @implements {IProjectWeekly}
 */
export class ProjectWeekly extends ProjectWeeklyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProjectWeekly
     */
    clone(): ProjectWeekly {
        return new ProjectWeekly(this);
    }
}
export default ProjectWeekly;
