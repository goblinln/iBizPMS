import ProjectStats_zh_CN_Base from './project-stats_zh_CN_base';

function getLocaleResource(){
    const ProjectStats_zh_CN_OwnData = {};
    const targetData = Object.assign(ProjectStats_zh_CN_Base(), ProjectStats_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;