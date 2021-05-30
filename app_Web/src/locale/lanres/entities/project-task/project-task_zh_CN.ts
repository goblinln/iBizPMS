import ProjectTask_zh_CN_Base from './project-task_zh_CN_base';

function getLocaleResource(){
    const ProjectTask_zh_CN_OwnData = {};
    const targetData = Object.assign(ProjectTask_zh_CN_Base(), ProjectTask_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;