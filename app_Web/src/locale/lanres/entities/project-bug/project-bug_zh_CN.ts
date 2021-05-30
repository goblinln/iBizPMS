import ProjectBug_zh_CN_Base from './project-bug_zh_CN_base';

function getLocaleResource(){
    const ProjectBug_zh_CN_OwnData = {};
    const targetData = Object.assign(ProjectBug_zh_CN_Base(), ProjectBug_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;