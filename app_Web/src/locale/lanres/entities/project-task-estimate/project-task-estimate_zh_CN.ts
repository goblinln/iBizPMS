import ProjectTaskEstimate_zh_CN_Base from './project-task-estimate_zh_CN_base';

function getLocaleResource(){
    const ProjectTaskEstimate_zh_CN_OwnData = {};
    const targetData = Object.assign(ProjectTaskEstimate_zh_CN_Base(), ProjectTaskEstimate_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;