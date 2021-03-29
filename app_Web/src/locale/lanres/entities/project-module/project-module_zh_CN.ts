import ProjectModule_zh_CN_Base from './project-module_zh_CN_base';

function getLocaleResource(){
    const ProjectModule_zh_CN_OwnData = {};
    const targetData = Object.assign(ProjectModule_zh_CN_Base(), ProjectModule_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;