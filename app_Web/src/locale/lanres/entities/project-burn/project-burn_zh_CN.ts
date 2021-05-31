import ProjectBurn_zh_CN_Base from './project-burn_zh_CN_base';

function getLocaleResource(){
    const ProjectBurn_zh_CN_OwnData = {};
    const targetData = Object.assign(ProjectBurn_zh_CN_Base(), ProjectBurn_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;