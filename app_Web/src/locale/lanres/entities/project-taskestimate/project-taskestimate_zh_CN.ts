import ProjectTaskestimate_zh_CN_Base from './project-taskestimate_zh_CN_base';

function getLocaleResource(){
    const ProjectTaskestimate_zh_CN_OwnData = {};
    const targetData = Object.assign(ProjectTaskestimate_zh_CN_Base(), ProjectTaskestimate_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;