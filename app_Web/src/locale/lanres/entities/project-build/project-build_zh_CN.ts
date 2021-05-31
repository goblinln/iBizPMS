import ProjectBuild_zh_CN_Base from './project-build_zh_CN_base';

function getLocaleResource(){
    const ProjectBuild_zh_CN_OwnData = {};
    const targetData = Object.assign(ProjectBuild_zh_CN_Base(), ProjectBuild_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;