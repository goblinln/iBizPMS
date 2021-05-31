import { ProjectTestTaskBase } from './project-test-task-base';

/**
 * 测试版本
 *
 * @export
 * @class ProjectTestTask
 * @extends {ProjectTestTaskBase}
 * @implements {IProjectTestTask}
 */
export class ProjectTestTask extends ProjectTestTaskBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProjectTestTask
     */
    clone(): ProjectTestTask {
        return new ProjectTestTask(this);
    }
}
export default ProjectTestTask;
