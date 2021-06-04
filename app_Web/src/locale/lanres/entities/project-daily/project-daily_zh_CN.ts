import ProjectDaily_zh_CN_Base from './project-daily_zh_CN_base';

function getLocaleResource(){
    const ProjectDaily_zh_CN_OwnData = {};
    const targetData = Object.assign(ProjectDaily_zh_CN_Base(), ProjectDaily_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;