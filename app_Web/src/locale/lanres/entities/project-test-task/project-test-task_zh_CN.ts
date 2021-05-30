import ProjectTestTask_zh_CN_Base from './project-test-task_zh_CN_base';

function getLocaleResource(){
    const ProjectTestTask_zh_CN_OwnData = {};
    const targetData = Object.assign(ProjectTestTask_zh_CN_Base(), ProjectTestTask_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;