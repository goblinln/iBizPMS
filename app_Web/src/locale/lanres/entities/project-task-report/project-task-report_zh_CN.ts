import ProjectTaskReport_zh_CN_Base from './project-task-report_zh_CN_base';

function getLocaleResource(){
    const ProjectTaskReport_zh_CN_OwnData = {};
    const targetData = Object.assign(ProjectTaskReport_zh_CN_Base(), ProjectTaskReport_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;