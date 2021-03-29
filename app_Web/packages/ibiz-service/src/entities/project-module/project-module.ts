import { ProjectModuleBase } from './project-module-base';

/**
 * 任务模块
 *
 * @export
 * @class ProjectModule
 * @extends {ProjectModuleBase}
 * @implements {IProjectModule}
 */
export class ProjectModule extends ProjectModuleBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProjectModule
     */
    clone(): ProjectModule {
        return new ProjectModule(this);
    }
}
export default ProjectModule;
